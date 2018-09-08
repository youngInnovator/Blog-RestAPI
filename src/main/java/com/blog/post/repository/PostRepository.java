package com.blog.post.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.post.entity.Post;

/**
* <h1>Post Repository</h1>
* The PostRepository is repository class
* Used to execute SQL and HQL queries and return results as Java Objects
* See Spring-Data for more info.
* 
*
* @author  Muhammad Saqib
* @version 1.0
* @since   2018-09-07 
*/
@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

}
