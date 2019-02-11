package com.example.d_bug.chungbukapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private Context context;

    private int intro;
    private int count;
    private String eventNum;
    private int flag;

    public ViewPagerAdapter(LayoutInflater inflater, Context context, String introNum, String eventNum, int flag) {
        this.inflater = inflater;
        this.context = context;

        this.count = Integer.parseInt(introNum);
        this.eventNum = eventNum;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    //ViewPager가 현재 보여질 Item(View객체)들을 생성할 때 자동으로 호출
    @Override
    public Object instantiateItem(View container, int position) {
        View view = inflater.inflate(R.layout.view_pager, null);
        CustomLayout viewPager = (CustomLayout) view.findViewById(R.id.viewPager);

        String imgURL = "";
        intro = position + 1;
        if (flag == 1) {
            imgURL = "http://2017sportsp.chungbuk.go.kr/DATA/sport/entries/para_" + eventNum + "_" + intro + ".jpg";
        } else {
            imgURL = "http://2017sports.chungbuk.go.kr/DATA/sport/entries/" + eventNum + "_" + intro + ".jpg";
        }
        Picasso.with(context)
                .load(imgURL)
                .resize(300, 400)
                .transform(new RoundedTransfrom(12, 0))
                .into(viewPager);

        ((ViewPager) container).addView(view);

        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        //세번째 파라미터가 View 객체 이지만 데이터 타입이 Object여서 형변환 실시
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == obj;
    }

}