package com.bih.nic.saathi.Model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.Hashtable;

public class BlockJobOfferPostedEntity implements KvmSerializable, Serializable {

    public static Class<BlockJobOfferPostedEntity> BlockJobOffer_CLASS = BlockJobOfferPostedEntity.class;
    private String BlockCode = "";
    private String BlockName = "";
    private String ttlReg = "";
    private String TotalJobOffer = "";
    private String ttlRegA = "";
    private String ttlRegR = "";


    public BlockJobOfferPostedEntity(SoapObject obj) {

        this.BlockCode = obj.getProperty("BlockCode").toString();
        this.BlockName = obj.getProperty("BlockName").toString();
        this.ttlReg = obj.getProperty("ttlReg").toString();
        this.TotalJobOffer = obj.getProperty("TotalJobOffer").toString();
        this.ttlRegA = obj.getProperty("ttlRegA").toString();
        this.ttlRegR = obj.getProperty("ttlRegR").toString();
        //this.skillName = obj.getProperty("SkillNameHn").toString();

    }


    public static Class<BlockJobOfferPostedEntity> getJobOffer_CLASS() {
        return BlockJobOffer_CLASS;
    }

    public static void setJobOffer_CLASS(Class<BlockJobOfferPostedEntity> jobOffer_CLASS) {
        BlockJobOffer_CLASS = jobOffer_CLASS;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
    }

    public String getTtlReg() {
        return ttlReg;
    }

    public void setTtlReg(String ttlReg) {
        this.ttlReg = ttlReg;
    }

    public String getTotalJobOffer() {
        return TotalJobOffer;
    }

    public void setTotalJobOffer(String totalJobOffer) {
        TotalJobOffer = totalJobOffer;
    }

    public String getTtlRegA() {
        return ttlRegA;
    }

    public void setTtlRegA(String ttlRegA) {
        this.ttlRegA = ttlRegA;
    }

    public String getTtlRegR() {
        return ttlRegR;
    }

    public void setTtlRegR(String ttlRegR) {
        this.ttlRegR = ttlRegR;
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
