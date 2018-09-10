# Blog - Rest API
Simple Restful CRUD API for **Blog** for POC or demo

## Technologies used
* Spring Boot
* Spring Rest
* Spring Data
* MySQL database for persistent

## Requirements
1. Java - 1.8.x
2. Maven - 3.x.x
3. MySQL - 5.x.x

## Steps to Setup
### 1. Clone the application
  - clone this project into your local directory: 
  `git clone https://github.com/youngInnovator/Blog-RestAPI.git`
### 2. Create MySQL database
In order to achieve persistence this project relies on MySQL.

[MySQL](https://dev.mysql.com/downloads/) version 5.6 or better. If you have Docker installed it might be useful to run the database as a [container](https://hub.docker.com/_/mysql/).

Create the database for MySQL using following steps.
```mysql
mysql> create database db_blog; -- Create the new database
mysql> create user 'blog'@'localhost' identified by 'blog'; -- Creates the user
mysql> grant all on db_example.* to 'blog'@'localhost'; -- Gives all the privileges to the new user on the newly created database
```

### 3. Change MySQL username and password as per your installation
* open `src/main/resources/application.properties`
* change `spring.datasource.username` and `spring.datasource.password` as per your MySQL installation (if required)

### 4. Build and run the app using maven
  - Build the project:
  
  ```
  mvn clean install
  ```
  
  - Run the `blog` project:
  
  ```
  java -jar blog-0.0.1-SNAPSHOT.jar
  ```
  
  Alternatively, you can run the app without packaging it using -
  
  ```
  mvn spring-boot:run
  ```
  
  The app will start running at [http://localhost:8080](http://localhost:8080).
  
  ### Run unit test
  ```
  mvn test
  ```
## Explore Rest APIs
The app defines following CRUD APIs.
1. Get all posts
```
GET /blog/post/
```
2. Get specific post

```
GET /blog/post/{postId}
```
3. Add new post

```
POST /blog/post/
```
4. Update existing post

```
PUT /blog/post/
```
5. Delete specific post

```
DELETE /blog/post/{postId}
```
6. Get specific post comments

```
GET /blog/post/{postId}/comments
```

7. Add new comment to specific post

```
POST /blog/post/{postId}/comment
```

8. Get specific comment

```
GET /blog/comment/{commentId}
```

9. Like specific comment

```
POST /blog/comment/{commentId}/like
```
10. Update existing comment

```
PUT /blog/comment/
```
11. Delete specific comment

```
DELETE /blog/comment/{postId}
```
