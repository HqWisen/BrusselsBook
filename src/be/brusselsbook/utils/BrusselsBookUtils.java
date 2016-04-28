package be.brusselsbook.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class BrusselsBookUtils {

	private static final int BUFFER_SIZE = 16384;
	private static final byte[] BUFFER = new byte[BUFFER_SIZE];

	public static ArrayList<String> createListFrom(String... array) {
		return new ArrayList<>(Arrays.asList(array));
	}

	public static List<Byte> toByteList(byte[] bytes) {
		List<Byte> list = new ArrayList<>();
		for (int i = 0; i < bytes.length; i++) {
			list.add(new Byte(bytes[i]));
		}
		return list;
	}

	public static byte[] toByteArray(List<Byte> byteList){
		byte[] bytes = new byte[byteList.size()];
		for(int i = 0; i<byteList.size(); i++){
			bytes[i] = byteList.get(i);
		}
		return bytes;
	}
	
	public static String serializableAsString(Serializable serializable) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(serializable);
			oos.close();
		} catch (IOException e) {
			throw new IOException("Cannot encode serializable to string.");
		}
		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}

	public static Serializable serializableFromString(String string) throws IOException, ClassNotFoundException {
		if(string == null){
			throw new IOException("Cannot decode a null string.");
		}
		byte[] data = Base64.getDecoder().decode(string);
		ObjectInputStream ois;
		Serializable arg;
		try {
			ois = new ObjectInputStream(new ByteArrayInputStream(data));
			arg = (Serializable) ois.readObject();
			ois.close();
		} catch (IOException e) {
			throw new IOException("Cannot get serializable argument.");
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Cast problem while parsing serializable.");
		}
		return arg;
	}

	public static List<Byte> toByteList(File file) throws IOException {
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new IOException("The file to transform to byte list hasn't been found.");
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int count;
		try {
			while ((count = in.read(BUFFER)) > 0) {
				out.write(BUFFER, 0, count);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			new IOException("An error occured while transforming the file to a byte list.");
		}
		return toByteList(out.toByteArray());
	}
	

	public static String xmlElementAsString(final Object object) {
		try {
			final JAXBContext context = JAXBContext.newInstance(object.getClass());
			final Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			final StringWriter out = new StringWriter();
			marshaller.marshal(object, out);
			return out.toString();
		} catch (JAXBException e) {
			return null;
		}
	}

	/**
	 * @param xml
	 * @param clazz
	 * @return
	 */
	public static Object stringAsXmlElement(final String xml, final Class<?> clazz) {
		try {
			final JAXBContext context = JAXBContext.newInstance(clazz);
			final Unmarshaller unmarshaller = context.createUnmarshaller();
			return unmarshaller.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			return null;
		}
	}

	/**
	 * @param encoded
	 * @param charset
	 * @return
	 */
	public static String decodeBase64AsString(final String encoded, final String charset) {
		try {
			final byte[] decodedBytes = Base64.getDecoder().decode(encoded);
			return new String(decodedBytes, charset);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * @param encoded
	 * @return
	 */
	public static String decodeBase64AsString(final String encoded) {
		return decodeBase64AsString(encoded, "UTF-8");
	}

	/**
	 * @return
	 */
	public static String generateKey() {
		String hashtext;

		final Date date = new Date();
		final String plaintext = String.valueOf(date.getTime());

		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(plaintext.getBytes());
			final byte[] digest = messageDigest.digest();
			final BigInteger bigInt = new BigInteger(1, digest);
			hashtext = bigInt.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return hashtext;
	}


}
