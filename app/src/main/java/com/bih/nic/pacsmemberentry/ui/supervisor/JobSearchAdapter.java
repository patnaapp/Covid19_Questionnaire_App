package com.bih.nic.pacsmemberentry.ui.supervisor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.Model.DefaultResponse;
import com.bih.nic.pacsmemberentry.Model.JobListEntity;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;
import com.bih.nic.pacsmemberentry.WebserviceHelper;
import com.bih.nic.pacsmemberentry.ui.patient.CovidQuestionnaire_Activity;

import java.util.ArrayList;

public class JobSearchAdapter extends RecyclerView.Adapter<JobSearchAdapter.ViewHolder> {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<JobListEntity> ThrList=new ArrayList<>();
    String panchayatCode,panchayatName="";
    Boolean isShowDetail = false;
    String regNo;

    public JobSearchAdapter(Activity listViewshowedit, ArrayList<JobListEntity> rlist, String regNo) {
        this.activity=listViewshowedit;
        this.ThrList=rlist;
        mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.regNo = regNo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adaptor_job_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final JobListEntity info = ThrList.get(position);

        //holder.tv_slno.setText(String.valueOf(position+1));
        holder.tv_patient.setText(info.getPatientName());
        holder.tv_ptnt_father.setText(info.getFHName());
        holder.tv_address.setText(info.getAddress());
        holder.tv_mobile_no.setText(info.getPatientMobNo());
        holder.tv_today_survey.setText(info.getToday());
        holder.tv_total_survey.setText(info.getTotal());



        if(info.getPatientMobNo() != null && info.getPatientMobNo() != "NA"){
            holder.iv_call.setVisibility(View.VISIBLE);
        }else{
            holder.iv_call.setVisibility(View.GONE);
        }



        holder.iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", info.getPatientMobNo(), null));
                activity.startActivity(intent);
            }
        });

        holder.btn_diagnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utiilties.isOnline(activity)) {
                    Intent intent = new Intent(activity, CovidQuestionnaire_Activity.class);
                    //intent.putExtra()

                }
                else {
                    new android.app.AlertDialog.Builder(activity)
                            .setTitle("अलर्ट !!")
                            .setMessage("कृपया अपना इंटर्नेट कनेक्शन ऑन करें")
                            .setCancelable(false)
                            .setPositiveButton("ओके", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent I = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                                    activity.startActivity(I);
                                    dialog.cancel();
                                }
                            }).show();

                }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return ThrList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_patient,tv_ptnt_father,tv_address,tv_mobile_no,tv_today_survey,tv_total_survey;
        ImageView iv_call;
        Button btn_diagnos;

        ViewHolder(View itemView) {
            super(itemView);
            //tv_slno=itemView.findViewById(R.id.tv_slno);
            tv_patient=itemView.findViewById(R.id.tv_patient);
            tv_ptnt_father=itemView.findViewById(R.id.tv_ptnt_father);
            tv_address=itemView.findViewById(R.id.tv_address);
            tv_mobile_no=itemView.findViewById(R.id.tv_mobile_no);
            tv_today_survey=itemView.findViewById(R.id.tv_today_survey);
            tv_total_survey=itemView.findViewById(R.id.tv_total_survey);

            iv_call=itemView.findViewById(R.id.iv_call);
            btn_diagnos=itemView.findViewById(R.id.btn_diagnos);

        }

        @Override
        public void onClick(View v) {

        }

    }


    public String getGenderHindi(String gender)
    {
        return gender;
    }

}
