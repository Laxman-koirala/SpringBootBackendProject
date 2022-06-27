package rest.api.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.api.springboot.rest.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Long>{
	

}
