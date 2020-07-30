package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class JobOfferPostedEntity implements KvmSerializable, Serializable {

    public static Class<JobOfferPostedEntity> JobOffer_CLASS = JobOfferPostedEntity.class;
    private String DistrictCode = "";
    private String DistrictName = "";
    private String ttlReg = "";
    private String TotalJobOffer = "";
    private String ttlRegA = "";
    private String ttlRegR = "";


    public JobOfferPostedEntity(SoapObject obj) {

        this.DistrictCode = obj.getProperty("DistrictCode").toString();
        this.DistrictName = obj.getProperty("DistrictName").toString();
        this.ttlReg = obj.getProperty("ttlReg").toString();
        this.TotalJobOffer = obj.getProperty("TotalJobOffer").toString();
        this.ttlRegA = obj.getProperty("ttlRegA").toString();
        this.ttlRegR = obj.getProperty("ttlRegR").toString();
        //this.skillName = obj.getProperty("SkillNameHn").toString();


    }


    public static Class<JobOfferPostedEntity> getJobOffer_CLASS() {
        return JobOffer_CLASS;
    }

    public static void setJobOffer_CLASS(Class<JobOfferPostedEntity> jobOffer_CLASS) {
        JobOffer_CLASS = jobOffer_CLASS;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    public String getTtlReg() {
        return ttlReg;
    }

    public void setTtlReg(String ttlReg) {
        this.ttlReg = ttlReg;
    }

    public String getTotalJobOffer() {
        return TotalJobOffer;
    }

    public void setTotalJobOffer(String totalJobOffer) {
        TotalJobOffer = totalJobOffer;
    }

    public String getTtlRegA() {
        return ttlRegA;
    }

    public void setTtlRegA(String ttlRegA) {
        this.ttlRegA = ttlRegA;
    }

    public String getTtlRegR() {
        return ttlRegR;
    }

    public void setTtlRegR(String ttlRegR) {
        this.ttlRegR = ttlRegR;
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
