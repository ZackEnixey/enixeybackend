package com.project.webapp.controllers.rest;

import java.security.Principal;
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

import com.project.webapp.dto.request.PurchaseRequestDTO;
import com.project.webapp.dto.response.PurchaseDto;
import com.project.webapp.dto.response.PurchaseDto;
import com.project.webapp.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;

	@PostMapping("/create")
	public ResponseEntity<PurchaseDto> createNewProduct(@RequestBody PurchaseDto purchaseDto) {
		PurchaseDto response = purchaseService.createNewProduct(purchaseDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<PurchaseDto>> getAllPurchases() {
		List<PurchaseDto> response = purchaseService.getAllPurchases();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PurchaseDto> getProductById(@PathVariable int id) {
		PurchaseDto response = purchaseService.getProductById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PurchaseDto> editProduct(@PathVariable int id, @RequestBody PurchaseDto purchaseDto) {
		PurchaseDto response = purchaseService.editProduct(id, purchaseDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PurchaseDto> deleteProduct(@PathVariable int id) {
		PurchaseDto response = purchaseService.deleteProduct(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/buy")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<PurchaseDto> buy(@RequestBody PurchaseRequestDTO purchaseRequestDTO, Principal principal) {
		PurchaseDto response = purchaseService.buy(purchaseRequestDTO, principal.getName());
		return new ResponseEntity<PurchaseDto>(response, HttpStatus.OK);
	}

}