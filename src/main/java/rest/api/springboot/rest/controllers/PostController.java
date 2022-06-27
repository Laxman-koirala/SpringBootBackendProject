package rest.api.springboot.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import rest.api.springboot.rest.services.PostService;
import rest.api.springboot.rest.response.msg.ApiResponse;
import rest.api.springboot.rest.response.msg.PostResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import rest.api.springboot.rest.entities.Category;
import rest.api.springboot.rest.entities.Post;

@RestController
@RequestMapping("/blog/api")
public class PostController {
	
	/*
	List <Post> getPosts();
	List<Post> getPostsbyCategory(Long categoryId);
	List<Post> getPostsbyUser(Long userId);
	List<Post> searchPost(String keyword);
	Post getPost(Long postId);
	Post postPost(Post post, Long userId, Long categoryId);
	Post putPost(Post post, Long postId);
	void deletePost(Long postId);
	 */
	
	@Autowired
	PostService postService;
	
	@GetMapping("/posts")
	PostResponse getPosts(
			@RequestParam (value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam (value="pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam (value="sortBy", defaultValue = "title", required = false) String sortBy,
			@RequestParam (value="sortDir", defaultValue = "asc", required = false) String sortDir
			
			){
		return postService.getPosts(pageNumber, pageSize, sortBy, sortDir);
	
	}
	
	@GetMapping("/postsbycategory/{categoryId}")
	ResponseEntity<List<Post>> getPostsbyCategory(@Valid @PathVariable(value="categoryId") String categoryId){
		List<Post> posts = postService.getPostsbyCategory(Long.parseLong(categoryId));
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
		
	}
	
	@GetMapping("/postsbyuser/{userId}")
	ResponseEntity<List<Post>> getPostsbyUser(@Valid @PathVariable(value="userId") String userId){
		List<Post> posts = postService.getPostsbyUser(Long.parseLong(userId));
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
		
	}
	
	
	
	@PostMapping("/userId/{userId}/categoryId/{categoryId}")
	ResponseEntity<Post> postPost (@Valid @RequestBody Post post ,
			@PathVariable(value="userId")String userId,
			@PathVariable(value="categoryId")String categoryId){

		Post createpost = postService.postPost(post, Long.parseLong(userId), Long.parseLong(categoryId));
		
		return new ResponseEntity<Post>(createpost, HttpStatus.CREATED);
	}
	
	@PutMapping("/updatepost/{postId}")
	ResponseEntity<Post> putPost(@Valid @RequestBody Post post,
			@PathVariable (value="postId") String postId){
		
		Post up = postService.putPost(post, Long.parseLong(postId));
		
		return new ResponseEntity<Post>(up, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{postId}")
	ResponseEntity<ApiResponse> deletePost(@PathVariable(value="postId") String postId) {
		postService.deletePost(Long.parseLong(postId));
		return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully Deleted",true),HttpStatus.OK);			
	}
	
	@GetMapping("/posts/{postId}")
	ResponseEntity<Post>getCategory(@PathVariable(value="postId") String postId) {
		Post pt = postService.getPost(Long.parseLong(postId));
		return new ResponseEntity<Post>(pt,HttpStatus.OK);			
	}
	
	@GetMapping("/posts/search/{Keyword}")
	ResponseEntity<List<Post>>searchpostbyTitle(@PathVariable(value="Keyword") String Keyword) {
		return new ResponseEntity<List<Post>>(postService.searchPost(Keyword),HttpStatus.OK);			
	}
	
	

}
