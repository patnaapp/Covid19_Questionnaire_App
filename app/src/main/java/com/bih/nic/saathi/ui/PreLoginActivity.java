package com.bih.nic.saathi.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bih.nic.saathi.R;

public class PreLoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
        getActionBar().hide();
    }

    public void onDeptartmentLoginClick(View v)
    {
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("role", "SUP");
        startActivity(intent);
        //finish();
    }

    public void onComplainSystemClick(View v)
    {
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("role", "PAT");
        startActivity(intent);
        //finish();
    }
}
