package com.trabajo.Grupo16OO22021.controllers;

import java.io.IOException;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lowagie.text.DocumentException;
import com.trabajo.Grupo16OO22021.entities.Lugar;
import com.trabajo.Grupo16OO22021.entities.PermisoDiario;
import com.trabajo.Grupo16OO22021.entities.PermisoPeriodo;
import com.trabajo.Grupo16OO22021.helpers.ViewRouteHelper;
import com.trabajo.Grupo16OO22021.models.PermisoModel;
import com.trabajo.Grupo16OO22021.models.PersonaModel;
import com.trabajo.Grupo16OO22021.models.RodadoModel;
import com.trabajo.Grupo16OO22021.models.UserModel;
import com.trabajo.Grupo16OO22021.models.UserRoleModel;
import com.trabajo.Grupo16OO22021.pdf.ProfilePDFExporter;
import com.trabajo.Grupo16OO22021.pdf.UserPDFExporter;
import com.trabajo.Grupo16OO22021.services.IUserService;
import com.trabajo.Grupo16OO22021.repositories.ILugarRepository;
import com.trabajo.Grupo16OO22021.repositories.IRoleRepository;
import com.trabajo.Grupo16OO22021.services.implementation.PermisoService;
import com.trabajo.Grupo16OO22021.services.implementation.PersonaService;
import com.trabajo.Grupo16OO22021.services.implementation.RodadoService;
import com.trabajo.Grupo16OO22021.services.implementation.RoleService;
import com.trabajo.Grupo16OO22021.services.implementation.UserService;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
	@Autowired
	@Qualifier("personaService")
	private PersonaService personaService;

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	@Qualifier("rodadoService")
	private RodadoService rodadoService;

	@Autowired
	@Qualifier("permisoService")
	private PermisoService permisoService;
	
	@Autowired
	@Qualifier("lugarRepository")
	private ILugarRepository lugarRepository;

	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);
		modelAndView.addObject("persona", new PersonaModel());
		modelAndView.addObject("permiso", new PermisoModel());
		return modelAndView;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public ModelAndView adminIndex() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.ADMIN_INDEX);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("username", user.getUsername());
		modelAndView.addObject("users", userService.getAll());
		return modelAndView;
	}

	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/users.html")
	public ModelAndView users() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USERS);
		mAV.addObject("users", userService.getAll());
		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/adminUsers.html")
	public ModelAndView Users() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ADMIN_USERS);
		mAV.addObject("users", userService.getAll());
		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/profiles.html")
	public String profiles(Model model) {
		model.addAttribute("roles", roleRepository.findAll());

		return ViewRouteHelper.PROFILES;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/adminProfiles.html")
	public String adminProfiles(Model model) {
		model.addAttribute("roles", roleRepository.findAll());

		return ViewRouteHelper.ADMIN_PROFILES;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/newuser")
	public ModelAndView create(Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
		mAV.addObject("user", new UserModel());
		model.addAttribute("roles", roleRepository.findAll());
		mAV.addObject("userList", userService.getAll());
		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/newprofile")
	public ModelAndView createProfile() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PROFILE_NEW);
		mAV.addObject("role", new UserRoleModel());
		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/updateuser{id}")
	public ModelAndView get(@PathVariable("id") int id, Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_UPDATE);
		mAV.addObject("user", userService.findById(id));
		model.addAttribute("roles", roleRepository.findAll());

		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/updateprofile{id}")
	public ModelAndView getprofile(@PathVariable("id") int id, Model model) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PROFILE_UPDATE);
		mAV.addObject("role", roleService.findById(id));
		model.addAttribute("roles", roleRepository.findAll());

		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/users/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new java.util.Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=UserList_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<com.trabajo.Grupo16OO22021.entities.User> listUsers = userService.getAll();

		UserPDFExporter exporter = new UserPDFExporter(listUsers);
		exporter.export(response);

	}

	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/profiles/export/pdf")
	public void exportToPDFProfile(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new java.util.Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=ProfileList_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<com.trabajo.Grupo16OO22021.entities.UserRole> listUserRole = roleService.getAll();

		ProfilePDFExporter exporter = new ProfilePDFExporter(listUserRole);
		exporter.export(response);

	}
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/buscar")
	public ModelAndView buscar(Model model) {
		long documento = 0;
		String apellido = null;
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.BUSCAR);
		model.addAttribute("persona", personaService.getAll());
		mAV.addObject("documento", documento);
		mAV.addObject("apellido", apellido);
		
		
		String dominio = null;
		mAV.addObject("dominio", dominio);
		
		LocalDate fechaDesde = null;
		LocalDate fechaHasta = null;

		mAV.addObject("fechaDesde", fechaDesde);
		mAV.addObject("fechaHasta", fechaHasta);
		
		String lugar = null;
		mAV.addObject("lugar", lugar);

		return mAV;

	}
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/permisoxperson")
	public ModelAndView buscarporpersona(long documento, String apellido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.RESULTADOS);
		List<PermisoDiario> permisoDiario = permisoService.buscarPermisoDiario(documento, apellido);
		List<PermisoPeriodo> permisoPeriodo = permisoService.buscarPermisoPeriodo(documento, apellido);
		mAV.addObject("permisoPeriodo", permisoPeriodo);
		mAV.addObject("permisoDiario", permisoDiario);
		return mAV;

	}
	
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/permisoxrodado")
	public ModelAndView permisoPorRodado(String dominio) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.RESULTADOS);
		List<PermisoPeriodo> permisoPeriodo = permisoService.buscarPermisoPeriodoRodado(dominio);
		mAV.addObject("permisoPeriodo", permisoPeriodo);
		return mAV;

	}
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/permisoxfecha")
	public ModelAndView getprfecha(String fechaDesde, String fechaHasta) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.RESULTADOS);
		LocalDate fechaDesde1 = LocalDate.parse(fechaDesde);
		LocalDate fechaHasta1 = LocalDate.parse(fechaHasta);

		List<PermisoDiario> permisosDiario = permisoService.traerDiarioEntreFechas(fechaDesde1, fechaHasta1);
		List<PermisoPeriodo> permisosPeriodo = permisoService.traerPeriodoEntreFechas(fechaDesde1, fechaHasta1);
		

		mAV.addObject("permisoPeriodo", permisosPeriodo);
		mAV.addObject("permisoDiario", permisosDiario);

		return mAV;
	}
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@PostMapping("/permisoxfechaylugar")
	public ModelAndView getprfechaylugar(String fechaDesde, String fechaHasta, String lugar) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.RESULTADOS);
		LocalDate fechaDesde1 = LocalDate.parse(fechaDesde);
		LocalDate fechaHasta1 = LocalDate.parse(fechaHasta);
		List<PermisoDiario> permisosDiario = permisoService.traerDiarioFechaYLugar(fechaDesde1, fechaHasta1, lugar);
		List<PermisoPeriodo> permisosPeriodo = permisoService.traerPeriodoFechaYLugar(fechaDesde1, fechaHasta1, lugar);
		for(PermisoDiario p : permisosDiario) {
			System.out.println(p.getIdPermiso());

		}
		mAV.addObject("permisoPeriodo", permisosPeriodo);
		mAV.addObject("permisoDiario", permisosDiario);

		return mAV;
	}
}
