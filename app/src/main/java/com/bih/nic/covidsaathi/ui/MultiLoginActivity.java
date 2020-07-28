package com.bih.nic.covidsaathi.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bih.nic.covidsaathi.R;
import com.bih.nic.covidsaathi.Utiilties;

public class MultiLoginActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_multi_login);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        Utiilties.setStatusBarColor(this);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_labour)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        String from = getIntent().getStringExtra("From");
        if(from != null && !from.equals("")){
//            if(from.equals(AppConstant.HQ))
//                navController.navigate(R.id.navigation_hq);
//            else if(from.equals(AppConstant.EMPLOYER))
//                navController.navigate(R.id.navigation_employer);
//            else
                navController.navigate(R.id.navigation_labour);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    public void onExit(){
        new AlertDialog.Builder(this)
                .setTitle("अलर्ट!!")
                .setMessage("क्या आप ऐप बन्द करना चाहते हैं??\n ")
                .setCancelable(false)
                .setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("नहीं", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        onExit();
    }
}
