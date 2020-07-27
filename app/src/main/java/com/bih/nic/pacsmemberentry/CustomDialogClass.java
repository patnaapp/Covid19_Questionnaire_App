package com.bih.nic.pacsmemberentry;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;
import com.bih.nic.pacsmemberentry.Model.UserDetails;

public class CustomDialogClass extends Dialog implements android.view.View.OnClickListener {

  public Activity c;
  public Dialog d;
  public Button yes, no;
  UserDetails userDetails;
  DataBaseHelper databaseHelper;
  String user_name="";
  String user_pass="";
  String id="";
  String role="";

  public CustomDialogClass(Activity a, String username,String password) {
    super(a);
    // TODO Auto-generated constructor stub
    this.c = a;
    this.user_name=username;
    this.user_pass=password;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.custom_dialog);
    databaseHelper=new DataBaseHelper(getContext());
    userDetails = databaseHelper.getUserDetails(user_name.toLowerCase());
    yes = (Button) findViewById(R.id.btn_yes);
    no = (Button) findViewById(R.id.btn_no);
    yes.setOnClickListener(this);
    no.setOnClickListener(this);

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
    case R.id.btn_yes:

      if(userDetails!=null) {
        id = userDetails.get_UserId();
        role = userDetails.getRole();
        if (id.equals(user_name)) {

            Intent intent = new Intent(getContext(), VerifyAadhaar.class);
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("UserId", userDetails.get_UserId().toLowerCase()).commit();
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("password", userDetails.get_Passwoed().toLowerCase()).commit();
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("Role", userDetails.getRole().toLowerCase()).commit();
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("Block", userDetails.getBlockCode()).commit();
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("BlockName", userDetails.getBlockName()).commit();
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("DistrictName", userDetails.getDistName()).commit();
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("District", userDetails.getDistCode()).commit();

            c.startActivity(intent);
            c.finish();
          //}

        } else {

          Toast.makeText(getContext(), "No record found", Toast.LENGTH_LONG).show();
        }
      }else {

        Toast.makeText(getContext(),"Please Trun On Internet First Time",Toast.LENGTH_LONG).show();
      }

      break;
    case R.id.btn_no:
      dismiss();
      break;
    default:
      break;
    }
    dismiss();
  }
}