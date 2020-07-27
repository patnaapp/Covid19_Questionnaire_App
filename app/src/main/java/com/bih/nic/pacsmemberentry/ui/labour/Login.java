package com.bih.nic.pacsmemberentry.ui.labour;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;
import com.bih.nic.pacsmemberentry.Model.UserDetails;
import com.bih.nic.pacsmemberentry.Model.panchayat;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;
import com.bih.nic.pacsmemberentry.WebserviceHelper;

import java.util.ArrayList;

public class Login extends Activity {

    Button btn_login;
    EditText et_reg_no, et_otp;
    String str_email, str_pass;
    String[] param;
    TextView text_signup,tv_version;
    TelephonyManager tm;
    private static String imei;
    TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getActionBar().hide();

        Utiilties.setStatusBarColor(Login.this);
        Initialization();

        String version = Utiilties.getAppVersion(this);
        if(version != null){
            tv_version.setText("App Version "+version);
        }else{
            tv_version.setText("");
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Login.this, HqHomeActivity.class);
//                startActivity(intent);
                setvalue();
                if(isValidInput()){
                    if(Utiilties.isOnline(getApplicationContext())) {
                        setvalue();
                        new LoginTask(et_reg_no.getText().toString(),et_otp.getText().toString()).execute();
                    }else {
                        Utiilties.internetNotAvailableDialog(Login.this);
                    }
                }

            }
        });
        //getIMEI();

    }

    Boolean isValidInput(){
        View focusView = null;
        boolean validate = true;

        if (et_reg_no.getText().toString().equals("")) {
            et_reg_no.setError("कृप्या सही पंजीकरण संख्या डालें");
            focusView = et_reg_no;
            validate = false;
        }

        if (et_otp.getText().toString().equals("")) {
            et_otp.setError("कृप्या सही ओटीपी डालें");
            focusView = et_otp;
            validate = false;
        }

        if (!validate) focusView.requestFocus();
        return validate;
    }

    private void setvalue() {
        str_email = et_reg_no.getText().toString();
        str_pass = et_otp.getText().toString();
    }

    private void Initialization() {
        et_reg_no = (EditText) findViewById(R.id.et_reg_no);
        et_otp = (EditText) findViewById(R.id.et_otp);
        btn_login = (Button) findViewById(R.id.btn_login);

        tv_version = (TextView) findViewById(R.id.tv_version);
    }

    public void onRequestOtp(View view){
        Intent intent = new Intent(this, RequestOtpActivity.class);
        startActivity(intent);
    }

    public void onChangeMobileNo(View view){
        Intent intent = new Intent(this, ChangeMobileNumberActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private class LoginTask extends AsyncTask<String, Void, UserDetails> {

        private final ProgressDialog dialog = new ProgressDialog(Login.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(
                Login.this).create();

        String regN0,otp;

        public LoginTask(String regN0, String otp) {
            this.regN0 = regN0;
            this.otp = otp;
        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage(getResources().getString(R.string.authenticating));
            this.dialog.show();
        }

        @Override
        protected UserDetails doInBackground(String... param) {

                return WebserviceHelper.loginUser(regN0,otp);


        }

        @Override
        protected void onPostExecute( UserDetails result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();

            if(result!=null) {
                if(result.isAuthenticated()) {
                    result.set_Passwoed(et_otp.getText().toString());
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
                    dataBaseHelper.insertUserDetails(result,str_email);

                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("UserId",result.get_UserId()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Password",et_otp.getText().toString()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("FHName",result.getFHName()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PatientId",result.getPatientId()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PatientName",result.getPatientName()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("SupervisorId",result.getSupervisorId()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("SupervisorName",result.getSupervisorName()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Mobile",result.getMobileNo()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Address",result.getAddress()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Covid19TestingDate",result.getCovid19TestingDate()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("ImpDate",result.getImpDate()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("isLogin",true).commit();
//                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("BlockName",result.getBlockName()).commit();
//                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PanchayatName",result.getPanchayatName()).commit();
//                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Photo",result.getProfileImg()).commit();

                    Intent intent=new Intent(Login.this, HqHomeActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"Null Response: Check Network and try again!!",Toast.LENGTH_LONG).show();
            }
        }
    }

    public  void BlinkTextView(TextView txt)
    {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000); //You can manage the time of the blink with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        txt.startAnimation(anim);
    }
    private class loadPanchayatData extends AsyncTask<String, Void, ArrayList<panchayat>> {


        String blockcode = "";
        ArrayList<panchayat> blocklist = new ArrayList<>();
        private final ProgressDialog dialog = new ProgressDialog(
                Login.this);

        loadPanchayatData(String distCode) {
            this.blockcode = distCode;
        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<panchayat> doInBackground(String... param) {

            this.blocklist = WebserviceHelper.getPanchayatData(blockcode);

            return this.blocklist;
        }

        @Override
        protected void onPostExecute(ArrayList<panchayat> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();

            }

            if (result != null) {
                if (result.size() > 0) {

                    DataBaseHelper placeData = new DataBaseHelper(Login.this);
                    long i = placeData.setPanchayatLocal(result, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("District", ""),blockcode);

                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.loading_fail), Toast.LENGTH_LONG).show();

                }
            }

        }

    }
}
