package com.trabajo.Grupo16OO22021.models;

import java.time.LocalDate;
import java.util.Set;

import com.trabajo.Grupo16OO22021.entities.Lugar;
import com.trabajo.Grupo16OO22021.entities.Persona;
import com.trabajo.Grupo16OO22021.entities.Rodado;

public class PermisoPeriodoModel extends PermisoModel{
	private int cantDias;
	private boolean vacaciones;
	private Rodado rodado;
	
	public PermisoPeriodoModel() {}

	public PermisoPeriodoModel(int idPermiso, Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta, int cantDias, boolean vacaciones, Rodado rodado) {
		super(idPermiso, pedido, fecha, desdeHasta);
		this.cantDias = cantDias;
		this.vacaciones = vacaciones;
		this.rodado = rodado;
	}

	public int getCantDias() {
		return cantDias;
	}

	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}

	public boolean isVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(boolean vacaciones) {
		this.vacaciones = vacaciones;
	}

	public Rodado getRodado() {
		return rodado;
	}

	public void setRodado(Rodado rodado) {
		this.rodado = rodado;
	}
	
	
	
}