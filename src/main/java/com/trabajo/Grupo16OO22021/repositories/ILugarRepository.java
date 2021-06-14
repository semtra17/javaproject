package com.trabajo.Grupo16OO22021.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabajo.Grupo16OO22021.entities.Lugar;

@Repository("lugarRepository")
public interface ILugarRepository extends JpaRepository<Lugar, Integer> {
	
	public abstract	List<Lugar> findAll();
	
}

