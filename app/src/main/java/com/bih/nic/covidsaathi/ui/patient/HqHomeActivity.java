package com.bih.nic.covidsaathi.ui.patient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.covidsaathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.covidsaathi.GlobalVariables;
import com.bih.nic.covidsaathi.Model.checkstatus;
import com.bih.nic.covidsaathi.R;
import com.bih.nic.covidsaathi.Utiilties;
import com.bih.nic.covidsaathi.WebserviceHelper;
import com.bih.nic.covidsaathi.ui.PreLoginActivity;


public class HqHomeActivity extends Activity {

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    TextView tv_email,tv_dept_name,tv_version;
    TextView tv_name,tv_f_name,tv_address,tv_block_name,tv_panchayat,tv_village,tv_mobile,tv_superviser;
    LinearLayout ll_first,ll_username,aprove_rjct_worksite,ll_emp_reports;
    String PatientName="",Address="",SupervisorName="", Mobile="", FHName="", Covid19TestingDate="", ProfileImg="",CompanyName="", UserId,UserRole="",Block_Code="",lvlone_id="",lvltwo_id="";
    String patientid="";
    checkstatus chk_status;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hq_home);

        // getActionBar().hide();
        Utiilties.setStatusBarColor(this);
        dataBaseHelper=new DataBaseHelper(HqHomeActivity.this);

        chk_status=new checkstatus();
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
        patientid=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("PatientId", "");


        //DistName=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("DistName", "");
        SupervisorName=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("SupervisorName", "");
        Mobile=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Mobile", "");
        FHName=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("FHName", "");
        Covid19TestingDate=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Covid19TestingDate", "");
        UserRole=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserRole", "");
        String username = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserName", "");

        new CheckStatusForSurvey().execute();

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

    public void OnClick_goToLoginScreen(View view){
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout from account? \n ")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        confirmLogout();
                    }
                })
                .setNegativeButton("No", null)
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
                .setTitle("Alert!!")
                .setMessage("Do you want to exit app??\n ")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        finish();
                    }
                })
                .setNegativeButton("YES", null)
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
        patientid=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("PatientId", "");
        Mobile=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Mobile", "");
        new CheckStatusForSurvey().execute();
        super.onResume();
    }

    public  void onSelfDiagonosis(View view)
    {
        if (Integer.parseInt(chk_status.getToday().toString())>0){
            AlertDialog.Builder ab = new AlertDialog.Builder(
                    HqHomeActivity.this);
            ab.setIcon(R.mipmap.ic_launcher);
            ab.setTitle("Already submitted");
            ab.setMessage("You have already submitted your diagnosis for today ");
            ab.setNegativeButton("[ OK ]", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {

                    dialog.dismiss();

                }
            });


            ab.show();
        }
        else {
            Intent i=new Intent(HqHomeActivity.this,CovidQuestionnaire_Activity.class);
            i.putExtra("data",chk_status);
            startActivity(i);
        }

    }

//    public  void onViewDeptJobVacency(View view){
//
//    }

    public void  on_ViewHospitals(View view)
    {

        Intent i=new Intent(HqHomeActivity.this,View_Facilities_activity.class);
        i.putExtra("facility_code","H");
        startActivity(i);
    }

    public void on_IsolationCentres(View view)
    {
        Intent i=new Intent(HqHomeActivity.this,View_Facilities_activity.class);
        i.putExtra("facility_code","I");
        startActivity(i);
    }

    public void onTestCentres(View view)
    {
        Intent i=new Intent(HqHomeActivity.this,View_Facilities_activity.class);
        i.putExtra("facility_code","T");
        startActivity(i);

    }

    private class CheckStatusForSurvey extends AsyncTask<String, Void, checkstatus>
    {
        private final ProgressDialog dialog = new ProgressDialog(HqHomeActivity.this);
        int optionType;

        @Override
        protected void onPreExecute()
        {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("लोड हो रहा है...");
            this.dialog.show();
        }

        @Override
        protected checkstatus doInBackground(String...arg)
        {

            return WebserviceHelper.checksurveystatus(patientid,Mobile);
        }

        @Override
        protected void onPostExecute(checkstatus result)
        {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue", "" + result);
            if (result != null) {
                chk_status=result;

//                Intent i =new Intent(MainHomeActivity.this, ProfileActivity.class);
//                i.putExtra("data",BenDetails);
//                startActivity(i);


            } else {

                Toast.makeText(getApplicationContext(), "Result Null..Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
