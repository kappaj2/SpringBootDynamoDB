package com.ktk.dynamo.dynamotest.repository;

import com.ktk.dynamo.dynamotest.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findByFirstName(String firstName);

}
