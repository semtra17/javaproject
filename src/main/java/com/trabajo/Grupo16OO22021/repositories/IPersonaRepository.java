package com.trabajo.Grupo16OO22021.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabajo.Grupo16OO22021.entities.Persona;

@Repository("personaRepository")
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
	public abstract Persona findById(int id);
	 
	
	public abstract List<Persona> findAll();
	
}