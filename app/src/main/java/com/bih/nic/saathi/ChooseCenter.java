package com.bih.nic.saathi;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.bih.nic.saathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.saathi.Model.BlockWeb;
import com.bih.nic.saathi.Model.District;
import com.bih.nic.saathi.Model.panchayat;

import java.util.ArrayList;

public class ChooseCenter extends Activity {
    Spinner spnBlock,spnDistrict,PanchayatSpinner;
    DataBaseHelper dataBaseHelper;
    ArrayList<BlockWeb>BlockList=new ArrayList<>();
    ArrayList<District>District=new ArrayList<>();
    ArrayList<panchayat>Panchayat=new ArrayList<>();
    String str_distcode="",str_blockcode="",str_blockName="",str_distName="",str_panchayatName="",str_panchaytId="";
    Button button_choose;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_center);

        Utiilties.setActionBarBackground(ChooseCenter.this);
        Utiilties.setStatusBarColor(ChooseCenter.this);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Choose Center");

        dataBaseHelper=new DataBaseHelper(getApplicationContext());
        spnDistrict=(Spinner)findViewById(R.id.distSpinner);
        spnBlock=(Spinner)findViewById(R.id.BlockSpinner);
        PanchayatSpinner=(Spinner)findViewById(R.id.PanchayatSpinner);
        button_choose=(Button)findViewById(R.id.button_choose);
        District= dataBaseHelper.getDistDetail();
        spnDistrict.setEnabled(false);
        spnBlock.setEnabled(false);
        if(District.size()==0)
        {
            new downloadDistrictData().execute("");
            setDistList();
        }



        spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i>0)
                {
                    str_distcode = District.get(i - 1).get_DistCode();
                    str_distName = District.get(i - 1).get_DistName();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        if(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Role", "").equalsIgnoreCase("CSC"))
        {
            BlockList = dataBaseHelper.getBlockDetail(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("District", ""));
            if (BlockList.size() == 0) {
                new loadBlockData(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("District", "")).execute();
            } else {
                setBlockList(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("District", ""));
            }
        }else {
                   /* BlockList = dataBaseHelper.getBlockDetail(str_distcode);
                    if (BlockList.size() == 0) {
                        new loadBlockData(str_distcode).execute();
                    } else {
                        setBlockList(str_distcode);
                    }*/
        }
        spnBlock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0) {
                    str_blockcode = BlockList.get(i-1).getBlockCode();
                    str_blockName=BlockList.get(i-1).getBlockName();
                    if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Role", "").equalsIgnoreCase("CSC")) {
                        Panchayat = dataBaseHelper.getpanchayatDetail(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Block", ""));
                        if (Panchayat.size() == 0) {
                            new loadPanchayatData(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Block", "")).execute();
                        } else {
                            setPanchayatList(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Block", ""));
                        }
                    } else {
                        Panchayat = dataBaseHelper.getpanchayatDetail(str_blockcode);
                        if (Panchayat.size() == 0) {
                            new loadPanchayatData(str_blockcode).execute();
                        } else {
                            setPanchayatList(str_blockcode);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        PanchayatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0) {
                    str_panchaytId = Panchayat.get(i-1).getPanchayatId();
                    str_panchayatName = Panchayat.get(i-1).getPanchayatName();
                }else {
                    str_panchaytId="";
                    str_panchayatName="";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        button_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int z=dataBaseHelper.getBenTableSize(str_distcode,str_panchaytId);
                if(z==0) {
                    new loadBenefiaryData().execute();
                }else {
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PanchayatCode", str_panchaytId).commit();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PanchayatName", str_panchayatName).commit();
                    Intent i = new Intent(ChooseCenter.this, VerifyAadhaar.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                    finish();
                }
            }
        });

    }
    private void setBlockList(String distcode){
        BlockList= dataBaseHelper.getBlockDetail(distcode);
        ArrayList<String>blocklist=new ArrayList<>();
        if(BlockList.size()>0){
            blocklist.add("--Choose--");
        }

        for (int i=0;i<BlockList.size();i++){
            blocklist.add(BlockList.get(i).getBlockName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.listview, blocklist);


        spnBlock.setAdapter(spinnerAdapter);

        if (!PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("BlockName", "").equals("")) {
            spnBlock.setSelection(((ArrayAdapter<String>) spnBlock.getAdapter()).getPosition(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("BlockName", "")));

        }



    }
    private void setPanchayatList(String blockcode){
        Panchayat= dataBaseHelper.getpanchayatDetail(blockcode);
        ArrayList<String>blocklist=new ArrayList<>();

        if(Panchayat.size()>0){
            blocklist.add("--Choose--");
        }

        for (int i=0;i<Panchayat.size();i++){
            blocklist.add(Panchayat.get(i).getPanchayatName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.listview, blocklist);


        PanchayatSpinner.setAdapter(spinnerAdapter);

        if (!PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("PanchayatName", "").equals("")) {
            PanchayatSpinner.setSelection(((ArrayAdapter<String>) PanchayatSpinner.getAdapter()).getPosition(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("PanchayatName", "")));

        }
    }
    private void setDistList(){
        District= dataBaseHelper.getDistDetail();

        ArrayList<String>distlist=new ArrayList<>();
        if(District.size()>0){
            distlist.add("--Choose--");
        }
        for (int i=0;i<District.size();i++){
            distlist.add(District.get(i).get_DistName());
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.listview, distlist);
        spnDistrict.setAdapter(spinnerAdapter);
        /* if(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Role", "").equals("dswo")){*/
        spnDistrict.setSelection(((ArrayAdapter<String>) spnDistrict.getAdapter()).getPosition(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("DistrictName", "")));

        // }
        // spnDistrict.setSelection(((ArrayAdapter<String>) spnDistrict.getAdapter()).getPosition(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("DistrictName", "")));
    }


    private class loadBlockData extends AsyncTask<String, Void, ArrayList<BlockWeb>>
    {

        String distCode = "";
        ArrayList<BlockWeb> blocklist = new ArrayList<BlockWeb>();
        private final ProgressDialog dialog = new ProgressDialog(
                ChooseCenter.this);

        loadBlockData(String distCode) {
            this.distCode = distCode;
        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<BlockWeb> doInBackground(String... param) {

            this.blocklist = WebserviceHelper.getBlockData(distCode);

            return this.blocklist;
        }

        @Override
        protected void onPostExecute(ArrayList<BlockWeb> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();

            }

            if (result != null)
            {
                if (result.size() > 0)
                {

                    DataBaseHelper placeData = new DataBaseHelper(ChooseCenter.this);
                    long i = placeData.setBlockDataLocal(result, this.distCode);
                    if (i > 0) setBlockList(distCode);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.loading_fail),Toast.LENGTH_LONG).show();

                }
            }

        }

    }
    private class downloadDistrictData extends AsyncTask<String, Void,ArrayList<District>>
    {

        @Override
        protected void onPreExecute()
        {


        }

        @Override
        protected ArrayList<District> doInBackground(String...arg)
        {
            return WebserviceHelper.getDistrictData();
        }

        @Override
        protected void onPostExecute(ArrayList<District> result)
        {
            Log.d("hshbccjb",""+result);

            DataBaseHelper helper=new DataBaseHelper(getApplicationContext());

            long i= helper.setDistrictDataLocalUserWise(result);
            Log.d("hcbbc",""+i);

            if(i>0)
            {
                setDistList();
            }
            else
            {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.loading_fail),Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class loadPanchayatData extends AsyncTask<String, Void, ArrayList<panchayat>>
    {
        String blockcode = "";
        ArrayList<panchayat> blocklist = new ArrayList<>();
        private final ProgressDialog dialog = new ProgressDialog(
                ChooseCenter.this);

        loadPanchayatData(String distCode) {
            this.blockcode = distCode;
        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<panchayat> doInBackground(String... param)
        {

            this.blocklist = WebserviceHelper.getPanchayatData(blockcode);

            return this.blocklist;
        }

        @Override
        protected void onPostExecute(ArrayList<panchayat> result)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();

            }

            if (result != null)
            {
                if (result.size() > 0)
                {
                    DataBaseHelper placeData = new DataBaseHelper(ChooseCenter.this);
                    long i = placeData.setPanchayatLocal(result, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("District", ""),blockcode);
                    if (i > 0) setPanchayatList(blockcode);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.loading_fail),Toast.LENGTH_LONG).show();

                }
            }

        }

    }

    private class loadBenefiaryData extends AsyncTask<String, Void, ArrayList<BenfiList>>
    {
        private final ProgressDialog dialog = new ProgressDialog(
                ChooseCenter.this);

        @Override
        protected void onPreExecute()
        {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Beneficiary List...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<BenfiList> doInBackground(String... param)
        {
            return WebserviceHelper.beneficiaryData(str_distcode, str_blockcode,str_panchaytId);
        }

        @Override
        protected void onPostExecute(ArrayList<BenfiList> result)
        {
            if (result != null)
            {
                if (result.size() > 0)
                {
                    Log.d("datafromserver", "" + result);

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(ChooseCenter.this);
                    long i = dataBaseHelper.ServergetBeneficiaryForWard(result, str_blockcode,str_panchaytId);
                    if (i > 0)
                    {
                        Toast.makeText(ChooseCenter.this, "डेटा को सफलतापूर्वक सिंक्रनाइज़ किया गया", Toast.LENGTH_LONG).show();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PanchayatCode", str_panchaytId).commit();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("PanchayatName", str_panchayatName).commit();
                        Intent i1 = new Intent(ChooseCenter.this, VerifyAadhaar.class);
                        // set the new task and clear flags
                        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i1);

                        finish();
                    }
                    if (this.dialog.isShowing())
                    {
                        this.dialog.dismiss();

                    }

                }
                else
                {

                }

            }
            else
            {
                Toast.makeText(ChooseCenter.this, getResources().getString(R.string.loading_fail), Toast.LENGTH_LONG).show();
            }
        }


    }
}
