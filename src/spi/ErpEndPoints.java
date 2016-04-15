package spi;

//import java.io.IOException;
//import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import javax.servlet.http.HttpServletResponse;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
//import com.google.appengine.api.blobstore.BlobKey;
//import com.google.appengine.api.blobstore.BlobstoreService;
//import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
//import com.google.appengine.api.images.ImagesService;
//import com.google.appengine.api.images.ImagesServiceFactory;
//import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.api.users.User;
//import com.google.appengine.labs.repackaged.org.json.JSONException;
//import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.api.server.spi.response.ConflictException;
import consts.Constants;
import profile.*;
import profile.ProfEnums.Status;
import group.Group;
import group.Post;
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

	@SuppressWarnings("deprecation")
	private static String generateDate() {
		Date date = new Date();
		int dd = date.getDate();
		int mm = date.getMonth() + 1;
		int yyyy = date.getYear() + 1900;
		int hours = date.getHours();
		int mins = date.getMinutes();
		int secs = date.getSeconds();
		String time = dd + "-" + mm + "-" + yyyy + " " + hours + ":" + mins
				+ ":" + secs;
		return time;

	}

	private static String extractID(String email) {
		return email == null ? null : email.substring(0, email.indexOf("@"));
	}

	@ApiMethod(name = "loadStudentProfile", httpMethod = HttpMethod.GET)
	public StudentProfile loadStudentProfile(final User user) {
		Key<StudentProfile> key = Key.create(StudentProfile.class,
				user.getUserId());
		StudentProfile sp = (StudentProfile) ofy().load().key(key).get();
		return sp;
	}

	public StudentProfile loadStudentById(@Named("id")String id) {
		Key<StudentProfile> key = Key.create(StudentProfile.class, id);
		StudentProfile sp = (StudentProfile) ofy().load().key(key).get();
		return sp;
	}

	@ApiMethod(name = "loadFacultyProfile", httpMethod = HttpMethod.GET)
	public FacultyProfile loadFacultyProfile(final User user) {
		Key<FacultyProfile> key = Key.create(FacultyProfile.class,
				user.getUserId());
		FacultyProfile fp = (FacultyProfile) ofy().load().key(key).get();
		return fp;
	}

	public FacultyProfile loadFacultyById(@Named("id") String id) {
		Key<FacultyProfile> key = Key.create(FacultyProfile.class, id);
		FacultyProfile fp = (FacultyProfile) ofy().load().key(key).get();
		return fp;
	}

	@ApiMethod(name = "checkUser", httpMethod = HttpMethod.GET)
	private ArrayList<Boolean> checkUser(final User user) {
		displayName = extractID(user.getEmail());
		boolean prof;
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		if (displayName.substring(0, 3).equals("prof")) {
			prof = true;
			list.add(prof);
			return list;
		}
		prof = false;
		list.add(prof);
		return list;
	}

	public ArrayList<Boolean> checkUserById(@Named("id")String id) {
		boolean prof;
		ArrayList<Boolean> list = new ArrayList<Boolean>();
		Key<FacultyProfile> key = Key.create(FacultyProfile.class, id);
		FacultyProfile fp = (FacultyProfile) ofy().load().key(key).get();
		if (fp != null)
			prof = true;
		else
			prof = false;
		list.add(prof);
		return list;
	}

	@ApiMethod(name = "saveFacultyProfile", httpMethod = HttpMethod.POST)
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

	@ApiMethod(name = "updateFacultyProfile", httpMethod = HttpMethod.POST)
	public FacultyProfile updateFacultyProfile(final User user, UFForm sForm) {
		FacultyProfile fp = loadFacultyProfile(user);
		fp.setCourses(sForm.getCourses());
		fp.setcProjects(sForm.getProjects());
		fp.setQuals(sForm.getQualifications());
		fp.setRsrch(sForm.getResearch());
		return fp;
	}

	@ApiMethod(name = "saveStudentProfile", httpMethod = HttpMethod.POST)
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
		sp = new StudentProfile(genId,userId,mainEmail);
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

	@ApiMethod(name = "updateStudentProfile", httpMethod = HttpMethod.POST)
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
	
	@ApiMethod(name ="uploadImage")
	public void uploadImage(@Named("key")String ImageUrl,User user){
		boolean prof = checkUserById(user.getUserId()).get(0);
		if(prof){
			FacultyProfile fp = loadFacultyById(user.getUserId());
			fp.setPic(ImageUrl);
		}
		else{
			StudentProfile sp = loadStudentById(user.getUserId());
			sp.setPic(ImageUrl);
		}
	}
	

	@ApiMethod(name = "searchFacultyProfile", httpMethod = HttpMethod.POST)
	public List<FacultyProfile> searchfProfile(
			@Named("QueryString") String QueryItem,
			@Named("QueryFilter") String filter) {
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
	public List<StudentProfile> searchsProfile(
			@Named("QueryString") String QueryItem,
			@Named("QueryFilter") String filter) {
		List<StudentProfile> profiles;
		Query<StudentProfile> q = ofy().load().type(StudentProfile.class);
		q = q.filter("name >=", QueryItem);
		q = q.filter("name <", QueryItem + "\ufffd");
		profiles = q.list();
		return profiles;
	}

	// Group Endpoint Methods

	@ApiMethod(name = "createGroup", httpMethod = HttpMethod.POST)
	public Group createGroup(@Named("groupName") String name, User user,
			@Named("visibility") Group.visibilityState visibility)
			throws ConflictException {
		Key<Group> Gkey = Key.create(Group.class, name);
		// check if group name exists
		Query<Group> q = ofy().load().type(Group.class);
		q = q.filter("name", name);
		if (q.list().size() != 0) {
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
		// save the group entity to data-store
		ofy().save().entity(g).now();
		return g;
	}

	@ApiMethod(name = "searchGroup", httpMethod = HttpMethod.POST)
	public List<Group> searchGroup(@Named("groupName") String name) {
		List<Group> groups;
		Query<Group> q = ofy().load().type(Group.class);
		q = q.filter("name >=", name);
		q = q.filter("name <", name + "\ufffd");
		groups = q.list();
		return groups;
	}

	public List<Group> getGroupById(@Named("id") Long id)
			throws RuntimeException {
		List<Group> gr = ofy().load().type(Group.class).filter("id", id).list();
		if (gr.size() > 1) {// since there can be no more than one group with a
							// single ID
			throw new RuntimeException();
		} else {
			return gr;
		}
	}

	@ApiMethod(name = "addPeopleToGroup", httpMethod = HttpMethod.POST)
	public void addMemberstoGroup(@Named("id") Long id,
			@Named("members") ArrayList<String> mem, User user) // mem -
																// ArrayList of
																// user IDs
			throws RuntimeException {
		boolean prof;
		StudentProfile sp;
		FacultyProfile fp;
		List<Group> gr = getGroupById(id);
		Group g = gr.get(0);
		for (String member : mem) {
			g.addMembers(member);
			prof = checkUserById(member).get(0);
			if (!prof) {
				sp = loadStudentById(member);
				sp.getGroups().add(g);
			} else {
				fp = loadFacultyById(member);
				fp.getGroups().add(g);
			}
		}
	}
	
	/*
	public void generateImageServingUrl(BlobKey key){
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
        ServingUrlOptions servingOptions = ServingUrlOptions.Builder.withBlobKey(key);
        String servingUrl = imagesService.getServingUrl(servingOptions);	
	}
	
	public static void setJSONResponse(BlobKey key,HttpServletResponse res) throws IOException{
        ImagesService imagesService = ImagesServiceFactory.getImagesService();
        ServingUrlOptions servingOptions = ServingUrlOptions.Builder.withBlobKey(key);
        String servingUrl = imagesService.getServingUrl(servingOptions);
        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setContentType("application/json");
        JSONObject json = new JSONObject();
        try {
            json.put("imageUrl", servingUrl);
            json.put("blobKey",  key.getKeyString());
        } catch (JSONException e){

            e.printStackTrace();            
        }
        PrintWriter out = res.getWriter();
        out.print(json.toString());
        out.flush();
        out.close();
	}
  */
	
	@ApiMethod(name = "addPostToGroupWithoutFiles", httpMethod = HttpMethod.POST)
	public void addPostToGroup(@Named("id") Long id,
			@Named("postContent") String content, User user) {
		List<Group> gr = getGroupById(id);
		Group g = gr.get(0);
		Notifications notif;
		String date = generateDate();
		Post post = new Post(content, user.getUserId(), date);
		g.getGroupPosts().add(post);
		
		if (checkUserById(user.getUserId()).get(0)) {
			StudentProfile sp = loadStudentById(user.getUserId());
			notif = new Notifications(date,"@1"+g.getName(),sp.getName());
		} else {
			FacultyProfile fp = loadFacultyById(user.getUserId());
			notif = new Notifications(date,"@1"+g.getName(),fp.getName());
		}
		
	  for(String member : g.getMembers()){
		  if (checkUserById(member).get(0)){
				StudentProfile sp = loadStudentById(user.getUserId());
		        sp.getNotifs().add(notif);
			} else {
				FacultyProfile fp = loadFacultyById(user.getUserId());
				fp.getNotifs().add(notif);
			}  
	  }  
		
		
	}
	
	@ApiMethod(name = "addPostToGroupWithFiles", httpMethod = HttpMethod.POST)
	public void addPostToGroupWFile(@Named("id") Long id,
			@Named("postContent") String content, User user, @Named("media") String media) {
		List<Group> gr = getGroupById(id);
		Group g = gr.get(0);
		Notifications notif;
		String date = generateDate();
		Post post = new Post(content, user.getUserId(), date,media);
		g.getGroupPosts().add(post);
		
		if (checkUserById(user.getUserId()).get(0)) {
			StudentProfile sp = loadStudentById(user.getUserId());
			notif = new Notifications(date,"@1"+g.getName(),sp.getName());
		} else {
			FacultyProfile fp = loadFacultyById(user.getUserId());
			notif = new Notifications(date,"@1"+g.getName(),fp.getName());
		}
		
	  for(String member : g.getMembers()){
		  if (checkUserById(member).get(0)){
				StudentProfile sp = loadStudentById(user.getUserId());
		        sp.getNotifs().add(notif);
			} else {
				FacultyProfile fp = loadFacultyById(user.getUserId());
				fp.getNotifs().add(notif);
			}  
	  }  
	}

	// @ApiMethod(name="notifyUser", httpMethod = HttpMethod.POST)
	public void notifyUser(@Named("value") int id,User user) {
		 if (checkUserById(user.getUserId()).get(0)){
				StudentProfile sp = loadStudentById(user.getUserId());
		        
			} else {
				FacultyProfile fp = loadFacultyById(user.getUserId());
			
			}  
	}
	
	@ApiMethod(name="getAllNotifications", httpMethod = HttpMethod.GET)
	public ArrayList<Notifications> getAllNotifications(User user){
		ArrayList<Notifications> notifs;
		 if (checkUserById(user.getUserId()).get(0)){
				StudentProfile sp = loadStudentById(user.getUserId());
		        notifs = sp.getNotifs();
			} else {
				FacultyProfile fp = loadFacultyById(user.getUserId());
				notifs = fp.getNotifs();
			}  
		return notifs;
	}
}