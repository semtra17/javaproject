package com.trabajo.Grupo16OO22021.converters;

import org.springframework.stereotype.Component;

import com.trabajo.Grupo16OO22021.entities.User;
import com.trabajo.Grupo16OO22021.models.UserModel;

@Component("userConverter")
public class UserConverter {

	public UserModel entityToModel(User user) {
		return new UserModel(user.getId(), user.getName(), user.getLastname(), user.getDocument(),user.getEmail(),user.getUsername(),user.getPassword(), user.isEnabled(), user.getUserRole());
	}

	public User modelToEntity(UserModel userModel) {
		return new User(userModel.getId(), userModel.getName(), userModel.getLastname(), userModel.getDocument(),userModel.getEmail(),userModel.getUsername(),userModel.getPassword(),userModel.isEnabled(),userModel.getUserRole());
	}
}