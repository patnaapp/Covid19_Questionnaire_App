package com.bih.nic.pacsmemberentry.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;
import com.bih.nic.pacsmemberentry.Model.WorkRequirementsEntity;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.listener.WorkReqrmntListener;

import java.util.ArrayList;

public class WorkReqrmntEntryAdapter extends RecyclerView.Adapter<WorkReqrmntEntryAdapter.ViewHolder> {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<WorkRequirementsEntity> ThrList=new ArrayList<>();

    Boolean isShowDetail = false;
    WorkReqrmntListener listener;
    String keyid;
    DataBaseHelper dataBaseHelper;

    public WorkReqrmntEntryAdapter(Activity listViewshowedit, ArrayList<WorkRequirementsEntity> rlist, WorkReqrmntListener listener,String isEdit) {
        this.activity=listViewshowedit;
        this.ThrList=rlist;
        mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.listener = listener;
        this.keyid = isEdit;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adaptor_work_reqmntentry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final WorkRequirementsEntity info = ThrList.get(position);

        dataBaseHelper = new DataBaseHelper(activity);
        holder.tv_slno.setText(String.valueOf(position+1));
        if (keyid.equals("Yes")){
            String skill = dataBaseHelper.getNameFor("SkilMaster", "Id", "SkillNameHn", info.getSkill_categId());
            holder.tv_skill_cat.setText(skill);
            String subskill = dataBaseHelper.getNameFor("SubSkillMaster", "SubskillId", "Sub_SkillNameHn", info.getSkill_sub_categId());
            holder.tv_skill_name.setText(subskill);
        }
        else {
            holder.tv_skill_cat.setText(info.getSkill_categ());
            holder.tv_skill_name.setText(info.getSkill_sub_categ());
        }
        holder.tv_no_perosn.setText(info.getNo_of_persons());
        holder.tv_gendar.setText(info.getGender());
        holder.tv_start_date.setText(info.getStart_date());
        holder.tv_exp.setText(info.getMin_exp());
        holder.tv_exp_max.setText(info.getMax_exp());
        holder.tv_salary.setText(info.getMin_salary());
        holder.tv_salary_max.setText(info.getMax_salary());
        holder.tv_status1.setText(info.getIsActive());

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveRequirement(position);
            }
        });

        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onModifyRequirement(position);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return ThrList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_slno,tv_skill_cat,tv_skill_name,tv_no_perosn,tv_gendar,tv_start_date,tv_exp,tv_exp_max,tv_salary,tv_salary_max,tv_status1;
        ImageView iv_delete,iv_edit;

        ViewHolder(View itemView) {
            super(itemView);
            tv_slno=itemView.findViewById(R.id.tv_slno);
            tv_skill_cat=itemView.findViewById(R.id.tv_skill_cat);
            tv_skill_name=itemView.findViewById(R.id.tv_skill_name);
            tv_no_perosn=itemView.findViewById(R.id.tv_no_perosn);
            tv_gendar=itemView.findViewById(R.id.tv_gendar);
            tv_start_date=itemView.findViewById(R.id.tv_start_date);
            tv_exp=itemView.findViewById(R.id.tv_exp);
            tv_exp_max=itemView.findViewById(R.id.tv_exp_max);
            tv_salary=itemView.findViewById(R.id.tv_salary);
            tv_salary_max=itemView.findViewById(R.id.tv_salary_max);
            tv_status1=itemView.findViewById(R.id.tv_status1);
            iv_delete=itemView.findViewById(R.id.iv_delete);
            iv_edit=itemView.findViewById(R.id.iv_edit);
            if (keyid.equals("Yes")){
                iv_delete.setVisibility(View.GONE);
                iv_edit.setVisibility(View.VISIBLE);
            }
            else {
                iv_delete.setVisibility(View.VISIBLE);
                iv_edit.setVisibility(View.GONE);
            }

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
