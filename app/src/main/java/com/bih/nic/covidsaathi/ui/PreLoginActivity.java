package com.bih.nic.covidsaathi.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bih.nic.covidsaathi.R;

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
