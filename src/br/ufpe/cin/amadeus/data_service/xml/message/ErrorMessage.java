package br.ufpe.cin.amadeus.data_service.xml.message;

import org.jdom.Element;

public class ErrorMessage {

	private int id;
	private String operation;
	private String type;
	private String text;

	private Element xmlMessage;
	private String xmlStringMessage;

	
	public ErrorMessage(int id, String op, String type, String text) {
		this.id = id;
		this.operation = op;
		this.type = type;
		this.text = text;
		this.buildXML();
	}

	private void buildXML() {
		Element root = new Element("msg");
		
		Element idTag = new Element("id");
		idTag.setText(this.getId() + "");
		Element operationTag = new Element("op");
		operationTag.setText(this.getOperation());
		Element typeTag = new Element("type");
		typeTag.setText(this.getType());
		Element textTag = new Element("text");
		textTag.setText(this.getText());
		
		root.addContent(idTag);
		root.addContent(operationTag);
		root.addContent(typeTag);
		root.addContent(textTag);
		
		//building a xml string
		String msg = "<msg><id>#id#</id><op>#op#</op><type>#type#</type><text>#text#</text></msg>";
		msg = msg.replace("#id#", this.id + "");
		msg = msg.replace("#op#", this.operation);
		msg = msg.replace("#type#", this.getType());
		msg = msg.replace("#text#", this.getText());
		this.xmlStringMessage = msg;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Element getXmlMessage() {
		return xmlMessage;
	}

	public void setXmlMessage(Element xmlMessage) {
		this.xmlMessage = xmlMessage;
	}

	public String getXmlStringMessage() {
		return xmlStringMessage;
	}

	public void setXmlStringMessage(String xmlStringMessage) {
		this.xmlStringMessage = xmlStringMessage;
	}

}
