package com.bih.nic.pacsmemberentry.ui.patient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;
import com.bih.nic.pacsmemberentry.GlobalVariables;
import com.bih.nic.pacsmemberentry.Model.BenDetails;
import com.bih.nic.pacsmemberentry.Model.BlockWeb;
import com.bih.nic.pacsmemberentry.Model.CategoryMaster;
import com.bih.nic.pacsmemberentry.Model.District;
import com.bih.nic.pacsmemberentry.Model.SkillMaster;
import com.bih.nic.pacsmemberentry.Model.SubSkillMaster;
import com.bih.nic.pacsmemberentry.Model.panchayat;
import com.bih.nic.pacsmemberentry.Model.qualification;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;
import com.bih.nic.pacsmemberentry.Verhoeff;
import com.bih.nic.pacsmemberentry.WebserviceHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class ModifyDocumentActivity extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner spn_panch_name,spn_block_name,spn_dist_name,spn_sub_skill,spn_skill,spn_category,spn_type_of_worker,spn_gender;
    EditText edt_exp_year,edt_Qualification,edt_catogery,edt_ifsc_code,edt_ac_name,edt_ac_no,edt_mobileno,edt_aadharno,edt_exp_month,edt_age,edt_gaurdian,edt_gaurdian_no,edt_address,edt_prev_address;
    BenDetails benDetails;
    DataBaseHelper dataBaseHelper;

    ArrayList<SkillMaster> skillList;
    ArrayList<CategoryMaster> cateogryList;
    ArrayList<qualification> Qualification;
    ArrayList<SubSkillMaster> subSkillList;
    ArrayList<District>DistrictList=new ArrayList<>();
    ArrayList<BlockWeb>BlockList=new ArrayList<>();
    ArrayList<panchayat>PanchayatList=new ArrayList<>();
    Button save_button,valAdhaar;
    EditText edt_aadharn_name;
    boolean check=true, isVerified = false, validIfsc= false;
    ImageView img_back;

    String skillId,subSkillId,CategoryId;
    String Dist_id="",Dist_name="",block_id="",block_name="",panch_id="",panch_name="",cat_id="",cat_name="",eduction_id="";
    String Mobile_no="",Bank_Ac_no="",_Bank_name="",Ifsc_code="",catogery="",skill_id="",sub_Skill_id="",Qualificaton="",int_no_year_exp="",str_adhaar="",str_int_no_month_exp="";

    String ben_type_aangan[] = {"-चयन करे-","पुरुष","महिला","ट्रांसजेंडर"};
    String Gender_Name="",Gender_Code="";
    ArrayAdapter ben_type_aangan_aaray;
    EditText edt_reg_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_document);

        getActionBar().hide();

        Utiilties.setStatusBarColor(this);

        benDetails=(BenDetails)getIntent().getSerializableExtra("data");
        img_back=(ImageView) findViewById(R.id.img);
        dataBaseHelper=new DataBaseHelper(this);
        spn_panch_name=(Spinner)findViewById(R.id.spn_panch_name);
        spn_block_name=(Spinner)findViewById(R.id.spn_block_name);

        spn_dist_name=(Spinner)findViewById(R.id.spn_dist_name);
        spn_sub_skill=(Spinner)findViewById(R.id.spn_sub_skill);
        spn_skill=(Spinner)findViewById(R.id.spn_skill);
        spn_category=(Spinner)findViewById(R.id.spn_category);
        spn_type_of_worker=(Spinner)findViewById(R.id.spn_type_of_worker);
        spn_gender=(Spinner)findViewById(R.id.spn_gender);
        edt_exp_year=(EditText)findViewById(R.id.edt_exp_year);
        edt_ifsc_code=(EditText)findViewById(R.id.edt_ifsc_code);
        edt_ac_name=(EditText)findViewById(R.id.edt_ac_name);
        edt_ac_no=(EditText)findViewById(R.id.edt_ac_no);
        edt_mobileno=(EditText)findViewById(R.id.edt_mobileno);
        edt_aadharno=(EditText)findViewById(R.id.edt_aadharno);
        edt_exp_month=(EditText)findViewById(R.id.edt_exp_month);
        edt_reg_name=(EditText)findViewById(R.id.edt_reg_name);

        edt_age=(EditText)findViewById(R.id.edt_age);
        edt_gaurdian=(EditText)findViewById(R.id.edt_gaurdian);
        edt_gaurdian_no=(EditText)findViewById(R.id.edt_gaurdian_no);
        edt_address=(EditText)findViewById(R.id.edt_address);
        edt_prev_address=(EditText)findViewById(R.id.edt_prev_address);

        save_button=(Button)findViewById(R.id.save_button) ;
        valAdhaar=(Button)findViewById(R.id.valAdhaar) ;

        edt_aadharn_name=(EditText)findViewById(R.id.edt_aadharn_name) ;


        spn_panch_name.setOnItemSelectedListener(this);
        spn_block_name.setOnItemSelectedListener(this);
        spn_dist_name.setOnItemSelectedListener(this);

        spn_sub_skill.setOnItemSelectedListener(this);
        spn_skill.setOnItemSelectedListener(this);
        spn_category.setOnItemSelectedListener(this);

        //spn_gender=findViewById(R.id.spin_gender);
        ben_type_aangan_aaray = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_aangan);
        spn_gender.setAdapter(ben_type_aangan_aaray);

        edt_ifsc_code.addTextChangedListener(inputTextWatcher1);

        extractFrom_Data();

        try{
            int index = Integer.parseInt(benDetails.getIntGender());
            spn_gender.setSelection(index);
        }catch (Exception e){

        }


        loadSkillSpinnerData();
        loadCategorySpinnerData();
        loadDistrictSpinnerData();
        loadQualification();
        loadBlockSpinnerData(benDetails.getDistrictCode());
        loadPanchayatSpinnerData(benDetails.getBlockCode());
        loadSubSkillSpinnerData(benDetails.getSkill_Id());

        if(check && isVerified){
            valAdhaar.setVisibility(View.GONE);
        }

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spn_panch_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    panch_id = PanchayatList.get(position-1).getPanchayatId();
                    panch_name = PanchayatList.get(position-1).getPanchayatName();

                } else {
                    panch_id = "";
                    panch_name = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                panch_id = "";
                panch_name = "";

            }

        });
        spn_block_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    block_id = BlockList.get(position-1).getBlockCode();
                    block_name = BlockList.get(position-1).getBlockName();
                    loadPanchayatSpinnerData(block_id);

                } else {
                    block_id = "";
                    block_name = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                block_id = "";
                block_name = "";
            }

        });
        spn_dist_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Dist_id = DistrictList.get(position-1).get_DistCode();
                    Dist_name = DistrictList.get(position-1).get_DistName();
                    loadBlockSpinnerData(Dist_id);


                } else {
                    block_name = "";
                    block_name = "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                block_name = "";
                block_name = "";
            }

        });
        spn_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    cat_id = cateogryList.get(position).getCat_id();
                    cat_name = cateogryList.get(position).getCat_name_HinDi();
                } else {
                    cat_id = "";
                    cat_name = "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cat_id = "";
                cat_name = "";
            }

        });
        spn_type_of_worker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    eduction_id = Qualification.get(position-1).getQualificationId();
                    //cat_name = cateogryList.get(position).getCat_name_HinDi();



                } else {
                    eduction_id = "";


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                eduction_id = "";

            }

        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataTOUpload();
                Boolean value = Utiilties.isOnline(ModifyDocumentActivity.this);
                if (value.equals(true)) {
                    if(validateData()) {
                        benDetails = new BenDetails();
                        benDetails.setDistrictCode(Dist_id);
                        benDetails.setBlockCode(block_id);
                        benDetails.setPanchayatCode(panch_id);
                        benDetails.setVchMobile(Mobile_no);
                        benDetails.setVchBankAccount(Bank_Ac_no);
                        benDetails.setVchBankName(_Bank_name);
                        benDetails.setVchName(edt_reg_name.getText().toString());
                        benDetails.setVchIfsc(Ifsc_code);
                        benDetails.setIntCategory(Dist_id);
                        benDetails.setSkill_Id(skill_id);
                        benDetails.setSubSkillId(sub_Skill_id);
                        benDetails.setVchAadhaar(str_adhaar);
                        benDetails.setIntQualification(eduction_id);
                        benDetails.setIntGender(Gender_Code);
                        benDetails.setIntAge(edt_age.getText().toString());
                        benDetails.setVchGuardian_name(edt_gaurdian.getText().toString());
                        benDetails.setVchGuardian_number(edt_gaurdian_no.getText().toString());
                        benDetails.setVchAddress(edt_address.getText().toString());
                        benDetails.setVchWorkAddress(edt_prev_address.getText().toString());
                        benDetails.setNameAsPerAdhaar(edt_aadharn_name.getText().toString());

                        benDetails.setIntExpYears(edt_exp_year.getText().toString());
                        benDetails.setIntExpMonths(edt_exp_month.getText().toString());


                        new UploadPendingTask(benDetails).execute();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Please Turn On Internet Coneection First",Toast.LENGTH_LONG).show();
                }
            }
        });

        valAdhaar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_aadharn_name.getText().length()!=0) {
                    if(edt_aadharno.getText().length()!=0) {
                        new ValidateAdhhar(edt_aadharno.getText().toString(),edt_aadharn_name.getText().toString()).execute();
                    }else {
                        Toast.makeText(getApplicationContext(),"आधार नंबर दर्ज करेंं",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"आधार के अनुसार नाम दर्ज करें",Toast.LENGTH_LONG).show();
                }
            }
        });

        spn_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("arg2",""+position);
                if (position > 0) {
                    Gender_Name = ben_type_aangan[position].toString();

                    if (Gender_Name.equals("पुरुष")) {

                        Gender_Code = "1";
                    } else if (Gender_Name.equals("महिला")) {

                        Gender_Code = "2";
                    }
                    else if (Gender_Name.equals("ट्रांसजेंडर")) {

                        Gender_Code = "3";
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });
    }

    private TextWatcher inputTextWatcher1 = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (edt_ifsc_code.getText().length() <= 12) {
                try {
                    if (Utiilties.isIfscCodeValid(edt_ifsc_code.getText().toString())) {
                        edt_ifsc_code.setTextColor(Color.parseColor("#0B610B"));
                        validIfsc = true;
                    } else {
                        //showMessageDialogue("Invalid Aadhaar Number");
                        //Toast.makeText(getApplicationContext(),"Invalid Aadhaar Number",Toast.LENGTH_LONG).show();
                        edt_ifsc_code.setTextColor(Color.parseColor("#ff0000"));
                        validIfsc = false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                edt_ifsc_code.setTextColor(Color.parseColor("#000000"));
                validIfsc = false;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void extractFrom_Data(){

        edt_aadharn_name.setText(benDetails.getNameAsPerAdhaar());
        edt_exp_year.setText(benDetails.getIntExpMonths());
        edt_exp_month.setText(benDetails.getIntExpYears());
        edt_aadharno.setText(benDetails.getVchAadhaar());


        edt_reg_name.setText(benDetails.getVchName());
        //   spn_category
        edt_ifsc_code.setText(benDetails.getVchIfsc());
        edt_ac_name.setText(benDetails.getVchBankName());
        edt_ac_no.setText(benDetails.getVchBankAccount());
        edt_mobileno.setText(benDetails.getVchMobile());

        edt_age.setText(benDetails.getIntAge());
        edt_gaurdian.setText(benDetails.getVchGuardian_name());
        edt_gaurdian_no.setText(benDetails.getVchGuardian_number());
        edt_address.setText(benDetails.getVchAddress());
        if(benDetails.getVchWorkAddress().equals("anyType{}")){
            edt_prev_address.setText("");
        }else{
            edt_prev_address.setText(benDetails.getVchWorkAddress());
        }



    }

    public void setSkillSpinner(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");
        int index = 0;
        for (SkillMaster info: skillList){
            list.add(info.getSkillNameHn().trim());
            //if(benDetails.get)
        }

        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_skill.setAdapter(adaptor);
        if(benDetails.getSkill_Id()!=null){
            String skill_hn = dataBaseHelper.getNameFor("SkilMaster", "Id", "SkillNameHn", benDetails.getSkill_Id());
            spn_skill.setSelection(((ArrayAdapter<String>) spn_skill.getAdapter()).getPosition(skill_hn.trim()));

        }

    }

    public void setSubSkillSpinner(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");

        for (SubSkillMaster info: subSkillList){

            list.add(info.getSkillNameHn().trim());
        }

        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_sub_skill.setAdapter(adaptor);
        if(benDetails.getSubSkillName()!=null){
            String sub_skill_hn = dataBaseHelper.getNameFor("SubSkillMaster", "SubskillId", "Sub_SkillNameHn", benDetails.getSubSkillId());
            spn_sub_skill.setSelection(((ArrayAdapter<String>) spn_sub_skill.getAdapter()).getPosition(sub_skill_hn.trim()));

        }
    }

    public void loadCategorySpinnerData(){
        cateogryList = dataBaseHelper.getCategoryMasterList();
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");
        int index = 0;
        for (CategoryMaster info: cateogryList){
            list.add(info.getCat_name_HinDi());
            //if(benDetails.get)
        }
        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_category.setAdapter(adaptor);
    }
    public void loadQualification(){
        Qualification = dataBaseHelper.Qualification();
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");
        int index = 0;
        for (qualification info: Qualification){
            list.add(info.getQualification_name());
            //if(benDetails.get)
        }
        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_type_of_worker.setAdapter(adaptor);
        if(benDetails.getIntQualification()!=null){
            //if(!benDetails.getIntCategory().equalsIgnoreCase("NA"))
            spn_type_of_worker.setSelection(((ArrayAdapter<String>) spn_type_of_worker.getAdapter()).getPosition(benDetails.getIntQualification_name()));
        }
    }
    public void loadDistrictSpinnerData(){
        DistrictList = dataBaseHelper.getDistDetail();
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");
        int index = 0;
        for (District info: DistrictList){
            list.add(info.get_DistName());
            //if(benDetails.get)
        }
        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_dist_name.setAdapter(adaptor);

        if(benDetails.getDistrictName()!=null){
            spn_dist_name.setSelection(((ArrayAdapter<String>) spn_dist_name.getAdapter()).getPosition(benDetails.getDistrictName()));

        }
        spn_dist_name.setEnabled(false);
    }
    public void loadBlockSpinnerData(String district){
        BlockList.clear();
        BlockList = dataBaseHelper.getBlockDetail(district);
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");
        int index = 0;
        for (BlockWeb info: BlockList){
            list.add(info.getBlockName());
            //if(benDetails.get)
        }
        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_block_name.setAdapter(adaptor);
        if(benDetails.getBlockName()!=null){
            spn_block_name.setSelection(((ArrayAdapter<String>) spn_block_name.getAdapter()).getPosition(benDetails.getBlockName()));

        }

        spn_block_name.setEnabled(false);
    }
    public void loadPanchayatSpinnerData(String panchayt){
        PanchayatList = dataBaseHelper.getpanchayatDetail(panchayt);
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");
        int index = 0;
        for (panchayat info: PanchayatList){
            list.add(info.getPanchayatName());
            //if(benDetails.get)
        }
        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_panch_name.setAdapter(adaptor);
        if(benDetails.getPanchayatName()!=null){
            spn_panch_name.setSelection(((ArrayAdapter<String>) spn_panch_name.getAdapter()).getPosition(benDetails.getPanchayatName()));

        }
    }

    public void loadSkillSpinnerData(){
        skillList = dataBaseHelper.getSkillMasterList();
        if (skillList.size() > 0){
            setSkillSpinner();
        }else{
            if (Utiilties.isOnline(this) == false) {
                showAlertForInternet();
            } else {
                new SyncSkillMasterData().execute();
            }
        }
    }

    public void loadSubSkillSpinnerData(String skillid){
        subSkillList = dataBaseHelper.getSubSkillMasterList(skillid);
        if (subSkillList.size() > 0){
            setSubSkillSpinner();
        }else{
            if (Utiilties.isOnline(this) == false) {
                showAlertForInternet();
            } else {
                new SyncSubSkillMasterData().execute();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spn_skill:
                if (position > 0) {
                    skill_id = skillList.get(position -1).getId();
                    loadSubSkillSpinnerData(skill_id);
                }
                break;
            case R.id.spn_sub_skill:
                if (position > 0) {
                    sub_Skill_id = subSkillList.get(position - 1).getId();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class SyncSkillMasterData extends AsyncTask<String, Void, ArrayList<SkillMaster>> {
        private final ProgressDialog dialog = new ProgressDialog(ModifyDocumentActivity.this);
        int optionType;

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Syncing Skills Data...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<SkillMaster> doInBackground(String...arg) {
            return WebserviceHelper.getSkillMasterData();
        }

        @Override
        protected void onPostExecute(ArrayList<SkillMaster> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if(result.size() > 0){

                DataBaseHelper helper=new DataBaseHelper(getApplicationContext());
                long i= helper.setSkillMasterData(result);

                skillList = helper.getSkillMasterList();
                setSkillSpinner();


                if(i>0) {
                    Toast.makeText(getApplicationContext(), "Data Synced Successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed to save Data! Try again",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "No Data Found!!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class SyncSubSkillMasterData extends AsyncTask<String, Void, ArrayList<SubSkillMaster>> {
        private final ProgressDialog dialog = new ProgressDialog(ModifyDocumentActivity.this);


        // int optionType;

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Syncing Skills Data...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<SubSkillMaster> doInBackground(String...arg) {
            return WebserviceHelper.getSubSkillMasterData();
        }

        @Override
        protected void onPostExecute(ArrayList<SubSkillMaster> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if(result.size() > 0){

                DataBaseHelper helper=new DataBaseHelper(getApplicationContext());
                long i= helper.setSubSkillMasterData(result);

                subSkillList = helper.getSubSkillMasterList(skillId);
                setSubSkillSpinner();

                if(i>0) {
                    Toast.makeText(getApplicationContext(), "Data Synced Successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Failed to save Data! Try again",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "No Data Found!!",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void showAlertForInternet(){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("Internet Connnection Error!!!");
        ab.setMessage("Please turn on your mobile data or wifi connection");
        ab.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int whichButton) {
                GlobalVariables.isOffline = false;
                Intent I = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                startActivity(I);
                finish();
            }
        });
        ab.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        finish();
                    }
                });

        ab.show();
    }

    public void setDataTOUpload(){

        Mobile_no=edt_mobileno.getText().toString();
        Bank_Ac_no=edt_ac_no.getText().toString();
        _Bank_name=edt_ac_name.getText().toString();
        Ifsc_code=edt_ifsc_code.getText().toString();
        int_no_year_exp=edt_exp_year.getText().toString();
        str_int_no_month_exp=edt_exp_month.getText().toString();
        str_adhaar=edt_aadharno.getText().toString();


    }

    private class UploadPendingTask extends AsyncTask<String, Void, String> {
        String Reg_No=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserId", "");
        String pid = "";
        BenDetails data;
        private final ProgressDialog dialog = new ProgressDialog(ModifyDocumentActivity.this);

        UploadPendingTask(BenDetails data) {
            this.data = data;

            pid = Reg_No;
            Log.e("Pid  ", pid + " ");
        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage(getApplicationContext().getResources().getString(R.string.uploading));
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {


            String date=getCurrentDate();

            String res = WebserviceHelper.UpdateUserDetail(this.data,Reg_No,date);

            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();

            }


            if (result != null) {
                String res=result;
                String substring=res.substring(0,1);
                Log.d("upldddoddata",""+substring);

                if(substring.equalsIgnoreCase("1")){
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("UserName",edt_aadharn_name.getText().toString().trim()).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Mobile",edt_mobileno.getText().toString().trim()).commit();
                    new AlertDialog.Builder(ModifyDocumentActivity.this)
                            .setTitle("सूचना")
                            .setMessage(res)
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            })
                            .show();
                    // Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();

                }else if(substring.equalsIgnoreCase("2")){
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();

                }else if(substring.equalsIgnoreCase("4")){
                    Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();

                }

            }


        }

    }
    public static String getCurrentDate()
    {
        Calendar cal=Calendar.getInstance();
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        month=month+1;

        int h=cal.get(Calendar.HOUR);
        int m=cal.get(Calendar.MINUTE);
        int s=cal.get(Calendar.SECOND);

        String date=year+"-"+month+"-"+day;
        return date;

    }

    private boolean validateData() {
        View focusView = null;
        boolean validate = true;

        try {
            if (!benDetails.getVchName().equalsIgnoreCase(edt_aadharn_name.getText().toString()) && !isVerified) {
                check = false;
                valAdhaar.setVisibility(View.VISIBLE);
            }
        }catch (NullPointerException e){e.printStackTrace();}
        try {
            if(!benDetails.getVchAadhaar().equalsIgnoreCase(edt_aadharno.getText().toString()) && !isVerified){
                check=false;
                valAdhaar.setVisibility(View.VISIBLE);
            }
        }catch (NullPointerException e){e.printStackTrace();}

//        if (Dist_id.equalsIgnoreCase("")) {
//                Toast.makeText(getApplicationContext(), "कृपया जिला नाम चुनें", Toast.LENGTH_LONG).show();
//                validate = false;
//        } if(block_id.equalsIgnoreCase("")){
//            Toast.makeText(getApplicationContext(), "कृपया ब्लॉक का नाम चुनें", Toast.LENGTH_LONG).show();
//            validate = false;
//        }
        if(panch_id.equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया पंचायत नाम का चयन करें", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if(str_adhaar.equals("") || str_adhaar.equals("NA")){
            Toast.makeText(getApplicationContext(), "कृपया मान्य आधार संख्या दर्ज करें", Toast.LENGTH_LONG).show();
            validate = false;
        }else if (!Verhoeff.validateVerhoeff(str_adhaar)) {
            Toast.makeText(getApplicationContext(), "कृपया मान्य आधार संख्या दर्ज करें", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if(Bank_Ac_no.equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया बैंक खाता नंबर दर्ज करें", Toast.LENGTH_LONG).show();
            validate = false;
        } if(Mobile_no.length()!=10){
            Toast.makeText(getApplicationContext(), "कृपया मोबाइल नंबर दर्ज करें", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if(Ifsc_code.equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया IfSc कोड दर्ज करें ", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if(edt_age.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया आयु दर्ज करें", Toast.LENGTH_LONG).show();
            validate = false;
        }

        if(edt_gaurdian.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया अभिभावक का नाम दर्ज करें", Toast.LENGTH_LONG).show();
            validate = false;
        }

        if(edt_gaurdian_no.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया अभिभावक का मोबाइल नंबर दर्ज करें", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if(edt_address.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया पता दर्ज करें", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if(edt_prev_address.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया पिछला काम का पता दर्ज करें", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if(Gender_Code.equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया लिंग का चयन करें ", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if(skill_id.equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया कुशलता चुनें ", Toast.LENGTH_LONG).show();
            validate = false;
        } if(sub_Skill_id.equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(), "कृपया कुशलता चुनें  ", Toast.LENGTH_LONG).show();
            validate = false;
        } if(!check){
            Toast.makeText(getApplicationContext(), "आधार के अनुसार नाम दर्ज करें  ", Toast.LENGTH_LONG).show();
            validate = false;
        }if(!validIfsc){
            Toast.makeText(getApplicationContext(), "कृपया सही IFSC कोड दर्ज करें  ", Toast.LENGTH_LONG).show();
            validate = false;
        }

        if(focusView != null && focusView.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        return validate;

    }
    private class ValidateAdhhar extends AsyncTask<String, Void, String> {

        String pid = "-1";

        private final ProgressDialog dialog = new ProgressDialog(
                ModifyDocumentActivity.this);
        String _adhaar="",_name="";
        ValidateAdhhar(String adhaar ,String name) {
            this._adhaar=adhaar;
            this._name=name;

            // Log.e("Pid  ", pid + " ");
        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage(getResources().getString(R.string.uploading));
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {



            String res = WebserviceHelper.VerifyAdhaar(_adhaar,_name);

            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
                // Log.d("gdfgggv",result);

                try {
                    if (result != null) {
                        if (result.equalsIgnoreCase("Authentication Success")) {

                            valAdhaar.setBackground(ContextCompat.getDrawable(ModifyDocumentActivity.this, R.drawable.buttonshape));
                            check=true;
                            isVerified = true;
                            //showSuccessMessageDialogue(result.toString());

                        } else {
                            Toast.makeText(getApplicationContext(), "आधार और नाम सही नहीं, कृपया सही नाम और आधार संख्या डालें", Toast.LENGTH_LONG).show();
                            //showMessageDialogue();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "इंटरनेट की स्पीड स्लो है | कृपया कुछ समय बाद प्रयास करे", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
