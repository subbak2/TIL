package com.scsa.business_logic;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.scsa.domain_object.Weather;

public class WeatherMain {
	Frame f;
	Button buttons[];
	Panel Body;
	List WeatherList;
	WeatherDAO_SAX dao = WeatherDAO_SAX.getInstance();
	
	public static void main(String[] args) {
		WeatherMain a = new WeatherMain();		
		a.createGUI();
		a.addEvent();
		a.showList();
	}	
	
	public void createGUI() {
		f = new Frame("실시간 낙성대 날씨정보");
		f.setSize(400,700);
		f.setVisible(true);
		buttons = new Button[1];
		buttons[0] = new Button ("실시간 정보 불러오기");
		f.add(buttons[0], BorderLayout.NORTH);
		Body = new Panel();
		WeatherList = new List();
		Body.add(WeatherList);
		Body.setLayout(new GridLayout(0,1));
		f.add(Body, BorderLayout.CENTER);		
	}
	
	public void addEvent() {
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		buttons[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				showList();				
			}			
		});
	}
	
	public void showList() {
		dao.connectXML();
		java.util.List<Weather> weather = dao.getWeatherList();
		WeatherList.removeAll();
		for (Weather w : weather) {
			WeatherList.add(w.toString());
		}
	}
}
