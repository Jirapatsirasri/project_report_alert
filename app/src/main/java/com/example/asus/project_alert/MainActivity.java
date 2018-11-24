package com.example.asus.project_alert;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText username, userPassword, userEmail;
    private Button mSubmitButton;
    private DatabaseReference mDatabase;
    private ProgressBar mProgress;
    private FirebaseAuth mAuth;
    private RadioButton male,female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.name);
        userPassword = (EditText) findViewById(R.id.password);
        userEmail = (EditText) findViewById(R.id.email);
        mSubmitButton = (Button) findViewById(R.id.submit);
        male = findViewById(R.id.radioButton4);
        female = findViewById(R.id.radioButton3);
        mProgress = new ProgressBar(this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Regristation");
        mAuth = FirebaseAuth.getInstance();

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = username.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                final String email = userEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
                    return;
                }

//                //enable button when information complete
//                username.addTextChangedListener(loginTextWatcher);
//                userEmail.addTextChangedListener(loginTextWatcher);
//                userPassword.addTextChangedListener(loginTextWatcher);

                mProgress.setVisibility(View.VISIBLE);
                startRegistration();
            }
        });
    }

//    private  TextWatcher loginTextWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String usernameInput = username.getText().toString().trim();
//            String passwordInput = userPassword.getText().toString().trim();
//            String emailInput = userEmail.getText().toString().trim();
//
//            mSubmitButton.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty() && !emailInput.isEmpty());
//        }

//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };

    private void startRegistration() {
        final String name = username.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        final String email = userEmail.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String user_id = mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = mDatabase.child(user_id);
                current_user_db.child("name").setValue(name);
                current_user_db.child("email").setValue(email);
                //db collect sex value
                if (male.isChecked()) {
                    current_user_db.child("sex").setValue("male");
                }
                if (female.isChecked()) {
                    current_user_db.child("sex").setValue("female");
                }
                Toast.makeText(MainActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                mProgress.setVisibility(View.GONE);
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgress.setVisibility(View.GONE);
    }
}


