package com.blog.post.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.post.entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

}
