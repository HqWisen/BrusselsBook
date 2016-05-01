package be.brusselsbook.parser;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Cafe {

	@XmlAttribute
	private String creationDate;
	@XmlAttribute
	private String nickname;
	
	@XmlElement(name = "Informations")
	private CafesInfos cafeInfos;
	
	@XmlElementWrapper(name = "Comments")
	@XmlElement(name = "Comment")
	private List<Comment> commentList;
	
	@XmlElementWrapper(name = "Tags")
	@XmlElement(name = "Tag")
	private List<Tag> tagList;

	public String getCreationDate() {
		return creationDate;
	}

	public String getNickname() {
		return nickname;
	}

	public CafesInfos getCafeInfos() {
		return cafeInfos;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	@Override
	public String toString() {
		String string = "Name: ";
		string += cafeInfos;
		return string;
	}



}
