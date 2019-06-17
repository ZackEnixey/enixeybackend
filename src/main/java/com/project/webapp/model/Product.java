package com.project.webapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.project.webapp.dto.response.ProductDto;

@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private int idProduct;

	@ManyToOne
	private Company company;

	private String name;

	private String label;

	@Column(name = "product_type")
	private String productType;

	private Integer price;

	private String description;

	@Column(name = "picture_url")
	private String pictureUrl;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@OneToMany
	private List<Item> items;

	public Product() {
	}

	public Product(ProductDto productDto) {
		super();
		this.idProduct = productDto.getIdProduct();
		this.company = productDto.getCompany();
		this.name = productDto.getName();
		this.label = productDto.getLabel();
		this.productType = productDto.getProductType();
		this.price = Integer.parseInt(productDto.getPrice());
		this.description = productDto.getDescription();
		this.pictureUrl = productDto.getPictureUrl();
		this.isDeleted = productDto.getIsDeleted();
	}

	public Product(int idProduct, Company company, String name, String label, String productType, String price,
			String description, String pictureUrl, boolean isDeleted) {
		super();
		this.idProduct = idProduct;
		this.company = company;
		this.name = name;
		this.label = label;
		this.productType = productType;
		this.price = Integer.parseInt(price);
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
