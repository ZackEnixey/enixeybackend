package com.project.webapp.dto.response;

import com.project.webapp.model.AppUser;

public class AppUserDto {
	private int idAppUser;
	private String name;
	private String lastName;
	private String email;
	private String pass;
	private String country;
	private String city;
	private String address;
	private String personalId;
	private String registerToken;
	private boolean isComfirmed;
	private boolean isPasswordChanged;
	private boolean isDeleted;

	public AppUserDto() {
	};

	public AppUserDto(AppUser appUser) {
		this.idAppUser = appUser.getIdAppUser();
		this.name = appUser.getName();
		this.lastName = appUser.getLastName();
		this.email = appUser.getEmail();
		this.pass = appUser.getPass();
		this.country = appUser.getCountry();
		this.city = appUser.getCity();
		this.address = appUser.getAddress();
		this.personalId = appUser.getPersonalId();
		this.registerToken = appUser.getRegisterToken();
		this.isComfirmed = appUser.isComfirmed();
		this.isPasswordChanged = appUser.isPasswordChanged();
		this.isDeleted = appUser.isDeleted();
	}

	public AppUserDto(int idAppUser, String name, String lastName, String email, String password, String country,
			String city, String address, String personalId, String registerToken, boolean isComfirmed,
			boolean isPasswordChanged, boolean isDeleted) {
		this.idAppUser = idAppUser;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
		this.country = country;
		this.city = city;
		this.address = address;
		this.personalId = personalId;
		this.registerToken = registerToken;
		this.isComfirmed = isComfirmed;
		this.isPasswordChanged = isPasswordChanged;
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getRegisterToken() {
		return registerToken;
	}

	public void setRegisterToken(String registerToken) {
		this.registerToken = registerToken;
	}

	public boolean isComfirmed() {
		return isComfirmed;
	}

	public void setComfirmed(boolean isComfirmed) {
		this.isComfirmed = isComfirmed;
	}

	public boolean isPasswordChanged() {
		return isPasswordChanged;
	}

	public void setPasswordChanged(boolean isPasswordChanged) {
		this.isPasswordChanged = isPasswordChanged;
	}

	public int getIdAppUser() {
		return idAppUser;
	}

	public void setIdAppUser(int idAppUser) {
		this.idAppUser = idAppUser;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
