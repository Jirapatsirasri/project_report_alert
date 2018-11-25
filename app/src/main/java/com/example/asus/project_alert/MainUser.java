package com.example.asus.project_alert;

import android.content.ClipData;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        //create action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Main Menu");


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



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem menu) {
        switch (menu.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(this, report.class);
                this.startActivity(intent);
                break;
            case R.id.logout:
                Intent intent1 = new Intent(this,LoginActivity.class);
                this.startActivity(intent1);
                break;
        }
        return true;
    }
}