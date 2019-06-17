package com.project.webapp.dto.response;

import com.project.webapp.model.Item;

public class ItemDto {

	private int idItem;
	private int quantity;
	private boolean isDeleted;
	
	public ItemDto(Item item) {
		this.idItem = item.getIdItem();
		this.quantity = item.getQuantity();
		this.isDeleted = item.isDeleted();
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public int getQuantity() {
		return quantity;
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


	
}
