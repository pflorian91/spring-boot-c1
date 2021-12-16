package com.webgenerals.springbootc1.repository;

import com.webgenerals.springbootc1.entity.AddressEntity;
import com.webgenerals.springbootc1.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AddressRepository
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByUserDetails(UserEntity userEntity);

    AddressEntity findByAddressId(String addressId);
}
