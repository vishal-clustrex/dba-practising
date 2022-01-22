package com.home.course1.model;

import java.math.BigDecimal;

public class Person {
	private String name;
	private String birthday;
	private String email;
	private BigDecimal revenue;
	private boolean isCustomer;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getRevenue() {
		return revenue;
	}
	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}
	public boolean getIsCustomer() {
		return isCustomer;
	}
	public void setIsCustomer(Boolean isCustomer) {
		this.isCustomer = isCustomer;
	}
}
