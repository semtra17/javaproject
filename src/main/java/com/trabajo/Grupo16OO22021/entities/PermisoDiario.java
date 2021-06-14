package com.trabajo.Grupo16OO22021.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "permisoDiario")
@PrimaryKeyJoinColumn(referencedColumnName = "idPermiso")
public class PermisoDiario extends Permiso {

	@Column(name = "motivo", length = 60, nullable = false)
	private String motivo;

	public PermisoDiario() {
	}

	public PermisoDiario(int idPermiso, Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta, String motivo) {
		super(idPermiso, pedido, fecha, desdeHasta);
		this.motivo = motivo;
	}

	public PermisoDiario(Persona pedido, LocalDate fecha, Set<Lugar> desdeHasta, String motivo) {
		super(pedido, fecha, desdeHasta);
		this.motivo = motivo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public String toString() {
		return "\n\n Permiso diario: \n\n Fecha: " + fecha + "\n\n DNI de la persona: " + pedido.getDocumento()
				+ " \n\n Motivo: " + motivo + "\n\n";
	}

}
