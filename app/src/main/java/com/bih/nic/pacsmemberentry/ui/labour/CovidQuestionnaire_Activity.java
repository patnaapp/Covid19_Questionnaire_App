package com.bih.nic.pacsmemberentry.ui.labour;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.GlobalVariables;
import com.bih.nic.pacsmemberentry.Model.DecimalDigitsInputFilter;
import com.bih.nic.pacsmemberentry.Model.DefaultResponse;
import com.bih.nic.pacsmemberentry.Model.Questionnaire_entity;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;
import com.bih.nic.pacsmemberentry.WebserviceHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CovidQuestionnaire_Activity extends Activity implements AdapterView.OnItemSelectedListener {

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
    Button btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_questionnaire_);
        getActionBar().hide();
        Utiilties.setStatusBarColor(this);
        initialize();

        try {
            version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//                TextView tv = (TextView)getActivity().findViewById(R.id.txtVersion_1);
//                tv.setText(getActivity().getString(R.string.app_version) + version + " ");
        } catch (PackageManager.NameNotFoundException e) {
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
        et_body_temp.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,1)});

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
            }
        });
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
        edt_covid_test_date=findViewById(R.id.edt_covid_test_date);
        edt_discharge_date=findViewById(R.id.edt_discharge_date);
        img_test_date=findViewById(R.id.img_test_date);
        img_discharge_date=findViewById(R.id.img_discharge_date);
        btn_submit=findViewById(R.id.btn_submit);
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
                _ed_test_date = mYear + smMonth + smDay;

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
                    if(_fever_status_nm.equals("YES")){
                        _fever_status_id = "Y";
                    }else if(_fever_status_nm.equals("NO")){
                        _fever_status_id = "N";
                    }
                }
                break;
            case R.id.sp_Cough:
                if (position > 0) {
                    cough_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
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
                if (position > 0) {
                    med_kit_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    if(med_kit_nm.equals("YES")){
                        med_kit_id = "Y";
                    }else if(cold_nm.equals("NO")){
                        med_kit_id = "N";
                    }
                }
                break;
            case R.id.sp_family_covid_postive:
                if (position > 0) {
                    family_member_positive_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
                    if(family_member_positive_nm.equals("YES")){
                        family_member_positive_id = "Y";
                    }else if(family_member_positive_nm.equals("NO")){
                        family_member_positive_id = "N";
                    }
                }
                break;
            case R.id.sp_continue_home_isolation:
                if (position > 0) {
                    cont_home_isolatn_nm = ben_type_check[position];
                    //  tv_t_status.setError(null);
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
        String isValied = "yes";
        View focusView = null;

        if (TextUtils.isEmpty(_fever_status_id)) {
            Toast.makeText(getApplicationContext(), "Please select your fever status", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_fever_status;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(cough_id)) {
            Toast.makeText(getApplicationContext(), "Please select your cough status", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_Cough;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(cold_id)) {
            Toast.makeText(getApplicationContext(), "Please select your cold status", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_cold;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(med_kit_id)) {
            Toast.makeText(getApplicationContext(), "Please select your if you recieved medicine kit", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_medicine_kit;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(family_member_positive_id)) {
            Toast.makeText(getApplicationContext(), "Please select your if your any family member is positive", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_family_covid_postive;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(cont_home_isolatn_nm)) {
            Toast.makeText(getApplicationContext(), "Please select do you want to continue home isolation", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_continue_home_isolation;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(med_asst_id)) {
            Toast.makeText(getApplicationContext(), "Please select do you need medical assistance", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_medical_assitnce;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(yoga_id)) {
            Toast.makeText(getApplicationContext(), "Please select do you do yoga", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_yoga;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(separate_id)) {
            Toast.makeText(getApplicationContext(), "Please select do you stay separate at home", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_stay_separate;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(all_pecaution_id)) {
            Toast.makeText(getApplicationContext(), "Please select do you take all precautions", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_all_precaution;
            cancelRegistration = true;
        }


        if (TextUtils.isEmpty(et_body_temp.getText().toString())) {
            et_body_temp.setError("Please enter your body temperature");
            focusView = et_body_temp;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(et_other_prblm.getText().toString())) {
            et_other_prblm.setError("Please enter do you have any other problems.");
            focusView = et_other_prblm;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(edt_covid_test_date.getText().toString())) {
            edt_covid_test_date.setError("Please select covid test date.");
            focusView = edt_covid_test_date;
            cancelRegistration = true;
        }

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
            benfiList.setEntry_by("");
            benfiList.setFever_id(_fever_status_id);
            benfiList.setCough_id(cough_id);
            benfiList.setCold_id(cold_id);
            benfiList.setMedicine_kit_id(med_kit_id);
            benfiList.setAny_other_posiutive_id(family_member_positive_id);
            benfiList.setCont_isolation_id(cont_home_isolatn_id);
            benfiList.setMed_asst_id(med_asst_id);
            //benfiList.setHusbandName(benfiList.getHusbandName());
            benfiList.setYoga_id(yoga_id);
            benfiList.setSeparately_in_house_id(separate_id);
            benfiList.setAll_precautions_id(all_pecaution_id);
            benfiList.setBodyTemp(et_body_temp.getText().toString());
            benfiList.setOther_prblm(et_other_prblm.getText().toString());
            benfiList.setTest_date(edt_covid_test_date.getText().toString());
            benfiList.setDischarge_date(edt_discharge_date.getText().toString());


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

                new Daily_Questionnaire(benfiList).execute();


            }
        }
    }

    private class Daily_Questionnaire extends AsyncTask<String, Void, DefaultResponse> {

        private final ProgressDialog dialog = new ProgressDialog(CovidQuestionnaire_Activity.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(CovidQuestionnaire_Activity.this).create();

        Questionnaire_entity info;

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Updating...");
            this.dialog.show();
        }

        public Daily_Questionnaire(Questionnaire_entity info) {
            this.info = info;
        }

        @Override
        protected DefaultResponse doInBackground(String... param) {

            return WebserviceHelper.RegistrationNewBen(info,version);
        }

        @Override

        protected void onPostExecute(DefaultResponse result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (result != null) {

                if (result.getStatus()==true) {

                    AlertDialog.Builder ab = new AlertDialog.Builder(CovidQuestionnaire_Activity.this);
                    ab.setCancelable(false);
                    // ab.setIcon(R.drawable.biharlogo);
                    ab.setMessage("Self diagnosis uploaded successfully");
                    ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent intent = new Intent(getBaseContext(),HqHomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            //setFinishOnTouchOutside(false);
                            finish();
                        }
                    });

                    ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
                    ab.show();

                } else if (result.getStatus()==false) {

                    AlertDialog.Builder ab = new AlertDialog.Builder(CovidQuestionnaire_Activity.this);
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

            }
            else
            {

                Toast.makeText(CovidQuestionnaire_Activity.this,"इंटरनेट की स्पीड स्लो है | कृपया कुछ समय बाद प्रयास करे: ", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
