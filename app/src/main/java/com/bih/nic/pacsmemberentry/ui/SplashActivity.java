package com.bih.nic.pacsmemberentry.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bih.nic.pacsmemberentry.CommonPref;
import com.bih.nic.pacsmemberentry.DataBaseHelper.DataBaseHelper;

import com.bih.nic.pacsmemberentry.GlobalVariables;
import com.bih.nic.pacsmemberentry.ui.patient.HqHomeActivity;
import com.bih.nic.pacsmemberentry.MarshmallowPermission;
import com.bih.nic.pacsmemberentry.Model.Versioninfo;
import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;
import com.bih.nic.pacsmemberentry.WebserviceHelper;
import com.bih.nic.pacsmemberentry.ui.supervisor.Supervisor_HomeActivity;

import java.io.IOException;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class SplashActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000;
    ProgressBar progressBar;
    DataBaseHelper databaseHelper;
    MarshmallowPermission permission;
    SQLiteDatabase db;
    private static final int PERMISSION_ALL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActionBar().hide();
        Utiilties.setStatusBarColor(SplashActivity.this);



        databaseHelper=new DataBaseHelper(getApplicationContext());
        try {
            databaseHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");
        }

        try {

            databaseHelper.openDataBase();
            //modifyTable();
            //modifyTable1();
        } catch (SQLException sqle) {

            throw sqle;
        }

       // start();

    }
    @Override
    protected void onResume()
    {
        // TODO Auto-generated method stub
        requestRequiredPermission();
        super.onResume();

//        if(Utiilties.isOnline(SplashActivity.this)){
//
//        new CheckUpdate().execute();}else {start();}
    }

    public void modifyTable()
    {
        String UserTable = "UserDetails";

        if(isColumnExists(UserTable, "UserName") == false)
        {
            AlterTable(UserTable, "UserName");
        }

        if(isColumnExists(UserTable, "PanchayatCode") == false)
        {
            AlterTable(UserTable, "PanchayatCode");
        }

        if(isColumnExists(UserTable, "PanchayatName") == false)
        {
            AlterTable(UserTable, "PanchayatName");
        }

        if(isColumnExists(UserTable, "Age") == false)
        {
            AlterTable(UserTable, "Age");
        }

        if(isColumnExists(UserTable, "Mobile") == false)
        {
            AlterTable(UserTable, "Mobile");
        }

        if(isColumnExists(UserTable, "Address") == false)
        {
            AlterTable(UserTable, "Address");
        }

        if(isColumnExists(UserTable, "Img_ben") == false)
        {
            AlterTable(UserTable, "Img_ben");
        }

        if(isColumnExists(UserTable, "Lat1") == false)
        {
            AlterTable(UserTable, "Lat1");
        }

        if(isColumnExists(UserTable, "Long1") == false)
        {
            AlterTable(UserTable, "Long1");
        }
    }

    public void modifyTable1(){
        String UserTable = "MasterDept";

        if(isColumnExists(UserTable, "Dept_Id") == false){
            AlterTable(UserTable, "Dept_Id");
        }

        if(isColumnExists(UserTable, "Dept_Name") == false){
            AlterTable(UserTable, "Dept_Name");
        }

        if(isColumnExists(UserTable, "DeptName_Hn") == false){
            AlterTable(UserTable, "DeptName_Hn");
        }

        if(isColumnExists(UserTable, "Dept_Abbrev") == false){
            AlterTable(UserTable, "Dept_Abbrev");
        }

        if(isColumnExists(UserTable, "NameSymbol") == false){
            AlterTable(UserTable, "NameSymbol");
        }

        if(isColumnExists(UserTable, "status") == false){
            AlterTable(UserTable, "status");
        }


    }


    public void AlterTable(String tableName,String columnName)
    {
        db = databaseHelper.getReadableDatabase();

        try{
            db.execSQL("ALTER TABLE "+tableName+" ADD COLUMN "+columnName+" TEXT");
            Log.e("ALTER Done",tableName +"-"+ columnName);
        }
        catch (Exception e)
        {
            Log.e("ALTER Failed",tableName +"-"+ columnName);
        }
    }

    public boolean isColumnExists (String table, String column) {
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("PRAGMA table_info("+ table +")", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                if (column.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        cursor.close();
        return false;
    }

    private class CheckUpdate extends AsyncTask<Void, Void, Versioninfo> {


        CheckUpdate() {

        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Versioninfo doInBackground(Void... Params) {

            TelephonyManager tm = null;
            String imei = null;

            permission=new MarshmallowPermission(SplashActivity.this, Manifest.permission.READ_PHONE_STATE);

            if(permission.result==-1 || permission.result==0){

            }

            String version = null;
            try {
                version = getPackageManager().getPackageInfo(getPackageName(),
                        0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Versioninfo versioninfo = WebserviceHelper.CheckVersion(imei, version);

            return versioninfo;

        }

        @Override
        protected void onPostExecute(final Versioninfo versioninfo) {

            final AlertDialog.Builder ab = new AlertDialog.Builder(
                    SplashActivity.this);
            ab.setCancelable(false);
            if (versioninfo != null && versioninfo.isValidDevice()) {

                CommonPref.setCheckUpdate(getApplicationContext(),
                        System.currentTimeMillis());

                if (versioninfo.getAdminMsg().trim().length() > 0
                        && !versioninfo.getAdminMsg().trim()
                        .equalsIgnoreCase("anyType{}")) {

                    ab.setTitle(versioninfo.getAdminTitle());
                    ab.setMessage(Html.fromHtml(versioninfo.getAdminMsg()));
                    ab.setPositiveButton(getResources().getString(R.string.ok),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    dialog.dismiss();

                                    showDailog(ab, versioninfo);

                                }
                            });
                    ab.show();
                } else {
                    showDailog(ab, versioninfo);
                }
            } else {
                if (versioninfo != null) {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.wrong_device_text),
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    start();

                }
            }

        }
    }

    private void start() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Boolean isLogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("isLogin", false);
                String userId = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserId", "");
                String userRole = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user_role", "");

                if(isLogin && !userId.equals("")){
                    if(userRole.equals("PAT")){
                        Intent i = new Intent(SplashActivity.this, HqHomeActivity.class);
                        startActivity(i);
                        finish();
                    }else if (userRole.equals("SUP")){
                        Intent i = new Intent(SplashActivity.this, Supervisor_HomeActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Intent i = new Intent(SplashActivity.this, PreLoginActivity.class);
                        startActivity(i);
                        finish();
                    }

                }else{
                    Intent i = new Intent(SplashActivity.this, PreLoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);

    }

    private void showDailog(AlertDialog.Builder ab,
                            final Versioninfo versioninfo) {

        if (versioninfo.isVerUpdated()) {

            if (versioninfo.getPriority() == 0) {

                start();
            } else if (versioninfo.getPriority() == 1) {

                ab.setTitle(versioninfo.getUpdateTile());
                ab.setMessage(versioninfo.getUpdateMsg());

                ab.setPositiveButton(getResources().getString(R.string.update),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                Intent launchIntent = getPackageManager()
                                        .getLaunchIntentForPackage("com.android.vending");
                                ComponentName comp = new ComponentName(
                                        "com.android.vending",
                                        "com.google.android.finsky.activities.LaunchUrlHandlerActivity"); // package

                                launchIntent.setComponent(comp);
                                launchIntent.setData(Uri
                                        .parse("market://details?id="
                                                + getApplicationContext()
                                                .getPackageName()));

                                try {
                                    startActivity(launchIntent);
                                    finish();
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(
                                            Intent.ACTION_VIEW, Uri
                                            .parse(versioninfo
                                                    .getAppUrl())));
                                    finish();
                                }

                                dialog.dismiss();
                            }


                        });
                ab.setNegativeButton(getResources().getString(R.string.ignore),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                dialog.dismiss();

                                start();
                            }

                        });

                ab.show();

            } else if (versioninfo.getPriority() == 2) {

                ab.setTitle(versioninfo.getUpdateTile());
                ab.setMessage(versioninfo.getUpdateMsg());
                ab.setPositiveButton(getResources().getString(R.string.update),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

								/*Intent myWebLink = new Intent(
										android.content.Intent.ACTION_VIEW);
								myWebLink.setData(Uri.parse(versioninfo
										.getAppUrl()));

								startActivity(myWebLink);

								dialog.dismiss();*/

                                Intent launchIntent = getPackageManager()
                                        .getLaunchIntentForPackage(
                                                "com.android.vending");
                                ComponentName comp = new ComponentName(
                                        "com.android.vending",
                                        "com.google.android.finsky.activities.LaunchUrlHandlerActivity"); // package
                                // name
                                // and
                                // activity
                                launchIntent.setComponent(comp);
                                launchIntent.setData(Uri
                                        .parse("market://details?id="
                                                + getApplicationContext()
                                                .getPackageName()));

                                try {
                                    startActivity(launchIntent);
                                    finish();
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(
                                            Intent.ACTION_VIEW, Uri
                                            .parse(versioninfo
                                                    .getAppUrl())));
                                    finish();
                                }

                                dialog.dismiss();
                            }
                        });
                ab.show();
            }
        } else {

            start();
        }


    }

    private void requestRequiredPermission(){
        String[] PERMISSIONS = {
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION,
                CAMERA,
                WRITE_EXTERNAL_STORAGE,
        };

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }else{
            checkOnline();
        }
    }
    public  boolean hasPermissions(Context context, String... allPermissionNeeded)
    {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && context != null && allPermissionNeeded != null)
            for (String permission : allPermissionNeeded)
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                    return false;
        return true;
    }

    protected void checkOnline() {
        // TODO Auto-generated method stub
        super.onResume();


        if (Utiilties.isOnline(SplashActivity.this) == false) {

            AlertDialog.Builder ab = new AlertDialog.Builder(SplashActivity.this);
            ab.setTitle("Alert Dialog !!!");
            ab.setMessage(Html.fromHtml("<font color=#000000>Internet Connection is not avaliable... \n Please Turn ON Network Connection \n To Turn ON Network Connection Press Yes Button Else To Exit Press Cancel Button.</font>"));
            ab.setPositiveButton("Turn On Network Connection", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,
                                    int whichButton) {
                    GlobalVariables.isOffline = false;
                    Intent I = new Intent(
                            android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(I);
                }
            });
            ab.setNegativeButton("Go Offline", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,
                                    int whichButton) {

                    start();
                }
            });


            ab.show();

        } else {

            GlobalVariables.isOffline = false;
            //new CheckUpdate().execute();
            new CheckUpdate().execute();
        }
    }
}
