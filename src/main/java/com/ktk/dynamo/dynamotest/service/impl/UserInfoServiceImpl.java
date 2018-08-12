package com.ktk.dynamo.dynamotest.service.impl;

import com.ktk.dynamo.dynamotest.model.UserInfo;
import com.ktk.dynamo.dynamotest.repository.UserInfoRepository;
import com.ktk.dynamo.dynamotest.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoRepository userInfoRepository;

    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserInfo createUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public Optional<UserInfo> findUserInfo(String username) {
        return userInfoRepository.findUserInfoByUsername(username);
    }
}
