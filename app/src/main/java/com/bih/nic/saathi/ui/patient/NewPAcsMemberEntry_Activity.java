package com.bih.nic.saathi.ui.patient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bih.nic.saathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.saathi.GlobalVariables;
import com.bih.nic.saathi.Model.BlockWeb;
import com.bih.nic.saathi.Model.PacsMemeberEntity;
import com.bih.nic.saathi.R;
import com.bih.nic.saathi.Utiilties;
import com.bih.nic.saathi.Verhoeff;
import com.bih.nic.saathi.WebserviceHelper;

import java.util.ArrayList;

public class NewPAcsMemberEntry_Activity extends Activity {

    Button btn_proceed;
    Spinner sp_block,sp_pacs,sp_ward,sp_revenue_vill,sp_gender,sp_categ,sp_birth_yr,sp_yogyata,sp_marital_status,sp_vyaparmandal,sp_panchayat;
    EditText et_reg_no,et_ben_NAME,et_f_name,et_aadhaar,et_ben_mob;
    ArrayList<BlockWeb>BlockList=new ArrayList<>();
    DataBaseHelper dataBaseHelper;
    String DistrictCode="",block_id="",block_name="",pacs_id="",pacs_name="",ward_id="",ward_name="",vill_id="",vill_name="",gender_id="",gender_name="",categ_id="",categ_name="",yogyta_id="",yogyata_name="";
    String marital_stat_code="",marital_stat_name="";
    private boolean validAadhaar;
    String famrmer_reg_no="",ben_name="",father_name="",aadhaar_no="",ben_mob="",birth_yr_id="",birth_yr_nm="",User_Role="",vyaparmandal_id="",vyaparmandal_name="";
    PacsMemeberEntity benfiList;
    String pan_id="",pan_name="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_p_acs_member_entry_);

        getActionBar().hide();
        Utiilties.setStatusBarColor(this);
        dataBaseHelper=new DataBaseHelper(this);

        initialise();
        User_Role= PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserRole", "");
        loadBlockSpinnerData(DistrictCode);


        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
            }
        });

        sp_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position > 0)
                {
                    block_id = BlockList.get(position-1).getBlockCode();
                    block_name = BlockList.get(position-1).getBlockName();

                }
                else
                {
                    block_id = "";
                    block_name = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                block_id = "";
                block_name = "";
            }

        });
    }

    public void initialise()
    {
        btn_proceed=findViewById(R.id.btn_proceed);
        sp_block=findViewById(R.id.sp_block);
        sp_pacs=findViewById(R.id.sp_pacs);
        sp_ward=findViewById(R.id.sp_ward);
        sp_revenue_vill=findViewById(R.id.sp_revenue_vill);
        sp_gender=findViewById(R.id.sp_gender);
        sp_categ=findViewById(R.id.sp_categ);
        sp_birth_yr=findViewById(R.id.sp_birth_yr);
        sp_yogyata=findViewById(R.id.sp_yogyata);
        sp_marital_status=findViewById(R.id.sp_marital_status);
        sp_vyaparmandal=findViewById(R.id.sp_vyaparmandal);
        sp_panchayat=findViewById(R.id.sp_panchayat);
        et_reg_no=findViewById(R.id.et_reg_no);
        et_ben_NAME=findViewById(R.id.et_ben_NAME);
        et_f_name=findViewById(R.id.et_f_name);
        et_aadhaar=findViewById(R.id.et_aadhaar);
        et_aadhaar.addTextChangedListener(inputTextWatcher1);
        et_ben_mob=findViewById(R.id.et_ben_mob);
    }

    public void loadBlockSpinnerData(String district)
    {
        BlockList.clear();
        BlockList = dataBaseHelper.getBlockDetail(district);
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");
        int index = 0;
        for (BlockWeb info: BlockList)
        {
            list.add(info.getBlockName());
            //if(benDetails.get)
        }
        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_block.setAdapter(adaptor);
//        if(benDetails.getBlockName()!=null){
//            sp_block.setSelection(((ArrayAdapter<String>) sp_block.getAdapter()).getPosition(sp_block.getBlockName()));
//
//        }
//
//        sp_block.setEnabled(false);
    }

    private TextWatcher inputTextWatcher1 = new TextWatcher()
    {

        public void beforeTextChanged(CharSequence s, int start, int count,int after)
        {

        }

        public void onTextChanged(CharSequence s, int start, int before,int count)
        {
            if (et_aadhaar.getText().length() == 12)
            {
                try
                {
                    if (Verhoeff.validateVerhoeff(et_aadhaar.getText().toString()))
                    {
                        et_aadhaar.setTextColor(Color.parseColor("#0B610B"));
                        validAadhaar = true;
                    }
                    else
                    {
                        //showMessageDialogue("Invalid Aadhaar Number");
                        Toast.makeText(getApplicationContext(),"आधार नंबर गलत है ",Toast.LENGTH_LONG).show();
                        et_aadhaar.setTextColor(Color.parseColor("#ff0000"));
                        validAadhaar = false;
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                et_aadhaar.setTextColor(Color.parseColor("#000000"));
                validAadhaar = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            if (!validAadhaar && et_aadhaar.getText().toString().length() == 12)
            {
//                CommonMethods.showErrorDialog(getResources().getString(R.string.invalid_value),
//                        getResources().getString(R.string.check_aadhaar_no));
            }
        }
    };


    public void registration()
    {
        //Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        famrmer_reg_no = et_reg_no.getText().toString();
        ben_name = et_ben_NAME.getText().toString();
        father_name = et_f_name.getText().toString();
        aadhaar_no = et_aadhaar.getText().toString();
        ben_mob = et_ben_mob.getText().toString();
        boolean cancelRegistration = false;
        String isValied = "yes";
        View focusView = null;

        if(User_Role.equals(""))
        {
            if (TextUtils.isEmpty(block_id))
            {
                Toast.makeText(getApplicationContext(), "कृपया प्रखंड का नाम का चयन करे |", Toast.LENGTH_LONG).show();
                // sp_district.setError("कृपया जिला का नाम का चयन करे |");
                focusView = sp_block;
                cancelRegistration = true;
            }
            if (TextUtils.isEmpty(pacs_id))
            {
                Toast.makeText(getApplicationContext(), "कृपया पैक्स का नाम का चयन करे |", Toast.LENGTH_LONG).show();
                // sp_district.setError("कृपया जिला का नाम का चयन करे |");
                focusView = sp_pacs;
                cancelRegistration = true;
            }
        }
        else if(User_Role.equals(""))
        {
            if (TextUtils.isEmpty(vyaparmandal_id))
            {
                Toast.makeText(getApplicationContext(), "कृपया व्यापर मंडल का नाम का चयन करे |", Toast.LENGTH_LONG).show();

                focusView = sp_vyaparmandal;
                cancelRegistration = true;
            }
            if (TextUtils.isEmpty(pan_id))
            {
                Toast.makeText(getApplicationContext(), "कृपया पंचायत का नाम का चयन करे |", Toast.LENGTH_LONG).show();

                focusView = sp_panchayat;
                cancelRegistration = true;
            }
        }

        if (TextUtils.isEmpty(ward_id))
        {
            Toast.makeText(getApplicationContext(), "कृपया वार्ड का नाम का चयन करे |", Toast.LENGTH_LONG).show();

            focusView = sp_ward;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(vill_id))
        {

            Toast.makeText(getApplicationContext(), "कृपया गाँव का चयन करें |", Toast.LENGTH_LONG).show();

            focusView = sp_revenue_vill;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(gender_id))
        {

            Toast.makeText(getApplicationContext(), "कृपया लिंग का चयन करे |", Toast.LENGTH_LONG).show();
            focusView = sp_gender;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(categ_id))
        {
            Toast.makeText(getApplicationContext(), "कृपया केटेगरी का  चयन करे |", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_categ;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(yogyta_id))
        {

            Toast.makeText(getApplicationContext(), "कृपया योग्यता का चयन करे |", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_yogyata;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(marital_stat_code))
        {

            Toast.makeText(getApplicationContext(), "कृपया वय्वाहिक स्तिथि का चयन करे |", Toast.LENGTH_LONG).show();
            // sp_district.setError("कृपया जिला का नाम का चयन करे |");
            focusView = sp_marital_status;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(et_reg_no.getText().toString()))
        {
            et_reg_no.setError("कृपया किसान निबंधन संख्या  डाले |");
            focusView = et_reg_no;
            cancelRegistration = true;
        }
        if (TextUtils.isEmpty(ben_name))
        {
            et_ben_NAME.setError("कृपया नाम (आधार के अनुसार) डाले |");
            focusView = et_ben_NAME;
            cancelRegistration = true;
        }

        if (TextUtils.isEmpty(et_f_name.getText().toString()))
        {
            et_f_name.setError("कृपया पिता/पति का नाम डाले |");
            focusView = et_f_name;
            cancelRegistration = true;
        }

        if(!validAadhaar)
        {
            if(!Verhoeff.validateVerhoeff(aadhaar_no))
            {
                et_aadhaar.setError("कृपया आधार नंबर सही डाले |");
                focusView = et_aadhaar;
                cancelRegistration = true;
            }
        }
        if (cancelRegistration)
        {
            focusView.requestFocus();
        }
        else
        {
            benfiList.setFarmer_name(ben_name);
            benfiList.setYob(birth_yr_id);
            benfiList.setAadhaar_no(et_aadhaar.getText().toString().trim());
            benfiList.setFarmer_father_name(et_f_name.getText().toString().trim());
            benfiList.setFarmer_reg_no(et_reg_no.getText().toString().trim());

            if (!GlobalVariables.isOffline && !Utiilties.isOnline(this))
            {

                AlertDialog.Builder ab = new AlertDialog.Builder(this);
                ab.setMessage(Html.fromHtml("<font color=#000000>Internet Connection is not avaliable..Please Turn ON Network Connection </font>"));
                ab.setPositiveButton("Turn On Network Connection", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        Intent I = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(I);
                    }
                });

                ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
                ab.show();

            }
            else
            {
                new ValidateAdhhar(benfiList).execute();
            }

        }
    }


    private class ValidateAdhhar extends AsyncTask<String, Void, String>
    {

        String pid = "-1";
        PacsMemeberEntity data;
        private final ProgressDialog dialog = new ProgressDialog(NewPAcsMemberEntry_Activity.this);

        ValidateAdhhar(PacsMemeberEntity data)
        {
            this.data = data;
            //pid = data.getId();
            Log.e("Pid  ", pid + " ");
        }

        @Override
        protected void onPreExecute()
        {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage(getResources().getString(R.string.uploading));
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param)
        {
            String res = WebserviceHelper.VerifyAdhaar1(this.data);
            return res;
        }

        @Override
        protected void onPostExecute(String result)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }

            try
            {
                if (result != null)
                {
                    if (result.equalsIgnoreCase("Authentication Success"))
                    {

                        showSuccessMessageDialogue(result.toString());

                    }
                    else
                    {
                        showMessageDialogue("आधार और नाम सही नहीं", "कृपया सही नाम और आधार संख्या डालें");
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "इंटरनेट की स्पीड स्लो है | कृपया कुछ समय बाद प्रयास करे", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }



        public void showMessageDialogue(String messageTxt, String essage)
        {

            new AlertDialog.Builder(NewPAcsMemberEntry_Activity.this)
                    .setCancelable(false)
                    .setTitle(messageTxt)
                    .setMessage(essage)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                        }
                    })
                    .show();

        }

        public void showSuccessMessageDialogue(String messageTxt)
        {

            new AlertDialog.Builder(NewPAcsMemberEntry_Activity.this)
                    .setCancelable(false)
                    .setTitle("सफल !!")
                    .setMessage("आपका आधार सत्यापन सफल हुआ !")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {

                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            moveToNext();
                        }
                    })
                    .show();
        }

    }

    public void moveToNext()
    {
        PacsMemeberEntity info = new PacsMemeberEntity();
        info.setBlock_code(block_id);
        info.setPacs_code(pacs_id);
        info.setWard_code(ward_id);
        info.setVill_code(vill_id);
        info.setFarmer_reg_no(famrmer_reg_no);
        info.setFarmer_name(ben_name);
        info.setFarmer_father_name(father_name);
        info.setGender_code(gender_id);
        info.setCateg_code(categ_id);
        info.setYob(birth_yr_id);

        info.setAadhaar_no(aadhaar_no);
        info.set_farmer_mob(ben_mob);
        info.setYogyata_code(yogyta_id);
        info.setMarital_stat_code(marital_stat_code);

        Intent i = new Intent(NewPAcsMemberEntry_Activity.this,BankDetailsActivity.class);
        i.putExtra("data", info);
        startActivity(i);

    }
}
