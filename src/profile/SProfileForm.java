package profile;

import profile.ProfEnums.Admission;
import profile.ProfEnums.BGroup;
import profile.ProfEnums.Branch;
import profile.ProfEnums.Category;
import profile.ProfEnums.Degree;
import profile.ProfEnums.Gender;
import profile.ProfEnums.Hosteller;
import profile.ProfEnums.Marital;

public class SProfileForm {
	private String name ;   //non-update-able
	private String email; //non-update-able
	private String mobileNo;
	private Gender gen;  //non-update-able
	
	private Marital Marital_status;  
	private BGroup bGroup;                //non-update-able      
    private String dob; //non-update-able
    private String mName;           
    private String fName;  
    private Category cat;          //non-update-able
    private boolean pwd;      //non-update-able
    
    
    private Hosteller hos;
    private Admission adm;        //non-update-able
    private Branch br;           
    private Degree deg;
    private int year_adm; //year of admission
    private String Specialization;
    
    
    private String PAddress;   //non-update-able
    private String CAddress;
    private String Aadhar_no ; //non-update-able
    private String Pstate;    //non-update-able
    private String Cstate;
    private String Pcity;  //non-update-able
    private String Ccity;
    private String Pzip;  //non-update-able
    private String Czip;
    private String Pcountry; //non-update-able
    private String Ccountry;
    private boolean same;
    
    private String BankName;
    private String AcNo;
    private String IFSCcode;
    
     
    private String sBankName;
    private String sAcNo;
    private String sIFSCcode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public boolean isPwd() {
		return pwd;
	}
	public void setPwd(boolean pwd) {
		this.pwd = pwd;
	}
	public Category getCat() {
		return cat;
	}
	public void setCat(Category cat) {
		this.cat = cat;
	}
	public Admission getAdm() {
		return adm;
	}
	public void setAdm(Admission adm) {
		this.adm = adm;
	}
	public Hosteller getHos() {
		return hos;
	}
	public void setHos(Hosteller hos) {
		this.hos = hos;
	}
	public Branch getBr() {
		return br;
	}
	public void setBr(Branch br) {
		this.br = br;
	}
	public Degree getDeg() {
		return deg;
	}
	public void setDeg(Degree deg) {
		this.deg = deg;
	}
	public int getYear_adm() {
		return year_adm;
	}
	public void setYear_adm(int year_adm) {
		this.year_adm = year_adm;
	}
	public String getSpecialization() {
		return Specialization;
	}
	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}
	public String getPAddress() {
		return PAddress;
	}
	public void setPAddress(String pAddress) {
		PAddress = pAddress;
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
	}
	public String getCcountry() {
		return Ccountry;
	}
	public void setCcountry(String ccountry) {
		Ccountry = ccountry;
	}
	public boolean isSame() {
		return same;
	}
	public void setSame(boolean same) {
		this.same = same;
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
	public String getsIFSCcode() {
		return sIFSCcode;
	}
	public void setsIFSCcode(String sIFSCcode) {
		this.sIFSCcode = sIFSCcode;
	}
    
}
