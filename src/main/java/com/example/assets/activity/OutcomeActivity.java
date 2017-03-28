package com.example.assets.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.assets.R;

public class OutcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_outcome);
        TextView text = (TextView) findViewById(R.id.ao_tv_text);
        text.setText("nihao");
    }
}
