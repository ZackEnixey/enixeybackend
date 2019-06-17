package com.project.webapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.webapp.dto.response.ProductDto;
import com.project.webapp.model.AppUser;
import com.project.webapp.model.Company;
import com.project.webapp.model.Product;
import com.project.webapp.repository.AppUserRepository;
import com.project.webapp.repository.CompanyRepository;
import com.project.webapp.repository.ProductRepository;
import com.project.webapp.service.ProductService;
import com.project.webapp.util.WebShopException;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<ProductDto> findAllProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> productsDto = new ArrayList<ProductDto>();
		for(Product product: products) {
			productsDto.add(new ProductDto(product));
		}
		return productsDto;
	}

	@Override
	public ProductDto findProductById(int id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no product with id: " + id));
		return new ProductDto(product);
	}

	@Override
	public ProductDto editProduct(int id, ProductDto productDto) {
		Product product = productRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no product with id: " + id));
		product = productRepository.saveAndFlush(product);
		return new ProductDto(product);
	}

	@Override
	public ProductDto createProduct(ProductDto productDto, String email) {
		System.out.println("I entered Product ServiceImpl");
		AppUser appUser = appUserRepository.findByEmail(email);
		Company company = companyRepository.findById(appUser.getCompany().getIdCompany()).orElse(null);
		
		Product product = new Product(productDto);
		product.setCompany(company);
		product = productRepository.saveAndFlush(product);
		return new ProductDto(product);
	}

	@Override
	public ProductDto deleteProduct(int id ) {
		Product product = productRepository.findById(id).orElseThrow(() -> new WebShopException(HttpStatus.BAD_REQUEST, "there is no product with id: " + id));
		product.setDeleted(false);
		product = productRepository.saveAndFlush(product);//istoo
		return new ProductDto(product);
	}

	@Override
	public ProductDto createNewProductCOPYmethod(ProductDto productDto) {
		Product product = new Product(productDto);
		//product.setCompany(companyRepository.findById(productDto.getCompanyId()).orElse(null) );
		product = productRepository.saveAndFlush(product);
		return new ProductDto(product);
	}
	
	

}
