package be.brusselsbook.data;

import javax.xml.bind.annotation.XmlAttribute;

public class Banquet {

	@XmlAttribute
	private int capacity;
	
	public int getCapacity(){
		return capacity;
	}
	
	@Override
	public String toString() {
		return Integer.toString(capacity);
	}
	
}
