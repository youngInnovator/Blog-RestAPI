package com.blog.comment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.comment.entity.Comment;
import com.blog.post.entity.Post;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

	public List<Comment> findByPost(Post post);
}
