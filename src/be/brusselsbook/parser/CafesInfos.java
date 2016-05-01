package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlElement;

public class CafesInfos extends Informations {

	@XmlElement(name = "Smoking")
	private String smoking; // null if none, empty string if has.

	@XmlElement(name = "Snack")
	private String snack;

	public String getSmoking() {
		return smoking;
	}

	public String getSnack() {
		return snack;
	}

	public boolean canSmoke() {
		return getSmoking() != null;
	}

	public boolean canSnack() {
		return getSnack() != null;
	}

	@Override
	public String toString() {
		String string = "Nam: ";
		string += super.getName();
		/*
		 * string += "; "; string+= canSnack();
		 */
		return string;
	}

}
