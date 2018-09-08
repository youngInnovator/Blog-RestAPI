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

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Comment getComment(@PathVariable("id") Integer commentId) throws EntityNotFoundException {
		return commentService.getCommentById(commentId);
	}
	
	@RequestMapping(value = "/{id}/like", method = RequestMethod.POST)
	public Integer likeComment(@PathVariable("id") Integer commentId) throws EntityNotFoundException {
		return commentService.likeComment(commentId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Comment updateComment(@RequestBody Comment comment) throws EntityNotFoundException {
		return commentService.updateComment(comment);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Integer deleteCommentById(@PathVariable("id") Integer commentId) throws EntityNotFoundException {
		return commentService.deleteCommentById(commentId);	
	}

	@ExceptionHandler(EntityNotFoundException.class)
	private ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}

}
