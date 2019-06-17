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

import com.project.webapp.dto.response.ProductDto;
import com.project.webapp.repository.ProductRepository;
import com.project.webapp.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping(value = "/create")
//	@PreAuthorize("hasAuthority('COMPANYADMIN')")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, Principal principal) {
		System.out.println("Entered ProductController " + productDto.getName());
		ProductDto response = productService.createProduct(productDto, principal.getName());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/createcopy")
	public ResponseEntity<ProductDto> createNewProductCOPYmethod(@RequestBody ProductDto productDto) {
		ProductDto response = productService.createNewProductCOPYmethod(productDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> response = productService.findAllProducts();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
		ProductDto response = productService.findProductById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> editProduct(@PathVariable int id, @RequestBody ProductDto productDto) {
		ProductDto response = productService.editProduct(id, productDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductDto> deleteProduct(@PathVariable int id) {
		ProductDto response = productService.deleteProduct(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
