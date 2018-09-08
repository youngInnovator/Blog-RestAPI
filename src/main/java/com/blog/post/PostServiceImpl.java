package com.blog.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.blog.common.EntityNotFoundException;
import com.blog.post.entity.Post;
import com.blog.post.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> getAllPosts() {

		Iterable<Post> posts = postRepository.findAll();
		List<Post> postList = new ArrayList<>();
		posts.forEach(postList::add);
		return postList;
	}

	@Override
	public Post getPostById(Integer id) throws EntityNotFoundException {
		Optional<Post> post = postRepository.findById(id);
		if (post.isPresent())
			return post.get();

		throw new EntityNotFoundException();
	}

	@Override
	public Post updatePost(Post post) {
		postRepository.save(post);
		return post;
	}

	@Override
	public Integer deletePostById(Integer id) throws EntityNotFoundException {
		try {
			postRepository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new EntityNotFoundException();
		}
		return id;
	}

	@Override
	public Post addPost(Post post) {
		postRepository.save(post);
		return post;
	}

}
