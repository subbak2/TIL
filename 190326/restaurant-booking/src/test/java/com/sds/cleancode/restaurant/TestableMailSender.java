package com.sds.cleancode.restaurant;

public class TestableMailSender extends MailSender {

	private int countSendMailMethodIsCalled;

	public int getCountSendMailMethodIsCalled() {
		return countSendMailMethodIsCalled;
	}

	@Override
	public void sendMail(Schedule schedule) {
		countSendMailMethodIsCalled++;
	}
	
}
