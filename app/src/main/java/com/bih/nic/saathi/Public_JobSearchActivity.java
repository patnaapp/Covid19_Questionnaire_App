package com.bih.nic.saathi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.saathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.saathi.Model.BenDetails;
import com.bih.nic.saathi.Model.District;
import com.bih.nic.saathi.Model.JobListEntity;
import com.bih.nic.saathi.Model.SkillMaster;
import com.bih.nic.saathi.Model.SubSkillMaster;
import com.bih.nic.saathi.adapter.PublicJobSearchAdapter;

import java.util.ArrayList;

public class Public_JobSearchActivity extends Activity implements AdapterView.OnItemSelectedListener {

    RecyclerView listView;
    TextView tv_Norecord;
    Spinner spn_skill,spn_sub_skill;
    ImageView img_back;
    PublicJobSearchAdapter adaptor_showedit_listDetail;

    ProgressDialog dialog;
    ArrayList<JobListEntity> data;
    BenDetails benDetails;

    ArrayList<SkillMaster> skillList, cateogryList;
    ArrayList<SubSkillMaster> subSkillList;
    ArrayList<District> DistrictList;

    String skillId,subSkillId;
    String DistId="", RegNo,skill_code="",sub_Skill_code="";

    DataBaseHelper dataBaseHelper;
    Spinner spn_public_skill,spn_subskill,spn_experience;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__public_job_search);

        getActionBar().hide();
        Utiilties.setStatusBarColor(this);

        initialise();

        RegNo = getIntent().getStringExtra("data");
        String distName = getIntent().getStringExtra("DistName");

        setDistrictSpinner(distName);
        loadSkillSpinnerData();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //new SyncJobSearchData().execute();
        //For Testing
//        JobListEntity info = new JobListEntity();
//        info.setWorkSite("Test");
//        data = new ArrayList<>();
//        data.add(info);
//        populateData();
    }

    public void initialise(){
        dataBaseHelper=new DataBaseHelper(this);

        spn_skill = findViewById(R.id.spn_skill);
        spn_sub_skill = findViewById(R.id.spn_sub_skill);
        spn_public_skill = findViewById(R.id.spn_public_skill);
        spn_subskill = findViewById(R.id.spn_subskill);
        spn_experience = findViewById(R.id.spn_experience);
        tv_Norecord = findViewById(R.id.tv_Norecord);

        listView = findViewById(R.id.listviewshow);
        img_back=(ImageView) findViewById(R.id.img);

        spn_sub_skill.setOnItemSelectedListener(this);
        spn_public_skill.setOnItemSelectedListener(this);
        spn_subskill.setOnItemSelectedListener(this);
        spn_experience.setOnItemSelectedListener(this);
        spn_skill.setOnItemSelectedListener(this);
    }

    public void populateData(){
        if(data != null && data.size()> 0){
            Log.e("data", ""+data.size());
            tv_Norecord.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            adaptor_showedit_listDetail = new PublicJobSearchAdapter(this, data);
            listView.setLayoutManager(new LinearLayoutManager(this));
            listView.setAdapter(adaptor_showedit_listDetail);

        }else{
            listView.setVisibility(View.GONE);
            tv_Norecord.setVisibility(View.VISIBLE);
        }
    }

//    public void setSkillSpinner(){
//        ArrayList<String> list = new ArrayList<String>();
//        list.add("-Select-");
//        int index = 0;
//        for (SkillMaster info: skillList){
//            list.add(info.getSkillName());
//        }
//
//        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
//        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spn_skill.setAdapter(adaptor);
//
//    }

    public void setDistrictSpinner(String DistName){
        DistrictList=dataBaseHelper.getDistDetail();
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");
        for (District info: DistrictList){
            list.add(info.get_DistName());
        }

        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_sub_skill.setAdapter(adaptor);

        try{
            if(!DistName.isEmpty()){
                spn_sub_skill.setSelection(list.indexOf(DistName));
            }
        }catch (Exception e){
            new SyncJobSearchData().execute();
        }

    }

//    public void loadSkillSpinnerData(){
//        skillList = dataBaseHelper.getSkillMasterList();
//        if (skillList.size() > 0){
//            setSkillSpinner();
//        }else{
//            if (Utiilties.isOnline(this) == false) {
//                showAlertForInternet();
//            } else {
//                new SyncSkillMasterData().execute();
//            }
//        }
//    }

//    public void loadSubSkillSpinnerData(){
//        subSkillList = dataBaseHelper.getSubSkillMasterList(skillId);
//        if (subSkillList.size() > 0){
//            setSubSkillSpinner();
//        }else{
//            if (Utiilties.isOnline(this) == false) {
//                showAlertForInternet();
//            } else {
//                new SyncSubSkillMasterData().execute();
//            }
//        }
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
//            case R.id.spn_skill:
//                if (position > 0) {
//                    skillId = skillList.get(position -1).getId();
//                    loadSubSkillSpinnerData();
//                }
//                break;
            case R.id.spn_sub_skill:
                if (position > 0) {
                    DistId = DistrictList.get(position - 1).get_DistCode();

                }
                break;
            case R.id.spn_skill:
                if (position > 0) {
                    skill_code = skillList.get(position -1).getId();
                    loadSubSkillSpinnerData(skill_code);
                }
                break;
            case R.id.spn_subskill:
                if (position > 0) {
                    sub_Skill_code = subSkillList.get(position - 1).getId();

                    new SyncJobSearchData().execute();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class SyncJobSearchData extends AsyncTask<String, Void, ArrayList<JobListEntity>> {
        private final ProgressDialog dialog = new ProgressDialog(Public_JobSearchActivity.this);
        int optionType;

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("लोड हो रहा है...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<JobListEntity> doInBackground(String...arg) {
            //return WebserviceHelper.searchJobMasterData(RegNo, DistId,skill_code,sub_Skill_code);
            return WebserviceHelper.searchJobMasterData(RegNo, DistId);
        }

        @Override
        protected void onPostExecute(ArrayList<JobListEntity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            data = result;
            populateData();

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


    public void setSkillSpinner(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");
        int index = 0;
        for (SkillMaster info: skillList){
            list.add(info.getSkillName());
            //if(benDetails.get)
        }

        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_public_skill.setAdapter(adaptor);
        if(benDetails.getSkill_Id()!=null){
            spn_public_skill.setSelection(((ArrayAdapter<String>) spn_public_skill.getAdapter()).getPosition(benDetails.getSkill_Name()));

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


    private class SyncSkillMasterData extends AsyncTask<String, Void, ArrayList<SkillMaster>> {
        private final ProgressDialog dialog = new ProgressDialog(Public_JobSearchActivity.this);
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

    public void setSubSkillSpinner(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("-Select-");
        for (SubSkillMaster info: subSkillList){
            list.add(info.getSkillName());
        }

        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_sub_skill.setAdapter(adaptor);
        if(benDetails.getSubSkillId()!=null){
            spn_sub_skill.setSelection(((ArrayAdapter<String>) spn_sub_skill.getAdapter()).getPosition(benDetails.getSubSkillName()));

        }
    }


    private class SyncSubSkillMasterData extends AsyncTask<String, Void, ArrayList<SubSkillMaster>> {
        private final ProgressDialog dialog = new ProgressDialog(Public_JobSearchActivity.this);


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



}
