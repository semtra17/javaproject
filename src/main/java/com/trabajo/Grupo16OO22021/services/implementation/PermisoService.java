package com.trabajo.Grupo16OO22021.services.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trabajo.Grupo16OO22021.converters.PermisoConverter;
import com.trabajo.Grupo16OO22021.entities.Lugar;
import com.trabajo.Grupo16OO22021.entities.Permiso;
import com.trabajo.Grupo16OO22021.entities.PermisoDiario;
import com.trabajo.Grupo16OO22021.entities.PermisoPeriodo;
import com.trabajo.Grupo16OO22021.models.PermisoDiarioModel;
import com.trabajo.Grupo16OO22021.models.PermisoPeriodoModel;
import com.trabajo.Grupo16OO22021.repositories.IPermisoDiarioRepository;
import com.trabajo.Grupo16OO22021.repositories.IPermisoPeriodoRepository;
import com.trabajo.Grupo16OO22021.services.IPermisoService;

@Qualifier("permisoService")
@Service
public class PermisoService implements IPermisoService {

	@Autowired
	@Qualifier("permisoPeriodoRepository")
	private IPermisoPeriodoRepository permisoPeriodoRepository;

	@Autowired
	@Qualifier("permisoDiarioRepository")
	private IPermisoDiarioRepository permisoDiarioRepository;

	@Autowired
	@Qualifier("permisoConverter")
	private PermisoConverter permisoConverter;

	@Override
	public List<PermisoPeriodo> getAll() {
		return permisoPeriodoRepository.findAll();
	}

	@Override
	public List<PermisoDiario> getAll1() {
		return permisoDiarioRepository.findAll();
	}
	public List<PermisoPeriodo> getPeriodos(long documento, String apellido) {
		List<Permiso> permisos = permisoPeriodoRepository.findByPerson(documento, apellido);
		List<PermisoPeriodo> permisosPeriodos =  new ArrayList<>();
		for(Permiso p: permisos) {
			if(p instanceof PermisoPeriodo)
				permisosPeriodos.add((PermisoPeriodo) p);
				
		}
			
		return permisosPeriodos;
	}
	public List<PermisoDiario> getDiarios(long documento, String apellido) {
		List<Permiso> permisos = permisoDiarioRepository.findByPerson(documento, apellido);
		List<PermisoDiario> permisosDiarios =  new ArrayList<>();
		for(Permiso p: permisos) {
			if(p instanceof PermisoDiario)
				permisosDiarios.add((PermisoDiario) p);
				
		}
			
		return permisosDiarios;

	}

	@Override
	public PermisoPeriodoModel insertOrUpdate(PermisoPeriodoModel permisoPeriodoModel) {

		PermisoPeriodo permiso = permisoPeriodoRepository.save(permisoConverter.modelToEntity(permisoPeriodoModel));
		permiso = permisoPeriodoRepository.save(permiso);

		return permisoConverter.entityToModel(permiso);
	}

	@Override
	public PermisoDiarioModel insertOrUpdate(PermisoDiarioModel permisoDiarioModel) {

		PermisoDiario permiso = permisoDiarioRepository
				.save(permisoConverter.permisoDiarioModelToEntity(permisoDiarioModel));
		permiso = permisoDiarioRepository.save(permiso);

		return permisoConverter.permisoDiarioEntityToModel(permiso);
	}

	@Override
	public boolean validatePermisoPeriodo(PermisoPeriodoModel permisoPeriodoModel) {
		if (permisoPeriodoModel.getPedido() == null || permisoPeriodoModel.getRodado() == null
				|| permisoPeriodoModel.getCantDias() == 0 || permisoPeriodoModel.getFecha().toString().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean validetePermisoDiario(PermisoDiarioModel permisoDiarioModel) {
		if (permisoDiarioModel.getPedido() == null || permisoDiarioModel.getFecha().toString().equals("")
				|| permisoDiarioModel.getMotivo().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean comprobarFecha(PermisoDiarioModel permisoDiarioModel) {
		
		if(permisoDiarioModel.getFecha().isBefore(LocalDate.now())) {
			return false;
		}
		return true;
		
	}
	public boolean comprobarFecha(PermisoPeriodoModel permisoPeriodoModel) {
		
		if(permisoPeriodoModel.getFecha().isBefore(LocalDate.now())) {
			return false;
		}
		return true;
		
	}
	
	

	

	public List<PermisoPeriodo> buscarPermisoPeriodoRodado(String dominio) {
		List<PermisoPeriodo> permisoPeriodo = permisoPeriodoRepository.buscarPermisoPeriodoRodado(dominio);
		
		return permisoPeriodo;
	}
	@Override
	public List<PermisoDiario> traerDiarioEntreFechas(LocalDate desde, LocalDate hasta) {

//>>>>>>> 71379bfe2e93a4b8aaf4d5a886f1db8e020edaa6
		List<Permiso> permisos = permisoDiarioRepository.traerPermisosPorfecha(desde,hasta);
		List<PermisoDiario> permisosOk = new ArrayList<PermisoDiario>();

		for (Permiso permiso : permisos) {

			if (permiso instanceof PermisoDiario) {
				permisosOk.add((PermisoDiario)permiso);
			}
		}

		return permisosOk;
	}
	@Override
	public List<PermisoPeriodo> traerPeriodoEntreFechas(LocalDate desde, LocalDate hasta) {

		List<Permiso> permisos = permisoPeriodoRepository.traerPeriodoEntreFechas(desde, hasta);
		List<PermisoPeriodo> permisosOk = new ArrayList<PermisoPeriodo>();

		for (Permiso permiso : permisos) {
			if(permiso instanceof PermisoPeriodo) {
					permisosOk.add((PermisoPeriodo)permiso);	
			}
		

		}
		return permisosOk;
	}

	public List<PermisoDiario> traerDiarioFechaYLugar(LocalDate fechaDesde, LocalDate fechaHasta, String lugar) {

		List<Permiso> permisos = permisoDiarioRepository.traerPermisosPorfechaYFecha(lugar,fechaDesde, fechaHasta);
		List<PermisoDiario> permisosDiario2 = new ArrayList<PermisoDiario>();

		for (Permiso permiso : permisos) {
			if(permiso instanceof PermisoDiario) {
				permisosDiario2.add((PermisoDiario)permiso);	
			}
		

		}
		return permisosDiario2;
	}



	

	public List<PermisoPeriodo> traerPeriodoFechaYLugar(LocalDate fechaDesde, LocalDate fechaHasta, String lugar) {

		List<Permiso> permisos = permisoPeriodoRepository.traerPermisosPorfechaYlugar(lugar,fechaDesde, fechaHasta);
		List<PermisoPeriodo> permisosPeriodoOk = new ArrayList<PermisoPeriodo>();

		for (Permiso permiso : permisos) {
			if(permiso instanceof PermisoPeriodo) {
				permisosPeriodoOk.add((PermisoPeriodo)permiso);	
			}
		

		}
		return permisosPeriodoOk;
	}




}