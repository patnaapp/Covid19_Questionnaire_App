package com.bih.nic.saathi.ui.patient;

import android.app.Activity;
import android.app.ProgressDialog;
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

import com.bih.nic.saathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.saathi.Model.District;
import com.bih.nic.saathi.Model.FacilitiesEntity;
import com.bih.nic.saathi.R;
import com.bih.nic.saathi.Utiilties;
import com.bih.nic.saathi.WebserviceHelper;
import com.bih.nic.saathi.adapter.WorkApprovalAdapter;

import java.util.ArrayList;

public class View_Facilities_activity extends Activity
{
    Spinner sp_Dist,sp_level_type;
    ArrayList<District> DistrictList = new ArrayList<District>();
    String User_Id="",Dist_Code="",Dist_Name="";
    String facility_code="";
    DataBaseHelper localDBHelper;
    ArrayList<String> districtNameArray;
    ArrayAdapter<String> districtadapter;
    ArrayList<FacilitiesEntity> data;
    TextView tv_Norecord;
    RecyclerView listView;
    WorkApprovalAdapter adaptor_showedit_listDetail;
    String ben_type_centre[] = {"-select-","All","District Level","Block Level","Sub-Divison Level"};
    ArrayAdapter ben_aaray_centre;
    String level_type_name="",level_type_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__facilities_activity);
        localDBHelper=new DataBaseHelper(View_Facilities_activity.this);

        getActionBar().hide();
        Utiilties.setStatusBarColor(this);
       // sp_Dist=findViewById(R.id.sp_Dist);
        sp_level_type=findViewById(R.id.sp_level_type);
        ben_aaray_centre = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ben_type_centre);
        sp_level_type.setAdapter(ben_aaray_centre);
        tv_Norecord=findViewById(R.id.tv_Norecord);
        listView=findViewById(R.id.listviewshow);
       // loadDistrictSpinnerdata();
        facility_code = getIntent().getStringExtra("facility_code");

        ImageView img_back= findViewById(R.id.img);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sp_level_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("arg2",""+position);
                if (position > 0) {

                    level_type_name = ben_type_centre[position].toString();

                    if (level_type_name.equals("District Level")) {

                        level_type_id = "D";

                    } else if (level_type_name.equals("Block Level")) {

                        level_type_id = "B";

                    }
                    else if (level_type_name.equals("Sub-Divison Level")) {

                        level_type_id = "S";

                    }
                    else if (level_type_name.equals("All")) {

                        level_type_id = "0";

                    }
                    new GetQuarantineFacilitiesList().execute();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

//        sp_Dist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                // TODO Auto-generated method stub
//                if (position >= 0) {
//
//                    District district = DistrictList.get(position);
//                    Dist_Code = district.get_DistCode();
//                    Dist_Name = district.get_DistName();
//                    new GetQuarantineFacilitiesList().execute();
//
//                } else {
//                    Dist_Code = "";
//                    Dist_Name = "";
//
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent)
//            {
//                // TODO Auto-generated method stub
//
//            }
//        });
    }

    public void loadDistrictSpinnerdata()
    {

        DistrictList = localDBHelper.getDistrictLocal();
        districtNameArray = new ArrayList<String>();
        districtNameArray.add("-Select District-");
        int i = 1;
        for (District district : DistrictList)
        {
            districtNameArray.add(district.get_DistNameHN());
            i++;
        }
        //districtadapter = new ArrayAdapter<String>(this, R.layout.dropdownlist, districtNameArray);
        //districtadapter.setDropDownViewResource(R.layout.dropdownlist);

        districtadapter = new ArrayAdapter<String>(this,android. R.layout.simple_spinner_dropdown_item, districtNameArray);
        //districtadapter.setDropDownViewResource(R.layout.spinner_textview);
        sp_Dist.setAdapter(districtadapter);

    }

    private class GetQuarantineFacilitiesList extends AsyncTask<String, Void, ArrayList<FacilitiesEntity>>
    {
        private final ProgressDialog dialog = new ProgressDialog(View_Facilities_activity.this);
        int optionType;

        @Override
        protected void onPreExecute()
        {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("लोड हो रहा है...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<FacilitiesEntity> doInBackground(String...arg)
        {
            return WebserviceHelper.GetQuarantineFacility_List(facility_code,level_type_id,"0");
        }

        @Override
        protected void onPostExecute(ArrayList<FacilitiesEntity> result)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }

            data = result;

            populateData();

        }
    }
    public void populateData()
    {
        if(data != null && data.size()> 0)
        {
            Log.e("data", ""+data.size());
            tv_Norecord.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            adaptor_showedit_listDetail = new WorkApprovalAdapter(this, data, Dist_Code);
            listView.setLayoutManager(new LinearLayoutManager(this));
            listView.setAdapter(adaptor_showedit_listDetail);

        }
        else
        {
            listView.setVisibility(View.GONE);
            tv_Norecord.setVisibility(View.VISIBLE);
        }
    }
}
