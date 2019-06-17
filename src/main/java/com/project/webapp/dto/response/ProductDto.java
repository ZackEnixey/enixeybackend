package com.project.webapp.dto.response;

import com.project.webapp.model.Company;
import com.project.webapp.model.Product;

public class ProductDto {
	private int idProduct;
	private Company company;

	private String name;
	private String label;
	private String productType;
	private String price;
	private String description;
	private String pictureUrl;
	private boolean isDeleted;

	public ProductDto() {
	};

	public ProductDto(Product product) {
		super();
		this.idProduct = product.getIdProduct();
		this.company = product.getCompany();
		this.name = product.getName();
		this.label = product.getLabel();
		this.productType = product.getProductType();
		this.price = Integer.toString(product.getPrice());
		this.description = product.getDescription();
		this.pictureUrl = product.getPictureUrl();
		this.isDeleted = product.getIsDeleted();
	}

	public ProductDto(int idProduct, Company company, String name, String label, String productType, int price,
			String description, String pictureUrl, boolean isDeleted) {
		super();
		this.idProduct = idProduct;
		this.company = company;
		this.name = name;
		this.label = label;
		this.productType = productType;
		this.price = Integer.toString(price);
		this.description = description;
		this.pictureUrl = pictureUrl;
		this.isDeleted = isDeleted;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
