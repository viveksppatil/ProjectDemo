package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Entity.Role;
import com.Enum.RolesEnum;
import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RolesEnum name);
	
}
