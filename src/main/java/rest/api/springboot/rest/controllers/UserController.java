package rest.api.springboot.rest.controllers;

import java.util.List;

import javax.validation.Valid;

import rest.api.springboot.rest.services.UserService;
import rest.api.springboot.rest.response.msg.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import rest.api.springboot.rest.entities.User;

@RestController
@RequestMapping("/blog/api")
public class UserController {
	/*
	List<User>getUsers();
	User getUser(Long userId);
	User createUser(User user);
	User updateUser(User user, Long userId);
	void deleteUser(Long userId);
	*/
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<User> getuser(){
		return userService.getUsers();
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUser(@PathVariable(value="userId") String userId){
		User getuser = userService.getUser(Long.parseLong(userId));
		return new ResponseEntity<User>(getuser, HttpStatus.OK);
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User postUser = userService.createUser(user);
		return new ResponseEntity<User>(postUser, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user,@PathVariable(value="userId") String userId) {
		User putUser = userService.updateUser(user, Long.parseLong(userId));
		return new ResponseEntity<User>(putUser, HttpStatus.OK);

	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value="userId") String userId) {
		userService.deleteUser(Long.parseLong(userId));
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true), HttpStatus.OK);
	}

}
