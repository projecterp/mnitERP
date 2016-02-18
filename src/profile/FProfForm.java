package profile;

import java.util.ArrayList;
import java.util.StringTokenizer;

import profile.ProfEnums.Branch;
import profile.ProfEnums.Gender;

public class FProfForm {
	
	private String name;
	private String email;  //other than institute email
	private String profID; // Not visible to other people
	private String officeNo;
	private String mobileNo; // Visibility depends on professor
	private boolean mobVisible;
	private Gender gen;
	private Branch dept;
	private String qual;
	private String courses;
	private String Projects;
	private String publications;
	private ArrayList<String> Q;
	private ArrayList<String> Course;
	private ArrayList<String> Projs;
	private ArrayList<String> pubs;
	
	public FProfForm(String name,String email,String officeNo,String mobileNo,boolean mobVisible,Gender gen,Branch dept){
		this.setName(name);
		this.setEmail(email);
		this.setOfficeNo(officeNo);
		this.setMobileNo(mobileNo);
		this.setMobVisible(mobVisible);
		this.setGen(gen);
		this.setDept(dept);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getQual() {
		return Q;
	}

	public void setQual(String qual) {
		this.qual = qual;
		StringTokenizer tok = new StringTokenizer(this.qual,",");
		while(tok.hasMoreElements()){
			Q.add((String) tok.nextElement());
		}	
	}

	public ArrayList<String> getCourses() {
		return Course;
	}

	public void setCourses(String courses) {
		this.courses = courses;
		StringTokenizer tok = new StringTokenizer(this.courses,",");
		while(tok.hasMoreElements()){
			Course.add((String) tok.nextElement());
		}	
	}

	public ArrayList<String> getProjects() {
		return Projs;
	}

	public void setProjects(String projects) {
		this.Projects = projects;
		StringTokenizer tok = new StringTokenizer(this.Projects,",");
		while(tok.hasMoreElements()){
			Projs.add((String) tok.nextElement());
		}	
	}

	public ArrayList<String> getPublications() {
		return pubs;
	}

	public void setPublications(String publications) {
		this.publications = publications;
		StringTokenizer tok = new StringTokenizer(this.publications,";");
		while(tok.hasMoreElements()){
			pubs.add((String) tok.nextElement());
		}	
	}
}
