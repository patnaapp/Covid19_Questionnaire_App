package com.bih.nic.saathi.adapter;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bih.nic.saathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.saathi.Model.FacilitiesEntity;
import com.bih.nic.saathi.R;
import com.bih.nic.saathi.listener.WorkReqrmntListener;

import java.util.ArrayList;

public class WorkApprovalAdapter extends RecyclerView.Adapter<WorkApprovalAdapter.ViewHolder> {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<FacilitiesEntity> ThrList=new ArrayList<>();

    Boolean isShowDetail = false;
    WorkReqrmntListener listener;
    String keyid;
    DataBaseHelper dataBaseHelper;
    String UserRole="",facility_code="";

    // public WorkApprovalAdapter(Activity listViewshowedit, ArrayList<ApproveWorkSiteEntity> rlist, WorkReqrmntListener listener, String isEdit) {
    public WorkApprovalAdapter(Activity listViewshowedit, ArrayList<FacilitiesEntity> rlist, String isEdit) {
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
        final FacilitiesEntity info = ThrList.get(position);

        dataBaseHelper = new DataBaseHelper(activity);
        UserRole= PreferenceManager.getDefaultSharedPreferences(activity).getString("UserRole", "");
        facility_code = activity.getIntent().getStringExtra("facility_code");
        if (facility_code.equals("T"))
        {
            holder.ll_beds.setVisibility(View.GONE);
            holder.ll_hsptl_id.setVisibility(View.GONE);
        }
        holder.tv_slno.setText(String.valueOf(position+1));

        holder.tv_beds.setText(info.getBed());
        holder.tv_avlbl_bed.setText(info.getAvailable());
        holder.tv_hsptl_id.setText(info.getHostpitalId());
        holder.tv_type.setText(info.getType());
        holder.tv_hn_hospital.setText(info.getNameHn());
        holder.tv_hsptl_ene.setText(info.getName());
        holder.tv_lvl_type.setText(info.getLevelType());
        holder.tv_cntr_type.setText(info.getCenterType());

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
        TextView tv_slno,tv_beds,tv_avlbl_bed,tv_hsptl_id,tv_type,tv_hn_hospital,tv_hsptl_ene,tv_lvl_type,tv_cntr_type;
        ImageView iv_delete,iv_edit;
        LinearLayout ll_beds,ll_hsptl_id;

        ViewHolder(View itemView) {
            super(itemView);
            tv_slno=itemView.findViewById(R.id.tv_slno);
            tv_beds=itemView.findViewById(R.id.tv_beds);
            tv_avlbl_bed=itemView.findViewById(R.id.tv_avlbl_bed);
            tv_hsptl_id=itemView.findViewById(R.id.tv_hsptl_id);
            tv_type=itemView.findViewById(R.id.tv_type);
            tv_hn_hospital=itemView.findViewById(R.id.tv_hn_hospital);
            tv_hsptl_ene=itemView.findViewById(R.id.tv_hsptl_ene);
            tv_lvl_type=itemView.findViewById(R.id.tv_lvl_type);
            tv_cntr_type=itemView.findViewById(R.id.tv_cntr_type);

            iv_delete=itemView.findViewById(R.id.iv_delete);
            iv_edit=itemView.findViewById(R.id.iv_edit);
            ll_beds=itemView.findViewById(R.id.ll_beds);
            ll_hsptl_id=itemView.findViewById(R.id.ll_hsptl_id);


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
