package com.trabajo.Grupo16OO22021.controllers;


import org.springframework.beans.factory.annotation.Autowired;




import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.trabajo.Grupo16OO22021.entities.User;
import com.trabajo.Grupo16OO22021.helpers.ViewRouteHelper;
import com.trabajo.Grupo16OO22021.models.*;
import com.trabajo.Grupo16OO22021.services.implementation.PermisoService;
import com.trabajo.Grupo16OO22021.services.implementation.RoleService;

import com.trabajo.Grupo16OO22021.repositories.*;
import com.trabajo.Grupo16OO22021.services.*;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/manage")
public class ConfigurationController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	

	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	
	@GetMapping("")
	public ModelAndView configuracion(Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.MANAGE);
		mAV.addObject("users", userService.getAll());
		model.addAttribute("roles",roleRepository.findAll());

		return mAV;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/createuser")
	public RedirectView create(@ModelAttribute("user") UserModel userModel,RedirectAttributes atributos) {
		
		NotificacionModel notificacion = new NotificacionModel();
		User user = userService.findByDocumento(userModel.getDocument());
		if(!userService.validate(userModel)) {
			notificacion.setMensajeError("Hubo un error al crear el usuario, vuelva a intentarlo");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			return new RedirectView(ViewRouteHelper.MANAGE_ROOT);
		}
		if( user != null) {
			notificacion.setMensajeError("El DNI que intento registrar ya se encuentra en el sistema");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			
			return new RedirectView(ViewRouteHelper.MANAGE_ROOT);
		}
			
		
		else {
			notificacion.setMensajeConfirmacion("Usuario creado correctamente");
			atributos.addFlashAttribute("mensajeConfirmacion", notificacion.getMensajeConfirmacion());
			userService.insertOrUpdate(userModel);
			return new RedirectView(ViewRouteHelper.MANAGE_ROOT);
		}
		
	}		
	
	@PostMapping("/updateuser{id}")
	public RedirectView update(@PathVariable("id") int id, @ModelAttribute("user") UserModel userModel, Model model,RedirectAttributes atributos) {
		
		NotificacionModel notificacion = new NotificacionModel();
		
		if(!userService.validate(userModel)) {
			ModelAndView mAV = new ModelAndView();
			notificacion.setMensajeError("Hubo un error al intentar modificar el usuario, intentelo de nuevo");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			mAV.addObject("user", userService.findById(id));
			model.addAttribute("roles",roleRepository.findAll());
			return new RedirectView(ViewRouteHelper.MANAGE_ROOT);
		}else {
			notificacion.setMensajeConfirmacion("Usuario modificado correctamente");
			atributos.addFlashAttribute("mensajeConfirmacion", notificacion.getMensajeConfirmacion());
			userService.insertOrUpdate(userModel);
			return new RedirectView(ViewRouteHelper.MANAGE_ROOT);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/createprofile")
	public RedirectView newProfile(@ModelAttribute("role") UserRoleModel userRoleModel,RedirectAttributes atributos) {
		NotificacionModel notificacion = new NotificacionModel();
		if(!roleService.validate(userRoleModel)) {
			notificacion.setMensajeError("Hubo un error al intentar crear el perfil, intentelo de nuevo");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			return new RedirectView(ViewRouteHelper.MANAGE_ROOT);
		}else {
			notificacion.setMensajeConfirmacion("Perfil creado correctamente");
			atributos.addFlashAttribute("mensajeConfirmacion", notificacion.getMensajeConfirmacion());
			roleService.insertOrUpdate(userRoleModel);
			return new RedirectView(ViewRouteHelper.MANAGE_ROOT);
		}
	}
	
	@PostMapping("/updateprofile{id}")
	public RedirectView updateProfile(@PathVariable("id") int id, @ModelAttribute("role") UserRoleModel userRoleModel,RedirectAttributes atributos) {
		NotificacionModel notificacion = new NotificacionModel();
		if(!roleService.validate(userRoleModel)) {
			notificacion.setMensajeError("Hubo un error al intentar modificar el perfil, intentelo de nuevo");
			atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
			return new RedirectView(ViewRouteHelper.PROFILE_UPDATE_ROOT);
		}else {
			notificacion.setMensajeConfirmacion("Perfil modificado correctamente");
			atributos.addFlashAttribute("mensajeConfirmacion", notificacion.getMensajeConfirmacion());
			roleService.insertOrUpdate(userRoleModel);
			return new RedirectView(ViewRouteHelper.MANAGE_ROOT);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id ,RedirectAttributes atributos) {
		NotificacionModel notificacion = new NotificacionModel();
		notificacion.setMensajeConfirmacion("Usuario eliminado correctamente");
		atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
		userService.remove(id);
		return new RedirectView(ViewRouteHelper.MANAGE_ROOT);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/deleteprofile/{id}")
	public RedirectView deleteprofile(@PathVariable("id") int id,RedirectAttributes atributos) {
		NotificacionModel notificacion = new NotificacionModel();
		notificacion.setMensajeConfirmacion("Perfil eliminado correctamente");
		atributos.addFlashAttribute("mensajeError", notificacion.getMensajeError());
		roleService.remove(id);
		return new RedirectView(ViewRouteHelper.MANAGE_ROOT);
	}
	
	
	
	
}