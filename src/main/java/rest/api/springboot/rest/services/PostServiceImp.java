package rest.api.springboot.rest.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import rest.api.springboot.rest.entities.Category;
import rest.api.springboot.rest.entities.Post;
import rest.api.springboot.rest.entities.User;
import rest.api.springboot.rest.exceptions.ResourceNotFoundException;
import rest.api.springboot.rest.repository.PostRepo;
import rest.api.springboot.rest.repository.UserRepo;
import rest.api.springboot.rest.response.msg.PostResponse;
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
	public PostResponse getPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
			
		
		Pageable pa = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePost = postRepo.findAll(pa);
		List<Post> posts = pagePost.getContent();
		
		PostResponse pr = new PostResponse();
		pr.setContent(posts);
		pr.setPageNumber(pagePost.getNumber());
		pr.setPageSize(pagePost.getSize());
		pr.setTotalElements(pagePost.getTotalElements());
		pr.setTotalPages(pagePost.getTotalPages());
		pr.setLastpage(pagePost.isLast());
		
		return pr;
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
		return postRepo.findByTitleContaining(keyword);
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
