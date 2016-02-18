package spi;

import java.text.ParseException;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;

import consts.Constants;
import profile.*;
import profile.ProfEnums.Status;
import static service.OfyService.factory;
import static service.OfyService.ofy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

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
		if (displayName.substring(0, 3).equals("prof")) {
			return true;
		}
		return false;
	}
	
/*	public void getProfile(final User user) throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("Authentication failed!!");
		}
		if (!checkUser(user)) {
			StudentProfile sp = loadStudentProfile(user);
			//return sp;
		} else {
			FacultyProfile fp = loadFacultyProfile(user);
	
		}
	}
*/
	@ApiMethod(name = "saveFacultyProfile", path = "profile", httpMethod = HttpMethod.POST)
	public FacultyProfile saveFacultyProfile(FProfForm fProfForm,final User user)
		 throws UnauthorizedException {
		
		if (user == null) {
			throw new UnauthorizedException(
					"Authentication Required for logging in");
		}

		mainEmail = user.getEmail();
		genId = user.getUserId();
		userId = extractID(mainEmail);

		FacultyProfile fp = new FacultyProfile(mainEmail,genId,userId);
		fp.setQuals(fProfForm.getQual());
		fp.setcProjects(fProfForm.getProjects());;
		fp.setDept(fProfForm.getDept());
		fp.setGen(fProfForm.getGen());
		fp.setMobileNo(fProfForm.getMobileNo());
		fp.setRsrch(fProfForm.getPublications());
		fp.setOfficeNo(fProfForm.getOfficeNo());
		fp.setEmail(fProfForm.getEmail());
		
		// saves faculty profile object in data-store
		ofy().save().entity(fp).now();
		return fp;

	}
	
	@ApiMethod(name = "updateStudentProfile", httpMethod = HttpMethod.POST, path="update")
    public StudentProfile updateStudentProfile(final User user,USForm sForm){
		StudentProfile sp = loadStudentProfile(user);
		
		return sp;
		
	}
    
    
    
	@ApiMethod(name = "saveStudentProfile", httpMethod = HttpMethod.POST, path="student")
	public StudentProfile saveStudentProfile(SProfileForm sProfileForm,User user)
			throws UnauthorizedException, ParseException {

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
		    sp.setDob(sProfileForm.getDob());  // can throw parsing exception
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
		    //sp.setYear_adm(sp.getCollegeID().substring(0,3));
		    sp.setState(Status.ACTIVE);
		    sp.setSame(sProfileForm.isSame());
			sp.setPAddress(sProfileForm.getPAddress());
	    	sp.setPcity(sProfileForm.getPcity());
	    	sp.setPstate(sProfileForm.getPstate());
	    	sp.setPcountry(sProfileForm.getPcountry());
	    	sp.setPzip(sProfileForm.getPzip());
		    if(!sp.isSame()){
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
	
}
