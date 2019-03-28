package com.scsa.business_logic;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.scsa.domain_object.Weather;

public class WeatherDAO_DOM {
	List<Weather> list = new ArrayList<Weather>();
	private static WeatherDAO_DOM instance = new WeatherDAO_DOM();
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	private WeatherDAO_DOM() {
	}

	public static WeatherDAO_DOM getInstance() {
		return instance;
	}

	public List<Weather> getWeatherList() {
		return list;
	}

	public void connectXML() {
		try {
			DocumentBuilder parser = factory.newDocumentBuilder();
			Document document = parser.parse("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162058500");
			NodeList weatherList = document.getElementsByTagName("data");

			for (int i = 0; i < weatherList.getLength(); i++) {
				Element n = (Element) weatherList.item(i);
				Weather ns = new Weather();
				n.getElementsByTagName("hour").item(0).getFirstChild().getNodeValue();
				ns.setHour(Integer.parseInt(n.getElementsByTagName("hour").item(0).getFirstChild().getNodeValue()));
				ns.setTemp(Double.parseDouble(n.getElementsByTagName("temp").item(0).getFirstChild().getNodeValue()));
				ns.setWfKor(n.getElementsByTagName("wfKor").item(0).getFirstChild().getNodeValue());
				ns.setReh(Integer.parseInt(n.getElementsByTagName("reh").item(0).getFirstChild().getNodeValue()));
				list.add(ns);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
