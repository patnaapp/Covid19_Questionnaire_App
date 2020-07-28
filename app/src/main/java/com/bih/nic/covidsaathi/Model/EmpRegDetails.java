package com.bih.nic.covidsaathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

public class EmpRegDetails  implements KvmSerializable, Serializable {

    private static final long serialVersionUID = 1L;

    public static Class<EmpRegDetails> UserDetails_CLASS = EmpRegDetails.class;
    String DistCode = "";
    String DistName = "";
    String OrgCode = "";
    String OrgName = "";
    String Crosspondance_address = "";
    String Contact_Person = "";
    String Mobile_Number = "";
    String Alternative_Mobile_Number = "";
    String Email = "";
    String Password = "";
    String Otp = "";

    public EmpRegDetails() {

        super();
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

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }

    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String orgCode) {
        OrgCode = orgCode;
    }

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String orgName) {
        OrgName = orgName;
    }

    public String getCrosspondance_address() {
        return Crosspondance_address;
    }

    public void setCrosspondance_address(String crosspondance_address) {
        Crosspondance_address = crosspondance_address;
    }

    public String getContact_Person() {
        return Contact_Person;
    }

    public void setContact_Person(String contact_Person) {
        Contact_Person = contact_Person;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public void setMobile_Number(String mobile_Number) {
        Mobile_Number = mobile_Number;
    }

    public String getAlternative_Mobile_Number() {
        return Alternative_Mobile_Number;
    }

    public void setAlternative_Mobile_Number(String alternative_Mobile_Number) {
        Alternative_Mobile_Number = alternative_Mobile_Number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getOtp() {
        return Otp;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }
}
