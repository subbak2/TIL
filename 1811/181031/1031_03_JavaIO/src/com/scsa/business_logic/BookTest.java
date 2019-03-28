package com.scsa.business_logic;

import com.scsa.controller.EventController;

public class BookTest {
	public static void main(String[] args) {
		EventController a = new EventController();
		a.createUI();
		a.addEvent();	
	}
}
