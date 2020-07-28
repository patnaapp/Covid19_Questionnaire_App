package com.bih.nic.covidsaathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class WorkDetailsEntity implements KvmSerializable, Serializable {
    public static Class<WorkDetailsEntity> Work_details_CLASS = WorkDetailsEntity.class;
    private String Fin_yr="";
    private String related_dept="";
    private String dist_id="";
    private String dist_name="";
    private String blk_id="";
    private String blk_name="";
    private String work_site_eng="";
    private String work_site_hn="";
    private String Location_en ="";
    private String Location_hn="";
    private String pincode="";
    private String supervisor_nm_en="";
    private String supervisor_nm_hn="";
    private String supervisor_mob="";
    private String WorksId="";
    private String DeptId="";
    private String OfficeId="";
    private String Workstatus="";

    private String WorkSiteNameHn="";
    private String LocationHn="";
    private String ContactPersonHn="";
    private String RelatedDept="";
    private String FYearID="";
    private String BlockCode="";
    private String DistCode="";


    public WorkDetailsEntity() {

    }

    public WorkDetailsEntity(SoapObject res1)
    {

        this.WorksId=res1.getProperty("WorksId").toString();
        this.DeptId=res1.getProperty("DeptId").toString();
        this.OfficeId=res1.getProperty("OfficeId").toString();
        this.work_site_eng=res1.getProperty("WorkSiteName").toString();
        this.Location_en=res1.getProperty("Location").toString();
        this.pincode=res1.getProperty("PinNo").toString();
        this.supervisor_nm_en=res1.getProperty("ContactPerson").toString();
        this.supervisor_mob=res1.getProperty("CPMobileNo").toString();
        this.Workstatus=res1.getProperty("Workstatus").toString();
        this.WorkSiteNameHn=res1.getProperty("WorkSiteNameHn").toString();
        this.LocationHn=res1.getProperty("LocationHn").toString();
        this.ContactPersonHn=res1.getProperty("ContactPersonHn").toString();
        this.RelatedDept=res1.getProperty("RelatedDept").toString();
        this.FYearID=res1.getProperty("FYearID").toString();
        this.BlockCode=res1.getProperty("BlockCode").toString();
        this.DistCode=res1.getProperty("DistCode").toString();

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

    public static Class<WorkDetailsEntity> getWork_details_CLASS() {
        return Work_details_CLASS;
    }

    public static void setWork_details_CLASS(Class<WorkDetailsEntity> work_details_CLASS) {
        Work_details_CLASS = work_details_CLASS;
    }

    public String getFin_yr() {
        return Fin_yr;
    }

    public void setFin_yr(String fin_yr) {
        Fin_yr = fin_yr;
    }

    public String getRelated_dept() {
        return related_dept;
    }

    public void setRelated_dept(String related_dept) {
        this.related_dept = related_dept;
    }

    public String getDist_id() {
        return dist_id;
    }

    public void setDist_id(String dist_id) {
        this.dist_id = dist_id;
    }

    public String getDist_name() {
        return dist_name;
    }

    public void setDist_name(String dist_name) {
        this.dist_name = dist_name;
    }

    public String getBlk_id() {
        return blk_id;
    }

    public void setBlk_id(String blk_id) {
        this.blk_id = blk_id;
    }

    public String getBlk_name() {
        return blk_name;
    }

    public void setBlk_name(String blk_name) {
        this.blk_name = blk_name;
    }

    public String getWork_site_eng() {
        return work_site_eng;
    }

    public void setWork_site_eng(String work_site_eng) {
        this.work_site_eng = work_site_eng;
    }

    public String getWork_site_hn() {
        return work_site_hn;
    }

    public void setWork_site_hn(String work_site_hn) {
        this.work_site_hn = work_site_hn;
    }

    public String getLocation_en() {
        return Location_en;
    }

    public void setLocation_en(String location_en) {
        Location_en = location_en;
    }

    public String getLocation_hn() {
        return Location_hn;
    }

    public void setLocation_hn(String location_hn) {
        Location_hn = location_hn;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getSupervisor_nm_en() {
        return supervisor_nm_en;
    }

    public void setSupervisor_nm_en(String supervisor_nm_en) {
        this.supervisor_nm_en = supervisor_nm_en;
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

    public String getWorksId() {
        return WorksId;
    }

    public void setWorksId(String worksId) {
        WorksId = worksId;
    }

    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public String getOfficeId() {
        return OfficeId;
    }

    public void setOfficeId(String officeId) {
        OfficeId = officeId;
    }

    public String getWorkstatus() {
        return Workstatus;
    }

    public void setWorkstatus(String workstatus) {
        Workstatus = workstatus;
    }

    public String getWorkSiteNameHn() {
        return WorkSiteNameHn;
    }

    public void setWorkSiteNameHn(String workSiteNameHn) {
        WorkSiteNameHn = workSiteNameHn;
    }

    public String getLocationHn() {
        return LocationHn;
    }

    public void setLocationHn(String locationHn) {
        LocationHn = locationHn;
    }

    public String getContactPersonHn() {
        return ContactPersonHn;
    }

    public void setContactPersonHn(String contactPersonHn) {
        ContactPersonHn = contactPersonHn;
    }

    public String getRelatedDept() {
        return RelatedDept;
    }

    public void setRelatedDept(String relatedDept) {
        RelatedDept = relatedDept;
    }

    public String getFYearID() {
        return FYearID;
    }

    public void setFYearID(String FYearID) {
        this.FYearID = FYearID;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
    }
}
