package spi;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.users.UserService;


@SuppressWarnings("serial")
public class Dashboard extends HttpServlet {
  UserService userService = UserServiceFactory.getUserService();
  User user = userService.getCurrentUser(); 
  public void doGet(HttpServletRequest req,HttpServletResponse res){
	  doPost(req,res);
  }
  public void doPost(HttpServletRequest req,HttpServletResponse res){
	  req.getParameter("");
	  
	  
  }
	
}
