package com.trabajo.Grupo16OO22021.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.trabajo.Grupo16OO22021.entities.PermisoDiario;
import com.trabajo.Grupo16OO22021.entities.PermisoPeriodo;
import com.trabajo.Grupo16OO22021.entities.Persona;
import com.trabajo.Grupo16OO22021.entities.Rodado;
import com.trabajo.Grupo16OO22021.helpers.ViewRouteHelper;
import com.trabajo.Grupo16OO22021.models.NotificacionModel;
import com.trabajo.Grupo16OO22021.models.PermisoDiarioModel;
import com.trabajo.Grupo16OO22021.models.PermisoPeriodoModel;
import com.trabajo.Grupo16OO22021.models.PersonaModel;
import com.trabajo.Grupo16OO22021.models.RodadoModel;
import com.trabajo.Grupo16OO22021.repositories.ILugarRepository;
import com.trabajo.Grupo16OO22021.repositories.IPermisoDiarioRepository;
import com.trabajo.Grupo16OO22021.repositories.IPermisoPeriodoRepository;
import com.trabajo.Grupo16OO22021.repositories.IPersonaRepository;

import com.trabajo.Grupo16OO22021.services.implementation.PermisoService;
import com.trabajo.Grupo16OO22021.services.implementation.PersonaService;
import com.trabajo.Grupo16OO22021.services.implementation.RodadoService;

@Controller
public class GestionController {

	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;

	@Autowired
	@Qualifier("lugarRepository")
	private ILugarRepository lugarRepository;

	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;

	@Autowired
	@Qualifier("personaRepository")
	private IPersonaRepository personaRepository;

	@Autowired
	@Qualifier("permisoPeriodoRepository")
	private IPermisoPeriodoRepository permisoPeriodoRepository;

	@Autowired
	@Qualifier("permisoDiarioRepository")
	private IPermisoDiarioRepository permisoDiarioRepository;

	@Autowired
	@Qualifier("rodadoService")
	private RodadoService rodadoService;

	@GetMapping("/gestiondepermisos")
	public ModelAndView gestionPermisos(Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GESTION_PERMISOS);
		
		model.addAttribute("notificacion", new NotificacionModel("",""));
		mAV.addObject("persona", new PersonaModel());
		mAV.addObject("rodado", new RodadoModel());
		mAV.addObject("permisoDiario", new PermisoDiarioModel());
		mAV.addObject("permisoPeriodo", new PermisoPeriodoModel());
		mAV.addObject("lugares", lugarRepository.findAll());
		return mAV;
	}

	@PostMapping("/crearpersona")
	public RedirectView create(@ModelAttribute("persona") PersonaModel personaModel,RedirectAttributes atributos) {
		NotificacionModel notificacion = new NotificacionModel();
		Persona p = personaService.findByDocumento(personaModel.getDocumento());
		if (!personaService.validate(personaModel)) {
			notificacion.setMensajeError("Uno de los campos fue completado erroneamente, vuelva a intentarlo");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			return new RedirectView(ViewRouteHelper.PERSONA_NEW_ROOT);
		} 
		
		if( p != null) {
			notificacion.setMensajeError("El DNI que intento registrar ya se encuentra en el sistema");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			
			return new RedirectView(ViewRouteHelper.PERSONA_NEW_ROOT);
		}
			
		else {
			personaService.insertOrUpdate(personaModel);
			notificacion.setMensajeConfirmacion("Persona creada correctamente");
			atributos.addFlashAttribute("mensajeConfirmacion", notificacion.getMensajeConfirmacion());
			return new RedirectView(ViewRouteHelper.PERMISO_NEW_ROOT);
		}
	

	}

	@PostMapping("/newrodado")
	public RedirectView cargarrodados(RodadoModel rodadoModel, RedirectAttributes atributos) {
		NotificacionModel notificacion = new NotificacionModel();
		Rodado r = rodadoService.findDominio(rodadoModel.getDominio());
		if (!rodadoService.validate(rodadoModel)) {
			notificacion.setMensajeError("Uno de los campos fue completado erroneamente, vuelva a intentarlo");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			return new RedirectView(ViewRouteHelper.RODADO_NEW_ROOT);
		} if( r != null){
			notificacion.setMensajeError("El dominio que intento registrar ya se encuentra en el sistema");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			return new RedirectView(ViewRouteHelper.RODADO_NEW_ROOT);
		}
		else {
			rodadoService.insertOrUpdate(rodadoModel);
			notificacion.setMensajeConfirmacion("Rodado creado correctamente");
			atributos.addFlashAttribute("mensajeConfirmacion", notificacion.getMensajeConfirmacion());
			return new RedirectView(ViewRouteHelper.PERMISO_NEW_ROOT);
		}
	}

	@PostMapping("/crearPermisoDiario")
	public RedirectView newPermisoDiario(PermisoDiarioModel diarioModel, long documento,RedirectAttributes atributos) {
		Persona p = personaService.findByDocumento(documento);
		diarioModel.setPedido(p);
		NotificacionModel notificacion = new NotificacionModel();
		
		
		
		if (!permisoService.validetePermisoDiario(diarioModel) || p == null) {
			notificacion.setMensajeError("No se encuentra la persona que se desea agregar al permiso, recuerde completar el primer formulario antes de gestionar el permiso");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			
			return new RedirectView(ViewRouteHelper.PERMISO_NEW_ROOT);
		
		} 
		
		if(!permisoService.comprobarFecha(diarioModel)) {
			
			notificacion.setMensajeError("La fecha ingresada es anterior a la fecha de este momento, vuelva a intentarlo");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			
			return new RedirectView(ViewRouteHelper.PERMISO_NEW_ROOT);
			
		}
		else {
			notificacion.setMensajeConfirmacion("Permiso creado correctamente");
			atributos.addFlashAttribute("mensajeConfirmacion", notificacion.getMensajeConfirmacion());

			permisoService.insertOrUpdate(diarioModel);
			return new RedirectView(ViewRouteHelper.PERMISO_NEW_ROOT);
		}

	}
	



	@PostMapping("/crearPermisoPeriodo")
	public RedirectView newPermisoPeriodo(PermisoPeriodoModel periodoModel,String dominio, long documento,RedirectAttributes atributos) {
		Persona p = personaService.findByDocumento(documento);
		periodoModel.setPedido(p);
		Rodado r = rodadoService.findDominio(dominio);
		periodoModel.setRodado(r);
		NotificacionModel notificacion = new NotificacionModel();
		if (!permisoService.validatePermisoPeriodo(periodoModel) || p == null) {
			notificacion.setMensajeError("No se encuentra la persona que se desea agregar al permiso, recuerde completar el primer formulario antes de gestionar el permiso");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			return new RedirectView(ViewRouteHelper.PERMISO_NEW_ROOT);
		}
		if(!permisoService.validatePermisoPeriodo(periodoModel) || r == null){
			notificacion.setMensajeError("No se halló el rodado ingresado para obtener el permiso, reintente o vuelva a completar el formulario para rodados");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			return new RedirectView(ViewRouteHelper.PERMISO_NEW_ROOT);
			} 
		if(!permisoService.comprobarFecha(periodoModel)) {
					
					notificacion.setMensajeError("La fecha ingresada es anterior a la fecha de este momento, vuelva a intentarlo");
					atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
					
					return new RedirectView(ViewRouteHelper.PERMISO_NEW_ROOT);
					
				}
		else {
			notificacion.setMensajeConfirmacion("Permiso creado correctamente");
			atributos.addFlashAttribute("mensajeConfirmacion", notificacion.getMensajeConfirmacion());
			permisoService.insertOrUpdate(periodoModel);
			return new RedirectView(ViewRouteHelper.PERMISO_NEW_ROOT);
		}

	}
	
	@GetMapping("/buscarporpersona")
	public ModelAndView buscarpersona(Model model) {
		long documento = 0;
		String apellido = null;
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.BUSCAR_POR_PERSONA);
		model.addAttribute("persona", personaService.getAll());
		mAV.addObject("documento", documento);
		mAV.addObject("apellido", apellido);
		
		return mAV;

	}
	@PostMapping("/permisoxpersona")
	public ModelAndView buscarporpersona(long documento, String apellido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ENCONTRADO);
		List<PermisoDiario> permisoDiario = permisoService.buscarPermisoDiario(documento, apellido);
		List<PermisoPeriodo> permisoPeriodo = permisoService.buscarPermisoPeriodo(documento, apellido);
		mAV.addObject("permisoPeriodo", permisoPeriodo);
		mAV.addObject("permisoDiario", permisoDiario);
		return mAV;

	}

}
