package com.blogjava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogjava.model.Role;
import com.blogjava.model.RoleName;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName name);
}
