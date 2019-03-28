package com.scsa.business_logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.scsa.domain_object.Product;

@SuppressWarnings("serial")
public class saveThread implements Runnable, Serializable{
	ProductManagerImpl dao = ProductManagerImpl.getInstance();
	
	@SuppressWarnings("null")
	@Override
	public void run() {
		FileOutputStream fout = null;
		ObjectOutputStream oout = null;
		
		try {
			fout = new FileOutputStream(new File("Product.dat"));
			oout = new ObjectOutputStream(fout);
			int size = dao.getList().size();
			
			oout.flush();
			oout.writeInt(size);
			oout.flush();
			for (Product p : dao.getList()) {
				oout.writeObject(p);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oout!=null) {
				try {
					oout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
