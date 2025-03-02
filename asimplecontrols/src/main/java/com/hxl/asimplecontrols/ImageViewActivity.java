package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_girl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        Button btn = findViewById(R.id.btn);
        iv_girl = findViewById(R.id.iv_girl);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        iv_girl.setImageResource(R.drawable.ic_boy);
    }
}