package com.bih.nic.covidsaathi.ui.supervisor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.covidsaathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.covidsaathi.GlobalVariables;
import com.bih.nic.covidsaathi.Model.CovidDataEntity;
import com.bih.nic.covidsaathi.Model.CovidDataResponse;
import com.bih.nic.covidsaathi.R;
import com.bih.nic.covidsaathi.Utiilties;
import com.bih.nic.covidsaathi.ui.PreLoginActivity;
import com.bih.nic.covidsaathi.ui.SplashActivity;
import com.bih.nic.covidsaathi.ui.patient.AddHospitalActivity;
import com.bih.nic.covidsaathi.webservice.Api;
import com.bih.nic.covidsaathi.webservice.RetrofitClient;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Supervisor_HomeActivity extends Activity {

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    TextView tv_email,tv_dept_name,tv_version,tv_name_sup;
    TextView tv_confirmed,tv_recovered,tv_death;
    TextView tv_name,tv_f_name,tv_address,tv_block_name,tv_panchayat,tv_village,tv_mobile,tv_superviser;
    LinearLayout ll_upload_photo,ll_view_patient,ll_updates;
    String PatientName="",Address="",SupervisorName="", Mobile="", FHName="", Covid19TestingDate="", ProfileImg="",CompanyName="", UserId,UserRole="",Block_Code="",lvlone_id="",lvltwo_id="",SupervisorId="";


    CovidDataEntity covidData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sup_home);

        getActionBar().hide();
        Utiilties.setStatusBarColor(this);
        dataBaseHelper=new DataBaseHelper(Supervisor_HomeActivity.this);
        tv_name=findViewById(R.id.tv_name);
        tv_f_name=findViewById(R.id.tv_f_name);
        tv_address=findViewById(R.id.tv_address);
        tv_block_name=findViewById(R.id.tv_block_name);
        tv_panchayat=findViewById(R.id.tv_panchayat);
        tv_superviser=findViewById(R.id.tv_superviser);
        tv_mobile=findViewById(R.id.tv_mobile);
        tv_name_sup=findViewById(R.id.tv_name_sup);

        tv_confirmed=findViewById(R.id.tv_confirmed);
        tv_recovered=findViewById(R.id.tv_recovered);
        tv_death=findViewById(R.id.tv_death);

        tv_version= findViewById(R.id.tv_version);
        ll_upload_photo= findViewById(R.id.ll_upload_photo);
        ll_view_patient= findViewById(R.id.ll_view_patient);
        ll_updates= findViewById(R.id.ll_updates);

        String version = Utiilties.getAppVersion(this);
        if(version != null){
            tv_version.setText("App Version "+version);
        }else{
            tv_version.setText("");
        }


        SupervisorId=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("SupervisorId", "");
        SupervisorName=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("SupervisorName", "");
        UserRole=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user_role", "");

        Log.e("Role", UserRole);
        //String username = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserName", "");

        tv_superviser.setText(SupervisorName);

        if(UserRole.equals("SUP"))
        {
            tv_name_sup.setText("Supervisior Name");
            ll_upload_photo.setVisibility(View.GONE);
        }
        else if (UserRole.equals("DSTGPS"))
        {
            tv_name_sup.setText("Username");
            ll_view_patient.setVisibility(View.GONE);
        }

    }



    public void OnClick_goToLoginScreen(View view)
    {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout from account? \n ")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        confirmLogout();
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    }

    private void confirmLogout()
    {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("isLogin",false).commit();
        SharedPreferences settings = this.getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
        GlobalVariables.isLogin=false;
        Intent intent = new Intent(this, PreLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //intent.putExtra("From", AppConstant.HQ);
        startActivity(intent);
        finish();
    }

    public void onExit()
    {
        new AlertDialog.Builder(this)
                .setTitle("अलर्ट!!")
                .setMessage("क्या आप ऐप बन्द करना चाहते हैं??")
                .setCancelable(false)
                .setPositiveButton("हाँ", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        finish();
                    }
                })
                .setNegativeButton("नहीं", null)
                .show();
    }


    @Override
    public void onBackPressed()
    {
        //super.onBackPressed();
        onExit();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        fetchCovidData();
    }

    public  void onView_Patient(View view)
    {
        Intent i=new Intent(Supervisor_HomeActivity.this,JobSearchActivity.class);
        startActivity(i);
    }

    public void onGeoTagCentres(View view) {
        Intent i=new Intent(Supervisor_HomeActivity.this, AddHospitalActivity.class);
        startActivity(i);
    }

    public  void onViewDeptJobVacency(View view)
    {

    }

    public void fetchCovidData(){
        final ProgressDialog dialog = new ProgressDialog(this);
        //dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Fetching Covid Updates...");
        dialog.show();

        final Api request = RetrofitClient.getRetrofitInstance().create(Api.class);
        Call<CovidDataResponse> call = request.getCovidData();

        call.enqueue(new Callback<CovidDataResponse>() {
            @Override
            public void onResponse(Call<CovidDataResponse> call, Response<CovidDataResponse> response) {
                if (dialog.isShowing()) dialog.dismiss();

                covidData = response.body().getBR().getTotal();

                tv_confirmed.setText(covidData.getConfirmed());
                tv_recovered.setText(covidData.getRecovered());
                tv_death.setText(covidData.getDeceased());
                ll_updates.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<CovidDataResponse> call, Throwable t) {
                if (dialog.isShowing()) dialog.dismiss();
                Toast.makeText(Supervisor_HomeActivity.this, "Failed to updated Covide Data...", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
