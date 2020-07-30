package com.bih.nic.saathi.Model;

public class BiharSwasthyaData {

    private SwasthyaDataEntity total;
    private SwasthyaDistrictData districts;

    public BiharSwasthyaData(SwasthyaDataEntity total, SwasthyaDistrictData districts) {
        this.total = total;
        this.districts = districts;
    }

    public SwasthyaDistrictData getDistricts() {
        return districts;
    }

    public void setDistricts(SwasthyaDistrictData districts) {
        this.districts = districts;
    }

    public SwasthyaDataEntity getTotal() {
        return total;
    }

    public void setTotal(SwasthyaDataEntity total) {
        this.total = total;
    }
}
