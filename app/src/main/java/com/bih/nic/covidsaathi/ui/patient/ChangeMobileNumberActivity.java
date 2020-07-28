package com.bih.nic.covidsaathi.ui.patient;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.covidsaathi.GlobalVariables;
import com.bih.nic.covidsaathi.Model.DefaultResponse;
import com.bih.nic.covidsaathi.R;
import com.bih.nic.covidsaathi.Utiilties;
import com.bih.nic.covidsaathi.Verhoeff;
import com.bih.nic.covidsaathi.WebserviceHelper;

import java.util.ArrayList;

public class ChangeMobileNumberActivity extends Activity {

    EditText reg_no,et_aadhar_No,et_ben_Name;
    Spinner spin_gender;
    String ben_type_aangan[] = {"-चयन करे-","पुरुष","महिला","अन्य"};
    String Gender_Name="",Gender_Code="";
    ArrayAdapter ben_type_aangan_aaray;
    ArrayList<String> statusOfEncroachmentArray;
    String _ben_reg_no="",_ben_aadhar_no="",_ben_Name="";
    private boolean validAadhaar;
    Button email_sign_in_button2;
    TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_mobile_number);

        getActionBar().hide();
        Utiilties.setStatusBarColor(this);

        Initialize();

        String version = Utiilties.getAppVersion(this);
        if(version != null){
            tv_version.setText("ऐप वर्ज़न "+version);
        }else{
            tv_version.setText("");
        }

        spin_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("arg2",""+position);
                if (position > 0) {
                    Gender_Name = ben_type_aangan[position].toString();

                    if (Gender_Name.equals("पुरुष")) {

                        Gender_Code = "1";
                    } else if (Gender_Name.equals("महिला")) {

                        Gender_Code = "2";
                    }
                    else if (Gender_Name.equals("अन्य")) {

                        Gender_Code = "3";
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        email_sign_in_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
            }
        });
    }



    public void Initialize(){
        reg_no=findViewById(R.id.reg_no);
        et_aadhar_No=findViewById(R.id.et_aadhar_No);
        et_ben_Name=findViewById(R.id.et_ben_Name);
        email_sign_in_button2=findViewById(R.id.email_sign_in_button2);

        tv_version=findViewById(R.id.tv_version);

        spin_gender=findViewById(R.id.spin_gender);
        ben_type_aangan_aaray = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_aangan);
        spin_gender.setAdapter(ben_type_aangan_aaray);
    }


    public void registration() {
        //Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        _ben_reg_no = reg_no.getText().toString();
        _ben_aadhar_no = et_aadhar_No.getText().toString();
        _ben_Name = et_ben_Name.getText().toString();

        boolean cancelRegistration = false;
        String isValied = "yes";
        View focusView = null;

        if (TextUtils.isEmpty(_ben_reg_no)) {
            reg_no.setError("कृपया लाभार्थी का पंजीकरण संख्या डाले |");
            focusView = reg_no;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(_ben_aadhar_no)) {
            et_aadhar_No.setError("कृपया आधार नंबर डाले |");
            focusView = et_aadhar_No;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(Gender_Code)) {
            Toast.makeText(getApplicationContext(), "कृपया लाभार्थी अपना लिंग का चयन करे |", Toast.LENGTH_LONG).show();
            //sp_panchayat.setError("कृपया पंचायत का नाम का चयन करे |");
            focusView = spin_gender;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(_ben_Name)) {
            et_ben_Name.setError("कृपया पिता /पति का नाम डाले |");
            focusView = et_ben_Name;
            cancelRegistration = true;
        }

        if(!validAadhaar){
            if(!Verhoeff.validateVerhoeff(_ben_aadhar_no)){
                et_aadhar_No.setError("कृपया आधार नंबर सही डाले |");
                focusView = et_aadhar_No;
                cancelRegistration = true;
            }
        }
        if (cancelRegistration) {
            // error in login
            focusView.requestFocus();
        } else {

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
                new UPLOADDATA().execute();
                //new ValidateAdhhar(benfiList).execute();
            }

        }
    }


    private class UPLOADDATA extends AsyncTask<String, Void, DefaultResponse> {
        DefaultResponse data;
        String _uid;
        private final ProgressDialog dialog = new ProgressDialog(ChangeMobileNumberActivity.this);
        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(ChangeMobileNumberActivity.this).create();

//
//        UPLOADDATA(BarcodeEntity data) {
//            this.data = data;
//            this._uid = data.getUniqueNo();
//
//        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("UpLoading...");
            if (!ChangeMobileNumberActivity.this.isFinishing()) {
                this.dialog.show();
            }
        }

        @Override
        protected DefaultResponse doInBackground(String... param) {

//
//            String res = WebServiceHelper.UploadFinalData(data, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("USERID", ""));
//            return res;

            return WebserviceHelper.UploadFinalData(_ben_reg_no,_ben_aadhar_no,_ben_Name,Gender_Code);

        }

        @Override
        protected void onPostExecute(DefaultResponse result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue", "" + result);
            if (result != null) {


                //   if (result.getStatus().equals("true")) {
                if (result.getStatus()==true) {

                    Intent i=new Intent(ChangeMobileNumberActivity.this, UpdateMobileNumberActivity.class);
                    i.putExtra("Reg_No", _ben_reg_no);
                    i.putExtra("Migrant_Name", _ben_Name);
                    startActivity(i);

                }
                // else  if (result.getStatus().equals("false")){
                else  if (result.getStatus()==false){
                    AlertDialog.Builder ab = new AlertDialog.Builder(ChangeMobileNumberActivity.this);
                    ab.setCancelable(false);
                    ab.setTitle("Failed");
                   // ab.setIcon(R.drawable.labour1);
                    ab.setMessage(result.getMessage());
                    ab.setPositiveButton("[OK]", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
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
