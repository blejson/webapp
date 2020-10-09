package com.blejson.webapp.repositories;

import com.blejson.webapp.domain.Conversation;
import com.blejson.webapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by Blejson on 08.10.2020
 */
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByUser1(User user);
    List<Conversation> findByUser2(User user);

    @Query("select c from Conversation c where (c.user1 = :user1 and c.user2 = :user2) or (c.user1 = :user2 and c.user2 = :user1)")
    Optional<Conversation> findByUsers(@Param("user1") User user1, @Param("user2") User user2);

    @Query("select c from Conversation c where (c.user1 = :user and c.id = :id) or (c.user2 = :user and c.id = :id)")
    Optional<Conversation> findByUserAndId(@Param("user") User user, @Param("id") Long id);
}
