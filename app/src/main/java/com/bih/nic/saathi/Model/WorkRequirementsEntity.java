package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class WorkRequirementsEntity implements KvmSerializable, Serializable {
    public static Class<WorkRequirementsEntity> Work_req_CLASS = WorkRequirementsEntity.class;
    private String skill_categId="";
    private String skill_categ="";
    private String skill_sub_categId="";
    private String skill_sub_categ="";
    private String no_of_persons="";
    private String min_exp="";
    private String max_exp="";
    private String min_salary="";
    private String max_salary="";
    private String genderId="";
    private String gender="";
    private String start_date ="";
    private String isActiveId="";
    private String isActive="";
    private String pincode="";
    private String supervisor_nm_en="";
    private String supervisor_nm_hn="";
    private String supervisor_mob="";
    private String salaryTypeId="";
    private String salaryTypename="";
    private String WorksRegId="";
    private String WorksId="";
    private String FYearID="";
    private String SkillOther="";



    public WorkRequirementsEntity() {

    }

    public WorkRequirementsEntity(SoapObject res1) {
        this.WorksRegId=res1.getProperty("WorksRegId").toString();
        this.WorksId=res1.getProperty("WorksId").toString();
        this.skill_categId=res1.getProperty("SkillId").toString();
        this.no_of_persons=res1.getProperty("NoOfPerson").toString();
        this.isActiveId=res1.getProperty("Active").toString();
        if (this.isActiveId.equals("Y")){
            this.isActive="Yes";
        }
        else if (this.isActiveId.equals("N")){
            this.isActive="No";
        }

        this.skill_sub_categId=res1.getProperty("skillSub").toString();
        this.start_date=res1.getProperty("StartDate").toString();
        this.min_exp=res1.getProperty("Experiance").toString();
        this.salaryTypeId=res1.getProperty("SalaryType").toString();
        if (this.salaryTypeId.equals("D")){
            this.salaryTypename="Per Day";
        }
        if (this.salaryTypeId.equals("M")){
            this.salaryTypename="Per Month";
        }
        if (this.salaryTypeId.equals("Y")){
            this.salaryTypename="Per Year";
        }
        this.min_salary=res1.getProperty("Salary").toString();
        this.max_salary=res1.getProperty("SalaryMax").toString();
        this.max_exp=res1.getProperty("ExperianceMax").toString();
        this.gender=res1.getProperty("Gender").toString();
        this.FYearID=res1.getProperty("FYearID").toString();
        this.SkillOther=res1.getProperty("SkillOther").toString();


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

    public static Class<WorkRequirementsEntity> getWork_req_CLASS() {
        return Work_req_CLASS;
    }

    public static void setWork_req_CLASS(Class<WorkRequirementsEntity> work_req_CLASS) {
        Work_req_CLASS = work_req_CLASS;
    }

    public String getSalaryTypeId() {
        return salaryTypeId;
    }

    public void setSalaryTypeId(String salaryTypeId) {
        this.salaryTypeId = salaryTypeId;
    }

    public String getSalaryTypename() {
        return salaryTypename;
    }

    public void setSalaryTypename(String salaryTypename) {
        this.salaryTypename = salaryTypename;
    }

    public String getSkill_categId() {
        return skill_categId;
    }

    public void setSkill_categId(String skill_categId) {
        this.skill_categId = skill_categId;
    }

    public String getSkill_sub_categId() {
        return skill_sub_categId;
    }

    public void setSkill_sub_categId(String skill_sub_categId) {
        this.skill_sub_categId = skill_sub_categId;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getIsActiveId() {
        return isActiveId;
    }

    public void setIsActiveId(String isActiveId) {
        this.isActiveId = isActiveId;
    }

    public String getSkill_categ() {
        return skill_categ;
    }

    public void setSkill_categ(String skill_categ) {
        this.skill_categ = skill_categ;
    }

    public String getSkill_sub_categ() {
        return skill_sub_categ;
    }

    public void setSkill_sub_categ(String skill_sub_categ) {
        this.skill_sub_categ = skill_sub_categ;
    }

    public String getNo_of_persons() {
        return no_of_persons;
    }

    public void setNo_of_persons(String no_of_persons) {
        this.no_of_persons = no_of_persons;
    }

    public String getMin_exp() {
        return min_exp;
    }

    public void setMin_exp(String min_exp) {
        this.min_exp = min_exp;
    }

    public String getMax_exp() {
        return max_exp;
    }

    public void setMax_exp(String max_exp) {
        this.max_exp = max_exp;
    }

    public String getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(String min_salary) {
        this.min_salary = min_salary;
    }

    public String getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(String max_salary) {
        this.max_salary = max_salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
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

    public String getFYearID() {
        return FYearID;
    }

    public void setFYearID(String FYearID) {
        this.FYearID = FYearID;
    }
}
