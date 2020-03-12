package com.android.mohammadhf.thisorthat;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import com.android.mohammadhf.thisorthat.db.DbContext;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class General extends Application {

    public static Typeface typeface;
    public static int maxQue;

    @Override
    public void onCreate() {
        super.onCreate();
        typeface = Typeface.createFromAsset(getAssets(),"fonts/kamva.ttf");
        DbContext db = new DbContext(getApplicationContext());
        setMaxQue();
    }


    public void setMaxQue(){
        String url = "http://mohammadhf.ir/GetPosts.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        maxQue = response.length();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("General", "onErrorResponse: " + error.getMessage() );
                        maxQue = -1;
                    }
                });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(6000,1,1));
        Volley.newRequestQueue(getApplicationContext()).add(jsonArrayRequest);
    }
}
