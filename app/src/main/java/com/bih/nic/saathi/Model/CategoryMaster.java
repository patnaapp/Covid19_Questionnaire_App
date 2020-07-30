package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class CategoryMaster implements KvmSerializable {
    private String Cat_id="";
    private String Cat_name="";
    private String Cat_name_HinDi="";

    public static Class<CategoryMaster> USER_CLASS = CategoryMaster.class;

    public CategoryMaster(SoapObject sobj)
    {

        this.Cat_id=sobj.getProperty("Cat_id").toString();
        this.Cat_name=sobj.getProperty("Cat_name").toString();


    }

    public CategoryMaster() {

    }

    public String getCat_id() {
        return Cat_id;
    }

    public void setCat_id(String cat_id) {
        Cat_id = cat_id;
    }

    public String getCat_name() {
        return Cat_name;
    }

    public void setCat_name(String cat_name) {
        Cat_name = cat_name;
    }

    public String getCat_name_HinDi() {
        return Cat_name_HinDi;
    }

    public void setCat_name_HinDi(String cat_name_HinDi) {
        Cat_name_HinDi = cat_name_HinDi;
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
