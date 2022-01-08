package com.webgenerals.springbootc1.repository;

import com.webgenerals.springbootc1.entity.PasswordResetTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * PasswordResetTokenRepository
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenEntity, Long> {

    PasswordResetTokenEntity findByToken(String token);

}
