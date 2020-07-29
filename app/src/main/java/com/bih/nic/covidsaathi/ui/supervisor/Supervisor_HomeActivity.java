package com.bih.nic.covidsaathi.ui.supervisor;

import android.app.Activity;
import android.app.AlertDialog;
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

import com.bih.nic.covidsaathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.covidsaathi.GlobalVariables;
import com.bih.nic.covidsaathi.R;
import com.bih.nic.covidsaathi.Utiilties;
import com.bih.nic.covidsaathi.ui.PreLoginActivity;
import com.bih.nic.covidsaathi.ui.patient.AddHospitalActivity;


public class Supervisor_HomeActivity extends Activity {

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    TextView tv_email,tv_dept_name,tv_version,tv_name_sup;
    TextView tv_name,tv_f_name,tv_address,tv_block_name,tv_panchayat,tv_village,tv_mobile,tv_superviser;
    LinearLayout ll_upload_photo,ll_view_patient;
    String PatientName="",Address="",SupervisorName="", Mobile="", FHName="", Covid19TestingDate="", ProfileImg="",CompanyName="", UserId,UserRole="",Block_Code="",lvlone_id="",lvltwo_id="",SupervisorId="";

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

        tv_version= findViewById(R.id.tv_version);
        ll_upload_photo= findViewById(R.id.ll_upload_photo);
        ll_view_patient= findViewById(R.id.ll_view_patient);

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

    public void  onJobOfferReport(View view)
    {

    }

    public void onViewWorkOrgDetail(View view)
    {

    }

    public void onApproveWorkSite(View view)
    {

    }
}
