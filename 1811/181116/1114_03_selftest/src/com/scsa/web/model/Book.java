package com.scsa.web.model;

public class Book {
	private String bookname, isbn, category, country, date, company, author;
	private double price;
	private String description;

	public Book() {
	}

	public Book(String bookname, String isbn, String category, String country, String date, String company,
			String author, double price, String description) {
		this.bookname = bookname;
		this.isbn = isbn;
		this.category = category;
		this.country = country;
		this.date = date;
		this.company = company;
		this.author = author;
		this.price = price;
		this.description = description;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [bookname=" + bookname + ", isbn=" + isbn + ", category=" + category + ", country=" + country
				+ ", date=" + date + ", company=" + company + ", author=" + author + ", price=" + price
				+ ", description=" + description + "]";
	}

}
