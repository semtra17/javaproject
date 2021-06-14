package com.trabajo.Grupo16OO22021.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.trabajo.Grupo16OO22021.entities.PermisoDiario;
import com.trabajo.Grupo16OO22021.entities.PermisoPeriodo;
import com.trabajo.Grupo16OO22021.repositories.IPermisoDiarioRepository;
import com.trabajo.Grupo16OO22021.repositories.IPermisoPeriodoRepository;
import com.trabajo.Grupo16OO22021.services.QRCodeService;

@RestController
public class QRCodeController {

	private final int WIDTH = 250;
	private final int HEIGHT = 250;

	@Autowired
	private QRCodeService qrCodeService;

	@Autowired
	@Qualifier("permisoPeriodoRepository")
	private IPermisoPeriodoRepository permisoPeriodoRepository;
	
	@Autowired
	@Qualifier("permisoDiarioRepository")
	private IPermisoDiarioRepository permisoDiarioRepository;


	@GetMapping("/qr-code{id}")
	public ResponseEntity<byte[]> getQrCode(@PathVariable("id") int id) {
		Optional<PermisoPeriodo> permiso = permisoPeriodoRepository.findById(id);

		String link = "https://mattc7.github.io/Grupo-16-OO2-2021/index?";

		String fecha = "fecha=" + permiso.get().getFecha();

		String pedido = "&documento=" + permiso.get().getPedido().getDocumento() + "&nombre="
				+ permiso.get().getPedido().getNombre() + "&apellido=" + permiso.get().getPedido().getApellido();
		
		String rodado = "&dominio=" + permiso.get().getRodado().getDominio();
		
		String cantDiasYVacaciones = "&cantDias=" + permiso.get().getCantDias() + "&vacaciones="
				+ permiso.get().isVacaciones();
		
		String informacion = link + fecha + pedido + rodado + cantDiasYVacaciones;
	
		byte[] qrImage = qrCodeService.generate(informacion, WIDTH, HEIGHT);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
	}
	
	@GetMapping("/qrcode{id}")
	public ResponseEntity<byte[]> getQrCode1(@PathVariable("id") int id) {
		Optional<PermisoDiario> permiso = permisoDiarioRepository.findById(id);
		String link = "https://mattc7.github.io/Grupo-16-OO2-2021/index?";

		String fecha = "fecha=" + permiso.get().getFecha();

		String pedido = "&documento=" + permiso.get().getPedido().getDocumento() + "&nombre="
				+ permiso.get().getPedido().getNombre() + "&apellido=" + permiso.get().getPedido().getApellido();
			
		String motivo = "&motivo=" + permiso.get().getMotivo();
		
		String informacion = link + fecha + pedido + motivo;
				
		
		byte[] qrImage = qrCodeService.generate(informacion, WIDTH, HEIGHT);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
	}
}