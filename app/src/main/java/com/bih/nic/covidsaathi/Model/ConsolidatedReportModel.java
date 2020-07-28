package com.bih.nic.covidsaathi.Model;

import org.ksoap2.serialization.SoapObject;

public class ConsolidatedReportModel {

    public static Class<ConsolidatedReportModel> ConsolidatedReptModel = ConsolidatedReportModel.class;

    private String TotalOrg;
    private String TotalWorkDet;
    private String DistrictName;
    private String DistrictCode;
    private String TotalWorkAccp;
    private String TotalWorkReject;
    private String TotalWorkPerRej;
    private String NoOfPerson;
    private String NoOfPersonApp;
    private String BlockCode;
    private String BlockName;
    private String WorkSiteName;
    private String WorkSite_ContactPer;
    private String WorkSite_ContactMobile;
    private String WorkSite_WorkStatus;
    private String WorkSite_Location;


    private String OrgInfo_Name;
    private String OrgInfo_Type;
    private String OrgInfo_District;
    private String WorkSite_Name;
    private String WorkSite_DIstrict;
    private String WorkSite_Place;
    private String WorkSite_ContactPerson;
    private String WorkSite_Mobile;
    private String WorkSite_Vacancy;
    private String WorkSite_Skill;
    private String WorkSite_Status;


    public ConsolidatedReportModel(SoapObject final_object) {
        this.TotalOrg=final_object.getPrimitiveProperty("TotalOrg").toString();
        this.TotalWorkDet=final_object.getPrimitiveProperty("TotalWorkDet").toString();
        this.DistrictName=final_object.getPrimitiveProperty("DistrictName").toString();
        this.DistrictCode=final_object.getPrimitiveProperty("DistrictCode").toString();
        this.TotalWorkAccp=final_object.getPrimitiveProperty("TotalWorkAccp").toString();
        this.TotalWorkReject=final_object.getPrimitiveProperty("TotalWorkReject").toString();
        this.TotalWorkPerRej=final_object.getPrimitiveProperty("TotalWorkPerRej").toString();
        this.NoOfPerson=final_object.getPrimitiveProperty("NoOfPerson").toString();
        this.NoOfPersonApp=final_object.getPrimitiveProperty("NoOfPersonApp").toString();

    }

    public ConsolidatedReportModel(SoapObject final_object, int i) {
        if(i==0) {
            this.TotalWorkDet = final_object.getPrimitiveProperty("TotalWorkDet").toString();
            this.BlockCode = final_object.getPrimitiveProperty("BlockCode").toString();
            this.BlockName = final_object.getPrimitiveProperty("BlockName").toString();
            this.TotalWorkAccp = final_object.getPrimitiveProperty("TotalWorkDetApproved").toString();
            this.TotalWorkReject = final_object.getPrimitiveProperty("TotalWorkDetRject").toString();
            this.TotalWorkPerRej = final_object.getPrimitiveProperty("TotalWorkDetPerRject").toString();
            this.NoOfPerson = final_object.getPrimitiveProperty("NoOfPerson").toString();
            this.NoOfPersonApp = final_object.getPrimitiveProperty("NoOfPersonApp").toString();
        }if (i==2){
            this.WorkSiteName = final_object.getPrimitiveProperty("WorkSiteName").toString();
            this.WorkSite_ContactPer = final_object.getPrimitiveProperty("WorkSite_ContactPer").toString();
            this.WorkSite_ContactMobile = final_object.getPrimitiveProperty("WorkSite_ContactMobile").toString();
            this.WorkSite_WorkStatus = final_object.getPrimitiveProperty("WorkSite_WorkStatus").toString();
            this.WorkSite_Location = final_object.getPrimitiveProperty("WorkSite_Location").toString();
        }if (i==1){
            this.BlockCode = final_object.getPrimitiveProperty("BlockCode").toString();
            this.BlockName = final_object.getPrimitiveProperty("BlockName").toString();
            this.OrgInfo_Name = final_object.getPrimitiveProperty("OrgInfo_Name").toString();
            this.OrgInfo_Type = final_object.getPrimitiveProperty("OrgInfo_Type").toString();
            this.OrgInfo_District = final_object.getPrimitiveProperty("OrgInfo_District").toString();
            this.WorkSite_Name = final_object.getPrimitiveProperty("WorkSite_Name").toString();
            this.WorkSite_DIstrict = final_object.getPrimitiveProperty("WorkSite_DIstrict").toString();
            this.WorkSite_Place = final_object.getPrimitiveProperty("WorkSite_Place").toString();
            this.WorkSite_ContactPerson = final_object.getPrimitiveProperty("WorkSite_ContactPerson").toString();
            this.WorkSite_Mobile = final_object.getPrimitiveProperty("WorkSite_Mobile").toString();
            this.WorkSite_Vacancy = final_object.getPrimitiveProperty("WorkSite_Vacancy").toString();
            this.WorkSite_Skill = final_object.getPrimitiveProperty("WorkSite_Skill").toString();
            this.WorkSite_Status = final_object.getPrimitiveProperty("WorkSite_Status").toString();
        }
    }

    public String getTotalOrg() {
        return TotalOrg;
    }

    public void setTotalOrg(String totalOrg) {
        TotalOrg = totalOrg;
    }

    public String getTotalWorkDet() {
        return TotalWorkDet;
    }

    public void setTotalWorkDet(String totalWorkDet) {
        TotalWorkDet = totalWorkDet;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getTotalWorkAccp() {
        return TotalWorkAccp;
    }

    public void setTotalWorkAccp(String totalWorkAccp) {
        TotalWorkAccp = totalWorkAccp;
    }

    public String getTotalWorkReject() {
        return TotalWorkReject;
    }

    public void setTotalWorkReject(String totalWorkReject) {
        TotalWorkReject = totalWorkReject;
    }

    public String getTotalWorkPerRej() {
        return TotalWorkPerRej;
    }

    public void setTotalWorkPerRej(String totalWorkPerRej) {
        TotalWorkPerRej = totalWorkPerRej;
    }

    public String getNoOfPerson() {
        return NoOfPerson;
    }

    public void setNoOfPerson(String noOfPerson) {
        NoOfPerson = noOfPerson;
    }

    public String getNoOfPersonApp() {
        return NoOfPersonApp;
    }

    public void setNoOfPersonApp(String noOfPersonApp) {
        NoOfPersonApp = noOfPersonApp;
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

    public String getWorkSiteName() {
        return WorkSiteName;
    }

    public void setWorkSiteName(String workSiteName) {
        WorkSiteName = workSiteName;
    }

    public String getWorkSite_ContactPer() {
        return WorkSite_ContactPer;
    }

    public void setWorkSite_ContactPer(String workSite_ContactPer) {
        WorkSite_ContactPer = workSite_ContactPer;
    }

    public String getWorkSite_ContactMobile() {
        return WorkSite_ContactMobile;
    }

    public void setWorkSite_ContactMobile(String workSite_ContactMobile) {
        WorkSite_ContactMobile = workSite_ContactMobile;
    }

    public String getWorkSite_WorkStatus() {
        return WorkSite_WorkStatus;
    }

    public void setWorkSite_WorkStatus(String workSite_WorkStatus) {
        WorkSite_WorkStatus = workSite_WorkStatus;
    }

    public String getWorkSite_Location() {
        return WorkSite_Location;
    }

    public void setWorkSite_Location(String workSite_Location) {
        WorkSite_Location = workSite_Location;
    }

    public String getOrgInfo_Name() {
        return OrgInfo_Name;
    }

    public void setOrgInfo_Name(String orgInfo_Name) {
        OrgInfo_Name = orgInfo_Name;
    }

    public String getOrgInfo_Type() {
        return OrgInfo_Type;
    }

    public void setOrgInfo_Type(String orgInfo_Type) {
        OrgInfo_Type = orgInfo_Type;
    }

    public String getOrgInfo_District() {
        return OrgInfo_District;
    }

    public void setOrgInfo_District(String orgInfo_District) {
        OrgInfo_District = orgInfo_District;
    }

    public String getWorkSite_Name() {
        return WorkSite_Name;
    }

    public void setWorkSite_Name(String workSite_Name) {
        WorkSite_Name = workSite_Name;
    }

    public String getWorkSite_DIstrict() {
        return WorkSite_DIstrict;
    }

    public void setWorkSite_DIstrict(String workSite_DIstrict) {
        WorkSite_DIstrict = workSite_DIstrict;
    }

    public String getWorkSite_Place() {
        return WorkSite_Place;
    }

    public void setWorkSite_Place(String workSite_Place) {
        WorkSite_Place = workSite_Place;
    }

    public String getWorkSite_ContactPerson() {
        return WorkSite_ContactPerson;
    }

    public void setWorkSite_ContactPerson(String workSite_ContactPerson) {
        WorkSite_ContactPerson = workSite_ContactPerson;
    }

    public String getWorkSite_Mobile() {
        return WorkSite_Mobile;
    }

    public void setWorkSite_Mobile(String workSite_Mobile) {
        WorkSite_Mobile = workSite_Mobile;
    }

    public String getWorkSite_Vacancy() {
        return WorkSite_Vacancy;
    }

    public void setWorkSite_Vacancy(String workSite_Vacancy) {
        WorkSite_Vacancy = workSite_Vacancy;
    }

    public String getWorkSite_Skill() {
        return WorkSite_Skill;
    }

    public void setWorkSite_Skill(String workSite_Skill) {
        WorkSite_Skill = workSite_Skill;
    }

    public String getWorkSite_Status() {
        return WorkSite_Status;
    }

    public void setWorkSite_Status(String workSite_Status) {
        WorkSite_Status = workSite_Status;
    }
}
