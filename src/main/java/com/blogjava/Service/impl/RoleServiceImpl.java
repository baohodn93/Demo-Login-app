package com.blogjava.Service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogjava.Service.IRoleService;
import com.blogjava.model.Role;
import com.blogjava.model.RoleName;
import com.blogjava.repository.IRoleRepository;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleRepository roleRepository;
	
	@Override
	public Optional<Role> findByName(RoleName name) {
		
		return roleRepository.findByName(name);
	}

}
