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

@RestController
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Post> getAllPosts() {
		return postService.getAllPosts();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Post getPost(@PathVariable("id") Integer postId) throws EntityNotFoundException {
		return postService.getPostById(postId);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Post addPost(@RequestBody Post post) {
		return postService.addPost(post);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Post updatePost(@RequestBody Post post) {
		return postService.updatePost(post);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Integer deletePost(@PathVariable("id") Integer postId) throws EntityNotFoundException {
		return postService.deletePostById(postId);
	}
	
	@RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
	public List<Comment> getAllPostComments(@PathVariable("id") Integer postId) {
		return commentService.getAllCommentByPost(postId);
	}
	
	@RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
	public Comment addPostComment(@RequestBody Comment comment, @PathVariable("id") Integer postId) throws EntityNotFoundException {
		return commentService.addPostComment(comment, postId);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	private ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}

}
