package com.bih.nic.pacsmemberentry.Model;

import org.ksoap2.serialization.SoapObject;

public class ward_model {

    private String wardId;
    private String wardName;
    private String benBindbyWaed;
    private String benBindPanch_code;
    public static Class<ward_model> WARD_MODEL_CLASS= ward_model.class;
    public ward_model(SoapObject final_object) {
        this.wardId=final_object.getProperty("WardCode").toString();
        this.wardName=final_object.getProperty("WardName").toString();
        this.benBindPanch_code=final_object.getProperty("PANCHAYATCODE").toString();
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getBenBindbyWaed() {
        return benBindbyWaed;
    }

    public void setBenBindbyWaed(String benBindbyWaed) {
        this.benBindbyWaed = benBindbyWaed;
    }


    public String getBenBindPanch_code() {
        return benBindPanch_code;
    }

    public void setBenBindPanch_code(String benBindPanch_code) {
        this.benBindPanch_code = benBindPanch_code;
    }
}
