package com.example.asus.project_alert;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainUser extends AppCompatActivity {

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    //widget
    private Button mSignOut;

    //call
    private Button calling;

    private Button profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        //create action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Main Menu");


        //create profile button

//        profile = (Button) findViewById(R.id.profile);
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(MainUser.this, ProfileUser.class));
//            }
//        });

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

        //create call button
        calling = findViewById(R.id.calling);
        calling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainUser.this, CallAlert.class);
                startActivity(intent2);
            }
        });

        //crate sign out button
//        mSignOut = findViewById(R.id.sign_out);
//        mSignOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(MainUser.this, LoginActivity.class));
//                finish();
//            }
//        });
    }
}