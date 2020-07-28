package com.bih.nic.pacsmemberentry.ui.supervisor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;
import com.bih.nic.pacsmemberentry.GlobalVariables;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;
import com.bih.nic.pacsmemberentry.ui.Login;
import com.bih.nic.pacsmemberentry.ui.patient.CovidQuestionnaire_Activity;


public class Supervisor_HomeActivity extends Activity {

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    TextView tv_email,tv_dept_name,tv_version;
    TextView tv_name,tv_f_name,tv_address,tv_block_name,tv_panchayat,tv_village,tv_mobile,tv_superviser;
    LinearLayout ll_first,ll_username,aprove_rjct_worksite,ll_emp_reports;
    String PatientName="",Address="",SupervisorName="", Mobile="", FHName="", Covid19TestingDate="", ProfileImg="",CompanyName="", UserId,UserRole="",Block_Code="",lvlone_id="",lvltwo_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sup_home);

        // getActionBar().hide();
        Utiilties.setStatusBarColor(this);
        dataBaseHelper=new DataBaseHelper(Supervisor_HomeActivity.this);
        tv_name=findViewById(R.id.tv_name);
        tv_f_name=findViewById(R.id.tv_f_name);
        tv_address=findViewById(R.id.tv_address);
        tv_block_name=findViewById(R.id.tv_block_name);
        tv_panchayat=findViewById(R.id.tv_panchayat);
        tv_superviser=findViewById(R.id.tv_superviser);
        tv_mobile=findViewById(R.id.tv_mobile);
        tv_version=(TextView) findViewById(R.id.tv_version);

        String version = Utiilties.getAppVersion(this);
        if(version != null){
            tv_version.setText("App Version "+version);
        }else{
            tv_version.setText("");
        }

        PatientName=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("PatientName", "");
        Address=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Address", "");


        //DistName=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("DistName", "");
        SupervisorName=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("SupervisorName", "");
        Mobile=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Mobile", "");
        FHName=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("FHName", "");
        Covid19TestingDate=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Covid19TestingDate", "");
        UserRole=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserRole", "");
        String username = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserName", "");

        tv_name.setText(PatientName);
        tv_f_name.setText(FHName);
        tv_address.setText(Address);
        tv_mobile.setText(Mobile);
        tv_superviser.setText(SupervisorName);

//        if(UserRole.equals("ORGADM"))
//        {
//            ll_first.setVisibility(View.GONE);
//            ll_username.setVisibility(View.VISIBLE);
//            aprove_rjct_worksite.setVisibility(View.VISIBLE);
//
//        }
//        else if (UserRole.equals("DSTADM"))
//        {
//            ll_first.setVisibility(View.GONE);
//            ll_emp_reports.setVisibility(View.GONE);
//            ll_username.setVisibility(View.VISIBLE);
//            aprove_rjct_worksite.setVisibility(View.VISIBLE);
//
//        }
//
//        else
//        {
//            ll_username.setVisibility(View.GONE);
//            aprove_rjct_worksite.setVisibility(View.GONE);
//        }
    }

    public void OnClick_goToLoginScreen(View view)
    {
        new AlertDialog.Builder(this)
                .setTitle("लॉग आउट ?")
                .setMessage("क्या आप वाकई एप्लिकेशन से लॉगआउट करना चाहते हैं \n ")
                .setCancelable(false)
                .setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        confirmLogout();
                    }
                })
                .setNegativeButton("नहीं", null)
                .show();
    }

    private void confirmLogout()
    {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("isLogin",false).commit();
        SharedPreferences settings = this.getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
        GlobalVariables.isLogin=false;
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //intent.putExtra("From", AppConstant.HQ);
        startActivity(intent);
        finish();
    }

    public void onExit()
    {
        new AlertDialog.Builder(this)
                .setTitle("अलर्ट!!")
                .setMessage("क्या आप ऐप बन्द करना चाहते हैं??\n ")
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
        Intent i=new Intent(Supervisor_HomeActivity.this,CovidQuestionnaire_Activity.class);
        startActivity(i);
    }

    public  void onViewDeptJobVacency(View view){

    }

    public void  onJobOfferReport(View view){


    }

    public void onViewWorkOrgDetail(View view){

    }

    public void onApproveWorkSite(View view){



    }
}
