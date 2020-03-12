package com.android.mohammadhf.thisorthat.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.mohammadhf.thisorthat.General;
import com.android.mohammadhf.thisorthat.R;

public class AboutMeActivity extends AppCompatActivity {

    private TextView name;
    private TextView myName;
    private TextView email;
    private TextView myEmail;
    private TextView aboutProgrammer;
    private TextView aboutMe;
    private TextView aboutApp;
    private TextView aboutMyApp;
    private TextView version;
    private TextView appVersion;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        initial();
        setFont();
        ImageButton btnBack = findViewById(R.id.btn_back_me);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initial(){
        name = findViewById(R.id.txt__name);
        myName = findViewById(R.id.txt_my_name);
        email = findViewById(R.id.txt_email);
        myEmail = findViewById(R.id.txt_my_email);
        aboutProgrammer = findViewById(R.id.txt_about_programmer);
        aboutMe = findViewById(R.id.txt_about_me);
        aboutApp = findViewById(R.id.txt_about_app);
        aboutMyApp = findViewById(R.id.txt_about_my_app);
        version = findViewById(R.id.txt_version);
        appVersion = findViewById(R.id.txt_app_version);
        title = findViewById(R.id.txt_title_me);
    }

    public void setFont(){
        name.setTypeface(General.typeface);
        myName.setTypeface(General.typeface);
        email.setTypeface(General.typeface);
        myEmail.setTypeface(General.typeface);
        aboutProgrammer.setTypeface(General.typeface);
        aboutMe.setTypeface(General.typeface);
        aboutApp.setTypeface(General.typeface);
        aboutMyApp.setTypeface(General.typeface);
        version.setTypeface(General.typeface);
        appVersion.setTypeface(General.typeface);
        title.setTypeface(General.typeface);
    }
}
