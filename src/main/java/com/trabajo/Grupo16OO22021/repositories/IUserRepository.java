package com.trabajo.Grupo16OO22021.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabajo.Grupo16OO22021.entities.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {
	public abstract User findById(int id);
	
	public abstract List<User> findAll();
	
	@Query("SELECT u FROM User u JOIN FETCH u.userRole WHERE u.username = (:username)")
	public abstract User findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);

}
