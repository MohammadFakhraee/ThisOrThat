package com.android.mohammadhf.thisorthat.networking;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.mohammadhf.thisorthat.General;
import com.android.mohammadhf.thisorthat.activities.MainActivity;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;

public class NetMissDialog {


    /** This method
     * Closes the activity that caller has sent.
     * programmer should use this method when internet is missed and app has to connect to the internet.
     * @param activity
     */
    public static void netMissDialog(final Activity activity){
        new iOSDialogBuilder(activity)
                .setTitle("مشکل داریم")
                .setSubtitle("اتصال به اینترنت برقرار نشد. لطفا اتصال خود را بررسی کرده و مجددا تلاش کنید.")
                .setFont(General.typeface)
                .setCancelable(false)
                .setPositiveListener("تلاش مجدد", new iOSDialogClickListener() {
                    @Override
                    public void onClick(iOSDialog dialog) {
                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                })
                .setNegativeListener("انصراف", new iOSDialogClickListener() {
                    @Override
                    public void onClick(iOSDialog dialog) {
                        activity.finish();
                    }
                })
                .build().show();
    }


    /** This method
     * Doesn't close the activity and just alerts to the user that internet is missed.
     * programmer should use default values if wants to use this method.
     * @param activity
     */
    public static void netMissAlert(final Activity activity){
        final iOSDialogBuilder builder = new iOSDialogBuilder(activity);
        builder.setTitle("مشکل داریم")
                .setSubtitle("اتصال به اینترنت برقرار نشد. لطفا اتصال خود را بررسی کرده و مجددا تلاش کنید.")
                .setFont(General.typeface)
                .setCancelable(true)
                .setBoldPositiveLabel(true)
                .build().show();
    }
}
