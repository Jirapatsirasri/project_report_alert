package com.example.asus.project_alert;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CallAlert extends AppCompatActivity {

    TextView txt, lad, rescue, hos1, hos2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_alert);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("EMERGENCY CALL");

        txt = (TextView) findViewById(R.id.txt);
        lad = (TextView) findViewById(R.id.lad);
        rescue = (TextView)findViewById(R.id.rescue);
        hos1 = (TextView)findViewById(R.id.hos1);
        hos2 = (TextView)findViewById(R.id.hos2);

        //create calling to phone 5 numbers

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = txt.getText().toString();
                if (!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                } else {
                    Toast.makeText(CallAlert.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = lad.getText().toString();
                if (!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                } else {
                    Toast.makeText(CallAlert.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rescue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = rescue.getText().toString();
                if (!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                } else {
                    Toast.makeText(CallAlert.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = hos1.getText().toString();
                if (!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                } else {
                    Toast.makeText(CallAlert.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = hos2.getText().toString();
                if (!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                } else {
                    Toast.makeText(CallAlert.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
