package com.bih.nic.pacsmemberentry.ui.patient;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bih.nic.pacsmemberentry.R;
import com.bih.nic.pacsmemberentry.Utiilties;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BankDetailsActivity extends Activity
{
    EditText et_ifsc,et_bank_acc,et_cnfrm_acc,et_selected_per_name,et_selected_per_yob,et_selected_per_aadhar,edt_selction_date,et_raiyat_land,et_non_raiyat_land,et_kcc_no,et_voter_id,edt_remarks;
    Spinner spn_relation,spn_ben_type,spin_share_no,spn_have_kcc,spn_kcc_bank,spn_want_kcc;
    ImageView img_date;
    private int mYear, mMonth, mDay;
    DatePickerDialog datedialog;
    String _ed_selction_dob="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);

        getActionBar().hide();
        Utiilties.setStatusBarColor(this);

        initialise();

        img_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
    }

    public void initialise()
    {
        et_ifsc=findViewById(R.id.et_ifsc);
        et_bank_acc=findViewById(R.id.et_bank_acc);
        et_cnfrm_acc=findViewById(R.id.et_cnfrm_acc);
        et_selected_per_name=findViewById(R.id.et_selected_per_name);
        et_selected_per_yob=findViewById(R.id.et_selected_per_yob);
        et_selected_per_aadhar=findViewById(R.id.et_selected_per_aadhar);
        edt_selction_date=findViewById(R.id.edt_selction_date);
        et_raiyat_land=findViewById(R.id.et_raiyat_land);
        et_non_raiyat_land=findViewById(R.id.et_non_raiyat_land);
        et_kcc_no=findViewById(R.id.et_kcc_no);
        et_voter_id=findViewById(R.id.et_voter_id);
        edt_remarks=findViewById(R.id.edt_remarks);
        spn_relation=findViewById(R.id.spn_relation);
        spn_ben_type=findViewById(R.id.spn_ben_type);
        spin_share_no=findViewById(R.id.spin_share_no);
        spn_have_kcc=findViewById(R.id.spn_have_kcc);
        spn_kcc_bank=findViewById(R.id.spn_kcc_bank);
        spn_want_kcc=findViewById(R.id.spn_want_kcc);
        img_date=findViewById(R.id.img_selection_date);
    }


    public void ShowDialog() {


        Calendar c = Calendar.getInstance();
        Date min = new Date(2018, 4, 25);
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        datedialog = new DatePickerDialog(BankDetailsActivity.this,
                mDateSetListener, mYear, mMonth, mDay);

        datedialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        // datedialog.getDatePicker().setMinDate(min.getTime());
        datedialog.show();

    }


    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            mYear = selectedYear;
            mMonth = selectedMonth;
            mDay = selectedDay;
            String ds = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            ds = ds.replace("/", "-");
            String[] separated = ds.split(" ");
            Date min = new Date(2018, 4, 25);
            try {
                // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTimeString = sdf.getTimeInstance().format(new Date());
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String newString = currentTimeString.replace("A.M.", "");

                String smDay = "" + mDay, smMonth = "" + (mMonth + 1);
                if (mDay < 10) {
                    smDay = "0" + mDay;//Integer.parseInt("0" + mDay);
                }
                if ((mMonth + 1) < 10) {
                    smMonth = "0" + (mMonth + 1);
                }


                edt_selction_date.setText(smDay + "-" + smMonth + "-" + mYear);
                //_DOB = mYear + "-" + smMonth + "-" + smDay + " " + newString;
                _ed_selction_dob = mYear + smMonth + smDay;

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    };

}
