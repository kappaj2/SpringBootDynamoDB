package com.ktk.dynamo.dynamotest.service.impl;

import com.ktk.dynamo.dynamotest.model.User;
import com.ktk.dynamo.dynamotest.repository.UserRepository;
import com.ktk.dynamo.dynamotest.service.UserService;
import com.ktk.dynamo.dynamotest.web.restcontroller.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that will handle all the interaction with the repository.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Add a new user using the DTO as input.
     *
     * @param userDTO
     * @return
     */
    @Override
    public User addUser(UserDTO userDTO) {

        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return userRepository.save(user);
    }

    /**
     * List all the users.
     *
     * @return
     */
    @Override
    public List<User> listUsers() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Find by firstName field
     *
     * @param firstName
     * @return
     */
    @Override
    public List<User> findUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    /**
     * Find by id fields
     *
     * @param id
     * @return
     */
    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }
}
