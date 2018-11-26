package com.example.asus.project_alert;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class report extends AppCompatActivity {
    private EditText text_topic, text_detail, text_location;
    private RadioButton acc_eme, traffic, weather, identify, anonymous,lost;
    private Button btn_send;
    private RadioGroup group_type,group_send;
    private ViewGroup report;
    private DatabaseReference db, childRef, listLocation, listDetail, listType, listTopic,mDatabase;
    private FirebaseUser currentUser;
    private FirebaseAuth auth;
    private String namecheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //Create Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("REPORT ALERT");

        //create option button
        report = findViewById(R.id.report_page);
        text_topic = findViewById(R.id.edittext_topic);
        text_detail = findViewById(R.id.edittext_detail);
        acc_eme = findViewById(R.id.ra_butt_acc_eme);
        traffic = findViewById(R.id.ra_butt_traffic);
        weather = findViewById(R.id.ra_butt_weahter);
        identify = findViewById(R.id.ra_butt_identify);
        lost = findViewById(R.id.ra_butt_lost);
        anonymous = findViewById(R.id.ra_butt_anonymous);
        btn_send = findViewById(R.id.btn_send);
        text_location = findViewById(R.id.edittext_location);
        group_type = findViewById(R.id.grouptype);
        group_send = findViewById(R.id.groupsend);

        //disable button
        btn_send.setVisibility(View.INVISIBLE);

        //changed text_topic
        text_topic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    TransitionManager.beginDelayedTransition(report);
                    btn_send.setEnabled(false);
                }
                else
                    TransitionManager.beginDelayedTransition(report);
                    btn_send.setVisibility(View.VISIBLE);
                    btn_send.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //changed text_detail
        text_detail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    btn_send.setEnabled(false);
                }
                else
                    TransitionManager.beginDelayedTransition(report);
                    btn_send.setVisibility(View.VISIBLE);
                    btn_send.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //changed text_location
        text_location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    btn_send.setEnabled(false);
                }
                else
                    TransitionManager.beginDelayedTransition(report);
                    btn_send.setVisibility(View.VISIBLE);
                    btn_send.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //image click to next page
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check data
                String get_topic = text_topic.getText().toString();
                String get_detail = text_detail.getText().toString();
                String get_location = text_location.getText().toString();

                //input text condition
                if (TextUtils.isEmpty(get_topic)) {
                    Toast.makeText(getApplicationContext(), "Please fill the topic!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(get_detail)) {
                    Toast.makeText(getApplicationContext(), "Please fill the Detail!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(get_location)) {
                    Toast.makeText(getApplicationContext(), "Please fill the location!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //check group_type
                if(group_type.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please choose Type of Alert!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(group_send.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please choose Type of Send!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                //db path
                db = FirebaseDatabase.getInstance().getReference("Report");
                //กำหนด หัวข้อ db
                childRef = db.push();
                listTopic = childRef.child("Topic");
                listTopic.setValue(get_topic);

                //choose button group_type
                if (acc_eme.isChecked()) {
                    listType = childRef.child("Type of Alert");
                    listType.setValue("Accident / Emergency");
                }
                if (traffic.isChecked()) {
                    listType = childRef.child("Type of Alert");
                    listType.setValue("Traffic");
                }
                if (weather.isChecked()) {
                    listType = childRef.child("Type of Alert");
                    listType.setValue("Weather");
                }
                if (lost.isChecked()) {
                    listType = childRef.child("Type of Alert");
                    listType.setValue("Lost and Found");
                }


                //choose button group_send
                if (anonymous.isChecked()) {
                    listType = childRef.child("Username");
                    listType.setValue("Anonymous");
                }
                if (identify.isChecked()){
                    listType = childRef.child("Username");
                    db = FirebaseDatabase.getInstance().getReference("Regristation").child(currentUser.getUid());
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (identify.isChecked()) {
                                if (dataSnapshot.exists()){
                                    listType.setValue(dataSnapshot.child("name").getValue().toString());
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                //send data to database

                listDetail = childRef.child("Detail");
                listDetail.setValue(get_detail);
//                listDetail.push().setValue(get_detail);
                listLocation = childRef.child("Location");
                listLocation.setValue(get_location);
//                listLocation.push().setValue(get_location);



                //click to next page
                Intent intent = new Intent(report.this, complete_alert.class);
                startActivity(intent);
            }
        });
    }

    // แตะที่ไหน keyboard หายโดยไม่ต้องกดย้อนกลับ
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (identify.isChecked()) {
                if (dataSnapshot.exists()){
                    Log.i("Check", "data: " + dataSnapshot.child("name").getValue().toString());
                    namecheck = dataSnapshot.child("name").getValue().toString();
                }
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}