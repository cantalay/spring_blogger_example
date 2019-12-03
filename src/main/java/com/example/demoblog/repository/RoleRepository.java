package com.example.demoblog.repository;

import com.example.demoblog.model.ERole;
import com.example.demoblog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}
