package spi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import consts.Constants;
import profile.*;
import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.Key;

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

	private boolean checkUser(final User user) {
		displayName = extractID(user.getEmail());
		if (displayName.substring(0, 3).equals("prof")) {
			return true;
		}
		return false;
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

	public void getProfile(final User user) throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("Authentication failed!!");
		}
		if (!checkUser(user)) {
			loadStudentProfile(user);
		} else {
			loadFacultyProfile(user);
		}
	}

	@ApiMethod(name = "saveFacultyProfile", path = "profile", httpMethod = HttpMethod.POST)
	public FacultyProfile saveFacultyProfile(final User user,
			FProfForm fProfileForm) throws UnauthorizedException {

		if (user == null) {
			throw new UnauthorizedException(
					"Authentication Required for logging in");
		}

		mainEmail = user.getEmail();
		genId = user.getUserId();
		userId = extractID(mainEmail);

		FacultyProfile fp = new FacultyProfile(mainEmail,genId,userId);
		// saves faculty profile object in datastore
		ofy().save().entity(fp).now();
		return fp;

	}

	@ApiMethod(name = "saveStudentProfile", httpMethod = HttpMethod.POST)
	public StudentProfile saveStudentProfile(User user)
			throws UnauthorizedException {

		if (user == null) {
			throw new UnauthorizedException(
					"Authentication Required for logging in");
		}

		mainEmail = user.getEmail();
		genId = user.getUserId();
		userId = extractID(mainEmail);

		StudentProfile sp = (StudentProfile) ofy().load()
				.key(Key.create(StudentProfile.class, genId)).get();
		if (sp == null) {
			sp = new StudentProfile(mainEmail, genId, userId);
		
		} else {

		}
		
		// saves student profile object in datastore
		ofy().save().entity(sp).now();
		return sp;
	}
	
	
}
