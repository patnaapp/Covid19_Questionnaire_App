package com.bih.nic.saathi.webservice;
import com.bih.nic.saathi.Model.SwasthyaDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("data.json")
    Call<SwasthyaDataResponse> getCovidData();
//
//    @POST("verifylogin")
//    Call<TubewellUserDetail> AuthenticateTubewellUser(@Body JsonObject param);
}
