package com.bih.nic.covidsaathi.Model;

import org.ksoap2.serialization.SoapObject;

public class workListModel {
    
    private String Work_Id;
    private String Worl_Name;
    private String Worksite_nameHn;

    public static Class<workListModel> workListModelClass= workListModel.class;


    public workListModel(SoapObject final_object) {
        this.Work_Id=final_object.getProperty("WorksId").toString();
        this.Worl_Name=final_object.getProperty("WorkSiteName").toString();
        this.Worksite_nameHn=final_object.getProperty("WorkSiteNameHn").toString();
    }
    public String getWork_Id() {
        return Work_Id;
    }

    public void setWork_Id(String work_Id) {
        Work_Id = work_Id;
    }

    public String getWorl_Name() {
        return Worl_Name;
    }

    public void setWorl_Name(String worl_Name) {
        Worl_Name = worl_Name;
    }

    public String getWorksite_nameHn() {
        return Worksite_nameHn;
    }

    public void setWorksite_nameHn(String worksite_nameHn) {
        Worksite_nameHn = worksite_nameHn;
    }
}
