package com.example.dellpc.facts.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.dellpc.facts.model.ApiClient;
import com.example.dellpc.facts.model.ApiInterface;
import com.example.dellpc.facts.R;

public class Global {
    static String TAG = "Global";
    public static ProgressDialog progressDialog;
    public static ApiInterface apiService = ((ApiInterface) ApiClient.getClient().create(ApiInterface.class));

    public static void progressHide(Context context) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog.hide();
        }
    }

    public static void progressShow(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setTitle(context.getResources().getString(R.string.app_name));
        progressDialog.setMessage(context.getResources().getString(R.string.loading));
        progressDialog.show();
    }


    public static void showToastShort(Context context, String text) {
        try {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}