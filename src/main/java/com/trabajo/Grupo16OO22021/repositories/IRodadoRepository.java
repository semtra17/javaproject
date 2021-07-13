package com.trabajo.Grupo16OO22021.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabajo.Grupo16OO22021.entities.Rodado;

@Repository("rodadoRepository")
public interface IRodadoRepository extends JpaRepository<Rodado, Integer> {
		public abstract Rodado findById(int id);
		
		public abstract List<Rodado> findAll();
		
		@Query("SELECT r FROM Rodado r where r.dominio=:dominio")
		public abstract Rodado findByDominio(@Param("dominio") String dominio);

		

}
