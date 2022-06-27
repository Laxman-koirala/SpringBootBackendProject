package rest.api.springboot.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.api.springboot.rest.entities.Category;
import rest.api.springboot.rest.exceptions.ResourceNotFoundException;
import rest.api.springboot.rest.repository.CategoryRepo;

@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public Category getCategory(Long categoryId) {
		Category catego = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		return catego;
	}

	@Override
	public Category postCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public Category putCategory(Category category, Long categoryId) {
		Category catego = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		catego.setTitle(category.getTitle());
		catego.setDescription(category.getDescription());
		return categoryRepo.save(catego);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		Category catego = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		categoryRepo.delete(catego);
		
	}

}
