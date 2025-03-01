package com.hxl.asimplecontrols;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        
        TextView tv_learn_text_view = findViewById(R.id.tv_learn_text_view);
        tv_learn_text_view.setText(R.string.ni_hao_text_view_controls);
    }
}
