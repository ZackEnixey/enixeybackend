package com.project.webapp.service;

import java.util.List;

import com.project.webapp.dto.response.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductDto productDto, String email);

	ProductDto createNewProductCOPYmethod(ProductDto productDto);

	List<ProductDto> findAllProducts();

	ProductDto findProductById(int id);

	ProductDto editProduct(int id, ProductDto productDto);

	ProductDto deleteProduct(int id);

}
