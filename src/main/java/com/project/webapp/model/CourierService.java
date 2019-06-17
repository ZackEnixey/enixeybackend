package com.project.webapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.project.webapp.dto.response.CourierServiceDto;

@Entity
@NamedQuery(name = "CourierService.findAll", query = "SELECT c FROM CourierService c")
public class CourierService {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_courier_service")
	private int idCourierService;

	private String name;

	@Column(name = "picture_url")
	private String pictureUrl;

	private int price;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@OneToMany
	private List<Purchase> purchases;

	public CourierService() {
	};

	public CourierService(CourierServiceDto courierServiceDto) {
		super();
		this.idCourierService = courierServiceDto.getIdCourierService();
		this.name = courierServiceDto.getName();
		this.pictureUrl = courierServiceDto.getPictureUrl();
		this.price = courierServiceDto.getPrice();
		this.isDeleted = courierServiceDto.isDeleted();
	}

	public CourierService(int idCourierService, String name, String priceUrl, int price, boolean isDeleted) {
		super();
		this.idCourierService = idCourierService;
		this.name = name;
		this.pictureUrl = priceUrl;
		this.price = price;
		this.isDeleted = isDeleted;
	}

	public int getIdCourierService() {
		return idCourierService;
	}

	public void setIdCourierService(int idCourierService) {
		this.idCourierService = idCourierService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String priceUrl) {
		this.pictureUrl = priceUrl;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}
