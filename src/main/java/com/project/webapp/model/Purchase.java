package com.project.webapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.project.webapp.dto.response.PurchaseDto;

@Entity
@NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p")
public class Purchase {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_purchase")
	private int idPurchase;

	@ManyToOne
	private CourierService courierService;

	@ManyToOne
	private AppUser appUser;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "is_storned")
	private boolean isStorned;

	@Column(name = "total_price")
	private int totalPrice;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@OneToMany
	private List<Item> items;

	public Purchase() {
	}

	public Purchase(PurchaseDto purchaseDto) {
		super();
		this.idPurchase = purchaseDto.getIdPurchase();
//		this.courierService = purchaseDto.getCourierService();
//		this.appUser = purchaseDto.getAppUser();
		this.date = purchaseDto.getDate();
		this.isStorned = purchaseDto.isStorned();
		this.totalPrice = purchaseDto.getTotalPrice();
		this.isDeleted = purchaseDto.isDeleted();
	}

	public Purchase(int idPurchase, CourierService courierService, AppUser appUser, Date date, boolean idStorned,
			int totalPrice, boolean isDeleted) {
		super();
		this.idPurchase = idPurchase;
		this.courierService = courierService;
		this.appUser = appUser;
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

	public CourierService getCourierService() {
		return courierService;
	}

	public void setCourierService(CourierService courierService) {
		this.courierService = courierService;
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

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
