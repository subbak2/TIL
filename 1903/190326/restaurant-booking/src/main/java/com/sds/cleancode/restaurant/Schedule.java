package com.sds.cleancode.restaurant;

import org.joda.time.DateTime;

public class Schedule {
	private DateTime dateTime;
	private int numberOfPeople;
	private Customer customer;

	public Schedule(DateTime dateTime, int numberOfPeople, Customer customer) {
		this.dateTime = dateTime;
		this.numberOfPeople = numberOfPeople;
		this.customer = customer;
	}

	public DateTime getDateTime() {
		return dateTime;
	}
	
	public int getNumberOfPeople() {
		return numberOfPeople;
	}
	
	public Customer getCustomer() {
		return customer;
	}
}
