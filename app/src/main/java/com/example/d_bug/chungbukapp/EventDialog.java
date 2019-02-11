package com.example.d_bug.chungbukapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class EventDialog extends Dialog {

    private Context context;

    private Intent intent;
    private int key = 83;
    private int flag = 0;

    private ViewPager pager;
    private ImageButton btnPrevious;
    private ImageButton btnNext;
    private Button btnDialogSched;

    private TextView dialogTitle;
    private TextView dialogStadiumInfo;

    ImageButton btnClear;

    private String title;
    private String cityName;
    private String eventNum;
    private String introNum;
    private String stadiumNum;
    private String imgURL;
    private StadiumItem[] stadiumItems;

    public EventDialog(Context context) {
        super(context);
        this.context=context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_dialog);

        //다이얼로그 사이즈 조절 (화면가로95% 화면세로80%)
        WindowManager.LayoutParams params = getWindow().getAttributes();
        WindowManager windowManager = ((WindowManager) context.getApplicationContext().getSystemService(context.getApplicationContext().WINDOW_SERVICE));
        params.width = (int) (windowManager.getDefaultDisplay().getWidth() * 0.95);
        params.height = (int) (windowManager.getDefaultDisplay().getHeight() * 0.8);
        getWindow().setAttributes(params);

        dialogTitle = (TextView) findViewById(R.id.dialogTitle);
        dialogStadiumInfo = (TextView) findViewById(R.id.dialogStadiumInfo);

        pager = (ViewPager) findViewById(R.id.pager);
        btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
        btnNext = (ImageButton) findViewById(R.id.btnNext);

        btnDialogSched = (Button) findViewById(R.id.btnDialogSched);
        if (imgURL.contains("paragame")) {
            key = 144;
            flag = 1;
        }
        btnDialogSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, MyWebView.class);
                intent.putExtra("key", key);
                intent.putExtra("flag", flag);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }
        });

        btnPrevious.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = pager.getCurrentItem();

                //현재 위치(position)에서 -1을 해서 position 변경 (첫인덱스면 더이상 이동하지 않음)
                //두번째 매개변수 true:부드럽게 false:효과없음
                pager.setCurrentItem(position-1, true);
            }
        });

        btnNext.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = pager.getCurrentItem();

                //현재 위치(position)에서 +1 을 해서 position 변경 (마지막인덱스면 더이상 이동하지 않음)
                //두번째 매개변수 true:부드럽게 false:효과없음
                pager.setCurrentItem(position+1, true);
            }
        });

        btnClear = (ImageButton) findViewById(R.id.btnClear);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getLayoutInflater(), context, introNum, eventNum, flag);
        pager.setAdapter(adapter);
    }

    public void setParams(String title, String cityName, String eventNum, String introNum, String stadiumNum, StadiumItem[] stadiumItems, String imgURL) {
        this.title = title;
        this.cityName = cityName;
        this.eventNum = eventNum;
        this.introNum = introNum;
        this.stadiumNum = stadiumNum;
        this.stadiumItems = stadiumItems;
        this.imgURL = imgURL;
    }

    public void set() {
        dialogTitle.setText(" " + title);
        dialogStadiumInfo.setText("경기장 안내" + " (" + stadiumNum + "개)");

        ListView listView = (ListView) findViewById(R.id.stadiumList);

        ArrayList arrayList = new ArrayList();
        for(int i=0; i<Integer.parseInt(stadiumNum); i++) {
            arrayList.add(stadiumItems[i]);
        }

        StadiumItemAdapter adapter = new StadiumItemAdapter(context, arrayList);
        listView.setAdapter(adapter);
    }
}