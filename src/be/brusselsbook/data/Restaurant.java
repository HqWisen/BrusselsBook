package be.brusselsbook.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Restaurant {
	
	@XmlAttribute
	private String creationDate;
	
	@XmlAttribute
	private String nickname;
	
	@XmlElement(name = "Informations")
	private Informations informations;
	
	@XmlElementWrapper(name = "Comments")
	@XmlElement(name = "Comment")
	private List<Comment> commentList;
	
	@XmlElementWrapper(name = "Tags")
	@XmlElement (name = "Tag")
	private List<Tag> tagList;
	
	
	
	private Restaurant(){		
	}

	public String getCreationDate() {
		return creationDate;
	}

	public String getNickname() {
		return nickname;
	}

	public Informations getInformations(){
		return informations;
	}
	
	public List<Comment> getCommentList(){
		return commentList;
	}
	
}
