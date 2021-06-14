package com.trabajo.Grupo16OO22021.models;

import java.time.LocalDate;
import java.util.Set;

import com.trabajo.Grupo16OO22021.entities.Lugar;
import com.trabajo.Grupo16OO22021.entities.Persona;

public class PermisoDiarioModel extends PermisoModel {
	private String motivo;

	public PermisoDiarioModel() {
	}

	public PermisoDiarioModel(int idPermiso, Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta, String motivo) {
		super(idPermiso, pedido, fecha, desdeHasta);
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
}
