package com.project.webapp.dto.request;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoggedInUserDTO {

	private int id;
	private String token;
	private String username;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;

	public LoggedInUserDTO(){

	}

	public LoggedInUserDTO(int id, String token, String username, String email, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.token = token;
		this.username = username;
		this.email = email;
		this.authorities = authorities;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
