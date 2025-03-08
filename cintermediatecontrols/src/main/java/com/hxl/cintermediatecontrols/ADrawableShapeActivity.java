package com.hxl.cintermediatecontrols;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ADrawableShapeActivity extends AppCompatActivity implements View.OnClickListener {

    private View v_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adrawable_shape);

        findViewById(R.id.btn_rect).setOnClickListener(this);
        findViewById(R.id.btn_oval).setOnClickListener(this);

        v_content = findViewById(R.id.v_content);
        v_content.setBackgroundResource(R.drawable.shape_rect_gold);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_rect) {
            v_content.setBackgroundResource(R.drawable.shape_rect_gold);
        } else {
            v_content.setBackgroundResource(R.drawable.shape_oval_rose);
        }
    }
}