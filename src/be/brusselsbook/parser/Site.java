package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlAttribute;

public class Site {
	
	@XmlAttribute
	private String link;

	public String getLink() {
		return link;
	}

	@Override
	public String toString() {
		return link;
	}
	
}
