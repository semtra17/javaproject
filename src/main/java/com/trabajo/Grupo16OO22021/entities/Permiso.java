package com.trabajo.Grupo16OO22021.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance( strategy = InheritanceType.JOINED)
@Table(name = "permiso")
public class Permiso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idPermiso;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	protected Persona pedido;
	
	@Column(name = "fecha")
	protected LocalDate fecha;
	
	@ManyToMany
	@JoinTable(name="permisoxlugar"
			, joinColumns=@JoinColumn(name="permisoId")
			, inverseJoinColumns=@JoinColumn(name="lugarId")
	)
	protected Set<Lugar> desdeHasta;
	
	
	public Permiso() {}

	public Permiso(int idPermiso, Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta) {
		super();
		this.idPermiso = idPermiso;
		this.pedido = pedido;
		this.fecha = fecha;
		this.desdeHasta = desdeHasta;
	}

	public Permiso(Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta) {
		super();
		this.pedido = pedido;
		this.fecha = fecha;
		this.desdeHasta = desdeHasta;
	}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Persona getPedido() {
		return pedido;
	}

	public void setPedido(Persona pedido) {
		this.pedido = pedido;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Set<Lugar> getDesdeHasta() {
		return desdeHasta;
	}

	public void setDesdeHasta(Set<Lugar> desdeHasta) {
		this.desdeHasta = desdeHasta;
	}

}
