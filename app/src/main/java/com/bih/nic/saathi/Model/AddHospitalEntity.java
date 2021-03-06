package com.bih.nic.saathi.Model;

public class AddHospitalEntity {


    private String Dist_Code;
    private String Dist_Name;
    private String Cat_Code;
    private String Cat_Name = "";
    private String Hos_Code = "";
    private String Hos_Name = "";
    private String Image = "";
    private String Latitude = "";
    private String Longitude = "";
    private String LevelType_Id = "";
    private String Type_Id = "";
    byte[] pic;
    private String UserId;
    public static Class<AddHospitalEntity> USER_CLASS = AddHospitalEntity.class;

    public AddHospitalEntity() {

    }


    public String getDist_Code() {
        return Dist_Code;
    }

    public void setDist_Code(String dist_Code) {
        Dist_Code = dist_Code;
    }

    public String getDist_Name() {
        return Dist_Name;
    }

    public void setDist_Name(String dist_Name) {
        Dist_Name = dist_Name;
    }

    public String getCat_Code() {
        return Cat_Code;
    }

    public void setCat_Code(String cat_Code) {
        Cat_Code = cat_Code;
    }

    public String getCat_Name() {
        return Cat_Name;
    }

    public void setCat_Name(String cat_Name) {
        Cat_Name = cat_Name;
    }

    public String getHos_Code() {
        return Hos_Code;
    }

    public void setHos_Code(String hos_Code) {
        Hos_Code = hos_Code;
    }

    public String getHos_Name() {
        return Hos_Name;
    }

    public void setHos_Name(String hos_Name) {
        Hos_Name = hos_Name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLevelType_Id() {
        return LevelType_Id;
    }

    public void setLevelType_Id(String levelType_Id) {
        LevelType_Id = levelType_Id;
    }

    public String getType_Id() {
        return Type_Id;
    }

    public void setType_Id(String type_Id) {
        Type_Id = type_Id;
    }
}
