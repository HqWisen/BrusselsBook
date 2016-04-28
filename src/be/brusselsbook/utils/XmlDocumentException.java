package be.brusselsbook.utils;

public class XmlDocumentException extends RuntimeException {

	private static final long serialVersionUID = 5076000036052831628L;

	public XmlDocumentException(String message) {
		super(message);
	}
	
	public XmlDocumentException(Throwable cause){
		super(cause);
	}
}
