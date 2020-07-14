package com.blejson.webapp.repositories;

import com.blejson.webapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Blejson on 14.07.2020
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
