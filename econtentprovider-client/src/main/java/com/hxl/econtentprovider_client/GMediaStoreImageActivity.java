package com.hxl.econtentprovider_client;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GMediaStoreImageActivity extends AppCompatActivity {

    private TextView et_phone;
    private TextView et_title;
    private TextView et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmedia_store_image);

        et_phone = findViewById(R.id.et_phone);
        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
    }
}