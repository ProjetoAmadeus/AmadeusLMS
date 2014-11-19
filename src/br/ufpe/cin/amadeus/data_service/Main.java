package br.ufpe.cin.amadeus.data_service;

import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.HibernateUtil;
import br.ufpe.cin.amadeus.data_service.controllers.CtrlResquest;

public class Main {
	
	public static final String getCourse = "<msg><id>1</id><type>GET</type><op>getCourse</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idCourse</name><value>7</value></param></msg>";
	public static final String getCourseList = "<msg><id>1</id><type>GET</type><op>getCourseList</op><login>grso</login><pass>dartanham</pass></msg>";
	public static final String getCourseInformation = "<msg><id>1</id><type>GET</type><op>getCourseInformation</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idCourse</name><value>7</value></param></msg>";
	
	public static final String getModule = "<msg><id>1</id><type>GET</type><op>getModule</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idModule</name><value>10</value></param></msg>";
	public static final String getModuleList = "<msg><id>1</id><type>GET</type><op>getModuleList</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idCourse</name><value>7</value></param></msg>";
	public static final String getModuleInformation = "<msg><id>1</id><type>GET</type><op>getModuleInformation</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idModule</name><value>10</value></param></msg>";
	
	public static final String getCourseMaterialList = "<msg><id>1</id><type>GET</type><op>getCourseMaterialList</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idCourse</name><value>7</value></param></msg>";
	public static final String getCourseActivityList = "<msg><id>1</id><type>GET</type><op>getCourseActivityList</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idCourse</name><value>7</value></param></msg>";
	
	public static final String getForum = "<msg><id>1</id><type>GET</type><op>getForum</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idForum</name><value>17</value></param></msg>";
	public static final String getPoll = "<msg><id>1</id><type>GET</type><op>getPoll</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idPoll</name><value>13</value></param></msg>";
	public static final String getPoolResults = "<msg><id>1</id><type>GET</type><op>getPollResults</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idPoll</name><value>13</value></param></msg>";
	public static final String getMaterialDelivery = "<msg><id>1</id><type>GET</type><op>getMaterialDelivery</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idMaterialRequest</name><value>43</value></param></msg>";
	public static final String getGame = "<msg><id>1</id><type>GET</type><op>getGame</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>idGame</name><value>1</value></param></msg>";
	public static final String getLearningObject = "<msg><id>1</id><type>GET</type><op>getLearningObject</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>learningObjectId</name><value>21</value></param></msg>";
	public static final String getEvaluation = "<msg><id>1</id><type>GET</type><op>getEvaluation</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>evaluationId</name><value>22</value></param></msg>";
	public static final String getVideo = "<msg><id>1</id><type>GET</type><op>getVideo</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>videoId</name><value>20</value></param></msg>";
	public static final String getMaterial = "<msg><id>1</id><type>GET</type><op>getMaterial</op><login>grso</login><pass>dartanham</pass><param><id>nada</id><name>materialId</name><value>20</value></param></msg>";
	
	public static void main(String[] args) {
		
		System.out.println("início da execução");
		
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		CtrlResquest req = CtrlResquest.getInstance();

		String ret = req.execute(Main.getCourse);
		System.out.println("*********** RESPOSTA DO SERVIDOR ***********");
		System.out.println(ret);
		System.out.println("*********** RESPOSTA DO SERVIDOR ***********");
		
//		req.execute(Main.getCourseList);
//		req.execute(Main.getCourseInformation);
		
//		req.execute(Main.getModule);
//		req.execute(Main.getModuleList);
//		req.execute(Main.getModuleInformation);
		
//		req.execute(Main.getCourseMaterialList);
//		req.execute(Main.getCourseActivityList);
		
//		req.execute(Main.getForum);
//		req.execute(Main.getPoll);
//		req.execute(Main.getPoolResults);
//		req.execute(Main.getMaterialDelivery);
//		req.execute(Main.getGame);
//		req.execute(Main.getLearningObject);
//		req.execute(Main.getEvaluation);
//		req.execute(Main.getVideo);
//		req.execute(Main.getMaterial);
		
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		
		System.out.println("fim da execução");
		
	}
	
}
