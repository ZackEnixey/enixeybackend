package com.project.webapp.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.webapp.dto.response.RoleDto;
import com.project.webapp.repository.RoleRepository;
import com.project.webapp.service.RoleService;

@RestController 
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("")
	public ResponseEntity<List<RoleDto>> getAllRoles(){
		List<RoleDto> response = roleService.getAllRoles();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable int id){
    	RoleDto response = roleService.getRoleById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
//    @PutMapping("/{id}")
//    public ResponseEntity<RoleDto> editRole(@PathVariable int id, @RequestBody RoleDto roleDto){
//    	RoleDto response = roleService.editRole(id, roleDto);
//    	return new ResponseEntity<>(response, HttpStatus.OK );
//    }
//    
//    @PostMapping("/create")
//    public ResponseEntity<RoleDto> createNewRole(@RequestBody RoleDto roleDto){
//    	RoleDto response = roleService.createNewRole(roleDto);
//    	return new ResponseEntity<>(response, HttpStatus.OK );
//    }
//    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<RoleDto> deleteRoleById(@PathVariable int id){
//    	RoleDto response = roleService.deleteRoleById(id);
//    	return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
