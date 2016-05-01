package be.brusselsbook.parser;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="Cafes")
public class Cafes {
	@XmlElement(name = "Cafe")
	List<CafeXml> cafeList;

	public List<CafeXml> getCafeList() {
		return cafeList;
	}

}
