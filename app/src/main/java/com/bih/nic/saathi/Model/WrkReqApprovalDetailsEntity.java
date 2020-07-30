package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class WrkReqApprovalDetailsEntity implements KvmSerializable, Serializable {

    public static Class<WrkReqApprovalDetailsEntity> RequirementApproval_CLASS = WrkReqApprovalDetailsEntity.class;
    private String WorksRegId = "";
    private String NoOfPerson = "";
    private String Active = "";
    private String Experiance = "";
    private String ExperianceMax = "";
    private String Gender = "";
    private String SkillId = "";
    private String skillSub = "";
    private String Salary = "";
    private String SalaryMax = "";
    private String SkillCategoryHn = "";
    private String SkillNameHn = "";



    public WrkReqApprovalDetailsEntity(SoapObject obj) {

        this.WorksRegId = obj.getProperty("WorksRegId").toString();
        this.NoOfPerson = obj.getProperty("NoOfPerson").toString();
        this.Active = obj.getProperty("Active").toString();
        this.Experiance = obj.getProperty("Experiance").toString();
        this.ExperianceMax = obj.getProperty("ExperianceMax").toString();
       // this.vchMobile = obj.getProperty("Location").toString();
        this.Gender = obj.getProperty("Gender").toString();
        this.SkillId = obj.getProperty("SkillId").toString();
        this.skillSub = obj.getProperty("skillSub").toString();
        this.Salary = obj.getProperty("Salary").toString();
        this.SalaryMax = obj.getProperty("SalaryMax").toString();
        this.SkillCategoryHn = obj.getProperty("SkillCategoryHn").toString();
        this.SkillNameHn = obj.getProperty("SkillNameHn").toString();


    }

    public static Class<WrkReqApprovalDetailsEntity> getRequirementApproval_CLASS() {
        return RequirementApproval_CLASS;
    }

    public static void setRequirementApproval_CLASS(Class<WrkReqApprovalDetailsEntity> requirementApproval_CLASS) {
        RequirementApproval_CLASS = requirementApproval_CLASS;
    }

    public String getWorksRegId() {
        return WorksRegId;
    }

    public void setWorksRegId(String worksRegId) {
        WorksRegId = worksRegId;
    }

    public String getNoOfPerson() {
        return NoOfPerson;
    }

    public void setNoOfPerson(String noOfPerson) {
        NoOfPerson = noOfPerson;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getExperiance() {
        return Experiance;
    }

    public void setExperiance(String experiance) {
        Experiance = experiance;
    }

    public String getExperianceMax() {
        return ExperianceMax;
    }

    public void setExperianceMax(String experianceMax) {
        ExperianceMax = experianceMax;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getSkillId() {
        return SkillId;
    }

    public void setSkillId(String skillId) {
        SkillId = skillId;
    }

    public String getSkillSub() {
        return skillSub;
    }

    public void setSkillSub(String skillSub) {
        this.skillSub = skillSub;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getSalaryMax() {
        return SalaryMax;
    }

    public void setSalaryMax(String salaryMax) {
        SalaryMax = salaryMax;
    }

    public String getSkillCategoryHn() {
        return SkillCategoryHn;
    }

    public void setSkillCategoryHn(String skillCategoryHn) {
        SkillCategoryHn = skillCategoryHn;
    }

    public String getSkillNameHn() {
        return SkillNameHn;
    }

    public void setSkillNameHn(String skillNameHn) {
        SkillNameHn = skillNameHn;
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
