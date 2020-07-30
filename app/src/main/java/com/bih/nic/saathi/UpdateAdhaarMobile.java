package com.bih.nic.saathi;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateAdhaarMobile extends Activity {
EditText edt1,ed2;
Button btn_update;
TextView benId;

String str_mobile="",str_adhaar="",str_benId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utiilties.setActionBarBackground(UpdateAdhaarMobile.this);
        Utiilties.setStatusBarColor(UpdateAdhaarMobile.this);

        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Update Aadhaar Number");

        setContentView(R.layout.activity_update_adhaar_mobile);
        edt1=(EditText)findViewById(R.id.edit_mobile);
        ed2=(EditText)findViewById(R.id.edit_adhar);
        benId=(TextView) findViewById(R.id.benId);
        btn_update=(Button) findViewById(R.id.btn_update);
        try {
            str_benId=  getIntent().getStringExtra("BenId");
            benId.setText(str_benId);
        }catch (Exception e){
            e.printStackTrace();
        }


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                Boolean value = isNetworkConnected();
                if (value.equals(true)) {
                    if(validateData())
                    new UpdateUidStatus().execute();
                }
            }
        });


    }

    private void setData(){
        str_mobile=edt1.getText().toString();
        str_adhaar=ed2.getText().toString();
    }




    private class UpdateUidStatus extends AsyncTask<String, Void, String> {

        private final ProgressDialog dialog = new ProgressDialog(UpdateAdhaarMobile.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(
                UpdateAdhaarMobile.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage(getResources().getString(R.string.updating));
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {


            return WebserviceHelper.UpDateAdhaar(str_benId,str_mobile,str_adhaar);



        }

        @Override
        protected void onPostExecute( String result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();
            if(result.equals("1")){
                Toast.makeText(getApplicationContext(),"Successfully Updated Aadhaar number",Toast.LENGTH_LONG).show();
            }
            else {Toast.makeText(getApplicationContext(),"Loading Fail",Toast.LENGTH_LONG).show();}


        }
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    private boolean validateData() {
        View focusView = null;
        boolean validate = true;

        if (str_mobile.equalsIgnoreCase("")) {
            if(str_mobile.length()!=10) {
                //if (spn_AaganBadi.getSelectedItem().toString().trim().equals(getResources().getString(R.string.select_spinner_hint).trim())) {
                Toast.makeText(getApplicationContext(), "Please Enter valid Mobile Number", Toast.LENGTH_LONG).show();
                validate = false;
            }
        } else if (Verhoeff.validateVerhoeff(str_adhaar)) {
            Toast.makeText(getApplicationContext(), "Please Enter valid Aadhaar Number", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if(focusView != null && focusView.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        return validate;

    }
}
