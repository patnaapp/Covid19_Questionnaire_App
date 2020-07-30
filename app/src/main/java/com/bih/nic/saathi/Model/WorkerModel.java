package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.SoapObject;

public class WorkerModel {
    private String Succes;
    private String Status;
    private String WorksRegId;
    private String WorksId;
    private String SkillCategoryId;
    private String SkillCategoryHn;
    private String SkillId;
    private String NoOfPerson;
    private String EntryDate;
    private String StartDate;
    private String Experiance;
    private String Salary;
    private String SalaryMax;
    private String ExperianceMax;
    private String Gender;
    private String DistrictCode;
    private String DistrictNameHN;
    private String BlockCode;
    private String BlockNameHN;
    private String DistCount;
    private String ContactPersonHn;
    private String CPMobileNo;
    private String vchRegNum;
    private String vchName;
    private String vchAge;
    private String vchMobile;
    private String vchDist;
    private String vchBlock;
    private String vchPanchayat;
    private String vchAddress;
    private String vchGuardian_name;
    private String vchGuardian_number;
    private String intTypeOfWorker;
    private String intExpYears;
    private String SkillNameHn;
    private String WorkSiteName;
    private String PinNo;
    private String LabourFound;
    private String IsVerified;
    private String intvchExpYears;
    private String vchOtherProfession;
    private String intvchGender;


    public WorkerModel(SoapObject final_object) {

                 this.Succes=final_object.getProperty("msg").toString();
                this.Status=final_object.getProperty("Status").toString();
                this.WorksRegId=final_object.getProperty("WorksRegId").toString();
                this.WorksId=final_object.getProperty("WorksId").toString();
                this.SkillCategoryId=final_object.getProperty("SkillCategoryId").toString();
                this.SkillCategoryHn=final_object.getProperty("SkillCategoryHn").toString();
                this.SkillId=final_object.getProperty("SkillId").toString();
                this.NoOfPerson=final_object.getProperty("NoOfPerson").toString();
                this.EntryDate=final_object.getProperty("EntryDate").toString();
                this.StartDate=final_object.getProperty("StartDate").toString();
                this.Experiance=final_object.getProperty("Experiance").toString();
                this.Salary=final_object.getProperty("Salary").toString();
                this.SalaryMax=final_object.getProperty("SalaryMax").toString();
                this.ExperianceMax=final_object.getProperty("ExperianceMax").toString();
                this.Gender=final_object.getProperty("Gender").toString();
                this.DistrictCode=final_object.getProperty("DistrictCode").toString();
                this.DistrictNameHN=final_object.getProperty("DistrictNameHN").toString();

                this.ContactPersonHn=final_object.getProperty("ContactPersonHn").toString();
                this.CPMobileNo=final_object.getProperty("CPMobileNo").toString();
                this.BlockNameHN=final_object.getProperty("BlockNameHN").toString();

                this.WorkSiteName=final_object.getProperty("WorkSiteName").toString();
                this.PinNo=final_object.getProperty("PinNo").toString();
                this.LabourFound=final_object.getProperty("LabourFound").toString();
                this.intvchGender=final_object.getProperty("IsVerified").toString();

    }
    public WorkerModel(SoapObject final_object,String s) {

                 this.DistrictCode=final_object.getProperty("DistrictCode").toString();
                this.DistrictNameHN=final_object.getProperty("DistrictNameHN").toString();
                this.BlockNameHN=final_object.getProperty("BlockNameHN").toString();
                this.SkillNameHn=final_object.getProperty("SkillNameHn").toString();
                this.vchRegNum=final_object.getProperty("vchRegNum").toString();
                this.vchName=final_object.getProperty("vchName").toString();

                this.vchAge=final_object.getProperty("intAge").toString();
                this.vchMobile=final_object.getProperty("vchMobile").toString();
                this.vchDist=final_object.getProperty("vchDist").toString();
                this.vchBlock=final_object.getProperty("vchBlock").toString();
                this.vchPanchayat=final_object.getProperty("vchPanchayat").toString();
                this.vchAddress=final_object.getProperty("vchAddress").toString();
                this.vchGuardian_name=final_object.getProperty("vchGuardian_name").toString();
                this.vchGuardian_number=final_object.getProperty("vchGuardian_number").toString();
                this.intTypeOfWorker=final_object.getProperty("intTypeOfWorker").toString();
                this.intExpYears=final_object.getProperty("intExpYears").toString();
                this.intvchExpYears=final_object.getProperty("intExpYears").toString();
                this.vchOtherProfession=final_object.getProperty("vchOtherProfession").toString();
                this.intvchGender=final_object.getProperty("intGender").toString();


    }

    public String getSucces() {
        return Succes;
    }

    public void setSucces(String succes) {
        Succes = succes;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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

    public String getSkillCategoryId() {
        return SkillCategoryId;
    }

    public void setSkillCategoryId(String skillCategoryId) {
        SkillCategoryId = skillCategoryId;
    }

    public String getSkillCategoryHn() {
        return SkillCategoryHn;
    }

    public void setSkillCategoryHn(String skillCategoryHn) {
        SkillCategoryHn = skillCategoryHn;
    }

    public String getSkillId() {
        return SkillId;
    }

    public void setSkillId(String skillId) {
        SkillId = skillId;
    }

    public String getNoOfPerson() {
        return NoOfPerson;
    }

    public void setNoOfPerson(String noOfPerson) {
        NoOfPerson = noOfPerson;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getExperiance() {
        return Experiance;
    }

    public void setExperiance(String experiance) {
        Experiance = experiance;
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

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getDistrictNameHN() {
        return DistrictNameHN;
    }

    public void setDistrictNameHN(String districtNameHN) {
        DistrictNameHN = districtNameHN;
    }

    public String getDistCount() {
        return DistCount;
    }

    public void setDistCount(String distCount) {
        DistCount = distCount;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getBlockNameHN() {
        return BlockNameHN;
    }

    public void setBlockNameHN(String blockNameHN) {
        BlockNameHN = blockNameHN;
    }

    public String getContactPersonHn() {
        return ContactPersonHn;
    }

    public void setContactPersonHn(String contactPersonHn) {
        ContactPersonHn = contactPersonHn;
    }

    public String getCPMobileNo() {
        return CPMobileNo;
    }

    public void setCPMobileNo(String CPMobileNo) {
        this.CPMobileNo = CPMobileNo;
    }

    public String getVchRegNum() {
        return vchRegNum;
    }

    public void setVchRegNum(String vchRegNum) {
        this.vchRegNum = vchRegNum;
    }

    public String getVchName() {
        return vchName;
    }

    public void setVchName(String vchName) {
        this.vchName = vchName;
    }

    public String getVchAge() {
        return vchAge;
    }

    public void setVchAge(String vchAge) {
        this.vchAge = vchAge;
    }

    public String getVchMobile() {
        return vchMobile;
    }

    public void setVchMobile(String vchMobile) {
        this.vchMobile = vchMobile;
    }

    public String getVchDist() {
        return vchDist;
    }

    public void setVchDist(String vchDist) {
        this.vchDist = vchDist;
    }

    public String getVchBlock() {
        return vchBlock;
    }

    public void setVchBlock(String vchBlock) {
        this.vchBlock = vchBlock;
    }

    public String getVchPanchayat() {
        return vchPanchayat;
    }

    public void setVchPanchayat(String vchPanchayat) {
        this.vchPanchayat = vchPanchayat;
    }

    public String getVchAddress() {
        return vchAddress;
    }

    public void setVchAddress(String vchAddress) {
        this.vchAddress = vchAddress;
    }

    public String getVchGuardian_name() {
        return vchGuardian_name;
    }

    public void setVchGuardian_name(String vchGuardian_name) {
        this.vchGuardian_name = vchGuardian_name;
    }

    public String getVchGuardian_number() {
        return vchGuardian_number;
    }

    public void setVchGuardian_number(String vchGuardian_number) {
        this.vchGuardian_number = vchGuardian_number;
    }

    public String getIntTypeOfWorker() {
        return intTypeOfWorker;
    }

    public void setIntTypeOfWorker(String intTypeOfWorker) {
        this.intTypeOfWorker = intTypeOfWorker;
    }

    public String getIntExpYears() {
        return intExpYears;
    }

    public void setIntExpYears(String intExpYears) {
        this.intExpYears = intExpYears;
    }

    public String getSkillNameHn() {
        return SkillNameHn;
    }

    public void setSkillNameHn(String skillNameHn) {
        SkillNameHn = skillNameHn;
    }

    public String getWorkSiteName() {
        return WorkSiteName;
    }

    public void setWorkSiteName(String workSiteName) {
        WorkSiteName = workSiteName;
    }

    public String getPinNo() {
        return PinNo;
    }

    public void setPinNo(String pinNo) {
        PinNo = pinNo;
    }

    public String getLabourFound() {
        return LabourFound;
    }

    public void setLabourFound(String labourFound) {
        LabourFound = labourFound;
    }

    public String getIsVerified() {
        return IsVerified;
    }

    public void setIsVerified(String isVerified) {
        IsVerified = isVerified;
    }

    public String getIntvchExpYears() {
        return intvchExpYears;
    }

    public void setIntvchExpYears(String intvchExpYears) {
        this.intvchExpYears = intvchExpYears;
    }

    public String getVchOtherProfession() {
        return vchOtherProfession;
    }

    public void setVchOtherProfession(String vchOtherProfession) {
        this.vchOtherProfession = vchOtherProfession;
    }

    public String getIntvchGender() {
        return intvchGender;
    }

    public void setIntvchGender(String intvchGender) {
        this.intvchGender = intvchGender;
    }
}
