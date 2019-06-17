package com.project.webapp.controllers.rest;

import com.project.webapp.dto.response.CompanyDto;
import com.project.webapp.repository.CompanyRepository;
import com.project.webapp.service.CompanyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@PostMapping("/create")
//  @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
		CompanyDto response = companyService.createCompany(companyDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("")
	public ResponseEntity<List<CompanyDto>> getAllCompanies() {
		List<CompanyDto> response = companyService.listAllCompanies();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable int id) {
		CompanyDto response = companyService.getCompanyById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CompanyDto> editCompany(@PathVariable int id, @RequestBody CompanyDto companyDto) {
		CompanyDto response = companyService.editCompany(id, companyDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
//  @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CompanyDto> deleteCompanyById(@PathVariable int id) {
		CompanyDto response = companyService.deleteCompanyById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
