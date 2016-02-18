package profile;

import profile.ProfEnums.Gender;
import profile.ProfEnums.Branch;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import java.util.ArrayList;
import com.google.appengine.api.blobstore.BlobKey;
@Entity(name = "Faculty")
public class FacultyProfile {
	
	// profile picture  --> using blobstore.
	public BlobKey pic;
	// Basic fields
	@Id
	String FacultyID;
	public String name;
	public String instiEmail;
	private String email;
	private String profID; // Not visible to other people
	private String officeNo;
	private String mobileNo; // Visibility depends on professor
	private boolean mobVisible;
	private Gender gen;
	private Branch dept;
	
  	// Academic Information
	private ArrayList<String> quals;
    private ArrayList<String> rsrch;
    private ArrayList<String> cProjects;

    private FacultyProfile() {}
    
	public FacultyProfile(String instiEmail, String FacultyID, String profID) {
		// constructor stub
		this.instiEmail = instiEmail;
		this.FacultyID = FacultyID;
		this.setProfID(profID);
		this.setQuals(new ArrayList<String>());
		this.setRsrch(new ArrayList<String>());
		this.setcProjects(new ArrayList<String>());
	}

	// setter stubs for FacultyProfile

	public void updateCourses() {
       
	}

	public void updateResearch() {

	}
	
	public void updateQualifications(){
		
	}
	
	public void updateProjects(){
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfID() {
		return profID;
	}

	public void setProfID(String profID) {
		this.profID = profID;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public boolean isMobVisible() {
		return mobVisible;
	}

	public void setMobVisible(boolean mobVisible) {
		this.mobVisible = mobVisible;
	}

	public Gender getGen() {
		return gen;
	}

	public void setGen(Gender gen) {
		this.gen = gen;
	}

	public Branch getDept() {
		return dept;
	}

	public void setDept(Branch dept) {
		this.dept = dept;
	}

	public ArrayList<String> getQuals() {
		return quals;
	}

	public void setQuals(ArrayList<String> quals) {
		this.quals = quals;
	}

	public ArrayList<String> getRsrch() {
		return rsrch;
	}

	public void setRsrch(ArrayList<String> rsrch) {
		this.rsrch = rsrch;
	}

	public ArrayList<String> getcProjects() {
		return cProjects;
	}

	public void setcProjects(ArrayList<String> cProjects) {
		this.cProjects = cProjects;
	}

}
