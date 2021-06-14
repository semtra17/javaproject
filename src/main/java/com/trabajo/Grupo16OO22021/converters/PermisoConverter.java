package com.trabajo.Grupo16OO22021.converters;

import org.springframework.stereotype.Component;

import com.trabajo.Grupo16OO22021.entities.PermisoDiario;
import com.trabajo.Grupo16OO22021.entities.PermisoPeriodo;
import com.trabajo.Grupo16OO22021.models.PermisoDiarioModel;
import com.trabajo.Grupo16OO22021.models.PermisoPeriodoModel;

@Component("permisoConverter")
public class PermisoConverter {
	public PermisoPeriodoModel entityToModel(PermisoPeriodo permisoPeriodo) {
		return new PermisoPeriodoModel(permisoPeriodo.getIdPermiso(), permisoPeriodo.getPedido(), permisoPeriodo.getFecha(), permisoPeriodo.getDesdeHasta(), permisoPeriodo.getCantDias(),permisoPeriodo.isVacaciones(), permisoPeriodo.getRodado());
	}

	public PermisoPeriodo modelToEntity(PermisoPeriodoModel permisoPeriodoModel) {
		return new PermisoPeriodo(permisoPeriodoModel.getIdPermiso(), permisoPeriodoModel.getPedido(), permisoPeriodoModel.getFecha(), permisoPeriodoModel.getDesdeHasta(), permisoPeriodoModel.getCantDias(),permisoPeriodoModel.isVacaciones(), permisoPeriodoModel.getRodado());
	}
	
	public PermisoDiarioModel permisoDiarioEntityToModel(PermisoDiario permisoDiario) {
		return new PermisoDiarioModel(permisoDiario.getIdPermiso(), permisoDiario.getPedido(), permisoDiario.getFecha(), permisoDiario.getDesdeHasta(), permisoDiario.getMotivo());
	}

	public PermisoDiario permisoDiarioModelToEntity(PermisoDiarioModel permisoDiarioModel) {
		return new PermisoDiario(permisoDiarioModel.getIdPermiso(), permisoDiarioModel.getPedido(), permisoDiarioModel.getFecha(), permisoDiarioModel.getDesdeHasta(),permisoDiarioModel.getMotivo());
	}
}