package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class BenDetails implements KvmSerializable, Serializable {
    public static Class<BenDetails> USER_CLASS = BenDetails.class;
    private String Password="";
    private String DistrictCode="";
    private String DistrictName="";
    private String BlockCode="";
    private String BlockName="";
    private String PanchayatCode="";
    private String PanchayatName="";
    private String msg="";
    private String Status="";
    private String LastVisitedOn="";
    private String vchRegNum="";
    private String vchName="";
    private String intAge="";
    private String vchMobile="";
    private String vchAddress="";
    private String vchPhoto ="";
    private String is_Dignity_Kit="";
    private String is_having_fever="";
    private String is_having_cough="";
    private String is_respiratory_destress="";
    private String is_having_diarrhea="";
    private String is_having_ache="";
    private String is_having_headache="";
    private String stmCreatedOn="";
    private String intCreatedBy="";
    private String stmUpdatedOn="";
    private String intUpdatedBy="";
    private String bitDeletedFlag="";
    private String satusVal="";
    private String statusDate1="";
    private String statusDate="";
    private String intVechileType="";
    private String vchVechileNumber="";
    private String intRegistrationType="";
    private String intCovidSymptom="";
    private String intSyncId="";
    private String intGender="";
    private String vchOtherWorkerType="";
    private String vchAadhaar="";
    private String vchBankAccount="";
    private String vchBankName="";
    private String vchIfsc="";
    private String intCategory="";
    private String intQualification="";
    private String intQualification_name="";
    private String intTestingLab="";
    private String intPatientKept="";
    private String intHstatusCreatedBy="";
    private String dtmHstatusCreatedOn="";
    private String dtsamplingDate1="";
    private String dtsamplingDate="";
    private String intExpYears  ="";
    private String vchWorkAddress="";
    private String intExpMonths="";
    private String intTravelMode="";
    private String intTravelFare="";
    private String OTP="";
    private String OtpVerify="";
    private String is_having_cold="";
    private String intBloodTest="";
    private String intBloodTest1="";
    private String vchGuardian_name="";
    private String vchGuardian_number="";
    private String Skill_Id;
    private String Skill_Name;
    private String SubSkillId;
    private String SubSkillName;
    private String User_RegId;private String NameAsPerAdhaar;


    public BenDetails() {

    }

    public BenDetails(SoapObject res1) {
        this.Password=res1.getProperty("Password").toString();
        this.DistrictCode=res1.getProperty("DistrictCode").toString();
        this.DistrictName=res1.getProperty("DistrictName").toString();
        this.BlockCode=res1.getProperty("BlockCode").toString();
        this.BlockName=res1.getProperty("BlockName").toString();
        this.PanchayatCode=res1.getProperty("PanchayatCode").toString();
        this.PanchayatName=res1.getProperty("PanchayatName").toString();
        this.msg=res1.getProperty("msg").toString();
        this.Status=res1.getProperty("Status").toString();
        this.LastVisitedOn=res1.getProperty("LastVisitedOn").toString();
        this.vchRegNum=res1.getProperty("vchRegNum").toString();
        this.vchName=res1.getProperty("vchName").toString();
        this.intAge=res1.getProperty("intAge").toString();
        this.vchMobile=res1.getProperty("vchMobile").toString();
        this.vchGuardian_name=res1.getProperty("vchGuardian_name").toString();
        this.vchGuardian_number=res1.getProperty("vchGuardian_number").toString();
        this.intGender=res1.getProperty("intGender").toString();


        this.SubSkillId=res1.getProperty("SkillCategoryId").toString();
        this.Skill_Id=res1.getProperty("intTypeOfWorker").toString();
        this.Skill_Name=res1.getProperty("SkillCategory").toString();
        this.SubSkillName=res1.getProperty("SkillName").toString();

        this.vchAddress=res1.getProperty("vchAddress").toString();
        this.vchPhoto=res1.getProperty("Photo").toString();
        this.is_Dignity_Kit=res1.getProperty("is_Dignity_Kit").toString();
        this.is_having_fever=res1.getProperty("is_having_fever").toString();
        this.is_having_cough=res1.getProperty("is_having_cough").toString();
        this.is_respiratory_destress=res1.getProperty("is_respiratory_destress").toString();
        this.is_having_diarrhea=res1.getProperty("is_having_diarrhea").toString();
        this.is_having_ache=res1.getProperty("is_having_ache").toString();
        this.is_having_headache=res1.getProperty("is_having_headache").toString();
        this.stmCreatedOn=res1.getProperty("stmCreatedOn").toString();
        this.intCreatedBy=res1.getProperty("intCreatedBy").toString();
        this.stmUpdatedOn=res1.getProperty("stmUpdatedOn").toString();
      /*  this.intUpdatedBy=res1.getProperty("intUpdatedBy").toString();
        this.bitDeletedFlag=res1.getProperty("bitDeletedFlag").toString();
        this.satusVal=res1.getProperty("satusVal").toString();
        this.statusDate1=res1.getProperty("statusDate1").toString();
        this.statusDate=res1.getProperty("statusDate").toString();
        this.intVechileType=res1.getProperty("intVechileType").toString();
        this.vchVechileNumber=res1.getProperty("vchVechileNumber").toString();
      */  this.intRegistrationType=res1.getProperty("intRegistrationType").toString();
        /*this.intCovidSymptom=res1.getProperty("intCovidSymptom").toString();
        this.intSyncId=res1.getProperty("intSyncId").toString();
      */  this.vchOtherWorkerType=res1.getProperty("vchOtherWorkerType").toString();
        this.vchAadhaar=res1.getProperty("vchAadhaar").toString();
        this.vchAadhaar=res1.getProperty("vchAadhaar").toString();
        this.vchBankAccount=res1.getProperty("vchBankAccount").toString();
        this.vchBankName=res1.getProperty("vchBankName").toString();
        this.vchIfsc=res1.getProperty("vchIfsc").toString();
        this.intCategory=res1.getProperty("intCategory").toString();
        this.intQualification_name=res1.getProperty("VCHEDUCATION").toString();
       /* this.intTestingLab=res1.getProperty("intTestingLab").toString();
        this.intPatientKept=res1.getProperty("intPatientKept").toString();
        this.intHstatusCreatedBy=res1.getProperty("intHstatusCreatedBy").toString();
        this.dtmHstatusCreatedOn=res1.getProperty("dtmHstatusCreatedOn").toString();
        this.dtsamplingDate1=res1.getProperty("dtsamplingDate1").toString();
        this.dtsamplingDate=res1.getProperty("dtsamplingDate").toString();
      */  this.intExpYears=res1.getProperty("intExpYears").toString();
        this.vchWorkAddress=res1.getProperty("vchWorkAddress").toString();
        this.intExpMonths=res1.getProperty("intExpMonths").toString();

        //Aadhaar
        this.NameAsPerAdhaar=res1.getProperty("NameAsPerAadhar").toString();


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

    public String getIntGender() {
        return intGender;
    }

    public void setIntGender(String intGender) {
        this.intGender = intGender;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public String getPanchayatName() {
        return PanchayatName;
    }

    public void setPanchayatName(String panchayatName) {
        PanchayatName = panchayatName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getLastVisitedOn() {
        return LastVisitedOn;
    }

    public void setLastVisitedOn(String lastVisitedOn) {
        LastVisitedOn = lastVisitedOn;
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

    public String getIntAge() {
        return intAge;
    }

    public void setIntAge(String intAge) {
        this.intAge = intAge;
    }

    public String getVchMobile() {
        return vchMobile;
    }

    public void setVchMobile(String vchMobile) {
        this.vchMobile = vchMobile;
    }

    public String getVchAddress() {
        return vchAddress;
    }

    public void setVchAddress(String vchAddress) {
        this.vchAddress = vchAddress;
    }

    public String getVchPhoto() {
        return vchPhoto;
    }

    public void setVchPhoto(String vchPhoto) {
        this.vchPhoto = vchPhoto;
    }

    public String getIs_Dignity_Kit() {
        return is_Dignity_Kit;
    }

    public void setIs_Dignity_Kit(String is_Dignity_Kit) {
        this.is_Dignity_Kit = is_Dignity_Kit;
    }

    public String getIs_having_fever() {
        return is_having_fever;
    }

    public void setIs_having_fever(String is_having_fever) {
        this.is_having_fever = is_having_fever;
    }

    public String getIs_having_cough() {
        return is_having_cough;
    }

    public void setIs_having_cough(String is_having_cough) {
        this.is_having_cough = is_having_cough;
    }

    public String getIs_respiratory_destress() {
        return is_respiratory_destress;
    }

    public void setIs_respiratory_destress(String is_respiratory_destress) {
        this.is_respiratory_destress = is_respiratory_destress;
    }

    public String getIs_having_diarrhea() {
        return is_having_diarrhea;
    }

    public void setIs_having_diarrhea(String is_having_diarrhea) {
        this.is_having_diarrhea = is_having_diarrhea;
    }

    public String getIs_having_ache() {
        return is_having_ache;
    }

    public void setIs_having_ache(String is_having_ache) {
        this.is_having_ache = is_having_ache;
    }

    public String getIs_having_headache() {
        return is_having_headache;
    }

    public void setIs_having_headache(String is_having_headache) {
        this.is_having_headache = is_having_headache;
    }

    public String getStmCreatedOn() {
        return stmCreatedOn;
    }

    public void setStmCreatedOn(String stmCreatedOn) {
        this.stmCreatedOn = stmCreatedOn;
    }

    public String getIntCreatedBy() {
        return intCreatedBy;
    }

    public void setIntCreatedBy(String intCreatedBy) {
        this.intCreatedBy = intCreatedBy;
    }

    public String getStmUpdatedOn() {
        return stmUpdatedOn;
    }

    public void setStmUpdatedOn(String stmUpdatedOn) {
        this.stmUpdatedOn = stmUpdatedOn;
    }

    public String getIntUpdatedBy() {
        return intUpdatedBy;
    }

    public void setIntUpdatedBy(String intUpdatedBy) {
        this.intUpdatedBy = intUpdatedBy;
    }

    public String getBitDeletedFlag() {
        return bitDeletedFlag;
    }

    public void setBitDeletedFlag(String bitDeletedFlag) {
        this.bitDeletedFlag = bitDeletedFlag;
    }

    public String getSatusVal() {
        return satusVal;
    }

    public void setSatusVal(String satusVal) {
        this.satusVal = satusVal;
    }

    public String getStatusDate1() {
        return statusDate1;
    }

    public void setStatusDate1(String statusDate1) {
        this.statusDate1 = statusDate1;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public String getIntVechileType() {
        return intVechileType;
    }

    public void setIntVechileType(String intVechileType) {
        this.intVechileType = intVechileType;
    }

    public String getVchVechileNumber() {
        return vchVechileNumber;
    }

    public void setVchVechileNumber(String vchVechileNumber) {
        this.vchVechileNumber = vchVechileNumber;
    }

    public String getIntRegistrationType() {
        return intRegistrationType;
    }

    public void setIntRegistrationType(String intRegistrationType) {
        this.intRegistrationType = intRegistrationType;
    }

    public String getIntCovidSymptom() {
        return intCovidSymptom;
    }

    public void setIntCovidSymptom(String intCovidSymptom) {
        this.intCovidSymptom = intCovidSymptom;
    }

    public String getIntSyncId() {
        return intSyncId;
    }

    public void setIntSyncId(String intSyncId) {
        this.intSyncId = intSyncId;
    }

    public String getVchOtherWorkerType() {
        return vchOtherWorkerType;
    }

    public void setVchOtherWorkerType(String vchOtherWorkerType) {
        this.vchOtherWorkerType = vchOtherWorkerType;
    }

    public String getVchAadhaar() {
        return vchAadhaar;
    }

    public void setVchAadhaar(String vchAadhaar) {
        this.vchAadhaar = vchAadhaar;
    }

    public String getVchBankAccount() {
        return vchBankAccount;
    }

    public void setVchBankAccount(String vchBankAccount) {
        this.vchBankAccount = vchBankAccount;
    }

    public String getVchBankName() {
        return vchBankName;
    }

    public void setVchBankName(String vchBankName) {
        this.vchBankName = vchBankName;
    }

    public String getVchIfsc() {
        return vchIfsc;
    }

    public void setVchIfsc(String vchIfsc) {
        this.vchIfsc = vchIfsc;
    }

    public String getIntCategory() {
        return intCategory;
    }

    public void setIntCategory(String intCategory) {
        this.intCategory = intCategory;
    }

    public String getIntQualification() {
        return intQualification;
    }

    public void setIntQualification(String intQualification) {
        this.intQualification = intQualification;
    }

    public String getIntTestingLab() {
        return intTestingLab;
    }

    public void setIntTestingLab(String intTestingLab) {
        this.intTestingLab = intTestingLab;
    }

    public String getIntPatientKept() {
        return intPatientKept;
    }

    public void setIntPatientKept(String intPatientKept) {
        this.intPatientKept = intPatientKept;
    }

    public String getIntHstatusCreatedBy() {
        return intHstatusCreatedBy;
    }

    public void setIntHstatusCreatedBy(String intHstatusCreatedBy) {
        this.intHstatusCreatedBy = intHstatusCreatedBy;
    }

    public String getDtmHstatusCreatedOn() {
        return dtmHstatusCreatedOn;
    }

    public void setDtmHstatusCreatedOn(String dtmHstatusCreatedOn) {
        this.dtmHstatusCreatedOn = dtmHstatusCreatedOn;
    }

    public String getDtsamplingDate1() {
        return dtsamplingDate1;
    }

    public void setDtsamplingDate1(String dtsamplingDate1) {
        this.dtsamplingDate1 = dtsamplingDate1;
    }

    public String getDtsamplingDate() {
        return dtsamplingDate;
    }

    public void setDtsamplingDate(String dtsamplingDate) {
        this.dtsamplingDate = dtsamplingDate;
    }

    public String getIntExpYears() {
        return intExpYears;
    }

    public void setIntExpYears(String intExpYears) {
        this.intExpYears = intExpYears;
    }

    public String getVchWorkAddress() {
        return vchWorkAddress;
    }

    public void setVchWorkAddress(String vchWorkAddress) {
        this.vchWorkAddress = vchWorkAddress;
    }

    public String getIntExpMonths() {
        return intExpMonths;
    }

    public void setIntExpMonths(String intExpMonths) {
        this.intExpMonths = intExpMonths;
    }

    public String getIntTravelMode() {
        return intTravelMode;
    }

    public void setIntTravelMode(String intTravelMode) {
        this.intTravelMode = intTravelMode;
    }

    public String getIntTravelFare() {
        return intTravelFare;
    }

    public void setIntTravelFare(String intTravelFare) {
        this.intTravelFare = intTravelFare;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getOtpVerify() {
        return OtpVerify;
    }

    public void setOtpVerify(String otpVerify) {
        OtpVerify = otpVerify;
    }

    public String getIs_having_cold() {
        return is_having_cold;
    }

    public void setIs_having_cold(String is_having_cold) {
        this.is_having_cold = is_having_cold;
    }

    public String getIntBloodTest() {
        return intBloodTest;
    }

    public void setIntBloodTest(String intBloodTest) {
        this.intBloodTest = intBloodTest;
    }

    public String getIntBloodTest1() {
        return intBloodTest1;
    }

    public void setIntBloodTest1(String intBloodTest1) {
        this.intBloodTest1 = intBloodTest1;
    }

    public String getSkill_Id() {
        return Skill_Id;
    }

    public void setSkill_Id(String skill_Id) {
        Skill_Id = skill_Id;
    }

    public String getSkill_Name() {
        return Skill_Name;
    }

    public void setSkill_Name(String skill_Name) {
        Skill_Name = skill_Name;
    }

    public String getSubSkillId() {
        return SubSkillId;
    }

    public void setSubSkillId(String subSkillId) {
        SubSkillId = subSkillId;
    }

    public String getSubSkillName() {
        return SubSkillName;
    }

    public void setSubSkillName(String subSkillName) {
        SubSkillName = subSkillName;
    }

    public String getUser_RegId() {
        return User_RegId;
    }

    public void setUser_RegId(String user_RegId) {
        User_RegId = user_RegId;
    }

    public String getIntQualification_name() {
        return intQualification_name;
    }

    public void setIntQualification_name(String intQualification_name) {
        this.intQualification_name = intQualification_name;
    }

    public String getNameAsPerAdhaar() {
        return NameAsPerAdhaar;
    }

    public void setNameAsPerAdhaar(String nameAsPerAdhaar) {
        NameAsPerAdhaar = nameAsPerAdhaar;
    }
}
