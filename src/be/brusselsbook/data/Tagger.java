package be.brusselsbook.data;

import javax.xml.bind.annotation.XmlAttribute;

public class Tagger {
	@XmlAttribute
	private String nickname;

	public String getNickname() {
		return nickname;
	}

}
