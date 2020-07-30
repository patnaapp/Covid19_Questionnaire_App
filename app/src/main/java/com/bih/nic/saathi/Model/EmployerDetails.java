package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class EmployerDetails implements KvmSerializable {
    private static Class<EmployerDetails> EmployerCLASS = EmployerDetails.class;
    private boolean Status = false;
    private String OrgId="";
    private String ComanyNameEn = "";
    private String ComanyNameHn="";
    private String CompanyType="";
    private String AddressEn="";
    private String DistCode="";
    private String Mobile="";
    private String AlternateMobile="";
    private String Password="";
    private String EntryBy="";
    private String EntryDate="";
    private String EntryIP="";
    private String UserId="";
    private String UserRole="";
    private String LastVisitedOn="";
    private String msg;


    public EmployerDetails() {

    }
   // public static Class<UserDetails> getdata = BenfiList.class;
    public static Class<EmployerDetails> getUserClass() {
        return EmployerCLASS;
    }


   /* public UserDetails(SoapObject res1) {
        this.setAuthenticated(Boolean.parseBoolean(res1.getProperty(
                "isAuthenticated").toString()));
        this._UserId=res1.getProperty("UserID").toString();
        this.DistCode=res1.getProperty("DistrictId").toString();
        this.BlockCode=res1.getProperty("BlockCode").toString();
        this.DistName=res1.getProperty("_District_name").toString();
        this.BlockName=res1.getProperty("_Block_Name").toString();
        this.Role=res1.getProperty("Role").toString();

    }*/
 public EmployerDetails(SoapObject res1) {
        this.setStatus(Boolean.parseBoolean(res1.getProperty("Status").toString()));

        this.OrgId=res1.getProperty("Id").toString();
        this.ComanyNameEn=res1.getProperty("ComanyNameEn").toString();
        this.ComanyNameHn=res1.getProperty("ComanyNameHn").toString();
        this.CompanyType=res1.getProperty("CompanyType").toString();
        this.AddressEn=res1.getProperty("AddressEn").toString();
        this.DistCode=res1.getProperty("DistCode").toString();
        this.Mobile=res1.getProperty("Mobile").toString();
        this.AlternateMobile=res1.getProperty("AlternateMobile").toString();
        this.Password=res1.getProperty("Password").toString();
        this.EntryBy=res1.getProperty("EntryBy").toString();
        this.EntryDate=res1.getProperty("EntryDate").toString();
        this.EntryIP=res1.getProperty("EntryIP").toString();
        this.UserId=res1.getProperty("UserId").toString();
        this.UserRole=res1.getProperty("UserRole").toString();
        this.LastVisitedOn=res1.getProperty("LastVisitedOn").toString();
        this.msg=res1.getProperty("msg").toString();


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

    public String getOrgId() {
        return OrgId;
    }

    public void setOrgId(String orgId) {
        OrgId = orgId;
    }

    public String getComanyNameEn() {
        return ComanyNameEn;
    }

    public void setComanyNameEn(String comanyNameEn) {
        ComanyNameEn = comanyNameEn;
    }

    public String getComanyNameHn() {
        return ComanyNameHn;
    }

    public void setComanyNameHn(String comanyNameHn) {
        ComanyNameHn = comanyNameHn;
    }

    public String getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(String companyType) {
        CompanyType = companyType;
    }

    public String getAddressEn() {
        return AddressEn;
    }

    public void setAddressEn(String addressEn) {
        AddressEn = addressEn;
    }

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAlternateMobile() {
        return AlternateMobile;
    }

    public void setAlternateMobile(String alternateMobile) {
        AlternateMobile = alternateMobile;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEntryBy() {
        return EntryBy;
    }

    public void setEntryBy(String entryBy) {
        EntryBy = entryBy;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }

    public String getEntryIP() {
        return EntryIP;
    }

    public void setEntryIP(String entryIP) {
        EntryIP = entryIP;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
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
}
