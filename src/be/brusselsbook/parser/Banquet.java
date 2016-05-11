package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlAttribute;

public class Banquet {

	@XmlAttribute
	private int capacity;
	
	public int getCapacity(){
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return Integer.toString(capacity);
	}
	
}
