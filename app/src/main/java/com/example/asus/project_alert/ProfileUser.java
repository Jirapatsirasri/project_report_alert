package com.example.asus.project_alert;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProfileUser extends AppCompatActivity {

    //Edit profile button
    private Button edit_profile;
    //Show data in firebase
    private DatabaseReference mDatabase;
    private TextView mMailView,mNameView;
    //Firebase
    FirebaseAuth auth;
    //uid
    private String userID;
    public TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        //Create Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PROFILE");

        //Link to edit profile
        edit_profile = (Button) findViewById(R.id.edit_profile);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProf = new Intent(ProfileUser.this, EditProfile.class);
                startActivity(editProf);
            }
        });

        //Show Email: and Username:
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("Regristation").child(user.getUid());
        mMailView = (TextView) findViewById(R.id.email);
        mNameView = findViewById(R.id.name);
        mDatabase.addListenerForSingleValueEvent(valueEventListener);

    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                Log.i("test", dataSnapshot.toString());
                mMailView.setText("Email : " + dataSnapshot.child("email").getValue().toString());
                mNameView.setText("Username : "+dataSnapshot.child("name").getValue().toString());
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}