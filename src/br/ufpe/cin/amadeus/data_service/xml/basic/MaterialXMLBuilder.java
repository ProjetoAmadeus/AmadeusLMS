package br.ufpe.cin.amadeus.data_service.xml.basic;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.data_service.xml.DSElement;

public class MaterialXMLBuilder {
	
	private final String module = "mod";
	private final String course = "course";
	
	private final String material = "mat";
	private final String id = "id";
	private final String name = "name";
	private final String date = "date";
	private final String type = "type";
	

	public DSElement buildMaterial(Course course, Module module, Material material){
		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getMaterialList");
		
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		
		DSElement idMaterialTag = new DSElement(this.id);
		idMaterialTag.setText(material.getId() + "");
		
		DSElement nameMaterialTag = new DSElement(this.name);
		nameMaterialTag.setText(material.getArchiveName());
		
		DSElement dateMaterialTag = new DSElement(this.date);
		dateMaterialTag.setText(material.getCreationDate() + "");
		
		DSElement typeMaterialTag = new DSElement(this.type);
		typeMaterialTag.setText(material.getExtension());
		
		DSElement materialTag = new DSElement(this.material);
		materialTag.addContent(idMaterialTag);
		materialTag.addContent(nameMaterialTag);
		materialTag.addContent(dateMaterialTag);
		materialTag.addContent(typeMaterialTag);
		
		root.addContent(materialTag);
		
		
		DSElement moduleIdTag = new DSElement(this.id);
		moduleIdTag.setText(module.getId() + "");
		DSElement moduleNameTag = new DSElement(this.name);
		moduleNameTag.setText(module.getName());
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(moduleIdTag);
		moduleTag.addContent(moduleNameTag);
		
		root.addContent(moduleTag);
		
		
		DSElement courseIdTag = new DSElement(this.id);
		courseIdTag.setText(course.getId() + "");
		DSElement courseNameTag = new DSElement(this.name);
		courseNameTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdTag);
		courseTag.addContent(courseNameTag);

		root.addContent(courseTag);
		
		
		return root;
	}
}
