package com.example.d_bug.chungbukapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.Calendar;

public class AlarmReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int day1;
        int day2;

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();

        calendar1.set(2017, 9, 20);
        calendar2.set(2017, 8, 15);
        calendar3.set(calendar3.get(Calendar.YEAR), calendar3.get(Calendar.MONTH), calendar3.get(Calendar.DATE));

        day1 = caldate(calendar1,calendar3);
        day2 = caldate(calendar2,calendar3);

        NotificationManager nm = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);

        Intent intentActivity = new Intent(context, SplashActivity.class);
        intentActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentActivity, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.img_launcher)
                .setContentTitle("가슴뛰는 충북 전국체전!")
                .setContentText("전국체전 D" + day1 + "일  \n" + "장애인체전 D" + day2 + "일 (일정보기↓)")
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true)
                .setTicker("충북 전국체전 길라잡이");

        Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
        bigTextStyle.bigText("전국체전 : 2017.10.20 ~ 10.26 (7일간)\n장애인체전 : 2017.09.15 ~ 09.19 (5일간)").setSummaryText("전국체전 D" + day1 + "일  \n" + "장애인체전 D" + day2 + "일");
        builder.setStyle(bigTextStyle);

        Notification notification = builder.build();
        nm.notify(1, notification);
    }

    public int caldate(Calendar cal1, Calendar cal2) {

            Calendar today = cal2; //오늘 날짜
            Calendar dday = cal1;

            long day = dday.getTimeInMillis()/(24*60*60*1000);
            long tday = today.getTimeInMillis()/(24*60*60*1000);
            long count = tday - day;
            return (int) count;
    }
}