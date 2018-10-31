package com.scsa.domain_object;

@SuppressWarnings("serial")
public class Magazine extends Book{
	private int year, month;

	public Magazine() {
	}

	public Magazine(String i, String t, String a, String p, String d, String pr, String q, String y, String m) {
		super(i,t,a,p,d, pr,q);
		year = Integer.parseInt(y);
		month = Integer.parseInt(m);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return super.toString() + "\t|" + year + "." + month;
	}
}
