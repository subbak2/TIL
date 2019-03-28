package com.scsa.business_logic;

import java.util.List;

import com.scsa.domain_object.Book;
import com.scsa.domain_object.Magazine;
import com.scsa.exception.ExistISBNException;

public interface ILibrary {
	void add(Book b) throws ExistISBNException;
	List<Book> BookList();
	List<Magazine> MagazineList();
	Book search(String isbn);
	void save(String filename);
	void load(String filename);
}
