package com.blogjava.Service;

import java.util.Optional;

import com.blogjava.model.Role;
import com.blogjava.model.RoleName;

public interface IRoleService {
	Optional<Role> findByName(RoleName name);
}
