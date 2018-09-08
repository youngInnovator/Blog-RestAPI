package com.blog.post;

import java.util.List;

import com.blog.common.EntityNotFoundException;
import com.blog.post.entity.Post;

/**
* <h1>Post Service</h1>
* The PostService is spring service inteface
* Used to accept comment objects from controller
* Process objects if required
* Forward them to repository layer to execute database queries.
* 
*
* @author  Muhammad Saqib
* @version 1.0
* @since   2018-09-07 
*/
public interface PostService {
	
	/**
	 * Method return all of the posts with their comments
	 * @return List<Post> List of all posts
	 */
	public List<Post> getAllPosts();
	
	/**
	 * Method takes id of post and return respective post
	 * throws exception in case of post with specific id not found in database
	 * @param postId Id of the post
	 * @return Post
	 * @throws EntityNotFoundException
	 */
	public Post getPostById(Integer id) throws EntityNotFoundException;
	
	/**
	 * Method takes Post and update values of post into database
	 * return updated post
	 * throws exception in case of post with specific id not found in database
	 * @param post Post object
	 * @return updated post
	 * @throws EntityNotFoundException
	 */
	public Post updatePost(Post post);
	
	/**
	 * Method takes Post and insert new post into database
	 * return inserted post
	 * @param post Post object
	 * @return inserted post
	 */
	public Post addPost(Post post);
	
	/**
	 * Method takes id of post and delete it from database
	 * return id of deleted post
	 * throws exception in case of post with specific id not found in database
	 * @param postId Id of the post
	 * @return id of deleted post
	 * @throws EntityNotFoundException
	 */
	public Integer deletePostById(Integer id) throws EntityNotFoundException;

}
