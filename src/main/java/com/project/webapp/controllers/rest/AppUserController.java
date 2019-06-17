package com.project.webapp.controllers.rest;

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

import com.project.webapp.dto.request.ChangePassDTO;
import com.project.webapp.dto.request.InviteDTO;
import com.project.webapp.dto.response.AppUserDto;
import com.project.webapp.repository.AppUserRepository;
import com.project.webapp.service.AppUserService;

@RestController
@RequestMapping("/appuser")
public class AppUserController {
	
	@Autowired
	AppUserService appUserService;
	
	@PostMapping("/create")
	public ResponseEntity<AppUserDto> createAppUser(@RequestBody AppUserDto appUserDto){
		AppUserDto response = appUserService.createAppUser(appUserDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<List<AppUserDto>> getAllAppUsers(){
		List<AppUserDto> response = appUserService.findAllAppUsers();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppUserDto> getAppUserById(@PathVariable int id){
		AppUserDto response = appUserService.getAppUserById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AppUserDto> editAppUser(@PathVariable int id, @RequestBody AppUserDto appUserDto){
		AppUserDto response = appUserService.editAppUser(id, appUserDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
		
	@DeleteMapping("/{id}")
	public ResponseEntity<AppUserDto> deleteAppUserById(@PathVariable int id){
		AppUserDto response = appUserService.deleteAppUserById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////   LOGING   /////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/invite") 
	public ResponseEntity<AppUserDto> inviteCompanyAdmin(@RequestBody InviteDTO invite){
		AppUserDto response = appUserService.inviteCompanyAdmin(invite);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/confirm/{token}")
	public ResponseEntity<String> comfirm(@PathVariable String token){
		appUserService.comfirmEmail(token);
		return new ResponseEntity<String>("Email successful verified", HttpStatus.OK);
	}
	
	@GetMapping("/getuserbytoken/{token}")
	public ResponseEntity<AppUserDto> getAppUserByToken(@PathVariable String token){
		AppUserDto response = appUserService.getAppUserByToken(token);
		return new ResponseEntity<AppUserDto>(response, HttpStatus.OK);
	}
	
	@PostMapping("/changetemporarypassword")
	public ResponseEntity<String> changeTemporaryPassword(@RequestBody ChangePassDTO changePassDTO){
		System.out.println(changePassDTO.getNewPassword() + " " + changePassDTO.getTemporaryPassword() + " " + changePassDTO.getEmail());
		appUserService.changeTemporaryPassword(changePassDTO);
		return new ResponseEntity<String>("Password successfully changed", HttpStatus.OK);
	}

	
}
