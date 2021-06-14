package com.trabajo.Grupo16OO22021.converters;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.trabajo.Grupo16OO22021.entities.UserRole;
import com.trabajo.Grupo16OO22021.models.UserRoleModel;

@Component("roleConverter")
public class RoleConverter {
	public UserRoleModel entityToModel(UserRole userRole) {
		return new UserRoleModel(userRole.getId(), userRole.getName(), userRole.getDescription());
	}

	public UserRole modelToEntity(UserRoleModel userRoleModel) {
		return new UserRole(userRoleModel.getId(), userRoleModel.getName(),userRoleModel.getDescription());
	}

	public UserRoleModel entityToModel(Optional<UserRole> userRole) {
		return new UserRoleModel(userRole.get().getId(), userRole.get().getName(), userRole.get().getDescription());
	}

	
}
