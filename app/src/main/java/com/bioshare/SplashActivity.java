package com.bioshare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);

        Thread splashScreenThread=new Thread(){
            public void run(){
                try {
                    sleep(2000);
                    SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
                    boolean  firstTime=pref.getBoolean("first", true);
                    SharedPreferences.Editor editor=pref.edit();
                    if(firstTime) {
                        editor.putBoolean("first",false);
                        editor.commit();
                        Intent splashIntent = new Intent();
                        splashIntent.setClass(getApplicationContext(), TutorialActivity.class);
                        startActivity(splashIntent);
                        finish();
                    }
                    else
                    {
                        Intent splashIntent = new Intent();
                        splashIntent.setClass(getApplicationContext(),LoginActivity.class);
                        startActivity(splashIntent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        splashScreenThread.start();

    }
}
