package profile;

public class USForm {
	private String mobileNo;
	private String email; 
    private String BankName;
    private String AcNo;
    private String IFSCcode;
    private String sBankName;
    private String sAcNo;
    private String sIFSCcode;
    
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
