package com.webgenerals.springbootc1.service;

import com.webgenerals.springbootc1.dto.AddressDto;

import java.util.List;

/**
 * AddressService
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public interface AddressService {

    List<AddressDto> getAddressesByUserId(String userId);
    AddressDto getAddressById(String addressId);
}
