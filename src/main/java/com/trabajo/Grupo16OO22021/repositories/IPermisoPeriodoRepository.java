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
import com.trabajo.Grupo16OO22021.entities.PermisoPeriodo;
import com.trabajo.Grupo16OO22021.entities.Rodado;

@Repository("permisoPeriodoRepository")
public interface IPermisoPeriodoRepository extends JpaRepository<PermisoPeriodo, Serializable> {
	
	
	public abstract List<PermisoPeriodo> findAll();

	@Query("SELECT p FROM Permiso p INNER JOIN p.pedido per WHERE per.documento = :documento and per.apellido = :apellido")
	//@Query("from Permiso p inner join fetch p.persona per where per.documento= ?1 and per.apellido= ?2")
	public abstract List<Permiso> findByPerson(@Param("documento") long documento, @Param("apellido")  String apellido);

	@Query("SELECT p FROM Permiso p INNER JOIN p.rodado r WHERE r.dominio= :dominio")
	public List<PermisoPeriodo> buscarPermisoPeriodoRodado(@Param("dominio") String dominio);
	
	@Query("SELECT p FROM Permiso p WHERE ADDDATE(p.fecha, p.cantDias) BETWEEN :desde and :hasta")
	public abstract List<Permiso> traerPeriodoEntreFechas(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);

	@Query("SELECT p FROM Permiso p INNER JOIN p.desdeHasta l WHERE p.fecha BETWEEN :desde and :hasta and l.lugar =:lugar")
	public abstract List<Permiso> traerPermisosPorfechaYlugar(@Param("lugar") String lugar ,@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);



}
