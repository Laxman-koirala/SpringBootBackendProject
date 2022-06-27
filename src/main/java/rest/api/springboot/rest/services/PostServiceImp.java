package rest.api.springboot.rest.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.api.springboot.rest.entities.Category;
import rest.api.springboot.rest.entities.Post;
import rest.api.springboot.rest.entities.User;
import rest.api.springboot.rest.exceptions.ResourceNotFoundException;
import rest.api.springboot.rest.repository.PostRepo;
import rest.api.springboot.rest.repository.UserRepo;
import rest.api.springboot.rest.repository.CategoryRepo;

@Service
public class PostServiceImp implements PostService{
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public List<Post> getPosts() {
		return postRepo.findAll();
	}

	@Override
	public List<Post> getPostsbyCategory(Long categoryId) {
		Category cato = categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","CategoryId", categoryId));
		List<Post> posts = postRepo.findByCategory(cato);
		return posts;
	}

	@Override
	public List<Post> getPostsbyUser(Long userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","UserId", userId));
		List<Post> posts = postRepo.findByUser(user);
		return posts;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPost(Long postId) {
		Post postuser = postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post","PostId", postId));
		return postuser;
	}

	@Override
	public Post postPost(Post post,Long userId, Long categoryId) {
		User postuser = userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","UserId", userId));
		Category categoryuser = categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","CategoryId", categoryId));
		post.setUser(postuser);
		post.setCategory(categoryuser);
		post.setAddedDate(new Date());
		post.setImageName("default.png");
		return postRepo.save(post);
	}

	@Override
	public Post putPost(Post post, Long postId) {
		
		Post postuser = postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post","PostId", postId));
		postuser.setContent(post.getContent());
		postuser.setTitle(post.getTitle());
		
		return postRepo.save(postuser);
	
		
	}

	@Override
	public void deletePost(Long postId) {
		Post postuser = postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post","PostId", postId));
		postRepo.delete(postuser);
		
	}

}
