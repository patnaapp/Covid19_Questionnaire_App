package com.bih.nic.pacsmemberentry.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class JobListEntity implements KvmSerializable, Serializable {

    public static Class<JobListEntity> JobListEntity_CLASS = JobListEntity.class;

    private String today = "";
    private String total = "";
    private String PatientId = "";
    private String PatientName = "";
    private String FHName = "";
    private String PatientMobNo = "";
    private String Address = "";
    private String ImpDate = "";
    private String MapDate = "";

    public JobListEntity(SoapObject obj) {

        this.today = obj.getProperty("today").toString();
        this.total = obj.getProperty("total").toString();
        this.PatientId = obj.getProperty("PatientId").toString();
        this.PatientName = obj.getProperty("PatientName").toString();
        this.FHName = obj.getProperty("FHName").toString();
        this.PatientMobNo = obj.getProperty("PatientMobNo").toString();
        this.Address = obj.getProperty("Address").toString();
        this.ImpDate = obj.getProperty("ImpDate").toString();
        this.MapDate = obj.getProperty("MapDate").toString();

    }

    public static Class<JobListEntity> getJobListEntity_CLASS() {
        return JobListEntity_CLASS;
    }

    public static void setJobListEntity_CLASS(Class<JobListEntity> jobListEntity_CLASS) {
        JobListEntity_CLASS = jobListEntity_CLASS;
    }

    public JobListEntity() {
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
}
