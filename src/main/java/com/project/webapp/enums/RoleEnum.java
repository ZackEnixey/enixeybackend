package com.project.webapp.enums;

public enum RoleEnum {
	ADMIN(1),COMPANYADMIN(2),USER(3);
	
	private int value;
	
	private RoleEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
