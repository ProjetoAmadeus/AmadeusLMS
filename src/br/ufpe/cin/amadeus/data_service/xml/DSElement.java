package br.ufpe.cin.amadeus.data_service.xml;

import java.util.List;

import org.jdom.Element;

public class DSElement extends Element{

	private static final long serialVersionUID = -1600713344787931922L;

	
	public DSElement() {
		super();
	}
	public DSElement(String string) {
		super(string);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<" + this.getName() + ">");
		
		List<DSElement> children = this.getChildren();
		for (DSElement ele : children) {
			
			if(ele.getChildren().size() > 0){
				sb.append("<" + ele.getName() + ">");
				sb.append(ele.toString());
				sb.append("</" + ele.getName() + ">");
			}else{
				sb.append("<" + ele.getName() + ">");
				sb.append(ele.getValue());
				sb.append("</" + ele.getName() + ">");
			}
		}
		sb.append("</" + this.getName() + ">");
		
		return sb.toString();
	}
	
}
