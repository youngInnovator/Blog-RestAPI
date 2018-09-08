package com.blog.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.blog.comment.entity.Comment;
import com.blog.comment.repository.CommentRepository;
import com.blog.common.EntityNotFoundException;
import com.blog.post.PostService;
import com.blog.post.entity.Post;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostService postService;

	@Override
	public List<Comment> getAllCommentByPost(Integer postId) {
		Post post = new Post();
		post.setId(postId);
		return commentRepository.findByPost(post);
	}

	@Override
	public Comment getCommentById(Integer id) throws EntityNotFoundException {

		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isPresent())
			return comment.get();

		throw new EntityNotFoundException();
	}

	@Override
	public Comment updateComment(Comment comment) throws EntityNotFoundException {
		Comment tempComment = getComment(comment.getId());

		tempComment.setCommentedBy(comment.getCommentedBy());
		tempComment.setComment(comment.getComment());
		commentRepository.save(tempComment);
		return tempComment;
	}

	@Override
	public Comment addPostComment(Comment comment, Integer postId) throws EntityNotFoundException {
		Post post = postService.getPostById(postId);
		comment.setPost(post);
		commentRepository.save(comment);

		return comment;
	}

	@Override
	public Integer deleteCommentById(Integer id) throws EntityNotFoundException {
		try {
			commentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new EntityNotFoundException();
		}
		return id;
	}

	@Override
	public Integer likeComment(Integer id) throws EntityNotFoundException {
		Comment tempComment = getComment(id);
		tempComment.setLikes(tempComment.getLikes().intValue() + 1);
		commentRepository.save(tempComment);
		return tempComment.getLikes();
	}

	private Comment getComment(Integer id) throws EntityNotFoundException {
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isPresent())
			return comment.get();

		throw new EntityNotFoundException();
	}
}
