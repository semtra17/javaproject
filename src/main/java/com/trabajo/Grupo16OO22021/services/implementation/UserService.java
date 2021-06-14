package com.trabajo.Grupo16OO22021.services.implementation;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trabajo.Grupo16OO22021.services.IUserService;
import com.trabajo.Grupo16OO22021.converters.UserConverter;
import com.trabajo.Grupo16OO22021.entities.UserRole;
import com.trabajo.Grupo16OO22021.models.UserModel;
import com.trabajo.Grupo16OO22021.repositories.IUserRepository;

@Qualifier("userService")
@Service
public class UserService implements IUserService, UserDetailsService {

	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;

	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public List<com.trabajo.Grupo16OO22021.entities.User> getAll() {
		return userRepository.findAll();
	}


	@Override
	public boolean remove(int id) {
		try {
			userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public UserModel findById(int id) {
		return userConverter.entityToModel(userRepository.findById(id));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.trabajo.Grupo16OO22021.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return buildUser(user, buildGrantedAuthorities(user.getUserRole()));
	}

	private User buildUser(com.trabajo.Grupo16OO22021.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, // accountNonExpired,
																										// credentialsNonExpired,
																										// accountNonLocked,
				true, grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(UserRole userRole) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getDescription()));
		
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}


	@Override
	public UserModel insertOrUpdate(UserModel userModel) {
		String encodePassword = bCryptPasswordEncoder.encode(userModel.getPassword());
		userModel.setPassword(encodePassword);
		com.trabajo.Grupo16OO22021.entities.User user = userRepository.save(userConverter.modelToEntity(userModel));
		user = userRepository.save(user);
		
	return  userConverter.entityToModel(user);
	}
	public com.trabajo.Grupo16OO22021.entities.User findByDocumento(int documento) {
		List<com.trabajo.Grupo16OO22021.entities.User> user = getAll();
		com.trabajo.Grupo16OO22021.entities.User u = new com.trabajo.Grupo16OO22021.entities.User();
		for(com.trabajo.Grupo16OO22021.entities.User u1 : user) {
			if(u1.getDocument()==documento) {
				u = u1;
				return u;
			}
		}
		return null;
	}

	@Override
	public boolean validate(UserModel userModel) {
		if (userModel.getName().equals("") || userModel.getName().length() < 3 || userModel.getLastname().equals("")
				|| userModel.getLastname().length() < 3 || userModel.getEmail().equals("")
				|| userModel.getUsername().equals("") || userModel.getUsername().length() < 5
				|| Integer.toString(userModel.getDocument()).length() < 7 || userModel.getPassword().equals("")
				|| userModel.getPassword().length() < 5||userModel.getUserRole()==null) {
			return false;
		} else {
			return true;
		}

	}


	

}
