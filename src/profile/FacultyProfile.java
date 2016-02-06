package profile;

import profile.ProfEnums.Gender;
import profile.ProfEnums.Branch;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity(name = "Faculty")
public class FacultyProfile {
	// profile picture

	// Basic fields
	@Id
	String FacultyID;
	public String name;
	public String instiEmail;
	public String email;
	public String profID; // Not visible to other people
	public String officeNo;
	public String mobileNo; // Visibility depends on professor
	public Gender gen;
	public Branch dept;

	// Academic Information
	/**********************************************
	 * public StringBuilder qual = new StringBuilder(); public String quals =
	 * qual.toString(); public StringBuilder rsrch = new StringBuilder(); public
	 * String research = rsrch.toString(); public StringBuilder cprojs = new
	 * StringBuilder(); public String Cprojects = cprojs.toString(); public
	 * StringBuilder sCourses = new StringBuilder(); public String Courses=
	 * sCourses.toString();
	 ************************************************/

	public FacultyProfile(String instiEmail, String FacultyID, String profID) {
		// constructor stub
		this.instiEmail = instiEmail;
		this.FacultyID = FacultyID;
		this.profID = profID;
	}

	// setter stubs for FacultyProfile

	public void setQualifications() {

	}

	public void setProjects() {

	}

	public void setCourss() {

	}

	public void removeCourses() {

	}

	public void addPublications() {

	}

}
