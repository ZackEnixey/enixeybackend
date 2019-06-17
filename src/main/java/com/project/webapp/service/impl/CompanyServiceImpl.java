package com.project.webapp.service.impl;

import com.project.webapp.dto.response.CompanyDto;
import com.project.webapp.model.Company;
import com.project.webapp.repository.CompanyRepository;
import com.project.webapp.service.CompanyService;
import com.project.webapp.util.WebShopException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Override
	public CompanyDto createCompany(CompanyDto companyDto) {
		Company company = new Company(companyDto);
		companyRepository.saveAndFlush(company);
		return new CompanyDto(company);
	}

	@Override
	public List<CompanyDto> listAllCompanies() {
		List<Company> companies = companyRepository.findAll();
		List<CompanyDto> companiesDto = new ArrayList<CompanyDto>();
		for (Company company : companies) {
			companiesDto.add(new CompanyDto(company));
		}
		return companiesDto;
	}

	@Override
	public CompanyDto getCompanyById(int id) {
		Company company = companyRepository.getOne(id);
		return new CompanyDto(company);
	}

	@Override /// TRY SENDING ID IN THE BODY
	public CompanyDto editCompany(int id, CompanyDto companyDto) {
		Company company = companyRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no such ID"));
		if(companyDto.getName() != null) 		{ company.setName(companyDto.getName()); }
		if(companyDto.getCountry() != null)		{ company.setCountry(companyDto.getCountry()); }
		if(companyDto.getCity() != null) 		{ company.setCity(companyDto.getCity()); }
		if(companyDto.getAddress() != null) 	{ company.setAddress(companyDto.getAddress()); }
		if(companyDto.getBankAccount() != null) { company.setBankAccount(companyDto.getBankAccount()); }
		if(companyDto.isDeleted()) 				{ company.setDeleted(companyDto.isDeleted()); }
		
		company = companyRepository.saveAndFlush(company);
		return new CompanyDto(company);
	}

	@Override
	public CompanyDto deleteCompanyById(int id) {
		Company company = companyRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no such ID"));
		company.setDeleted(true);
		company = companyRepository.saveAndFlush(company);
		return new CompanyDto(company);
	}

}
