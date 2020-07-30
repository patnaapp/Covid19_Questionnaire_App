package com.bih.nic.saathi.ui.patient;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bih.nic.saathi.Model.PaymentStatusEntity;
import com.bih.nic.saathi.R;
import com.bih.nic.saathi.Utiilties;

public class PaymentStatusActivity extends Activity {

    TextView tv_district,tv_block,tv_panchayat,tv_qrt_14,tv_name,tv_account,tv_ifsc,tv_bank_name,tv_paymentstatus,tv_eupi_status,tv_rjct_reason;
    LinearLayout ll_rjct_remarks;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);

        getActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        Utiilties.setStatusBarColor(this);

        tv_district = findViewById(R.id.tv_district);
        tv_block = findViewById(R.id.tv_block);
        tv_panchayat = findViewById(R.id.tv_panchayat);
        tv_qrt_14 = findViewById(R.id.tv_qrt_14);
        tv_name = findViewById(R.id.tv_name);
        tv_account = findViewById(R.id.tv_account);
        tv_ifsc = findViewById(R.id.tv_ifsc);
        tv_bank_name = findViewById(R.id.tv_bank_name);
        tv_paymentstatus = findViewById(R.id.tv_paymentstatus);
        tv_eupi_status = findViewById(R.id.tv_eupi_status);
        tv_rjct_reason = findViewById(R.id.tv_rjct_reason);
        ll_rjct_remarks = findViewById(R.id.ll_rjct_remarks);
        img_back=(ImageView) findViewById(R.id.img);
        ll_rjct_remarks.setVisibility(View.GONE);

        PaymentStatusEntity info = (PaymentStatusEntity)getIntent().getSerializableExtra("data");
        setData(info);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setData(PaymentStatusEntity info)
    {
        tv_district.setText(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("DistName", ""));
        tv_block.setText(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("BlockName", ""));
        tv_panchayat.setText(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("PanchayatName", ""));
        tv_qrt_14.setText(info.getIsQRT14Days());
        tv_name.setText(info.getBeneficiaryName());
        tv_account.setText(info.getAccountNumber());
        tv_ifsc.setText(info.getIFSC());
        tv_bank_name.setText(info.getBankName());
        tv_paymentstatus.setText(info.getPaymentStatus());
        tv_eupi_status.setText(info.getEupi_UTR_Status());

        if (info.getEupi_UTR_Status().equals("RJCT"))
        {
            ll_rjct_remarks.setVisibility(View.GONE);
            tv_rjct_reason.setText(info.getEupi_StatusRemarks());
        }
    }
}
