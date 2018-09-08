package com.blog.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.comment.CommentService;
import com.blog.comment.entity.Comment;
import com.blog.common.EntityNotFoundException;
import com.blog.post.entity.Post;

/**
* <h1>Post Controller</h1>
* The PostController is rest controller class
* Used to accept requests from user forward them to service to retrieve results
* Convert Java objects to Json objects and return back as http response
* 
*
* @author  Muhammad Saqib
* @version 1.0
* @since   2018-09-07 
*/
@RestController
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;

	/**
	 * Method return all of the posts with their comments
	 * @return List<Post> List of all posts
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}
	
	/**
	 * Method takes id of post and return respective post
	 * throws exception in case of post with specific id not found in database
	 * @param postId Id of the post
	 * @return Post
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Post getPost(@PathVariable("id") Integer postId) throws EntityNotFoundException {
		return postService.getPostById(postId);
	}

	/**
	 * Method takes Post and insert new post into database
	 * return inserted post
	 * @param post Post object
	 * @return inserted post
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Post addPost(@RequestBody Post post) {
		return postService.addPost(post);
	}

	/**
	 * Method takes Post and update values of post into database
	 * return updated post
	 * throws exception in case of post with specific id not found in database
	 * @param post Post object
	 * @return updated post
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Post updatePost(@RequestBody Post post) {
		return postService.updatePost(post);
	}

	/**
	 * Method takes id of post and delete it from database
	 * return id of deleted post
	 * throws exception in case of post with specific id not found in database
	 * @param postId Id of the post
	 * @return id of deleted post
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Integer deletePost(@PathVariable("id") Integer postId) throws EntityNotFoundException {
		return postService.deletePostById(postId);
	}
	
	/**
	 * Method takes id of post
	 * return list of comments for that post
	 * @param postId Id of the post
	 * @return List<Comment> List of comments for specific post
	 */
	@RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
	public List<Comment> getAllPostComments(@PathVariable("id") Integer postId) {
		return commentService.getAllCommentByPost(postId);
	}
	
	/**
	 * Method takes comment and postId and insert new comment into database for specific post
	 * return inserted comment
	 * @param comment Comment object
	 * @param postId Id of the post
	 * @return inserted comment
	 */
	@RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
	public Comment addPostComment(@RequestBody Comment comment, @PathVariable("id") Integer postId) throws EntityNotFoundException {
		return commentService.addPostComment(comment, postId);
	}

	/**
	 * Method takes Comment and update values of comment into database
	 * return updated comment
	 * throws exception in case of comment with specific id not found in database
	 * @param comment Comment object
	 * @return updated comment
	 * @throws EntityNotFoundException
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	private ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}

}
