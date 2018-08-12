package com.ktk.dynamo.dynamotest.service;

import com.ktk.dynamo.dynamotest.model.User;
import com.ktk.dynamo.dynamotest.web.restcontroller.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User addUser(UserDTO userDTO);

    List<User> listUsers();

    List<User> findUserByFirstName(String firstName);

    Optional<User> findUserById(String id);
}

