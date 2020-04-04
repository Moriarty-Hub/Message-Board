package com.repository;

import com.entity.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    Iterable<Message> findAllByOrderByCreateTimeDesc();
}
