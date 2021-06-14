package com.trabajo.Grupo16OO22021.services;

import java.util.List;

import com.trabajo.Grupo16OO22021.entities.User;
import com.trabajo.Grupo16OO22021.models.UserModel;

public interface IUserService {

	public List<User> getAll();

	public UserModel insertOrUpdate(UserModel user);

	public boolean remove(int id);

	public UserModel findById(int id);

	public boolean validate(UserModel userModel);

	public User findByDocumento(int documento);
}
