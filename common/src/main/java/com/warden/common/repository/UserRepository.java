package com.warden.common.repository;

import com.warden.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author warden
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long>, JpaRepository<User, Long> {
    User findByUserName(String username);
}
