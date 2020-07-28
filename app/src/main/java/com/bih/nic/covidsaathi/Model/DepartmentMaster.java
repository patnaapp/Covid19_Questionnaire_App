package com.bih.nic.covidsaathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class DepartmentMaster implements KvmSerializable {

    public static Class<DepartmentMaster> DeptMaster_CLASS = DepartmentMaster.class;

    private String DeptId="";
    private String DeptName = "";
    private String DeptNameHn = "";
    private String Dept_Abb = "";
    private String Name_Symbol = "";
    private String Status = "";

    public DepartmentMaster(SoapObject res1) {
        this.DeptId=res1.getProperty("DeptId").toString();
        this.DeptName=res1.getProperty("DeptName").toString();
        this.DeptNameHn=res1.getProperty("DeptNameHn").toString();
        this.Dept_Abb=res1.getProperty("Dept_Abb").toString();
        this.Name_Symbol=res1.getProperty("Name_Symbol").toString();
        this.Status=res1.getProperty("Status").toString();
    }

    public DepartmentMaster() {
    }

    public static Class<DepartmentMaster> getDeptMaster_CLASS() {
        return DeptMaster_CLASS;
    }

    public static void setDeptMaster_CLASS(Class<DepartmentMaster> deptMaster_CLASS) {
        DeptMaster_CLASS = deptMaster_CLASS;
    }

    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public String getDeptNameHn() {
        return DeptNameHn;
    }

    public void setDeptNameHn(String deptNameHn) {
        DeptNameHn = deptNameHn;
    }

    public String getDept_Abb() {
        return Dept_Abb;
    }

    public void setDept_Abb(String dept_Abb) {
        Dept_Abb = dept_Abb;
    }

    public String getName_Symbol() {
        return Name_Symbol;
    }

    public void setName_Symbol(String name_Symbol) {
        Name_Symbol = name_Symbol;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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
