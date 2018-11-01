package com.scsa.business_logic;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.scsa.domain_object.Weather;

public class WeatherDAO_SAX {
	List<Weather> list = new ArrayList<Weather>();
	private static WeatherDAO_SAX instance = new WeatherDAO_SAX();
	SAXParserFactory factory = SAXParserFactory.newInstance();

	private WeatherDAO_SAX() {
	}

	public static WeatherDAO_SAX getInstance() {
		return instance;
	}

	public List<Weather> getWeatherList() {
		return list;
	}

	public void connectXML() {
		// http://www.weather.go.kr/weather/lifenindustry/sevice_rss.jsp
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162058500", new SAXHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class SAXHandler extends DefaultHandler {
		StringBuilder builder = new StringBuilder();
		Weather n = null;

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			if (qName.equals("data")) {
				n = new Weather();
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (n != null) {
				if (qName.equals("hour")) {
					n.setHour(Integer.parseInt(builder.toString().trim()));
				} else if (qName.equals("temp")) {
					n.setTemp(Double.parseDouble(builder.toString().trim()));
				} else if (qName.equals("wfKor")) {
					n.setWfKor(builder.toString().trim());
				} else if (qName.equals("reh")) {
					n.setReh(Integer.parseInt(builder.toString().trim()));
				} else if (qName.equals("data")) {
					list.add(n);
					n = null;
				}
			}
			builder.setLength(0);
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			builder.append(String.valueOf(ch, start, length));
		}
	}
}
