package be.brusselsbook.data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Informations {

	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "Address")
	private Address address;
	
	@XmlElement(name = "Tel")
	private String tel;
	
	@XmlElement(name = "Site")
	private Site site;

	
	@XmlElementWrapper(name = "Closed")
	@XmlElement(name = "On")
	private List<Day> closedDayList;

	
	
	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}

	public Site getSite() {
		return site;
	}
	
	public List<Day> getClosedDayList(){
		return closedDayList;
	}
	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < closedDayList.size();i++){
			if (closedDayList.get(i) == null){
				string += "heere";
			}
			else{
				
			
			string += closedDayList.get(i).getDayIndex();
			string += " ";
			//string += closedDayList.get(0).getHour();
		
			}
		}
		//string += name + ": ";
		//string += address + "; ";
		//string += tel;
		//string += " | " + site;
		return string;
	}
}
