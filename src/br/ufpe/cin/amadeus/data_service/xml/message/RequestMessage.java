package br.ufpe.cin.amadeus.data_service.xml.message;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class RequestMessage {
	
	private String id;
	private String type;
	private String op;
	
	private String login;
	private String pass;
	
	private String idParam;
	private String nameParam;
	private String valueParam;
	
	private Document completeMessage;
	
	
	public RequestMessage(String xmlMessage) throws JDOMException, IOException {
		this.completeMessage = this.stringXmlToDocument(xmlMessage);
		this.fillParametersFromXMLDocument(this.completeMessage);
	}
	
	public Document stringXmlToDocument(String xml) throws JDOMException, IOException{
        Document xmlDocument = new Document();
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setValidation(false);
        xmlDocument = saxBuilder.build(new StringReader(xml));

        return xmlDocument;
    }
	
	public void fillParametersFromXMLDocument(Document xmlDoc){
		Element root = xmlDoc.getRootElement();
		
		this.id = root.getChildText("id");
		this.type = root.getChildText("type");
		this.op = root.getChildText("op");
		
		this.login = root.getChildText("login");
		this.pass = root.getChildText("pass");
		
		Element param = root.getChild("param");
		if(param != null){
			this.idParam = param.getChildText("id");
			this.nameParam = param.getChildText("name");
			this.valueParam = param.getChildText("value");
		}
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getNameParam() {
		return nameParam;
	}

	public void setNameParam(String nameParam) {
		this.nameParam = nameParam;
	}

	public String getValueParam() {
		return valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}

	public Document getCompleteMessage() {
		return completeMessage;
	}

	public void setCompleteMessage(Document xmlMessage) {
		this.completeMessage = xmlMessage;
	}

	
	public String getLogin() {
		return login;
	}

	
	public void setLogin(String login) {
		this.login = login;
	}

	
	public String getPass() {
		return pass;
	}

	
	public void setPass(String pass) {
		this.pass = pass;
	}

	
	public String getIdParam() {
		return idParam;
	}

	
	public void setIdParam(String idParam) {
		this.idParam = idParam;
	}

}
