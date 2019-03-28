package com.scsa.domain_object;

public class Weather {
	private int hour;
	private double temp;
	private String wfKor;
	private int reh;
	
	public Weather() {
	}

	@Override
	public String toString() {
		return "Weather [hour=" + hour + ", temp=" + temp + ", wfKor=" + wfKor + ", reh=" + reh + "]";
	}

	public int getHour() {
		return hour;
	}

	public double getTemp() {
		return temp;
	}

	public String getWfKor() {
		return wfKor;
	}

	public int getReh() {
		return reh;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public void setWfKor(String wfKor) {
		this.wfKor = wfKor;
	}

	public void setReh(int reh) {
		this.reh = reh;
	}
	
}
