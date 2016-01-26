package profile;

import profile.ProfEnums.*;

public class StudentProfile {
	
 //Profile Fields
	
	//Basic fields
	
	public String name ;
	public String instiEmail;
	public String email;
	public String collegeID;
	public String mobileNo;
	public Gender gen;
	
	// Personal Information
	
	public Marital Marital_status;  
	public BGroup bGroup;                      
    public String dob;
    public String mName;            
    public String fName;
    public Category cat;          
    public boolean pwd;
    
    //Academic Information
    
    public Hosteller hos;
    public Admission adm;        
    public Branch br;           
    public Degree deg;            
    public duration time;
    public int year_adm; //year of admission
    public Status state;
    public String Specialization;
   
    // Address fields
    
    public String PAddress;
    public String CAddress;
    public String Aadhar_no;
    public String Pstate;
    public String Cstate;
    public String Pcity;
    public String Ccity;
    public String Pzip;
    public String Czip;
    public String Pcountry;
    public String Ccountry;
    
    //Personal Bank a/c details
    
    public String BankName;
    public String AcNo;
    public String IFSCcode;
	
    //Scholarship bank a/c details
    
    public String sBankName;
    public String sAcNo;

	//Profile picture

 
}
