package com.project.webapp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.webapp.dto.response.CompanyDto;

@Entity
@NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_company")
	private int idCompany;

	private String name;

	private String country;

	private String city;

	private String address;

	@Column(name = "bank_account")
	private String bankAccount;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@OneToMany

	private List<AppUser> appUsers;

	@OneToMany
	@JsonBackReference
	private List<Product> products;

	public Company() {
	}

	public Company(CompanyDto companyDto) {
		this.name = companyDto.getName();
		this.country = companyDto.getCountry();
		this.city = companyDto.getCity();
		this.address = companyDto.getAddress();
		this.bankAccount = companyDto.getBankAccount();
		this.isDeleted = companyDto.isDeleted();
	}

	public Company(String name, String country, String city, String address, String bankAccount, boolean isDeleted) {
		this.name = name;
		this.country = country;
		this.city = city;
		this.address = address;
		this.bankAccount = bankAccount;
		this.isDeleted = isDeleted;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public List<AppUser> getAppUsers() {
		return appUsers;
	}

	public void setAppUsers(List<AppUser> appUsers) {
		this.appUsers = appUsers;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
