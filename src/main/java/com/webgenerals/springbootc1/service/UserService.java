package com.webgenerals.springbootc1.service;

import com.webgenerals.springbootc1.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * UserService
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);

    UserDto getUser(String email);

    UserDto getUserByUserId(String id);

    UserDto updateUser(String id, UserDto user);

    void deleteUser(String id);

    List<UserDto> getUsers(int page, int limit);

    boolean verifyEmailToken(String token);
}
