package com.project.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.webapp.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{

}
