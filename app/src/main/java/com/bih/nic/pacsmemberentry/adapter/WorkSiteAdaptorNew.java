package com.bih.nic.pacsmemberentry.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.Model.ConsolidatedReportModel;
import com.bih.nic.pacsmemberentry.Model.DefaultResponse;
import com.bih.nic.pacsmemberentry.Model.JobListEntity;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.WebserviceHelper;

import java.util.ArrayList;

public class WorkSiteAdaptorNew extends RecyclerView.Adapter<WorkSiteAdaptorNew.ViewHolder> {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<ConsolidatedReportModel> ThrList = new ArrayList<>();
    String panchayatCode, panchayatName = "";
    Boolean isShowDetail = false;
    String regNo;
    String type = "";

    public WorkSiteAdaptorNew(Activity listViewshowedit, ArrayList<ConsolidatedReportModel> rlist, String regNo, String type) {
        this.activity = listViewshowedit;
        this.ThrList = rlist;
        mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.regNo = regNo;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adaptor_worksitereport_new, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ConsolidatedReportModel info = ThrList.get(position);

        holder.tv_slno.setText(String.valueOf(position + 1));

            holder.txt_block.setText(ThrList.get(position).getBlockName());
            holder.tv_text1.setText(ThrList.get(position).getOrgInfo_Name());
            holder.tv_text2.setText(ThrList.get(position).getOrgInfo_Type());
            holder.tv_text3.setText(ThrList.get(position).getOrgInfo_District());
            holder.tv_text4.setText(ThrList.get(position).getWorkSite_Name());
            holder.tv_text5.setText(ThrList.get(position).getWorkSite_DIstrict());
            holder.tv_text6.setText(ThrList.get(position).getWorkSite_Place());
            holder.tv_text7.setText(ThrList.get(position).getWorkSite_ContactPerson());
            holder.tv_text8.setText(ThrList.get(position).getWorkSite_Mobile());
            holder.tv_text9.setText(ThrList.get(position).getWorkSite_Vacancy());
            holder.tv_text10.setText(ThrList.get(position).getWorkSite_Skill());
            holder.tv_text11.setText(ThrList.get(position).getWorkSite_Status());



    }

    @Override
    public int getItemCount() {
        return ThrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_slno, txt_block,tv_text1, tv_text2, tv_text3, tv_text4, tv_text5,tv_text6,tv_text7,tv_text8,tv_text9,tv_text10,tv_text11;

        ViewHolder(View itemView) {
            super(itemView);
            tv_slno = itemView.findViewById(R.id.tv_slno);
            txt_block = itemView.findViewById(R.id.txt_block);
            tv_text1 = itemView.findViewById(R.id.txt_text1);
            tv_text2 = itemView.findViewById(R.id.txt_text2);
            tv_text3 = itemView.findViewById(R.id.txt_text3);
            tv_text4 = itemView.findViewById(R.id.txt_text4);
            tv_text5 = itemView.findViewById(R.id.txt_text5);
            tv_text6 = itemView.findViewById(R.id.txt_text6);
            tv_text7 = itemView.findViewById(R.id.txt_text7);
            tv_text8 = itemView.findViewById(R.id.txt_text8);
            tv_text9 = itemView.findViewById(R.id.txt_text9);
            tv_text10 = itemView.findViewById(R.id.txt_text10);
            tv_text11 = itemView.findViewById(R.id.txt_text11);


        }

        @Override
        public void onClick(View v) {

        }

    }


    public String getGenderHindi(String gender) {
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


            } else {
                Toast.makeText(activity, "Result:null ..Uploading failed...Please Try Later", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
