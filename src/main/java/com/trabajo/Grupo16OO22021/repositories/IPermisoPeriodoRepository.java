package com.trabajo.Grupo16OO22021.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabajo.Grupo16OO22021.entities.PermisoPeriodo;

@Repository("permisoPeriodoRepository")
public interface IPermisoPeriodoRepository extends JpaRepository<PermisoPeriodo, Serializable> {
	

	public abstract List<PermisoPeriodo> findAll();


}
