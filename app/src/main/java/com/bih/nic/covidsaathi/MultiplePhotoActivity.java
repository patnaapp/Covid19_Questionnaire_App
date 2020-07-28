package com.bih.nic.covidsaathi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bih.nic.covidsaathi.DataBaseHelper.DataBaseHelper;

import java.util.Calendar;


@SuppressLint("NewApi")
public class MultiplePhotoActivity extends Activity implements OnClickListener {
    private final static int CAMERA_PIC = 99;
    ImageView img1, img2, img3, img4,img5;
    Button btnOk;

    int ThumbnailSize = 0;
    String PID = "0";
    String AREA = "";
    //ArrayList<Intent> imageData;
    Intent imageData1,imageData2,imageData3,imageData4,imageData5;
    String isOpen = "",purpose="";
    TextView text_p1;
    LinearLayout linearLayout2;
    RelativeLayout re_p2;
    BenfiList benfiList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multiple_photo);

        Utiilties.setActionBarBackground(this);
        Utiilties.setStatusBarColor(this);

        PID = getIntent().getStringExtra("KEY_PID");
        isOpen = getIntent().getStringExtra("isOpen");
        purpose = getIntent().getStringExtra("pupose");

        if (PID == null) {
            PID = "0";
        }



        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        img1 = (ImageView) findViewById(R.id.imageButton1);
        img2 = (ImageView) findViewById(R.id.imageButton2);
        img3 = (ImageView) findViewById(R.id.imageButton3);
        img4 = (ImageView) findViewById(R.id.imageButton4);
        img5 = (ImageView) findViewById(R.id.imageButton5);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        re_p2 = (RelativeLayout) findViewById(R.id.re_p2);
        text_p1 = (TextView) findViewById(R.id.text_p1);

        btnOk = (Button) findViewById(R.id.btnOk);

        btnOk.setOnClickListener(this);


        //btnOk.setEnabled(false);
        if (!purpose.equalsIgnoreCase("edit"))
        {
            btnOk.setEnabled(false);
            btnOk.setBackgroundResource(R.drawable.buttonbackshape1);
        }

        if (displaymetrics.widthPixels < displaymetrics.heightPixels) {
            ThumbnailSize = displaymetrics.widthPixels / 2;
            //img1.getLayoutParams().height = ThumbnailSize;
            //img3.getLayoutParams().height = ThumbnailSize;


        } else {

            ThumbnailSize = displaymetrics.widthPixels / 4;
            img1.getLayoutParams().height = img2.getLayoutParams().height = img3
                    .getLayoutParams().height = img4.getLayoutParams().height = img5.getLayoutParams().height = ThumbnailSize;

        }
        if (isOpen.equals("2")) {
            text_p1.setText("बंद केंद्र की फोटो");
            linearLayout2.setVisibility(View.GONE);
            re_p2.setVisibility(View.GONE);
        }

        DataBaseHelper placeData = new DataBaseHelper(this);
        SQLiteDatabase db = placeData.getReadableDatabase();
        //int HIDID = getIntent().getIntExtra("KEY_HIDID", 0);

        Cursor cursor = db
                .rawQuery(
                        "SELECT photo1,photo2,photo3,photo4 FROM BeneFiciaryList where BenId =?",
                        new String[]{String.valueOf(PID)});

        Log.e("PID  ", PID);


        if (cursor.moveToNext()) {

            if (!cursor.isNull(0)) {

                byte[] imgData = cursor.getBlob(cursor.getColumnIndex("photo1"));
                Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,
                        imgData.length);
                img1.setImageBitmap(Utiilties.GenerateThumbnail(bmp,
                        ThumbnailSize, ThumbnailSize));
                img1.setOnClickListener(this);
            } else {
                img1.setOnClickListener(this);
                img2.setEnabled(false);
                img3.setEnabled(false);
                img4.setEnabled(false);
                img5.setEnabled(false);
                btnOk.setEnabled(true);

            }

            if (!cursor.isNull(1)) {
                byte[] imgData = cursor.getBlob(cursor.getColumnIndex("photo2"));

                Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,
                        imgData.length);
                img2.setImageBitmap(Utiilties.GenerateThumbnail(bmp,
                        ThumbnailSize, ThumbnailSize));
                img2.setOnClickListener(this);

            } else {
                img2.setOnClickListener(this);
                img5.setOnClickListener(this);
                img3.setEnabled(false);
                img4.setEnabled(false);

            }
            if (!cursor.isNull(2)) {
                byte[] imgData = cursor.getBlob(cursor.getColumnIndex("photo3"));

                Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,
                        imgData.length);
                img3.setImageBitmap(Utiilties.GenerateThumbnail(bmp,
                        ThumbnailSize, ThumbnailSize));
                img3.setOnClickListener(this);
            } else {
                img3.setOnClickListener(this);
                img4.setEnabled(false);
            }
            if (!cursor.isNull(3)) {
                byte[] imgData = cursor.getBlob(cursor.getColumnIndex("photo4"));

                Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,
                        imgData.length);
                img4.setImageBitmap(Utiilties.GenerateThumbnail(bmp,
                        ThumbnailSize, ThumbnailSize));
                img4.setOnClickListener(this);
            } else {
                img4.setOnClickListener(this);

            }

        }
        cursor.close();
        db.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_multiple_photo, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnOk)) {
            if (imageData1==null&& imageData2==null&& imageData3==null&& imageData4==null&& imageData5==null&& purpose.equals("edit")){
                finish();
            }else {
                saveImage();
                DataBaseHelper placeData = new DataBaseHelper(this);
                benfiList=placeData.getBenfiListToUpload(PID);
               /* Intent iUserHome = new Intent(getApplicationContext(),
                        MultiplePhotoActivity.class);
                iUserHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(iUserHome);*/
                View checkBoxView = View.inflate(getApplicationContext(), R.layout.checkbox, null);
                final CheckBox checkBox = (CheckBox) checkBoxView.findViewById(R.id.checkbox);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        // Save to shared preferences
                    }
                });
                checkBox.setText("Check the box to confirm .");
                checkBox.setTextColor(Color.parseColor("#ff222222"));

                AlertDialog.Builder builder = new AlertDialog.Builder(MultiplePhotoActivity.this);
                builder.setTitle("Message");
                builder.setMessage(" please verify that all information shared by you is correct ")
                        .setView(checkBoxView)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(checkBox.isChecked()) {
                                    new UploadPendingTask(benfiList).execute();
                                }else {
                                    Toast.makeText(getApplicationContext(),"Please check the box first",Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

                // new UploadPendingTask(benfiList).execute();
            }
        } else {
            final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


                buildAlertMessageNoGps();
            }
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true) {

                Intent iCamera = new Intent(getApplicationContext(),
                        CameraActivity.class);
                if (view.equals(img1))
                    iCamera.putExtra("KEY_PIC", "1");
                else if (view.equals(img2))
                    iCamera.putExtra("KEY_PIC", "2");
                else if (view.equals(img3))
                    iCamera.putExtra("KEY_PIC", "3");
                else if (view.equals(img4))
                    iCamera.putExtra("KEY_PIC", "4");
                else if (view.equals(img5))
                    iCamera.putExtra("KEY_PIC", "5");
                startActivityForResult(iCamera, CAMERA_PIC);
            }
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_PIC:
                if (resultCode == RESULT_OK) {
                    byte[] imgData = data.getByteArrayExtra("CapturedImage");


                    //imageData.add(data);
                    if (isOpen.equals("2")) btnOk.setEnabled(true);

                    switch (data.getIntExtra("KEY_PIC", 0)) {
                        case 1:
                            imageData1=data;
                            Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,
                                    imgData.length);
                            img1.setScaleType(ScaleType.FIT_XY);
                            img1.setImageBitmap(Utiilties.GenerateThumbnail(bmp,
                                    500, 500));
                            img2.setEnabled(true);
                            break;

                        case 2:
                            imageData2=data;
                            img2.setScaleType(ScaleType.FIT_XY);
                            img2.setImageBitmap(Utiilties.GenerateThumbnail(
                                    BitmapFactory.decodeByteArray(imgData, 0,
                                            imgData.length), 500, 500));
                            //img2.setOnClickListener(null);
                            img3.setEnabled(true);
                            //btnOk.setEnabled(false);
                           // btnOk.setBackgroundResource(R.drawable.buttonbackshape);
                            break;

                        case 3:
                            imageData3=data;
                            img3.setScaleType(ScaleType.FIT_XY);
                            img3.setImageBitmap(Utiilties.GenerateThumbnail(
                                    BitmapFactory.decodeByteArray(imgData, 0,
                                            imgData.length), 500, 500));
                            //img3.setOnClickListener(null);
                           img4.setEnabled(true);
                           /* btnOk.setEnabled(false);
                            btnOk.setBackgroundResource(R.drawable.buttonbackshape);*/
                            break;

                        case 4:
                            imageData4=data;
                            img4.setScaleType(ScaleType.FIT_XY);
                            img4.setImageBitmap(Utiilties.GenerateThumbnail(
                                    BitmapFactory.decodeByteArray(imgData, 0,
                                            imgData.length), 500,
                                    500));
                            img5.setEnabled(true);
                            btnOk.setEnabled(true);
                            btnOk.setText(" Submit Physical Verification ");
                            btnOk.setBackgroundResource(R.drawable.buttonbackshape);
                            break;
                        case 5:
                            imageData5=data;
                            img5.setScaleType(ScaleType.FIT_XY);
                            img5.setImageBitmap(Utiilties.GenerateThumbnail(
                                    BitmapFactory.decodeByteArray(imgData, 0,
                                            imgData.length), 500,
                                    500));
                            //img4.setOnClickListener(null);
                            break;

                    }


                }

        }

    }



    private void saveImage() {

        int i = 0;
        DataBaseHelper placeData = new DataBaseHelper(
                MultiplePhotoActivity.this);
        SQLiteDatabase db = placeData.getWritableDatabase();
        for (i = 0; i < 5; i++) {
            ContentValues values = new ContentValues();
            String[] whereArgs;
            byte[] imgData;
            switch (i) {
                case 0:
                    //GPSTime
                    if (imageData1!=null) {
                        imgData = imageData1.getByteArrayExtra("CapturedImage");

                        values.put("photo1", imgData);

                        values.put("Lat",
                                String.valueOf(imageData1.getStringExtra("Lat")));
                        values.put("Lng",
                                String.valueOf(imageData1.getStringExtra("Lng")));
                      /*  values.put("UploadDate",
                                String.valueOf(imageData1.getStringExtra("GPSTime")));*/
                        whereArgs = new String[]{String.valueOf(PID)};
                     long c= db.update("BeneFiciaryList", values, "BenId=?", whereArgs);
                        btnOk.setEnabled(true);
                     if(c>0)
                     {
                         Log.e("IMAGEE","Updated");
                     } else
                     {
                         Log.e("IMAGEE","not updttad");
                     }
                    }
                    break;
                case 1:
                    if (imageData2!=null) {
                        imgData = imageData2.getByteArrayExtra("CapturedImage");

                        values.put("photo2", imgData);
                        whereArgs = new String[]{String.valueOf(PID)};
                        db.update("BeneFiciaryList", values, "BenId=?", whereArgs);
                    }
                    break;
                case 2:
                    if (imageData3!=null) {
                        imgData = imageData3.getByteArrayExtra("CapturedImage");
                        values.put("photo3", imgData);
                        whereArgs = new String[]{String.valueOf(PID)};
                        long c1= db.update("BeneFiciaryList", values, "BenId=?", whereArgs);
                        if(c1>0)
                        {
                            Log.e("IMAGEE","Updated");
                        }
                        else
                        {
                            Log.e("IMAGEE","not updttad");
                        }
                    }
                    break;
                case 3:
                    if (imageData4!=null) {
                        imgData = imageData4.getByteArrayExtra("CapturedImage");

                        values.put("photo4", imgData);
                        whereArgs = new String[]{String.valueOf(PID)};
                        db.update("BeneFiciaryList", values, "BenId=?", whereArgs);
                    }
                    break;


            }
        }

        db.close();
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GPS");
        builder.setMessage("GPS is turned OFF...\nDo U Want Turn On GPS..")
//		builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Turn on GPS", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private class UploadPendingTask extends AsyncTask<String, Void, String> {

        String pid = "";
        BenfiList data;
        private final ProgressDialog dialog = new ProgressDialog(MultiplePhotoActivity.this);

        UploadPendingTask(BenfiList data) {
            this.data = data;
            pid = data.getBeneficiary_id();
            Log.e("Pid  ", pid + " ");
        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage(getApplicationContext().getResources().getString(R.string.uploading));
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {


//                String device_type = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("DEVICE_TYPE", "");
//                String device_name = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("DEVICE_NAME", "");
//                String app_version = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("APP_VERSION", "");
//
//                String device=device_type +":"+device_name;

            /*String devicename=getDeviceName();
            String app_version=getAppVersion();
            boolean isTablet=isTablet(getContext());
            if(isTablet) {
                devicename="Tablet::"+devicename;
                Log.e("DEVICE_TYPE", "Tablet");
            } else {
                devicename="Mobile::"+devicename;
                Log.e("DEVICE_TYPE", "Mobile");
            }*/
            String date=getCurrentDate();
           String USERID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("UserId", "");
            String res = WebserviceHelper.Upload(this.data,USERID,date);

            return res;
        }

        @Override
        protected void onPostExecute(String result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();

            }
            Log.d("uploddata",""+result);

            if (result != null) {
                Log.d("uploddata",""+result);
                Log.d("piddata",pid);


                if (result.equalsIgnoreCase("1")) {
                    Toast.makeText(getApplicationContext(),"Data Uploaded Successfully",Toast.LENGTH_LONG).show();
                   DataBaseHelper placeData = new DataBaseHelper(getApplicationContext());
                   placeData.deletePendingUpload3(pid);
                   Intent i=new Intent(MultiplePhotoActivity.this,VerifyAadhaar.class);
                   startActivity(i);
                   finish();

                } else {

                    Toast.makeText(getApplicationContext(),"Data is not Uploading",Toast.LENGTH_LONG).show();

                }

            } else {

                Toast.makeText(getApplicationContext(),getResources().getString(R.string.Error_in_Network),
                        Toast.LENGTH_SHORT).show();

            }


        }

    }
    public static String getCurrentDate()
    {
        Calendar cal=Calendar.getInstance();
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        month=month+1;

        int h=cal.get(Calendar.HOUR);
        int m=cal.get(Calendar.MINUTE);
        int s=cal.get(Calendar.SECOND);

        String date=year+"-"+month+"-"+day;
        return date;

    }

}
