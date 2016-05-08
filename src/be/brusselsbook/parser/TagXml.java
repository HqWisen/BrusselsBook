package be.brusselsbook.parser;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class TagXml {

	@XmlAttribute
	private String name;

	@XmlElement(name = "User")
	private List<Tagger> taggerList;

	public String getName() {
		return name;
	}

	public List<Tagger> getTaggerList() {
		return taggerList;
	}

	@Override
	public String toString() {
		return "Tag: " + name + " by " + taggerList;
	}

}
