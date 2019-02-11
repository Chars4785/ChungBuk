package com.example.d_bug.chungbukapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

//ViewHolder 타입 holder변수로 레이아웃에 접근 불가능 함
//Picasso라이브러리를 이용해 레이아웃의 background 속성을 설정하기 위해 CustomLayout 정의
//Picasso.with(context).load(imgURL).into(holder.customLayout);

public class CustomLayout extends LinearLayout implements Target {

    public CustomLayout(Context context) {
        super(context);
    }
    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        setBackground(new BitmapDrawable(getResources(), bitmap));
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
}