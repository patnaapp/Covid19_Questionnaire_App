package com.bih.nic.pacsmemberentry.ui.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.CameraActivity;
import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;
import com.bih.nic.pacsmemberentry.GlobalVariables;
import com.bih.nic.pacsmemberentry.Model.AddHospitalEntity;
import com.bih.nic.pacsmemberentry.Model.CategoryMaster;
import com.bih.nic.pacsmemberentry.Model.District;
import com.bih.nic.pacsmemberentry.Model.HospitalMastar;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;
import com.bih.nic.pacsmemberentry.WebserviceHelper;

import java.util.ArrayList;

public class AddHospitalActivity extends AppCompatActivity {
    Spinner sp_Dist,sp_category,sp_hospital;
    String User_Id="",Dist_Code="",Dist_Name="",latitude="",longitude="",Cat_Code="",Cat_Name="",Hos_Code="",Hos_Name="";
    ArrayList<District> DistrictList = new ArrayList<District>();
    ArrayList<CategoryMaster> CategoryList = new ArrayList<CategoryMaster>();
    ArrayList<HospitalMastar> HospitalList = new ArrayList<HospitalMastar>();
    DataBaseHelper localDBHelper;
    ArrayList<String> districtNameArray;
    ArrayList<String> categoryNameArray;
    ArrayList<String> hospitalNameArray;
    ArrayAdapter<String> districtadapter;
    ArrayAdapter<String> categoryadapter;
    ArrayAdapter<String> hospitaladapter;
    private final static int CAMERA_PIC = 99;
    ImageView img1;
    byte[] img;
    Bitmap bmp;
    AddHospitalEntity benfiList;;
    Button btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospital);
        localDBHelper=new DataBaseHelper(AddHospitalActivity.this);

        User_Id=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserId", "");

        sp_Dist=findViewById(R.id.sp_Dist);
        sp_category=findViewById(R.id.sp_category);
        sp_hospital=findViewById(R.id.sp_hospital);
        img1 = findViewById(R.id.img1);
        btn_reg = findViewById(R.id.btn_reg);

        loadDistrictSpinnerdata();
        loadCategorySpinnerdata();

        sp_Dist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position >= 0) {

                    District district = DistrictList.get(position);
                    Dist_Code = district.get_DistCode();
                    Dist_Name = district.get_DistName();
                    CategoryList = localDBHelper.getCategoryLocal();
                    if(CategoryList.size()==0) {
                        new loadCategory().execute();
                    }else {
                        loadCategorySpinnerdata();
                    }

                } else {
                    Dist_Code = "";
                    Dist_Name = "";

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position >= 0) {

                    CategoryMaster district = CategoryList.get(position);
                    Cat_Code = district.getCat_id();
                    Cat_Name = district.getCat_name();
                    HospitalList = localDBHelper.getHospital();
                    if(HospitalList.size()==0) {
                        new loadHospital().execute();
                    }else {
                        loadHospitaldata();
                    }

                } else {
                    Cat_Code = "";
                    Cat_Name = "";

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        sp_hospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position >= 0) {

                    HospitalMastar district = HospitalList.get(position);
                    Hos_Code = district.getHos_Code();
                    Hos_Name = district.getHos_Name();

                } else {
                    Hos_Code = "";
                    Hos_Name = "";

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        btn_reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Utiilties.isOnline(AddHospitalActivity.this)) {
                    registration();
                } else {
                    android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(AddHospitalActivity.this);
                    alertDialog.setTitle("अलर्ट डायलॉग !");
                    alertDialog.setMessage("इंटरनेट कनेक्शन उपलब्ध नहीं है .. कृपया नेटवर्क कनेक्शन चालू करें?");
                    alertDialog.setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent I = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                            startActivity(I);
                        }
                    });
                    alertDialog.setNegativeButton("नहीं", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    android.app.AlertDialog alert = alertDialog.create();
                    alert.show();
                }
            }
        });

    }
    public void onClick(View view) {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


            buildAlertMessageNoGps();
        }
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true) {

            Intent iCamera = new Intent(getApplicationContext(), CameraActivity.class);
            if (view.equals(img1))
                iCamera.putExtra("KEY_PIC", "1");


        }
    }

    public void loadDistrictSpinnerdata() {

        DistrictList = localDBHelper.getDistrictLocal();
        districtNameArray = new ArrayList<String>();
        //districtNameArray.add("-Select District-");
        int i = 1;
        for (District district : DistrictList) {
            districtNameArray.add(district.get_DistName());
            i++;
        }
        //districtadapter = new ArrayAdapter<String>(this, R.layout.dropdownlist, districtNameArray);
        //districtadapter.setDropDownViewResource(R.layout.dropdownlist);

        districtadapter = new ArrayAdapter<String>(this,android. R.layout.simple_spinner_dropdown_item, districtNameArray);
        //districtadapter.setDropDownViewResource(R.layout.spinner_textview);
        sp_Dist.setAdapter(districtadapter);

    }
    public void loadCategorySpinnerdata() {

        CategoryList = localDBHelper.getCategoryLocal();
        categoryNameArray = new ArrayList<String>();
        //districtNameArray.add("-Select District-");
        int i = 1;
        for (District district : DistrictList) {
            categoryNameArray.add(district.get_DistName());
            i++;
        }
        categoryadapter = new ArrayAdapter<String>(this,android. R.layout.simple_spinner_dropdown_item, categoryNameArray);
        sp_Dist.setAdapter(categoryadapter);

    }
    public void loadHospitaldata() {

        HospitalList = localDBHelper.getHospital();
        hospitalNameArray = new ArrayList<String>();
        //districtNameArray.add("-Select District-");
        int i = 1;
        for (HospitalMastar district : HospitalList) {
            hospitalNameArray.add(district.getHos_Name());
            i++;
        }
        hospitaladapter = new ArrayAdapter<String>(this,android. R.layout.simple_spinner_dropdown_item, hospitalNameArray);
        sp_hospital.setAdapter(hospitaladapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case CAMERA_PIC:
                if (resultCode == RESULT_OK) {
                    byte[] imgData = data.getByteArrayExtra("CapturedImage");
                    // byte[] imgData = data.getByteArrayExtra("CapturedImage");
                    DataBaseHelper placeData = new DataBaseHelper(getApplicationContext());

                    switch (data.getIntExtra("KEY_PIC", 0)) {
                        case 1:
                            img = imgData;
                            Bitmap bmpImg = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                            bmp = bmpImg;
                            img1.setImageBitmap(bmpImg);
                            //str_aadhar_img = org.kobjects.base64.Base64.encode(imgData);

                            latitude = String.valueOf(data.getStringExtra("Lat"));
                            longitude = String.valueOf(data.getStringExtra("Lng"));
                            break;

                    }
                }
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setIcon(R.drawable.bihgov);
        builder.setTitle("GPS ?");
        builder.setMessage("(GPS)जीपीएस बंद है\nस्थान के अक्षांश और देशांतर प्राप्त करने के लिए कृपया जीपीएस चालू करें")
//		builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("[(GPS) जीपीएस चालू करे ]", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("[ बंद करें ]", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void registration() {
        boolean cancelRegistration = false;
        String isValied = "yes";
        View focusView = null;
        if (TextUtils.isEmpty(Dist_Code)) {
            Toast.makeText(getApplicationContext(), "Please Select District !", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_Dist;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(Cat_Code)) {
            Toast.makeText(getApplicationContext(), "Please Select Category !", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_category;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(Hos_Code)) {
            Toast.makeText(getApplicationContext(), "Please Select Hospital", Toast.LENGTH_LONG).show();
            //sp_block.setError("कृपया प्रखंड का नाम का चयन करे |");
            focusView = sp_hospital;
            cancelRegistration = true;
        }
        if(img == null){
            Toast.makeText(this, "Please Capture Picture", Toast.LENGTH_SHORT).show();
            //focusView = rl_photo;
            cancelRegistration = true;
        }

        if (cancelRegistration) {
            // error in login
            focusView.requestFocus();
        } else {
            benfiList.setDist_Code(Dist_Code);
            benfiList.setCat_Code(Cat_Code);
            benfiList.setHos_Code(Hos_Code);
            benfiList.setPic(img);
            benfiList.setUserId(User_Id);

            if (!GlobalVariables.isOffline && !Utiilties.isOnline(this)) {

                android.app.AlertDialog.Builder ab = new android.app.AlertDialog.Builder(this);
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
                //new stateData().execute();
                new RegistrationTask(benfiList).execute();
            }

        }
    }

    private class RegistrationTask extends AsyncTask<AddHospitalEntity, Void, String> {

        private final ProgressDialog dialog = new ProgressDialog(AddHospitalActivity.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(AddHospitalActivity.this).create();

        AddHospitalEntity info;

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Uploading...");
            this.dialog.show();
        }

        public RegistrationTask(AddHospitalEntity info) {
            this.info = info;
        }

        @Override
        protected String doInBackground(AddHospitalEntity... param) {

            return WebserviceHelper.Registration(info);
        }

        @Override

        protected void onPostExecute(String result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (result != null) {

                if (result.equals("1")) {

                    android.app.AlertDialog.Builder ab = new android.app.AlertDialog.Builder(AddHospitalActivity.this);
                    ab.setCancelable(false);
                    //ab.setIcon(R.drawable.biharlogo);
                    ab.setMessage(Html.fromHtml(
                            "<font color=#000000>मुख्यमंत्री राहत कोष , बिहार से आपदा प्रबंधन विभाग,बिहार पटना के माध्यम से मुख्यमंत्री विशेष सहायता के लिए आपका पंजीकरण सफल रहा है |</font>"));
                    ab.setPositiveButton("ओके", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent intent = new Intent(getBaseContext(),AddHospitalActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            //setFinishOnTouchOutside(false);
                            finish();
                        }
                    });

                    ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
                    ab.show();

                } else if (result.equals("2")) {

                    android.app.AlertDialog.Builder ab = new android.app.AlertDialog.Builder(AddHospitalActivity.this);
                    ab.setMessage(Html.fromHtml(
                            "<font color=#000000>आपने पहले ही इस बैंक खाता को पंजीकृत कर लिया है! </font>"));
                    ab.setPositiveButton("ओके", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    });

                    ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
                    ab.show();
                }
            } else {

                Toast.makeText(AddHospitalActivity.this,
                        "इंटरनेट की स्पीड स्लो है | कृपया कुछ समय बाद प्रयास करे: ", Toast.LENGTH_SHORT)
                        .show();
            }
        }


    }

    private class loadCategory extends AsyncTask<String, Void, CategoryMaster> {

        private final ProgressDialog dialog = new ProgressDialog(AddHospitalActivity.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(AddHospitalActivity.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected CategoryMaster doInBackground(String... param) {


            return WebserviceHelper.loadCategory();

        }

        @Override
        protected void onPostExecute(CategoryMaster result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {

                Log.d("Resultgfg", "" + result);
                DataBaseHelper placeData = new DataBaseHelper(AddHospitalActivity.this);
                long c = placeData.insertCategory(result);

            }


        }

    }

    private class loadHospital extends AsyncTask<String, Void, HospitalMastar> {

        private final ProgressDialog dialog = new ProgressDialog(AddHospitalActivity.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(AddHospitalActivity.this).create();
        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected HospitalMastar doInBackground(String... param) {


            return WebserviceHelper.loadHospital(Dist_Code,Cat_Code);

        }

        @Override
        protected void onPostExecute(HospitalMastar result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                DataBaseHelper placeData = new DataBaseHelper(AddHospitalActivity.this);
                long c = placeData.insertHospital(result);

                Log.d("Resultgfg", "" + result);

            }


        }

    }
}
