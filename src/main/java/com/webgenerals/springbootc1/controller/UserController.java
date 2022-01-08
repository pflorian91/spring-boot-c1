package com.webgenerals.springbootc1.controller;

import com.webgenerals.springbootc1.dto.AddressDto;
import com.webgenerals.springbootc1.dto.UserDto;
import com.webgenerals.springbootc1.exceptions.UserServiceException;
import com.webgenerals.springbootc1.model.PasswordResetModel;
import com.webgenerals.springbootc1.model.PasswordResetRequestModel;
import com.webgenerals.springbootc1.model.UserDetailsRequestModel;
import com.webgenerals.springbootc1.response.*;
import com.webgenerals.springbootc1.service.AddressService;
import com.webgenerals.springbootc1.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserController
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

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

    @GetMapping(
            path = "/{userId}/addresses",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public CollectionModel<AddressRestResponse> getAddresses(@PathVariable String userId) {
        List<AddressRestResponse> responses = new ArrayList<>();

        List<AddressDto> addressDtos = addressService.getAddressesByUserId(userId);

        if (addressDtos != null && !addressDtos.isEmpty()) {
            Type type = new TypeToken<List<AddressRestResponse>>() {
            }.getType();
            ModelMapper modelMapper = new ModelMapper();
            responses = modelMapper.map(addressDtos, type);

            for (AddressRestResponse addressRestResponse : responses) {
                Link selfLink = WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getAddressById(userId, addressRestResponse.getAddressId()))
                        .withSelfRel();
                addressRestResponse.add(selfLink);
            }
        }

        Link userLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(userId).withRel("user");
        Link selfRel = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getAddresses(userId)).withSelfRel();

        return CollectionModel.of(responses, userLink, selfRel);
    }

    @GetMapping(
            path = "/{userId}/addresses/{addressId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public EntityModel<AddressRestResponse> getAddressById(@PathVariable String userId, @PathVariable String addressId) {
        EntityModel<AddressRestResponse> entityModel = null;

        AddressDto address = addressService.getAddressById(addressId);

        if (address != null) {
            ModelMapper modelMapper = new ModelMapper();
            AddressRestResponse response = modelMapper.map(address, AddressRestResponse.class);

            // http://localhost:8080/users/<userId>
            Link userRel = WebMvcLinkBuilder.linkTo(UserController.class).slash(userId).withRel("user");
            // using methodOn
            Link addressesRel = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getAddresses(userId))
                    .withRel("addresses2");

            Link selfRel = WebMvcLinkBuilder.linkTo(UserController.class).slash(userId).slash("addresses").slash(addressId).withSelfRel();

            entityModel = EntityModel.of(response, Arrays.asList(userRel, addressesRel, selfRel));
        }

        return entityModel;
    }

    // /users/email-verification?token=s262hs7jsd21
    @GetMapping(
            path = "/email-verification",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public OperationStatusModel verifyEmailToken(@RequestParam(value = "token") String token) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());

        boolean isVerified = userService.verifyEmailToken(token);

        if (isVerified) {
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        } else {
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
        }

        return returnValue;
    }

    @PostMapping(
            path = "/password-reset-request",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public OperationStatusModel requestResetPassword(@RequestBody PasswordResetRequestModel model) {
        OperationStatusModel returnValue = new OperationStatusModel();

        boolean operationResult = userService.requestPasswordReset(model.getEmail());

        returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
        returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

        if (operationResult) {
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }

        return returnValue;
    }

    @PostMapping(
            path = "/password-reset",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public OperationStatusModel resetPassword(@RequestBody PasswordResetModel model) {
        OperationStatusModel returnValue = new OperationStatusModel();

        boolean operationResult = userService.resetPassword(
                model.getToken(),
                model.getPassword()
        );

        returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
        returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

        if (operationResult) {
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }

        return returnValue;
    }
}
