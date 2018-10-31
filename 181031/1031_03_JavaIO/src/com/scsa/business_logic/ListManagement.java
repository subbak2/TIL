package com.scsa.business_logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.scsa.domain_object.Book;
import com.scsa.domain_object.Magazine;
import com.scsa.exception.ExistISBNException;

public class ListManagement implements ILibrary {
	private static ListManagement instance = new ListManagement();
	public static int flag;

	private ListManagement() {
	}

	public static ListManagement getInstance() {
		return instance;
	}

	static List<Book> books = new ArrayList<Book>();
	static List<Magazine> magazines = new ArrayList<Magazine>();

	@Override
	public void add(Book b) throws ExistISBNException {
		if (b instanceof Magazine) {
			try {
				if (search(b.getIsbn()) != null) {
					throw new ExistISBNException(b.getIsbn());
				}
			} catch (NullPointerException e) {
				System.out.println("Null입니다 ");
			}
			magazines.add((Magazine) b);
		} else {
			try {
				if (search(b.getIsbn()) != null) {
					throw new ExistISBNException(b.getIsbn());
				}
			} catch (NullPointerException e) {
				System.out.println("Null입니다 ");
			}
			books.add(b);

		}
	}

	@Override
	public List<Book> BookList() {
		return books;
	}

	public List<Magazine> MagazineList() {
		return magazines;
	}

	@Override
	public Book search(String isbn) {
		for (Book b : books) {
			if (b.getIsbn().equals(isbn))
				return b;
		}
		for (Book b : magazines) {
			if (b.getIsbn().equals(isbn))
				return b;
		}
		return null;
	}

	@Override
	public void save(String filename) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(filename));

			if (flag == 1) {
				out.writeInt(magazines.size());
				for (Book b : magazines) {
					if (b != null)
						out.writeObject(b);
				}
			} else {
				out.writeInt(books.size());
				for (Book b : books) {
					if (b != null)
						out.writeObject(b);
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void load(String filename) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(filename));
			int count = in.readInt();
			if (flag == 1) {
				magazines.clear();
				while (--count >= 0) {
					Book b = null;
					try {
						b = (Magazine) in.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					magazines.add((Magazine) b);
				}
			} else {
				books.clear();	
				while (--count >= 0) {
					Book b = null;
					try {
						b = (Book) in.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					books.add(b);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
