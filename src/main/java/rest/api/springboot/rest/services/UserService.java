package rest.api.springboot.rest.services;

import java.util.List;

import rest.api.springboot.rest.entities.User;

public interface UserService {
	
	List<User>getUsers();
	User getUser(Long userId);
	User createUser(User user);
	User updateUser(User user, Long userId);
	void deleteUser(Long userId);
	

}
