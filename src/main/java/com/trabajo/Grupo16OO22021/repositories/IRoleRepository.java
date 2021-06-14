package com.trabajo.Grupo16OO22021.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.trabajo.Grupo16OO22021.entities.UserRole;

@Qualifier("roleRepository")
@Repository
public interface IRoleRepository extends JpaRepository<UserRole, Integer>{
	
}

