package com.bih.nic.pacsmemberentry.ui.patient;

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

import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;
import com.bih.nic.pacsmemberentry.GlobalVariables;
import com.bih.nic.pacsmemberentry.Model.BenDetails;
import com.bih.nic.pacsmemberentry.Model.District;
import com.bih.nic.pacsmemberentry.Model.JobListEntity;
import com.bih.nic.pacsmemberentry.Model.SkillMaster;
import com.bih.nic.pacsmemberentry.Model.SubSkillMaster;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;
import com.bih.nic.pacsmemberentry.WebserviceHelper;
import com.bih.nic.pacsmemberentry.adapter.JobSearchAdapter;

import java.util.ArrayList;

public class JobSearchActivity extends Activity implements AdapterView.OnItemSelectedListener {

    RecyclerView listView;
    TextView tv_Norecord;
    Spinner spn_skill,spn_sub_skill;
    ImageView img_back;
    JobSearchAdapter adaptor_showedit_listDetail;

    ProgressDialog dialog;
    ArrayList<JobListEntity> data;
    BenDetails benDetails;

    ArrayList<SkillMaster> skillList, cateogryList;
    ArrayList<SubSkillMaster> subSkillList;
    ArrayList<District> DistrictList;

    String skillId,subSkillId;
    String DistId="", RegNo;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);

        getActionBar().hide();
        Utiilties.setStatusBarColor(this);

        initialise();

        RegNo = getIntent().getStringExtra("data");


        String distName = getIntent().getStringExtra("DistName");
        new SyncJobSearchData().execute();

        setDistrictSpinner(distName);

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
        tv_Norecord = findViewById(R.id.tv_Norecord);

        listView = findViewById(R.id.listviewshow);
        img_back=(ImageView) findViewById(R.id.img);

        spn_sub_skill.setOnItemSelectedListener(this);
        spn_skill.setOnItemSelectedListener(this);
    }

    public void populateData(){
        if(data != null && data.size()> 0){
            Log.e("data", ""+data.size());
            tv_Norecord.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            adaptor_showedit_listDetail = new JobSearchAdapter(this, data, RegNo);
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
                    new SyncJobSearchData().execute();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class SyncJobSearchData extends AsyncTask<String, Void, ArrayList<JobListEntity>> {
        private final ProgressDialog dialog = new ProgressDialog(JobSearchActivity.this);
        int optionType;

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("लोड हो रहा है...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<JobListEntity> doInBackground(String...arg) {
            return WebserviceHelper.searchJobMasterData(RegNo, DistId);
        }

        @Override
        protected void onPostExecute(ArrayList<JobListEntity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            data = checkForJobSelection(result);
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

    public ArrayList<JobListEntity> checkForJobSelection(ArrayList<JobListEntity> list){
        ArrayList<JobListEntity> selectedJob = new ArrayList<JobListEntity>();
        for(JobListEntity item: list){
            if(item.getIsSelected() != null && item.getIsSelected().equals("Y")){
                selectedJob.add(item);
                return selectedJob;
            }
        }

        return list;
    }
}
