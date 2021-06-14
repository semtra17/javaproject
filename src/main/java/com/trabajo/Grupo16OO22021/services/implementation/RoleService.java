package com.trabajo.Grupo16OO22021.services.implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.trabajo.Grupo16OO22021.converters.RoleConverter;
import com.trabajo.Grupo16OO22021.entities.UserRole;
import com.trabajo.Grupo16OO22021.models.UserRoleModel;
import com.trabajo.Grupo16OO22021.repositories.IRoleRepository;
import com.trabajo.Grupo16OO22021.services.IRoleService;

@Qualifier("roleService")
@Service
public class RoleService implements IRoleService{

	@Autowired
	@Qualifier("roleRepository")
	private IRoleRepository roleRepository;
	

	@Autowired
	@Qualifier("roleConverter")
	private RoleConverter roleConverter;
	
	@Override
	public List<UserRole> getAll() {
		return roleRepository.findAll();
	}

	@Override
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel) {
		UserRole userRole = roleRepository.save(roleConverter.modelToEntity(userRoleModel));
		
	return  roleConverter.entityToModel(userRole);
	}

	@Override
	public boolean remove(int id) {
		try {
			roleRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public UserRoleModel findById(int id) {
		return roleConverter.entityToModel(roleRepository.findById(id));
		}
	@Override
	public boolean validate(UserRoleModel userRoleModel) {
		if(userRoleModel.getName().equals("")||userRoleModel.getDescription().equals("")) {
			return false;
		}
		else {
			return true;
		}
	}
	
}
