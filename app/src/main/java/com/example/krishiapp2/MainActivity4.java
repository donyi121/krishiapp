package com.example.krishiapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    EditText t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        t1 = (EditText) findViewById(R.id.Text);
        String TempHolder = getIntent().getStringExtra("name");
        t1.setText(TempHolder);

    }
}