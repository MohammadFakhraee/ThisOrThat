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
import android.widget.Toast;

import com.android.mohammadhf.thisorthat.General;
import com.android.mohammadhf.thisorthat.R;
import com.android.mohammadhf.thisorthat.db.DbContext;
import com.android.mohammadhf.thisorthat.model.Question;
import com.android.mohammadhf.thisorthat.model.answer;
import com.android.mohammadhf.thisorthat.networking.NetMissDialog;
import com.android.mohammadhf.thisorthat.networking.NetService;
import com.android.mohammadhf.thisorthat.networking.NetworkChecker;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import ru.katso.livebutton.LiveButton;

public class MainActivity extends AppCompatActivity {

    private LiveButton ansOne;
    private LiveButton ansTwo;
    private LiveButton btnNextQues;
    private TextView ansOnePers;
    private TextView ansTwoPers;
    private TextView title;
    private TextView or;
    private TextView currentID;
    private ImageButton btnBackToMenu;
    private ImageButton btnShowChart;
    private String TAG = "HELLO";
    private NetService netService;
    private Question question;
    private int counter;
    private boolean isClicked = false;
    private boolean isRecieved = false;
    private ProgressBar bar1;
    private ProgressBar bar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (NetworkChecker.readNetworkStatus(MainActivity.this) == 0) {
            NetMissDialog.netMissDialog(MainActivity.this);
        } else {
            initial();
            addFonts();
//            DbContext.newSession().getAnswerDao().deleteAll();
            bar1.setVisibility(View.VISIBLE);
            bar2.setVisibility(View.VISIBLE);
            counter = DbContext.newSession().getAnswerDao().loadAll().size() + 1;
            netService = new NetService(this);
            if (counter > General.maxQue) {
                if(General.maxQue == -1)
                    NetMissDialog.netMissDialog(MainActivity.this);
                else
                    quesMissingDialog(MainActivity.this);
            } else {
                JSONObject jsonObjectReq = new JSONObject();
                try {
                    jsonObjectReq.put("THIS_NUMBER", counter);
                    netService.getQuestion(jsonObjectReq, new NetService.OnQuestionListener() {
                        @Override
                        public void onResponse(Question question) {
                            MainActivity.this.question = question;
                            bar1.setVisibility(View.GONE);
                            bar2.setVisibility(View.GONE);
                            setAnswers();
                            isRecieved = true;
                        }

                        @Override
                        public void onErrorResponse() {
                            NetMissDialog.netMissDialog(MainActivity.this);
                        }
                    });
                } catch (JSONException e) {
                    Log.e(TAG, "onCreate: " + e.getMessage());
                }
            }

            btnBackToMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            btnShowChart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),ChartActivity.class);
                    startActivity(intent);
                }
            });

            ansOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (!isClicked) {
                        if (isRecieved) {
                            JSONObject requestJsonObject = new JSONObject();
                            try {
                                requestJsonObject.put("answer", "numbers_one");
                                requestJsonObject.put("nums", question.getAnsOneNums() + 1);
                                requestJsonObject.put("id", question.getId());
                                isRecieved = false;
                                netService.sendAnswer(requestJsonObject, new NetService.OnComplete() {
                                    @Override
                                    public void onResponse(boolean complete) {
                                        if (complete) {
                                            ansTwo.setBackgroundColor(v.getResources().getColor(R.color.grey));
                                            question.setAnsOneNums(question.getAnsOneNums() + 1);
                                            setNums();
                                            isClicked = true;
                                            answer sample = new answer();
                                            sample.setChoose_answer(question.getAnsOne());
                                            sample.setOther_answer(question.getAnsTwo());
                                            DbContext.newSession().getAnswerDao().insert(sample);
                                        } else
                                            Toast.makeText(getApplicationContext(),
                                                    "خطا در اتصال به سرور! لطفا دوباره امتحان کنید!",
                                                    Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "صبر کن اطلاعاتو از سرور بگیرم", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "روی گزینه بعدی کلیک کن", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            ansTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (!isClicked) {
                        if (isRecieved) {
                            JSONObject requestJsonObject = new JSONObject();
                            try {
                                requestJsonObject.put("answer", "numbers_two");
                                requestJsonObject.put("nums", question.getAnsTwoNums() + 1);
                                requestJsonObject.put("id", question.getId());
                                isRecieved = false;
                                netService.sendAnswer(requestJsonObject, new NetService.OnComplete() {
                                    @Override
                                    public void onResponse(boolean complete) {
                                        if (complete) {
                                            ansOne.setBackgroundColor(v.getResources().getColor(R.color.grey));
                                            question.setAnsTwoNums(question.getAnsTwoNums() + 1);
                                            setNums();
                                            isClicked = true;
                                            answer sample = new answer();
                                            sample.setChoose_answer(question.getAnsTwo());
                                            sample.setOther_answer(question.getAnsOne());
                                            DbContext.newSession().getAnswerDao().insert(sample);
                                        } else
                                            Toast.makeText(getApplicationContext(),
                                                    "خطا در اتصال به سرور! لطفا دوباره امتحان کنید!",
                                                    Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "صبر کن اطلاعاتو از سرور بگیرم", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "روی گزینه بعدی کلیک کن", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnNextQues.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (isClicked) {
                        if (NetworkChecker.readNetworkStatus(MainActivity.this) == 0) {
                            Toast.makeText(getApplicationContext(),
                                    "خظا در اتصال به اینترنت! لطفا اتصال خود را بررسی کنید و دوباره امتحان کنید!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            emptyButtonsTxt();
                            counter++;
                            if (counter > General.maxQue) {
                                quesMissingDialog(MainActivity.this);
                            } else {
                                bar1.setVisibility(View.VISIBLE);
                                bar2.setVisibility(View.VISIBLE);
                                JSONObject jsonObjectReq = new JSONObject();
                                try {
                                    jsonObjectReq.put("THIS_NUMBER", counter);
                                    netService.getQuestion(jsonObjectReq, new NetService.OnQuestionListener() {
                                        @Override
                                        public void onResponse(Question question) {
                                            MainActivity.this.question = question;
                                            bar1.setVisibility(View.GONE);
                                            bar2.setVisibility(View.GONE);
                                            setAnswers();
                                            setButtonsToNormal(v);
                                            isClicked = false;
                                            isRecieved = true;
                                        }

                                        @Override
                                        public void onErrorResponse() {
                                            NetMissDialog.netMissDialog(MainActivity.this);
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "لطفا یک گزینه را انتخاب کنید!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

//    public void netMissDialog(final Activity activity) {
//        new iOSDialogBuilder(activity)
//                .setTitle("مشکل داریم")
//                .setSubtitle("اتصال به اینترنت برقرار نشد. لطفا اتصال خود را بررسی کرده و مجددا تلاش کنید.")
//                .setFont(General.typeface)
//                .setCancelable(false)
//                .setPositiveListener("تلاش مجدد", new iOSDialogClickListener() {
//                    @Override
//                    public void onClick(iOSDialog dialog) {
//                        Intent intent = new Intent(activity, MainActivity.class);
//                        activity.startActivity(intent);
//                        activity.finish();
//                    }
//                })
//                .setNegativeListener("انصراف", new iOSDialogClickListener() {
//                    @Override
//                    public void onClick(iOSDialog dialog) {
//                        activity.finish();
//                    }
//                })
//                .build().show();
//    }

    public void quesMissingDialog(final Activity activity) {
        new iOSDialogBuilder(MainActivity.this)
                .setSubtitle("سوالا تموم شد. بعدا دوباره امتحان کن ممکنه سوال اضافه شده باشه. (:")
                .setFont(General.typeface)
                .setCancelable(false)
                .setBoldPositiveLabel(true)
                .setPositiveListener("باشه", new iOSDialogClickListener() {
                    @Override
                    public void onClick(iOSDialog dialog) {
                        activity.finish();
                    }
                })
                .setTitle("اووپس..")
                .build().show();
    }

    public void initial() {
        ansOne = findViewById(R.id.answer_one);
        ansTwo = findViewById(R.id.answer_two);
        btnNextQues = findViewById(R.id.next_question_button);
        ansOnePers = findViewById(R.id.ans_one_pers);
        ansTwoPers = findViewById(R.id.ans_two_pers);
        currentID = findViewById(R.id.txt_current_num);
        btnBackToMenu = findViewById(R.id.btn_backtomenu);
        btnShowChart = findViewById(R.id.btn_showchart);
        bar1 = findViewById(R.id.progressBar);
        bar2 = findViewById(R.id.progressBar2);
        title = findViewById(R.id.txt_title);
        or = findViewById(R.id.txt_or);
    }

    public void setAnswers() {
        currentID.setText(String.valueOf(question.getId()));
        ansOne.setText(question.getAnsOne());
        ansTwo.setText(question.getAnsTwo());
    }

    public void setNums() {
        int sum = question.getAnsOneNums() + question.getAnsTwoNums();
        int one = Math.round((100 * question.getAnsOneNums()) / sum);
        ansOnePers.setText(String.valueOf(one) + "%");
        ansTwoPers.setText(String.valueOf(100 - one) + "%");
    }

    public void emptyButtonsTxt() {
        currentID.setText("");
        ansOne.setText("");
        ansTwo.setText("");
    }

    public void setButtonsToNormal(View v) {
        ansOne.setBackgroundColor(v.getResources().getColor(R.color.DarkYellow));
        ansTwo.setBackgroundColor(v.getResources().getColor(R.color.darkGreen));

        ansOnePers.setText("");
        ansTwoPers.setText("");
    }

    public void addFonts() {
        ansOne.setTypeface(General.typeface);
        ansTwo.setTypeface(General.typeface);
        ansOnePers.setTypeface(General.typeface);
        ansTwoPers.setTypeface(General.typeface);
        btnNextQues.setTypeface(General.typeface);
        title.setTypeface(General.typeface);
        or.setTypeface(General.typeface);
        currentID.setTypeface(General.typeface);
    }
}