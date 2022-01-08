package com.webgenerals.springbootc1.service.impl;

import com.webgenerals.springbootc1.dto.AddressDto;
import com.webgenerals.springbootc1.dto.UserDto;
import com.webgenerals.springbootc1.entity.PasswordResetTokenEntity;
import com.webgenerals.springbootc1.entity.UserEntity;
import com.webgenerals.springbootc1.exceptions.UserServiceException;
import com.webgenerals.springbootc1.repository.PasswordResetTokenRepository;
import com.webgenerals.springbootc1.repository.UserRepository;
import com.webgenerals.springbootc1.response.ErrorMessages;
import com.webgenerals.springbootc1.service.UserService;
import com.webgenerals.springbootc1.util.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserServiceImpl
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Record already exists");
        }

        for (int i = 0; i < user.getAddresses().size(); i++) {
            AddressDto addressDto = user.getAddresses().get(i);
            addressDto.setUserDetails(user);
            addressDto.setAddressId(utils.generateAddressId(30));
            user.getAddresses().set(i, addressDto);
        }

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setEmailVerificationStatus(Boolean.FALSE);
        userEntity.setEmailVerificationToken(Utils.generateEmailVerificationToken(publicUserId));

        UserEntity storedUserDetails = userRepository.save(userEntity);

        return modelMapper.map(storedUserDetails, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);

        return userRepository.findAll(pageable).getContent().stream().map(userEntity -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            return userDto;
        }).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity user = userRepository.findUserByEmail(email);

        if (user == null) throw new UsernameNotFoundException(email);

        UserDto returnValue = new UserDto();

        BeanUtils.copyProperties(user, returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserByEmail(email);

        if (user == null) throw new UsernameNotFoundException(email);

        return new User(user.getEmail(), user.getEncryptedPassword(), user.getEmailVerificationStatus(), true, true, true, new ArrayList<>());
    }

    @Override
    public UserDto getUserByUserId(String id) {
        UserEntity user = userRepository.findByUserId(id);

        if (user == null) throw new UsernameNotFoundException(id);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(user, returnValue);

        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) {
        UserDto returnValue = new UserDto();

        UserEntity userEntity = userRepository.findByUserId(userId);

        if (null == userEntity) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        UserEntity updatedUser = userRepository.save(userEntity);

        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;
    }

    @Override
    public void deleteUser(String id) {
        UserEntity userEntity = userRepository.findByUserId(id);

        if (userEntity == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        userRepository.delete(userEntity);
    }

    @Override
    public boolean verifyEmailToken(String token) {
        boolean returnValue = false;

        UserEntity user = userRepository.findUserByEmailVerificationToken(token);

        if (null != user && false == Utils.hasTokenExpired(token)) {
            user.setEmailVerificationToken(null);
            user.setEmailVerificationStatus(Boolean.TRUE);
            userRepository.save(user);
            returnValue = true;
        }

        return returnValue;
    }

    @Override
    public boolean requestPasswordReset(String email) {
        boolean returnValue = false;

        UserEntity userEntity = userRepository.findUserByEmail(email);

        if (userEntity == null) {
            return returnValue;
        }

        String token = Utils.generatePasswordResetToken(userEntity.getUserId());

        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setUserDetails(userEntity);

        passwordResetTokenRepository.save(passwordResetTokenEntity);

        returnValue = true;

        return returnValue;
    }

    @Override
    public boolean resetPassword(String token, String password) {
        boolean returnValue = false;

        if (Utils.hasTokenExpired(token)) {
            return returnValue;
        }

        PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepository.findByToken(token);

        if (passwordResetTokenEntity == null) {
            return returnValue;
        }

        String encodedPassword = bCryptPasswordEncoder.encode(password);

        UserEntity userDetails = passwordResetTokenEntity.getUserDetails();
        userDetails.setEncryptedPassword(encodedPassword);

        UserEntity savedUserEntity = userRepository.save(userDetails);

        // verify password was saved successfully
        if (savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
            returnValue = true;
        }

        passwordResetTokenRepository.delete(passwordResetTokenEntity);

        return returnValue;
    }
}
