package com.bih.nic.saathi.Model;

public class SwasthyaDataResponse {

    private BiharSwasthyaData BR;

    public SwasthyaDataResponse(BiharSwasthyaData BR) {
        this.BR = BR;
    }

    public BiharSwasthyaData getBR() {
        return BR;
    }

    public void setBR(BiharSwasthyaData BR) {
        this.BR = BR;
    }
}
