package com.project.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.webapp.dto.response.RoleDto;
import com.project.webapp.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
