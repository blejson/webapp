package com.blejson.webapp.repositories;

import com.blejson.webapp.domain.PostMessage;
import org.springframework.data.repository.CrudRepository;

public interface PostMessageRepository extends CrudRepository<PostMessage, Long> {
}
