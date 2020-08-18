package com.blejson.webapp.repositories;

import com.blejson.webapp.domain.PostMessage;
import com.blejson.webapp.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostMessageRepository extends CrudRepository<PostMessage, Long> {
    List<PostMessage> findByAuthor(User author);
}
