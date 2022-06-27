package rest.api.springboot.rest.services;

import java.util.List;

import rest.api.springboot.rest.entities.Category;
import rest.api.springboot.rest.entities.Post;
import rest.api.springboot.rest.response.msg.PostResponse;

public interface PostService {
	
	PostResponse getPosts(int pageNumber, int pageSize, String sortBy, String sortDir);
	List<Post> getPostsbyCategory(Long categoryId);
	List<Post> getPostsbyUser(Long userId);
	List<Post> searchPost(String keyword);
	Post getPost(Long postId);
	Post postPost(Post post, Long userId, Long categoryId);
	Post putPost(Post post, Long postId);
	void deletePost(Long postId);

}
