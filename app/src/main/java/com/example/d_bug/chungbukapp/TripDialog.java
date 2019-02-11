package com.example.d_bug.chungbukapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class TripDialog extends Dialog {

    private Intent intent;

    private Context context;

    private FrameLayout btnFrame;

    private TextView dialogTitle;
    private TextView dialogAddress;
    private TextView dialogTel;
    private TextView dialogTime;
    private TextView dialogIntro;
    private TextView dialogFacility;

    private Button btnDialogDirection;
    private Button btnDialogTel;
    private Button btnDialogURL;

    ImageButton btnClear;

    private String title;
    private String tel;
    private String intro;
    private String city;
    private String address;
    private String time;
    private String siteURL;
    private String facility;
    private String latitude;
    private String longitude;

    public TripDialog(Context context) {
        super(context);
        this.context=context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_dialog);

        //다이얼로그 사이즈 조절 (화면가로95% 화면세로80%)
        WindowManager.LayoutParams params = getWindow().getAttributes();
        WindowManager windowManager = ((WindowManager) context.getApplicationContext().getSystemService(context.getApplicationContext().WINDOW_SERVICE));
        params.width = (int) (windowManager.getDefaultDisplay().getWidth() * 0.95);
        params.height = (int) (windowManager.getDefaultDisplay().getHeight() * 0.8);
        getWindow().setAttributes(params);

        btnFrame = (FrameLayout) findViewById(R.id.btnFrame);

        dialogTitle = (TextView) findViewById(R.id.dialogTitle);
        dialogAddress = (TextView) findViewById(R.id.dialogAddress);
        dialogTel = (TextView) findViewById(R.id.dialogTel);
        dialogTime = (TextView) findViewById(R.id.dialogTime);
        dialogFacility = (TextView) findViewById(R.id.dialogFacility);
        dialogIntro = (TextView) findViewById(R.id.dialogIntro);

        btnDialogDirection = (Button) findViewById(R.id.btnDialogDirection);
        btnDialogTel = (Button) findViewById(R.id.btnDialogTel);
        btnDialogURL = (Button) findViewById(R.id.btnDialogURL);

        btnClear = (ImageButton) findViewById(R.id.btnClear);
    }

    public void setParams(String title, String tel, String intro, String city, String address,
                         String time, String siteURL, String facility, String latitude, String longitude) {
        this.title = title;
        this.tel = tel;
        this.intro = intro;
        this.city = city;
        this.address = address;
        this.time = time;
        this.siteURL = siteURL;
        this.facility = facility;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void set() {
        dialogTitle.setText(" " + title);
        dialogAddress.setText(address);
        dialogTel.setText(tel);
        dialogTime.setText(time);
        dialogFacility.setText(facility);
        dialogIntro.setText(intro);

        btnDialogDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + latitude + "," + longitude));
                context.startActivity(intent);
            }
        });

        btnDialogTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str[] = tel.split(" ");
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + str[0]));
                context.startActivity(intent);
            }
        });

        if(!siteURL.equals("-")) {
            btnFrame.setVisibility(FrameLayout.VISIBLE);
            btnDialogURL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(siteURL));
                    context.startActivity(intent);
                }
            });
        }
    }
}