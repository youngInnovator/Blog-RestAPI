package com.blog.comment.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.comment.entity.Comment;
import com.blog.post.entity.Post;

/**
* <h1>Comment Repository</h1>
* The CommentRepository is repository class
* Used to execute SQL and HQL queries and return results as Java Objects
* See Spring-Data for more info.
* 
*
* @author  Muhammad Saqib
* @version 1.0
* @since   2018-09-07 
*/
@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

	/**
	 * Method finds all the comments related to post
	 * @param post
	 * @return List of comments
	 */
	public List<Comment> findByPost(Post post);
}
