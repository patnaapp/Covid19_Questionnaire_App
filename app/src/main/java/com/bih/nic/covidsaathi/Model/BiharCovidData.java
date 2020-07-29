package com.bih.nic.covidsaathi.Model;

public class BiharCovidData {

    private CovidDataEntity total;

    public BiharCovidData(CovidDataEntity total) {
        this.total = total;
    }

    public CovidDataEntity getTotal() {
        return total;
    }

    public void setTotal(CovidDataEntity total) {
        this.total = total;
    }
}
