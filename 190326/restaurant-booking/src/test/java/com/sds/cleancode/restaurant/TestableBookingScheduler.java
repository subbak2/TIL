package com.sds.cleancode.restaurant;

import org.joda.time.DateTime;

public class TestableBookingScheduler extends BookingScheduler {

	public TestableBookingScheduler(int capacityPerHour) {
		super(capacityPerHour);
	}

	@Override
	protected DateTime getNow() {
		return new DateTime(2019, 3, 24, 17, 0);
	}

}
