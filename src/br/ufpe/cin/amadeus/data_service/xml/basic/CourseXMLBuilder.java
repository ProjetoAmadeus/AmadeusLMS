package br.ufpe.cin.amadeus.data_service.xml.basic;

import java.util.List;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.data_service.xml.DSElement;

public class CourseXMLBuilder {
	
	private final String course = "course";
	private final String courseList = "courseList";	
	
	private final String id = "id";
	private final String name = "name";
	private final String color = "color";
	private final String qMod = "qMod";
	private final String qMat = "qMat";
	private final String qAct = "qAct";
	private final String description = "desc";
	private final String professors = "profs";
	private final String helpers = "helps";
	private final String objectives = "objec";
	private final String program = "program";
	private final String maxStudents = "max_stud";
	private final String beginDate = "i_date";
	private final String endDate = "e_date";
	private final String keywords = "kwords";
	
	private final String date = "date";
	private final String extension = "ext";
	
	private final String materialList = "matList";
	private final String material = "mat";
	
	private final String activityList = "actList";
	
	
	public DSElement buildCourseInformation(Course course, String courseColor) {

		DSElement idCourseTag = new DSElement(this.id);
		idCourseTag.setText(course.getId() + "");

		DSElement nameCourseTag = new DSElement(this.name);
		nameCourseTag.setText(course.getName());

		DSElement colorCourseTag = new DSElement(this.color);
		colorCourseTag.setText(courseColor);

		DSElement qModCourseTag = new DSElement(this.qMod);
		qModCourseTag.setText(course.getModules().size() + "");

		DSElement qMatCourseTag = new DSElement(this.qMat);

		DSElement qActCourseTag = new DSElement(this.qAct);

		DSElement descCourseTag = new DSElement(this.description);

		DSElement profsCourseTag = new DSElement(this.professors);
		profsCourseTag.setText(course.getProfessor().getName());

		DSElement helpsCourseTag = new DSElement(this.helpers);

		DSElement objectivesCourseTag = new DSElement(this.objectives);
		objectivesCourseTag.setText(course.getObjectives());

		DSElement programCourseTag = new DSElement(this.program);
		programCourseTag.setText(course.getContent());

		DSElement maxStudantsCourseTag = new DSElement(this.maxStudents);

		DSElement initDateCourseTag = new DSElement(this.beginDate);
		initDateCourseTag.setText(course.getInitialCourseDate().toString());

		DSElement endDateCourseTag = new DSElement(this.endDate);
		endDateCourseTag.setText(course.getFinalCourseDate().toString());

		DSElement keywordsCourseTag = new DSElement(this.keywords);

		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(idCourseTag);
		courseTag.addContent(nameCourseTag);
		courseTag.addContent(colorCourseTag);
		courseTag.addContent(qModCourseTag);
		courseTag.addContent(qMatCourseTag);
		courseTag.addContent(qActCourseTag);
		courseTag.addContent(descCourseTag);
		courseTag.addContent(profsCourseTag);
		courseTag.addContent(helpsCourseTag);
		courseTag.addContent(objectivesCourseTag);
		courseTag.addContent(programCourseTag);
		courseTag.addContent(maxStudantsCourseTag);
		courseTag.addContent(initDateCourseTag);
		courseTag.addContent(endDateCourseTag);
		courseTag.addContent(keywordsCourseTag);

		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("id auto gerado");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getCourseInformation");

		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		root.addContent(courseTag);

		return root;
	}
	
	public DSElement buildCourseList(List<Course> courses) {

		DSElement courseListTag = new DSElement(this.courseList); 
		for (Course c : courses) {
			DSElement idCourse = new DSElement(this.id);
			idCourse.setText(c.getId() + "");
			DSElement nameCourse = new DSElement(this.name);
			nameCourse.setText(c.getName());

			DSElement course = new DSElement(this.course);
			course.addContent(idCourse);
			course.addContent(nameCourse);

			courseListTag.addContent(course); 
		}

		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getCourseList");

		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		root.addContent(courseListTag);

		return root;
	}
	
	public DSElement buildCourse(int idCourse, String nameCourse, int nModules,
			int nMaterials, int nActivities) {

		DSElement idCourseTag = new DSElement(this.id);
		idCourseTag.setText(idCourse + "");

		DSElement nameCourseTag = new DSElement(this.name);
		nameCourseTag.setText(nameCourse);

		DSElement qModTag = new DSElement(this.qMod);
		qModTag.setText(nModules + "");

		DSElement qMatTag = new DSElement(this.qMat);
		qMatTag.setText(nMaterials + "");

		DSElement qActTag = new DSElement(this.qAct);
		qActTag.setText(nActivities + "");

		DSElement courseTag = new DSElement(this.course);
		courseTag.addContent(idCourseTag);
		courseTag.addContent(nameCourseTag);
		courseTag.addContent(qModTag);
		courseTag.addContent(qMatTag);
		courseTag.addContent(qActTag);

		DSElement root = new DSElement("msg");

		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getCourse");

		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		root.addContent(courseTag);

		return root;
	}
	
	public DSElement buildCourseMaterialList(Course course, List<Material> materials) {

		DSElement root = new DSElement("msg");
		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getCourseMaterialList");
		
		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		DSElement materialListTag = new DSElement(this.materialList);
		for (Material mat : materials) {
			DSElement idMatTag = new DSElement(this.id);
			idMatTag.setText(mat.getId() + "");

			DSElement nameMatTag = new DSElement(this.name);
			nameMatTag.setText(mat.getArchiveName());

			DSElement dateMatTag = new DSElement(this.date);
			dateMatTag.setText(mat.getCreationDate() + "");

			DSElement fileTypeTag = new DSElement(this.extension);
			fileTypeTag.setText(mat.getExtension());

			DSElement matTag = new DSElement(this.material);
			matTag.addContent(idMatTag);
			matTag.addContent(nameMatTag);
			matTag.addContent(dateMatTag);
			matTag.addContent(fileTypeTag);

			materialListTag.addContent(matTag);
		}
		root.addContent(materialListTag);
		
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
	
	public DSElement buildCourseActivityList(Course course, List<Forum> forums,
			List<Poll> polls, List<Game> games, List<LearningObject> objs,
			List<Evaluation> evaluations, List<VideoIriz> videos,
			List<MaterialRequest> materialRequests) {
		
		DSElement root = new DSElement("msg");
		DSElement idTag = new DSElement("id");
		idTag.setText("0");
		DSElement typeTag = new DSElement("type");
		typeTag.setText("DATA");
		DSElement opTag = new DSElement("op");
		opTag.setText("getCourseActivityList");

		root.addContent(idTag);
		root.addContent(typeTag);
		root.addContent(opTag);
		
		DSElement activityListTag = new DSElement(this.activityList);
		activityListTag.addContent( Util.buildForums(forums) );
		activityListTag.addContent( Util.buildPolls(polls) );
		activityListTag.addContent( Util.buildGames(games) );
		activityListTag.addContent( Util.buildLearningObjects(objs) );
		activityListTag.addContent( Util.buildEvaluations(evaluations) );
		activityListTag.addContent( Util.buildVideos(videos) );
		activityListTag.addContent( Util.buildMaterialRequest(materialRequests) );
		root.addContent(activityListTag);
		
		
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
