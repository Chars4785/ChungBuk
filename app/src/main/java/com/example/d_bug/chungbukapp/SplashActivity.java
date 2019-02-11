package com.example.d_bug.chungbukapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;

public class SplashActivity extends Activity {


    @Override
    protected void onResume() {
        networkCheck();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private void networkCheck() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("네트워크가 연결되어 있지 않습니다.");
        alertDialogBuilder.setMessage("설정으로 이동하겠습니까?")
                .setCancelable(false)
                .setPositiveButton("이동",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(Settings.ACTION_SETTINGS));
                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                SplashActivity.this.finish();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilder.create();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (false == isConnected()) {
            alertDialog.show();
        } else {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    private boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetWork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetWork != null && activeNetWork.isConnectedOrConnecting();
        return isConnected;
    }
}