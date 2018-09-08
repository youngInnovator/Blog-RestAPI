package com.blog.comment;

import java.util.List;

import com.blog.comment.entity.Comment;
import com.blog.common.EntityNotFoundException;

public interface CommentService {
	
	public List<Comment> getAllCommentByPost(Integer postId);
	
	public Comment getCommentById(Integer id) throws EntityNotFoundException;
	
	public Comment updateComment(Comment comment) throws EntityNotFoundException;
	
	public Comment addPostComment(Comment comment, Integer postId) throws EntityNotFoundException;
	
	public Integer deleteCommentById(Integer id) throws EntityNotFoundException;
	
	public Integer likeComment(Integer id) throws EntityNotFoundException;

}
