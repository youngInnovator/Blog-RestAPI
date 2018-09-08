package com.blog.post;

import static org.hamcrest.Matchers.*;

import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;

import static org.mockito.BDDMockito.given;
import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.blog.comment.entity.Comment;
import com.blog.common.TestUtil;
import com.blog.post.PostController;
import com.blog.post.entity.Post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { com.blog.config.BlogRestApiApplication.class })
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PostController postController;
	
	@MockBean
	private PostService postService;

	@Test
	public void getAllPosts() throws Exception {
		Post post = new Post();
		post.setContent("Test Content");
		post.setTitle("Test Title");

		List<Post> allPosts = singletonList(post);

		given(postController.getAllPosts()).willReturn(allPosts);

		mvc.perform(get("/post/").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].title", is(post.getTitle())))
				.andExpect(jsonPath("$[0].content", is(post.getContent())));
	}

	@Test
	public void getPostById() throws Exception {
		Post post = new Post();
		post.setId(new Integer(1));
		post.setContent("Test Content");
		post.setTitle("Test Title");

		given(postController.getPost(post.getId())).willReturn(post);

		mvc.perform(get("/post/{id}", 1).contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("title", is(post.getTitle())))
				.andExpect(jsonPath("content", is(post.getContent())));
	}

	@Test
	public void addPost() throws Exception {
		Post post = new Post();
		post.setContent("Test Content");
		post.setTitle("Test Title");
		
		Post postAdded = new Post();
		postAdded.setId(new Integer(1));
		postAdded.setContent("Test Content");
		postAdded.setTitle("Test Title");
		
		given(postController.addPost(any(Post.class))).willReturn(postAdded);

		mvc.perform(post("/post/").contentType(APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(post)))
				.andExpect(status().isOk()).andExpect(jsonPath("title", is(post.getTitle())))
				.andExpect(jsonPath("content", is(post.getContent())));
	}
	
	@Test
	public void updatePost() throws Exception {
		Post post = new Post();
		post.setContent("Test Content Updated");
		post.setTitle("Test Title");
		
		Post postUpdated = new Post();
		postUpdated.setId(new Integer(1));
		postUpdated.setContent("Test Content Updated");
		postUpdated.setTitle("Test Title");
		
		given(postController.updatePost(any(Post.class))).willReturn(postUpdated);

		mvc.perform(put("/post/").contentType(APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(post)))
				.andExpect(status().isOk()).andExpect(jsonPath("title", is(post.getTitle())))
				.andExpect(jsonPath("content", is(post.getContent())));
	}
	
	@Test
	public void deletePost() throws Exception {

		mvc.perform(delete("/post/{id}", 1).contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getPostComment() throws Exception {
		Comment comment = new Comment();
		comment.setCommentedBy("Test User");
		comment.setComment("Test Comment");

		List<Comment> allComments = singletonList(comment);

		given(postController.getAllPostComments(new Integer(1))).willReturn(allComments);

		mvc.perform(get("/post/{id}/comments", 1).contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].commentedBy", is(comment.getCommentedBy())))
				.andExpect(jsonPath("$[0].comment", is(comment.getComment())));
	}
	
	@Test
	public void addPostComment() throws Exception {
		Comment comment = new Comment();
		comment.setCommentedBy("Test User");
		comment.setComment("Test Comment");
		comment.setLikes(new Integer(0));
		
		Comment commentAdded = new Comment();
		commentAdded.setId(new Integer(1));
		commentAdded.setCommentedBy("Test User");
		commentAdded.setComment("Test Comment");
		
		given(postController.addPostComment(any(Comment.class), any(Integer.class))).willReturn(commentAdded);

		mvc.perform(post("/post/{id}/comment", 1).contentType(APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(comment)))
				.andExpect(status().isOk()).andExpect(jsonPath("commentedBy", is(comment.getCommentedBy())))
				.andExpect(jsonPath("comment", is(comment.getComment())))
				.andExpect(jsonPath("likes", is(comment.getLikes())));
	}
}
