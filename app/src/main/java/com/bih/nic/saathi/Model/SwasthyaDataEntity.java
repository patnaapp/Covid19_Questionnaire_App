package com.bih.nic.saathi.Model;

import java.io.Serializable;

public class SwasthyaDataEntity implements Serializable {

    private String confirmed;
    private String deceased;
    private String migrated="";
    private String recovered;
    private String tested;

    public SwasthyaDataEntity(String confirmed, String deceased, String migrated, String recovered, String tested) {
        this.confirmed = confirmed;
        this.deceased = deceased;
        this.migrated = migrated;
        this.recovered = recovered;
        this.tested = tested;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getMigrated() {
        return migrated;
    }

    public void setMigrated(String migrated) {
        this.migrated = migrated;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTested() {
        return tested;
    }

    public void setTested(String tested) {
        this.tested = tested;
    }
}
