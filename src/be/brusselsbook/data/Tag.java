package be.brusselsbook.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Tag { 
	
	@XmlAttribute
	private String name;
	

	
	/*
	@XmlElementWrapper(name = "Users")
	@XmlElement(name = "User")
	private List<Tagger> taggerList;
	*/
	private  Tagger user ; //Warpper ??



	public String getName() {
		return name;
	}
	




}
