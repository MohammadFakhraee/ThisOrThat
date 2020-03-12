package com.android.mohammadhf.thisorthat.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.mohammadhf.thisorthat.General;
import com.android.mohammadhf.thisorthat.R;
import com.android.mohammadhf.thisorthat.StatisticsSharedPref;
import com.android.mohammadhf.thisorthat.db.DbContext;
import com.android.mohammadhf.thisorthat.model.UserStatistic;
import com.android.mohammadhf.thisorthat.model.answer;
import com.android.mohammadhf.thisorthat.networking.NetMissDialog;
import com.android.mohammadhf.thisorthat.networking.NetService;
import com.android.mohammadhf.thisorthat.networking.NetworkChecker;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ChartActivity extends AppCompatActivity {

    private static final String TAG = "ChartActivity";
    private TextView title;
    private TextView biAsar;
    private TextView normal;
    private TextView khas;
    private TextView kol;
    private TextView changeBiAsar;
    private TextView changeNormal;
    private TextView changeKhas;
    private TextView changeKol;
    private TextView normalPers;
    private TextView khasPers;
    private TextView changeNormalPers;
    private TextView changeKhasPers;
    private ImageButton btnBackToMain;
    private ProgressBar progressBar;
    private List<answer> answersList;

    private TextView txtAboutKol;
    private TextView txtAboutKol2;
    private TextView txtAboutKhas;
    private TextView txtAboutKhas2;
    private TextView txtAboutNormal;
    private TextView txtAboutNormal2;
    private TextView txtAboutBiAsar;
    private TextView txtAboutBiasar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        initial();
        setFont();

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (NetworkChecker.readNetworkStatus(getApplicationContext()) == 0) {
            setDefaultValues(new StatisticsSharedPref(getApplicationContext()).getUserAnswersStatistics());
            NetMissDialog.netMissAlert(ChartActivity.this);
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);

            answersList = DbContext.newSession().getAnswerDao().loadAll();
            if (answersList.size() == 0) {
                progressBar.setVisibility(View.GONE);
                } else {
                NetService netService = new NetService(getApplicationContext());
                netService.getAllAnswers(new NetService.OnAllAnswersListener() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        setNormalKhas(jsonArray);
                        setNormalKhasPers();
                        saveDataToSharedPref();
                    }

                    @Override
                    public void onErrorResponse() {
                        NetMissDialog.netMissDialog(ChartActivity.this);
                    }
                });
            }
        }
    }

    public void initial() {
        title = findViewById(R.id.txt_title_chart);
        biAsar = findViewById(R.id.txt_bi_asar);
        normal = findViewById(R.id.txt_normal);
        khas = findViewById(R.id.txt_khas);
        kol = findViewById(R.id.txt_kol);
        changeBiAsar = findViewById(R.id.txt_bi_asar_change);
        changeNormal = findViewById(R.id.txt_normal_change);
        changeKhas = findViewById(R.id.txt_khas_change);
        changeKol = findViewById(R.id.txt_kol_change);
        normalPers = findViewById(R.id.txt_darsad_normal);
        khasPers = findViewById(R.id.txt_darsad_khas);
        changeNormalPers = findViewById(R.id.txt_darsad_normal_change);
        changeKhasPers = findViewById(R.id.txt_darsad_khas_change);
        btnBackToMain = findViewById(R.id.btn_backtomenu_chart);
        progressBar = findViewById(R.id.progressBarChart);

        txtAboutKol = findViewById(R.id.txt1);
        txtAboutKol2 = findViewById(R.id.txt2);
        txtAboutKhas = findViewById(R.id.txt3);
        txtAboutKhas2 = findViewById(R.id.txt4);
        txtAboutNormal = findViewById(R.id.txt5);
        txtAboutNormal2 = findViewById(R.id.txt6);
        txtAboutBiAsar = findViewById(R.id.txt7);
        txtAboutBiasar2 = findViewById(R.id.txt8);
    }

    public void setFont() {
        title.setTypeface(General.typeface);
        biAsar.setTypeface(General.typeface);
        normal.setTypeface(General.typeface);
        khas.setTypeface(General.typeface);
        kol.setTypeface(General.typeface);
        changeBiAsar.setTypeface(General.typeface);
        changeNormal.setTypeface(General.typeface);
        changeKhas.setTypeface(General.typeface);
        changeKol.setTypeface(General.typeface);
        normalPers.setTypeface(General.typeface);
        khasPers.setTypeface(General.typeface);
        changeNormalPers.setTypeface(General.typeface);
        changeKhasPers.setTypeface(General.typeface);

        txtAboutKol.setTypeface(General.typeface);
        txtAboutKol2.setTypeface(General.typeface);
        txtAboutKhas.setTypeface(General.typeface);
        txtAboutKhas2.setTypeface(General.typeface);
        txtAboutNormal.setTypeface(General.typeface);
        txtAboutNormal2.setTypeface(General.typeface);
        txtAboutBiAsar.setTypeface(General.typeface);
        txtAboutBiasar2.setTypeface(General.typeface);
    }

    public void setNormalKhas(JSONArray jsonArray){
        for (int id = 1; id <= answersList.size(); id++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(id-1);
                answer answer = answersList.get(id - 1);
                if (answer.getChoose_answer().equals(jsonObject.getString("answer_one"))) {
                    if (jsonObject.getInt("numbers_one") > jsonObject.getInt("numbers_two")) {
                        changeNormal.setText(String.valueOf(Integer.parseInt(changeNormal.getText().toString()) + 1));
                    } else if (jsonObject.getInt("numbers_one") < jsonObject.getInt("numbers_two")) {
                        changeKhas.setText(String.valueOf(Integer.parseInt(changeKhas.getText().toString()) + 1));
                    } else {
                        changeBiAsar.setText(String.valueOf(Integer.parseInt(changeBiAsar.getText().toString()) + 1));
                    }
                    changeKol.setText(String.valueOf(Integer.parseInt(changeKol.getText().toString()) + 1));
                }else if (answer.getChoose_answer().equals(jsonObject.getString("answer_two"))){
                    if(jsonObject.getInt("numbers_two") > jsonObject.getInt("numbers_one")){
                        changeNormal.setText(String.valueOf(Integer.parseInt(changeNormal.getText().toString()) + 1));
                    }else if (jsonObject.getInt("numbers_two") < jsonObject.getInt("numbers_one")){
                        changeKhas.setText(String.valueOf(Integer.parseInt(changeKhas.getText().toString()) + 1));
                    }else{
                        changeBiAsar.setText(String.valueOf(Integer.parseInt(changeBiAsar.getText().toString()) + 1));
                    }
                    changeKol.setText(String.valueOf(Integer.parseInt(changeKol.getText().toString()) + 1));
                }
            } catch (JSONException e) {
                Log.e(TAG, "onResponse: " + e.getMessage());
            }
        }
        progressBar.setVisibility(View.GONE);
    }

    public void setNormalKhasPers(){
        int kol = Integer.parseInt(changeKol.getText().toString());
        if(kol != 0){
            changeNormalPers.setText(String.valueOf(Math.round( (100* Integer.parseInt(changeNormal.getText().toString())) / kol )));
            changeKhasPers.setText(String.valueOf(Math.round( (100* Integer.parseInt(changeKhas.getText().toString())) / kol )));
        }
    }

    public void setDefaultValues(UserStatistic statistic){
        changeKol.setText(String.valueOf(statistic.getKol()));
        changeKhas.setText(String.valueOf(statistic.getKhas()));
        changeNormal.setText(String.valueOf(statistic.getNormal()));
        changeBiAsar.setText(String.valueOf(statistic.getBiAsar()));
        setNormalKhasPers();
    }

    public void saveDataToSharedPref(){
        UserStatistic statistic = new UserStatistic();
        statistic.setKol(Integer.parseInt(changeKol.getText().toString()));
        statistic.setKhas(Integer.parseInt(changeKhas.getText().toString()));
        statistic.setNormal(Integer.parseInt(changeNormal.getText().toString()));
        statistic.setBiAsar(Integer.parseInt(changeBiAsar.getText().toString()));

        new StatisticsSharedPref(getApplicationContext()).saveUserAnswersStatistics(statistic);
    }

}
