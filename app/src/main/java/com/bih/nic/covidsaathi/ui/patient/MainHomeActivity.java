package com.bih.nic.covidsaathi.ui.patient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.covidsaathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.covidsaathi.GlobalVariables;
import com.bih.nic.covidsaathi.Model.BenDetails;
import com.bih.nic.covidsaathi.Model.PaymentStatusEntity;
import com.bih.nic.covidsaathi.R;
import com.bih.nic.covidsaathi.Utiilties;
import com.bih.nic.covidsaathi.WebserviceHelper;
import com.bih.nic.covidsaathi.ui.MultiLoginActivity;
import com.bih.nic.covidsaathi.ui.supervisor.JobSearchActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainHomeActivity extends Activity {
    LinearLayout ll_profile,ll_register_Grivance;
    String Reg_No="",user_name="", mobile="", address="", DistName="", ProfileImg="";
    TextView tv_benname,urole,tv_mobile,tv_address,tv_version;
    CircleImageView profile_image;
    //ImageView profile_image;

    BenDetails BenDetails;
    DataBaseHelper dataBaseHelper;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        getActionBar().hide();

        BenDetails=new BenDetails();
        dataBaseHelper=new DataBaseHelper(MainHomeActivity.this);
        Utiilties.setStatusBarColor(MainHomeActivity.this);

        ll_profile=(LinearLayout)findViewById(R.id.ll_profile);
        ll_register_Grivance=(LinearLayout)findViewById(R.id.ll_register_Grivance);
        tv_benname=(TextView) findViewById(R.id.tv_benname);
        urole=(TextView) findViewById(R.id.urole);
        tv_mobile=(TextView) findViewById(R.id.tv_mobile);
        tv_address=(TextView) findViewById(R.id.tv_address);
        tv_version=(TextView) findViewById(R.id.tv_version);

        profile_image=(CircleImageView) findViewById(R.id.profile_image);

        String version = Utiilties.getAppVersion(this);
        if(version != null){
            tv_version.setText("ऐप वर्ज़न "+version);
        }else{
            tv_version.setText("");
        }

        ll_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!GlobalVariables.isOffline && !Utiilties.isOnline(MainHomeActivity.this)) {

                    AlertDialog.Builder ab = new AlertDialog.Builder(MainHomeActivity.this);
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

                    new FetchBenData1().execute();

                }

            }
        });
        ll_register_Grivance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!GlobalVariables.isOffline && !Utiilties.isOnline(MainHomeActivity.this)) {

                    AlertDialog.Builder ab = new AlertDialog.Builder(MainHomeActivity.this);
                    ab.setMessage(Html.fromHtml(
                            "<font color=#000000>Internet Connection is not avaliable..Please Turn ON Network Connection </font>"));
                    ab.setPositiveButton("Turn On Network Connection", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            new FetchBenData().execute();
                            Intent I = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                            startActivity(I);
                        }
                    });

                    ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
                    ab.show();

                }else{
                    new FetchBenData().execute();

                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Reg_No=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserId", "");
        user_name=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserName", "");
        mobile=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Mobile", "");
        address=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Address", "");
        DistName=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("DistName", "");
        ProfileImg=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Photo", "");


        tv_benname.setText(user_name);
        urole.setText("पंजीकरण संख्या: \n"+Reg_No);
        tv_mobile.setText("मोबाइल नंबर: \n"+mobile);
        tv_address.setText("  पता: "+address);

        String imagesr=dataBaseHelper.getBenImg(Reg_No);

        if (imagesr!=null) {

            byte[] imgData = Base64.decode(imagesr, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            profile_image.setImageBitmap(bmp);


            //}
        }
        else {
            if(!ProfileImg.equalsIgnoreCase("NA")) {
                String url = "http://shramsadhan.bih.nic.in" + ProfileImg.replace("~", "");
                Log.e("imgUrl", url);
                Picasso.with(this).load(url).into(profile_image);
            }else {
                // profile_image.setBackgroundResource(R.drawable.profile);

            }
        }
    }

    public void onSearchJob(View view){
        Intent i =new Intent(MainHomeActivity.this, JobSearchActivity.class);
        i.putExtra("data",Reg_No);
        i.putExtra("DistName",DistName);
        startActivity(i);
    }

    public void onCheckPaymentStatus(View view){
        new SyncPaymentStatus().execute();
    }

    public void onLogout(View view){
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
        SharedPreferences settings = this.getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
        GlobalVariables.isLogin=false;
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("isLogin",false).commit();
//        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("UserRole","").commit();
        Intent intent = new Intent(this, MultiLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void onExit(){
        new AlertDialog.Builder(this)
                .setTitle("अलर्ट!!")
                .setMessage("क्या आप ऐप बन्द करना चाहते हैं??\n ")
                .setCancelable(false)
                .setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("नहीं", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        onExit();
    }

    private class FetchBenData extends AsyncTask<String, Void, BenDetails> {
        BenDetails data;
        String _uid;
        private final ProgressDialog dialog = new ProgressDialog(MainHomeActivity.this);
        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(MainHomeActivity.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("लोड हो रहा है...");
            if (!MainHomeActivity.this.isFinishing()) {
                this.dialog.show();
            }
        }

        @Override
        protected BenDetails doInBackground(String... param) {

//
//            String res = WebServiceHelper.UploadFinalData(data, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("USERID", ""));
//            return res;

            return WebserviceHelper.getBen_Details(Reg_No);

        }

        @Override
        protected void onPostExecute(BenDetails result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue", "" + result);
            if (result != null) {
                BenDetails=result;
                Intent i =new Intent(MainHomeActivity.this, ModifyDocumentActivity.class);
                i.putExtra("data",BenDetails);
                startActivity(i);


            } else {

                Toast.makeText(getApplicationContext(), "Result Null..Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }
    private class FetchBenData1 extends AsyncTask<String, Void, BenDetails> {
        BenDetails data;
        String _uid;
        private final ProgressDialog dialog = new ProgressDialog(MainHomeActivity.this);
        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(MainHomeActivity.this).create();



        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("लोड हो रहा है...");
            if (!MainHomeActivity.this.isFinishing()) {
                this.dialog.show();
            }
        }

        @Override
        protected BenDetails doInBackground(String... param) {

            return WebserviceHelper.getBen_Details(Reg_No);

        }

        @Override
        protected void onPostExecute(BenDetails result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue", "" + result);
            if (result != null) {
                BenDetails=result;
                Intent i =new Intent(MainHomeActivity.this, ProfileActivity.class);
                i.putExtra("data",BenDetails);
                startActivity(i);


            } else {

                Toast.makeText(getApplicationContext(), "Result Null..Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class SyncPaymentStatus extends AsyncTask<String, Void, PaymentStatusEntity> {
        BenDetails data;
        String _uid;
        private final ProgressDialog dialog = new ProgressDialog(MainHomeActivity.this);
        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(MainHomeActivity.this).create();



        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("लोड हो रहा है...");
            if (!MainHomeActivity.this.isFinishing()) {
                this.dialog.show();
            }
        }

        @Override
        protected PaymentStatusEntity doInBackground(String... param) {

            return WebserviceHelper.getPaymentDetail(Reg_No);

        }

        @Override
        protected void onPostExecute(PaymentStatusEntity result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            //Log.d("Responsevalue", "" + result);
            if (result != null) {
                if(result.getStatus()){
                    Intent i =new Intent(MainHomeActivity.this, PaymentStatusActivity.class);
                    i.putExtra("data",result);
                    startActivity(i);
                }else{
                    new AlertDialog.Builder(MainHomeActivity.this)
                            .setTitle("Failed")
                            .setMessage(result.getMsg())
                            .setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }


            } else {

                Toast.makeText(getApplicationContext(), "Result Null!!, OR Payment Status not found!!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
