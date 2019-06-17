package com.project.webapp.dto.request;

import java.util.List;

public class PurchaseRequestDTO {

	private int idCourierService;
	List<Integer> itemIds;
	List<Integer> itemCounts;

	public int getIdCourierService() {
		return idCourierService;
	}

	public void setIdCourierService(int idCourierService) {
		this.idCourierService = idCourierService;
	}

	public List<Integer> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<Integer> itemIds) {
		this.itemIds = itemIds;
	}

	public List<Integer> getItemCounts() {
		return itemCounts;
	}

	public void setItemCounts(List<Integer> itemCounts) {
		this.itemCounts = itemCounts;
	}

	@Override
	public String toString() {
		return "PurchaseRequestDTO [idCourierService=" + idCourierService + ", itemIds=" + itemIds + ", itemCounts="
				+ itemCounts + "]";
	}
	
	

}
