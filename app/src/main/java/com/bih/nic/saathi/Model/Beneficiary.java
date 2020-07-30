package com.bih.nic.saathi.Model;

import java.io.Serializable;

public class Beneficiary implements Serializable {

    private String id="";
    private String BenId="";
    private String BenName="";
    private String BenAadhar="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBenId() {
        return BenId;
    }

    public void setBenId(String benId) {
        BenId = benId;
    }

    public String getBenName() {
        return BenName;
    }

    public void setBenName(String benName) {
        BenName = benName;
    }

    public String getBenAadhar() {
        return BenAadhar;
    }

    public void setBenAadhar(String benAadhar) {
        BenAadhar = benAadhar;
    }
}
