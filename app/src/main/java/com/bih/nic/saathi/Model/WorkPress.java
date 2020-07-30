package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class WorkPress implements KvmSerializable, Serializable {

    public static Class<WorkPress> JobOffer_CLASS = WorkPress.class;
    private String DistrictCode = "";
    private String DistrictName = "";
    private String Role = "";
    private String Status = "";
    private String Dept_Code = "";
    private String Dept_Name = "";
    private String Karya_Asthal = "";
    private String No_Of_Vacency = "";


    public WorkPress(SoapObject obj) {

        this.DistrictCode = obj.getProperty("DistrictCode").toString();
        this.DistrictName = obj.getProperty("DistrictName").toString();
        this.Role = obj.getProperty("Role").toString();
        this.Status = obj.getProperty("Status").toString();
        this.Dept_Code = obj.getProperty("Dept_Code").toString();
        this.Dept_Name = obj.getProperty("Dept_Name").toString();
        this.Karya_Asthal = obj.getProperty("Karya_Asthal").toString();
        this.No_Of_Vacency = obj.getProperty("No_Of_Vacency").toString();
        //this.skillName = obj.getProperty("SkillNameHn").toString();


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

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDept_Code() {
        return Dept_Code;
    }

    public void setDept_Code(String dept_Code) {
        Dept_Code = dept_Code;
    }

    public String getDept_Name() {
        return Dept_Name;
    }

    public void setDept_Name(String dept_Name) {
        Dept_Name = dept_Name;
    }

    public String getKarya_Asthal() {
        return Karya_Asthal;
    }

    public void setKarya_Asthal(String karya_Asthal) {
        Karya_Asthal = karya_Asthal;
    }

    public String getNo_Of_Vacency() {
        return No_Of_Vacency;
    }

    public void setNo_Of_Vacency(String no_Of_Vacency) {
        No_Of_Vacency = no_Of_Vacency;
    }
}
