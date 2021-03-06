package com.bih.nic.saathi.ui.patient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.bih.nic.saathi.CameraActivity;
import com.bih.nic.saathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.saathi.GlobalVariables;
import com.bih.nic.saathi.Model.AddHospitalEntity;
import com.bih.nic.saathi.Model.CategoryMaster;
import com.bih.nic.saathi.Model.DefaultResponse;
import com.bih.nic.saathi.Model.District;
import com.bih.nic.saathi.Model.FacilitiesEntity;
import com.bih.nic.saathi.R;
import com.bih.nic.saathi.Utiilties;
import com.bih.nic.saathi.WebserviceHelper;
import com.bih.nic.saathi.ui.supervisor.Supervisor_HomeActivity;

import java.util.ArrayList;

public class AddHospitalActivity extends Activity {
    Spinner sp_Dist,sp_category,sp_hospital,sp_level_type,sp_type;
    String str_img="",level_type_Id="",level_type_Name="",type_Id="",type_Name="",User_Id="",Dist_Code="",Dist_Name="",latitude="",longitude="",Cat_Code="",Cat_Name="",Hos_Code="",Hos_Name="";
    ArrayList<District> DistrictList = new ArrayList<District>();
    ArrayList<CategoryMaster> CategoryList = new ArrayList<CategoryMaster>();
    ArrayList<FacilitiesEntity> HospitalList = new ArrayList<FacilitiesEntity>();
    DataBaseHelper localDBHelper;
    ArrayList<String> districtNameArray;
    ArrayList<String> categoryNameArray;
    ArrayList<String> hospitalNameArray;
    ArrayAdapter<String> districtadapter;
    ArrayAdapter<String> categoryadapter;
    ArrayAdapter<String> hospitaladapter;
    private final static int CAMERA_PIC = 99;
    int ThumbnailSize =500;
    ImageView img1;
    byte[] img;
    String distCode="";
    Bitmap bmp;
    AddHospitalEntity benfiList;;
    Button btn_reg;
    String level_type[] = {"-select-","All","District","Subdivision","Block"};
    String type[] = {"-select-","Goverment","Private"};
    String center_type[] = {"-select-","Hospital","Isolation Center","Testing Center"};
    ArrayAdapter ben_aaray_level_type,ben_aaray_type,ben_cen_type_type;
    RelativeLayout rl_photo;
    LinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospital);
        getActionBar().hide();
        localDBHelper=new DataBaseHelper(AddHospitalActivity.this);
        benfiList= new AddHospitalEntity();

        User_Id=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserId", "");
        distCode=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("DistCode", "");

        Log.e("DistCode", Dist_Code);

        sp_Dist=findViewById(R.id.sp_Dist);
        sp_category=findViewById(R.id.sp_category);
        sp_hospital=findViewById(R.id.sp_hospital);
        sp_level_type=findViewById(R.id.sp_level_type);
        sp_type=findViewById(R.id.sp_type);
        img1 = findViewById(R.id.img1);
        btn_reg = findViewById(R.id.btn_reg);
        rl_photo = findViewById(R.id.rl_photo);
        ll_content = findViewById(R.id.ll_content);

        ImageView img = findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadDistrictSpinnerdata();
        loadCategorySpinnerdata();
        ben_aaray_level_type = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, level_type);
        sp_level_type.setAdapter(ben_aaray_level_type);
        ben_aaray_type = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, type);
        sp_type.setAdapter(ben_aaray_type);

        ben_cen_type_type = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, center_type);
        sp_category.setAdapter(ben_cen_type_type);

        ll_content.setVisibility(View.GONE);

        sp_Dist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position >= 0) {

                    District district = DistrictList.get(position);
                    Dist_Code = district.get_DistCode();
                    Dist_Name = district.get_DistName();
                    CategoryList = localDBHelper.getCategoryLocal();
//                    if(CategoryList.size()==0) {
//                        new loadCategory().execute();
//                    }else {
//                        loadCategorySpinnerdata();
//                    }

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
        sp_level_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position > 0) {

                    level_type_Name = level_type[position];
                    if(level_type_Name.equals("All"))
                    {
                        level_type_Id = "0";
                    }
                    if(level_type_Name.equals("District"))
                    {
                        level_type_Id = "D";
                    }
                    else if(level_type_Name.equals("Subdivision"))
                    {
                        level_type_Id = "S";
                    }
                    else if(level_type_Name.equals("Block"))
                    {
                        level_type_Id = "B";
                    }

                    ll_content.setVisibility(View.VISIBLE);

                } else {
                    level_type_Id = "";
                    level_type_Name = "";

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
                if (position > 0) {
//
//                    CategoryMaster district = CategoryList.get(position);
//                    Cat_Code = district.getCat_id();
//                    Cat_Name = district.getCat_name();
//                    HospitalList = localDBHelper.getHospital();


                    Cat_Name = center_type[position];
                    if (Cat_Name.equals("Hospital")) {
                        Cat_Code = "H";
                    } else if (Cat_Name.equals("Isolation Center")) {
                        Cat_Code = "I";
                    } else if (Cat_Name.equals("Testing Center")) {
                        Cat_Code = "T";
                    }
                    new loadHospital().execute();
//                    if(HospitalList.size()==0) {
//                        new loadHospital().execute();
//                    }else {
//                        loadHospitaldata(Dist_Code,level_type_Id,Cat_Name);
//                    }

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
                if (position > 0) {

                    FacilitiesEntity district = HospitalList.get(position-1);
                    Hos_Code = district.getHostpitalId();
                    Hos_Name = district.getName();

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
        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position > 0) {

                    type_Name = type[position];
                    if(type_Name.equals("Goverment"))
                    {
                        type_Id = "G";
                    }
                    else if(type_Name.equals("Private"))
                    {
                        type_Id = "P";
                    }

                } else {
                    type_Id = "";
                    type_Name = "";

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
//        rl_photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//
//
//                    buildAlertMessageNoGps();
//                }
//                if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true) {
//
//                    Intent iCamera = new Intent(getApplicationContext(), CameraActivity.class);
//                        iCamera.putExtra("KEY_PIC", "1");
//
//                }
//            }
//        });

    }
    public void onClick(View view) {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


            buildAlertMessageNoGps();
        }
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true) {

            Intent iCamera = new Intent(getApplicationContext(), CameraActivity.class);
            if (view.equals(img1))
                iCamera.putExtra("KEY_PIC", "2");
                startActivityForResult(iCamera,CAMERA_PIC);


        }
    }

    //    public void loadDistrictSpinnerdata() {
//
//        DistrictList = localDBHelper.getDistrictLocal();
//        districtNameArray = new ArrayList<String>();
//        //districtNameArray.add("-Select District-");
//        int i = 1;
//        for (District district : DistrictList) {
//            districtNameArray.add(district.get_DistName());
//            i++;
//        }
//        //districtadapter = new ArrayAdapter<String>(this, R.layout.dropdownlist, districtNameArray);
//        //districtadapter.setDropDownViewResource(R.layout.dropdownlist);
//
//        districtadapter = new ArrayAdapter<String>(this,android. R.layout.simple_spinner_dropdown_item, districtNameArray);
//        //districtadapter.setDropDownViewResource(R.layout.spinner_textview);
//        sp_Dist.setAdapter(districtadapter);
//
//    }
    public void loadDistrictSpinnerdata() {

        DistrictList = localDBHelper.getDistrictLocal();
        String[] typeNameArray = new String[DistrictList.size() + 1];
        typeNameArray[0] = "-Select District-";
        int i = 0;
        int setID=0;
        //String distCode=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Dist_Code", "");
        for (District type : DistrictList) {
            typeNameArray[i+1] = type.get_DistName();
            if(distCode.equalsIgnoreCase(DistrictList.get(i).get_DistCode()))
            {
                setID=i;
            }
            i++;
        }
        //districtadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, typeNameArray);
        districtadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, typeNameArray);
        //districtadapter.setDropDownViewResource(R.layout.dropdownlist);
        sp_Dist.setAdapter(districtadapter);

        sp_Dist.setSelection(setID+1);
        sp_Dist.setEnabled(false);
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
    public void loadHospitaldata(ArrayList<FacilitiesEntity> result) {

//        HospitalList = localDBHelper.getHospital(distCode,LevelType,CentreType);
        hospitalNameArray = new ArrayList<String>();
        hospitalNameArray.add("-select-");
        //districtNameArray.add("-Select District-");
        int i = 1;
        for (FacilitiesEntity district : result) {
            hospitalNameArray.add(district.getName());
            i++;
        }
        hospitaladapter = new ArrayAdapter<String>(this,android. R.layout.simple_spinner_dropdown_item, hospitalNameArray);
        sp_hospital.setAdapter(hospitaladapter);

    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode)
//        {
//            case CAMERA_PIC:
//                if (resultCode == RESULT_OK) {
//                    byte[] imgData = data.getByteArrayExtra("CapturedImage");
//
//                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);
//                    //img_studphoto.setScaleType(ImageView.ScaleType.FIT_XY);
//                    img1.setImageBitmap(Utiilties.GenerateThumbnail(bmp,500, 500));
//
//                    break;
//
//                }
//        }
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case CAMERA_PIC:
                if (resultCode == RESULT_OK) {
                    byte[] imgData = data.getByteArrayExtra("CapturedImage");
                    // byte[] imgData = data.getByteArrayExtra("CapturedImage");
                    DataBaseHelper placeData = new DataBaseHelper(getApplicationContext());

                    //  switch (data.getIntExtra("KEY_PIC", 0)) {
                    //  case 1:
                    img = imgData;
                    Bitmap bmpImg = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                    bmp = bmpImg;
                    img1.setImageBitmap(bmpImg);
                    str_img = org.kobjects.base64.Base64.encode(imgData);

                    latitude = String.valueOf(data.getStringExtra("Lat"));
                    longitude = String.valueOf(data.getStringExtra("Lng"));
                    // break;

                    //  }
                }
        }
    }

    private void buildAlertMessageNoGps() {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        //builder.setIcon(R.drawable.bihgov);
//        builder.setTitle("GPS ?");
//        builder.setMessage("(GPS)जीपीएस बंद है\nस्थान के अक्षांश और देशांतर प्राप्त करने के लिए कृपया जीपीएस चालू करें")
////		builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
//                .setCancelable(false)
//                .setPositiveButton("[(GPS) जीपीएस चालू करे ]", new DialogInterface.OnClickListener() {
//                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
//                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//                    }
//                })
//                .setNegativeButton("[ बंद करें ]", new DialogInterface.OnClickListener() {
//                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
//                        //dialog.cancel();
//                        dialog.cancel();
//                    }
//                });
////        final AlertDialog alert = builder.create();
////        alert.show();
//        builder.show();

        new android.app.AlertDialog.Builder(this)
                .setTitle("GPS")
                .setMessage("(GPS)जीपीएस बंद है\nस्थान के अक्षांश और देशांतर प्राप्त करने के लिए कृपया जीपीएस चालू करें ")
                .setCancelable(false)
                .setPositiveButton("[(GPS) जीपीएस चालू करे ]", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void registration() {
        boolean cancelRegistration = false;
        String isValied = "yes";
        View focusView = null;
//        if (TextUtils.isEmpty(Dist_Code)) {
//            Toast.makeText(getApplicationContext(), "Please Select District !", Toast.LENGTH_LONG).show();
//            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
//            focusView = sp_Dist;
//            cancelRegistration = true;
//        }

        if (TextUtils.isEmpty(level_type_Id)) {
            Toast.makeText(getApplicationContext(), "Please Select Lebel Type !", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_level_type;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(Cat_Code)) {
            Toast.makeText(getApplicationContext(), "Please Select Centre Type !", Toast.LENGTH_LONG).show();
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
//        if (TextUtils.isEmpty(type_Id)) {
//            Toast.makeText(getApplicationContext(), "Please Select Type", Toast.LENGTH_LONG).show();
//            //sp_block.setError("कृपया प्रखंड का नाम का चयन करे |");
//            focusView = sp_type;
//            cancelRegistration = true;
//        }
        if(img == null){
            Toast.makeText(this, "Please Capture Picture", Toast.LENGTH_SHORT).show();
            //focusView = rl_photo;
            cancelRegistration = true;
        }

        if (cancelRegistration) {
            // error in login
            focusView.requestFocus();
        } else {
            benfiList.setDist_Code(distCode);
            benfiList.setLevelType_Id(level_type_Id);
            benfiList.setCat_Code(Cat_Code);
            benfiList.setHos_Code(Hos_Code);
            benfiList.setType_Id(type_Id);
            benfiList.setImage(str_img);
            benfiList.setLatitude(latitude);
            benfiList.setLongitude(longitude);
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

//    private class loadHospital extends AsyncTask<String, Void, HospitalMastar> {
//
//        private final ProgressDialog dialog = new ProgressDialog(AddHospitalActivity.this);
//
//        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(AddHospitalActivity.this).create();
//        @Override
//        protected void onPreExecute() {
//
//            this.dialog.setCanceledOnTouchOutside(false);
//            this.dialog.setMessage("Loading...");
//            this.dialog.show();
//        }
//
//        @Override
//        protected HospitalMastar doInBackground(String... param) {
//
//
//            return WebserviceHelper.loadHospital(Dist_Code,Cat_Code,level_type_Id);
//
//        }
//
//        @Override
//        protected void onPostExecute(HospitalMastar result) {
//            if (this.dialog.isShowing()) {
//                this.dialog.dismiss();
//            }
//
//            if (result != null) {
//                DataBaseHelper placeData = new DataBaseHelper(AddHospitalActivity.this);
//                long c = placeData.insertHospital_new(result);
//                if(c>0){
//                    loadHospitaldata(Dist_Code,level_type_Id,Cat_Name);
//                }
//
//                Log.d("Resultgfg", "" + result);
//
//            }
//
//
//        }
//
//    }

    public class loadHospital extends AsyncTask<String, Void, ArrayList<FacilitiesEntity>> {
        String PanchayatCode = "",User_Id = "";

        public loadHospital() {

        }

        private final ProgressDialog dialog = new ProgressDialog(AddHospitalActivity.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(AddHospitalActivity.this).create();

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<FacilitiesEntity> doInBackground(String... params) {

            ArrayList<FacilitiesEntity> res1 = WebserviceHelper.GetQuarantineFacility_List(Cat_Code,level_type_Id,distCode);

            return res1;
        }

        @Override
        protected void onPostExecute(final ArrayList<FacilitiesEntity> result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();

                //loadSHGSpinnerData(SHGList);

                //new PsLoader(GlobalVariables.LoggedUser.get_DistrictCode(),GlobalVariables.LoggedUser.get_StateCode()).execute();

            }

            if (result != null) {
                if (result.size() > 0) {
                    HospitalList = result;
                    loadHospitaldata(result);
                } else {
                    Toast.makeText(AddHospitalActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }


//                if(c>0)
//                {
//                    setSHG_VillageSpinnerData(_varpanchayatID,CommonPref.getUserDetails(PhasuLevelActivity.this).getUserID());
//                }

            }
        }

    }
    private class RegistrationTask extends AsyncTask<String, Void, DefaultResponse> {
        private final ProgressDialog dialog = new ProgressDialog(AddHospitalActivity.this);
        AddHospitalEntity info;
        public RegistrationTask(AddHospitalEntity info) {

            this.info = info;

        }

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("अप्लोड हो रहा है...");
            this.dialog.show();
        }

        @Override
        protected DefaultResponse doInBackground(String...arg) {
            return WebserviceHelper.Registration(info);
        }

        @Override
        protected void onPostExecute(DefaultResponse result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if(result != null){
                if (result.getStatus()==true) {
                    Toast.makeText(AddHospitalActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddHospitalActivity.this,Supervisor_HomeActivity.class);
                    startActivity(i);
                }else{
                    AlertDialog.Builder ab = new AlertDialog.Builder(AddHospitalActivity.this);
                    ab.setCancelable(false);
                    ab.setTitle("Failed");
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
            }else{
                Toast.makeText(AddHospitalActivity.this, "Update Failed, Null Record", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
