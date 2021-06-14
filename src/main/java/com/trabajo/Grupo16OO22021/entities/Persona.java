package com.trabajo.Grupo16OO22021.entities;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "persona")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersona;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column (name = "apellido", nullable = false)
	private String apellido;
	
	@NotNull(message="El dni es obligatorio")
	@Column (name = "documento", unique = true, nullable = false)
	private long documento;
	
	public Persona() {}

	public Persona(int idPersona, String nombre, String apellido, long documento) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
	}

	public Persona(String nombre, String apellido, long documento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getDocumento() {
		return documento;
	}

	public void setDocumento(long documento) {
		this.documento = documento;
	}

}
