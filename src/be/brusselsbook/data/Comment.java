package be.brusselsbook.data;

import javax.xml.bind.annotation.XmlAttribute;

public class Comment {

	@XmlAttribute
	private String nickname;
	@XmlAttribute
	private String date;
	@XmlAttribute
	private int score;

	public String getNickname() {
		return nickname;
	}

	public String getDate() {
		return date;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		String string = "";
		string += nickname + " scored " + score;
		return string;
	}

}
