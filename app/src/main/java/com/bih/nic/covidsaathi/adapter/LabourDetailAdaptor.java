package com.bih.nic.covidsaathi.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bih.nic.covidsaathi.Model.WorkerModel;
import com.bih.nic.covidsaathi.R;

import java.util.ArrayList;

public class LabourDetailAdaptor extends RecyclerView.Adapter<LabourDetailAdaptor.ViewHolder> {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<WorkerModel> ThrList = new ArrayList<>();
    String panchayatCode, panchayatName = "";
    Boolean isShowDetail = false;
    String regNo;

    public LabourDetailAdaptor(Activity listViewshowedit, ArrayList<WorkerModel> rlist, String regNo) {
        this.activity = listViewshowedit;
        this.ThrList = rlist;
        mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.regNo = regNo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adaptor_labour_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final WorkerModel info = ThrList.get(position);

        holder.ll_selection.setVisibility(View.GONE);
        holder.tv_slno.setText(String.valueOf(position + 1));
        if (info.getIntvchGender().equalsIgnoreCase("1")){
            holder.tv_gendar.setText("Male");
        }else if(info.getIntvchGender().equalsIgnoreCase("2")){
            holder.tv_gendar.setText("Female");
        }
        holder.tv_vchname.setText(info.getVchName());
        holder.tv_district.setText(info.getVchDist());
        holder.tv_block.setText(info.getVchBlock());

        holder.tv_address.setText(info.getVchAddress());
        holder.tv_perage.setText(info.getVchAge());
        holder.tv_candidate_mobile.setText(info.getVchMobile());
        holder.tv_supervisor_name.setText(info.getVchGuardian_name());
        holder.tv_super_no.setText(info.getVchGuardian_number());
        holder.tv_skillname.setText(info.getSkillNameHn());
        holder.tv_exp_max.setText(info.getIntvchExpYears());
        holder.tv_regNo.setText(info.getVchRegNum());




    }

    @Override
    public int getItemCount() {
        return ThrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_slno, tv_block, tv_supervisor_name, tv_super_no, tv_district, tv_status, tv_selection_status;
        ImageView iv_call;
        Button btn_accpt, btn_rjct;
        LinearLayout ll_btn, ll_status, ll_selection;

        TextView tv_regNo,tv_vchname,tv_gendar,tv_address,tv_perage,tv_candidate_mobile,tv_skillname,tv_exp_max;

        ViewHolder(View itemView) {
            super(itemView);

            ll_selection = itemView.findViewById(R.id.ll_selection);
            tv_slno = itemView.findViewById(R.id.tv_slno);
            tv_gendar = itemView.findViewById(R.id.tv_gendar);
            tv_regNo = itemView.findViewById(R.id.tv_regNo);
            tv_vchname = itemView.findViewById(R.id.tv_vchname);
            tv_district = itemView.findViewById(R.id.tv_district);
            tv_block = itemView.findViewById(R.id.tv_block);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_perage = itemView.findViewById(R.id.tv_perage);
            tv_candidate_mobile = itemView.findViewById(R.id.tv_candidate_mobile);
            tv_supervisor_name = itemView.findViewById(R.id.tv_supervisor_name);
            tv_super_no = itemView.findViewById(R.id.tv_super_no);
            tv_skillname = itemView.findViewById(R.id.tv_skillname);
            tv_exp_max = itemView.findViewById(R.id.tv_exp_max);
        }

        @Override
        public void onClick(View v) {

        }

    }


    public String getGenderHindi(String gender) {

        return gender;
    }




}

