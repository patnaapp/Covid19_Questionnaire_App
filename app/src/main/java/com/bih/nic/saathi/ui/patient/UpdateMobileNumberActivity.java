package com.bih.nic.saathi.ui.patient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.saathi.GlobalVariables;
import com.bih.nic.saathi.Model.DefaultResponse;
import com.bih.nic.saathi.R;
import com.bih.nic.saathi.Utiilties;
import com.bih.nic.saathi.WebserviceHelper;
import com.bih.nic.saathi.ui.Login;

import java.util.ArrayList;

public class UpdateMobileNumberActivity extends Activity {

    EditText edt_reg_no,et_ben_Nameas_aadhar,et_mobile_No,et_mobile_No_cnfrm;
    Spinner spin_gender;
    String ben_type_aangan[] = {"-चयन करे-","पुरुष","महिला","अन्य"};
    String Gender_Name="",Gender_Code="";
    ArrayAdapter ben_type_aangan_aaray;
    ArrayList<String> statusOfEncroachmentArray;
    String _ben_mob_no="",_cnfrm_mob_no="",_ben_Name="";
    private boolean validAadhaar;
    String regNo="",Name="";
    Button btn_update_mobile;
    TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mobile_number);

        getActionBar().hide();
        Utiilties.setStatusBarColor(this);
        regNo=getIntent().getStringExtra("Reg_No");
        Name=getIntent().getStringExtra("Migrant_Name");
        Initialize();

        String version = Utiilties.getAppVersion(this);
        if(version != null){
            tv_version.setText("ऐप वर्ज़न "+version);
        }else{
            tv_version.setText("");
        }

        edt_reg_no.setEnabled(false);
        et_ben_Nameas_aadhar.setEnabled(false);
        edt_reg_no.setText(regNo);
        et_ben_Nameas_aadhar.setText(Name);


        btn_update_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMob();
            }
        });

    }



    public void Initialize(){
        edt_reg_no=findViewById(R.id.edt_reg_no);
        et_ben_Nameas_aadhar=findViewById(R.id.et_ben_Nameas_aadhar);
        et_mobile_No=findViewById(R.id.et_mobile_No);
        et_mobile_No_cnfrm=findViewById(R.id.et_mobile_No_cnfrm);
        btn_update_mobile=findViewById(R.id.btn_update_mobile);
        tv_version=findViewById(R.id.tv_version);
    }


    public void updateMob() {
        //Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        _ben_mob_no = et_mobile_No.getText().toString();
        _cnfrm_mob_no = et_mobile_No_cnfrm.getText().toString();


        boolean cancelRegistration = false;
        String isValied = "yes";
        View focusView = null;

        if (TextUtils.isEmpty(_ben_mob_no)) {
            et_mobile_No.setError("कृपया मोबाइल नंबर  डाले |");
            focusView = et_mobile_No;
            cancelRegistration = true;
        }
        else if (et_mobile_No.getText().toString().length() != 10) {
            et_mobile_No.setError("मोबाइल नंबर सही नहीं है |");
            focusView = et_mobile_No;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(_cnfrm_mob_no)) {
            et_mobile_No_cnfrm.setError("कृपया मोबाइल नंबर कन्फर्म करे |");
            focusView = et_mobile_No_cnfrm;
            cancelRegistration = true;
        }else if (et_mobile_No_cnfrm.getText().toString().length() != 10) {
            et_mobile_No_cnfrm.setError("मोबाइल नंबर सही नहीं है |");
            focusView = et_mobile_No_cnfrm;
            cancelRegistration = true;
        }

        if (!(_cnfrm_mob_no.equals(_ben_mob_no)))
        {
            et_mobile_No_cnfrm.setError("कृपया मोबाइल नंबर सही डाले |");
            focusView = et_mobile_No_cnfrm;
            cancelRegistration = true;
        }



        if (cancelRegistration) {
            // error in login
            focusView.requestFocus();
        }
        else {

            if (!GlobalVariables.isOffline && !Utiilties.isOnline(this)) {

                AlertDialog.Builder ab = new AlertDialog.Builder(this);
                ab.setMessage(Html.fromHtml(
                        "<font color=#000000>Internet Connection is not avaliable..Please Turn ON Network Connection </font>"));
                ab.setPositiveButton("Turn On Network Connection", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent I = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(I);
                    }
                });

                ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
                ab.show();

            }else{
                new UopdateMobile().execute();
                //new ValidateAdhhar(benfiList).execute();
            }

        }
    }


    private class UopdateMobile extends AsyncTask<String, Void, DefaultResponse> {
        DefaultResponse data;
        String _uid;
        private final ProgressDialog dialog = new ProgressDialog(UpdateMobileNumberActivity.this);
        private final AlertDialog alertDialog = new AlertDialog.Builder(UpdateMobileNumberActivity.this).create();


        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("UpLoading...");
            if (!UpdateMobileNumberActivity.this.isFinishing()) {
                this.dialog.show();
            }
        }

        @Override
        protected DefaultResponse doInBackground(String... param) {

            return WebserviceHelper.UpdateMobileNumber(regNo,_cnfrm_mob_no);

        }

        @Override
        protected void onPostExecute(DefaultResponse result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue", "" + result);
            if (result != null) {



                if (result.getStatus()==true) {

                    AlertDialog.Builder ab = new AlertDialog.Builder(UpdateMobileNumberActivity.this);
                    ab.setCancelable(false);
                    ab.setTitle("Updated");
                    //ab.setIcon(R.drawable.labour1);
                    ab.setMessage(result.getMessage());
                    ab.setPositiveButton("[OK]", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent intent = new Intent(getBaseContext(), Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            //setFinishOnTouchOutside(false);
                            finish();
                            dialog.dismiss();
                        }
                    });

                    ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
                    ab.show();


                }
                else  if (result.getStatus()==false){
                    // Toast.makeText(getApplicationContext(), "Uploading data failed ", Toast.LENGTH_SHORT).show();


                    AlertDialog.Builder ab = new AlertDialog.Builder(UpdateMobileNumberActivity.this);
                    ab.setCancelable(false);
                    ab.setTitle("Failed");
                  //  ab.setIcon(R.drawable.labour1);
                    ab.setMessage(result.getMessage());
                    ab.setPositiveButton("[OK]", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent intent = new Intent(getBaseContext(),Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            //setFinishOnTouchOutside(false);
                            finish();
                            dialog.dismiss();
                        }
                    });

                    ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
                    ab.show();



                }

            } else {
                //chk_msg_OK_networkdata("Uploading failed.Please Try Again Later");
                Toast.makeText(getApplicationContext(), "Result Null..Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
