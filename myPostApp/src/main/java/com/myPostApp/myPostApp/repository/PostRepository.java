package com.myPostApp.myPostApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myPostApp.myPostApp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("SELECT p from Post as p WHERE p.exclusive is true")
	List<Post> findAllPublic();
	
	@Query("SELECT p from Post as p WHERE p.user.id = ?1")
	List<Post> findAllByUser(int userId);
	
	@Query("select p from Post p where lower(p.content) like lower(concat('%', ?1,'%')) and p.exclusive = true")
    List<Post> findPostsbyContent(String searchText);
}
