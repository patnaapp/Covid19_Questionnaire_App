package com.bih.nic.pacsmemberentry.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class UserDetails implements KvmSerializable {
    private static Class<UserDetails> USER_CLASS = UserDetails.class;
    private boolean isAuthenticated = false;

    private String UserName = "";
    private String _UserId="";
    private String _Passwoed="";

    private String DistCode="";
    private String DistName="";
    private String BlockCode="";
    private String BlockName="";
    private String PanchayatName="";
    private String PanchayatCode="";

    private String PatientId="";
    private String FHName="";
    private String PatientName="";
    private String SupervisorId="";
    private String SupervisorName="";
    private String Covid19TestingDate="";
    private String ImpDate="";

    private String Age="";
    private String MobileNo="";
    private String Address="";
    private String Role="";
    private String Message="";
    private String EMEI;
    private String AadhaarNo;
    private String profileImg="";


    public UserDetails() {

    }
   // public static Class<UserDetails> getdata = BenfiList.class;
    public static Class<UserDetails> getUserClass() {
        return USER_CLASS;
    }

 public UserDetails(SoapObject res1) {
        this.setAuthenticated(Boolean.parseBoolean(res1.getProperty("Status").toString()));
        this.Message=res1.getProperty("msg").toString();

        this._UserId=res1.getProperty("Id").toString();
        this.FHName=res1.getProperty("FHName").toString();
        this.PatientId=res1.getProperty("PatientId").toString();
        this.PatientName=res1.getProperty("PatientName").toString();
        this.SupervisorId=res1.getProperty("SupervisorId").toString();
        this.SupervisorName=res1.getProperty("SupervisorName").toString();
        this.Address=res1.getProperty("Address").toString();
        this.MobileNo=res1.getProperty("Mobile").toString();
        this.ImpDate=res1.getProperty("ImpDate").toString();
        this.Covid19TestingDate=res1.getProperty("Covid19TestingDate").toString();
        this._Passwoed=res1.getProperty("OTP").toString();

//        this.DistCode=res1.getProperty("DistrictCode").toString();
//        this.DistName=res1.getProperty("DistrictName").toString();
//        this.BlockCode=res1.getProperty("BlockCode").toString();
//        this.BlockName=res1.getProperty("BlockName").toString();
//        this.PanchayatCode=res1.getProperty("PanchayatCode").toString();
//        this.PanchayatName=res1.getProperty("PanchayatName").toString();


    }

    public UserDetails(SoapObject res1, String type) {
        this.setAuthenticated(Boolean.parseBoolean(res1.getProperty("Status").toString()));
        this.Message=res1.getProperty("msg").toString();

        this._UserId=res1.getProperty("UserId").toString();
        this._Passwoed=res1.getProperty("Password").toString();
        this.UserName=res1.getProperty("UserName").toString();
        this.SupervisorId=res1.getProperty("SupId").toString();
        this.SupervisorName=res1.getProperty("UserName").toString();
        this.Role=res1.getProperty("UserRole").toString();

//        this.DistCode=res1.getProperty("DistrictCode").toString();
//        this.DistName=res1.getProperty("DistrictName").toString();
//        this.BlockCode=res1.getProperty("BlockCode").toString();
//        this.BlockName=res1.getProperty("BlockName").toString();
//        this.PanchayatCode=res1.getProperty("PanchayatCode").toString();
//        this.PanchayatName=res1.getProperty("PanchayatName").toString();


    }

    @Override
    public Object getProperty(int index) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int index, Object value) {

    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {

    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getFHName() {
        return FHName;
    }

    public void setFHName(String FHName) {
        this.FHName = FHName;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getSupervisorId() {
        return SupervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        SupervisorId = supervisorId;
    }

    public String getSupervisorName() {
        return SupervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        SupervisorName = supervisorName;
    }

    public String getCovid19TestingDate() {
        return Covid19TestingDate;
    }

    public void setCovid19TestingDate(String covid19TestingDate) {
        Covid19TestingDate = covid19TestingDate;
    }

    public String getImpDate() {
        return ImpDate;
    }

    public void setImpDate(String impDate) {
        ImpDate = impDate;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getPanchayatName() {
        return PanchayatName;
    }

    public void setPanchayatName(String panchayatName) {
        PanchayatName = panchayatName;
    }

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String get_UserId() {
        return _UserId;
    }

    public void set_UserId(String _UserId) {
        this._UserId = _UserId;
    }

    public String get_Passwoed() {
        return _Passwoed;
    }

    public void set_Passwoed(String _Passwoed) {
        this._Passwoed = _Passwoed;
    }

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getEMEI() {
        return EMEI;
    }

    public void setEMEI(String EMEI) {
        this.EMEI = EMEI;
    }

    public String getAadhaarNo() {
        return AadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        AadhaarNo = aadhaarNo;
    }

}
