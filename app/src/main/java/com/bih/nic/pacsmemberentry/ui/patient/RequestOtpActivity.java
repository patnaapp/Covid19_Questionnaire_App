package com.bih.nic.pacsmemberentry.ui.patient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.Model.DefaultResponse;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;
import com.bih.nic.pacsmemberentry.WebserviceHelper;
import com.bih.nic.pacsmemberentry.ui.Login;

public class RequestOtpActivity extends Activity {

    EditText et_reg_no,et_mobile;
    TextView tv_version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_otp);

        getActionBar().hide();
        Utiilties.setStatusBarColor(this);

        initialization();

        ImageView imgBack = findViewById(R.id.img);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String version = Utiilties.getAppVersion(this);
        if(version != null){
            tv_version.setText("App Version "+version);
        }else{
            tv_version.setText("");
        }
    }

    private void initialization() {
        et_reg_no = (EditText) findViewById(R.id.et_reg_no);
        et_mobile = (EditText) findViewById(R.id.et_mobile);

        tv_version = (TextView) findViewById(R.id.tv_version);


    }

    public void onRequestOtp(View view){
        if(isValidInput()){
            if(Utiilties.isOnline(getApplicationContext())) {
                new requestOtp().execute();
            }else {
                Utiilties.internetNotAvailableDialog(RequestOtpActivity.this);
            }
        }
    }

    Boolean isValidInput(){
        View focusView = null;
        boolean validate = true;

        if (et_mobile.getText().toString().equals("")) {
            et_mobile.setError("Please enter valid mobile number");
            focusView = et_mobile;
            validate = false;
        }else if (et_mobile.getText().toString().length() < 10) {
            et_mobile.setError("Mobile number is less than 10 digit");
            focusView = et_mobile;
            validate = false;
        }

        if (!validate) focusView.requestFocus();
        return validate;
    }


    private class requestOtp extends AsyncTask<String, Void, DefaultResponse> {
        DefaultResponse data;
        String _uid;
        private final ProgressDialog dialog = new ProgressDialog(RequestOtpActivity.this);
        private final AlertDialog alertDialog = new AlertDialog.Builder(RequestOtpActivity.this).create();


        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("UpLoading...");
            if (!RequestOtpActivity.this.isFinishing()) {
                this.dialog.show();
            }
        }

        @Override
        protected DefaultResponse doInBackground(String... param) {

            return WebserviceHelper.RequestOTP(et_mobile.getText().toString());

        }

        @Override
        protected void onPostExecute(DefaultResponse result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue", "" + result);
            if (result != null) {


                if (result.getStatus()==true) {


                    AlertDialog.Builder ab = new AlertDialog.Builder(RequestOtpActivity.this);
                    ab.setCancelable(false);
                    ab.setTitle("OTP SENT");
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


                    AlertDialog.Builder ab = new AlertDialog.Builder(RequestOtpActivity.this);
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
                            //finish();
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
