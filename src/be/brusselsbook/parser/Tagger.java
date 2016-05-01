package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlAttribute;

public class Tagger {

	@XmlAttribute
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	@Override
	public String toString() {
		return nickname;
	}
	
}
