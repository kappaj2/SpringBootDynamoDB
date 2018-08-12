package com.ktk.dynamo.dynamotest.repository;

import com.ktk.dynamo.dynamotest.model.UserInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface UserInfoRepository extends CrudRepository<UserInfo, String> {

    Optional<UserInfo> findUserInfoByUsername(String username);
}
