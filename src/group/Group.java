package group;

import java.util.ArrayList;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity(name="Group")
public class Group {
@Id
private Long id;
private String name;
public enum visibilityState{
	PUBLIC,
	CLOSED,
	SECRET
};	
private visibilityState visibility;
private ArrayList<String> admins;
private ArrayList<String> members;     //GroupID
private ArrayList<Post> groupPosts;
private Group() {}	

public Group(String name, Long prop,String adminID,visibilityState visibility){
	this.setName(name);
	this.id = prop;
	this.admins.add(adminID);
    this.setVisibility(visibility);
}

 public ArrayList<String> getMembers(){
	 return members;
 }
 

 public void setAdmins(String adminID){
	 
 }
 
 public void removeAdmins(String name){
	 
	 
 }
 
 public void addMembers(String user){
	 members.add(user);
 }

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public visibilityState getVisibility() {
	return visibility;
}

public void setVisibility(visibilityState visibility) {
	this.visibility = visibility;
}

public ArrayList<Post> getGroupPosts() {
	return groupPosts;
}
 

}
