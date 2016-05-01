package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlAttribute;

public class ClosedDay {
	
	@XmlAttribute
	private int dayIndex;
	@XmlAttribute 
	private String hour; // 'am' or 'pm'

	public int getDayIndex() {
		return dayIndex;
	}

	public String getHour() {
		return hour;
	}

	@Override
	public String toString() {
		return "Closed on " + dayIndex + ":" + hour;
	}
	
}
