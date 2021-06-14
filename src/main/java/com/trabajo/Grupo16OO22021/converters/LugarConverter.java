package com.trabajo.Grupo16OO22021.converters;

import com.trabajo.Grupo16OO22021.entities.Lugar;
import com.trabajo.Grupo16OO22021.models.LugarModel;

public class LugarConverter {
	public LugarModel entityToModel(Lugar lugar) {
		return new LugarModel(lugar.getIdLugar(), lugar.getLugar(), lugar.getCodigoPostal());
	}

	public Lugar modelToEntity(LugarModel lugarModel) {
		return new Lugar(lugarModel.getIdLugar(), lugarModel.getLugar(), lugarModel.getCodigoPostal());
	}
}