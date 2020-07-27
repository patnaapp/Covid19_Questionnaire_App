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

public class WorkSiteAdaptor extends RecyclerView.Adapter<WorkSiteAdaptor.ViewHolder> {

    Activity activity;
    LayoutInflater mInflater;
    ArrayList<ConsolidatedReportModel> ThrList = new ArrayList<>();
    String panchayatCode, panchayatName = "";
    Boolean isShowDetail = false;
    String regNo;
    String type="";

    public WorkSiteAdaptor(Activity listViewshowedit, ArrayList<ConsolidatedReportModel> rlist, String regNo,String type) {
        this.activity = listViewshowedit;
        this.ThrList = rlist;
        mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.regNo = regNo;
        this.type=type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adaptor_worksite_report, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ConsolidatedReportModel info = ThrList.get(position);

        holder.tv_slno.setText(String.valueOf(position + 1));
        if(type.equalsIgnoreCase("2")){
            holder.tv_text1.setText(ThrList.get(position).getWorkSiteName());
            holder.tv_text2.setText(ThrList.get(position).getWorkSite_Location());
            holder.tv_text3.setText(ThrList.get(position).getWorkSite_ContactPer());
            holder.tv_text4.setText(ThrList.get(position).getWorkSite_ContactMobile());
            holder.tv_text5.setText(ThrList.get(position).getWorkSite_WorkStatus());
        }





    }

    @Override
    public int getItemCount() {
        return ThrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_slno, tv_text1,tv_text2,tv_text3,tv_text4,tv_text5;

        ViewHolder(View itemView) {
            super(itemView);
            tv_slno = itemView.findViewById(R.id.tv_slno);
            tv_text1 = itemView.findViewById(R.id.tv_text1);
            tv_text2 = itemView.findViewById(R.id.tv_text2);
            tv_text3 = itemView.findViewById(R.id.tv_text3);
            tv_text4 = itemView.findViewById(R.id.tv_text4);
            tv_text5 = itemView.findViewById(R.id.tv_text5);


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