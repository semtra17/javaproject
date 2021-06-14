package com.trabajo.Grupo16OO22021.helpers;

public class ViewRouteHelper {
	public final static String INDEX = "files/index";

	// Login and logout form
	public final static String HOME = "files/home";
	public final static String LOGOUT = "files/login/logout";
	public final static String GESTION_PERMISOS = "files/gestiondepermisos";
	// Users and profiles lists
	public final static String PROFILES = "files/profiles";
	public final static String USERS = "files/users";

	// Admin routes
	public final static String ADMIN_PROFILES = "files/adminProfiles";
	public final static String ADMIN_USERS = "files/adminUsers";
	public final static String ADMIN_INDEX = "files/admin";

	// Manage
	public final static String MANAGE = "files/manage/manage";
	public final static String USER_UPDATE = "files/manage/updateuser";
	public final static String USER_NEW = "files/manage/newuser";
	public final static String PROFILE_NEW = "files/manage/newprofile";
	public static final String PROFILE_UPDATE = "files/manage/updateprofile";

	// ROOT
	public final static String MANAGE_ROOT = "/manage";
	public final static String USER_NEW_ROOT = "/newuser";
	public static final String USER_UPDATE_ROOT = "/updateuser{id}";
	public static final String PROFILE_NEW_ROOT = "/newprofile";
	public static final String PROFILE_UPDATE_ROOT = "/updateprofile{id}";

	// Personas ROOT
	public final static String PERSONA_NEW_ROOT = "/gestiondepermisos";
	public final static String PERMISO_NEW_ROOT = "/gestiondepermisos";
	public final static String RODADO_NEW_ROOT = "/gestiondepermisos";

	public final static String BUSCAR = "/files/buscar";
	public final static String RESULTADOS = "/files/resultadoBusqueda";
	
	public final static String BUSCAR_POR_PERSONA = "/files/buscarporpersona";
	public final static String ENCONTRADO = "/files/permisoxpersona";

	public static final String BUSCAR_POR_RODADO = "/files/buscarporrodado";
	public static final String ENCONTRADO1 = "/files/permisoxrodado";
	
	public static final String BUSCAR_POR_FECHA = "/files/buscarporfecha";
	public static final String PERMISO_FECHA = "/files/permisoxfecha";

	public static final String PERMISO_POR_PERSONA1 = "files/permisoxperson";
	public static final String BUSCAR_POR_PERSONA1 = "files/buscarporperson";

	public static final String BUSCAR_POR_FECHAYLUGAR = "files/buscarporfechaylugar";
	public static final String PERMISO_POR_FECHAYLUGAR = "files/permisoxfechaylugar";



}