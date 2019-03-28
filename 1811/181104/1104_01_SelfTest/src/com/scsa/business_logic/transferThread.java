package com.scsa.business_logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

import com.scsa.domain_object.Product;

@SuppressWarnings("serial")
public class transferThread implements Runnable, Serializable{
	ProductManagerImpl dao = ProductManagerImpl.getInstance();
	Socket s = null;
	PrintWriter out = null;
	
	@Override
	public void run() {
		try {
			s = new Socket("127.0.0.1",7777);
			out = new PrintWriter(s.getOutputStream(),true);
			for (Product p : ProductManagerImpl.getInstance().getList()) {
				out.println(p.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( s!=null)
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
}
