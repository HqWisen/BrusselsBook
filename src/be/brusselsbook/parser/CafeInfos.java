package be.brusselsbook.parser;

import javax.xml.bind.annotation.XmlElement;

public class CafeInfos extends EstablishmentInfos {

	@XmlElement(name = "Smoking")
	private String smoking; // null if none, empty string if has.

	@XmlElement(name = "Snack")
	private String snack;

	private String getSmoking() {
		return smoking;
	}

	private String getSnack() {
		return snack;
	}

	public Boolean canSmoke() {
		return getSmoking() != null;
	}

	public Boolean canSnack() {
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
