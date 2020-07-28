package com.bih.nic.covidsaathi.NavigationDrawer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bih.nic.covidsaathi.BenfiList;
import com.bih.nic.covidsaathi.DataBaseHelper.DataBaseHelper;

import com.bih.nic.covidsaathi.ui.Login;
import com.bih.nic.covidsaathi.R;
import com.bih.nic.covidsaathi.Utiilties;
import com.bih.nic.covidsaathi.WebserviceHelper;

import java.util.ArrayList;

/**
 * Created by nisci on 05-Sep-2017.
 */

public class DrawerItemCustemiser extends ArrayAdapter<String> {

    Context context;
    int layoutResourceId;
    String fileName;

    // For Image loading via cache memory
    //public ImageLoader imageLoader;


    ArrayList<String> data = new ArrayList<String>();

    public DrawerItemCustemiser(Context context, int layoutResourceId,
                                ArrayList<String> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyViewHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            if (position==0) {
                row = inflater.inflate(R.layout.nav_header_main, parent, false);
                holder = new MyViewHolder();

                holder.text_user_name = (TextView) row.findViewById(R.id.text_user_name);

            }else{
                row = inflater.inflate(R.layout.nav_header_main2, parent, false);
                holder = new MyViewHolder();
                holder.text_nav_item = (TextView) row.findViewById(R.id.text_nav_item);
                holder.drawer_item_icon = (ImageView) row.findViewById(R.id.drawer_item_icon);
                switch (position){
                    case 0:
                        break;
                    case 1:
                        holder.drawer_item_icon.setImageResource(R.drawable.ic_action_sync);
                        break;
                   /* case 2:
                        holder.drawer_item_icon.setImageResource(R.drawable.ic_action_profile);
                        break;
                    case 3:
                        holder.drawer_item_icon.setImageResource(R.drawable.ic_action_about_us);
                        break;*/
                    case 2:
                        holder.drawer_item_icon.setImageResource(R.drawable.ic_action_logout);
                        break;


                }
                holder.text_nav_item .setText(data.get(position).toString());
                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         if (position==1){
                             if (Utiilties.isOnline(context)) {
                                new  loadBenefiaryData().execute();
                             }
                             else
                                 Toast.makeText(context, "No Internet Connection !", Toast.LENGTH_LONG).show();
                         }
                        else if (position==2){
                              PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserId","").commit();
                              PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("password","").commit();
                                 PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("Role","").commit();
                                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("Block","").commit();
                                 PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("District","").commit();
                                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("BlockName","").commit();
                                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("DistrictName","").commit();
                                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("PanchayatCode","").commit();
                                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("PanchayatName","").commit();
                             Intent intent=new Intent(context, Login.class);
                             context.startActivity(intent);
                             ((Activity) context).finish();
                           // logout((Activity) context);
                        }else{
                            Toast.makeText(context,"Coming Soon !", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }


            row.setTag(holder);
        } else {
            holder = (MyViewHolder) row.getTag();
        }

        holder = (MyViewHolder) row.getTag();
        return row;
    }

    static class MyViewHolder {
        TextView txtId,txtDate,txtName,text_nav_item,text_user_name;
        ImageView drawer_item_icon;
    }



    private class loadBenefiaryData extends AsyncTask<String, Void, ArrayList<BenfiList>> {

        private final ProgressDialog pd1 = new ProgressDialog(context);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(context).create();

        @Override
        protected void onPreExecute() {
            pd1.setTitle(" synchronize");
            pd1.setMessage("please wait downloading Beneficiary List ----");
            pd1.show();
        }

        @Override
        protected ArrayList<BenfiList> doInBackground(String... param) {

            return WebserviceHelper.beneficiaryData(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("District", ""), PreferenceManager.getDefaultSharedPreferences(getContext()).getString("Block", ""),"");
        }

        @Override
        protected void onPostExecute(ArrayList<BenfiList> result) {

            if (result != null) {
                if (result.size() > 0) {
                    Log.d("datafromserver", "" + result);


                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                    long i = dataBaseHelper.ServergetBeneficiaryForWard(result, PreferenceManager.getDefaultSharedPreferences(getContext()).getString("District", ""), PreferenceManager.getDefaultSharedPreferences(getContext()).getString("Block", ""));
                    if (i > 0) {

                        Toast.makeText(getContext(), "डेटा को सफलतापूर्वक सिंक्रनाइज़ किया गया", Toast.LENGTH_LONG).show();


                    } else {

                    }


                } else {
                    Toast.makeText(getContext(), getContext().getResources().getString(R.string.loading_fail), Toast.LENGTH_LONG).show();
                }
            }
            if (pd1.isShowing()) {

                pd1.dismiss();
            }
        }

    }
 }




