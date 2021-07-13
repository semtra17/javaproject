package com.trabajo.Grupo16OO22021.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabajo.Grupo16OO22021.entities.Permiso;
import com.trabajo.Grupo16OO22021.entities.PermisoDiario;

@Repository("permisoDiarioRepository")
public interface IPermisoDiarioRepository extends JpaRepository<PermisoDiario, Serializable> {
		
		public abstract	List<PermisoDiario> findAll();
		
		@Query("SELECT p FROM Permiso p INNER JOIN p.pedido per WHERE per.documento = :documento and per.apellido = :apellido")
		public abstract List<Permiso> findByPerson(@Param("documento") long documento, @Param("apellido") String apellido);
		
		
		@Query("SELECT p FROM Permiso p  WHERE p.fecha BETWEEN :desde and :hasta")
		public abstract List<Permiso> traerPermisosPorfecha(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);
		
		@Query("SELECT p FROM Permiso p INNER JOIN p.desdeHasta l WHERE p.fecha BETWEEN :desde and :hasta and l.lugar =:lugar")
		public abstract List<Permiso> traerPermisosPorfechaYFecha(@Param("lugar") String lugar ,@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);
}

