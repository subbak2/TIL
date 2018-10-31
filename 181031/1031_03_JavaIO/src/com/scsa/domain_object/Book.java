package com.scsa.domain_object;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable{
	private String isbn, title, author, publisher;
	private String desc;
	private int price, quantity;

	public Book() {	
	}

	public Book(String i, String t, String a, String p, String d, String pr, String q) {
		isbn = i;
		title = t;
		author = a;
		publisher = p;
		desc = d;
		price = Integer.parseInt(pr);
		quantity = Integer.parseInt(q);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return isbn + "\t|" + title + "\t|" + author + "\t|" + publisher + "\t|" + price + "\t|" + desc;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
