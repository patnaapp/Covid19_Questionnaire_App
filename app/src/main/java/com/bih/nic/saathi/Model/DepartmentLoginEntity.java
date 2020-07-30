package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class DepartmentLoginEntity implements KvmSerializable {
    private static Class<DepartmentLoginEntity> DepartmentCLASS = DepartmentLoginEntity.class;
    private boolean Status = false;
    private String User_Id="";
    private String Password = "";
    private String UserName="";
    private String BlockCode="";
    private String UserRole="";
    private String LoginStatus="";
    private String OrgId="";
    private String LvlOneId="";
    private String LvlTwoId="";
    private String LvlThree="";
    private String LastVisitedOn="";
    private String msg="";
    private String DistrictCode="";


    public DepartmentLoginEntity() {

    }
   // public static Class<UserDetails> getdata = BenfiList.class;
    public static Class<DepartmentLoginEntity> getUserClass() {
        return DepartmentCLASS;
    }

 public DepartmentLoginEntity(SoapObject res1) {
        this.setStatus(Boolean.parseBoolean(res1.getProperty("Status").toString()));

        this.User_Id=res1.getProperty("UserId").toString();
        this.Password=res1.getProperty("Password").toString();
        this.UserName=res1.getProperty("UserName").toString();
        this.BlockCode=res1.getProperty("BlockCode").toString();
        this.UserRole=res1.getProperty("UserRole").toString();
        this.LoginStatus=res1.getProperty("LoginStatus").toString();
        this.OrgId=res1.getProperty("OrgId").toString();
        this.LvlOneId=res1.getProperty("LvlOneId").toString();
        this.LvlTwoId=res1.getProperty("LvlTwoId").toString();
        this.LvlThree=res1.getProperty("LvlThree").toString();
        this.LastVisitedOn=res1.getProperty("LastVisitedOn").toString();
        this.msg=res1.getProperty("msg").toString();
        this.DistrictCode=res1.getProperty("DistrictCode").toString();


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

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    public String getLoginStatus() {
        return LoginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        LoginStatus = loginStatus;
    }

    public String getOrgId() {
        return OrgId;
    }

    public void setOrgId(String orgId) {
        OrgId = orgId;
    }

    public String getLvlOneId() {
        return LvlOneId;
    }

    public void setLvlOneId(String lvlOneId) {
        LvlOneId = lvlOneId;
    }

    public String getLvlTwoId() {
        return LvlTwoId;
    }

    public void setLvlTwoId(String lvlTwoId) {
        LvlTwoId = lvlTwoId;
    }

    public String getLvlThree() {
        return LvlThree;
    }

    public void setLvlThree(String lvlThree) {
        LvlThree = lvlThree;
    }

    public String getLastVisitedOn() {
        return LastVisitedOn;
    }

    public void setLastVisitedOn(String lastVisitedOn) {
        LastVisitedOn = lastVisitedOn;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }
}
