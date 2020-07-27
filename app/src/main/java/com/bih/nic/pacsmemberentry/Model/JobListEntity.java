package com.bih.nic.pacsmemberentry.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class JobListEntity implements KvmSerializable, Serializable {

    public static Class<JobListEntity> JobListEntity_CLASS = JobListEntity.class;
    private String id = "";
    private String vchRegNum = "";
    private String RequestedBy = "";
    private String WorksId = "";
    private String workSite = "";
    private String WorksRegId = "";
    private String SkillId = "";
    private String skillCategory = "";
    private String skillName = "";
    private String numberOfPerson = "";
    private String experience = "";
    private String experienceMax = "";
    private String salary = "";
    private String salaryMax = "";
    private String startDate = "";
    private String gendar = "";
    private String district = "";
    private String block = "";
    private String contactNumber = "";
    private String contactName = "";
    private String RequestedDate = "";
    private String isSelected = "";
    private String isAccepted = "";
    private String AcceptedDate = "";
    private String SelectedDate = "";

    public JobListEntity(SoapObject obj) {

        this.id = obj.getProperty("RequestId").toString();
        this.WorksId = obj.getProperty("WorksId").toString();
        this.workSite = obj.getProperty("WorkSiteNameHn").toString();
        this.WorksRegId = obj.getProperty("WorksRegId").toString();
        this.SkillId = obj.getProperty("SkillId").toString();
        //this.skillName = obj.getProperty("SkillNameHn").toString();

        //this.experience = obj.getProperty("Experiance").toString();
        this.vchRegNum = obj.getProperty("vchRegNum").toString();
        this.RequestedBy = obj.getProperty("RequestedBy").toString();
        this.RequestedDate = obj.getProperty("RequestedDate").toString();
        this.isAccepted = obj.getProperty("IsAccepted").toString();
        this.AcceptedDate = obj.getProperty("AcceptedDate").toString();
        this.SelectedDate = obj.getProperty("SelectedDate").toString();
        this.isSelected = obj.getProperty("IsSelected").toString();
//        this.numberOfPerson = obj.getProperty("NoOfPerson").toString();

        this.district = obj.getProperty("DistrictNameHN").toString();
        this.block = obj.getProperty("BlockNameHN").toString();
        this.skillCategory = obj.getProperty("SkillCategoryHn").toString();
        this.skillName = obj.getProperty("SkillNameHn").toString();
        this.experience = obj.getProperty("Experiance").toString();
        this.experienceMax = obj.getProperty("ExperianceMax").toString();
        this.salary = obj.getProperty("Salary1").toString();
        this.salaryMax = obj.getProperty("SalaryMax").toString();
        this.startDate = obj.getProperty("StartDate").toString();
        this.gendar = obj.getProperty("Gender_NameHN").toString();
        this.numberOfPerson = obj.getProperty("NoOfPerson").toString();
        //this.workSite = obj.getProperty("WorkSiteNameHn1").toString();
        this.contactNumber = obj.getProperty("CPMobileNo").toString();
        this.contactName = obj.getProperty("ContactPersonHn").toString();

    }

    public static Class<JobListEntity> getJobListEntity_CLASS() {
        return JobListEntity_CLASS;
    }

    public static void setJobListEntity_CLASS(Class<JobListEntity> jobListEntity_CLASS) {
        JobListEntity_CLASS = jobListEntity_CLASS;
    }

    public String getSelectedDate() {
        return SelectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        SelectedDate = selectedDate;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }

    public String getAcceptedDate() {
        return AcceptedDate;
    }

    public void setAcceptedDate(String acceptedDate) {
        AcceptedDate = acceptedDate;
    }

    public String getVchRegNum() {
        return vchRegNum;
    }

    public void setVchRegNum(String vchRegNum) {
        this.vchRegNum = vchRegNum;
    }

    public String getRequestedBy() {
        return RequestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        RequestedBy = requestedBy;
    }

    public String getWorksId() {
        return WorksId;
    }

    public void setWorksId(String worksId) {
        WorksId = worksId;
    }

    public String getWorksRegId() {
        return WorksRegId;
    }

    public void setWorksRegId(String worksRegId) {
        WorksRegId = worksRegId;
    }

    public String getSkillId() {
        return SkillId;
    }

    public void setSkillId(String skillId) {
        SkillId = skillId;
    }

    public String getRequestedDate() {
        return RequestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        RequestedDate = requestedDate;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getWorkSite() {
        return workSite;
    }

    public void setWorkSite(String workSite) {
        this.workSite = workSite;
    }

    public String getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(String skillCategory) {
        this.skillCategory = skillCategory;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(String numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExperienceMax() {
        return experienceMax;
    }

    public void setExperienceMax(String experienceMax) {
        this.experienceMax = experienceMax;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(String salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getGendar() {
        return gendar;
    }

    public void setGendar(String gendar) {
        this.gendar = gendar;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public JobListEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
