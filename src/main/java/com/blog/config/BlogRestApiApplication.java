package com.blog.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
* <h1>Blog RestApi Application</h1>
* The BlogRestApiApplication is the configuration class
* Used to setup Spring Application to handle blog related request
* Start the application
* 
*
* @author  Muhammad Saqib
* @version 1.0
* @since   2018-09-07 
*/
@SpringBootApplication
@ComponentScan({"com.blog"})
@EnableJpaRepositories(basePackages = { "com.blog.post.repository", "com.blog.comment.repository"})
@EntityScan(basePackages = { "com.blog.post.entity", "com.blog.comment.entity"})
public class BlogRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}
}
