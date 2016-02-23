package spi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.google.api.server.spi.response.ConflictException;
import consts.Constants;
import profile.*;
import profile.ProfEnums.Status;
import group.Group;
import static service.OfyService.ofy;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
//import static service.OfyService.factory;

//import com.googlecode.objectify.Objectify;

@Api(name = "mniterp", version = "v1", scopes = { Constants.EMAIL_SCOPE }, clientIds = {
		Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID }, description = "API for the MNIT ERP Backend application.")
public class ErpEndPoints {

	private String mainEmail;
	private String genId;
	private String userId;
	private String displayName;

	private static String extractID(String email) {
		return email == null ? null : email.substring(0, email.indexOf("@"));
	}

	@ApiMethod(name = "loadStudentProfile", path = "loadStudent", httpMethod = HttpMethod.GET)
	public StudentProfile loadStudentProfile(final User user) {
		Key<StudentProfile> key = Key.create(StudentProfile.class,
				user.getUserId());
		StudentProfile sp = (StudentProfile) ofy().load().key(key).get();
		return sp;
	}

	@ApiMethod(name = "loadFacultyProfile", path = "loadFaculty", httpMethod = HttpMethod.GET)
	public FacultyProfile loadFacultyProfile(final User user) {
		Key<FacultyProfile> key = Key.create(FacultyProfile.class,
				user.getUserId());
		FacultyProfile fp = (FacultyProfile) ofy().load().key(key).get();
		return fp;
	}

	@ApiMethod(name = "checkUser", path = "user", httpMethod = HttpMethod.GET)
	private boolean checkUser(final User user) {
		displayName = extractID(user.getEmail());
		boolean prof;
		if (displayName.substring(0, 3).equals("prof")) {
			prof = true;
			return prof;
		}
		prof = false;
		return prof;
	}

	@ApiMethod(name = "saveFacultyProfile", path = "profile", httpMethod = HttpMethod.POST)
	public FacultyProfile saveFacultyProfile(FProfForm fProfForm,
			final User user) throws UnauthorizedException {

		if (user == null) {
			throw new UnauthorizedException(
					"Authentication Required for logging in");
		}

		mainEmail = user.getEmail();
		genId = user.getUserId();
		userId = extractID(mainEmail);

		FacultyProfile fp = new FacultyProfile(mainEmail, genId, userId);
		fp.setQuals(fProfForm.getQual());
		fp.setcProjects(fProfForm.getProjects());
		fp.setDept(fProfForm.getDept());
		fp.setGen(fProfForm.getGen());
		fp.setMobileNo(fProfForm.getMobileNo());
		fp.setRsrch(fProfForm.getPublications());
		fp.setOfficeNo(fProfForm.getOfficeNo());
		fp.setEmail(fProfForm.getEmail());
		fp.setName(fProfForm.getName());
		fp.setMobVisible(fProfForm.isMobVisible());
		fp.setCourses(fProfForm.getCourses());
		// saves faculty profile object in data-store
		ofy().save().entity(fp).now();
		return fp;
	}

	@ApiMethod(name = "updateFacultyProfile", httpMethod = HttpMethod.POST, path = "update")
	public FacultyProfile updateFacultyProfile(final User user, UFForm sForm) {
		FacultyProfile fp = loadFacultyProfile(user);
		fp.setCourses(sForm.getCourses());
		fp.setcProjects(sForm.getProjects());
		fp.setQuals(sForm.getQualifications());
		fp.setRsrch(sForm.getResearch());
		return fp;
	}

	@ApiMethod(name = "saveStudentProfile", httpMethod = HttpMethod.POST, path = "student")
	public StudentProfile saveStudentProfile(SProfileForm sProfileForm,
			User user) throws UnauthorizedException, ParseException {

		if (user == null) {
			throw new UnauthorizedException(
					"Authentication Required for logging in");
		}

		mainEmail = user.getEmail();
		genId = user.getUserId();
		userId = extractID(mainEmail);
		StudentProfile sp;
		sp = new StudentProfile(mainEmail, genId, userId);
		sp.setAadhar_no(sProfileForm.getAadhar_no());
		sp.setAcNo(sProfileForm.getAcNo());
		sp.setAdm(sProfileForm.getAdm());
		sp.setBankName(sProfileForm.getBankName());
		sp.setbGroup(sProfileForm.getbGroup());
		sp.setBr(sProfileForm.getBr());
		sp.setCat(sProfileForm.getCat());
		sp.setDeg(sProfileForm.getDeg());
		sp.setDob(sProfileForm.getDob()); // can throw parsing exception
		sp.setEmail(sProfileForm.getEmail());
		sp.setfName(sProfileForm.getfName());
		sp.setGen(sProfileForm.getGen());
		sp.setHos(sProfileForm.getHos());
		sp.setIFSCcode(sProfileForm.getIFSCcode());
		sp.setMarital_status(sProfileForm.getMarital_status());
		sp.setmName(sProfileForm.getmName());
		sp.setMobileNo(sProfileForm.getMobileNo());
		sp.setName(sProfileForm.getfName());
		sp.setPwd(sProfileForm.isPwd());
		sp.setsAcNo(sProfileForm.getsAcNo());
		sp.setsBankName(sProfileForm.getsBankName());
		sp.setsIFSCcode(sProfileForm.getsIFSCcode());
		sp.setSpecialization(sProfileForm.getSpecialization());
		// sp.setYear_adm(sp.getCollegeID().substring(0,3));
		sp.setState(Status.ACTIVE);
		sp.setSame(sProfileForm.isSame());
		sp.setPAddress(sProfileForm.getPAddress());
		sp.setPcity(sProfileForm.getPcity());
		sp.setPstate(sProfileForm.getPstate());
		sp.setPcountry(sProfileForm.getPcountry());
		sp.setPzip(sProfileForm.getPzip());
		if (!sp.isSame()) {
			sp.setCAddress(sProfileForm.getCAddress());
			sp.setCcity(sProfileForm.getCcity());
			sp.setCstate(sProfileForm.getCstate());
			sp.setCcountry(sProfileForm.getCcountry());
			sp.setCzip(sProfileForm.getCzip());
		}
		// saves student profile object in data-store
		ofy().save().entity(sp).now();
		return sp;
	}

	@ApiMethod(name = "updateStudentProfile", httpMethod = HttpMethod.POST, path = "update")
	public StudentProfile updateStudentProfile(final User user, USForm sForm) {
		StudentProfile sp = loadStudentProfile(user);
		sp.setAcNo(sForm.getAcNo());
		sp.setsAcNo(sForm.getsAcNo());
		sp.setMobileNo(sForm.getMobileNo());
		sp.setEmail(sForm.getEmail());
		sp.setBankName(sForm.getBankName());
		sp.setsBankName(sForm.getsBankName());
		sp.setIFSCcode(sForm.getIFSCcode());
		sp.setsIFSCcode(sForm.getsIFSCcode());
		return sp;
	}

	@ApiMethod(name = "searchFacultyProfile", httpMethod = HttpMethod.POST)
	public List<FacultyProfile> searchfProfile(String QueryItem, String filter) {
		List<FacultyProfile> profiles;
		/*
		 * profiles = ofy().load().type(FacultyProfile.class) .filter("name >=",
		 * QueryItem).list(); profiles = ofy().load().type(FacultyProfile.class)
		 * .filter("name <", QueryItem + "\ufffd").list();
		 */
		Query<FacultyProfile> q = ofy().load().type(FacultyProfile.class);
		q = q.filter("name >=", QueryItem);
		q = q.filter("name <", QueryItem + "\ufffd");
		profiles = q.list();
		return profiles;
	}

	@ApiMethod(name = "searchsProfile", httpMethod = HttpMethod.POST)
	public List<StudentProfile> searchsProfile(String QueryItem, String filter) {
		List<StudentProfile> profiles;
		Query<StudentProfile> q = ofy().load().type(StudentProfile.class);
		q = q.filter("name >=", QueryItem);
		q = q.filter("name <", QueryItem + "\ufffd");
		profiles = q.list();
		return profiles;
	}

	@ApiMethod(name = "createGroup", httpMethod = HttpMethod.POST)
	public Group createGroup(String name, User user,
			Group.visibilityState visibility) throws ConflictException {
		Key<Group> Gkey = Key.create(Group.class, name);
		// check if group name exists
		Query<Group> q = ofy().load().type(Group.class);
		q = q.filter("name", name);
		if (q.list().size() == 0) {

		} else {
			throw new ConflictException("Group name not available");
		}
		/*
		 * boolean prof = checkUser(user); if(prof){ Key<FacultyProfile> key =
		 * Key.create(FacultyProfile.class, user.getUserId()); Gkey =
		 * factory().allocateId(key, Group.class); } else{ Key<StudentProfile>
		 * key = Key.create(StudentProfile.class, user.getUserId()); Gkey =
		 * factory().allocateId(key, Group.class); }
		 */
		Long prop = Gkey.getId();
		Group g = new Group(name, prop, user.getUserId(), visibility);
		return g;
	}

	@ApiMethod(name = "searchGroup", httpMethod = HttpMethod.POST)
	public List<Group> searchGroup(String name) {
		List<Group> groups;
		Query<Group> q = ofy().load().type(Group.class);
		q = q.filter("name >=", name);
		q = q.filter("name <", name + "\ufffd");
		groups = q.list();
		return groups;
	}

	@ApiMethod(name = "addPeopleToGroup", httpMethod = HttpMethod.POST)
	public void addMemberstoGroup(Long id, ArrayList<String> mem, User user)
			throws RuntimeException {
		Group g;
		List<Group> gr = ofy().load().type(Group.class).filter("id", id).list();
		if (gr.size() > 1)
			throw new RuntimeException();
		for (String member : mem) {
			g.addMembers(member);
		}

	}
}