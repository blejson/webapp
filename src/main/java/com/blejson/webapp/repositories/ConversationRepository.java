package com.blejson.webapp.repositories;

import com.blejson.webapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Blejson on 08.10.2020
 */
public interface ConversationRepository extends JpaRepository<User, Long> {
}
