package spi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import profile.*;
import consts.Constants;
import com.googlecode.objectify.*;

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

		FacultyProfile fp = new FacultyProfile();
		// TODO 3 (In Lesson 3)
		// Save the Profile entity in the data store

		// Return the profile
		return fp;

	}

	@ApiMethod(name = "saveStudentProfile", httpMethod = HttpMethod.POST)
	public StudentProfile saveStudentProfile(User user) throws UnauthorizedException{
		
		if (user == null) {
			throw new UnauthorizedException(
					"Authentication Required for logging in");
		}
		
		mainEmail = user.getEmail();
		genId = user.getUserId();
		userId = extractID(mainEmail);
		

		StudentProfile sp = new StudentProfile();
		return sp;
	}
}
