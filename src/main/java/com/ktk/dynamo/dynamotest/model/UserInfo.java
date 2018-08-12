package com.ktk.dynamo.dynamotest.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.util.Date;

@Data
@DynamoDBTable(tableName = "UserInfo")
public class UserInfo {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private String username;

    @DynamoDBAttribute
    private String oldPassword;

    @DynamoDBAttribute
    private String newPassword;

    @DynamoDBAttribute
    private Date dateChanged;

}