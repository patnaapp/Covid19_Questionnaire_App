package com.bih.nic.pacsmemberentry;

import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;

public class BenfiList implements Serializable {

    private String beneficiary_id;
    private String beneficiery_name;
    private String aadharNumber;
    private String uid;
    public static Class<BenfiList> BENFICLASS = BenfiList.class;

    private String blockcode;
    private String blocjname;
    private String distcode;
    private String distname;
    private String panchcode;
    private String panchname;
    private String AccountNo;
    private String uidStatus;
    private String NameInPass;
    private String entryby;
    private String Id;
    private String ModifiedName;
    private String LifeCertificate;
    private String UIDName;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String ben_Mobile;
    private String ward;
    private String EntryDate;
    private String EntryBy;
    private String NameASPerAdhaar;
    private String yearOfBorth;
    private String Latitude;
    private String Longitude;


    public BenfiList(SoapObject final_object) {
        this.beneficiary_id=final_object.getProperty("beneficiary_number").toString();
        this.beneficiery_name=final_object.getProperty("first_name").toString();
        this.aadharNumber=final_object.getProperty("ADHAR_NO").toString();
        this.AccountNo=final_object.getProperty("account_number").toString();
        this.uidStatus=final_object.getProperty("UID_STATUS").toString();
        this.NameInPass=final_object.getProperty("NameInPassbook").toString();
        this.UIDName=final_object.getProperty("UIDName").toString();

    }

    public BenfiList() {

    }

    public String getBeneficiary_id() {
        return beneficiary_id;
    }

    public void setBeneficiary_id(String beneficiary_id) {
        this.beneficiary_id = beneficiary_id;
    }

    public String getBeneficiery_name() {
        return beneficiery_name;
    }

    public void setBeneficiery_name(String beneficiery_name) {
        this.beneficiery_name = beneficiery_name;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getUid() {
        return uid;
    }

    public String getBlockcode() {
        return blockcode;
    }

    public void setBlockcode(String blockcode) {
        this.blockcode = blockcode;
    }

    public String getBlocjname() {
        return blocjname;
    }

    public void setBlocjname(String blocjname) {
        this.blocjname = blocjname;
    }

    public String getDistcode() {
        return distcode;
    }

    public void setDistcode(String distcode) {
        this.distcode = distcode;
    }

    public String getDistname() {
        return distname;
    }

    public void setDistname(String distname) {
        this.distname = distname;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public String getPanchcode() {
        return panchcode;
    }

    public void setPanchcode(String panchcode) {
        this.panchcode = panchcode;
    }

    public String getPanchname() {
        return panchname;
    }

    public void setPanchname(String panchname) {
        this.panchname = panchname;
    }

    public String getUidStatus() {
        return uidStatus;
    }

    public void setUidStatus(String uidStatus) {
        this.uidStatus = uidStatus;
    }

    public String getNameInPass() {
        return NameInPass;
    }

    public void setNameInPass(String nameInPass) {
        NameInPass = nameInPass;
    }

    public String getEntryby() {
        return entryby;
    }

    public void setEntryby(String entryby) {
        this.entryby = entryby;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getModifiedName() {
        return ModifiedName;
    }

    public void setModifiedName(String modifiedName) {
        ModifiedName = modifiedName;
    }

    public String getLifeCertificate() {
        return LifeCertificate;
    }

    public void setLifeCertificate(String lifeCertificate) {
        LifeCertificate = lifeCertificate;
    }

    public String getUIDName() {
        return UIDName;
    }

    public void setUIDName(String UIDName) {
        this.UIDName = UIDName;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public String getPhoto4() {
        return photo4;
    }

    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }

    public String getBen_Mobile() {
        return ben_Mobile;
    }

    public void setBen_Mobile(String ben_Mobile) {
        this.ben_Mobile = ben_Mobile;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }

    public String getEntryBy() {
        return EntryBy;
    }

    public void setEntryBy(String entryBy) {
        EntryBy = entryBy;
    }

    public String getNameASPerAdhaar() {
        return NameASPerAdhaar;
    }

    public void setNameASPerAdhaar(String nameASPerAdhaar) {
        NameASPerAdhaar = nameASPerAdhaar;
    }

    public String getYearOfBorth() {
        return yearOfBorth;
    }

    public void setYearOfBorth(String yearOfBorth) {
        this.yearOfBorth = yearOfBorth;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }
}
