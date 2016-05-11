package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlAttribute;

public class Site {
	
	@XmlAttribute
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link){
		this.link = link;
	}
	
	@Override
	public String toString() {
		return link;
	}
	
}
