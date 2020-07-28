package com.bih.nic.covidsaathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class SkillMaster implements KvmSerializable {

    public static Class<SkillMaster> SkillMaster_CLASS = SkillMaster.class;

    private String Id="";
    private String SkillName = "";
    private String SkillNameHn = "";


    public SkillMaster(SoapObject res1) {
        this.Id=res1.getProperty("Id").toString();
        this.SkillName=res1.getProperty("SkillName").toString();
        this.SkillNameHn=res1.getProperty("SkillNameHn").toString();

    }

    public SkillMaster() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSkillName() {
        return SkillName;
    }

    public void setSkillName(String skillName) {
        SkillName = skillName;
    }

    public String getSkillNameHn() {
        return SkillNameHn;
    }

    public void setSkillNameHn(String skillNameHn) {
        SkillNameHn = skillNameHn;
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
