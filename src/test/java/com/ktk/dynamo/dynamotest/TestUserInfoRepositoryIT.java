package com.ktk.dynamo.dynamotest;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.ktk.dynamo.dynamotest.model.UserInfo;
import com.ktk.dynamo.dynamotest.repository.UserInfoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DynamoTestApplication.class})
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=testKey",
        "amazon.aws.secretkey=testSecret"
})
public class TestUserInfoRepositoryIT {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Before
    public void setup() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        DescribeTableRequest request = new DescribeTableRequest().withTableName(UserInfo.class.getSimpleName());

        if(!amazonDynamoDB.describeTable(request).getTable().getTableStatus().equals("ACTIVE")) {
            CreateTableRequest tableRequest = dynamoDBMapper
                    .generateCreateTableRequest(UserInfo.class);

            tableRequest.setProvisionedThroughput(
                    new ProvisionedThroughput(1L, 1L));
            amazonDynamoDB.createTable(tableRequest);
        }
    }

    @Test
    public void testCreateUserInfo() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setDateChanged(new Date());
        userInfo.setNewPassword("NewPassword");
        userInfo.setOldPassword("OldPassword");
        userInfo.setUsername("TestUsername");

        UserInfo newUserInfo = userInfoRepository.save(userInfo);

        assertNotNull(newUserInfo.getId());
        assertEquals(newUserInfo.getNewPassword(), "NewPassword");
        assertEquals(newUserInfo.getUsername(), "TestUsername");

    }

}
