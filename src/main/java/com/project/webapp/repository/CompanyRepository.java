package com.project.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.webapp.model.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}