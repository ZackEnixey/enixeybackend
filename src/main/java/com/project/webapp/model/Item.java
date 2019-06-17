package com.project.webapp.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
public class Item implements Serializable {
	public int getQuantity() {
		return quantity;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item")
	private int idItem;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	@ManyToOne
	private Product product;

	@ManyToOne
	private Purchase purchase;

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Item that = (Item) o;
		return idItem == that.idItem && Objects.equals(quantity, that.quantity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idItem, quantity);
	}
}
