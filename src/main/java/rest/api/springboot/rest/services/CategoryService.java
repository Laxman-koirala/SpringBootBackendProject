package rest.api.springboot.rest.services;

import java.util.List;

import rest.api.springboot.rest.entities.Category;

public interface CategoryService {
	
	List <Category> getCategories();
	Category getCategory(Long categoryId);
	Category postCategory(Category category);
	Category putCategory(Category category, Long categoryId);
	void deleteCategory(Long categoryId);

}
