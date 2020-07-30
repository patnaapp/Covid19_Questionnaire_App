package com.bih.nic.saathi.Model;

import java.io.Serializable;

public class SwasthyaDistrictCount implements Serializable {
    private SwasthyaDataEntity total;

    public SwasthyaDistrictCount(SwasthyaDataEntity total) {
        this.total = total;
    }

    public SwasthyaDataEntity getTotal() {
        return total;
    }

    public void setTotal(SwasthyaDataEntity total) {
        this.total = total;
    }
}
