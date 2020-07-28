package com.bih.nic.pacsmemberentry.webservice;

import com.google.gson.JsonObject;

import bih.in.mwrdbihar.entity.AppVersion;
import bih.in.mwrdbihar.entity.TubewellUserDetail;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("getAppDetail")
    Call<AppVersion> getAppVersion();

    @POST("verifylogin")
    Call<TubewellUserDetail> AuthenticateTubewellUser(@Body JsonObject param);
}
