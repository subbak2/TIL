package com.sds.cleancode.restaurant;

public class TestableSmsSender extends SmsSender {

	private boolean sendMethodIsCalled;

	public boolean isSendMethodCalled() {
		return sendMethodIsCalled;
	}

	@Override
	public void send(Schedule schedule) {
		sendMethodIsCalled = true;
	}

}
