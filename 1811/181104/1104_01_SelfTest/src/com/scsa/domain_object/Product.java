package com.scsa.domain_object;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Product implements Serializable{	
	//��ǰ ���� ���� Ŭ����
	private String pid, pname;
	private int price;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}