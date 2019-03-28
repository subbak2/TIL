package com.sds.cleancode.restaurant;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingSchedulerTest {

	private static final int UNDER_CAPACITY = 1;
	private static final int CAPACITY_PER_HOUR = 3;
	private static final Customer CUSTOMER = mock(Customer.class);
	private static final Customer CUSTOMER_WITH_EMAIL = mock(Customer.class, Mockito.RETURNS_MOCKS);
	private static final DateTime ON_THE_HOUR = new DateTime(2019, 3, 26, 17, 0);
	private static final DateTime NOT_ON_THE_HOUR = new DateTime(2019, 3, 26, 17, 5);

	@InjectMocks
	@Spy
	private BookingScheduler bookingScheduler = new BookingScheduler(CAPACITY_PER_HOUR);
	@Spy
	private List<Schedule> schedules = new ArrayList<Schedule>();
	@Spy
	private SmsSender smsSender = new SmsSender();
	@Spy
	private MailSender mailSender = new MailSender();

	@Before
	public void setUp() {
		
	}

	@Test
	public void 정시가_아닌_경우_예외_발생() throws Exception {

		// arrange
		Schedule schedule = new Schedule(NOT_ON_THE_HOUR, UNDER_CAPACITY, CUSTOMER);

		// act
		try {
			bookingScheduler.addSchedule(schedule);
			fail();
		} catch (RuntimeException e) {
			assertThat(e.getMessage(), is("Booking should be on the hour."));
		}

		// assert

	}

	@Test
	public void 정시인_경우_스케줄_추가_성공() throws Exception {

		Schedule schedule = new Schedule(ON_THE_HOUR, UNDER_CAPACITY, CUSTOMER);

		bookingScheduler.addSchedule(schedule);

		assertThat(bookingScheduler.hasSchedule(schedule), is(true));

	}

	@Test
	public void 같은_시간대에_정원이_초과했을_경우_예외_발생() throws Exception {

		// arrange
		Schedule fullSchedule = new Schedule(ON_THE_HOUR, CAPACITY_PER_HOUR, CUSTOMER);
		schedules.add(fullSchedule);
		Schedule schedule = new Schedule(ON_THE_HOUR, UNDER_CAPACITY, CUSTOMER);

		// act
		try {
			bookingScheduler.addSchedule(schedule);
			fail();
		} catch (RuntimeException e) {
			assertThat(e.getMessage(), is("Number of people is over restaurant capacity per hour"));
		}

		// assert

	}

	@Test
	public void 시간대가_다르면_정원이_차있어도_스케줄_추가_성공() throws Exception {

		Schedule fullSchedule = new Schedule(ON_THE_HOUR, CAPACITY_PER_HOUR, CUSTOMER);
		schedules.add(fullSchedule);
		DateTime differntHour = new DateTime(2019, 3, 26, 16, 0);
		Schedule schedule = new Schedule(differntHour, UNDER_CAPACITY, CUSTOMER);

		bookingScheduler.addSchedule(schedule);

		assertThat(bookingScheduler.hasSchedule(schedule), is(true));

	}

	@Test
	public void 예약완료_시_SMS_발송() throws Exception {

		Schedule schedule = new Schedule(ON_THE_HOUR, UNDER_CAPACITY, CUSTOMER);

		bookingScheduler.addSchedule(schedule);

		verify(smsSender, times(1)).send(any(Schedule.class));

	}

	@Test
	public void 이메일이_없는_경우_이메일을_발송하지_않는다() throws Exception {

		Schedule schedule = new Schedule(ON_THE_HOUR, UNDER_CAPACITY, CUSTOMER);

		bookingScheduler.addSchedule(schedule);

		verify(mailSender, times(0)).sendMail(any(Schedule.class));

	}

	@Test
	public void 이메일이_있는_경우_이메일_발송() throws Exception {

		Schedule schedule = new Schedule(ON_THE_HOUR, UNDER_CAPACITY, CUSTOMER_WITH_EMAIL);

		bookingScheduler.addSchedule(schedule);

		verify(mailSender, times(1)).sendMail(any(Schedule.class));

	}

	@Test
	public void 현재_날짜가_일요일인_경우_예약_불가() throws Exception {

		Schedule schedule = new Schedule(ON_THE_HOUR, UNDER_CAPACITY, CUSTOMER_WITH_EMAIL);
		when(bookingScheduler.getNow()).thenReturn(new DateTime(2019, 3, 24, 17, 0));

		try {
			bookingScheduler.addSchedule(schedule);
			fail();
		} catch (RuntimeException e) {
			assertThat(e.getMessage(), is("Booking system is not available on sunday"));
		}


	}
	
}
