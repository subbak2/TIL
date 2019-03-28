package com.scsa.jdbc.order;

public class OrderInfo {
	private int onum;
	private int cnum;
	private int pnum;
	private int quant;
	
	public OrderInfo() {
	}

	public OrderInfo(int onum, int cnum, int pnum, int quant) {
		this.onum = onum;
		this.cnum = cnum;
		this.pnum = pnum;
		this.quant = quant;
	}

	public int getOnum() {
		return onum;
	}

	public void setOnum(int onum) {
		this.onum = onum;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	@Override
	public String toString() {
		return "OrderInfo [onum=" + onum + ", cnum=" + cnum + ", pnum=" + pnum + ", quant=" + quant + "]";
	}
	
	
}
