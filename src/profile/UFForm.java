package profile;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class UFForm {
	private boolean mobVisible;
	private ArrayList<String> Courses;
	private ArrayList<String> quals;
    private ArrayList<String> rsrch;
    private ArrayList<String> cProjects;
    
	public void updateCourses(String courses) {
		StringTokenizer tok = new StringTokenizer(courses,",");
		while(tok.hasMoreElements()){
			this.Courses.add((String) tok.nextElement());
		}	
	}
	
	public ArrayList<String> getCourses(){
		return Courses;
	}

	public void updateResearch(String publication) {
          this.rsrch.add(publication);
	}
	
	public ArrayList<String> getResearch(){
		return rsrch;
	}
	
	public void updateQualifications(String qual){
		this.quals.add(qual);
	}
	
	public ArrayList<String> getQualifications(){
		return quals;
	}
	
	public void updateProjects(String Projects){
		this.cProjects.add(Projects);
	}
	
	public ArrayList<String> getProjects(){
		return cProjects;
	}
   
	public void mobVisibility(boolean Visibility){
		this.mobVisible = Visibility;
	}
	
	public boolean ismobVisible(){
		return mobVisible;
	}
}