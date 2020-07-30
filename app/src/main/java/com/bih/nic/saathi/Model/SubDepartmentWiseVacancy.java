package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class SubDepartmentWiseVacancy implements KvmSerializable {

    public static Class<SubDepartmentWiseVacancy> DepartmentWiseVacancy_CLASS = SubDepartmentWiseVacancy.class;

    private String DeptId="";
    private String DeptName = "";
    private String DeptTotalWork = "";
    private String DeptTotalRequirement = "";

    private String Dept_WorkSiteName = "";
    private String Dept_Location = "";
    private String Dept_ContactPerson = "";
    private String Dept_CPMobileNo = "";

    private String DeptOrgId = "";
    private String Dept_orgname = "";
    private String Dept_orgTotalWork = "";
    private String Dept_orgTotlWorkReq = "";


    private String Dept_ComanyNameEn = "";
    private String Dept_NoOfPerson = "";
    private String Dept_Salary = "";
    private String Dept_SkillName = "";

    public SubDepartmentWiseVacancy(SoapObject res1, String i) {
        if(i.equalsIgnoreCase("1")) {
            this.DeptId = res1.getProperty("DeptID").toString();
            this.DeptName = res1.getProperty("DeptName").toString();
            this.DeptTotalWork = res1.getProperty("DeptTotalWork").toString();
            this.DeptTotalRequirement = res1.getProperty("DeptTotalRequirement").toString();
        }else if(i.equalsIgnoreCase("2")) {
            this.Dept_WorkSiteName = res1.getProperty("Dept_WorkSiteName").toString();
            this.Dept_Location = res1.getProperty("Dept_Location").toString();
            this.Dept_ContactPerson = res1.getProperty("Dept_ContactPerson").toString();
            this.Dept_CPMobileNo = res1.getProperty("Dept_CPMobileNo").toString();
        }else if(i.equalsIgnoreCase("3")) {
            this.Dept_WorkSiteName = res1.getProperty("Dept_WorkSiteName").toString();
            this.Dept_Location = res1.getProperty("Dept_Location").toString();
            this.Dept_ContactPerson = res1.getProperty("Dept_ContactPerson").toString();
            this.Dept_CPMobileNo = res1.getProperty("Dept_CPMobileNo").toString();
            this.Dept_ComanyNameEn = res1.getProperty("Dept_ComanyNameEn").toString();
            this.Dept_NoOfPerson = res1.getProperty("Dept_NoOfPerson").toString();
            this.Dept_Salary = res1.getProperty("Dept_Salary").toString();
            this.Dept_SkillName = res1.getProperty("Dept_SkillName").toString();
        }
    }


    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public String getDeptTotalWork() {
        return DeptTotalWork;
    }

    public void setDeptTotalWork(String deptTotalWork) {
        DeptTotalWork = deptTotalWork;
    }

    public String getDeptTotalRequirement() {
        return DeptTotalRequirement;
    }

    public void setDeptTotalRequirement(String deptTotalRequirement) {
        DeptTotalRequirement = deptTotalRequirement;
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

    public String getDeptOrgId() {
        return DeptOrgId;
    }

    public void setDeptOrgId(String deptOrgId) {
        DeptOrgId = deptOrgId;
    }

    public String getDept_orgname() {
        return Dept_orgname;
    }

    public void setDept_orgname(String dept_orgname) {
        Dept_orgname = dept_orgname;
    }

    public String getDept_orgTotalWork() {
        return Dept_orgTotalWork;
    }

    public void setDept_orgTotalWork(String dept_orgTotalWork) {
        Dept_orgTotalWork = dept_orgTotalWork;
    }

    public String getDept_orgTotlWorkReq() {
        return Dept_orgTotlWorkReq;
    }

    public void setDept_orgTotlWorkReq(String dept_orgTotlWorkReq) {
        Dept_orgTotlWorkReq = dept_orgTotlWorkReq;
    }

    public String getDept_WorkSiteName() {
        return Dept_WorkSiteName;
    }

    public void setDept_WorkSiteName(String dept_WorkSiteName) {
        Dept_WorkSiteName = dept_WorkSiteName;
    }

    public String getDept_Location() {
        return Dept_Location;
    }

    public void setDept_Location(String dept_Location) {
        Dept_Location = dept_Location;
    }

    public String getDept_ContactPerson() {
        return Dept_ContactPerson;
    }

    public void setDept_ContactPerson(String dept_ContactPerson) {
        Dept_ContactPerson = dept_ContactPerson;
    }

    public String getDept_CPMobileNo() {
        return Dept_CPMobileNo;
    }

    public void setDept_CPMobileNo(String dept_CPMobileNo) {
        Dept_CPMobileNo = dept_CPMobileNo;
    }

    public String getDept_ComanyNameEn() {
        return Dept_ComanyNameEn;
    }

    public void setDept_ComanyNameEn(String dept_ComanyNameEn) {
        Dept_ComanyNameEn = dept_ComanyNameEn;
    }

    public String getDept_NoOfPerson() {
        return Dept_NoOfPerson;
    }

    public void setDept_NoOfPerson(String dept_NoOfPerson) {
        Dept_NoOfPerson = dept_NoOfPerson;
    }

    public String getDept_Salary() {
        return Dept_Salary;
    }

    public void setDept_Salary(String dept_Salary) {
        Dept_Salary = dept_Salary;
    }

    public String getDept_SkillName() {
        return Dept_SkillName;
    }

    public void setDept_SkillName(String dept_SkillName) {
        Dept_SkillName = dept_SkillName;
    }
}
