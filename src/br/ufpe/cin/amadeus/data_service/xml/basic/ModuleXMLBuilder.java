package br.ufpe.cin.amadeus.data_service.xml.basic;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.data_service.xml.DSElement;

public class ModuleXMLBuilder {
	
	private final String module = "mod";
	private final String moduleList = "modList";
	private final String course = "course";
	
	private final String id = "id";
	private final String name = "name";
	private final String description = "desc";
	private final String date = "date";
	private final String type = "type";
	private final String qMat = "qMat";
	private final String qAct = "qAct";
	private final String qForums = "qForums";
	private final String qPolls = "qPolls";
	private final String qGames = "qGames";
	private final String qLobjs = "qLobjs";
	private final String qEvals = "qEvals";
	private final String qVideos = "qVideos";
	private final String qMatReqs = "qMatReqs";
	
	private final String materialList = "matList";
	private final String material = "mat";
	
	private final String activityList = "actList";
	
	
	public DSElement buildModuleInformation(Course course, Module module){
		
		DSElement idModuleTag = new DSElement(this.id);
		idModuleTag.setText(module.getId() + "");
		
		DSElement nameModuleTag = new DSElement(this.name);
		nameModuleTag.setText(module.getName());
		
		DSElement descModuleTag = new DSElement(this.description);
		descModuleTag.setText(module.getDescription());
		
		DSElement qtdMatModuleTag = new DSElement(this.qMat);
		qtdMatModuleTag.setText(module.getMaterials().size() + "");
		
		DSElement qtdForumsModuleTag = new DSElement(this.qForums);
		qtdForumsModuleTag.setText(module.getForums().size() + "");
		
		DSElement qtdPollsModuleTag = new DSElement(this.qPolls);
		qtdPollsModuleTag.setText(module.getPolls().size() + "");
		
		DSElement qtdGamesModuleTag = new DSElement(this.qGames);
		qtdGamesModuleTag.setText(module.getGames().size() + "");

		DSElement qtdLobjsModuleTag = new DSElement(this.qLobjs);
		qtdLobjsModuleTag.setText(module.getLearningObjects().size() + "");
		
		DSElement qtdEvalsModuleTag = new DSElement(this.qEvals);
		qtdEvalsModuleTag.setText(module.getEvaluations().size() + "");
		
		DSElement qtdVideosModuleTag = new DSElement(this.qVideos);
		qtdVideosModuleTag.setText(module.getVideos().size() + "");
		
		DSElement qtdMatReqsModuleTag = new DSElement(this.qMatReqs);
		qtdMatReqsModuleTag.setText(module.getMaterialRequests().size() + "");
		
		DSElement courseIdModuleTag = new DSElement(this.id);
		courseIdModuleTag.setText(course.getId() + "");
		DSElement courseNameModuleTag = new DSElement(this.name);
		courseNameModuleTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdModuleTag);
		courseTag.addContent(courseNameModuleTag);

		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(idModuleTag);
		moduleTag.addContent(nameModuleTag);
		moduleTag.addContent(descModuleTag);
		moduleTag.addContent(qtdMatModuleTag);
		moduleTag.addContent(qtdForumsModuleTag);
		moduleTag.addContent(qtdPollsModuleTag);
		moduleTag.addContent(qtdGamesModuleTag);
		moduleTag.addContent(qtdLobjsModuleTag);
		moduleTag.addContent(qtdEvalsModuleTag);
		moduleTag.addContent(qtdVideosModuleTag);
		moduleTag.addContent(qtdMatReqsModuleTag);
		moduleTag.addContent(courseTag);
		
		
		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getModuleInformation");
		
		DSElement root = new DSElement("msg");
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		root.addContent(moduleTag);
		
		return root;
	}
	
	public DSElement buildModuleList(Course course, List<Module> modules){
		
		DSElement moduleListTag = new DSElement(this.moduleList);
		for (Module mod : modules) {
			DSElement moduleTag = new DSElement(this.module);
			DSElement idModuleTag = new DSElement(this.id);
			idModuleTag.setText(mod.getId() + "");
			
			DSElement nameModuleTag = new DSElement(this.name);
			nameModuleTag.setText(mod.getName());
			
			moduleTag.addContent(idModuleTag);
			moduleTag.addContent(nameModuleTag);
			
			moduleListTag.addContent(moduleTag);
		}
		
		DSElement courseIdModuleTag = new DSElement(this.id);
		courseIdModuleTag.setText(course.getId() + "");
		DSElement courseNameModuleTag = new DSElement(this.name);
		courseNameModuleTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdModuleTag);
		courseTag.addContent(courseNameModuleTag);
		
		
		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getModuleList");
		
		DSElement root = new DSElement("msg");
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		root.addContent(moduleListTag);
		root.addContent(courseTag);
		
		return root;
	}
	
	public DSElement buildModule(Course course, Module module){
		
		int activities = 0; 
		activities += module.getForums().size();
		activities += module.getPolls().size();
		activities += module.getGames().size();
		activities += module.getLearningObjects().size();
		activities += module.getEvaluations().size();
		activities += module.getMaterialRequests().size();
		
		DSElement idModuleTag = new DSElement(this.id);
		idModuleTag.setText(module.getId() + "");
		
		DSElement nameModuleTag = new DSElement(this.name);
		nameModuleTag.setText(module.getName());
		
		DSElement qtdMaterialsModuleTag = new DSElement(this.qMat);
		qtdMaterialsModuleTag.setText(module.getMaterials().size() + "");
		
		DSElement qtdActivitiesModuleTag = new DSElement(this.qAct);
		qtdActivitiesModuleTag.setText(activities + "");
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(idModuleTag);
		moduleTag.addContent(nameModuleTag);
		moduleTag.addContent(qtdMaterialsModuleTag);
		moduleTag.addContent(qtdActivitiesModuleTag);
		

		DSElement courseIdModuleTag = new DSElement(this.id);
		courseIdModuleTag.setText(course.getId() + "");
		DSElement courseNameModuleTag = new DSElement(this.name);
		courseNameModuleTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdModuleTag);
		courseTag.addContent(courseNameModuleTag);

		
		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getModule");
		
		DSElement root = new DSElement("msg");
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		root.addContent(moduleTag);
		root.addContent(courseTag);
		
		return root;
	}
	
	public DSElement buildModuleMaterialList(Course course, Module module){

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getModuleInformation");
		
		DSElement root = new DSElement("msg");
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		List<Material> materials = module.getMaterials();
		DSElement materialListTag = new DSElement(this.materialList);
		for (Material mat : materials) {
			DSElement idMaterialTag = new DSElement(this.id);
			idMaterialTag.setText(mat.getId() + "");
			
			DSElement nameMaterialTag = new DSElement(this.name);
			nameMaterialTag.setText(mat.getArchiveName());
			
			DSElement dateMaterialTag = new DSElement(this.date);
			dateMaterialTag.setText(mat.getCreationDate() + "");
			
			DSElement typeMaterialTag = new DSElement(this.type);
			typeMaterialTag.setText(mat.getExtension());
			
			DSElement material = new DSElement(this.material);
			material.addContent(idMaterialTag);
			material.addContent(nameMaterialTag);
			material.addContent(dateMaterialTag);
			material.addContent(typeMaterialTag);
			
			materialListTag.addContent(material);
		}
		root.addContent(materialListTag);

		
		DSElement courseIdModuleTag = new DSElement("id");
		courseIdModuleTag.setText(course.getId() + "");
		DSElement courseNameModuleTag = new DSElement("name");
		courseNameModuleTag.setText(course.getName());

		DSElement courseTag = new DSElement("course");
		courseTag.addContent(courseIdModuleTag);
		courseTag.addContent(courseNameModuleTag);
		
		root.addContent(courseTag);
		
		return root;
	}
	
	public DSElement buildModuleActivityList(Course course, Module module){
		
		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getModuleInformation");
		
		DSElement root = new DSElement("msg");
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		DSElement activityListTag = new DSElement(this.activityList);
		activityListTag.addContent( Util.buildForums(module.getForums()) );
		activityListTag.addContent( Util.buildPolls(module.getPolls()) );
		activityListTag.addContent( Util.buildGames(module.getGames()) );
		activityListTag.addContent( Util.buildLearningObjects(module.getLearningObjects()) );
		activityListTag.addContent( Util.buildMaterialRequest(module.getMaterialRequests()) );
		activityListTag.addContent( Util.buildEvaluations(module.getEvaluations()) );
		activityListTag.addContent( Util.buildVideos(module.getVideos()) );
		root.addContent(activityListTag);
		
		
		DSElement moduleIdTag = new DSElement(this.id);
		moduleIdTag.setText(module.getId() + "");
		DSElement moduleNameTag = new DSElement(this.name);
		moduleNameTag.setText(module.getName());
		
		DSElement moduleTag = new DSElement(this.module);
		moduleTag.addContent(moduleIdTag);
		moduleTag.addContent(moduleNameTag);
		
		root.addContent(moduleTag);
		
		
		DSElement courseIdModuleTag = new DSElement(this.id);
		courseIdModuleTag.setText(course.getId() + "");
		DSElement courseNameModuleTag = new DSElement(this.name);
		courseNameModuleTag.setText(course.getName());
		
		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(courseIdModuleTag);
		courseTag.addContent(courseNameModuleTag);
		
		root.addContent(courseTag);
		
		return root;
	}
	
}
