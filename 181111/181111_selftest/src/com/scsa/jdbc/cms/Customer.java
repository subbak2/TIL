package com.scsa.jdbc.cms;

public class Customer {

	private int cnum;
	private String cname;
	private String caddress;
	
	public Customer() {
	}

	public Customer(int cnum, String cname, String caddress) {
		this.cnum = cnum;
		this.cname = cname;
		this.caddress = caddress;
	}

	@Override
	public String toString() {
		return "Customer [cnum=" + cnum + ", cname=" + cname + ", caddress=" + caddress + "]";
	}
	
	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
}
