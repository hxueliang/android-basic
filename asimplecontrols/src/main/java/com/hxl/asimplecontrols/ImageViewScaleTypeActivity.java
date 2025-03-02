package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageViewScaleTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_scale_type);

        ImageView iv_9 = findViewById(R.id.iv_girl_9);
        iv_9.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}