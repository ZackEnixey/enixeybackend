package com.project.webapp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.webapp.dto.response.AppUserDto;

@Entity
@NamedQuery(name = "AppUser.findAll", query = "SELECT a FROM AppUser a")
public class AppUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_app_user")
	private int idAppUser;

	private String name;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	private String pass;

	private String country;

	private String city;

	private String address;

	@Column(name = "personal_id")
	private String personalId;

	@Column(name = "register_token")
	private String registerToken;

	@Column(name = "is_comfirmed")
	private boolean isComfirmed;

	@Column(name = "is_password_changed")
	private boolean isPasswordChanged;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	@JsonManagedReference
	private Company company;

	@ManyToMany
	@JoinTable(name = "app_user_has_role", joinColumns = {
			@JoinColumn(name = "app_user_id_app_user") }, inverseJoinColumns = { @JoinColumn(name = "role_id_role") })
	private List<Role> roles;

	@OneToMany(mappedBy = "appUser")
	private List<Purchase> purchase;

	public AppUser() {
	};

	public AppUser(AppUserDto appUserDto) {
		super();
		this.idAppUser = appUserDto.getIdAppUser();
		this.name = appUserDto.getName();
		this.lastName = appUserDto.getLastName();
		this.email = appUserDto.getEmail();
		this.pass = appUserDto.getPass();
		this.country = appUserDto.getCountry();
		this.city = appUserDto.getCity();
		this.address = appUserDto.getAddress();
		this.personalId = appUserDto.getPersonalId();
		this.registerToken = appUserDto.getRegisterToken();
		this.isComfirmed = appUserDto.isComfirmed();
		this.isPasswordChanged = appUserDto.isPasswordChanged();
		this.isDeleted = appUserDto.isDeleted();
	}

	public AppUser(int idAppUser, String name, String lastName, String email, String pass, String country, String city,
			String address, String personalId, String registerToken, boolean isComfirmed, boolean isPasswordChanged,
			boolean isDeleted) {
		super();
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

	public int getIdAppUser() {
		return idAppUser;
	}

	public void setIdAppUser(int idAppUser) {
		this.idAppUser = idAppUser;
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

	public void setPass(String password) {
		this.pass = password;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Purchase> getPurchase() {
		return purchase;
	}

	public void setPurchase(List<Purchase> purchase) {
		this.purchase = purchase;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
