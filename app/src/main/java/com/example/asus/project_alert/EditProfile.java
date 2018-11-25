package com.example.asus.project_alert;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {
    //Change photo
    private TextView selectpic_ ;
    private String mSelectedImageUri1;
    private AppCompatActivity frthis_1 = this;
    private ImageView profile2;
    //Edit user
    private Button btn_save;
    private EditText username,userEmail;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference("message"); // SetKey//// ref.setValue("This is a test message"); // SetValue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //Create Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("EDIT PROFILE");

        btn_save = (Button)findViewById(R.id.btn_save);
        userEmail = (EditText)findViewById(R.id.email);
        username = (EditText)findViewById(R.id.name);
        //submit button
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String get_userEmail = userEmail.getText().toString();
                String get_username = username.getText().toString();
//                if (TextUtils.isEmpty(get_userEmail)){
//                    Toast.makeText(getApplicationContext(),"Pls fill email!",Toast.LENGTH_SHORT);
//                }
                if(TextUtils.isEmpty(get_username)){
                    Toast.makeText(getApplicationContext(),"Pls fill username!",Toast.LENGTH_SHORT);
                }
                sendToFireBase(/*get_userEmail,*/get_username);
            }

        });

        //Change photo
        /*selectpic_ = (TextView) findViewById(R.id.photo_change);
        profile2 = (ImageView) findViewById(R.id.profile1);

        if (mSelectedImageUri1 != null) {
            Glide.with(this)
                    .load(mSelectedImageUri1)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profile2);
        } else {
            Glide.with(this)
                    .load(R.mipmap.ic_launcher_round)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profile2);
        }

        //Select Pictures
        selectpic_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.create(frthis_1)
                        .single()
                        .theme(R.style.AppTheme)
                        .start();
            }
        }); //Photo Edit */
    }

    //Put the data in the Firebase
    private void sendToFireBase (/*String get_userEmail,*/ String get_username) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference childRef;
//        DatabaseReference listTopic;
        DatabaseReference listContent;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        childRef = ref.child("Regristation").child(user.getUid());
//        listTopic = childRef.child("email");
//        listTopic.setValue(get_userEmail);
        listContent = childRef.child("name");
        listContent.setValue(get_username);
        Toast.makeText(getApplicationContext(), "Success!!", Toast.LENGTH_SHORT).show();
    }

    //Change photo
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Image image = ImagePicker.getFirstImageOrNull(data);
//
//        if (image != null) {
//            mSelectedImageUri1 = image.getPath();
//            Glide.with(this)
//                    .load(mSelectedImageUri1)
//                    .apply(RequestOptions.circleCropTransform())
//                    .into(profile2);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    } //Photo edit_profile

}