package com.ktk.dynamo.dynamotest.web.restcontroller;

import com.ktk.dynamo.dynamotest.model.User;
import com.ktk.dynamo.dynamotest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * List all the users in the database
     *
     * @return
     */
    @GetMapping("/list")
    public List<User> findAllUsers() {
        return userService.listUsers();
    }

    /**
     * List all the users in the database. Use ResponseEntity return type.
     *
     * @return
     */
    @GetMapping("/list1")
    public ResponseEntity<List<User>> list() {
        List<User> userList = userService.listUsers();
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    /**
     * Seatch for a user using the entituy id field.
     *
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    public ResponseEntity find(@PathVariable final String id) {

        Optional<User> userOptional = userService.findUserById(id);

        if (userOptional.isPresent()) {
            return new ResponseEntity(userOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * Search for a user using the firstName field.
     *
     * @param firstname
     * @return
     */
    @GetMapping("/find-by-firstname/{firstname}")
    public ResponseEntity<List<User>> findByFirstName(@PathVariable final String firstname) {
        return new ResponseEntity(userService.findUserByFirstName(firstname), HttpStatus.OK);
    }

    /**
     * Create a new user entity.
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity saveNewUser(@RequestBody UserDTO userDTO) {
        User newUser = userService.addUser(userDTO);
        return newUser == null ? new ResponseEntity("Error creating user", HttpStatus.BAD_REQUEST)
                : new ResponseEntity(newUser, HttpStatus.CREATED);

    }
}
