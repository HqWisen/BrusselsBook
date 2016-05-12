package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlAttribute;

public class Site {
	
	@XmlAttribute
	private String link;

	public String getLink() {
		return link;
	}

	public void setSLink(String link){
		this.link = link;
	}
	
	@Override
	public String toString() {
		return link;
	}
	
}
