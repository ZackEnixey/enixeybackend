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

import com.project.webapp.dto.response.CompanyDto;
import com.project.webapp.dto.response.CourierServiceDto;
import com.project.webapp.model.CourierService;
import com.project.webapp.service.CourierServiceService;

@RestController
@RequestMapping("/courierservice")
public class CourierServiceController {
	
	@Autowired
	CourierServiceService courierServiceService;
	
	@PostMapping("/create")
    public ResponseEntity<CourierServiceDto> createCourierService(@RequestBody CourierServiceDto courierServiceDto){
		CourierServiceDto response = courierServiceService.createCourierService(courierServiceDto);
    	return new ResponseEntity<>(response, HttpStatus.CREATED );
    }
	
	@GetMapping("")
	public ResponseEntity<List<CourierServiceDto>> getAllCourierServices(){
		List<CourierServiceDto> response = courierServiceService.getAllCourierServices();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourierServiceDto> getCountryById(@PathVariable int id){
		CourierServiceDto response = courierServiceService.getCountryById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CourierServiceDto> editCourierService(@PathVariable int id, @RequestBody CourierServiceDto courierServiceDto){
		CourierServiceDto response = courierServiceService.editCourierService(id, courierServiceDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CourierServiceDto> deleteCountryById(@PathVariable int id ){
		CourierServiceDto response = courierServiceService.deleteCountryById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
