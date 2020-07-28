package com.bih.nic.pacsmemberentry.ui.patient;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.GlobalVariables;
import com.bih.nic.pacsmemberentry.Model.DecimalDigitsInputFilter;
import com.bih.nic.pacsmemberentry.Model.Questionnaire_entity;
import com.bih.nic.pacsmemberentry.Model.Upload_Questionnaire_entity;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;
import com.bih.nic.pacsmemberentry.WebserviceHelper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CovidQuestionnaire_Activity extends Activity implements AdapterView.OnItemSelectedListener, LocationListener {

    Spinner sp_fever_status,sp_Cough,sp_cold,sp_medicine_kit,sp_family_covid_postive,sp_continue_home_isolation,sp_medical_assitnce,sp_yoga,sp_stay_separate,sp_all_precaution;
    EditText et_body_temp,et_other_prblm,edt_covid_test_date,edt_discharge_date;
    ImageView img_test_date,img_discharge_date;
    private int mYear, mMonth, mDay;
    DatePickerDialog datedialog;
    String _ed_test_date="",_ed_dischrg_date="";
    String ben_type_check[] = {"-select-","YES","NO"};
    ArrayAdapter ben_aaray_check;
    String _fever_status_id="",_fever_status_nm="",cough_id="",cough_nm="",cold_id="",cold_nm="",med_kit_id="",med_kit_nm="";
    String family_member_positive_id="",family_member_positive_nm="",cont_home_isolatn_nm="",cont_home_isolatn_id="",med_asst_id="",med_asst_nm="",yoga_id="",yoga_nm="",separate_id="",separate_nm="",all_pecaution_id="",all_precaution_nm="";
    Questionnaire_entity benfiList;
    String version="";
    Button btn_submit,btn_location_lnr_1;
    String userid="",ques_count="";
    LinearLayout ll_test_date;
    LocationManager mlocManager = null;
    LocationManager locationManager;
    Location loc;
    boolean isGPS = false;
    boolean isNetwork = false;
    ArrayList<String> permissions = new ArrayList<>();
    private ProgressDialog dialog;
    static Location LastLocation = null;
    private final int UPDATE_LATLNG = 2;
    private final int UPDATE_ADDRESS = 1;
    TextView tv_lat1,tv_long1;
    String locationpoint="",loc_captured="N";
    String Lat1="",Long1="";
    ArrayList<Upload_Questionnaire_entity> daily_ques_array;
    String ques_Id="";
    String superisor_id="",patient_id="",role;
    ProgressBar profressBar1;
    LinearLayout ll_body_temp;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_questionnaire_);
        getActionBar().hide();
        Utiilties.setStatusBarColor(this);
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        userid= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Mobile", "");
        superisor_id= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("SupervisorId", "");
        patient_id= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("PatientId", "");
        role=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user_role", "");

        if(role.equals("SUP")){
            patient_id = getIntent().getStringExtra("PatientId");
        }

        initialize();
        mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        GlobalVariables.glocation = null;

        try
        {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//                TextView tv = (TextView)getActivity().findViewById(R.id.txtVersion_1);
//                tv.setText(getActivity().getString(R.string.app_version) + version + " ");
        }
        catch (PackageManager.NameNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        img_test_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
        img_discharge_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog1();
            }
        });
        et_body_temp.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(4,1)});
        btn_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                locationpoint="1";
                registration();
                //locationManager();
//                if (loc_captured.equals("Y")){
//                    registration();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "Please capture location", Toast.LENGTH_LONG).show();
//                }

            }
        });

//        btn_location_lnr_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                locationpoint="1";
//                locationManager();
//                //getLocation();
//            }
//        });
//        if (Integer.parseInt(ques_count)>0){
//            ll_test_date.setVisibility(View.GONE);
//        }
//        else if (Integer.parseInt(ques_count)==0){
//            ll_test_date.setVisibility(View.VISIBLE);
//        }
    }

    public void initialize()
    {
        sp_fever_status=findViewById(R.id.sp_fever_status);
        ben_aaray_check = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_check);
        sp_fever_status.setAdapter(ben_aaray_check);
        sp_fever_status.setOnItemSelectedListener(this);

        sp_Cough=findViewById(R.id.sp_Cough);
        ben_aaray_check = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_check);
        sp_Cough.setAdapter(ben_aaray_check);
        sp_Cough.setOnItemSelectedListener(this);

        sp_cold=findViewById(R.id.sp_cold);
        sp_cold.setOnItemSelectedListener(this);
        ben_aaray_check = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_check);
        sp_cold.setAdapter(ben_aaray_check);

        sp_medicine_kit=findViewById(R.id.sp_medicine_kit);
        sp_medicine_kit.setOnItemSelectedListener(this);
        ben_aaray_check = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_check);
        sp_medicine_kit.setAdapter(ben_aaray_check);

        sp_family_covid_postive=findViewById(R.id.sp_family_covid_postive);
        sp_family_covid_postive.setOnItemSelectedListener(this);
        ben_aaray_check = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_check);
        sp_family_covid_postive.setAdapter(ben_aaray_check);

        sp_continue_home_isolation=findViewById(R.id.sp_continue_home_isolation);
        sp_continue_home_isolation.setOnItemSelectedListener(this);
        ben_aaray_check = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_check);
        sp_continue_home_isolation.setAdapter(ben_aaray_check);

        sp_medical_assitnce=findViewById(R.id.sp_medical_assitnce);
        sp_medical_assitnce.setOnItemSelectedListener(this);
        ben_aaray_check = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_check);
        sp_medical_assitnce.setAdapter(ben_aaray_check);

        sp_yoga=findViewById(R.id.sp_yoga);
        sp_yoga.setOnItemSelectedListener(this);
        ben_aaray_check = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_check);
        sp_yoga.setAdapter(ben_aaray_check);

        sp_stay_separate=findViewById(R.id.sp_stay_separate);
        sp_stay_separate.setOnItemSelectedListener(this);
        ben_aaray_check = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_check);
        sp_stay_separate.setAdapter(ben_aaray_check);

        sp_all_precaution=findViewById(R.id.sp_all_precaution);
        sp_all_precaution.setOnItemSelectedListener(this);
        ben_aaray_check = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_check);
        sp_all_precaution.setAdapter(ben_aaray_check);

        et_body_temp=findViewById(R.id.et_body_temp);
        et_other_prblm=findViewById(R.id.et_other_prblm);
        et_other_prblm.addTextChangedListener(inputTextWatcher1);
        edt_covid_test_date=findViewById(R.id.edt_covid_test_date);
        edt_discharge_date=findViewById(R.id.edt_discharge_date);
        img_test_date=findViewById(R.id.img_test_date);
        img_discharge_date=findViewById(R.id.img_discharge_date);
        ll_test_date=findViewById(R.id.ll_test_date);
        btn_submit=findViewById(R.id.btn_submit);
        btn_location_lnr_1=findViewById(R.id.btn_location_lnr_1);

        tv_lat1=findViewById(R.id.tv_lat_lnr1);
        tv_long1=findViewById(R.id.tv_long_lnr1);
        ll_body_temp=findViewById(R.id.ll_body_temp);

        daily_ques_array = new ArrayList<>();
    }

    public void ShowDialog()
    {


        Calendar c = Calendar.getInstance();
        Date min = new Date(2018, 4, 25);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datedialog = new DatePickerDialog(CovidQuestionnaire_Activity.this,
                mDateSetListener, mYear, mMonth, mDay);

        datedialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        // datedialog.getDatePicker().setMinDate(min.getTime());
        datedialog.show();

    }

    public void ShowDialog1()
    {


        Calendar c = Calendar.getInstance();
        Date min = new Date(2018, 4, 25);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datedialog = new DatePickerDialog(CovidQuestionnaire_Activity.this,
                mDateSetListener1, mYear, mMonth, mDay);

        // datedialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        // datedialog.getDatePicker().setMinDate(min.getTime());
        datedialog.show();

    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            mYear = selectedYear;
            mMonth = selectedMonth;
            mDay = selectedDay;
            String ds = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            ds = ds.replace("/", "-");
            String[] separated = ds.split(" ");
            Date min = new Date(2018, 4, 25);
            try {
                // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTimeString = sdf.getTimeInstance().format(new Date());
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String newString = currentTimeString.replace("A.M.", "");

                String smDay = "" + mDay, smMonth = "" + (mMonth + 1);
                if (mDay < 10) {
                    smDay = "0" + mDay;//Integer.parseInt("0" + mDay);
                }
                if ((mMonth + 1) < 10) {
                    smMonth = "0" + (mMonth + 1);
                }


                edt_covid_test_date.setText(smDay + "-" + smMonth + "-" + mYear);
                //_DOB = mYear + "-" + smMonth + "-" + smDay + " " + newString;
                _ed_test_date = mYear +"-"+smMonth+"-"+ smDay;

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    };

    DatePickerDialog.OnDateSetListener mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            mYear = selectedYear;
            mMonth = selectedMonth;
            mDay = selectedDay;
            String ds = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            ds = ds.replace("/", "-");
            String[] separated = ds.split(" ");
            Date min = new Date(2018, 4, 25);
            try {
                // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTimeString = sdf.getTimeInstance().format(new Date());
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String newString = currentTimeString.replace("A.M.", "");

                String smDay = "" + mDay, smMonth = "" + (mMonth + 1);
                if (mDay < 10) {
                    smDay = "0" + mDay;//Integer.parseInt("0" + mDay);
                }
                if ((mMonth + 1) < 10) {
                    smMonth = "0" + (mMonth + 1);
                }


                edt_discharge_date.setText(smDay + "-" + smMonth + "-" + mYear);
                //_DOB = mYear + "-" + smMonth + "-" + smDay + " " + newString;
                _ed_dischrg_date = mYear + smMonth + smDay;

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.sp_fever_status:
                if (position > 0) {
                    _fever_status_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    ques_Id="";
                    if(_fever_status_nm.equals("YES")){
                        _fever_status_id = "Y";
                        ll_body_temp.setVisibility(View.VISIBLE);
                    }else if(_fever_status_nm.equals("NO")){
                        _fever_status_id = "N";
                        ll_body_temp.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.sp_Cough:
                if (position > 0) {
                    cough_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    ques_Id="";
                    if(cough_nm.equals("YES")){
                        cough_id = "Y";
                    }else if(cough_nm.equals("NO")){
                        cough_id = "N";
                    }
                }
                break;
            case R.id.sp_cold:
                if (position > 0) {
                    cold_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    ques_Id="";
                    if(cold_nm.equals("YES")){
                        cold_id = "Y";
                    }else if(cold_nm.equals("NO")){
                        cold_id = "N";
                    }
                }
                break;
//            case R.id.sp_:
//                if (position > 0) {
//                    cold_nm = ben_type_check[position];
//                    //  tv_t_status.setError(null);
//                    if(cold_nm.equals("YES")){
//                        cold_id = "Y";
//                    }else if(cold_nm.equals("NO")){
//                        cold_id = "N";
//                    }
//                }
//                break;
            case R.id.sp_medicine_kit:
                if (position > 0)
                {
                    med_kit_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    ques_Id="";
                    if(med_kit_nm.equals("YES"))
                    {
                        med_kit_id = "Y";
                    }
                    else if(med_kit_nm.equals("NO"))
                    {
                        med_kit_id = "N";
                    }
                }
                break;
            case R.id.sp_family_covid_postive:
                if (position > 0) {
                    family_member_positive_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    ques_Id="";
                    if(family_member_positive_nm.equals("YES"))
                    {
                        family_member_positive_id = "Y";
                    }
                    else if(family_member_positive_nm.equals("NO"))
                    {
                        family_member_positive_id = "N";
                    }
                }
                break;
            case R.id.sp_continue_home_isolation:
                if (position > 0) {
                    cont_home_isolatn_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    ques_Id="";
                    if(cont_home_isolatn_nm.equals("YES")){
                        cont_home_isolatn_id = "Y";
                    }else if(cont_home_isolatn_nm.equals("NO")){
                        cont_home_isolatn_id = "N";
                    }
                }
                break;

            case R.id.sp_medical_assitnce:
                if (position > 0) {
                    med_asst_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    ques_Id="";
                    if(med_asst_nm.equals("YES")){
                        med_asst_id = "Y";
                    }else if(med_asst_nm.equals("NO")){
                        med_asst_id = "N";
                    }
                }
                break;
            case R.id.sp_yoga:
                if (position > 0) {
                    yoga_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    ques_Id="";
                    if(yoga_nm.equals("YES")){
                        yoga_id = "Y";
                    }else if(yoga_nm.equals("NO")){
                        yoga_id = "N";
                    }
                }
                break;
            case R.id.sp_stay_separate:
                if (position > 0) {
                    separate_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    ques_Id="";
                    if(separate_nm.equals("YES")){
                        separate_id = "Y";
                    }else if(separate_nm.equals("NO")){
                        separate_id = "N";
                    }
                }
                break;
            case R.id.sp_all_precaution:

                if (position > 0) {
                    all_precaution_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    ques_Id="";
                    if(all_precaution_nm.equals("YES")){
                        all_pecaution_id = "Y";

                    }else if(all_precaution_nm.equals("NO")){
                        all_pecaution_id = "N";
                    }
                }
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void registration() {

        benfiList = new Questionnaire_entity();
        boolean cancelRegistration = false;
        String isvalid = "yes";
        View focusView = null;

        if (TextUtils.isEmpty(_fever_status_id)) {
            ((TextView)sp_fever_status.getSelectedView()).setError("Please select your fever status");
            //Toast.makeText(getApplicationContext(), "Please select your fever status", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_fever_status;

            cancelRegistration = true;

        }

        if (TextUtils.isEmpty(cough_id)) {
            ((TextView)sp_Cough.getSelectedView()).setError("Please select your cough status");
            //  Toast.makeText(getApplicationContext(), "Please select your cough status", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_Cough;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(cold_id)) {
            ((TextView)sp_cold.getSelectedView()).setError("Please select your cold status");
            //Toast.makeText(getApplicationContext(), "Please select your cold status", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_cold;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(med_kit_id)) {
            ((TextView)sp_medicine_kit.getSelectedView()).setError("Please select your if you recieved medicine kit");
            // Toast.makeText(getApplicationContext(), "Please select your if you recieved medicine kit", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_medicine_kit;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(family_member_positive_id)) {
            ((TextView)sp_family_covid_postive.getSelectedView()).setError("Please select your if your any family member is positive");
            //   Toast.makeText(getApplicationContext(), "Please select your if your any family member is positive", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_family_covid_postive;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(cont_home_isolatn_nm)) {
            ((TextView)sp_continue_home_isolation.getSelectedView()).setError("Please select do you want to continue home isolation");
            //   Toast.makeText(getApplicationContext(), "Please select do you want to continue home isolation", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_continue_home_isolation;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(med_asst_id)) {
            ((TextView)sp_medical_assitnce.getSelectedView()).setError("Please select do you need medical assistance");
            //Toast.makeText(getApplicationContext(), "Please select do you need medical assistance", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_medical_assitnce;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(yoga_id)) {
            ((TextView)sp_yoga.getSelectedView()).setError("Please select do you do yoga");
            //    Toast.makeText(getApplicationContext(), "Please select do you do yoga", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_yoga;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(separate_id)) {
            ((TextView)sp_stay_separate.getSelectedView()).setError("Please select do you stay separate at home");
            //   Toast.makeText(getApplicationContext(), "Please select do you stay separate at home", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_stay_separate;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(all_pecaution_id)) {
            ((TextView)sp_all_precaution.getSelectedView()).setError("Please select do you take all precautions");
            //   Toast.makeText(getApplicationContext(), "Please select do you take all precautions", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_all_precaution;
            cancelRegistration = true;
        }

        if (_fever_status_id.equals("Y"))
        {
            if (TextUtils.isEmpty(et_body_temp.getText().toString()))
            {
                et_body_temp.setError("Please enter your body temperature");
                focusView = et_body_temp;
                cancelRegistration = true;
            }
            else if (Float.parseFloat(et_body_temp.getText().toString())<90){
                et_body_temp.setError("Please enter correct body temperature");
                focusView = et_body_temp;
                cancelRegistration = true;
            }
        }

        if (TextUtils.isEmpty(et_other_prblm.getText().toString())) {
            et_other_prblm.setError("Please enter do you have any other problems.");
            focusView = et_other_prblm;
            cancelRegistration = true;
        }

        // if (Integer.parseInt(ques_count)==0) {
        if (TextUtils.isEmpty(edt_covid_test_date.getText().toString())) {
            edt_covid_test_date.setError("Please select covid test date.");
            focusView = edt_covid_test_date;
            cancelRegistration = true;
        }
        // }

//        if (TextUtils.isEmpty(edt_discharge_date.getText().toString())) {
//            edt_discharge_date.setError("Please select discharge test date.");
//            focusView = edt_discharge_date;
//            cancelRegistration = true;
//        }
//

        if (cancelRegistration) {
            // error in login
            focusView.requestFocus();
        } else {

            locationManager();

        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private class Daily_Questionnaire extends AsyncTask<String, Void, String> {

        ArrayList<Upload_Questionnaire_entity> data;

        private final ProgressDialog progressDialog = new ProgressDialog(CovidQuestionnaire_Activity.this);


        Daily_Questionnaire(ArrayList<Upload_Questionnaire_entity> data)
        {
            this.data = data;

        }
        protected void onPreExecute() {
            progressDialog.setCancelable(false);
            progressDialog.show();

            progressDialog.setMessage("Uploading data...");

        }

        @Override
        protected String doInBackground(String... param) {

            String res= WebserviceHelper.UploadAttendanceData(CovidQuestionnaire_Activity.this,data);

            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                profressBar1.setVisibility(View.GONE);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();

                }
            }
            catch(Exception e)
            {

            }

            try
            {
                if(result!=null)
                {

                    if(result.equals("1"))
                    {
                        progressDialog.dismiss();
                        AlertDialog.Builder ab = new AlertDialog.Builder(
                                CovidQuestionnaire_Activity.this);
                        ab.setIcon(R.mipmap.ic_launcher);
                        ab.setTitle("Success");
                        ab.setMessage("Questionnaire Submitted Successfuly");
                        ab.setNegativeButton("[ OK ]", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {

                                dialog.dismiss();
                                if(role.equals("SUP")){
                                    Intent intent = new Intent();
                                    intent.putExtra("inserted", true);
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }else{
                                    Intent i=new Intent(CovidQuestionnaire_Activity.this,HqHomeActivity.class);
                                    startActivity(i);
                                    finish();
                                }

                            }
                        });


                        ab.show();


                    }
                    else {
                        progressDialog.dismiss();
                        // Toast.makeText(getApplicationContext(), "Sorry! failed to upload Attendance for " + _aid+" \nResponse " , Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Uploading failed" , Toast.LENGTH_SHORT).show();

                    }
                }
                else
                { progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Response:NULL, Sorry! failed to upload", Toast.LENGTH_SHORT).show();

                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void locationManager() {
        dialog.setMessage("Capturing location...");
        dialog.show();

        if (GlobalVariables.glocation == null) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            //btn_location1.setEnabled(false);
            mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, (float) 0.01, mlistener);
            mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, (float) 0.01, mlistener);
        } else {
            //btn_location1.setEnabled(true);
            //pbar.setVisibility(View.GONE);
            //progress_finding_location.setVisibility(View.GONE);

        }
    }


    private void updateUILocation(Location location) {

        Message.obtain(
                mHandler,
                UPDATE_LATLNG,
                new DecimalFormat("#.0000000").format(location.getLatitude())
                        + "-"
                        + new DecimalFormat("#.0000000").format(location
                        .getLongitude()) + "-" + location.getAccuracy() + "-" + location.getTime())
                .sendToTarget();

    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case UPDATE_ADDRESS:
                case UPDATE_LATLNG:
                    String[] LatLon = ((String) msg.obj).split("-");
//
                    Log.e("", "Lat-Long" + LatLon[0] + "   " + LatLon[1]);


                    break;
            }
        }
    };

    private final LocationListener mlistener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {

            //A new location update is received. Do something useful with it.
            //Update the UI with
            //the location update.
            if (Utiilties.isGPSEnabled(CovidQuestionnaire_Activity.this)) {

                LastLocation = location;
                GlobalVariables.glocation = location;
                updateUILocation(GlobalVariables.glocation);
                //   if (getIntent().getStringExtra("KEY_PIC").equals("1")) {
                if (location.getLatitude() > 0.0) {
                    //dialog = new ProgressDialog(CAptureFieldLocationActivity.this);
                    //long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                    if (location.getAccuracy() > 0 && location.getAccuracy() < 150) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        //pbar.setVisibility(View.GONE);
                        btn_location_lnr_1.setEnabled(true);

//                        if (locationpoint.equals("1"))
//                        {

                        Lat1 = Double.toString(location.getLatitude());
                        Long1 = Double.toString(location.getLongitude());

                        tv_lat1.setText("LAT: "+Lat1);
                        tv_long1.setText("LONG: "+Long1);
                        //Log.d("Lat",+Lat1);
                        loc_captured="Y";
                        locationManager.removeUpdates(this);

                        btn_location_lnr_1.setEnabled(false);
                        Log.d("ADebugTag", "Value: " + Long1);
                        if (Lat1 !=null ||Lat1 !=""){
                            //registration();
                            benfiList.setEntry_by(userid);
                            benfiList.setFever_id(_fever_status_id);
                            benfiList.setCough_id(cough_id);
                            benfiList.setCold_id(cold_id);
                            benfiList.setMedicine_kit_id(med_kit_id);
                            benfiList.setAny_other_posiutive_id(family_member_positive_id);
                            benfiList.setCont_isolation_id(cont_home_isolatn_id);
                            benfiList.setMed_asst_id(med_asst_id);
                            benfiList.setYoga_id(yoga_id);
                            benfiList.setSeparately_in_house_id(separate_id);
                            benfiList.setAll_precautions_id(all_pecaution_id);
                            benfiList.setBodyTemp(et_body_temp.getText().toString());
                            benfiList.setOther_prblm(et_other_prblm.getText().toString());
                            benfiList.setTest_date(edt_covid_test_date.getText().toString());
                            // benfiList.setDischarge_date(edt_discharge_date.getText().toString());
                            benfiList.setLatitude(Lat1);
                            benfiList.setLongitude(Long1);

                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"1",_fever_status_id,Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"2",cough_id,Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"3",cold_id,Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"4",et_other_prblm.getText().toString(),Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"5",med_kit_id,Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"6",family_member_positive_id,Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"7",cont_home_isolatn_id,Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"8",med_asst_id,Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"9",yoga_id,Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"10",separate_id,Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"11",all_pecaution_id,Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));
                            daily_ques_array.add(new Upload_Questionnaire_entity(userid,superisor_id,patient_id,"12",et_body_temp.getText().toString(),Utiilties.getCurrentDate(),Lat1,Long1,version,_ed_test_date));

                            if (!GlobalVariables.isOffline && !Utiilties.isOnline(CovidQuestionnaire_Activity.this)) {

                                AlertDialog.Builder ab = new AlertDialog.Builder(CovidQuestionnaire_Activity.this);
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

                                new Daily_Questionnaire(daily_ques_array).execute();


                            }
                        }


                        // }

                    }
                    else
                    {
                        dialog.setMessage("Wait for gps to become stable");
                        dialog.show();
                        btn_location_lnr_1.setEnabled(false);

                    }

                }


            }
            else
            {
                Message.obtain(
                        mHandler,
                        UPDATE_LATLNG,
                        new DecimalFormat("#.0000000").format(location.getLatitude())
                                + "-"
                                + new DecimalFormat("#.0000000").format(location
                                .getLongitude()) + "-" + location.getAccuracy() + "-" + location.getTime())
                        .sendToTarget();

            }


        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

    };

    private TextWatcher inputTextWatcher1 = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count,int after)
        {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            if (et_other_prblm.getText().length() >0)
            {
                checkForEnglish(et_other_prblm);
            }
        }

        @Override
        public void afterTextChanged(Editable s)
        {

        }
    };

    public void checkForEnglish(EditText etxt)
    {
        if (etxt.getText().length() > 0)
        {
            String s = etxt.getText().toString();
            if (isInputInEnglish(s))
            {
                //OK
            }
            else
            {
                Toast.makeText(this, "Please write in english and only text ,no numbers", Toast.LENGTH_SHORT).show();
                etxt.setText("");
            }
        }
    }

    public static boolean isInputInEnglish(String txtVal)
    {
        String regx = "^[A-Z_ ]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txtVal);
        return matcher.find();
    }

}
