package rest.api.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.api.springboot.rest.entities.User;

public interface UserRepo extends JpaRepository<User,Long>{
	

}
