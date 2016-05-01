package be.brusselsbook.parser;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class CafeXml {

	@XmlAttribute
	private String creationDate;
	@XmlAttribute
	private String nickname;
	
	@XmlElement(name = "Informations")
	private CafeInfos cafeInfos;
	
	@XmlElementWrapper(name = "Comments")
	@XmlElement(name = "Comment")
	private List<CommentXml> commentList;
	
	@XmlElementWrapper(name = "Tags")
	@XmlElement(name = "Tag")
	private List<TagXml> tagList;

	public String getCreationDate() {
		return creationDate;
	}

	public String getNickname() {
		return nickname;
	}

	public CafeInfos getCafeInfos() {
		return cafeInfos;
	}

	public List<CommentXml> getCommentList() {
		return commentList;
	}

	public List<TagXml> getTagList() {
		return tagList;
	}

	@Override
	public String toString() {
		String string = "Name: ";
		string += cafeInfos;
		return string;
	}



}
