package com.bih.nic.saathi;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.saathi.DataBaseHelper.DataBaseHelper;
import com.bih.nic.saathi.Model.ImageItem;
import com.bih.nic.saathi.NavigationDrawer.NavigationDrawerFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



public class HomeActivity extends Activity  implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        Utiilties.setActionBarBackground(HomeActivity.this);
        Utiilties.setStatusBarColor(HomeActivity.this);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(R.string.app_name);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
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
        private GridView gridView;
        private GridViewAdapter gridAdapter;
        ArrayList<String> name = new ArrayList<>();
        ProgressDialog pd1;
        ArrayList<String> spinnerlist = new ArrayList<>();
        String USERID = "";
        TextView user_name, urole, tv_dist, cdate, txt_center;
        String UserName, UserRole, USERDIST;
        ;
        Spinner choosedist;
        String str_spinner = "";
        String Userid = "";
        String blockcode = "";
        String distcode = "";
        String panchyatcode = "";
        String blockName = "";
        String DistName = "";
        String PanchayatName = "";
        DataBaseHelper dataBaseHelper;
        ArrayList<ImageItem> imageItems = new ArrayList<>();
        ArrayList<BenfiList> ListOfData = new ArrayList<>();

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
            View rootView = inflater.inflate(R.layout.activity_home1, container, false);

            name.add("Verify Aadhaar Number");
            name.add("Capture Life Certificate");
            name.add("Upload Verified Data");


            try {
                USERID = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("USER_ID", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            dataBaseHelper = new DataBaseHelper(getActivity());
            user_name = (TextView) rootView.findViewById(R.id.textView);
            urole = (TextView) rootView.findViewById(R.id.urole);
            cdate = (TextView) rootView.findViewById(R.id.cdate);
            choosedist = (Spinner) rootView.findViewById(R.id.spinnerdist);
            txt_center = (TextView) rootView.findViewById(R.id.txt_center);


            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                    (getActivity(), R.layout.pinnerdesign, spinnerlist);

            choosedist.setAdapter(dataAdapter);


            try {
                String USERDIST = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("DIST_NAME", "");
                if (!USERDIST.equals("")) {
                    // choosedist.setSelection(spinnerlist.indexOf(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            pd1 = new ProgressDialog(getActivity());
            pd1.setTitle("Data is Uploading Wait");
            pd1.setCancelable(false);
            Userid = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("UserId", "");
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


            user_name.setText(Userid);
            txt_center.setText("Center :- " + "District : " + DistName.toLowerCase() + " , " + "Block : " + blockName.toLowerCase() + " , " + PanchayatName.toLowerCase());
            urole.setText("CSC");
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            String currentdate = formatter.format(date);
            cdate.setText(currentdate);


            gridView = (GridView) rootView.findViewById(R.id.gridView);
            gridAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item_layout, getData(), name);
            gridView.setAdapter(gridAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    switch (i) {

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

                            Upload();

                            break;
                        case 3:

                            break;
                    }
                }
            });
            return rootView;
        }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        public ArrayList<ImageItem> getData() {
            imageItems = new ArrayList<>();
            TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);

            for (int i = 0; i < imgs.length(); i++) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
                imageItems.add(new ImageItem(bitmap, "Image#" + i));
            }
            return imageItems;
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

                    Upload();

                    break;
                case 3:

                    break;
            }


        }


        private void Upload() {
            AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
            ab.setTitle(getResources().getString(R.string.upload_text));
            ab.setMessage(getResources().getString(R.string.do_you_want_to_upload));
            ab.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int whichButton) {

                    dialog.dismiss();


                }
            });

            ab.setNegativeButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.dismiss();
                    ArrayList<BenfiList> ListOfData = dataBaseHelper.getLifeCertificate(USERID);
                    if (ListOfData.size() > 0) {


                        for (BenfiList data : ListOfData) {

                            new UpdateUidStatus(data).execute();

                        }
                    }

                }
            });

            ab.create().getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
            ab.show();
        }

        // Upload Dialog

        public class UpdateUidStatus extends AsyncTask<String, Void, String> {


            BenfiList benfiList;

            public UpdateUidStatus(BenfiList data) {
                this.benfiList = data;
            }


            @Override
            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(String... param) {


                return WebserviceHelper.UpdateAliveCertificate1(benfiList);
            }

            @Override
            protected void onPostExecute(String result) {


                //Log.d("chdbcujhh",""+result);
                if (result.equals("1")) {
                    Toast.makeText(getActivity(), "Life Certificate Uploaded Successfully", Toast.LENGTH_LONG).show();


                }
            }
        }
    }




}
