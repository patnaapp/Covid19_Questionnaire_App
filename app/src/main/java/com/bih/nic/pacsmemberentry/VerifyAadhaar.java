package com.bih.nic.pacsmemberentry;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;
import com.bih.nic.pacsmemberentry.Model.UserDetails;
import com.bih.nic.pacsmemberentry.Model.panchayat;
import com.bih.nic.pacsmemberentry.Model.ward_model;
import com.bih.nic.pacsmemberentry.NavigationDrawer.NavigationDrawerFragment;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class VerifyAadhaar extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);




        Utiilties.setActionBarBackground(VerifyAadhaar.this);
        Utiilties.setStatusBarColor(VerifyAadhaar.this);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Physical Verification");


        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }




    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, VerifyAadhaar.PlaceholderFragment.newInstance(position + 1))
                .commit();
    }



    @Override
    public void onBackPressed() {
        finish();
    }
    public void onSectionAttached(int number) {
        switch (number) {
            /*case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;*/
        }
    }


    public static class PlaceholderFragment extends Fragment implements RecyclerItemClickListener.OnItemClickListener {


        ArrayList<String> name = new ArrayList<>();
        TextView edt_Bene_No, edt_Bene_ac_no, edt_Bene_uid_status, edt_Bene_nameInPass;
        EditText edt_Bene_aadhaarNo, edt_Bene_name, edt_Bene_yearbirth;
        Button btn_verify;
        DataBaseHelper dataBaseHelper;
        EditText filterText, edt_Modified, edt_Bene_nameInadhaar, edt_Bene_mobNo;
        ImageView img_filter, img_search;
        BenfiList benfiList;
        TextView txt_dist, txt_block, txt_pnchayat;
        UserDetails userDetails;
        String Userid = "", userRole = "";
        String blockcode = "";
        String distcode = "";
        String panchyatcode = "";
        String blockName = "";
        String DistName = "";
        String PanchayatName = "";
        String str_aadhaarno, str_aadhaar_name, UID = "";
        LinearLayout lin_data;
        ProgressDialog pd1;
        String BenId = "";
        ImageView right;
        Button btn_upload, btn_update, capturelife;
        LinearLayout lin_modified;
        TextView txt_Modified;
        String str_mod_Aadhaar = "";
        int flag = 0;
        String SearchAdhaarName = "";
        CheckBox ch, ch1, ch2, ch3;
        Spinner spn_ward;
        ArrayList<String> WARDLISTString = new ArrayList<String>();
        ArrayList<ward_model> wardlist = new ArrayList<ward_model>();
        String Ward_Name = "";
        TextView userdetl;
        String str_spinner = "";
        ImageView sync_ward;
        ArrayList<panchayat>Panchayat=new ArrayList<>();
        Spinner spn_Bene_panch;
        String NameAsPerAdhhar = "", BenMobileNo = "", Ben_YearOfBirth = "", BenWard = "", BenAdhaar = "";
        String PanchayatCode="";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_verify_aadhaar, container, false);

            name.add("Verify Aadhaar Number");
            name.add("Capture Life Certificate");
            name.add("Upload Verified Data");


            dataBaseHelper = new DataBaseHelper(getActivity());
            Userid = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("UserId", "");
            userRole = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("Role", "");
            filterText = (EditText) rootView.findViewById(R.id.flterText);
            userdetl = (TextView) rootView.findViewById(R.id.userdetl);
            spn_ward = (Spinner) rootView.findViewById(R.id.edt_Bene_ward);
            img_filter = (ImageView) rootView.findViewById(R.id.img_filter);
            img_search = (ImageView) rootView.findViewById(R.id.img_search);
            edt_Bene_No = (TextView) rootView.findViewById(R.id.edt_Bene_No);
            edt_Bene_name = (EditText) rootView.findViewById(R.id.edt_Bene_name);
            edt_Bene_ac_no = (TextView) rootView.findViewById(R.id.edt_Bene_ac_no);
            edt_Bene_uid_status = (TextView) rootView.findViewById(R.id.edt_Bene_uid_status);
            sync_ward=(ImageView)rootView.findViewById(R.id.sync_ward);
            spn_Bene_panch=(Spinner)rootView.findViewById(R.id.spn_Bene_panch);

            btn_verify = (Button) rootView.findViewById(R.id.btn_verify);
            capturelife = (Button) rootView.findViewById(R.id.capturelife);


            edt_Bene_nameInPass = (TextView) rootView.findViewById(R.id.edt_Bene_nameInPass);
            lin_data = (LinearLayout) rootView.findViewById(R.id.lin_data);
            right = (ImageView) rootView.findViewById(R.id.right);
            btn_update = (Button) rootView.findViewById(R.id.btn_update);

            txt_dist = (TextView) rootView.findViewById(R.id.txt_dist);
            txt_block = (TextView) rootView.findViewById(R.id.txt_block);
            txt_pnchayat = (TextView) rootView.findViewById(R.id.txt_pnchayat);
            btn_upload = (Button) rootView.findViewById(R.id.btn_upload);
            edt_Modified = (EditText) rootView.findViewById(R.id.edt_Modified);


            lin_modified = (LinearLayout) rootView.findViewById(R.id.lin_modified);
            txt_Modified = (TextView) rootView.findViewById(R.id.txt_Modified);


            edt_Bene_nameInadhaar = (EditText) rootView.findViewById(R.id.edt_Bene_nameInadhaar);
            edt_Bene_mobNo = (EditText) rootView.findViewById(R.id.edt_Bene_mobNo);
            edt_Bene_yearbirth = (EditText) rootView.findViewById(R.id.edt_Bene_yearbirth);
            edt_Bene_aadhaarNo = (EditText) rootView.findViewById(R.id.edt_Bene_aadhaarNo);


            sync_ward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new loadBlockData(benfiList.getBeneficiary_id()).execute();
                }
            });
            spn_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position > 0) {
                        Ward_Name = wardlist.get(position).getWardName();
                        BenWard = wardlist.get(position).getWardId();


                    } else {
                        Ward_Name = "";

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Ward_Name = "";
                }

            });
            ch = (CheckBox) rootView.findViewById(R.id.checkBox1);
            ch1 = (CheckBox) rootView.findViewById(R.id.checkBox2);
            ch2 = (CheckBox) rootView.findViewById(R.id.checkBox3);
            ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                  if (ch.isChecked()) {

                                                      ch1.setChecked(false);
                                                      ch2.setChecked(false);
                                                  }
                                              }
                                          }
            );
            ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                   if (ch1.isChecked()) {
                                                       ch.setChecked(false);
                                                       ch2.setChecked(false);
                                                   }
                                               }
                                           }
            );
            ch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                   if (ch2.isChecked()) {
                                                       ch1.setChecked(false);
                                                       ch.setChecked(false);
                                                   }
                                               }
                                           }
            );

            btn_upload.setVisibility(View.GONE);
            btn_update.setVisibility(View.GONE);
            edt_Bene_name.setEnabled(false);
            userdetl.setText(userRole + " - " + Userid);


            try {
                blockcode = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("Block", "");
                distcode = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("District", "");
                panchyatcode = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("PanchayatCode", "");
                DistName = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("DistrictName", "");
                blockName = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("BlockName", "");
                PanchayatName = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("PanchayatName", "");

            } catch (NullPointerException e) {
                e.printStackTrace();
            }


            txt_dist.setText(DistName);
            txt_block.setText(blockName);
            txt_pnchayat.setText(PanchayatName);
            img_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flag = 0;

                    btn_verify.setVisibility(View.GONE);
                    btn_upload.setVisibility(View.GONE);
                    capturelife.setVisibility(View.GONE);
                    lin_modified.setVisibility(View.GONE);
                    edt_Modified.getText().clear();
                    setData();

                }
            });
            capturelife.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // String str_aadhaarno=edt_Bene_aadhaarNo.getText().toString();
                    String UID = edt_Bene_No.getText().toString();
                    launchApp("aadhar.com.demordapp", str_aadhaarno, UID);
                }
            });

            btn_verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  /*  str_aadhaar_name=edt_Bene_name.getText().toString();
                    // str_aadhaarno=edt_Bene_aadhaarNo.getText().toString();
                    str_mod_Aadhaar=edt_Modified.getText().toString();
                    if(filterText.getText().toString().equals("")) {
                        Toast.makeText(getActivity(),"Enter data to filter",Toast.LENGTH_LONG).show();
                    }else {
                        if(flag==0) {
                            SearchAdhaarName=edt_Bene_name.getText().toString();
                            new USerStatus().execute();
                        }else if(flag==1){
                            SearchAdhaarName=edt_Modified.getText().toString();
                            new USerStatus().execute();
                        }
                    }*/
                    setDataTOEnter();
                    if(NameAsPerAdhhar.equalsIgnoreCase("")){
                        Toast.makeText(getActivity(),"Please Enter Name As Per Adhaar",Toast.LENGTH_LONG).show();
                    }else if(BenMobileNo.equalsIgnoreCase("")){
                        Toast.makeText(getActivity(),"Please Enter Mobile Number",Toast.LENGTH_LONG).show();
                    }else if(Ben_YearOfBirth.equalsIgnoreCase("")){
                        Toast.makeText(getActivity(),"Please Enter Year Of Birth",Toast.LENGTH_LONG).show();
                    }else if(BenAdhaar.equalsIgnoreCase("")){
                        if(edt_Bene_aadhaarNo.getText().length()!=0){
                            if(Verhoeff.validateVerhoeff(edt_Bene_aadhaarNo.getText().toString())){
                                Toast.makeText(getActivity(), "Please Enter correct Adhaar", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Please Enter  Adhaar", Toast.LENGTH_LONG).show();
                        }
                    }else if(BenWard.equalsIgnoreCase("--choose--")){
                        Toast.makeText(getActivity(),"Please Select ward",Toast.LENGTH_LONG).show();
                    } else {
                        saveImage(benfiList.getBeneficiary_id());
                        Intent intent = new Intent(getActivity(), MultiplePhotoActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("KEY_PID", benfiList.getBeneficiary_id());
                        intent.putExtra("pupose", "new");
                        intent.putExtra("isOpen", "");
                        startActivity(intent);
                    }

                }
            });
            btn_upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<BenfiList> dataProgress = dataBaseHelper.getAllVoucher(benfiList.getBeneficiary_id());
                    if (dataProgress.size() > 0) {


                        for (BenfiList data : dataProgress) {
                            new UploadPendingTask(data).execute();

                        }
                    } else {
                        Toast.makeText(getActivity(), "No Data to upload", Toast.LENGTH_LONG).show();
                    }
                }
            });
            int z = dataBaseHelper.getBenTableSize(distcode, blockcode);
            if (z == 0) {
                new loadBenefiaryData().execute();
            }
            // setPanchayatList(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("Block", ""));

            return rootView;
        }


        @Override
        public void onItemClick(View view, int position) {

        }

        @Override
        public void onLongItemClick(View view, int position) {
            switch (position) {

                case 0:


                    Intent intent = new Intent(getActivity(), VerifyAadhaar.class);
                    intent.putExtra("SpinnerDist", str_spinner);
                    intent.putExtra("WhichActivity", "home");
                    startActivity(intent);

                    break;
                case 1:
                    Intent intent2 = new Intent(getActivity(), VerifyAadhaar1.class);
                    startActivity(intent2);
                    break;
                case 2:


                    break;
                case 3:

                    break;
            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((VerifyAadhaar) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
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

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.listview, blocklist);


            spn_Bene_panch.setAdapter(spinnerAdapter);


        }

        private class UploadPendingTask extends AsyncTask<String, Void, String> {

            String pid = "-1";
            BenfiList data;
            private final ProgressDialog dialog = new ProgressDialog(
                    getActivity());

            UploadPendingTask(BenfiList data) {
                this.data = data;
                pid = data.getId();
                Log.e("Pid  ", pid + " ");
            }

            @Override
            protected void onPreExecute() {

                this.dialog.setCanceledOnTouchOutside(false);
                this.dialog.setMessage(getResources().getString(R.string.uploading));
                this.dialog.show();
            }

            @Override
            protected String doInBackground(String... param) {

                String res = WebserviceHelper.UpdateUidStatus(this.data);

                return res;
            }

            @Override
            protected void onPostExecute(String result) {
                if (this.dialog.isShowing()) {
                    this.dialog.dismiss();

                }

                if (result != null) {
                    if (result.equalsIgnoreCase("1")) {
                        dataBaseHelper.deleteVerifiedAdaharUpload(pid, Userid);
                        Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Server error", Toast.LENGTH_LONG).show();
                }
            }
        }

        class USerStatus extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd1 = new ProgressDialog(getActivity());
                pd1.setTitle("please wait ....");
                pd1.setMessage("Verifying Your Aadhaar detail..");
                pd1.setCancelable(false);
                pd1.show();
            }

            @Override
            protected String doInBackground(String... strings) {


                String s = "";

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    //HttpPost httpPost = new HttpPost("http://erachat.condoassist2u.com/api/setOnlineOffline");
                    HttpPost httpPost = new HttpPost("http://baaf.bihar.gov.in:8080/authekycp25/api/authenticate");
                    httpPost.setHeader("Content-type", "application/json");

                    JSONObject jsonObject = new JSONObject();
                    //jsonObject.accumulate("uid", "675075550397");
                    jsonObject.accumulate("uid", str_aadhaarno);
                    jsonObject.accumulate("uidType", "A");
                    jsonObject.accumulate("consent", "Y");
                    jsonObject.accumulate("subAuaCode", "PSWDB22456");
                    jsonObject.accumulate("txn", "eLAuth" + getDataTimeStamp());
                    jsonObject.accumulate("isPI", "y");
                    jsonObject.accumulate("isBio", "n");
                    jsonObject.accumulate("isOTP", "n");
                    jsonObject.accumulate("bioType", "");
                    jsonObject.accumulate("name", SearchAdhaarName);
                    jsonObject.accumulate("rdInfo", "");
                    jsonObject.accumulate("rdData", "");
                    jsonObject.accumulate("otpValue", "");


                    StringEntity stringEntity = new StringEntity(jsonObject.toString());
                    httpPost.setEntity(stringEntity);
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    s = readResponse(httpResponse);
                    // Log.d("tag11", " " + s);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return s;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (pd1.isShowing()) {

                    pd1.dismiss();
                }
                try {
                    if (s != null) {
                        JSONObject jsonObject = new JSONObject(s);
                        String message = jsonObject.getString("status");
                        if (message.equalsIgnoreCase("ERROR")) {
                            String errmessage = jsonObject.getString("errMsg");
                            showMessageDialogue(message, errmessage);
                            //  btn_upload.setVisibility(View.VISIBLE);
                            lin_modified.setVisibility(View.VISIBLE);
                            flag = 1;

                        }
                        else
                        {
                            showSuccessMessageDialogue(message);
                            btn_upload.setVisibility(View.VISIBLE);
                            capturelife.setVisibility(View.VISIBLE);
                            BenfiList benfiList1 = new BenfiList();
                            benfiList1.setBeneficiary_id(BenId);
                            benfiList1.setBeneficiery_name(edt_Bene_name.getText().toString());
                            benfiList1.setUidStatus("Y");
                            dataBaseHelper.updateInspectionDetails(benfiList1);
                            benfiList1.setBeneficiery_name(edt_Bene_name.getText().toString());
                            benfiList1.setDistcode(distcode);
                            benfiList1.setBlockcode(blockcode);
                            benfiList1.setPanchcode(panchyatcode);
                            benfiList1.setNameInPass(benfiList.getNameInPass());
                            benfiList1.setEntryby(Userid);
                            benfiList1.setModifiedName(str_mod_Aadhaar);
                            benfiList1.setAadharNumber(edt_Bene_aadhaarNo.getText().toString());

                            long i = dataBaseHelper.insertCheckedBeneficiary(benfiList1, Userid);
                            if (i > 0)
                            {
                                Toast.makeText(getActivity(), "Successfully updated", Toast.LENGTH_LONG).show();
                            }
                            setData1();

                            ArrayList<BenfiList> dataProgress = dataBaseHelper.getAllVoucher(benfiList.getBeneficiary_id());
                            if (dataProgress.size() > 0)
                            {
                                for (BenfiList data : dataProgress)
                                {
                                    new UploadPendingTask(data).execute();

                                }
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "No Data to upload", Toast.LENGTH_LONG).show();
                            }
                        }
                    }


                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }


            }
        }

        private void setData1()
        {
            right.setVisibility(View.GONE);
            String str_filter = filterText.getText().toString();
            benfiList = dataBaseHelper.getDataFilter(str_filter);
            if (benfiList.getBeneficiery_name() != null)
            {
                BenId = benfiList.getBeneficiary_id();
                edt_Bene_No.setText(benfiList.getBeneficiary_id());
                edt_Bene_name.setText(benfiList.getBeneficiery_name());
                // edt_Bene_ac_no.setText(benfiList.getAccountNo());
                edt_Bene_ac_no.setText(Utiilties.MaskEditText(benfiList.getAccountNo()));
                edt_Bene_uid_status.setText(benfiList.getUidStatus());
                edt_Bene_aadhaarNo.setText(Utiilties.MaskEditText(benfiList.getAadharNumber()));
                str_aadhaarno = benfiList.getAadharNumber();
                edt_Bene_nameInPass.setText(benfiList.getNameInPass());
                btn_verify.setVisibility(View.VISIBLE);
                lin_data.setVisibility(View.VISIBLE);
                if (benfiList.getUidStatus().equalsIgnoreCase("Y"))
                {
                    right.setVisibility(View.VISIBLE);
                    btn_verify.setVisibility(View.GONE);
                    btn_upload.setVisibility(View.GONE);

                }
                else {
                    right.setVisibility(View.GONE);
                    btn_verify.setVisibility(View.VISIBLE);

                }
            } else {
                btn_verify.setVisibility(View.GONE);
                lin_data.setVisibility(View.GONE);
                showMessageDialogue("Beneficiary either already submitted Jivan parman or not do not exist");
            }

        }

        public void showMessageDialogue(String messageTxt)
        {
            // MainActi.this.runOnUiThread(new Runnable() {
            // @Override
            //  public void run() {
            new AlertDialog.Builder(getActivity())
                    .setCancelable(false)
                    .setTitle("Message")
                    .setMessage(messageTxt)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            filterText.getText().clear();
                        }
                    })
                    .show();
        }

        public void showMessageDialogue(String messageTxt, String essage) {
            // MainActi.this.runOnUiThread(new Runnable() {
            // @Override
            //  public void run() {
            new AlertDialog.Builder(getActivity())
                    .setCancelable(false)
                    .setTitle("Error")
                    .setMessage(essage)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
            // }
            // });
        }

        public void showSuccessMessageDialogue(String messageTxt) {
            // MainActi.this.runOnUiThread(new Runnable() {
            // @Override
            //  public void run() {
            new AlertDialog.Builder(getActivity())
                    .setCancelable(false)
                    .setTitle("Success")
                    .setMessage("Authenticated Successfully")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
            // }
            // });
        }

        private String readResponse(HttpResponse httpResponse) {
            InputStream is = null;
            String return_text = "";
            try {
                is = httpResponse.getEntity().getContent();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                String line = "";
                StringBuffer sb = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                return_text = sb.toString();
                Log.d("return_text", "" + return_text);
            }
            catch (Exception e)
            {

            }
            return return_text;


        }

        public void loadwarlist() {

            if (wardlist.size() > 0) {
                WARDLISTString.add("--Choose--");
            }

            for (int i = 0; i < wardlist.size(); i++) {
                WARDLISTString.add(wardlist.get(i).getWardName());
            }

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.listview, WARDLISTString);


            spn_ward.setAdapter(spinnerAdapter);


        }

        private void setData() {
            right.setVisibility(View.GONE);
            if (filterText.getText().length() >= 4) {
                String str_filter = filterText.getText().toString();
                benfiList = dataBaseHelper.getDataFilter(str_filter);
                if (benfiList.getBeneficiery_name() != null) {
                    BenId = benfiList.getBeneficiary_id();
                    if(Utiilties.isOnline(getActivity()))
                    {
                        new loadBlockData(BenId).execute();
                    }
                    else
                    {

                    }
                    edt_Bene_No.setText(benfiList.getBeneficiary_id());
                    edt_Bene_name.setText(benfiList.getBeneficiery_name());
                    //  edt_Bene_ac_no.setText(benfiList.getAccountNo());
                    edt_Bene_ac_no.setText(Utiilties.MaskEditText(benfiList.getAccountNo()));
                    edt_Bene_uid_status.setText(benfiList.getUidStatus());

                    edt_Bene_aadhaarNo.setText(Utiilties.MaskEditText(benfiList.getAadharNumber()));
                    str_aadhaarno = benfiList.getAadharNumber();
                    edt_Bene_nameInPass.setText(benfiList.getNameInPass());
                    btn_verify.setVisibility(View.VISIBLE);
                    lin_data.setVisibility(View.VISIBLE);
                    if (benfiList.getUidStatus().equalsIgnoreCase("Y"))
                    {
                        right.setVisibility(View.VISIBLE);
                        btn_verify.setVisibility(View.VISIBLE);
                        btn_upload.setVisibility(View.GONE);
                        capturelife.setVisibility(View.GONE);
                    }
                    else
                    {
                        right.setVisibility(View.GONE);
                        btn_verify.setVisibility(View.VISIBLE);

                    }
                }
                else
                {

                    btn_verify.setVisibility(View.GONE);
                    lin_data.setVisibility(View.GONE);
                    showMessageDialogue("Beneficiary either already submitted Jivan parman ,Physical Verification or not exist");

                }
            }
            else
            {
                Toast.makeText(getActivity(), "Please Enter Minimum 4 character to search", Toast.LENGTH_LONG).show();
            }

        }

        private void setDataTOEnter() {
            NameAsPerAdhhar = edt_Bene_nameInadhaar.getText().toString();
            BenMobileNo = edt_Bene_mobNo.getText().toString();
            Ben_YearOfBirth = edt_Bene_yearbirth.getText().toString();
            BenAdhaar = benfiList.getAadharNumber();

        }

        public String getDataTimeStamp() {
            String pidTimeStamp = null;
            try {
                DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                Calendar localCalendar = GregorianCalendar
                        .getInstance();
                localCalendar.setTime(date);
                pidTimeStamp = String.valueOf(localCalendar
                        .get(Calendar.YEAR)) + "-" +
                        (String.valueOf(localCalendar.get(Calendar.MONTH) + 1).length() < 2 ? "0" + String.valueOf(localCalendar
                                .get(Calendar.MONTH) + 1) : String.valueOf(localCalendar
                                .get(Calendar.MONTH) + 1))
                        + "-" +
                        (String.valueOf(localCalendar.get(Calendar.DATE)).length() < 2 ? "0" + String.valueOf(localCalendar.get(Calendar.DATE)) : String.valueOf(localCalendar.get(Calendar.DATE)))
                        + "T" +
                        (String.valueOf(localCalendar.get(Calendar.HOUR_OF_DAY)).length() < 2 ? "0" + String.valueOf(localCalendar.get(Calendar.HOUR_OF_DAY)) : String.valueOf(localCalendar.get(Calendar.HOUR_OF_DAY)))
                        + ":" +
                        (String.valueOf(localCalendar.get(Calendar.MINUTE)).length() < 2 ? "0" + String.valueOf(localCalendar.get(Calendar.MINUTE)) : String.valueOf(localCalendar.get(Calendar.MINUTE)))
                        + ":" +
                        (String.valueOf(localCalendar.get(Calendar.SECOND)).length() < 2 ? "0" + String.valueOf(localCalendar.get(Calendar.SECOND)) : String.valueOf(localCalendar.get(Calendar.SECOND)));

                pidTimeStamp = pidTimeStamp.replace("T", "").replace("-", "").replace(":", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return pidTimeStamp;

        }


        protected void launchApp(String packageName, String AadharNo, String Uid) {
            Intent mIntent = getActivity().getPackageManager().getLaunchIntentForPackage(
                    packageName);
            if (mIntent != null) {

                try {

                    mIntent.putExtra("number", AadharNo);
                    mIntent.putExtra("uid", Uid);
                    startActivity(mIntent);


                } catch (ActivityNotFoundException err) {
                    Toast t = Toast.makeText(getActivity(),
                            R.string.app_not_found, Toast.LENGTH_SHORT);
                    t.show();
                }
            } else {
                Toast t = Toast.makeText(getActivity(),
                        R.string.app_not_found, Toast.LENGTH_SHORT);
                t.show();

            }
        }


        private class loadBenefiaryData extends AsyncTask<String, Void, ArrayList<BenfiList>> {
            private final ProgressDialog dialog = new ProgressDialog(
                    getActivity());

            @Override
            protected void onPreExecute() {

                this.dialog.setCanceledOnTouchOutside(false);
                this.dialog.setMessage("Loading Beneficiary List...");
                this.dialog.show();
            }

            @Override
            protected ArrayList<BenfiList> doInBackground(String... param) {

                return WebserviceHelper.beneficiaryData(distcode, blockcode, "");
            }

            @Override
            protected void onPostExecute(ArrayList<BenfiList> result) {


                if (result != null) {
                    if (result.size() > 0) {
                        Log.d("datafromserver", "" + result);


                        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                        long i = dataBaseHelper.ServergetBeneficiaryForWard(result, distcode, blockcode);
                        if (i > 0) {

                            Toast.makeText(getActivity(), "डेटा को सफलतापूर्वक सिंक्रनाइज़ किया गया", Toast.LENGTH_LONG).show();
                        }
                        if (this.dialog.isShowing()) {
                            this.dialog.dismiss();

                        }


                    } else {

                    }


                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.loading_fail), Toast.LENGTH_LONG).show();
                }
            }


        }


        private void saveImage(String BenId) {

            int i = 0;
            DataBaseHelper placeData = new DataBaseHelper(
                    getActivity());
            SQLiteDatabase db = placeData.getWritableDatabase();
            ContentValues values = new ContentValues();
            String[] whereArgs;
            values.put("wardId", BenWard);
            values.put("distcode", distcode);
            values.put("blockcode", blockcode);
            values.put("blockname", PanchayatCode);
            values.put("ben_movileno", BenMobileNo);
            values.put("ben_name_asper_adhaar", NameAsPerAdhhar);
            values.put("Ben_year_birth", Ben_YearOfBirth);
            values.put("BenAadharNo", BenAdhaar);
            whereArgs = new String[]{BenId};
            long c = db.update("BeneFiciaryList", values, "BenId=?", whereArgs);
            if (c > 0) {
                Log.e("IMAGEE", "Updated");
            } else {
                Log.e("IMAGEE", "not updttad");
            }
        }


        private class loadBlockData extends AsyncTask<String, Void, ArrayList<ward_model>> {


            String BenId = "";

            private final ProgressDialog dialog = new ProgressDialog(
                    getActivity());

            loadBlockData(String BenId) {
                this.BenId = BenId;
            }

            @Override
            protected void onPreExecute() {

                this.dialog.setCanceledOnTouchOutside(false);
                this.dialog.setMessage("Loading ward please wait...");
                this.dialog.show();
            }

            @Override
            protected ArrayList<ward_model> doInBackground(String... param) {

                return WebserviceHelper.getBenByWard(BenId);


            }

            @Override
            protected void onPostExecute(ArrayList<ward_model> result) {
                if (this.dialog.isShowing()) {
                    this.dialog.dismiss();

                }

                if (result != null) {
                    if (result.size() > 0) {
                        wardlist.clear();
                        wardlist = result;
                        PanchayatCode=result.get(1).getBenBindPanch_code();
                        loadwarlist();
                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.loading_fail),
                                Toast.LENGTH_LONG).show();

                    }
                }

            }

        }
    }


}
