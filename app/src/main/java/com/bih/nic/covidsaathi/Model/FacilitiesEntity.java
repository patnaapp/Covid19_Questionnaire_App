package com.bih.nic.covidsaathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class FacilitiesEntity implements KvmSerializable, Serializable {

    public static Class<FacilitiesEntity> Facility_CLASS = FacilitiesEntity.class;
    private String HostpitalId = "";
    private String Type = "";
    private String NameHn = "";
    private String Name = "";
    private String Bed = "";
    private String Available = "";
    private String MapGroup = "";
    private String LevelType = "";
    private String CenterType = "";



    public FacilitiesEntity(SoapObject obj, String status) {

            this.HostpitalId = obj.getProperty("HostpitalId").toString();
            this.Type = obj.getProperty("Type").toString();
            this.NameHn = obj.getProperty("NameHn").toString();
            this.Name = obj.getProperty("Name").toString();
            this.Bed = obj.getProperty("Bed").toString();
            this.Available = obj.getProperty("Available").toString();
            //this.MapGroup = obj.getProperty("MapGroup").toString();
            this.LevelType = obj.getProperty("LevelType").toString();
            this.CenterType = obj.getProperty("CenterType").toString();

    }

    public String getHostpitalId() {
        return HostpitalId;
    }

    public void setHostpitalId(String hostpitalId) {
        HostpitalId = hostpitalId;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
