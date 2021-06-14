package com.trabajo.Grupo16OO22021.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.trabajo.Grupo16OO22021.entities.PermisoDiario;

@Repository("permisoDiarioRepository")
public interface IPermisoDiarioRepository extends JpaRepository<PermisoDiario, Serializable> {
		
		public abstract	List<PermisoDiario> findAll();
		
	
}

