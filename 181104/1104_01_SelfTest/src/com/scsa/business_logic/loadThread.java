package com.scsa.business_logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.scsa.domain_object.Product;


@SuppressWarnings("serial")
public class loadThread implements Runnable, Serializable{
	ProductManagerImpl dao = ProductManagerImpl.getInstance();
	
	@SuppressWarnings("null")
	@Override
	public void run() {
		FileInputStream fin = null;
		ObjectInputStream oin = null;
		
		try {
			fin = new FileInputStream("Product.dat");
			oin = new ObjectInputStream(fin);
			dao.list.clear();
			int count = oin.readInt();
			
			while (--count>=0) {
				Product p;
				try {
					p = (Product) oin.readObject();
					if (p==null) break;
					dao.list.add(p);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
