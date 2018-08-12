package com.ktk.dynamo.dynamotest.service;

import com.ktk.dynamo.dynamotest.model.UserInfo;

import java.util.Optional;

public interface UserInfoService {

    UserInfo createUserInfo(UserInfo userInfo);

    Optional<UserInfo> findUserInfo(String username);
}
