package com.example.asus.project_alert;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class showAlert extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlertAdapter adapter;
    private List<Alert> alertList;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_alert);

        //Create Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("WHAT'S HAPPENED");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alertList = new ArrayList<>();
        adapter = new AlertAdapter(this, alertList);
        recyclerView.setAdapter(adapter);



        db = FirebaseDatabase.getInstance().getReference("Report");
        db.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            alertList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.i("test", snapshot.toString());
                   // Log.i("test", snapshot.child("Type of Alert").getValue().toString());

                    Alert alert = new Alert();
                    alert.type_of_alert = snapshot.child("Type of Alert").getValue().toString();
                    alert.detail = snapshot.child("Detail").getValue().toString();
                    alert.topic = snapshot.child("Topic").getValue().toString();
                    alert.sent_type = snapshot.child("Username").getValue().toString();
                    alert.location = snapshot.child("Location").getValue().toString();
                    alertList.add(alert);
                }
                adapter.notifyDataSetChanged();
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    }
