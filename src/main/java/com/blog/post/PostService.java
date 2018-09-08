package com.blog.post;

import java.util.List;

import com.blog.common.EntityNotFoundException;
import com.blog.post.entity.Post;

public interface PostService {
	
	public List<Post> getAllPosts();
	
	public Post getPostById(Integer id) throws EntityNotFoundException;
	
	public Post updatePost(Post post);
	
	public Post addPost(Post post);
	
	public Integer deletePostById(Integer id) throws EntityNotFoundException;

}
