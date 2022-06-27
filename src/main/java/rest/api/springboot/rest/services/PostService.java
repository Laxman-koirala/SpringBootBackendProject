package rest.api.springboot.rest.services;

import java.util.List;

import rest.api.springboot.rest.entities.Category;
import rest.api.springboot.rest.entities.Post;

public interface PostService {
	
	List <Post> getPosts();
	List<Post> getPostsbyCategory(Long categoryId);
	List<Post> getPostsbyUser(Long userId);
	List<Post> searchPost(String keyword);
	Post getPost(Long postId);
	Post postPost(Post post, Long userId, Long categoryId);
	Post putPost(Post post, Long postId);
	void deletePost(Long postId);

}
