package com.example.d_bug.chungbukapp;

//메뉴 버튼 클릭 리스너
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MenuButtonListener implements View.OnClickListener {

    private Context context;
    private DrawerLayout drawer;
    private LinearLayout menu;
    private Intent intent;
    private int flag = 0;

    Class aClass;

    public MenuButtonListener(Context context, DrawerLayout drawer, LinearLayout menu, int flag) {
        this(context, drawer, menu);
        this.flag = flag;
    }

    public MenuButtonListener(Context context, DrawerLayout drawer, LinearLayout menu) {
        this.context = context;
        this.drawer = drawer;
        this.menu = menu;
    }

    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnGreeting :
                if (flag == 1) { aClass = ParaGreetingActivity.class; } else { aClass = GreetingActivity.class; }

                if (context.getClass().getName().equals(aClass.getName())) {
                        drawer.closeDrawer(menu);
                } else {
                        intent = new Intent(context, aClass);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        context.startActivity(intent);
                }
                break;

            case R.id.btnSummary :
                if (flag == 1) { aClass = ParaSummaryActivity.class; } else { aClass = SummaryActivity.class; }

                if(context.getClass().getName().equals(aClass.getName())) {
                    drawer.closeDrawer(menu);
                } else {
                    intent = new Intent(context, aClass);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }
                break;

            case R.id.btnSymbol :
                if (flag == 1) { aClass = ParaSymbolActivity.class; } else { aClass = SymbolActivity.class; }

                if(context.getClass().getName().equals(aClass.getName())) {
                    drawer.closeDrawer(menu);
                } else {
                    intent = new Intent(context, aClass);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    context.startActivity(intent);
                }
                break;

            case R.id.btnOrganizing :
                if(context.getClass().getName().equals(OrganizingActivity.class.getName())) {
                    drawer.closeDrawer(menu);
                } else {
                    intent = new Intent(context, OrganizingActivity.class);
                    intent.putExtra("flag", flag);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    context.startActivity(intent);
                }
                break;

            case R.id.btnDirection :
                if(context.getClass().getName().equals(DirectionActivity.class.getName())) {
                    drawer.closeDrawer(menu);
                } else {
                    intent = new Intent(context, DirectionActivity.class);
                    intent.putExtra("flag", flag);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    context.startActivity(intent);
                }
                break;

            case R.id.btnKakao :
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://story.kakao.com/cb_sports"));
                context.startActivity(intent);
                break;

            case R.id.btnYoutube :
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCI8rBCFE5Wt_p4_UWqQV8Pg"));
                context.startActivity(intent);
                break;

            case R.id.btnFacebook :
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cb21sports/"));
                context.startActivity(intent);
                break;

            case R.id.btnNaver :
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.naver.com/cb-sports"));
                context.startActivity(intent);
                break;
        }
    }
}