package com.example.asus.project_alert;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        //create action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Main User");


        ImageButton alert = findViewById(R.id.alert);


        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainUser.this, report.class);
                startActivity(intent);
            }
        });

        Button intent = findViewById(R.id.intent);

        intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainUser.this, showAlert.class);
                startActivity(intent1);
            }
        });




    }

    /*public void onClickNext (View view) {
        Button bt_intent = findViewById(R.id.intent);
        Intent intent = new Intent(MainUser.this,showAlert.class);
        startActivity(intent);
    }*/
}
