package com.repository;

import com.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {

    User findUserByUsername(String username);
}
