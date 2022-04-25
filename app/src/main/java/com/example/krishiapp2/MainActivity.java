package com.example.krishiapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ImageView veg;
    TextView text1;
    EditText text2;
    EditText text3;
    EditText text4;
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        veg = (ImageView) findViewById(R.id.imageView1);
        text1 = (TextView) findViewById(R.id.textView);
        text2 = (EditText) findViewById(R.id.textview1);
        text3 = (EditText) findViewById(R.id.textview2);
        text4 = (EditText) findViewById(R.id.textview3);
        button1 = (Button) findViewById(R.id.button1);




            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (text2.getText().toString().length() == 0 ) {

                        Toast.makeText(getApplicationContext(), "Enter Details", Toast.LENGTH_SHORT).show();

                    }
                    else if (text3.getText().toString().length() == 0 ) {

                        Toast.makeText(getApplicationContext(), "Enter Details", Toast.LENGTH_SHORT).show();
                    }
                     else if (text4.getText().toString().length() == 0) {

                        Toast.makeText(getApplicationContext(), "Enter Details", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(getApplicationContext(), "Sign Up", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                        startActivity(intent);
                    }
                }
            });

    }

}