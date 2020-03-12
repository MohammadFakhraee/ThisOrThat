package com.android.mohammadhf.thisorthat.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.mohammadhf.thisorthat.General;
import com.android.mohammadhf.thisorthat.R;

public class MenuActivity extends AppCompatActivity {

    private Button btnStartGame;
    private Button btnAboutMe;
    private Button btnChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initial();
        setFont();
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AboutMeActivity.class);
                startActivity(intent);
            }
        });
        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChartActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initial(){
        btnStartGame = findViewById(R.id.btn_start_game);
        btnAboutMe = findViewById(R.id.btn_about_me);
        btnChart = findViewById(R.id.btn_chart);
    }

    public void setFont(){
        btnAboutMe.setTypeface(General.typeface);
        btnStartGame.setTypeface(General.typeface);
        btnChart.setTypeface(General.typeface);
    }
}
