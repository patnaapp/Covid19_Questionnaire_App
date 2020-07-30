package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class AcptdRjctdJobOfferEntity implements KvmSerializable, Serializable {

    public static Class<AcptdRjctdJobOfferEntity> AcptdRjctdOffer_CLASS = AcptdRjctdJobOfferEntity.class;
    private String vchregnum = "";
    private String vchName = "";
    private String vchMobile = "";
    private String gender = "";
    private String SkillName = "";
    private String WorkSiteNameHn = "";
    private String count = "";
    private String row_num = "";
    private String AddressEn = "";
    private String Location = "";
    private String ComanyNameEn = "";
    private String Salary = "";
    private String SkillCategory = "";
    private String vchGuardian_name = "";
    private String vchGuardian_number = "";
    private String NoOfPerson = "";


    public AcptdRjctdJobOfferEntity(SoapObject obj) {

        this.vchregnum = obj.getProperty("vchregnum").toString();
        if (this.vchregnum.equals("anyType{}")){
            this.vchregnum="NA";
        }
        this.vchName = obj.getProperty("vchName").toString();
        this.vchMobile = obj.getProperty("vchMobile").toString();
        this.gender = obj.getProperty("gender").toString();
        this.SkillName = obj.getProperty("SkillName").toString();
        this.WorkSiteNameHn = obj.getProperty("WorkSiteNameHn").toString();
        if (this.WorkSiteNameHn.equals("anyType{}")){
            this.WorkSiteNameHn="NA";
        }

        this.count = obj.getProperty("count").toString();
        this.row_num = obj.getProperty("row_num").toString();
        if (this.row_num.equals("anyType{}")){
            this.row_num="NA";
        }
        this.AddressEn = obj.getProperty("AddressEn").toString();
        if (this.AddressEn.equals("anyType{}")){
            this.AddressEn="NA";
        }
        this.Location = obj.getProperty("Location").toString();
        if (this.Location.equals("anyType{}")){
            this.Location="NA";
        }
        this.ComanyNameEn = obj.getProperty("ComanyNameEn").toString();
        if (this.ComanyNameEn.equals("anyType{}")){
            this.ComanyNameEn="NA";
        }
        this.Salary = obj.getProperty("Salary").toString();
        this.SkillCategory = obj.getProperty("SkillCategory").toString();
        if (this.SkillCategory.equals("anyType{}")){
            this.SkillCategory="NA";
        }
        this.vchGuardian_name = obj.getProperty("vchGuardian_name").toString();
        if (this.vchGuardian_name.equals("anyType{}")){
            this.vchGuardian_name="NA";
        }
        this.vchGuardian_number = obj.getProperty("vchGuardian_number").toString();
        if (this.vchGuardian_number.equals("anyType{}")){
            this.vchGuardian_number="NA";
        }
        this.NoOfPerson = obj.getProperty("NoOfPerson").toString();
        //this.skillName = obj.getProperty("SkillNameHn").toString();


    }


    public static Class<AcptdRjctdJobOfferEntity> getJobOffer_CLASS() {
        return AcptdRjctdOffer_CLASS;
    }

    public static void setJobOffer_CLASS(Class<AcptdRjctdJobOfferEntity> jobOffer_CLASS) {
        AcptdRjctdOffer_CLASS = jobOffer_CLASS;
    }

    public String getVchregnum() {
        return vchregnum;
    }

    public void setVchregnum(String vchregnum) {
        this.vchregnum = vchregnum;
    }

    public String getVchName() {
        return vchName;
    }

    public void setVchName(String vchName) {
        this.vchName = vchName;
    }

    public String getVchMobile() {
        return vchMobile;
    }

    public void setVchMobile(String vchMobile) {
        this.vchMobile = vchMobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSkillName() {
        return SkillName;
    }

    public void setSkillName(String skillName) {
        SkillName = skillName;
    }

    public String getWorkSiteNameHn() {
        return WorkSiteNameHn;
    }

    public void setWorkSiteNameHn(String workSiteNameHn) {
        WorkSiteNameHn = workSiteNameHn;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRow_num() {
        return row_num;
    }

    public void setRow_num(String row_num) {
        this.row_num = row_num;
    }

    public String getAddressEn() {
        return AddressEn;
    }

    public void setAddressEn(String addressEn) {
        AddressEn = addressEn;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getComanyNameEn() {
        return ComanyNameEn;
    }

    public void setComanyNameEn(String comanyNameEn) {
        ComanyNameEn = comanyNameEn;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getSkillCategory() {
        return SkillCategory;
    }

    public void setSkillCategory(String skillCategory) {
        SkillCategory = skillCategory;
    }

    public String getVchGuardian_name() {
        return vchGuardian_name;
    }

    public void setVchGuardian_name(String vchGuardian_name) {
        this.vchGuardian_name = vchGuardian_name;
    }

    public String getVchGuardian_number() {
        return vchGuardian_number;
    }

    public void setVchGuardian_number(String vchGuardian_number) {
        this.vchGuardian_number = vchGuardian_number;
    }

    public String getNoOfPerson() {
        return NoOfPerson;
    }

    public void setNoOfPerson(String noOfPerson) {
        NoOfPerson = noOfPerson;
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
}
