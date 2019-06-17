package com.project.webapp.dto.response;

import com.project.webapp.model.Role;

public class RoleDto {
	private int idRole;
	private String name;
	private boolean isDeleted;

	public RoleDto() {
	};

	public RoleDto(Role role) {
		super();
		this.idRole = role.getIdRole();
		this.name = role.getName();
	}

	public RoleDto(int idRole, String name, boolean isDeleted) {
		super();
		this.idRole = idRole;
		this.name = name;
		this.isDeleted = isDeleted;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
