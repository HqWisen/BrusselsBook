package be.brusselsbook.parser;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="Cafes")
public class Cafes {
	@XmlElement(name = "Cafe")
	List<Cafe> cafeList;

	public List<Cafe> getCafeList() {
		return cafeList;
	}

}
