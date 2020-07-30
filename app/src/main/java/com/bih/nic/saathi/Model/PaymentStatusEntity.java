package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class PaymentStatusEntity implements KvmSerializable, Serializable {

    public static Class<PaymentStatusEntity> PaymentStatusEntity_CLASS = PaymentStatusEntity.class;

    private String msg = "";
    private Boolean Status = false;
    private String DistrictCode = "";
    private String BlockCode = "";
    private String MobileNo = "";
    private String PanchayatCode = "";
    private String VillageCode = "";
    private String WardNo = "";
    private String IsQRT14Days = "";
    private String eupi_BenNameasPerBank = "";
    private String BeneficiaryName = "";
    private String FHName = "";
    private String AccountNumber = "";
    private String IFSC = "";
    private String BankName = "";
    private String eupi_UTR_Status = "";
    private String PaymentStatus = "";
    private String eupi_StatusRemarks = "";

    public PaymentStatusEntity(SoapObject obj) {
        this.Status = Boolean.parseBoolean(obj.getProperty("Status").toString());

        this.msg = obj.getProperty("msg").toString();
        this.DistrictCode = obj.getProperty("DistrictCode").toString();
        this.BlockCode = obj.getProperty("BlockCode").toString();
        this.MobileNo = obj.getProperty("MobileNo").toString();
        this.PanchayatCode = obj.getProperty("PanchayatCode").toString();
        this.VillageCode = obj.getProperty("VillageCode").toString();
        this.WardNo = obj.getProperty("WardNo").toString();
        this.IsQRT14Days = obj.getProperty("IsQRT14Days").toString();
        this.eupi_BenNameasPerBank = obj.getProperty("eupi_BenNameasPerBank").toString();
        this.BeneficiaryName = obj.getProperty("BeneficiaryName").toString();
        this.FHName = obj.getProperty("FHName").toString();
        this.AccountNumber = obj.getProperty("AccountNumber").toString();
        this.IFSC = obj.getProperty("IFSC").toString();
        this.BankName = obj.getProperty("BankName").toString();
        this.eupi_UTR_Status = obj.getProperty("eupi_UTR_Status").toString();
        this.PaymentStatus = obj.getProperty("PaymentStatus").toString();
        this.eupi_StatusRemarks = obj.getProperty("eupi_StatusRemarks").toString();
    }

    public PaymentStatusEntity() {
    }

    public static Class<PaymentStatusEntity> getPaymentStatusEntity_CLASS() {
        return PaymentStatusEntity_CLASS;
    }

    public static void setPaymentStatusEntity_CLASS(Class<PaymentStatusEntity> paymentStatusEntity_CLASS) {
        PaymentStatusEntity_CLASS = paymentStatusEntity_CLASS;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public String getVillageCode() {
        return VillageCode;
    }

    public void setVillageCode(String villageCode) {
        VillageCode = villageCode;
    }

    public String getWardNo() {
        return WardNo;
    }

    public void setWardNo(String wardNo) {
        WardNo = wardNo;
    }

    public String getIsQRT14Days() {
        return IsQRT14Days;
    }

    public void setIsQRT14Days(String isQRT14Days) {
        IsQRT14Days = isQRT14Days;
    }

    public String getEupi_BenNameasPerBank() {
        return eupi_BenNameasPerBank;
    }

    public void setEupi_BenNameasPerBank(String eupi_BenNameasPerBank) {
        this.eupi_BenNameasPerBank = eupi_BenNameasPerBank;
    }

    public String getBeneficiaryName() {
        return BeneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        BeneficiaryName = beneficiaryName;
    }

    public String getFHName() {
        return FHName;
    }

    public void setFHName(String FHName) {
        this.FHName = FHName;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getEupi_UTR_Status() {
        return eupi_UTR_Status;
    }

    public void setEupi_UTR_Status(String eupi_UTR_Status) {
        this.eupi_UTR_Status = eupi_UTR_Status;
    }

    public String getEupi_StatusRemarks() {
        return eupi_StatusRemarks;
    }

    public void setEupi_StatusRemarks(String eupi_StatusRemarks) {
        this.eupi_StatusRemarks = eupi_StatusRemarks;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
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
