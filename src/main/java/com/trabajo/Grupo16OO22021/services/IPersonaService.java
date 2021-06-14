package com.trabajo.Grupo16OO22021.services;

import java.util.List;

import com.trabajo.Grupo16OO22021.entities.Persona;
import com.trabajo.Grupo16OO22021.models.PersonaModel;

public interface IPersonaService {
	
	public List<Persona> getAll();

	public PersonaModel insertOrUpdate(PersonaModel persona);

	public boolean remove(int id);

	public PersonaModel findById(int id);
	

	public boolean validate(PersonaModel personaModel);


}
