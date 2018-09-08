package com.blog.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.blog"})
@EnableJpaRepositories(basePackages = { "com.blog.post.repository", "com.blog.comment.repository"})
@EntityScan(basePackages = { "com.blog.post.entity", "com.blog.comment.entity"})
public class BlogRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}
}
