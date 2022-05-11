package com.project.myapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.myapp.models.ERole;
import com.project.myapp.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

	Optional<Role> findByName(ERole name);
}
