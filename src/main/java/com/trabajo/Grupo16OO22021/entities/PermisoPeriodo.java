package com.trabajo.Grupo16OO22021.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "permisoPeriodo")
@PrimaryKeyJoinColumn(referencedColumnName = "idPermiso")
public class PermisoPeriodo extends Permiso {

	@Column(name = "cantDias", length = 2, nullable = false)
	private int cantDias;

	@Column(name = "vacaciones", length = 45, nullable = false)
	private boolean vacaciones;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Rodado rodado;

	public PermisoPeriodo() {
	}

	public PermisoPeriodo(int idPermiso, Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta, int cantDias,
			boolean vacaciones, Rodado rodado) {
		super(idPermiso, pedido, fecha, desdeHasta);
		this.cantDias = cantDias;
		this.vacaciones = vacaciones;
		this.rodado = rodado;
	}

	public PermisoPeriodo(Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta, int cantDias, boolean vacaciones,
			Rodado rodado) {
		super(pedido, fecha, desdeHasta);
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

	@Override
	public String toString() {

		String vacaciones1 = (isVacaciones() ? "Sí" : "No");

		return "\n\n Permiso periodo: \n\n Fecha: " + fecha + "\n\n Dni de la persona: " + pedido.getDocumento()
				+ "\n\n Dominio del rodado: " + rodado.getDominio() + "\n\n Cantidad de dias: " + cantDias
				+ "\n\n Por vacaciones?: " + vacaciones1 + "\n\n";
	}

}
