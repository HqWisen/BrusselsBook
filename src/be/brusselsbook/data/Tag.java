package be.brusselsbook.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Tag {

	@XmlAttribute
	private String name;

	@XmlElement(name = "User")
	private List<Tagger> taggerList;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Tag: " + name + " by " + taggerList;
	}

}
