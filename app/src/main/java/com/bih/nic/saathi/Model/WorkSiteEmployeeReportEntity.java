package com.bih.nic.saathi.Model;

import com.bih.nic.saathi.AppConstant;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class WorkSiteEmployeeReportEntity implements KvmSerializable, Serializable {
    public static Class<WorkSiteEmployeeReportEntity> WorkSiteEmployeeReportEntity_CLASS = WorkSiteEmployeeReportEntity.class;

    private String WorksRegId="";
    private String WorksId="";
    private String WorkSiteName="";
    private String SkillCategory="";
    private String SkillName="";
    private String NoOfPerson="";
    private String ttlReg="";
    private String TotalJobOffer="";
    private String ttlRegA ="";
    private String ttlRegR="";
    private String ttlPendinAppoint="";
    private String ttlAppointed="";
    private String supervisor_nm_hn="";
    private String supervisor_mob="";

    private String Location="";
    private String ContactPerson="";
    private String Designaton="";
    private String CPMobileNo="";
    private String SubSkillName="";

    private String ComanyNameEn="";
    private String AddressEn="";

    private String vchregnum="";
    private String vchName="";
    private String vchMobile="";
    private String intGender="";


    public WorkSiteEmployeeReportEntity() {

    }

    public WorkSiteEmployeeReportEntity(SoapObject res1, String status)
    {

        switch (status){
            case AppConstant.WORKSITE_EMPLOYEE_REPORT:
                this.WorksRegId=res1.getProperty("WorksRegId").toString();
                this.WorksId=res1.getProperty("WorksId").toString();
                this.SkillCategory=res1.getProperty("SkillCategory").toString();
                this.ttlReg=res1.getProperty("ttlReg").toString();
                this.TotalJobOffer=res1.getProperty("TotalJobOffer").toString();
                this.ttlRegA=res1.getProperty("ttlRegA").toString();
                this.ttlRegR=res1.getProperty("ttlRegR").toString();
                this.ttlPendinAppoint=res1.getProperty("ttlPendinAppoint").toString();
                this.ttlAppointed=res1.getProperty("ttlAppointed").toString();
                this.SkillName=res1.getProperty("SkillName").toString();
                this.WorkSiteName=res1.getProperty("WorkSiteName").toString();
                this.NoOfPerson=res1.getProperty("NoOfPerson").toString();
                break;

            case AppConstant.WORKSITE_DETAIL:
                this.Location=res1.getProperty("Location").toString();
                this.ContactPerson=res1.getProperty("ContactPerson").toString();
                this.CPMobileNo=res1.getProperty("CPMobileNo").toString();
                this.ComanyNameEn=res1.getProperty("ComanyNameEn").toString();
                this.AddressEn=res1.getProperty("AddressEn").toString();
                this.TotalJobOffer=res1.getProperty("TotalJobOfferCount").toString();
                this.WorkSiteName=res1.getProperty("WorkSiteName").toString();
                this.NoOfPerson=res1.getProperty("NoOfPerson").toString();
                break;

            case AppConstant.WORKSITE_NO_PERSON:
                this.Location=res1.getProperty("Location").toString();
                this.ContactPerson=res1.getProperty("ContactPerson").toString();
                this.Designaton=res1.getProperty("Designaton").toString();
                this.CPMobileNo=res1.getProperty("CPMobileNo").toString();
                this.SubSkillName=res1.getProperty("SubSkillName").toString();
                this.SkillName=res1.getProperty("SkillName").toString();
                this.WorkSiteName=res1.getProperty("WorkSiteName").toString();
                this.NoOfPerson=res1.getProperty("NoOfPerson").toString();
                break;

            case AppConstant.WORKSITE_JOB_OFFERED:
                this.SkillName=res1.getProperty("SkillName").toString();
                this.vchregnum=res1.getProperty("vchregnum").toString();
                this.vchName=res1.getProperty("vchName").toString();
                this.vchMobile=res1.getProperty("vchMobile").toString();
                this.intGender=res1.getProperty("intGender").toString();
                break;

            case AppConstant.WORKSITE_JOB_ACCEPTED:
                this.SkillName=res1.getProperty("SkillName").toString();
                this.vchregnum=res1.getProperty("vchregnum").toString();
                this.vchName=res1.getProperty("vchName").toString();
                this.vchMobile=res1.getProperty("vchMobile").toString();
                this.intGender=res1.getProperty("intGender").toString();
                break;

            case AppConstant.WORKSITE_JOB_REJECTED:
                this.SkillName=res1.getProperty("SkillName").toString();
                this.vchregnum=res1.getProperty("vchregnum").toString();
                this.vchName=res1.getProperty("vchName").toString();
                this.vchMobile=res1.getProperty("vchMobile").toString();
                this.intGender=res1.getProperty("intGender").toString();
                break;

            case AppConstant.WORKSITE_JOB_APPOINTED:
                this.SkillName=res1.getProperty("SkillName").toString();
                this.vchregnum=res1.getProperty("vchregnum").toString();
                this.vchName=res1.getProperty("vchName").toString();
                this.vchMobile=res1.getProperty("vchMobile").toString();
                this.intGender=res1.getProperty("intGender").toString();
                break;
        }


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

    public String getIntGender() {
        return intGender;
    }

    public void setIntGender(String intGender) {
        this.intGender = intGender;
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String contactPerson) {
        ContactPerson = contactPerson;
    }

    public String getDesignaton() {
        return Designaton;
    }

    public void setDesignaton(String designaton) {
        Designaton = designaton;
    }

    public String getCPMobileNo() {
        return CPMobileNo;
    }

    public void setCPMobileNo(String CPMobileNo) {
        this.CPMobileNo = CPMobileNo;
    }

    public String getSubSkillName() {
        return SubSkillName;
    }

    public void setSubSkillName(String subSkillName) {
        SubSkillName = subSkillName;
    }

    public String getWorksRegId() {
        return WorksRegId;
    }

    public void setWorksRegId(String worksRegId) {
        WorksRegId = worksRegId;
    }

    public String getWorksId() {
        return WorksId;
    }

    public void setWorksId(String worksId) {
        WorksId = worksId;
    }

    public String getWorkSiteName() {
        return WorkSiteName;
    }

    public void setWorkSiteName(String workSiteName) {
        WorkSiteName = workSiteName;
    }

    public String getSkillCategory() {
        return SkillCategory;
    }

    public void setSkillCategory(String skillCategory) {
        SkillCategory = skillCategory;
    }

    public String getSkillName() {
        return SkillName;
    }

    public void setSkillName(String skillName) {
        SkillName = skillName;
    }

    public String getNoOfPerson() {
        return NoOfPerson;
    }

    public void setNoOfPerson(String noOfPerson) {
        NoOfPerson = noOfPerson;
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

    public String getTtlPendinAppoint() {
        return ttlPendinAppoint;
    }

    public void setTtlPendinAppoint(String ttlPendinAppoint) {
        this.ttlPendinAppoint = ttlPendinAppoint;
    }

    public String getTtlAppointed() {
        return ttlAppointed;
    }

    public void setTtlAppointed(String ttlAppointed) {
        this.ttlAppointed = ttlAppointed;
    }

    public String getSupervisor_nm_hn() {
        return supervisor_nm_hn;
    }

    public void setSupervisor_nm_hn(String supervisor_nm_hn) {
        this.supervisor_nm_hn = supervisor_nm_hn;
    }

    public String getSupervisor_mob() {
        return supervisor_mob;
    }

    public void setSupervisor_mob(String supervisor_mob) {
        this.supervisor_mob = supervisor_mob;
    }
}
