package com.android.mohammadhf.thisorthat.activities;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.android.mohammadhf.thisorthat.General;
import com.android.mohammadhf.thisorthat.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Runnable killSplash = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        };
        new Handler().postDelayed(killSplash,3000);
    }
}