package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class ApproveWorkSiteEntity implements KvmSerializable, Serializable {

    public static Class<ApproveWorkSiteEntity> Approval_CLASS = ApproveWorkSiteEntity.class;
    private String ComanyNameEn = "";
    private String WorksId = "";
    private String a_id = "";
    private String DistrictName = "";
    private String BlockName = "";
    private String WorkSiteName = "";
    private String Location = "";
    private String PinNo = "";
    private String EntryDate = "";
    private String CPMobileNo = "";
    private String BenStatus = "";
    private String IsVerifiedOrgAdm = "";
    private String VerifiedRemarksOrgAdm = "";
    private String VerifiedRemarksTypeOrgAdm = "";


    public ApproveWorkSiteEntity(SoapObject obj,String status) {

        if (status.equals("1")){
            this.ComanyNameEn = obj.getProperty("ComanyNameEn").toString();
            this.WorksId = obj.getProperty("WorksId").toString();
            this.a_id = obj.getProperty("a_id").toString();
            this.BlockName = obj.getProperty("BlockName").toString();
            this.WorkSiteName = obj.getProperty("WorkSiteName").toString();
            this.Location = obj.getProperty("Location").toString();
            this.PinNo = obj.getProperty("PinNo").toString();
            this.EntryDate = obj.getProperty("EntryDate").toString();
            this.CPMobileNo = obj.getProperty("CPMobileNo").toString();
            this.BenStatus = obj.getProperty("BenStatus").toString();
            this.IsVerifiedOrgAdm = obj.getProperty("IsVerifiedOrgAdm").toString();
            this.VerifiedRemarksOrgAdm = obj.getProperty("VerifiedRemarksOrgAdm").toString();
            this.VerifiedRemarksTypeOrgAdm = obj.getProperty("VerifiedRemarksTypeOrgAdm").toString();
        }
        else if (status.equals("2")){
            this.ComanyNameEn = obj.getProperty("ComanyNameEn").toString();
            this.WorksId = obj.getProperty("WorksId").toString();
            this.a_id = obj.getProperty("a_id").toString();
            this.BlockName = obj.getProperty("BlockName").toString();
            this.WorkSiteName = obj.getProperty("WorkSiteName").toString();
            this.Location = obj.getProperty("Location").toString();
            this.PinNo = obj.getProperty("PinNo").toString();
            this.EntryDate = obj.getProperty("EntryDate").toString();
            this.CPMobileNo = obj.getProperty("CPMobileNo").toString();
            this.BenStatus = obj.getProperty("BenStatus").toString();
        }

        //this.skillName = obj.getProperty("SkillNameHn").toString();


    }

    public static Class<ApproveWorkSiteEntity> getApproval_CLASS() {
        return Approval_CLASS;
    }

    public static void setApproval_CLASS(Class<ApproveWorkSiteEntity> approval_CLASS) {
        Approval_CLASS = approval_CLASS;
    }

    public String getComanyNameEn() {
        return ComanyNameEn;
    }

    public void setComanyNameEn(String comanyNameEn) {
        ComanyNameEn = comanyNameEn;
    }

    public String getWorksId() {
        return WorksId;
    }

    public void setWorksId(String worksId) {
        WorksId = worksId;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPinNo() {
        return PinNo;
    }

    public void setPinNo(String pinNo) {
        PinNo = pinNo;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }

    public String getCPMobileNo() {
        return CPMobileNo;
    }

    public void setCPMobileNo(String CPMobileNo) {
        this.CPMobileNo = CPMobileNo;
    }

    public String getBenStatus() {
        return BenStatus;
    }

    public void setBenStatus(String benStatus) {
        BenStatus = benStatus;
    }

    public String getIsVerifiedOrgAdm() {
        return IsVerifiedOrgAdm;
    }

    public void setIsVerifiedOrgAdm(String isVerifiedOrgAdm) {
        IsVerifiedOrgAdm = isVerifiedOrgAdm;
    }

    public String getVerifiedRemarksOrgAdm() {
        return VerifiedRemarksOrgAdm;
    }

    public void setVerifiedRemarksOrgAdm(String verifiedRemarksOrgAdm) {
        VerifiedRemarksOrgAdm = verifiedRemarksOrgAdm;
    }

    public String getVerifiedRemarksTypeOrgAdm() {
        return VerifiedRemarksTypeOrgAdm;
    }

    public void setVerifiedRemarksTypeOrgAdm(String verifiedRemarksTypeOrgAdm) {
        VerifiedRemarksTypeOrgAdm = verifiedRemarksTypeOrgAdm;
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
