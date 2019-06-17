package com.project.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.webapp.model.AppUser;


@Repository
public interface AppUserRepository  extends JpaRepository<AppUser, Integer> {

	AppUser findByEmail(String email);
	
	AppUser findByRegisterToken(String token);
}
