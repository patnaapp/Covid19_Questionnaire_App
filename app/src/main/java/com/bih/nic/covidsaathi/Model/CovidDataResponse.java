package com.bih.nic.covidsaathi.Model;

public class CovidDataResponse {
    private BiharCovidData BR;

    public CovidDataResponse(BiharCovidData BR) {
        this.BR = BR;
    }

    public BiharCovidData getBR() {
        return BR;
    }

    public void setBR(BiharCovidData BR) {
        this.BR = BR;
    }
}
