package com.project.webapp.service;

import java.util.List;

import com.project.webapp.dto.response.CompanyDto;

public interface CompanyService {

	CompanyDto createCompany(CompanyDto companyDto);

	List<CompanyDto> listAllCompanies();

	CompanyDto getCompanyById(int id);

	CompanyDto editCompany(int id, CompanyDto companyDto);

	CompanyDto deleteCompanyById(int id);

}
