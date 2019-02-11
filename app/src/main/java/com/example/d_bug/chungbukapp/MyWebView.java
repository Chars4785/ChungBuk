package com.example.d_bug.chungbukapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MyWebView extends AppCompatActivity {

    private WebView mWebView;

    Intent intent;

    int flag = 0;
    int key = 0;

    DrawerLayout drawer;               //드로어 레이아웃
    RelativeLayout tabPage;          //기본 화면
    LinearLayout menuPage;             //메뉴 화면
    //메뉴 화면 밖이나 뒤로가기 버튼 클릭시 메뉴 화면 제거를 위한 리스너 정의
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(menuPage)) {
            drawer.closeDrawer(menuPage);
        } else {
            super.onBackPressed();
        }
    }

    ImageButton btnOpenMenu;           //메뉴 버튼
    ImageButton btnHome;               //홈 버튼

    ImageView logo;                     //메인로고
    TextView subTitle;                 //부제목 텍스트 뷰

    Button btnGreeting;                //인사말 버튼
    Button btnSummary;                 //대회개요 버튼
    Button btnSymbol;                  //상징물 버튼
    Button btnOrganizing;              //조직위원회 버튼
    Button btnDirection;               //찾아오는 길 버튼
    ImageButton btnKakao;              //카카오 링크 버튼
    ImageButton btnYoutube;            //유튜브 링크 버튼
    ImageButton btnFacebook;           //페이스북 링크 버튼
    ImageButton btnNaver;              //네이버 링크 버튼

    ImageButton btnAla;                //노티 알람 버튼
    boolean onOff;                     //알람 버튼 상태

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        //드로어 메뉴를 위한 변수 정의
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        tabPage = (RelativeLayout) findViewById(R.id.tabPage);
        menuPage = (LinearLayout) findViewById(R.id.menuPage);

        //상단 액션바(탭) 정의
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        //메뉴 버튼과 홈 버튼의 리스너 정의
        btnOpenMenu = (ImageButton) findViewById(R.id.btnMenu);
        btnOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(menuPage);
            }
        });

        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        //노티 버튼 상태 정의 및 저장
        btnAla = (ImageButton) findViewById(R.id.btnAla);

        SharedPreferences save = getSharedPreferences("SwitchSave", MODE_PRIVATE);
        String state = save.getString("state", "true");
        if(state.equals("true")){
            btnAla.setImageResource(R.drawable.img_on);
            onOff = true;
            alarm_on(getApplicationContext());
        }else{
            btnAla.setImageResource(R.drawable.img_off);
            onOff = false;
            alarm_off(getApplicationContext());
        }

        btnAla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onOff){
                    alarm_off(getApplicationContext());
                    btnAla.setImageResource(R.drawable.img_off);
                    onOff = false;
                    SharedPreferences saveSwitch = getSharedPreferences("SwitchSave", MODE_PRIVATE);
                    SharedPreferences.Editor editor = saveSwitch.edit();
                    editor.putString("state", "false");
                    editor.commit();
                }else{
                    alarm_on(getApplicationContext());
                    btnAla.setImageResource(R.drawable.img_on);
                    onOff = true;
                    SharedPreferences saveSwitch = getSharedPreferences("SwitchSave", MODE_PRIVATE);
                    SharedPreferences.Editor editor = saveSwitch.edit();
                    editor.putString("state", "true");
                    editor.commit();
                }
            }
        });

        //메뉴탭 버튼 리스너 정의
        flag = getIntent().getIntExtra("flag", 0);

        btnGreeting = (Button) findViewById(R.id.btnGreeting);
        btnGreeting.setOnClickListener(new MenuButtonListener(this, drawer, menuPage, flag));

        btnSummary = (Button) findViewById(R.id.btnSummary);
        btnSummary.setOnClickListener(new MenuButtonListener(this, drawer, menuPage, flag));

        btnSymbol = (Button) findViewById(R.id.btnSymbol);
        btnSymbol.setOnClickListener(new MenuButtonListener(this, drawer, menuPage, flag));

        btnOrganizing = (Button) findViewById(R.id.btnOrganizing);
        btnOrganizing.setOnClickListener(new MenuButtonListener(this, drawer, menuPage, flag));

        btnDirection = (Button) findViewById(R.id.btnDirection);
        btnDirection.setOnClickListener(new MenuButtonListener(this, drawer, menuPage, flag));

        btnKakao = (ImageButton) findViewById(R.id.btnKakao);
        btnKakao.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnYoutube = (ImageButton) findViewById(R.id.btnYoutube);
        btnYoutube.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnFacebook = (ImageButton) findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnNaver = (ImageButton) findViewById(R.id.btnNaver);
        btnNaver.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        mWebView = (WebView)findViewById(R.id.webView);

        //웹뷰 정의 및 로드
        key = getIntent().getIntExtra("key", 0);

        //상당 메인로고 정의
        logo = (ImageView) findViewById(R.id.logo);
        if(flag == 1) {
            logo.setImageResource(R.drawable.img_para_logo);
        }

        //부제목 텍스트뷰 정의
        subTitle = (TextView) findViewById(R.id.subTitle);
        if (key == 83 || key == 144) {
            subTitle.setText("경기일정");
        } else {
            subTitle.setText("행사일정");
        }

        if (flag == 1) {
            mWebView.loadUrl("http://2017sportsp.chungbuk.go.kr/paragame/contents.do?key=" + key);
            mWebView.setWebViewClient(new MyWebViewClient());
        } else {
            mWebView.loadUrl("http://2017sports.chungbuk.go.kr/www/contents.do?key=" + key);
            mWebView.setWebViewClient(new MyWebViewClient());
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }

    public void alarm_on(Context context){
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceive.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 1, intent, 0);

        Calendar calendar = Calendar.getInstance();

        if (calendar.get(Calendar.HOUR_OF_DAY) < 9) {
            //당일 아침 9시10분에 처음 시작해서, 24시간 마다 푸시
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 9, 10, 00);
            am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, sender);
        } else {
            //내일 아침 9시10분에 처음 시작해서, 24시간 마다 푸시
            calendar.add(Calendar.DATE, 1);
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 9, 10, 00);
            am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, sender);
        }
    }

    public void alarm_off(Context context){
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceive.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 1, intent, 0);
        am.cancel(sender);
    }
}

