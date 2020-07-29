package com.bih.nic.covidsaathi.webservice;
import com.bih.nic.covidsaathi.Model.CovidDataResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface Api {

    @GET("data.json")
    Call<CovidDataResponse> getCovidData();
//
//    @POST("verifylogin")
//    Call<TubewellUserDetail> AuthenticateTubewellUser(@Body JsonObject param);
}
