package be.brusselsbook.utils;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlDocument {
	private static final DocumentBuilderFactory documentBuilderFactory;
	private static final DocumentBuilder documentBuilder;
	private static final TransformerFactory transformerFactory;
	private static final Transformer transformer;

	private static final String DEFAULT_ARGSKEY = "args";
	private static final String UNKNOWN_AUTHOR = "unknown";

	private static int idCounter;

	static {
		idCounter = 0;
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		transformerFactory = TransformerFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XmlDocumentException("Cannot create document builder of " + XmlDocument.class);
		}
		documentBuilder = docBuilder;
		Transformer tfrm = null;
		try {
			tfrm = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			throw new XmlDocumentException("Cannot create transfomer of " + XmlDocument.class);
		}
		transformer = tfrm;
	}

	protected static Document parseDocument(String docString) {
		Document document = null;
		if (docString == null) {
			return null;
		}
		try {
			document = documentBuilder.parse(new InputSource(new StringReader(docString)));
		} catch (SAXException | IOException e) {
			throw new XmlDocumentException("Cannot parse " + XmlDocument.class.getName() + " from string.");
		}
		return document;
	}

	public static XmlDocument parse(String docString) {
		Document document = parseDocument(docString);
		if (document != null) {
			XmlDocument result = new XmlDocument(document);
			return result;
		}
		return null;
	}

	private static int getNextId() {
		return ++idCounter;
	}

	private int id;
	private Document document;
	private Map<String, Element> elements;
	private String author;

	public XmlDocument() {
		this.id = getNextId();
		this.document = documentBuilder.newDocument();
		this.elements = new HashMap<>();
		addRootElement();
		addArgsElement();
		setAuthor(UNKNOWN_AUTHOR);

	}

	protected XmlDocument(Document document) {
		this.document = document;
		this.elements = new HashMap<>();
		Node firstNode = document.getFirstChild();
		if (firstNode.getNodeType() != Node.ELEMENT_NODE) {
			throw new XmlDocumentException("The first child of the parsed document must be an element.");
		} else {
			Element root = (Element) firstNode;
			putElements(root);
			this.id = Integer.parseInt(root.getAttribute("id"));
			this.author = root.getAttribute("author");
		}
	}

	private void putElements(Element element) {
		putElement(element.getNodeName(), element);
		NodeList childList = element.getChildNodes();
		for (int i = 0; i < childList.getLength(); i++) {
			if (childList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				putElements((Element) childList.item(i));
			}
		}
	}

	private void addRootElement() {
		addElement(document, rootKey());
		getElement(rootKey()).setAttribute("id", Integer.toString(getId()));
	}

	private void addArgsElement() {
		addElementToRoot(argsKey());
	}

	protected void addElement(Node parent, String key) {
		Element element = document.createElement(key);
		addElement(parent, key, element);
	}

	protected void addElementToRoot(String key) {
		addElement(getRootElement(), key);
	}

	private void addElement(Node parent, String key, Element element) {
		if (parent == null) {
			throw new XmlDocumentException("Cannot add element because parent is null.");
		} else {
			parent.appendChild(element);
			putElement(key, element);
		}
	}

	private void putElement(String key, Element element) {
		elements.put(key, element);
	}

	protected String rootKey() {
		return getClass().getSimpleName();
	}

	protected String argsKey() {
		return DEFAULT_ARGSKEY;
	}

	protected Element getElement(String key) {
		if (containsElement(key)) {
			return elements.get(key);
		}
		return null;
	}

	protected Element getRootElement() {
		return getElement(rootKey());
	}

	protected Element getArgsElement() {
		return getElement(argsKey());
	}

	public String getStringArg(String tag) {
		if (containsElement(tag) && isUseableKey(tag)) {
			return getElement(tag).getTextContent();
		}
		return null;
	}

	public List<?> getListArg(String tag) {
		return (List<?>) getSerializableArg(tag);
	}

	public Serializable getSerializableArg(String tag) {
		String strarg = getStringArg(tag);
		Serializable arg = null;
		try {
			arg = BrusselsBookUtils.serializableFromString(strarg);
		} catch (ClassNotFoundException | IOException e) {
			new XmlDocumentException(e);
		}
		return arg;
	}

	public void setStringArg(String tag, String content) {
		if (!isUseableKey(tag)) {
			throw new XmlDocumentException(
					"Cannot add argument with tag: " + tag + " because is used in the document.");
		} else if (!containsElement(tag)) {
			addElement(getArgsElement(), tag);
		}
		getElement(tag).setTextContent(content);
	}

	public void setListArg(String tag, List<? extends Serializable> list) {
		setSerializableArg(tag, (Serializable) list);
	}

	public void setSerializableArg(String tag, Serializable serializable) {
		try {
			String content = BrusselsBookUtils.serializableAsString(serializable);
			setStringArg(tag, content);
		} catch (IOException e) {
			throw new XmlDocumentException(e);
		}
	}

	public boolean isUseableKey(String key) {
		return key != rootKey() && key != argsKey();
	}

	public boolean containsElement(String key) {
		return elements.containsKey(key);
	}

	public DOMSource createSource() {
		return new DOMSource(document);
	}

	public int getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
		getRootElement().setAttribute("author", author);
	}

	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		try {
			transformer.transform(createSource(), new StreamResult(writer));
		} catch (TransformerException e) {
			System.err.println("Error while printing " + getClass().getName());
			throw new XmlDocumentException("Cannot transform xmldocument to string.");
		}
		return writer.getBuffer().toString();
	}

}
