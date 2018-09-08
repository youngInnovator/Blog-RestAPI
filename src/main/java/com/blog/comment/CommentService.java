package com.blog.comment;

import java.util.List;

import com.blog.comment.entity.Comment;
import com.blog.common.EntityNotFoundException;

/**
* <h1>Comment Service</h1>
* The CommentService is spring service inteface
* Used to accept comment objects from controller
* Process objects if required
* Forward them to repository layer to execute database queries.
* 
*
* @author  Muhammad Saqib
* @version 1.0
* @since   2018-09-07 
*/
public interface CommentService {
	
	/**
	 * Method takes id of post
	 * return list of comments for that post
	 * @param postId Id of the post
	 * @return List<Comment> List of comments for specific post
	 */
	public List<Comment> getAllCommentByPost(Integer postId);
	
	/**
	 * Method takes id of comment and return respective comment
	 * throws exception in case of comment with specific id not found in database
	 * @param commentId Id of the comment
	 * @return Comment
	 * @throws EntityNotFoundException
	 */
	public Comment getCommentById(Integer id) throws EntityNotFoundException;
	
	/**
	 * Method takes Comment and update values of comment into database
	 * return updated comment
	 * throws exception in case of comment with specific id not found in database
	 * @param comment comment object
	 * @return updated comment
	 * @throws EntityNotFoundException
	 */
	public Comment updateComment(Comment comment) throws EntityNotFoundException;
	
	/**
	 * Method takes comment and postId and insert new comment into database for specific post
	 * return inserted comment
	 * @param comment Comment object
	 * @param postId Id of the post
	 * @return inserted comment
	 */
	public Comment addPostComment(Comment comment, Integer postId) throws EntityNotFoundException;
	
	/**
	 * Method takes id of comment and delete it from database
	 * return id of deleted comment
	 * throws exception in case of comment with specific id not found in database
	 * @param commentId Id of the comment
	 * @return id of deleted comment
	 * @throws EntityNotFoundException
	 */
	public Integer deleteCommentById(Integer id) throws EntityNotFoundException;
	
	/**
	 * Method takes id of comment increment like of that of that comment
	 * return current number of likes
	 * throws exception in case of comment with specific id not found in database
	 * @param commentId Id of the comment
	 * @return Number of likes for comment
	 * @throws EntityNotFoundException
	 */
	public Integer likeComment(Integer id) throws EntityNotFoundException;

}
