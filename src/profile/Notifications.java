package profile;

public class Notifications {

	private String Origin;
	private String time;
	private String handle; // Nature of origin - Group post - @1/event @2/general posts @3
	
 //   private boolean handlerGroup;
	public Notifications(String time, String handle, String Origin) {
     this.time = time;
     this.handle = handle;
     this.Origin = Origin;
	}
	
	public Notifications retrieveNotification(){
		return null;
	}

}
