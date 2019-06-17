package com.project.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.webapp.dto.response.CompanyDto;
import com.project.webapp.dto.response.RoleDto;
import com.project.webapp.model.Company;
import com.project.webapp.model.Role;
import com.project.webapp.repository.RoleRepository;
import com.project.webapp.service.RoleService;
import com.project.webapp.util.WebShopException;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

//	@Override
//	public RoleDto createNewRole(RoleDto roleDto) {
//		Role role = new Role(roleDto);
//		role = roleRepository.saveAndFlush(role);
//		return new RoleDto(role);
//	}
	
	@Override
	public List<RoleDto> getAllRoles() {
		List<Role> roles = roleRepository.findAll();
		List<RoleDto> rolesDto = new ArrayList<RoleDto>();
		for(Role role: roles) {
			rolesDto.add(new RoleDto(role));
		}
		return rolesDto;
	}

	@Override
	public RoleDto getRoleById(int id) {
		Role role = roleRepository.getOne(id);
		return new RoleDto(role);
	}

//	@Override
//	public RoleDto editRole(int id, RoleDto roleDto) {
//		Role role = roleRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no role iwth ID: " + id));
//		role = roleRepository.saveAndFlush(role);
//		return new RoleDto(role);
//	}
//
//	@Override
//	public RoleDto deleteRoleById(int id) {
//		Role role = roleRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no such ID"));
//		role.setDeleted(false);
//		role = roleRepository.saveAndFlush(role);
//		return new RoleDto(role);
//	}

}
