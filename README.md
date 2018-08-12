# This project tests SpringBoot with DynamoDB running locally.

## Install DynamoDB locally
Download DynamoDB to run locally.
# https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html

Now start locally - default port is 8000 when running locally.
```
$ cd /opt/Kinektek/KinektekProjectCode/TestSpringBootApps/SpringBootDynamoDB
$ java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb -inMemory
```
Optional parameters:

    * --sharedDb -> Share one db file among all the credential and regions.
    * --inMemory -> runs in memory instead of using a database file.

Now create a test table
```
$ aws dynamodb create-table --table-name TestUser --attribute-definitions AttributeName=Id,AttributeType=S AttributeName=FirstName,AttributeType=S --key-schema AttributeName=Id,KeyType=HASH AttributeName=FirstName,KeyType=RANGE --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://localhost:8000
```

List table in db
```
$ aws dynamodb list-tables --endpoint-url http://localhost:8000
```

Insert two test records
```$xslt
$ aws dynamodb put-item --table-name User --item '{ "Id" : {"S": "1"} , "FirstName": {"S":"John"}}' --endpoint-url http://localhost:8000
$ aws dynamodb put-item --table-name User --item '{ "Id" : {"S": "1"} , "FirstName": {"S":"Peter"}}' --endpoint-url http://localhost:8000
```

## Download the project and import as Maven project. 
Start by running the main class or using : mvn springboot:run

Couple of endpoints are made available for testing:
* [http://localhost:8080/users/v1/list](http://localhost:8080/users/v1/list)
* [http://localhost:8080/users/v1/list1](http://localhost:8080/users/v1/list1)
* [http://localhost:8080/users/v1/find-by-firstname/JohnyBoy1](http://localhost:8080/users/v1/find-by-firstname/JohnyBoy1)

Use Postman to do a post to the following url with JSON body
* [http://localhost:8080/users/v1/save](http://localhost:8080/users/v1/save)
```
{
	"firstName":"JohnyBoy1"
}
```

This is very basic, but will provide some proof of concept ideas.