package com.blog.comment;

import static org.hamcrest.Matchers.*;

import static org.mockito.Matchers.any;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { com.blog.config.BlogRestApiApplication.class })
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CommentController commentController;
	
	@MockBean
	private CommentService commentService;

	@Test
	public void getCommentById() throws Exception {
		Comment comment = new Comment();
		comment.setId(new Integer(1));
		comment.setCommentedBy("Test User");
		comment.setComment("Test Comment");
		comment.setLikes(new Integer(0));

		given(commentController.getComment(comment.getId())).willReturn(comment);

		mvc.perform(get("/comment/{id}", 1).contentType(APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(status().isOk()).andExpect(jsonPath("commentedBy", is(comment.getCommentedBy())))
		.andExpect(jsonPath("comment", is(comment.getComment())))
		.andExpect(jsonPath("likes", is(comment.getLikes())));
	}

	@Test
	public void likeComment() throws Exception {
		mvc.perform(post("/comment/{id}/like", 1).contentType(APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void updateComment() throws Exception {
		Comment comment = new Comment();
		comment.setCommentedBy("Test User");
		comment.setComment("Test Comment Updated");
		comment.setLikes(new Integer(0));
		
		Comment commentUpdated = new Comment();
		commentUpdated.setId(new Integer(1));
		commentUpdated.setCommentedBy("Test User");
		commentUpdated.setComment("Test Comment Updated");
		commentUpdated.setLikes(new Integer(0));
		
		given(commentController.updateComment(any(Comment.class))).willReturn(commentUpdated);

		mvc.perform(put("/comment/").contentType(APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(comment)))
		.andExpect(status().isOk()).andExpect(jsonPath("commentedBy", is(comment.getCommentedBy())))
		.andExpect(jsonPath("comment", is(comment.getComment())))
		.andExpect(jsonPath("likes", is(comment.getLikes())));
	}
	
	@Test
	public void deleteComment() throws Exception {

		mvc.perform(delete("/comment/{id}", 1).contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
