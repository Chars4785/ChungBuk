package com.example.d_bug.chungbukapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    Intent intent;

    int flag = 0;

    long pressedTime = 0;

    DrawerLayout drawer;               //드로어 레이아웃
    RelativeLayout tabPage;          //기본 화면
    LinearLayout menuPage;             //메뉴 화면
    //메뉴 화면 밖이나 뒤로가기 버튼 클릭시 메뉴 화면 제거를 위한 리스너 정의
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(menuPage)) {
            drawer.closeDrawer(menuPage);
        } else {
            if (pressedTime == 0) {
                Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
                pressedTime = System.currentTimeMillis();
            } else {
                int seconds = (int) (System.currentTimeMillis() - pressedTime);

                if(seconds > 2000) { pressedTime = 0; }
                else { finish(); }
            }
        }
    }

    ImageButton btnOpenMenu;           //메뉴 버튼
    ImageButton btnHome;               //홈 버튼

    TextView subTitle;                 //메인 로고 및 부제목 텍스트 뷰

    Button btnGreeting;                //인사말 버튼
    Button btnSummary;                 //대회개요 버튼
    Button btnSymbol;                  //상징물 버튼
    Button btnOrganizing;              //조직위원회 버튼
    Button btnDirection;               //찾아오는 길 버튼
    ImageButton btnKakao;              //카카오 링크 버튼
    ImageButton btnYoutube;            //유튜브 링크 버튼
    ImageButton btnFacebook;           //페이스북 링크 버튼
    ImageButton btnNaver;              //네이버 링크 버튼

    TextView textManual;               //안내 문구
    Button btnTrans;                   //우상단 전국/장애인 체전 전환 버튼

    ImageButton btnAla;                //노티 알람 버튼
    boolean onOff;                     //알람 버튼 상태

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //드로어 메뉴를 위한 변수 정의
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        tabPage = (RelativeLayout) findViewById(R.id.tabPage);
        menuPage = (LinearLayout) findViewById(R.id.menuPage);

        //상단 액션바(탭) 정의
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        //부제목 텍스트뷰 정의
        subTitle = (TextView) findViewById(R.id.subTitle);
        subTitle.setText("개최 지역"); //실제 구현시에는 메인화면에서 넘어온 문자열을 삽입

        //메뉴 버튼과 홈 버튼의 리스너 정의
        btnOpenMenu = (ImageButton) findViewById(R.id.btnMenu);
        btnOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedTime = 0;
                drawer.openDrawer(menuPage);
            }
        });

        btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedTime = 0;
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
        btnGreeting = (Button) findViewById(R.id.btnGreeting);
        btnGreeting.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnSummary = (Button) findViewById(R.id.btnSummary);
        btnSummary.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnSymbol = (Button) findViewById(R.id.btnSymbol);
        btnSymbol.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnOrganizing = (Button) findViewById(R.id.btnOrganizing);
        btnOrganizing.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnDirection = (Button) findViewById(R.id.btnDirection);
        btnDirection.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnKakao = (ImageButton) findViewById(R.id.btnKakao);
        btnKakao.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnYoutube = (ImageButton) findViewById(R.id.btnYoutube);
        btnYoutube.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnFacebook = (ImageButton) findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        btnNaver = (ImageButton) findViewById(R.id.btnNaver);
        btnNaver.setOnClickListener(new MenuButtonListener(this, drawer, menuPage));

        //안내문구 정의 및 색 지정
        textManual = (TextView) findViewById(R.id.textManual);
        String str = "각 지역별로 개최되는 종목들입니다.\n개최지 지역명을 지도에서 터치하여\n경기 및 관광에 관한 상세정보를 확인하세요.";
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#1d7ec0")), 24, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#1d7ec0")), 51, 59, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textManual.setText(ssb);

        //전환 버튼 정의
        btnTrans = (Button) findViewById(R.id.btnTrans);
        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedTime = 0;
                intent = new Intent(getApplicationContext(), ParaHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        //도시 버튼 리스너 정의
        //도시번호
        //단양군 :  1
        //제천시 :  2
        //충주시 :  3
        //음성군 :  4
        //진천군 :  5
        //증평군 :  6
        //괴산군 :  7
        //청주시 :  8
        //보은군 :  9
        //옥천군 : 10
        //영동군 : 11

        //단양
        Button btnDanyang = (Button) findViewById(R.id.danyaung);
        btnDanyang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 1);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //제천
        Button btnJecheon = (Button) findViewById(R.id.jecheon);
        btnJecheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 2);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //충주
        Button btnChungju = (Button) findViewById(R.id.chungju);
        btnChungju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 3);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //음성
        Button btnEumseong = (Button) findViewById(R.id.eumseong);
        btnEumseong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 4);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //진천
        Button btnJincheon = (Button) findViewById(R.id.jincheon);
        btnJincheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 5);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //증평
        Button btnJeungpyeon = (Button) findViewById(R.id.jeungpyeon);
        btnJeungpyeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 6);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //괴산
        Button btnGeosan = (Button) findViewById(R.id.goesan);
        btnGeosan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 7);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //청주
        Button btnCheongju = (Button) findViewById(R.id.cheongju);
        btnCheongju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 8);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //보은
        Button btnBoeun = (Button) findViewById(R.id.boeun);
        btnBoeun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 9);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //옥천
        Button btnOgcheon = (Button) findViewById(R.id.ogcheon);
        btnOgcheon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 10);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //영동
        Button btnYeongdong = (Button) findViewById(R.id.yeongdong);
        btnYeongdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("city#", 11);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
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