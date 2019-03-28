package com.sds.cleancode.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public class BookingScheduler {
	private int capacityPerHour;	
	private List<Schedule> schedules;	
	private SmsSender smsSender;
	private MailSender mailSender;

	public BookingScheduler(int capacityPerHour) {
		this.schedules = new ArrayList<Schedule>();
		this.capacityPerHour = capacityPerHour;
		this.smsSender = new SmsSender();
		this.mailSender = new MailSender();
	}
	
	public void addSchedule(Schedule schedule) {
		
		// throw an exception when booking time is on the hour.
		if(schedule.getDateTime().getMinuteOfHour() != 0 ){
			throw new RuntimeException("Booking should be on the hour.");
		}
		
		// throw an exception when capacity per hour is over
		int numberOfPeople = schedule.getNumberOfPeople();
		for ( Schedule bookedSchedule : schedules ) {
			if ( bookedSchedule.getDateTime().isEqual(schedule.getDateTime()) ) {
				numberOfPeople += bookedSchedule.getNumberOfPeople();
			}
		}
		if (numberOfPeople > capacityPerHour){
			throw new RuntimeException("Number of people is over restaurant capacity per hour");
		}
			
		
		// throw an exception on sunday
		DateTime now = getNow();
		if(now.getDayOfWeek() == DateTimeConstants.SUNDAY){
			throw new RuntimeException("Booking system is not available on sunday");
		}
		
		schedules.add(schedule);
		
		// send SMS to customer
		smsSender.send(schedule);
		// send E-mail to customer when e-mail is valid
		if(schedule.getCustomer().getEmail() != null){
			mailSender.sendMail(schedule);
		}
	}

	protected DateTime getNow() {
		return new DateTime();
	}
	
	public boolean hasSchedule(Schedule schedule) {
		return schedules.contains(schedule);
	}
	
}
