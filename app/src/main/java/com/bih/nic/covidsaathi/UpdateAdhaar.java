package com.bih.nic.covidsaathi;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.covidsaathi.DataBaseHelper.DataBaseHelper;

import java.util.Calendar;

public class UpdateAdhaar extends Activity {

    String BenId="";
    Button btn_update,btn_exit;
    TextView txt_benId,txt_adhaarno;
    String AadharName="";
    String AadhaarDob="";
    String currentdate="";
    DataBaseHelper dataBaseHelper;
    String Userid="";
    Button saveToLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_adhaar);

        btn_update=(Button)findViewById(R.id.btn_update);
        btn_exit=(Button)findViewById(R.id.btn_exit);
        txt_benId=(TextView) findViewById(R.id.txt_benId);
        txt_adhaarno=(TextView) findViewById(R.id.txt_adhaarno);
        saveToLocal=(Button)findViewById(R.id.saveToLocal);
        dataBaseHelper=new DataBaseHelper(getApplicationContext());
        Userid = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserId", "");
        Utiilties.setActionBarBackground(UpdateAdhaar.this);
        Utiilties.setStatusBarColor(UpdateAdhaar.this);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Verify Aadhaar");
        try {
            Intent intent = getIntent();
            BenId=  intent.getStringExtra("BenId");
            String adhaar=  intent.getStringExtra("AadharNo");
            AadharName = intent.getStringExtra("AadhaarName");
            AadhaarDob = intent.getStringExtra("AadhaarDOB");
            txt_benId.setText(BenId);
            if(!adhaar.equals("")) {
                String hfhf=adhaar.substring(8,12);
                txt_adhaarno.setText("********"+hfhf);
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }

        currentdate=getCurrentDate();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utiilties.isOnline(getApplicationContext())) {

                    new UpdateUidStatus().execute();
                }else {
                    Toast.makeText(getApplicationContext(),"Please Turn On Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),VerifyAadhaar.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        saveToLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long i=0;
                BenfiList benfiList=new BenfiList();
                benfiList.setBeneficiary_id(BenId);
                benfiList.setLifeCertificate("Y");
                i=dataBaseHelper.insertLifeCertificate(benfiList,Userid);
                if(i>0){
                    Toast.makeText(getApplicationContext(),"Life certificate inserted Locally",Toast.LENGTH_LONG).show();
                }
            }
        });
    }





   private class UpdateUidStatus extends AsyncTask<String, Void, String> {

        private final ProgressDialog dialog = new ProgressDialog(UpdateAdhaar.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(
                UpdateAdhaar.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage(getResources().getString(R.string.updating));
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {


            return  WebserviceHelper.UpdateAliveCertificate(BenId,"Y");
        }

        @Override
        protected void onPostExecute( String result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();
            //Log.d("chdbcujhh",""+result);
            if(result.equals("1")){
                Toast.makeText(getApplicationContext(),"Aadhaar Number Uploaded Successfully",Toast.LENGTH_LONG).show();
                BenfiList benfiList=new BenfiList();
                benfiList.setBeneficiary_id(BenId);
                benfiList.setUidStatus("Y");

                // dataBaseHelper.updateInspectionDetails(benfiList);

                new AlertDialog.Builder(UpdateAdhaar.this)
                        .setCancelable(false)
                        .setTitle("Message")
                        .setMessage("Your Aadhaar Number Uploaded Successfully")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Intent intent=new Intent(getApplicationContext(),VerifyAadhaar.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();

                            }
                        }).show();

            }else {
                Toast.makeText(getApplicationContext(),"Aadhaar Number not Uploaded !",Toast.LENGTH_LONG).show();

                new AlertDialog.Builder(UpdateAdhaar.this)
                        .setCancelable(false)
                        .setTitle("Error")
                        .setMessage("Aadhaar Number not Uploaded !")
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Intent intent=new Intent(getApplicationContext(),VerifyAadhaar.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),VerifyAadhaar.class);
        startActivity(intent);
        finish();
    }

    public String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);

        String dayString = "";
        String monthString = "";

        if (day < 10) dayString = "0" + day;
        else dayString = day + "";
        if (month < 10) monthString = "0" + month;
        else monthString = month + "";

        int h = cal.get(Calendar.HOUR);
        int m = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);

        String date = "";
        String hour = "";
        String minute = "";
        String second = "";

        if (h < 10) hour = "0" + h;
        else hour = h + "";
        if (m < 10) minute = "0" + m;
        else minute = m + "";
        if (s < 10) second = "0" + s;
        else second = s + "";

        //date=dayString+"/"+monthString+"/"+year+"  "+hour+":"+minute+":"+second;
        date = dayString + "-" + monthString + "-" + year;

        return date;

    }
}
