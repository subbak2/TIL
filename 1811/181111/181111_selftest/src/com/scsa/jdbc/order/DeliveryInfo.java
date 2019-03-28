package com.scsa.jdbc.order;

public class DeliveryInfo extends OrderInfo {
	private String cname;
	private String caddress;
	
	public DeliveryInfo(int onum, int cnum, int pnum, int quant,
			String cname, String caddress) {
		super(onum, cnum, pnum, quant);
		this.cname = cname;
		this.caddress = caddress;
	}

	@Override
	public String toString() {
		return super.toString() +
				"DeliveryInfo [cname=" + cname + ", caddress=" + caddress + "]";
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
