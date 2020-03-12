package com.android.mohammadhf.thisorthat.networking;

import android.content.Context;
import android.util.Log;

import com.android.mohammadhf.thisorthat.model.Question;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetService {
    private String TAG = "Server";
    private Context context;

    public NetService(Context context){
        this.context = context;
    }

    public void getQuestion(JSONObject jsonObjectReq, final OnQuestionListener onQuestionListener){
        String url = "http://mohammadhf.ir/GetPost.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObjectReq,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Question question = new Question();
                        try {
                            question.setId(response.getInt("id"));
                            question.setAnsOne(response.getString("answer_one"));
                            question.setAnsTwo(response.getString("answer_two"));
                            question.setAnsOneNums(response.getInt("numbers_one"));
                            question.setAnsTwoNums(response.getInt("numbers_two"));
                            onQuestionListener.onResponse(question);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
                        onQuestionListener.onErrorResponse();
                    }
                });
        request.setRetryPolicy(new DefaultRetryPolicy(6000,1,1));
        Volley.newRequestQueue(context).add(request);


    }

    public void sendAnswer(JSONObject requestJsonObject, final OnComplete onComplete){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://mohammadhf.ir/UpdatePost.php",
                requestJsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        onComplete.onResponse(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
                        onComplete.onResponse(false);
                    }
                });
        request.setRetryPolicy(new DefaultRetryPolicy(18000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public void getAllAnswers(final OnAllAnswersListener onAllAnswersListener){
        String url = "http://mohammadhf.ir/GetPosts.php";
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        onAllAnswersListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onAllAnswersListener.onErrorResponse();
                    }
                });
        request.setRetryPolicy(new DefaultRetryPolicy(6000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }

    public interface OnQuestionListener {
        void onResponse(Question question);
        void onErrorResponse();
    }

    public interface OnComplete{
        void onResponse(boolean complete);
    }

    public interface OnAllAnswersListener{
        void onResponse(JSONArray jsonArray);
        void onErrorResponse();
    }
}
