package service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import profile.FacultyProfile;
import profile.StudentProfile;
import group.Group;
public class OfyService {

	// entity registration. 
    static {
    	try{
        factory().register(FacultyProfile.class);
        factory().register(StudentProfile.class);
        factory().register(Group.class);
    	}
    	catch(java.lang.NoClassDefFoundError e){
    		e.printStackTrace();
    	}
    }
    //@return Objectify service object.
    
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    // @return ObjectifyFactory.
  
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
    
    
}
