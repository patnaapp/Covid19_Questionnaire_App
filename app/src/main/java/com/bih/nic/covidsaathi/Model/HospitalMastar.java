package com.bih.nic.covidsaathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class HospitalMastar implements KvmSerializable {
    private String Hos_Code="";
    private String Hos_Name="";
    private String Cat_Code="";
    private String Dist_Code="";
    private String Type="";
    private String NameHn="";
    private String Bed="";
    private String Available="";
    private String MapGroup="";
    private String LevelType="";
    private String CenterType="";


    public static Class<CategoryMaster> USER_CLASS = CategoryMaster.class;

    public HospitalMastar(SoapObject sobj)
    {

        this.Hos_Code=sobj.getProperty("HostpitalId").toString();
        this.Type=sobj.getProperty("Type").toString();
        this.NameHn=sobj.getProperty("NameHn").toString();
        this.Hos_Name=sobj.getProperty("Name").toString();
        this.Bed=sobj.getProperty("Bed").toString();
        this.Available=sobj.getProperty("Available").toString();
        this.MapGroup=sobj.getProperty("MapGroup").toString();
        this.LevelType=sobj.getProperty("LevelType").toString();
        this.CenterType=sobj.getProperty("CenterType").toString();
        this.Dist_Code=sobj.getProperty("DistCode").toString();


    }

    public HospitalMastar() {

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

    public String getHos_Code() {
        return Hos_Code;
    }

    public void setHos_Code(String hos_Code) {
        Hos_Code = hos_Code;
    }

    public String getHos_Name() {
        return Hos_Name;
    }

    public void setHos_Name(String hos_Name) {
        Hos_Name = hos_Name;
    }

    public String getCat_Code() {
        return Cat_Code;
    }

    public void setCat_Code(String cat_Code) {
        Cat_Code = cat_Code;
    }

    public String getDist_Code() {
        return Dist_Code;
    }

    public void setDist_Code(String dist_Code) {
        Dist_Code = dist_Code;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getNameHn() {
        return NameHn;
    }

    public void setNameHn(String nameHn) {
        NameHn = nameHn;
    }

    public String getBed() {
        return Bed;
    }

    public void setBed(String bed) {
        Bed = bed;
    }

    public String getAvailable() {
        return Available;
    }

    public void setAvailable(String available) {
        Available = available;
    }

    public String getMapGroup() {
        return MapGroup;
    }

    public void setMapGroup(String mapGroup) {
        MapGroup = mapGroup;
    }

    public String getLevelType() {
        return LevelType;
    }

    public void setLevelType(String levelType) {
        LevelType = levelType;
    }

    public String getCenterType() {
        return CenterType;
    }

    public void setCenterType(String centerType) {
        CenterType = centerType;
    }
}
