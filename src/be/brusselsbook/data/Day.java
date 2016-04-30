package be.brusselsbook.data;

import javax.xml.bind.annotation.XmlAttribute;

public class Day {
	private Day(){		
	}

	@XmlAttribute
	private int day;
	
	@XmlAttribute 
	private String hour;

	public int getDayIndex() {
		return day;
	}

	public String getHour() {
		return hour;
	}

}
