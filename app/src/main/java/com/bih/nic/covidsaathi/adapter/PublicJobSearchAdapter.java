package com.bih.nic.covidsaathi.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bih.nic.covidsaathi.Model.JobListEntity;
import com.bih.nic.covidsaathi.R;

import java.util.ArrayList;

public class PublicJobSearchAdapter extends RecyclerView.Adapter<PublicJobSearchAdapter.ViewHolder> {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<JobListEntity> ThrList=new ArrayList<>();
    String panchayatCode,panchayatName="";

    public PublicJobSearchAdapter(Activity listViewshowedit, ArrayList<JobListEntity> rlist) {
        this.activity=listViewshowedit;
        this.ThrList=rlist;
        mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adaptor_public_job_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final JobListEntity info = ThrList.get(position);

        holder.tv_slno.setText(String.valueOf(position+1));
//        holder.tv_work_site.setText(info.getWorkSite());
//        holder.tv_skill_cat.setText(info.getSkillCategory());
//        holder.tv_skill_name.setText(info.getSkillName());
//        holder.tv_person_no.setText(info.getNumberOfPerson());
//        holder.tv_gendar.setText(info.getGendar());
//        holder.tv_start_date.setText(info.getStartDate());
//        holder.tv_exp.setText(info.getExperience());
//        holder.tv_exp_max.setText(info.getExperienceMax());
//        holder.tv_salary.setText(info.getSalary());
//        holder.tv_salary_max.setText(info.getSalaryMax());
//        holder.tv_block.setText(info.getBlock());
//        holder.tv_supervisor_name.setText(info.getContactName());
//        holder.tv_super_no.setText(info.getContactNumber());
//
//        if(info.getContactNumber() != null && info.getContactNumber() != "NA"){
//            holder.iv_call.setVisibility(View.VISIBLE);
//        }else{
//            holder.iv_call.setVisibility(View.GONE);
//        }
//
//        holder.iv_call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", info.getContactNumber(), null));
//                activity.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return ThrList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_slno,tv_work_site,tv_skill_cat,tv_skill_name,tv_person_no,tv_gendar,tv_start_date,tv_exp,tv_exp_max,tv_salary,tv_salary_max,tv_block,tv_supervisor_name,tv_super_no;
        ImageView iv_call;
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
            iv_call=itemView.findViewById(R.id.iv_call);
        }

        @Override
        public void onClick(View v) {

        }

    }


    public String getGenderHindi(String gender){

        return gender;
    }
}
