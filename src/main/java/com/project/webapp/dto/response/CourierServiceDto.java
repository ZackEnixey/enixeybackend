package com.project.webapp.dto.response;

import com.project.webapp.model.CourierService;

public class CourierServiceDto {
	private int idCourierService;
	private String name;
	private String pictureUrl;
	private int price;
	private boolean isDeleted;

	public CourierServiceDto() {};

	public CourierServiceDto(CourierService courierService) {
		super();
		this.idCourierService = courierService.getIdCourierService();
		this.name = courierService.getName();
		this.pictureUrl = courierService.getPictureUrl();
		this.price = courierService.getPrice();
		this.isDeleted = courierService.isDeleted();
	}

	public CourierServiceDto(int idCourierService, String name, String priceUrl, int price, boolean isDeleted) {
		super();
		this.idCourierService = idCourierService;
		this.name = name;
		this.pictureUrl = priceUrl;
		this.price = price;
		this.isDeleted = isDeleted;
	}
	
	public CourierServiceDto( String name, String priceUrl, int price, boolean isDeleted) {
		super();
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

}
