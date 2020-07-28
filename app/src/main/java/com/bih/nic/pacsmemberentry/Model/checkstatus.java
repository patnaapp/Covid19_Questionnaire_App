package com.bih.nic.pacsmemberentry.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class checkstatus implements KvmSerializable, Serializable {
    private String today="";
    private String total="";
    private String PatientId="";
    private String PatientName="";
    private String FHName="";
    private String PatientMobNo="";
    private String Address="";
    private String ImpDate="";
    private String MapDate="";
    private String IsInActive="";

    public static Class<checkstatus> Check_CLASS = checkstatus.class;

    public checkstatus(SoapObject sobj)
    {

        this.today=sobj.getProperty("today").toString();
        this.total=sobj.getProperty("total").toString();
        this.PatientId=sobj.getProperty("PatientId").toString();
        this.PatientName=sobj.getProperty("PatientName").toString();
        this.FHName=sobj.getProperty("FHName").toString();
        this.PatientMobNo=sobj.getProperty("PatientMobNo").toString();
        this.Address=sobj.getProperty("Address").toString();
        this.ImpDate=sobj.getProperty("ImpDate").toString();
        this.MapDate=sobj.getProperty("MapDate").toString();
        this.IsInActive=sobj.getProperty("IsInActive").toString();


    }

    public checkstatus() {

    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getFHName() {
        return FHName;
    }

    public void setFHName(String FHName) {
        this.FHName = FHName;
    }

    public String getPatientMobNo() {
        return PatientMobNo;
    }

    public void setPatientMobNo(String patientMobNo) {
        PatientMobNo = patientMobNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImpDate() {
        return ImpDate;
    }

    public void setImpDate(String impDate) {
        ImpDate = impDate;
    }

    public String getMapDate() {
        return MapDate;
    }

    public void setMapDate(String mapDate) {
        MapDate = mapDate;
    }

    public String getIsInActive() {
        return IsInActive;
    }

    public void setIsInActive(String isInActive) {
        IsInActive = isInActive;
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
