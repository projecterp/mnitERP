package profile;

import group.Group;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import profile.ProfEnums.*;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
//import com.google.appengine.api.blobstore.BlobKey;

@Entity(name="Student")
public class StudentProfile {
	
 //Profile Fields - a blob key 
	
	//Basic fields
	@Id
	String identifier;
	@Index
	private String name ;   //non-update-able
	private String instiEmail; //non-update-able
	private String email; //non-update-able
	@Index
	private String collegeID; //non-update-able
	private String mobileNo;
	@Index
	private Gender gen;  //non-update-able
	
	// Personal Information
	
	private Marital Marital_status;  
	private BGroup bGroup;                //non-update-able
	private String datePattern = "dd-MM-yyyy";
	private final SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
	@Index
    private String dob; //non-update-able
    private String mName;           
    private String fName;
    @Index
    private Category cat;          //non-update-able
    @Index
    private boolean pwd;      //non-update-able
    
    
    //Academic Information
    @Index
    private Hosteller hos;
    @Index
    private Admission adm;        //non-update-able
    @Index
    private Branch br; 
    @Index
    private Degree deg;          //non-update-able  
    private duration time;     //non-update-able
    private String year_adm; //year of admission
    @Index
    private Status state;     
    private String Specialization;
   
    // Address fields
    
    private String PAddress;   //non-update-able
    private String CAddress;
    private String Aadhar_no ; //non-update-able
    @Index
    private String Pstate;    //non-update-able
    private String Cstate;
    private String Pcity;  //non-update-able
    private String Ccity;
    private String Pzip;  //non-update-able
    private String Czip;
    private String Pcountry; //non-update-able
    private String Ccountry;
    private boolean same;
    //Personal Bank a/c details
    
    private String BankName;
    private String AcNo;
    private String IFSCcode;
	
    //Scholarship bank a/c details
    
    private String sBankName;
    private String sAcNo;
    private String sIFSCcode;
	//Profile picture
    private String pic;
    @Index
	private Date date;
    
    //Groups and notifications
    private ArrayList<Group> groups;
    private ArrayList<Notifications> notifs;
	private StudentProfile(){}
	
    public StudentProfile(String genId,String collegeID,String instiEmail) {
		// constructor stub
    	this.identifier = genId;
    	this.setInstiEmail(instiEmail);
    	this.setCollegeID(collegeID); 
	}

  //setter and getter methods for student profile go here
    
	public String getCollegeID() {
		return collegeID;
	}

	public void setCollegeID(String collegeID) {
		this.collegeID = collegeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstiEmail() {
		return instiEmail;
	}

	public void setInstiEmail(String instiEmail) {
		this.instiEmail = instiEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Gender getGen() {
		return gen;
	}

	public void setGen(Gender gen) {
		this.gen = gen;
	}

	public Marital getMarital_status() {
		return Marital_status;
	}

	public void setMarital_status(Marital marital_status) {
		Marital_status = marital_status;
	}

	public BGroup getbGroup() {
		return bGroup;
	}

	public void setbGroup(BGroup bGroup) {
		this.bGroup = bGroup;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) throws ParseException {
		setDate(sdf.parse(dob));
		this.dob = sdf.format(this.getDate());
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public boolean isPwd() {
		return pwd;
	}

	public void setPwd(boolean pwd) {
		this.pwd = pwd;
	}

	public Hosteller getHos() {
		return hos;
	}

	public void setHos(Hosteller hos) {
		this.hos = hos;
	}

	public Admission getAdm() {
		return adm;
	}

	public void setAdm(Admission adm) {
		this.adm = adm;
	}

	public Branch getBr() {
		return br;
	}

	public void setBr(Branch br) {
		this.br = br;
		if(br == Branch.ARCH){
			this.setTime(duration.Five_Years);
		}
	}

	public Degree getDeg() {
		return deg;
	}

	public void setDeg(Degree deg) {
		this.deg = deg;
		switch(deg){
		case BTech: this.setTime(duration.Four_Years);
		case MTech: this.setTime(duration.Two_Years);
		case PhD:   this.setTime(duration.Two_Years);
		case MSc:   this.setTime(duration.Two_Years);
		case MBA:   this.setTime(duration.Two_Years);
		}
		
	}

	public String getYear_adm() {
		return year_adm;
	}

	public void setYear_adm(String year_adm) {
		this.year_adm = year_adm;
	}

	public duration getTime() {
		return time;
	}

	public void setTime(duration time) {
		this.time = time;
	}

	public Status getState() {
		return state;
	}

	public void setState(Status state) {
		this.state = state;
	}

	public String getSpecialization() {
		return Specialization;
	}

	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}

	public String getCAddress() {
		return CAddress;
	}

	public void setCAddress(String cAddress) {
		CAddress = cAddress;
	}

	public String getAadhar_no() {
		return Aadhar_no;
	}

	public void setAadhar_no(String aadhar_no) {
		Aadhar_no = aadhar_no;
	}

	public String getPstate() {
		return Pstate;
	}

	public void setPstate(String pstate) {
		Pstate = pstate;
		if(this.isSame()){
			setCstate(pstate);
		}
	}

	public String getCstate() {
		return Cstate;
	}

	public void setCstate(String cstate) {
		Cstate = cstate;
	}

	public String getPcity() {
		return Pcity;
	}

	public void setPcity(String pcity) {
		if(this.isSame()){
			setCcity(pcity);
		}
		Pcity = pcity;
	}

	public String getCcity() {
		return Ccity;
	}

	public void setCcity(String ccity) {
		Ccity = ccity;
	}

	public String getPzip() {
		return Pzip;
	}

	public void setPzip(String pzip) {
		Pzip = pzip;
		if(this.isSame()){
			setCzip(pzip);
		}
	}

	public String getCzip() {
		return Czip;
	}

	public void setCzip(String czip) {
		Czip = czip;
	}

	public String getPcountry() {
		return Pcountry;
	}

	public void setPcountry(String pcountry) {
		Pcountry = pcountry;
		if(this.isSame()){
			setCcountry(pcountry);
		}
	}

	public String getCcountry() {
		return Ccountry;
	}

	public void setCcountry(String ccountry) {
		Ccountry = ccountry;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public String getAcNo() {
		return AcNo;
	}

	public void setAcNo(String acNo) {
		AcNo = acNo;
	}

	public String getIFSCcode() {
		return IFSCcode;
	}

	public void setIFSCcode(String iFSCcode) {
		IFSCcode = iFSCcode;
	}

	public String getsBankName() {
		return sBankName;
	}

	public void setsBankName(String sBankName) {
		this.sBankName = sBankName;
	}

	public String getsAcNo() {
		return sAcNo;
	}

	public void setsAcNo(String sAcNo) {
		this.sAcNo = sAcNo;
	}

	public String getPAddress() {
		return PAddress;
	}

	public void setPAddress(String pAddress) {
		PAddress = pAddress;
		if(this.isSame()){
			this.setCAddress(pAddress);		
			}
	}

	public boolean isSame() {
		return same;
	}

	public void setSame(boolean same) {
		this.same = same;
	}

	public String getsIFSCcode() {
		return sIFSCcode;
	}

	public void setsIFSCcode(String sIFSCcode) {
		this.sIFSCcode = sIFSCcode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public ArrayList<Notifications> getNotifs() {
		return notifs;
	}

	public void setNotifs(ArrayList<Notifications> notifs) {
		this.notifs = notifs;
	}

}
