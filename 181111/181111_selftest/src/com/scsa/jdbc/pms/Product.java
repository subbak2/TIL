package com.scsa.jdbc.pms;

public class Product {
	private int pnum;
	private String title;
	private int price;
	private String brand;
	
	public Product() {
	}

	public Product(int pnum, String title, int price) {
		this.pnum = pnum;
		this.title = title;
		this.price = price;
	}

	public Product(int pnum, String title, int price, String brand) {
		super();
		this.pnum = pnum;
		this.title = title;
		this.price = price;
		this.brand = brand;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [pnum=" + pnum + ", title=" + title + ", price=" + price + ", brand=" + brand + "]";
	}
	
	
}
