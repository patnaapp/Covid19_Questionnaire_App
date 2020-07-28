package com.bih.nic.pacsmemberentry.ui.supervisor;

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

import java.util.ArrayList;

public class JobSearchActivity extends Activity{

    RecyclerView listView;
    TextView tv_Norecord;
    Spinner spn_skill,spn_sub_skill;
    ImageView img_back;
    JobSearchAdapter adaptor_showedit_listDetail;

    ProgressDialog dialog;
    ArrayList<JobListEntity> data;
    BenDetails benDetails;

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
        if(Utiilties.isOnline(this)){
            new SyncJobSearchData().execute();
        }else{
            showAlertForInternet();
        }



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void initialise(){
        dataBaseHelper=new DataBaseHelper(this);
        tv_Norecord = findViewById(R.id.tv_Norecord);

        listView = findViewById(R.id.listviewshow);
        img_back=(ImageView) findViewById(R.id.img);

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
