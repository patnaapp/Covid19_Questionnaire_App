package com.bih.nic.saathi.ui.patient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bih.nic.saathi.Model.SwasthyaDataEntity;
import com.bih.nic.saathi.Model.SwasthyaDistrictData;
import com.bih.nic.saathi.R;
import com.levitnudi.legacytableview.LegacyTableView;

import static com.levitnudi.legacytableview.LegacyTableView.BOLD;
import static com.levitnudi.legacytableview.LegacyTableView.GOLDALINE;

public class SwasthyaDistrictDataActivity extends Activity {

    LegacyTableView legacyTableView;
    SwasthyaDistrictData covidDistrictData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_district_data);
        getActionBar().hide();

        covidDistrictData = (SwasthyaDistrictData)getIntent().getSerializableExtra("data");

        loadData();

        ImageView img = findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadData(){
        Log.e("data", covidDistrictData.getAraria().getTotal().getConfirmed());

        SwasthyaDataEntity entity;

        LegacyTableView.insertLegacyTitle("District","Confirmed", "Recovered","Deaths");

        entity = covidDistrictData.getAraria().getTotal();
        LegacyTableView.insertLegacyContent("Araria", entity.getConfirmed(), entity.getRecovered(), entity.getDeceased());

        entity = covidDistrictData.getArwal().getTotal();
        LegacyTableView.insertLegacyContent("Arwal", entity.getConfirmed(), entity.getRecovered(), entity.getDeceased());

        entity = covidDistrictData.getAurangabad().getTotal();
        LegacyTableView.insertLegacyContent("Aurangabad", entity.getConfirmed(), entity.getRecovered(), entity.getDeceased());

        entity = covidDistrictData.getBanka().getTotal();
        LegacyTableView.insertLegacyContent("Banka", entity.getConfirmed(), entity.getRecovered(), entity.getDeceased());

        entity = covidDistrictData.getAraria().getTotal();
        LegacyTableView.insertLegacyContent("Begusarai", entity.getConfirmed(), entity.getRecovered(), entity.getDeceased());

        entity = covidDistrictData.getBhagalpur().getTotal();
        LegacyTableView.insertLegacyContent("Bhagalpur", entity.getConfirmed(), entity.getRecovered(), entity.getDeceased());

        entity = covidDistrictData.getBhojpur().getTotal();
        LegacyTableView.insertLegacyContent("Bhojpur", entity.getConfirmed(), entity.getRecovered(), entity.getDeceased());

        entity = covidDistrictData.getBuxar().getTotal();
        LegacyTableView.insertLegacyContent("Buxar", entity.getConfirmed(), entity.getRecovered(), entity.getDeceased());

        legacyTableView = findViewById(R.id.legacy_table_view);
        //legacyTableView.resetVariables();
        legacyTableView.setTitle(LegacyTableView.readLegacyTitle());
        legacyTableView.setContent(LegacyTableView.readLegacyContent());
        legacyTableView.setTheme(GOLDALINE);
        legacyTableView.setTablePadding(20);
        legacyTableView.setHighlight(1);
        legacyTableView.setZoomEnabled(true);
        legacyTableView.setShowZoomControls(false);
        legacyTableView.setBottomShadowVisible(true);
        legacyTableView.setTitleFont(BOLD);
        legacyTableView.setContentTextSize(30);
        legacyTableView.setTitleTextSize(35);
        legacyTableView.build();
    }
}
