package com.bih.nic.pacsmemberentry.Model;

import java.io.Serializable;

public class Upload_Questionnaire_entity implements Serializable {

    public static Class<Upload_Questionnaire_entity> Upload_Questionnaire_CLASS = Upload_Questionnaire_entity.class;


    private String entry_by="";
    private String supervisor_id="";
    private String patient_id="";
    private String ques_id="";
    private String answer_id="";
    private String entry_date="";
    private String lat="";
    private String longi="";
    private String appver="";


    public Upload_Questionnaire_entity(String entry_by, String supervisor_id, String patient_id, String ques_id, String answer_id, String entry_date, String lat, String longi, String appver) {
        this.entry_by = entry_by;
        this.supervisor_id = supervisor_id;
        this.patient_id = patient_id;
        this.ques_id = ques_id;
        this.answer_id = answer_id;
        this.entry_date = entry_date;
        this.lat = lat;
        this.longi = longi;
        this.appver = appver;
    }

    public String getEntry_by() {
        return entry_by;
    }

    public void setEntry_by(String entry_by) {
        this.entry_by = entry_by;
    }

    public String getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(String supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getQues_id() {
        return ques_id;
    }

    public void setQues_id(String ques_id) {
        this.ques_id = ques_id;
    }

    public String getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(String answer_id) {
        this.answer_id = answer_id;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getAppver() {
        return appver;
    }

    public void setAppver(String appver) {
        this.appver = appver;
    }
}
