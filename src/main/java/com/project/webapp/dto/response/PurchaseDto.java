package com.project.webapp.dto.response;

import java.util.Date;

import com.project.webapp.model.AppUser;
import com.project.webapp.model.CourierService;
import com.project.webapp.model.Purchase;

public class PurchaseDto {
	private int idPurchase;
	private CourierServiceDto courierServiceDto;
	private AppUserDto appUserDto;
	private Date date;
	private boolean isStorned;
	private int totalPrice;
	private boolean isDeleted;

	public PurchaseDto() {
	}

	public PurchaseDto(Purchase purchase) {
		super();
		this.idPurchase = purchase.getIdPurchase();
		this.courierServiceDto = new CourierServiceDto(purchase.getCourierService());
		this.appUserDto = new AppUserDto(purchase.getAppUser());
		this.date = purchase.getDate();
		this.isStorned = purchase.isStorned();
		this.totalPrice = purchase.getTotalPrice();
		this.isDeleted = purchase.isDeleted();
	}

	public PurchaseDto(int idPurchase, CourierService courierService, AppUser appUser, Date date, boolean idStorned,
			int totalPrice, boolean isDeleted) {
		super();
		this.idPurchase = idPurchase;
//		this.courierService = courierService;
//		this.appUser = appUser;
		this.date = date;
		this.isStorned = idStorned;
		this.totalPrice = totalPrice;
		this.isDeleted = isDeleted;
	}

	public int getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(int idPurchase) {
		this.idPurchase = idPurchase;
	}

	public CourierServiceDto getCourierService() {
		return courierServiceDto;
	}

	public void setCourierService(CourierServiceDto courierService) {
		this.courierServiceDto = courierService;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isStorned() {
		return isStorned;
	}

	public void setStorned(boolean isStorned) {
		this.isStorned = isStorned;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public AppUserDto getAppUser() {
		return appUserDto;
	}

	public void setAppUser(AppUserDto appUser) {
		this.appUserDto = appUser;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
