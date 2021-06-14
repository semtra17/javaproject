package com.trabajo.Grupo16OO22021.services;

import java.util.List;


import com.trabajo.Grupo16OO22021.entities.Rodado;
import com.trabajo.Grupo16OO22021.models.RodadoModel;

public interface IRodadoService {

	public List<Rodado> getAll();
	
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel);
	
	public RodadoModel findById(int id);
	
	public boolean validate(RodadoModel rodadoModel);
	
	public Rodado findDominio(String dominio);

}
