package com.hxl.fadvancedcontrols;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class EListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elist_view);

        final ListView lv_planet = findViewById(R.id.lv_planet);
    }
}