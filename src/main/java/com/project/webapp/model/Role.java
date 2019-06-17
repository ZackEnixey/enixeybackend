package com.project.webapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.project.webapp.dto.response.RoleDto;

@Entity
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	private int idRole;

	private String name;

	@ManyToMany
	private List<AppUser> appUsers;

	public Role() {
	}

	public Role(RoleDto roleDto) {
		super();
		this.idRole = roleDto.getIdRole();
		this.name = roleDto.getName();
	}

	public Role(int idRole, String name, boolean isDeleted) {
		super();
		this.idRole = idRole;
		this.name = name;
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

	public List<AppUser> getAppUsers() {
		return appUsers;
	}

	public void setAppUsers(List<AppUser> appUsers) {
		this.appUsers = appUsers;
	}


}
