package com.trabajo.Grupo16OO22021.services.implementation;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trabajo.Grupo16OO22021.converters.RodadoConverter;
import com.trabajo.Grupo16OO22021.entities.Rodado;
import com.trabajo.Grupo16OO22021.models.RodadoModel;
import com.trabajo.Grupo16OO22021.repositories.IRodadoRepository;
import com.trabajo.Grupo16OO22021.services.IRodadoService;

@Service("rodadoService")
public class RodadoService implements IRodadoService {
	@Autowired
	@Qualifier("rodadoRepository")
	private IRodadoRepository rodadoRepository;

	@Autowired
	@Qualifier("rodadoConverter")
	private RodadoConverter rodadoConverter;

	@Override
	public List<Rodado> getAll() {
		return rodadoRepository.findAll();
	}

	@Override
	public RodadoModel insertOrUpdate(RodadoModel rodadoModel) {
		Rodado rodado = rodadoRepository.save(rodadoConverter.modelToEntity(rodadoModel));
		rodado = rodadoRepository.save(rodado);

		return rodadoConverter.entityToModel(rodado);
	}

	@Override
	public RodadoModel findById(int id) {
		return rodadoConverter.entityToModel(rodadoRepository.findById(id));
	}
	boolean validarPatente(String dominio) {
		boolean valida = false;
		if(dominio.matches("^[A-Z]{3}[0-9]{3}$")) {
			valida = true;;
		}
		else {
			if(dominio.matches("^[A-Z]{2}[0-9]{3}[A-Z]{2}$")) {
				valida = true;
			}
			else {
				valida = false;
			}
		}
		return valida;
	}
	@Override
	public boolean validate(RodadoModel rodadoModel) {
		boolean dominioValido = validarPatente(rodadoModel.getDominio());
		if (rodadoModel.getDominio().equals("") || rodadoModel.getVehiculo().equals("")
				|| rodadoModel.getVehiculo().length() < 3||!dominioValido) {
			return false;
		} else {
			return true;
		}

	}
	public Rodado findDominio(String dominio) {
		Rodado rodado = rodadoRepository.findByDominio(dominio);
		if(rodado == null)
			return null;
		else
			return rodado;
	}

	
	
}
