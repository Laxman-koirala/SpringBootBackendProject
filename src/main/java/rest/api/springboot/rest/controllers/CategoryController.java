package rest.api.springboot.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.api.springboot.rest.entities.Category;
import rest.api.springboot.rest.response.msg.ApiResponse;
import rest.api.springboot.rest.services.CategoryService;

@RestController
@RequestMapping("/blog/api")
public class CategoryController {
	/*
	List <Category> getCategories();
	Category getCategory(Long categoryId);
	Category postCategory(Category category);
	Category putCategory(Category category, Long categoryId);
	void deleteCategory(Long categoryId);
	 */
	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping("/categories")
	List<Category> getCategories(){
		return categoryService.getCategories();		
	}
	
	@GetMapping("/categories/{categoryId}")
	ResponseEntity<Category>getCategory(@PathVariable(value="categoryId") String categoryId) {
		Category catego = categoryService.getCategory(Long.parseLong(categoryId));
		return new ResponseEntity<Category>(catego,HttpStatus.OK);			
	}
	
	@PostMapping("/categories")
	ResponseEntity<Category> postCategory(@Valid @RequestBody Category category){
		Category catego = categoryService.postCategory(category);
		return new ResponseEntity<Category>(catego, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/categories/{categoryId}")
	ResponseEntity<Category> putCategory(@Valid @RequestBody Category category, @PathVariable(value="categoryId") String categortyId){
		Category catego = categoryService.putCategory(category,Long.parseLong(categortyId));
		return new ResponseEntity<Category>(catego, HttpStatus.OK);
		
	}
	@DeleteMapping("/categories/{categoryId}")
	ResponseEntity<ApiResponse> deleteCategory(@PathVariable(value="categoryId") String categoryId) {
		categoryService.deleteCategory(Long.parseLong(categoryId));
		return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully Deleted",true),HttpStatus.OK);			
	}
	
}
