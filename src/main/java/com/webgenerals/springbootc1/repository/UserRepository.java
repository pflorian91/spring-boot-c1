package com.webgenerals.springbootc1.repository;

import com.webgenerals.springbootc1.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findUserByEmail(String email);

    UserEntity findByUserId(String userId);
}
