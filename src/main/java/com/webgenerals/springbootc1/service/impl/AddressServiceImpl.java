package com.webgenerals.springbootc1.service.impl;

import com.webgenerals.springbootc1.dto.AddressDto;
import com.webgenerals.springbootc1.entity.AddressEntity;
import com.webgenerals.springbootc1.entity.UserEntity;
import com.webgenerals.springbootc1.repository.AddressRepository;
import com.webgenerals.springbootc1.repository.UserRepository;
import com.webgenerals.springbootc1.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * AddressServiceImpl
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAddressesByUserId(String userId) {
        List<AddressDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            return returnValue;
        }

        Iterable<AddressEntity> addressEntities = addressRepository.findAllByUserDetails(userEntity);
        for (AddressEntity addressEntity : addressEntities) {
            returnValue.add(modelMapper.map(addressEntity, AddressDto.class));
        }

        return returnValue;
    }

    @Override
    public AddressDto getAddressById(String addressId) {
        AddressDto returnValue = null;

        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
        ModelMapper modelMapper = new ModelMapper();
        if (null != addressEntity) {
            returnValue = modelMapper.map(addressEntity, AddressDto.class);
        }

        return returnValue;
    }
}
