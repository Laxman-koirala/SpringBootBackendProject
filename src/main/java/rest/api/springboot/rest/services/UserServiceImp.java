package rest.api.springboot.rest.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.api.springboot.rest.entities.User;
import rest.api.springboot.rest.exceptions.ResourceNotFoundException;
import rest.api.springboot.rest.repository.UserRepo;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUser(Long userId) {
		User getuser = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return getuser;
	}

	@Override
	public User createUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User updateUser(User user, Long userId) {
		User getuser = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		getuser.setName(user.getName());
		getuser.setEmail(user.getEmail());
		getuser.setPassword(user.getPassword());
		getuser.setAbout(user.getAbout());
		return userRepo.save(getuser);
	}

	@Override
	public void deleteUser(Long userId) {
		User getuser = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		userRepo.delete(getuser);
	}

}
