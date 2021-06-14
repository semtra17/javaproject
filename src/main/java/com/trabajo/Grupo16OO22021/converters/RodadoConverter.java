package com.trabajo.Grupo16OO22021.converters;

import org.springframework.stereotype.Component;

import com.trabajo.Grupo16OO22021.entities.Rodado;
import com.trabajo.Grupo16OO22021.models.RodadoModel;

@Component("rodadoConverter")
public class RodadoConverter {
	public RodadoModel entityToModel(Rodado rodado) {
		return new RodadoModel(rodado.getIdRodado(), rodado.getDominio(),rodado.getVehiculo());
	}

	public Rodado modelToEntity(RodadoModel rodadoModel) {
		return new Rodado(rodadoModel.getIdRodado(), rodadoModel.getDominio(), rodadoModel.getVehiculo());
	}
}
