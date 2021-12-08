package com.webgenerals.springbootc1.controller;

import com.webgenerals.springbootc1.dto.UserDto;
import com.webgenerals.springbootc1.exceptions.UserServiceException;
import com.webgenerals.springbootc1.model.UserDetailsRequestModel;
import com.webgenerals.springbootc1.response.ErrorMessages;
import com.webgenerals.springbootc1.response.OperationStatusModel;
import com.webgenerals.springbootc1.response.RequestOperationStatus;
import com.webgenerals.springbootc1.response.UserRestResponse;
import com.webgenerals.springbootc1.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserController
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public List<UserRestResponse> getUsers(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return userService.getUsers(page, limit)
                .stream().map(userDto -> {
                    UserRestResponse returnValue = new UserRestResponse();
                    BeanUtils.copyProperties(userDto, returnValue);
                    return returnValue;
                }).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRestResponse getUser(@PathVariable String id) {
        UserRestResponse returnValue = new UserRestResponse();

        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);

        return returnValue;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRestResponse createUser(@RequestBody UserDetailsRequestModel userdetails) throws Exception {

        if (userdetails.getFirstName().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userdetails, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);
        return modelMapper.map(createdUser, UserRestResponse.class);
    }

    @PutMapping(
            path = "/{id}",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRestResponse updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String id) {
        UserRestResponse returnValue = new UserRestResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto updatedUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;
    }

    @DeleteMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        userService.deleteUser(id);

        return returnValue;
    }
}
