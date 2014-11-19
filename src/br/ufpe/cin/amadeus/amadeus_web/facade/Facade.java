/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo ï¿½ parte do programa Amadeus Sistema de Gestï¿½o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS ï¿½ um software livre; vocï¿½ pode redistribui-lo e/ou modifica-lo dentro dos termos da Licenï¿½a Pï¿½blica Geral GNU como
publicada pela Fundaï¿½ï¿½o do Software Livre (FSF); na versï¿½o 2 da Licenï¿½a.
 
Este programa ï¿½ distribuï¿½do na esperanï¿½a que possa ser ï¿½til, mas SEM NENHUMA GARANTIA; sem uma garantia implï¿½cita de ADEQUAï¿½ï¿½O a qualquer MERCADO ou APLICAï¿½ï¿½O EM PARTICULAR. Veja a Licenï¿½a Pï¿½blica Geral GNU para maiores detalhes.
 
Vocï¿½ deve ter recebido uma cï¿½pia da Licenï¿½a Pï¿½blica Geral GNU, sob o tï¿½tulo "LICENCA.txt", junto com este programa, se nï¿½o, escreva para a Fundaï¿½ï¿½o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.facade;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.AmadeusDroidHistoric;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Course;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.ExternalLink;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Forum;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Game;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.HistoryLearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Homework;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Keyword;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.LearningObject;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Log;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Message;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Module;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.PersonRoleCourse;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Poll;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Role;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.RoleType;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.VideoIriz;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Evaluation;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.Question;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionDiscursive;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionGap;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionMultiple;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.QuestionTrueFalse;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.EvaluationRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.evaluation.realized.QuestionDiscursiveRealized;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.AccessInfo;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.MessengerMessage;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.OpenID;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Resume;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Tweet;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.UserRequest;
import br.ufpe.cin.amadeus.amadeus_web.exception.CourseInvalidException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidCurrentPasswordException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidLogonException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidMaterialException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidSocialCredencialsException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidUserException;
import br.ufpe.cin.amadeus.amadeus_web.exception.InvalidVideoException;
import br.ufpe.cin.amadeus.amadeus_web.exception.RequestException;
import br.ufpe.cin.amadeus.amadeus_web.syncronize.Archive;

public class Facade {
	
	private static Facade amadeusFacade = null;
	
	private Controller controller = null;
	
	private Facade(){
		controller = Controller.getInstance();
	}
	
	public static Facade getInstance(){
		if(amadeusFacade == null){
			return new Facade();
		} else {
			return amadeusFacade;
		}
	}
	
	
	/**
	 * Mï¿½todo que insere um usuï¿½rio no sistema Amadeus
	 * @param person Usuï¿½rio a ser cadastrado
	 * @return person Usuï¿½rio jï¿½ cadastrado, com seu ID gerado pelo sistema
	 * @throws UserAlreadyExistsException Exceï¿½ï¿½o levantada quando o usuï¿½rio a ser cadastrado jï¿½ existe no sistema
	 */
	public Person insertPerson(Person person) throws InvalidUserException{
		return controller.insertPerson(person);
	}
	
	/**
	 * Mï¿½todo que insere um curso no sistema Amadeus
	 * @param course
	 * @return course jï¿½ cadastrado, com seu ID gerado pelo sistema
	 * @throws CourseInvalidException 
	 */
	public Course insertCourse(Course course) throws CourseInvalidException {
		return controller.insertCourse(course);
	}
	
	public void deleteCourse(Course course) throws CourseInvalidException {
		controller.deleteCourse(course);
	}
	
    /*public PersonRoleCourse insertPersonRoleCourse(Course c){
    	return controller.insertPersonRoleCourse(c);
    }*/
    
    /*public PersonRoleCourse registerStudentCourse(Course c, AccessInfo user){
    	return controller.registerStudentCourse(c, user);
    }*/
	
	/**
	 * Mï¿½todo que atualiza um curso no sistema Amadeus
	 * @param course
	 * @return course jï¿½ atualizado
	 */
	public Course updateCourse(Course course) {
		return controller.updateCourse(course);
	}
	
	public Course getCoursesById(int courseId){
		return this.controller.getCoursesById(courseId);
	}
		
	public List<Course> getAllCourses() {
		return this.controller.getAllCourses();
	}
	
	public void validateCourseStepOne(Course c) throws CourseInvalidException {
		controller.validateCourseStepOne(c);
	}
	
	public List<Keyword> getMostPopularKeywords(){
		return controller.getMostPopularKeywords();
	}
	
	/**
	 * Mï¿½todo que insere uma palavra-chave no sistema amadeus
	 * @param keyword palavra-chave a ser cadastrada
	 * @return keyword palavra-chave jï¿½ cadastrada, com seu ID gerado pelo sistema
	 */
	public Keyword insertKeyword(Keyword keyword){
		return controller.insertKeyword(keyword);
	}
	
	/**
	 * Mï¿½todo que remove uma palavra-chave no sistema amadeus
	 * @param keyword palavra-chave a ser descadastrada
	 */
	public void removeKeyword(Keyword keyword){
		controller.removeKeyword(keyword);
	}
	
	/**
	 * Mï¿½todo que verifica se uma palavra-chave esta cadastrada no sistema amadeus
	 * @param keyword palavra-chave a ser verificada
	 * @return boleano da verificaï¿½ï¿½o
	 */
	public boolean existKeyword(Keyword keyword){
		return controller.existKeyword(keyword);
	}
	

	
	/**
	 * return a password 
	 * @param email
	 * @return
	 * @throws MessagingException 
	 * @throws IOException 
	 */
	public String remindPassword(String email) throws MessagingException, IOException{
		return controller.remindPassword(email);
	}
	
	public List<Course> getCoursesByKeyword(String keyword){
		return controller.getCoursesByKeyword(keyword);
	}
	
	public List<Course> getCoursesByAdvancedRule(String name, String professorName, Date initialDate, Date finalDate){
		return controller.getCoursesByAdvancedRule(name, professorName, initialDate, finalDate);
	}
	
	public List<Course>[] getCoursesByRule(String rule){
		return controller.getClassifiedCoursesByRule(rule);
	}
	
	public boolean existLogin(String login){
		return controller.existLogin(login);
	}
	
	/**
	 * This Method search one user by a given login 
	 * @param login
	 * @return if user not found return null otherwise return access info of the user
	 */
	
	public AccessInfo searchUserByLogin(String login){
		return controller.searchUserByLogin(login);
	}
	
	public AccessInfo searchUserById(int id){
		return controller.searchUserById(id);
	}
	
	/**
	 * Remove User
	 * @param person
	 * @return
	 * @throws InvalidUserException 
	 */
	
	public void removeUser(int id) throws InvalidUserException{
		controller.removeUser(id);
	}
	
	public void allowUserLogon(int id) throws InvalidUserException{
		controller.allowUserLogon(id);
	}
	
	/**
	 * Edit User
	 * @param person
	 * @return
	 */
	public Person editUser(Person person)throws Exception{
		return controller.editUser(person);
	}

	public boolean existEmail(String email) {
		return controller.existEmail(email);
	}

	/**
	 * this method does o logon of a user, given yours login 
	 * @param login
	 * @param password
	 * @return the info of the logged user
	 * @throws InvalidLogonException
	 */
	public AccessInfo logon(AccessInfo accessInfo) throws InvalidLogonException {
		return this.controller.logon(accessInfo);		
	}
	
	public boolean validateUser(AccessInfo accessInfo){
		return this.controller.validateUser(accessInfo);
	}
	
	/**
	 * this method return the number of pending tasks given the access info of a user
	 * @param userInfo
	 * @return
	 */
	public int getNumberOfPendingTasks(AccessInfo userInfo){
		return this.controller.getNumberOfPendingTasks(userInfo);
	}
	
	public List<Course> searchCoursesByAccessInfo(AccessInfo userInfo){
		return this.controller.searchCoursesByAccessInfo(userInfo);
	}
	
	public List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Course> getCoursesByUserSyncronize(AccessInfo userInfo) {
		return this.controller.getCoursesByUser(userInfo);
	}
		
	public Archive getArchiveByMaterial(int material_id){
		return this.controller.getArchiveByMaterial(material_id);
	}
		
	public List<br.ufpe.cin.amadeus.amadeus_web.syncronize.PersonRoleCourse> getStudentByUser(AccessInfo userInfo) {
		return this.controller.getStudentByUser(userInfo);
	}
	
	public AccessInfo updateUser(AccessInfo ai){
		return this.controller.updateUser(ai);
	}
	
	public int getNumberOfStudentsInCourse(Course c){
		return this.controller.getNumberOfStudentsInCourse(c);
	}

	public void updateModule(Module module){
		this.controller.updateModule(module);
	}
	
	public void deleteModule(Module module){
		this.controller.deleteModule(module);
	}
	
	public void updateResume(Resume resume) {
		this.controller.updateResume(resume);
	}
	
	public void insertUserRequest(UserRequest userRequest) {
		this.controller.insertUserRequest(userRequest);
	}
	
	public boolean canRequestTeaching(Person person){
		return this.controller.canRequestTeaching(person);
	}
	
	public void editPassword(AccessInfo accessInfo, String currentPassword, String newPassword, String newPasswordConfirmation) throws InvalidCurrentPasswordException {
		this.controller.editPassword(accessInfo, currentPassword, newPassword, newPasswordConfirmation);
		
	}
	
	public void integrationSocialNetworks(AccessInfo accessInfo, String twitterLogin, String facebookLogin) throws InvalidSocialCredencialsException{
		this.controller.integrationSocialNetworks(accessInfo, twitterLogin, facebookLogin);
		
	}
	
	public List<Person> getTeachersByCourse(Course c){
		return this.controller.getTeachersByCourse(c);
	}

	
	public Person getPersonFromEmail(String email){
		return this.controller.getAtributesFromEmail(email);
	}
	
	public Person getPersonByID(int id){
		return this.controller.getPersonByID(id);
	}
	
	public Person getPersonByLogin(String login){
		return this.controller.getPersonByLogin(login);
	}
	
	public Person getPersonByUserName(String userName){
		return this.controller.getPersonByUserName(userName);
	}
	
	public List<Keyword> getKeywordsByCourse(Course course) {
		return this.controller.getKeywordsByCourse(course);
	}
	
	public List<Course>[][] classifyCoursesByPage(List<Course>[] toBeClassified, int numberOfPages) {
		return this.controller.classifyCoursesByPage(toBeClassified, numberOfPages);
	}
	
	public Poll getPollByID(int id){
		return this.controller.getPollById(id);
	}
	
	public Homework getHomeworkByID(int id){
		return this.controller.getHomeworkById(id);
	}
	
	public Material getMaterialByID(int id){
		return this.controller.getMaterialById(id);
	}
	public MaterialRequest getMaterialRequestByID(int id){
		return this.controller.getMaterialRequestById(id);
	}
	public Module getModuleById(int id){
		return this.controller.getModuleById(id);
	}
	public Module insertModule(Module module) {
		return this.controller.insertModule(module);
	}
	public AmadeusDroidHistoric insertSocialHistory(AmadeusDroidHistoric historic){
		return this.controller.insertSocialHitory(historic);
	}
	
	public List<AmadeusDroidHistoric> getSocialHistory(){
		return this.controller.getSocialHistory();
	}
	public boolean canRegisterUser(AccessInfo user, Course course){
		return this.controller.canRegisterUser(user, course);
	}
	
	public List<Person> listStudentsByCourse(Course course) {
		return this.controller.listStudentsByCourse(course);
	}
	
	public List<Person> listTeachersByCourse(Course course) {
		return this.controller.getTeachersByCourse(course);
	}
	public void confirmRegistry(AccessInfo accinfo){
		this.controller.confirmRegistry(accinfo);
	}

	public Game updateGame(Game game){
		return this.controller.updateGame(game);
	}

	public Game getGameById(int gameId){
		return this.controller.getGameById(gameId);
	}
	
	public void updatePersonRoleCourse(PersonRoleCourse personRoleCourse) {
		this.controller.updatePersonRoleCourse(personRoleCourse);
	}
	
	public void requestAssistance(Course course, Resume resume, String interest) throws RequestException{
		this.controller.requestAssistance(course, resume, interest);
	}
	
	public boolean isValidationDataToRegisterCourse(Course course) {
		return this.controller.isValidationDataToRegisterCourse(course);
	}

	public List<UserRequest> searchAssistanceRequest(Person person, Course course) {
	       return this.controller.searchAssistanceRequest(person, course);
	}
	
	public List<PersonRoleCourse> getPossibleTeacherOrAssistantsInCourse(
			Person person, Course course) {
			return this.controller.getPossibleTeacherOrAssistantsInCourse(person, course);
	}

	public boolean canAssistanceRequest(Person person, Course course) {
		return this.controller.canAssistanceRequest(person, course);
	}
	
	public List<UserRequest> getPossibleTeachers() {
		return this.controller.getPossibleTeachers();
	}
	
	public List<UserRequest> getPossibleAssistants(AccessInfo accessInfo){
		return this.controller.getPossibleAssistants(accessInfo);
	}
	
	public void deleteUserRequest(UserRequest userRequest){
		this.controller.deleteUserRequest(userRequest);
	}
	
	public void updateUserRequest(UserRequest userRequest) {
		this.controller.updateUserRequest(userRequest);
	}
	
	public void approveTeachingRequest(UserRequest userRequest) throws Exception {
		this.controller.approveTeachingRequest(userRequest);
	}
	
	public void sendMail(List<String> emails, String subject, String message) throws Exception {
		this.controller.sendMail(emails, subject, message);
	}
	
	public void sendMail(String to, String subject, String message) throws Exception {
		this.controller.sendMail(to, subject, message);
	}
	
	public String sendEmailApproveTeachingRequest(String email) throws MessagingException, IOException {
		return this.controller.sendEmailApproveTeachingRequest(email);
	}
	
	public void disapprovedTeachingRequest(UserRequest userRequest) throws Exception {
		this.controller.disapprovedTeachingRequest(userRequest);
	}
	
	public String sendEmailDisapproveTeachingRequest(String email,
			String justification) throws MessagingException, IOException {
		return this.controller.sendEmailDisapproveTeachingRequest(email,
				justification);
	}
	
	public void approveAssistanceRequest(UserRequest userRequest, Course course) throws RequestException {
		this.controller.approveAssistanceRequest(userRequest, course);
	}

	public void disapprovedAssistanceRequest(UserRequest userRequest) throws RequestException {
		this.controller.disapprovedAssistanceRequest(userRequest);
	}
	
	public boolean isStudent(Person person, Course course){
		return this.controller.isStudent(person, course);
	}
	
	public List<PersonRoleCourse> getByRoleInCourse(Person person, Course course, Role role){
		return this.controller.getByRoleInCourse(person, course, role);
	}
	
	public UserRequest searchUserRequestById(int id) {
		return this.controller.searchUserRequestById(id);
	}

	public void sendEmailApproveAssistanceRequest(String email) throws MessagingException, IOException {
		this.controller.sendEmailApproveAssistanceRequest(email);
	}
	
	public void sendEmailDisapproveAssistanceRequest(String email, String justification) throws MessagingException, IOException {
		this.controller.sendEmailDisapproveAssistanceRequest(email, justification);
	}
	
	public List<UserRequest> requestTeacherOfUser(Person person) {
		return this.controller.requestTeacherOfUser(person);
	}

	public void requestTeacher(Resume resume, String interest) throws RequestException{
		this.controller.requestTeacher(resume, interest);
	}
	
	public List<Person> getAssistanceInCourse(Course course) {
		return this.controller.getAssistanceInCourse(course);
	}
		
	public Role getRoleByPersonInCourse(Person person, Course course) throws Exception {
		return this.controller.getRoleByPersonInCourse(person, course);
	}
	
	public void insertForum(Forum forum) {
		this.controller.insertForum(forum);
	}
	
	public Forum updateForum(Forum forum){
		return this.controller.updateForum(forum);
	}

	public Forum getForumById(int forumId){
		return this.controller.getForumById(forumId);
	}
	
	public Message getMessageById(int idMessage){
		return this.controller.getMessageById(idMessage);
	}
	
	public List<br.ufpe.cin.amadeus.amadeus_web.syncronize.Forum> getListForumSyncronize(){
		return this.controller.getListForum();
	}
	
	public List<Message> searchMessageByPaging(int tamanhoBloco, int qtdBloco, Forum forum) {
		return this.controller.searchMessageByPaging(tamanhoBloco, qtdBloco, forum);
	}
	
	public void saveMessage(Message message){
		this.controller.saveMessage(message);
	}
	
	public int getSizeSearchMessageByForum(Forum forum) {
		return this.controller.getSizeSearchMessageByForum(forum);
	}
	
	public boolean isValidationDataToInsertPoll(Course course, Date finalDatePoll) {
		return this.controller.isValidationDataToInsertPoll(course, finalDatePoll);
	}
	
	public boolean isValidationDataToAnswerPoll(Poll poll) {
		return this.controller.isValidationDataToAnswerPoll(poll);
	}
	
	public boolean isValidationDataToAnswerForum(Forum forum, Course course) {
		return this.controller.isValidationDataToAnswerForum(forum, course);
	}
	
	public List<Person> listAssistantsByCourse(Course course){
		return this.controller.listAssistantsByCourse(course);
	}

	public int getNextPositionModule(Course course) {
		return this.controller.getNextPositionModule(course);
	}
		
	public Keyword getKeywordWithIdByName(String name) {
		return this.controller.getKeywordWithIdByName(name);
	}
	
	public String searchString(String oldString){
		return this.controller.searchString(oldString);
	}
	
	public void merge(Object object) {
		this.controller.merge(object);
	}

	public void insertMaterial(Material material) {
		this.controller.insertMaterial(material);
	}
	public void insertMaterialRequest(MaterialRequest materialRequest) {
		this.controller.insertMaterialRequest(materialRequest);
	}

	public void updateMaterialRequest(MaterialRequest materialRequest) {
		this.controller.updateMaterialRequest(materialRequest);		
	}
	
	public Material getMaterial(Person person, MaterialRequest materialRequest) {
		return this.controller.getMaterial(person, materialRequest);
	}

	public void validateMaterial(FormFile archive, String name) throws InvalidMaterialException {
		this.controller.validateMaterial(archive, name);
	}
	
	public void validateVideoStep1(String name, String description) throws InvalidVideoException {
		this.controller.validateVideoStep1(name, description);
	}
	
	public void validateVideoURL(String url) throws InvalidVideoException {
		this.controller.validateVideoURL(url);
	}
	
	public void validateVideoFile(FormFile archive) throws InvalidVideoException {
		this.controller.validateVideoFile(archive);
	}
	
	public Role searchRoleByConstant(RoleType roleType) {
		return this.controller.searchRoleByConstant(roleType);
	}
	
	public void deleteKeywordsOrphan() {
		this.controller.deleteKeywordsOrphan();
	}
	
	public void incrementPopularityKeyword(int courseId, Set<Keyword> keywords) {
		this.controller.incrementPopularityKeyword(courseId, keywords);
	}
	
	public void decrementPopularityKeyword(int courseId, Set<Keyword> keywords) {
		this.controller.decrementPopularityKeyword(courseId, keywords);
	}
	
	public void flush()	{
		this.controller.flush();
	}

	public VideoIriz getVideoByID(int videoId) {
		return this.controller.getVideoByID(videoId);
	}
	
	
	public LearningObject getLearningObjectById(int learningId){
		return this.controller.getLearningObjectById(learningId);
	}
	
	public HistoryLearningObject getHistoryLearningObjectById(int idHistory){
		return this.controller.getHistoryLearningObject(idHistory);
	}
	
	public List<Object> getStudentPendingTasks (Person student){
		return controller.getStudentPendingTasks(student);
	}
	public int getTotalAccessLearningObject(int idLearning){
		return this.controller.getTotalAccessLearningObject(idLearning);
	}
	
	public HistoryLearningObject startSessionHistoryLearningObject (HistoryLearningObject history){
		return this.controller.startSessionHistoryLearningObject(history);
	}
	
	public void insertEvaluation(Evaluation evaluation) {
		this.controller.insertEvaluation(evaluation);
	}
	
	public Evaluation getEvaluationById(int evaluationId) {
		return this.controller.getEvaluationById(evaluationId);
	}
	
	public void insertEvaluationRealized(EvaluationRealized evR) {
		this.controller.insertEvaluationRealized(evR);
	}

	public Question getQuestionById(Evaluation evaluation, int questionId) {
		return this.controller.getQuestionById(evaluation, questionId);
	}
	
	public void registerStudentCourse(Course course, Person person) {
		this.controller.registerStudentCourse(course, person);
	}
	
	public void unregisterStudentCourse(Course course, Person person){
		this.controller.unregisterStudentCourse(course, person);
	}
	
	public String getMostPopularKeywwordsPreparedAsHTML() {
		return this.controller.getMostPopularKeywwordsPreparedAsHTML();
	}
	
	public List<Evaluation> getAllEvaluationFromCourse(Course course) {
		return this.controller.getAllEvaluationFromCourse(course);
	}
	
	public ActionMessages validateQuestionMultiple(QuestionMultiple questionMultipleForValidation) {
		return this.controller.validadeQuestionMultiple(questionMultipleForValidation);
	}
	
	public ActionMessages validateQuestionRealizedDiscursive(QuestionDiscursiveRealized questionDiscursiveRealized) {
		return this.controller.validateQuestionRealizedDiscursive(questionDiscursiveRealized);
	}
	
	public ActionMessages validateQuestionDiscursive(QuestionDiscursive questionDiscursiveForValidation) {
		return this.controller.validateQuestionDiscursive(questionDiscursiveForValidation);
	}
	
	public ActionMessages validateQuestionGap(QuestionGap questionGapForValidation) {
		return this.controller.validateQuestionGap(questionGapForValidation);
	}
	
	public ActionMessages validateQuestionTrueFalse(QuestionTrueFalse questionTrueFalseForValidation) {
		return this.controller.validateQuestionTrueFalse(questionTrueFalseForValidation);
	}
	
	public OpenID getOpenIDByIdentity(String identity) {
		return this.controller.getOpenIDByIdentity(identity);
	}
	
	public OpenID getOpenIDById(int id) {
		return this.controller.getOpenIDById(id);
	}
	
	public List<AccessInfo> getAllUsers() {
		return this.controller.getAllUsers();
	}
	
	public List<String> getAllEmail() {
		return this.controller.getAllEmail();
	}
	
	public List<String> getEmailOfStudens() {
		return this.controller.getEmailOfStudens();
	}
	
	public List<String> getEmailOfProfessors() {
		return this.controller.getEmailOfProfessors();
	}
	
	public List<String> getEmailOfAdmins() {
		return this.controller.getEmailOfAdmins();
	}
	
	public List<String> getEmailUsersOfCourse(int courseId) {
		return this.controller.getEmailUsersOfCourse(courseId);
	}	

	public List<Game> getGamesByCourse(
			int courseID) {
		return this.controller.getGamesByCourse(courseID);
	}
	
	public boolean verifyEmail(int personId, String email){
		return this.controller.verifyEmail(personId, email);
	}
	
	public List<AccessInfo> searchUsers(String userName, Integer userType, Integer courseId) {
		return this.controller.searchUsers(userName, userType, courseId);
	}
	
	public List<AccessInfo> searchUsersByType(Integer userType) {
		return this.controller.searchUsers(userType);
	}
	
	
	//external link
	public ExternalLink getExternalLinkById(int idLink){
		return this.controller.getExternalLinkById(idLink);
	}

	public Person editPerson(Person person) throws Exception {
		return this.controller.editUser(person);
	}
	
	//Log
	public void saveLog(Log log){
		this.controller.saveLog(log);
	}

	public void saveLog(){
		
	}

	public void answerForumActivity(Integer idForum, String message, String login){
		Forum forum = this.getForumById(idForum);
		Message msg = new Message();
		msg.setBody(message);
		Date date = new Date();
		
		AccessInfo user = this.searchUserByLogin(login);

		msg.setDate(date);
		msg.setAuthor(user.getPerson());

		forum.getMessages().add(msg);
		
		if (forum.getMessages().size() < 2) {
			forum.setCreationDate(date);
		}

		this.flush();


	}
	
	public Material findMaterialByID(int material_id) {
		return this.controller.findMaterialById(material_id);
	}
	
	public Log getLog() throws Exception {
		return this.controller.getLog();
	}

	public br.ufpe.cin.amadeus.amadeus_web.syncronize.Message getLastMessage() {
		return this.controller.getLastMessage();
	}

	public String getJSONArrayGameScore(int idGame){
		return this.controller.getJSONArrayGameScore(idGame);
	}
	
	public String getJSONArrayGameScoreVisualizacao(int idGame){
		return this.controller.getJSONArrayGameScoreVisualizacao(idGame);
	}
	
	public String getXmlGameScore(int idGame){
		return this.controller.getXmlGameScore(idGame);
	}
	
	public String getJSONArrayGameLevel(int idGame){
		return this.controller.getJSONArrayGameLevel(idGame);
	}
	
	public String getXmlGameLevel(int idGame){
		return this.controller.getXmlGameLevel(idGame);
	}
	
	public String getJSONArrayModuleGameTimePerDay (int idModule){
		return this.controller.getJSONArrayModuleGameTimePerDay(idModule);
	}
	
	public String getJSONArrayModuleGameTotalTime (int idModule){
		return this.controller.getJSONArrayModuleGameTotalTime(idModule);
	}
	
	public String getJSONArrayGameGrid(int idGame){
		return this.controller.getJSONArrayGameGrid(idGame);
	}
	
	public String getJSONArrayGameGridByUser(int idGame, int idUser) {
		return this.controller.getJSONArrayGameGridByUser(idGame, idUser);
	}
	
	public String getJSONArrayTagCloudForum(int idModule){
		return this.controller.getJSONArrayTagCloudForum(idModule);
	}
	
	public String getJSONArrayPostsPerModule(int idModule){
		return this.controller.getJSONArrayPostsPerModule(idModule);
	}
	
	public String getJSONArraySizeMessagePerModule(int idModule){
		return this.controller.getJSONArraySizeMessagePerModule(idModule);
	}
	
	public String getJSONArrayPersonGameTimePerModule(int idPerson, int idModule){
		return this.controller.getJSONArrayPersonGameTimePerModule(idPerson, idModule);
	}
	
	public String getJSONObjectTempoLevelPontuacao(int idGame)
	{
		return this.controller.getJSONObjectTempoLevelPontuacao(idGame);
	}
	
	public String getJSONObjectTempoQuantidadePartidas(int idGame)
	{
		return this.controller.getJSONObjectTempoQuantidadePartidas(idGame);
	}
	
	public String getJSONObjectQuantidadeTamanhoMSG(int idModule)
	{
		return this.controller.getJSONObjectQuantidadeTamanhoMSG(idModule);
	}
	
	public String getJSONObjectLevelPontuacao(int idGame)
	{
		return this.controller.getJSONObjectLevelPontuacao(idGame);
	}
	
	public String getJSONArrayGameMeta(int idGame){
		return this.controller.getJSONArrayGameMeta(idGame);
	}
	
	public String getJSONArrayPersonTimeOnline(int idPerson){
		return this.controller.getJSONArrayPersonTimeOnline(idPerson);
	}
	
	public String getJSONArrayForumVisualizacao(int idModule, int idAluno){
		return this.controller.getJSONArrayForumVisualizacao(idModule, idAluno);
	}
	public String getJSONArrayPostsPerUser(int idModule, int idUser){
		return this.controller.getJSONArrayPostsPerUser(idModule, idUser);
	}
	public String getJSONArrayMaterialView(int idUsuario, int idModule){
		return this.controller.getJSONArrayMaterialView(idUsuario, idModule);
	}
	public String getJSONArrayPollAnswered(int moduleID, int idAluno){
		return this.controller.getJSONArrayPollAnswered(moduleID, idAluno);
	}
	public String getJSONArrayGameOpen(int idModule, int idAluno){
		return this.controller.getJSONArrayGameOpen(idModule, idAluno);
	}

	/**
	 * Método criado para persistir as mensagens enviadas, como primeira mensagem (não resposta a outra)
	 * Recebe como parâmetro a mensagem, o pessoa que enviou e a pessoa que vai receber a mensagem.
	 * @author Nailson Cunha
	 * @param message
	 * @param from
	 * @param to
	 */
	public MessengerMessage saveMessengerMessage(MessengerMessage message, Person from,
			Person to) {
		return this.controller.saveMessengerMessage(message, from, to);
	}

	/**
	 * Método que retorna a lista de mensagens não ligas de uma determinada pessoa
	 * @author Nailson Cunha
	 * @param person A pessoa que solicita as mensagens não lidas.
	 * @return retorna a lista de mensagens ainda não lidas.
	 */
	public List<MessengerMessage> getAllUnreadByPerson(Person person) {
		return this.controller.getAllUnreadByPerson(person);
	}

	public MessengerMessage getMessengerMessageById(int idMensagem) {
		return this.controller.getMessengerMessageById(idMensagem);
	}

	public MessengerMessage saveOnlyMessage(MessengerMessage message) {
		return this.controller.saveOnlyMessage(message);
	}

	public void deleteMessengerMessage(MessengerMessage message) {
		this.controller.deleteMessengerMessage(message);
	}

	public List<MessengerMessage> getAllMessengerMessageByPerson(Person person) {
		
		return this.controller.getAllMessengerMessageByPerson(person);
	}

	/**
	 * Método que retorna todas as Persons do banco de dados
	 * @return A lista de pessoas.
	 */
	public List<Person> getAllPersons() {
		return this.controller.getAllPersons();
	}

	public int getPersonByTwitterLogin(String screenName) {
		return this.controller.getPersonByTwitterLogin(screenName);
	}

	public List<Tweet> getAllTweets() {
		return this.controller.getAllTweets();
	}

	public List<Tweet> getTweetBetweenDates(Date inicio, Date fim) {
		return this.controller.getTweetBetweenDates(inicio,fim);
	}
	
}