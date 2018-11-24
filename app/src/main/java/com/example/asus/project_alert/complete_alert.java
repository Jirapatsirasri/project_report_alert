package com.example.asus.project_alert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class complete_alert extends AppCompatActivity {

    ImageView alert,text,text1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_alert);
        alert = findViewById(R.id.alertimage_completepage);
        text = findViewById(R.id.text_alert_success);
        text1 = findViewById(R.id.text_wait_update);
        //splash screen
        Animation splash = AnimationUtils.loadAnimation(this,R.anim.splashscreen);
        alert.startAnimation(splash);
        text.startAnimation(splash);
        text1.startAnimation(splash);

        //back alert_page
        final Intent i = new Intent(this,MainUser.class);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
