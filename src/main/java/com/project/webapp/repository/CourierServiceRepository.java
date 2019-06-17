package com.project.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.webapp.model.CourierService;

@Repository
public interface CourierServiceRepository extends JpaRepository<CourierService, Integer> {

}
