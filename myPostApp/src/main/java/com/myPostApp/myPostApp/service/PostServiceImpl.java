package com.myPostApp.myPostApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myPostApp.myPostApp.entity.Post;
import com.myPostApp.myPostApp.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void save(Post thePost) {
		postRepository.save(thePost);
	}

	@Override
	public List<Post> findAllPublic() {
		return postRepository.findAllPublic();
	}

	@Override
	public List<Post> findAllByUser(int userId) {
		return postRepository.findAllByUser(userId);
	}

	@Override
	public List<Post> findPostsByContent(String searchText) {
		return postRepository.findPostsbyContent(searchText);
	}

}
