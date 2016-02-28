package group;

public class Post {
	private String content;
	private String postedBy;
	private String dateTime;
	private String dataLink;
	
   //Blob content to be added later on
	public Post(String content, String postedBy,String dateTime){
		this.setContent(content);
		this.setPostedBy(postedBy);
		this.setDateTime(dateTime);
	}
	
	public Post(String content, String postedBy,String dateTime,String dataLink){
		this.setContent(content);
		this.setPostedBy(postedBy);
		this.setDateTime(dateTime);
		this.setDataLink(dataLink);
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDataLink() {
		return dataLink;
	}

	public void setDataLink(String dataLink) {
		this.dataLink = dataLink;
	}	
}