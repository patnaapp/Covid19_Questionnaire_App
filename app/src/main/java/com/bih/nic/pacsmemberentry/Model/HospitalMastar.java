package com.bih.nic.pacsmemberentry.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class HospitalMastar implements KvmSerializable {
    private String Hos_Code="";
    private String Hos_Name="";
    private String Cat_Code="";
    private String Dist_Code="";

    public static Class<CategoryMaster> USER_CLASS = CategoryMaster.class;

    public HospitalMastar(SoapObject sobj)
    {

        this.Hos_Code=sobj.getProperty("Hos_Code").toString();
        this.Hos_Name=sobj.getProperty("Hos_Name").toString();
        this.Cat_Code=sobj.getProperty("Cat_Code").toString();
        this.Dist_Code=sobj.getProperty("Dist_Code").toString();


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
}
