package com.bih.nic.pacsmemberentry;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;
import com.bih.nic.pacsmemberentry.Model.UserDetails;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class VerifyAadhaarGlobally extends  Activity {

    TextView edt_Bene_No,edt_Bene_name,edt_Bene_ac_no,edt_Bene_uid_status,edt_Bene_nameInPass;
    EditText edt_Bene_aadhaarNo;
    Button btn_verify;
    DataBaseHelper dataBaseHelper;
    EditText filterText;
    ImageView img_filter,img_search;
    BenfiList benfiList;
    TextView txt_dist,txt_block,txt_pnchayat;
    UserDetails userDetails;
    String Userid = "";
    String blockcode="";
    String distcode="";
    String panchyatcode="";
    String blockName="";
    String DistName="";
    String PanchayatName="";
    String str_aadhaarno,str_aadhaar_name;
    LinearLayout lin_data;
    ProgressDialog pd1;
    String BenId="";
    ImageView right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_aadhaar);


        Utiilties.setActionBarBackground(VerifyAadhaarGlobally.this);
        Utiilties.setStatusBarColor(VerifyAadhaarGlobally.this);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Verify Aadhaar");
        dataBaseHelper=new DataBaseHelper(getApplicationContext());

        filterText=(EditText)findViewById(R.id.flterText) ;
        img_filter=(ImageView)findViewById(R.id.img_filter);
        img_search=(ImageView)findViewById(R.id.img_search);
        edt_Bene_No=(TextView) findViewById(R.id.edt_Bene_No);
        edt_Bene_name=(TextView) findViewById(R.id.edt_Bene_name);
        edt_Bene_ac_no=(TextView) findViewById(R.id.edt_Bene_ac_no);
        edt_Bene_uid_status=(TextView) findViewById(R.id.edt_Bene_uid_status);
        edt_Bene_aadhaarNo=(EditText) findViewById(R.id.edt_Bene_aadhaarNo);
        btn_verify=(Button)findViewById(R.id.btn_verify);
        edt_Bene_nameInPass=(TextView) findViewById(R.id.edt_Bene_nameInPass);
        lin_data=(LinearLayout)findViewById(R.id.lin_data);
        right=(ImageView) findViewById(R.id.right);

        txt_dist=(TextView)findViewById(R.id.txt_dist);
        txt_block=(TextView)findViewById(R.id.txt_block);
        txt_pnchayat=(TextView)findViewById(R.id.txt_pnchayat);
        Userid = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserId", "");
        try {
            blockcode = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Block", "");
            distcode = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("District", "");
            panchyatcode = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("PanchayatCode", "");
            DistName = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("DistrictName", "");
            blockName = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("BlockName", "");
            PanchayatName = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("PanchayatName", "");

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        //Log.d("uiguigcug",""+Userid);

        txt_dist.setText(DistName);
        txt_block.setText(blockName);
        txt_pnchayat.setText(PanchayatName);
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String benId=filterText.getText().toString();
                new SearchGlobal(benId).execute();

            }
        });

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_aadhaar_name=benfiList.getBeneficiery_name();
                str_aadhaarno=edt_Bene_aadhaarNo.getText().toString();
                if(filterText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"Enter data to filter",Toast.LENGTH_LONG).show();
                }else {
                    new USerStatus().execute();
                }

            }
        });


    }



    private void setData(BenfiList data){

        benfiList= data;
        if(benfiList.getBeneficiery_name()!=null) {
            BenId=benfiList.getBeneficiary_id();
            edt_Bene_No.setText(benfiList.getBeneficiary_id());
            edt_Bene_name.setText(benfiList.getBeneficiery_name());
            edt_Bene_ac_no.setText(benfiList.getAccountNo());
            edt_Bene_uid_status.setText(benfiList.getUidStatus());
            edt_Bene_aadhaarNo.setText(benfiList.getAadharNumber());
            edt_Bene_nameInPass.setText(benfiList.getNameInPass());
            btn_verify.setVisibility(View.VISIBLE);
            lin_data.setVisibility(View.VISIBLE);
            if(benfiList.getUidStatus().equalsIgnoreCase("Y")){
                right.setVisibility(View.VISIBLE);
            }else {
                right.setVisibility(View.GONE);
            }
        }else {
            btn_verify.setVisibility(View.GONE);
            lin_data.setVisibility(View.GONE);
        }

    }


    public String getDataTimeStamp()
    {
        String pidTimeStamp=null;
        try {
            DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            Calendar localCalendar = GregorianCalendar
                    .getInstance();
            localCalendar.setTime(date);
            pidTimeStamp = String.valueOf(localCalendar
                    .get(Calendar.YEAR)) + "-" +
                    (String.valueOf(localCalendar.get(Calendar.MONTH) + 1).length() < 2 ? "0" + String.valueOf(localCalendar
                            .get(Calendar.MONTH) + 1) : String.valueOf(localCalendar
                            .get(Calendar.MONTH) + 1))
                    + "-" +
                    (String.valueOf(localCalendar.get(Calendar.DATE)).length() < 2 ? "0" + String.valueOf(localCalendar.get(Calendar.DATE)) : String.valueOf(localCalendar.get(Calendar.DATE)))
                    + "T" +
                    (String.valueOf(localCalendar.get(Calendar.HOUR_OF_DAY)).length() < 2 ? "0" + String.valueOf(localCalendar.get(Calendar.HOUR_OF_DAY)) : String.valueOf(localCalendar.get(Calendar.HOUR_OF_DAY)))
                    + ":" +
                    (String.valueOf(localCalendar.get(Calendar.MINUTE)).length() < 2 ? "0" + String.valueOf(localCalendar.get(Calendar.MINUTE)) : String.valueOf(localCalendar.get(Calendar.MINUTE)))
                    + ":" +
                    (String.valueOf(localCalendar.get(Calendar.SECOND)).length() < 2 ? "0" + String.valueOf(localCalendar.get(Calendar.SECOND)) : String.valueOf(localCalendar.get(Calendar.SECOND)));

            pidTimeStamp= pidTimeStamp.replace("T", "").replace("-", "").replace(":", "");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  pidTimeStamp;

    }

    class USerStatus extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd1 = new ProgressDialog(VerifyAadhaarGlobally.this);
            pd1.setTitle("please wait ....");
            pd1.setCancelable(false);
            pd1.show();
        }

        @Override
        protected String doInBackground(String... strings) {


            String s = "";
            String myuser_id = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("myuserid", "");
            try {
                HttpClient httpClient = new DefaultHttpClient();
                //HttpPost httpPost = new HttpPost("http://erachat.condoassist2u.com/api/setOnlineOffline");
                HttpPost httpPost = new HttpPost("http://baaf.bihar.gov.in:8080/authekycp25/api/authenticate");
                httpPost.setHeader("Content-type", "application/json");

                JSONObject jsonObject = new JSONObject();
                //jsonObject.accumulate("uid", "675075550397");
                jsonObject.accumulate("uid", str_aadhaarno);
                jsonObject.accumulate("uidType","A");
                jsonObject.accumulate("consent","Y");
                jsonObject.accumulate("subAuaCode","PSWDB22456");

                 // SimpleDateFormat postFormater = new SimpleDateFormat("yyyyMMddTHHmmss");
                 //  String newDateStr = postFormater.format(Calendar.getInstance().getTime());
                 //jsonObject.accumulate("txn", "eLAuth" + "20181117T104125");
                jsonObject.accumulate("txn", "eLAuth" + getDataTimeStamp());
                jsonObject.accumulate("isPI","y");
                jsonObject.accumulate("isBio","n");
                jsonObject.accumulate("isOTP","n");
                jsonObject.accumulate("bioType","");
                //  jsonObject.accumulate("name","Niraj Kumar Tiwary");
                jsonObject.accumulate("name",str_aadhaar_name);
                jsonObject.accumulate("rdInfo","");
                jsonObject.accumulate("rdData","");
                jsonObject.accumulate("otpValue","");

                StringEntity stringEntity = new StringEntity(jsonObject.toString());
               // Log.d("ygygu",""+jsonObject.toString());

                httpPost.setEntity(stringEntity);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                s = readResponse(httpResponse);
                Log.d("tag11", " " + s);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return s;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (pd1.isShowing()) {

                pd1.dismiss();
            }
            try {
                if(s!=null) {
                    JSONObject jsonObject = new JSONObject(s);
                    String message = jsonObject.getString("status");
                    if (message.equalsIgnoreCase("ERROR")) {
                        String errmessage = jsonObject.getString("errMsg");
                        showMessageDialogue(message, errmessage);
                    } else {

                        showSuccessMessageDialogue(message);
                        BenfiList benfiList=new BenfiList();

                        benfiList.setBeneficiary_id(BenId);
                        benfiList.setUidStatus("Y");

                        dataBaseHelper.updateInspectionDetails(benfiList);
                        //setData();
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }

    public void showMessageDialogue(String messageTxt,String essage) {
        // MainActi.this.runOnUiThread(new Runnable() {
        // @Override
        //  public void run() {
        new AlertDialog.Builder(VerifyAadhaarGlobally.this)
                .setCancelable(false)
                .setTitle("Error")
                .setMessage(essage)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
        // }
        // });
    } public void showSuccessMessageDialogue(String messageTxt) {
        // MainActi.this.runOnUiThread(new Runnable() {
        // @Override
        //  public void run() {
        new AlertDialog.Builder(VerifyAadhaarGlobally.this)
                .setCancelable(false)
                .setTitle("Success")
                .setMessage("Authenticated Successfully")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
        // }
        // });
    }

    private String readResponse(HttpResponse httpResponse) {
        InputStream is = null;
        String return_text = "";
        try {
            is = httpResponse.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return_text = sb.toString();
            Log.d("return_text", "" + return_text);
        } catch (Exception e) {

        }
        return return_text;


    }

    private class SearchGlobal extends AsyncTask<String, Void, ArrayList<BenfiList>> {

          String Search="";
        public SearchGlobal(String benId) {
            Search=benId;
        }

        @Override
        protected void onPreExecute() {
            pd1 = new ProgressDialog(VerifyAadhaarGlobally.this);
            pd1.setTitle("please wait ....");
            pd1.setCancelable(false);
            pd1.show();
        }

        @Override
        protected ArrayList<BenfiList> doInBackground(String... param) {

            return WebserviceHelper.SearchGlobal(Search);
        }

        @Override
        protected void onPostExecute(ArrayList<BenfiList> result) {
            if (pd1.isShowing()) {

                pd1.dismiss();
            }

            if (result != null) {
                if (result.size() > 0) {
                    Log.d("datafromserver", "" + result);
                    for (int i=0;i<result.size();i++) {
                        benfiList = result.get(i);
                        setData(benfiList);
                    }

                }
            }

         }
     }


    @Override
    public void onBackPressed() {
        finish();
    }
}
