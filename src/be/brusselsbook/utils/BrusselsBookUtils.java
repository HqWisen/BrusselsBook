package be.brusselsbook.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public final class BrusselsBookUtils {

	public static ArrayList<String> createListFrom(String... array) {
		return new ArrayList<>(Arrays.asList(array));
	}

	public static String[] createArrayFrom(String... array) {
		String[] strings = new String[array.length];
		System.arraycopy(array, 0, strings, 0, array.length);
		return strings;
	}

	public static String marshal(final Object object) {
		try {
			final JAXBContext context = JAXBContext.newInstance(object.getClass());
			final Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			final StringWriter out = new StringWriter();
			marshaller.marshal(object, out);
			return out.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T unmarshal(final String xml, final Class<T> clazz) {
		try {
			final JAXBContext context = JAXBContext.newInstance(clazz);
			final Unmarshaller unmarshaller = context.createUnmarshaller();
			return clazz.cast(unmarshaller.unmarshal(new StringReader(xml)));
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static URL getResourceUrl(String resourcename){
		return BrusselsBookUtils.class.getClassLoader().getResource(resourcename);
	}
	
	public static String readFileFromResource(String resourcename) throws IOException{
		String path = getResourceUrl(resourcename).getPath();
		return readFile(path);
	}
	
	public static String readFile(String filename) throws IOException{
		return readFile(new File(filename));
	}
	
	public static String readFile(File file) throws IOException {
		Path path = Paths.get(file.getPath());
		List<String> codeLines = null;
		codeLines = Files.readAllLines(path);
		return getCodeAsString(codeLines);
	}
	
	public static String getCodeAsString(List<String> codeLines) {
		String code = "";
		for (String line : codeLines) {
			code += line + "\n";
		}
		return code;
	}

}
