package com.trabajo.Grupo16OO22021.services;

import java.util.List;

import com.trabajo.Grupo16OO22021.entities.UserRole;
import com.trabajo.Grupo16OO22021.models.UserRoleModel;

public interface IRoleService {

	public List<UserRole> getAll();

	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel);

	public boolean remove(int id);

	public boolean validate(UserRoleModel userRoleModel);

}
