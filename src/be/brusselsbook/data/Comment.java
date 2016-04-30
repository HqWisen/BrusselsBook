package be.brusselsbook.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Comment {

	@XmlAttribute
	private String nickname;
	@XmlAttribute
	private String date;
	@XmlAttribute
	private int score;
	@XmlValue
	private String content;

	public String getNickname() {
		return nickname;
	}

	public String getDate() {
		return date;
	}

	public int getScore() {
		return score;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		String string = "";
		//string += nickname + " scored " + score;
		string += content;
		return string;
	}

}
