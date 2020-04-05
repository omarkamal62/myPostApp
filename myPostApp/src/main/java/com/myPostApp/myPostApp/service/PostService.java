package com.myPostApp.myPostApp.service;

import java.util.List;

import com.myPostApp.myPostApp.entity.Post;

public interface PostService {

	void save(Post thePost);
	List<Post> findAllPublic();
	List<Post> findAllByUser(int userId);
	List<Post> findPostsByContent(String searchText);
}
