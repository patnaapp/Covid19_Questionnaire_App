package com.bih.nic.pacsmemberentry.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class BlkCompanyJobDetailsEntity implements KvmSerializable, Serializable {

    public static Class<BlkCompanyJobDetailsEntity> BlockCompanyJobs_CLASS = BlkCompanyJobDetailsEntity.class;
    private String ComanyNameEn = "";
    private String AddressEn = "";
    private String WorkSiteNameHn = "";
    private String NoOfPerson = "";
    private String Salary = "";
    private String vchMobile = "";
    private String Location = "";
    private String SkillName = "";


    public BlkCompanyJobDetailsEntity(SoapObject obj) {

        this.ComanyNameEn = obj.getProperty("ComanyNameEn").toString();
        this.AddressEn = obj.getProperty("AddressEn").toString();
        this.WorkSiteNameHn = obj.getProperty("WorkSiteNameHn").toString();
        this.NoOfPerson = obj.getProperty("NoOfPerson").toString();
        this.Salary = obj.getProperty("Salary").toString();
       // this.vchMobile = obj.getProperty("Location").toString();
        this.Location = obj.getProperty("Location").toString();
        this.SkillName = obj.getProperty("SkillName").toString();
        //this.skillName = obj.getProperty("SkillNameHn").toString();

    }


    public static Class<BlkCompanyJobDetailsEntity> getBlockCompanyJobs_CLASS() {
        return BlockCompanyJobs_CLASS;
    }

    public static void setBlockCompanyJobs_CLASS(Class<BlkCompanyJobDetailsEntity> blockCompanyJobs_CLASS) {
        BlockCompanyJobs_CLASS = blockCompanyJobs_CLASS;
    }

    public String getComanyNameEn() {
        return ComanyNameEn;
    }

    public void setComanyNameEn(String comanyNameEn) {
        ComanyNameEn = comanyNameEn;
    }

    public String getAddressEn() {
        return AddressEn;
    }

    public void setAddressEn(String addressEn) {
        AddressEn = addressEn;
    }

    public String getWorkSiteNameHn() {
        return WorkSiteNameHn;
    }

    public void setWorkSiteNameHn(String workSiteNameHn) {
        WorkSiteNameHn = workSiteNameHn;
    }

    public String getNoOfPerson() {
        return NoOfPerson;
    }

    public void setNoOfPerson(String noOfPerson) {
        NoOfPerson = noOfPerson;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getVchMobile() {
        return vchMobile;
    }

    public void setVchMobile(String vchMobile) {
        this.vchMobile = vchMobile;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getSkillName() {
        return SkillName;
    }

    public void setSkillName(String skillName) {
        SkillName = skillName;
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
