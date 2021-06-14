package com.trabajo.Grupo16OO22021.services.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trabajo.Grupo16OO22021.converters.PermisoConverter;
import com.trabajo.Grupo16OO22021.entities.Lugar;
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
	
	@Override
	public List<PermisoDiario> buscarPermisoDiario(long documento, String apellido) {
		List<PermisoDiario> permisoDiario = getAll1();
		List<PermisoDiario> permisoDiario1 = new ArrayList<PermisoDiario>();
		for (PermisoDiario p : permisoDiario) {
			if (p.getPedido().getApellido().equals(apellido) && p.getPedido().getDocumento() == documento) {
				permisoDiario1.add(p);
			}
		}
		return permisoDiario1;
	}

	@Override
	public List<PermisoPeriodo> buscarPermisoPeriodo(long documento, String apellido) {
		List<PermisoPeriodo> permisoPeriodo = getAll();
		List<PermisoPeriodo> permisoPeriodo1 = new ArrayList<PermisoPeriodo>();
		for (PermisoPeriodo p : permisoPeriodo) {
			if (p.getPedido().getApellido().equals(apellido) && p.getPedido().getDocumento() == documento) {
				permisoPeriodo1.add(p);
			}
		}
		return permisoPeriodo1;
	}

	public List<PermisoPeriodo> buscarPermisoPeriodoRodado(String dominio) {
		List<PermisoPeriodo> permisoPeriodo = getAll();
		List<PermisoPeriodo> permisoPeriodo1 = new ArrayList<PermisoPeriodo>();
		for (PermisoPeriodo p : permisoPeriodo) {
			if (p.getRodado().getDominio().equals(dominio)) {
				permisoPeriodo1.add(p);
			}
		}
		return permisoPeriodo1;
	}

	public List<PermisoDiario> traerDiarioEntreFechas(LocalDate desde, LocalDate hasta) {

//>>>>>>> 71379bfe2e93a4b8aaf4d5a886f1db8e020edaa6
		List<PermisoDiario> permisos = this.getAll1();
		List<PermisoDiario> permisosOk = new ArrayList<PermisoDiario>();

		for (PermisoDiario permiso : permisos) {

			if (permiso.getFecha().isAfter(desde) && permiso.getFecha().isBefore(hasta)||permiso.getFecha().isEqual(hasta)|| permiso.getFecha().isEqual(desde)) {

				permisosOk.add(permiso);

			}

		}
		return permisosOk;
	}

	public List<PermisoPeriodo> traerPeriodoEntreFechas(LocalDate desde, LocalDate hasta) {

		List<PermisoPeriodo> permisos = this.getAll();
		List<PermisoPeriodo> permisosOk = new ArrayList<PermisoPeriodo>();

		for (PermisoPeriodo permiso : permisos) {

			LocalDate desdePlus = desde.minusDays(permiso.getCantDias());

			if (permiso.getFecha().isAfter(desdePlus) && permiso.getFecha().isBefore(hasta)||permiso.getFecha().isEqual(desdePlus)||permiso.getFecha().isEqual(hasta)) {

				permisosOk.add(permiso);
			}

		}
		return permisosOk;
	}

	public List<PermisoDiario> traerDiarioFechaYLugar(LocalDate fechaDesde, LocalDate fechaHasta, String lugar) {

		List<PermisoDiario> permisosDiario = this.traerDiarioEntreFechas(fechaDesde, fechaHasta);
		List<PermisoDiario> permisosDiario2 = new ArrayList<PermisoDiario>();

		for (PermisoDiario permisoDiario : permisosDiario) {

			for (Lugar permisoLugar : permisoDiario.getDesdeHasta()) {
				if (permisoLugar.getLugar().equals(lugar.toUpperCase()))
					permisosDiario2.add(permisoDiario);
			}
		}
		return permisosDiario2;
	}

	public List<PermisoPeriodo> traerPeriodoFechaYLugar(LocalDate fechaDesde, LocalDate fechaHasta, String lugar) {

		List<PermisoPeriodo> permisosPeriodo = this.traerPeriodoEntreFechas(fechaDesde, fechaHasta);
		List<PermisoPeriodo> permisosPeriodoOk = new ArrayList<PermisoPeriodo>();

		for (PermisoPeriodo permisoPeriodo : permisosPeriodo) {

			for (Lugar permisoLugar : permisoPeriodo.getDesdeHasta()) {
				if (permisoLugar.getLugar().equals(lugar.toUpperCase()))
					permisosPeriodoOk.add(permisoPeriodo);
			}
		}
		return permisosPeriodoOk;
	}


}