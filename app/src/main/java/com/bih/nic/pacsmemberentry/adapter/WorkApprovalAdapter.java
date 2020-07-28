package com.bih.nic.pacsmemberentry.adapter;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;
import com.bih.nic.pacsmemberentry.Model.ApproveWorkSiteEntity;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.listener.WorkReqrmntListener;

import java.util.ArrayList;

public class WorkApprovalAdapter extends RecyclerView.Adapter<WorkApprovalAdapter.ViewHolder> {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<ApproveWorkSiteEntity> ThrList=new ArrayList<>();

    Boolean isShowDetail = false;
    WorkReqrmntListener listener;
    String keyid;
    DataBaseHelper dataBaseHelper;
    String UserRole="";

    // public WorkApprovalAdapter(Activity listViewshowedit, ArrayList<ApproveWorkSiteEntity> rlist, WorkReqrmntListener listener, String isEdit) {
    public WorkApprovalAdapter(Activity listViewshowedit, ArrayList<ApproveWorkSiteEntity> rlist, String isEdit) {
        this.activity=listViewshowedit;
        this.ThrList=rlist;
        mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.listener = listener;
        this.keyid = isEdit;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mInflater.inflate(R.layout.adaptor_work_approval, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ApproveWorkSiteEntity info = ThrList.get(position);

        dataBaseHelper = new DataBaseHelper(activity);
        UserRole= PreferenceManager.getDefaultSharedPreferences(activity).getString("UserRole", "");
        holder.tv_slno.setText(String.valueOf(position+1));

        holder.tv_block.setText(info.getBlockName());
        holder.tv_workstatus.setText(info.getBenStatus());
        holder.tv_org_name.setText(info.getComanyNameEn());
        holder.tv_work_site.setText(info.getWorkSiteName());
        holder.tv_location.setText(info.getLocation());
        holder.tv_pincode.setText(info.getPinNo());

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRemoveRequirement(position);
            }
        });

//        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (UserRole.equals("ORGADM")){
//                    Intent intent = new Intent(activity, WorkRequirementApproval_Activity.class);
//                    intent.putExtra("worksid",info.getWorksId());
//                    intent.putExtra("worksite",info.getWorkSiteName());
//                    intent.putExtra("orhname",info.getComanyNameEn());
//                    intent.putExtra("a_ID",info.getA_id());
//                    activity.startActivity(intent);
//                }
//                else if (UserRole.equals("DSTADM")){
//                    Intent intent = new Intent(activity, WorkReqApproval_Dst_Activity.class);
//                    intent.putExtra("worksid",info.getWorksId());
//                    intent.putExtra("worksite",info.getWorkSiteName());
//                    intent.putExtra("orhname",info.getComanyNameEn());
//                    intent.putExtra("a_ID",info.getA_id());
//                    activity.startActivity(intent);
//                }
//
//            }
//        });
    }

    @Override
    public int getItemCount()
    {
        return ThrList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_slno,tv_block,tv_workstatus,tv_org_name,tv_work_site,tv_location,tv_pincode;
        ImageView iv_delete,iv_edit;

        ViewHolder(View itemView) {
            super(itemView);
            tv_slno=itemView.findViewById(R.id.tv_slno);
            tv_block=itemView.findViewById(R.id.tv_block);
            tv_workstatus=itemView.findViewById(R.id.tv_workstatus);
            tv_org_name=itemView.findViewById(R.id.tv_org_name);
            tv_work_site=itemView.findViewById(R.id.tv_work_site);
            tv_location=itemView.findViewById(R.id.tv_location);
            tv_pincode=itemView.findViewById(R.id.tv_pincode);

            iv_delete=itemView.findViewById(R.id.iv_delete);
            iv_edit=itemView.findViewById(R.id.iv_edit);


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
