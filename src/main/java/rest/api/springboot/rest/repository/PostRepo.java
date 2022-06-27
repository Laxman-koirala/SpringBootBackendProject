package rest.api.springboot.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.api.springboot.rest.entities.Category;
import rest.api.springboot.rest.entities.Post;
import rest.api.springboot.rest.entities.User;

public interface PostRepo extends JpaRepository<Post, Long> {
	
	List<Post> findByUser (User user);
	List<Post> findByCategory (Category category);
	List<Post> findByTitleContaining(String title);

}
