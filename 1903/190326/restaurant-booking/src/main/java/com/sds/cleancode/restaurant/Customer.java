package com.sds.cleancode.restaurant;

public class Customer {

	private String name;
	private String phoneNumber;
	private String email;

	public Customer(String name,String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public Customer(String name,String phoneNumber,String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
