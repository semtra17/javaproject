package com.trabajo.Grupo16OO22021.models;

public class NotificacionModel {
	private String mensajeError;
	private String mensajeConfirmacion;
	
	

	public NotificacionModel(String mensajeError, String mensajeConfirmacion) {
		super();
		this.mensajeError = mensajeError;
		this.mensajeConfirmacion = mensajeConfirmacion;
	}

	public NotificacionModel() {
		
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getMensajeConfirmacion() {
		return mensajeConfirmacion;
	}

	public void setMensajeConfirmacion(String mensajeConfirmacion) {
		this.mensajeConfirmacion = mensajeConfirmacion;
	}
	
	
	
}
