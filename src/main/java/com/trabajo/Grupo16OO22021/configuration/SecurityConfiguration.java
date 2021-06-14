package com.trabajo.Grupo16OO22021.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.trabajo.Grupo16OO22021.services.implementation.UserService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	private CustomLoginSuccessHandler successHandler;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/*", "/imgs/*", "/js/*", "/vendor/bootstrap/css/*", "/vendor/jquery/*", "/vendor/bootstrap/js/*").permitAll()
				.antMatchers("/qr-code{id}","/qrcode{id}","/home", "/gestion","/gestiondepermisos", "/crearpersona", "/newrodado","/crearPermisoDiario","/crearPermisoPeriodo","/permisoxpersona","/buscarporpersona").permitAll()
				.antMatchers("/index/").hasAnyAuthority("AUDITOR_ROLE")
				.antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/home").loginProcessingUrl("/loginprocess")
				.usernameParameter("username").passwordParameter("password")
				.successHandler(successHandler)
			.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/logout").permitAll();
	}
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
    
}