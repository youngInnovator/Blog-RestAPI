package com.blog.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.comment.entity.Comment;
import com.blog.common.EntityNotFoundException;

/**
* <h1>Comment Controller</h1>
* The CommentController is rest controller class
* Used to accept requests from user forward them to service to retrieve results
* Convert Java objects to Json objects and return back as http response
* 
*
* @author  Muhammad Saqib
* @version 1.0
* @since   2018-09-07 
*/
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	/**
	 * Method takes id of comment and return respective comment
	 * throws exception in case of comment with specific id not found in database
	 * @param commentId Id of the comment
	 * @return Comment
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Comment getComment(@PathVariable("id") Integer commentId) throws EntityNotFoundException {
		return commentService.getCommentById(commentId);
	}
	
	/**
	 * Method takes id of comment increment like of that of that comment
	 * return current number of likes
	 * throws exception in case of comment with specific id not found in database
	 * @param commentId Id of the comment
	 * @return Number of likes for comment
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value = "/{id}/like", method = RequestMethod.POST)
	public Integer likeComment(@PathVariable("id") Integer commentId) throws EntityNotFoundException {
		return commentService.likeComment(commentId);
	}
	
	/**
	 * Method takes Comment and update values of comment into database
	 * return updated comment
	 * throws exception in case of comment with specific id not found in database
	 * @param comment Comment object
	 * @return updated comment
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Comment updateComment(@RequestBody Comment comment) throws EntityNotFoundException {
		return commentService.updateComment(comment);
	}

	/**
	 * Method takes id of comment and delete it from database
	 * return id of deleted comment
	 * throws exception in case of comment with specific id not found in database
	 * @param commentId Id of the comment
	 * @return id of deleted comment
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Integer deleteCommentById(@PathVariable("id") Integer commentId) throws EntityNotFoundException {
		return commentService.deleteCommentById(commentId);	
	}

	/**
	 * Method take EntityNotFoundException exception object
	 * return 404 status code back to response
	 * @param ex EntityNotFoundException object
	 * @return ResponseEntity with 404 status code
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	private ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}

}
