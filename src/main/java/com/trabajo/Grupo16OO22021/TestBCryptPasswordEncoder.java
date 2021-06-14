package com.trabajo.Grupo16OO22021;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.trabajo.Grupo16OO22021.entities.PermisoDiario;
import com.trabajo.Grupo16OO22021.services.implementation.PermisoService;

public class TestBCryptPasswordEncoder {

	public static void main(String[] args) {
		
//		 BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
//		 System.out.println(pe.encode("admin"));
		

		PermisoService permiso = new PermisoService();
		
		LocalDate fecha1 = LocalDate.of(2021, 02, 02);
		LocalDate fecha2 = LocalDate.of(2021, 02, 04);

		
		List<PermisoDiario> diario = permiso.traerDiarioEntreFechas(fecha1, fecha2);

		 for (PermisoDiario permisoDiario : diario) {
			
			 System.out.println(permisoDiario);
		}
			
	}
}