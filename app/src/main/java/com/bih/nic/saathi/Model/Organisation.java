package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class Organisation implements KvmSerializable, Serializable {

    private static final long serialVersionUID = 1L;

    public static Class<Organisation> Orgnisation_CLASS = Organisation.class;
    private String _OrgCode = "";
    private String _OrgName = "";


    public Organisation(SoapObject obj) {

        this._OrgCode = obj.getProperty("_OrgCode").toString();
        this._OrgName = obj.getProperty("_OrgName").toString();

    }

    public Organisation() {

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

    public String get_OrgCode() {
        return _OrgCode;
    }

    public void set_OrgCode(String _OrgCode) {
        this._OrgCode = _OrgCode;
    }

    public String get_OrgName() {
        return _OrgName;
    }

    public void set_OrgName(String _OrgName) {
        this._OrgName = _OrgName;
    }
}
