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

        holder.tv_slno.setText(String.valueOf(position+1));
        holder.tv_work_site.setText(info.getWorkSite());
        holder.tv_skill_cat.setText(info.getSkillCategory());
        holder.tv_skill_name.setText(info.getSkillName());
        holder.tv_person_no.setText(info.getNumberOfPerson());
        holder.tv_gendar.setText(info.getGendar());
        holder.tv_start_date.setText(info.getStartDate());
        holder.tv_exp.setText(info.getExperience());
        holder.tv_exp_max.setText(info.getExperienceMax());
        holder.tv_salary.setText(info.getSalary());
        holder.tv_salary_max.setText(info.getSalaryMax());
        holder.tv_block.setText(info.getBlock());
        holder.tv_supervisor_name.setText(info.getContactName());
        holder.tv_super_no.setText(info.getContactNumber());
        holder.tv_district.setText(info.getDistrict());

//        holder.rl_view_detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isShowDetail = !isShowDetail;
//
//                if(isShowDetail){
//                    holder.tv_detail_btn.setText("View Less");
//                    holder.ll_detail.setVisibility(View.VISIBLE);
//                }else{
//                    holder.tv_detail_btn.setText("View More");
//                    holder.ll_detail.setVisibility(View.GONE);
//                }
//                //Toast.makeText(context, "view detail", Toast.LENGTH_SHORT).show();
//            }
//        });


        if(info.getContactNumber() != null && info.getContactNumber() != "NA"){
            holder.iv_call.setVisibility(View.VISIBLE);
        }else{
            holder.iv_call.setVisibility(View.GONE);
        }

        if(info.getIsAccepted() != null && (info.getIsAccepted().equals("Y") || info.getIsAccepted().equals("R"))){
            holder.ll_btn.setVisibility(View.GONE);
            holder.ll_status.setVisibility(View.VISIBLE);

            if(info.getIsAccepted().equals("Y")){
                holder.tv_status.setText("आपने आवेदन स्वीकृत कर लिया");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.green));
            }else if(info.getIsAccepted().equals("R")){
                holder.tv_status.setText("आपने आवेदन अस्वीकृत किया");
                holder.tv_status.setTextColor(activity.getResources().getColor(R.color.holo_red_dark));

                holder.btn_rjct.setVisibility(View.GONE);
                holder.ll_btn.setVisibility(View.VISIBLE);
            }
        }else{
            holder.ll_btn.setVisibility(View.VISIBLE);
            holder.ll_status.setVisibility(View.GONE);
        }

        if(info.getIsSelected().equals("Y")){
            holder.ll_selection.setVisibility(View.VISIBLE);
            holder.ll_btn.setVisibility(View.GONE);
            holder.ll_status.setVisibility(View.GONE);
            holder.tv_selection_status.setText("बधाई हो! आपका चयन "+ info.getSkillName() +" पद के लिए, तारीख :- "+ info.getSelectedDate() +" को कर लिया गया हैं");
        }else{
            holder.ll_selection.setVisibility(View.GONE);
        }

        holder.iv_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", info.getContactNumber(), null));
                activity.startActivity(intent);
            }
        });

        holder.btn_accpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utiilties.isOnline(activity)) {

                    new android.app.AlertDialog.Builder(activity)
                            .setTitle("स्वीकृति की पुष्टि")
                            .setMessage("क्या आप वाकई इस नौकरी को स्वीकार करना चाहते हैं?")
                            .setCancelable(false)
                            .setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    new AcceptRecordsFromPacs(info, position).execute();
                                    dialog.dismiss();
                                }
                            }).setNegativeButton("नहीं ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
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

        holder.btn_rjct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utiilties.isOnline(activity)) {

                    new android.app.AlertDialog.Builder(activity)
                            .setTitle("अस्वीकृति की पुष्टि")
                            .setMessage("क्या आप वाकई इस नौकरी को अस्वीकार करना चाहते हैं?")
                            .setCancelable(false)
                            .setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    new RejectRecordsFromPacs(info, position).execute();
                                    dialog.dismiss();
                                }
                            }).setNegativeButton("नहीं ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();


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
        TextView tv_slno,tv_work_site,tv_skill_cat,tv_skill_name,tv_person_no,tv_gendar,tv_start_date,tv_exp,tv_exp_max,tv_salary,tv_salary_max,tv_block,tv_supervisor_name,tv_super_no,tv_district,tv_status,tv_selection_status;
        ImageView iv_call;
        Button btn_accpt,btn_rjct;
        LinearLayout ll_btn,ll_status,ll_selection;
        ViewHolder(View itemView) {
            super(itemView);
            tv_slno=itemView.findViewById(R.id.tv_slno);
            tv_work_site=itemView.findViewById(R.id.tv_work_site);
            tv_skill_cat=itemView.findViewById(R.id.tv_skill_cat);
            tv_skill_name=itemView.findViewById(R.id.tv_skill_name);
            tv_person_no=itemView.findViewById(R.id.tv_person_no);
            tv_gendar=itemView.findViewById(R.id.tv_gendar);
            tv_start_date=itemView.findViewById(R.id.tv_start_date);
            tv_exp=itemView.findViewById(R.id.tv_exp);
            tv_exp_max=itemView.findViewById(R.id.tv_exp_max);
            tv_salary=itemView.findViewById(R.id.tv_salary);
            tv_salary_max=itemView.findViewById(R.id.tv_salary_max);
            tv_block=itemView.findViewById(R.id.tv_block);
            tv_supervisor_name=itemView.findViewById(R.id.tv_supervisor_name);
            tv_super_no=itemView.findViewById(R.id.tv_super_no);
            tv_district=itemView.findViewById(R.id.tv_district);
            tv_status=itemView.findViewById(R.id.tv_status);
            iv_call=itemView.findViewById(R.id.iv_call);
            tv_selection_status=itemView.findViewById(R.id.tv_selection_status);

            btn_accpt=itemView.findViewById(R.id.btn_accpt);
            btn_rjct=itemView.findViewById(R.id.btn_rjct);
            ll_btn=itemView.findViewById(R.id.ll_btn);
            ll_status=itemView.findViewById(R.id.ll_status);
            ll_selection=itemView.findViewById(R.id.ll_selection);
        }

        @Override
        public void onClick(View v) {

        }

    }


    public String getGenderHindi(String gender)
    {
        return gender;
    }

    private class AcceptRecordsFromPacs extends AsyncTask<String, Void, DefaultResponse> {
        JobListEntity data;
        String rowid;
        int position;
        private final ProgressDialog dialog = new ProgressDialog(activity);
        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(activity).create();


        AcceptRecordsFromPacs(JobListEntity data, int position) {
            this.data = data;
            this.position = position;
            //_uid = data.getId();
            //rowid = data.get_phase1_id();

        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("पुष्टि किया जा रहा हैं...");
            this.dialog.show();
        }

        @Override
        protected DefaultResponse doInBackground(String... param) {
            DefaultResponse res = WebserviceHelper.UploadAcceptedRecordsFromPacs(data, regNo);
            return res;

        }

        @Override
        protected void onPostExecute(DefaultResponse result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            Log.d("Responsevalue", "" + result);
            if (result != null) {
                if(result.getStatus()){
                    ThrList.get(position).setIsAccepted("Y");
                    notifyDataSetChanged();

                    new android.app.AlertDialog.Builder(activity)
                            .setTitle("सूचना")
                            .setMessage("नौकरी का अनुरोध अपडेट कर दिया गया है, आगे की जानकारी सिग्रह ही आपको अप्डेट की जाएगी|")
                            .setCancelable(true)
                            .setPositiveButton("ओके", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            }).show();
                    //Toast.makeText(activity, "नौकरी का अनुरोध अपडेट कर दिया गया है, आगे की जानकारी सिग्रह ही आपको अप्डेट की जाएगी|", Toast.LENGTH_SHORT).show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setIcon(R.drawable.logo);
                    builder.setTitle("Failed");
                    // Ask the final question
                    builder.setMessage(result.getMessage());
                    builder.setPositiveButton("ओके", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }

            }
            else{
                Toast.makeText(activity, "Result:null ..Uploading failed...Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }


    private class RejectRecordsFromPacs extends AsyncTask<String, Void, DefaultResponse> {
        JobListEntity data;
        String rowid;
        int position;
        private final ProgressDialog dialog = new ProgressDialog(activity);
        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(activity).create();


        RejectRecordsFromPacs(JobListEntity data, int position) {
            this.data = data;
            this.position = position;
            //_uid = data.getId();
            //rowid = data.get_phase1_id();

        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("पुष्टि किया जा रहा हैं...");
            this.dialog.show();
        }

        @Override
        protected DefaultResponse doInBackground(String... param) {

            DefaultResponse res = WebserviceHelper.UploadRejectedRecordsFromPacs(data, regNo);
            return res;

        }

        @Override
        protected void onPostExecute(DefaultResponse result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue", "" + result);
            if (result != null) {
                if(result.getStatus()){
                    ThrList.get(position).setIsAccepted("R");
                    notifyDataSetChanged();

                    new android.app.AlertDialog.Builder(activity)
                            .setTitle("सूचना")
                            .setMessage("नौकरी का अनुरोध अपडेट कर दिया गया")
                            .setCancelable(true)
                            .setPositiveButton("ओके", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            }).show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setIcon(R.drawable.logo);
                    builder.setTitle("Failed");
                    // Ask the final question
                    builder.setMessage(result.getMessage());
                    builder.setPositiveButton("ओके", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }
//                String string = result;
//                String[] parts = string.split(",");
//                String part1 = parts[0]; // 004-
//                String part2 = parts[1];
//
//                if (part1.equals("1")) {
////
////                    dataBaseupload = new DataBaseHelper(mContext);
////                    long c = dataBaseupload.deleteRecPhase1(rowid);
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                    builder.setIcon(R.drawable.logo);
//                    builder.setTitle("Record Rejected!!");
//                    // Ask the final question
//                    builder.setMessage("Paddy Record Rejected for SlNo-"+data.getId());
//
//
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
////                            DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
////                            Phase1Listt = dataBaseHelper.getAllEntryDetailPhase1(PreferenceManager.getDefaultSharedPreferences(mContext).getString("USERID", ""));
////                            dialog.dismiss();
////                            refresh(Phase1Listt);
////                            Phase1Listt = dataBaseHelper.getAllEntryDetailPhase1(PreferenceManager.getDefaultSharedPreferences(mContext).getString("USERID", ""));
//                            dialog.dismiss();
//                            activity.finish();
//
//
//                        }
//                    });
//
//                    AlertDialog dialog = builder.create();
//
//                    dialog.show();
//
//                } else if (part1.equals("0")) {
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                    builder.setIcon(R.drawable.logo);
//                    builder.setTitle("Record Not Rejected!!");
//                    // Ask the final question
//                    builder.setMessage("Paddy Record Not Rejected for SlNo-"+rowid);
//
//
//                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
////                            DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
////                            Phase1Listt = dataBaseHelper.getAllEntryDetailPhase1(PreferenceManager.getDefaultSharedPreferences(mContext).getString("USERID", ""));
////                            dialog.dismiss();
////                            refresh(Phase1Listt);
////                            Phase1Listt = dataBaseHelper.getAllEntryDetailPhase1(PreferenceManager.getDefaultSharedPreferences(mContext).getString("USERID", ""));
//                            dialog.dismiss();
//
//
//
//                        }
//                    });
//
//                    AlertDialog dialog = builder.create();
//
//                    dialog.show();
//
//                } else {
//                    Toast.makeText(activity, "Your data is not uploaded Successfully ! ", Toast.LENGTH_SHORT).show();
//                }
            } else {

                Toast.makeText(activity, "Result:null ..Uploading failed...Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
