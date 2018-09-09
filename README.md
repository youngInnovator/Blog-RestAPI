# Blog-RestAPI
This is the poc or demo project for Blog - Rest API

## Technoliges used
* Spring Boot
* Spring Rest
* Spring Data
* MySQL database for persistent

## Pre-req to run the project
In order to achive persistance this project relies on MySQL.
To Install MySQL follow the instructions here.
Create the database for MySQL using following steps.

```mysql
mysql> create database db_blog; -- Create the new database
mysql> create user 'blog'@'localhost' identified by 'blog'; -- Creates the user
mysql> grant all on db_example.* to 'blog'@'localhost'; -- Gives all the privileges to the new user on the newly created database
```

application.properties file at following location contains database configurations, you can change according to your MySQL setup.
### How to run:
  - clone this project into your local directory: 
  `git clone https://github.com/youngInnovator/Blog-RestAPI.git`
  - Build the project: 
  `mvn clean install`
  - Run the `spring-bootstrap` project:
  `java -jar word-counter-0.0.1-SNAPSHOT-spring-boot.jar`
  
  ### Run unit test

