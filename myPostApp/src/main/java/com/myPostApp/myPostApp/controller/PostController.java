package com.myPostApp.myPostApp.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myPostApp.myPostApp.entity.Post;
import com.myPostApp.myPostApp.entity.User;
import com.myPostApp.myPostApp.service.PostService;
import com.myPostApp.myPostApp.service.UserService;


@RestController
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/api/publicposts")
	public ResponseEntity<Map<String,Object>> publicPosts(){
		Map<String,Object> retval = new HashMap<>();
		retval.put("posts", postService.findAllPublic());
		
		return ResponseEntity.ok().body(retval);
		
	}
	
	@PostMapping(value="/api/savepost")
	public ResponseEntity<Map<String, Object>> savePost(@RequestBody Map<String, Object> thePost, Principal principal){
		ObjectMapper mapper = new ObjectMapper();
		Post post = mapper.convertValue(thePost.get("post"), Post.class);

		post.setUser(userService.getUser(principal.getName()));
		postService.save(post);
		
		Map<String,Object> retval = new HashMap<>();
		retval.put("post", post);
		return ResponseEntity.ok().body(retval);
		
	}
	
	@GetMapping(path="/api/myposts")
	public ResponseEntity<Map<String,Object>> myPosts(Principal principal){
		
		User user = userService.getUser(principal.getName());
		System.out.println(principal.getName());
		
		Map<String,Object> retval = new HashMap<>();
		retval.put("posts", postService.findAllByUser(user.getId()));
		
		return ResponseEntity.ok().body(retval);
		
	}
	
	@GetMapping(path = "/api/posts/{searchText}")
	public ResponseEntity<Map<String,Object>> getPosts(@PathVariable(value = "searchText") String searchText){
		
		Map<String, Object> retval = new HashMap<>();
		List<Post> postList = postService.findPostsByContent(searchText);
		
		retval.put("posts", postList);
		
        return ResponseEntity.ok().body(retval);
	}
}
