package com.example.d_bug.chungbukapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//ArrayList에 저장된 Item 타입의 객체들을 인자로 받아 종목 관광 음식 숙박 Item을 구별하여 리사이클러 뷰를 생성
//종목(EventItem) 관광(TripItem) 음식(FoodItem) 숙박(LodgmentItem)은 각각 다른 타입의 객체이고 서로 다른 필드(맴버 변수)를 가짐
//네 가지 Item 타입의 카드 뷰 레이아웃 형식은 같으므로 네 타입의 객체가 같은 ViewHolder를 사용
//네 가지 Item 타입의 카드 뷰 클릭 리스너에 따라 서로 다른 Item 타입의 맴버 변수를 다루어야 하므로 onBindViewHolder()에서 타입을 구별하여 클릭 리스너 구현

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private ArrayList items;
    private int fragmentPosition;

    private int lastPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        CustomLayout customLayout;
        TextView textView;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            customLayout = (CustomLayout) view.findViewById(R.id.linearLayout);
            textView = (TextView) view.findViewById(R.id.imageTitle);
            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }

    public MyAdapter(ArrayList itmes, Context context, int fragmentPosition) {
        this.context = context;
        this.items = itmes;
        this.fragmentPosition = fragmentPosition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        ViewHolder holder;

        //종목 부분은 다른 카드 뷰 사용을 위해 플래그로 구별하여 뷰 홀더를 인플레이션
        if (fragmentPosition == 1) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview2, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        }
        holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        //ArrayList에 저장된 객체가 EventItem 타입일 경우
        if (items.get(position).getClass().getName().contains("EventItem")) {
            final EventItem item = (EventItem) items.get(position);

            Picasso.with(context)
                    .load(item.imgURL)
                    .resize(150, 120)
                    .into(holder.customLayout);
            holder.textView.setText(item.title);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EventDialog eventDialog = new EventDialog(context);
                    eventDialog.setParams(item.title, item.cityName, item.eventNum, item.introNum, item.stadiumNum, item.stadiumInfo, item.imgURL);
                    eventDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    eventDialog.show();
                    eventDialog.set();
                    eventDialog.btnClear.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            eventDialog.cancel();
                        }
                    });
                }
            });
        }
        //ArrayList에 저장된 객체가 TripItem 타입일 경우
        else if (items.get(position).getClass().getName().contains("TripItem")) {
            final TripItem item = (TripItem) items.get(position);

            //이미지URL 구별
            String imgURL = "";
            if(item.imgURL.contains("thumb")) {
                imgURL = "http://tour.chungbuk.go.kr/upload/www/tour" + item.imgURL;
            } else {
                imgURL = item.imgURL;
            }

            Picasso.with(context)
                    .load(imgURL)
                    .resize(170, 160)
                    .into(holder.customLayout);
            holder.textView.setText(item.title);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final TripDialog tripDialog = new TripDialog(context);
                    tripDialog.setParams(item.title, item.tel, item.intro, item.cityName, item.address,
                                         item.time, item.siteURL, item.facility, item.latitude, item.longitude);
                    tripDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    tripDialog.show();
                    tripDialog.set();
                    tripDialog.btnClear.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            tripDialog.cancel();
                        }
                    });
                }
            });
        }
        //ArrayList에 저장된 객체가 FoodItem 타입일 경우
        else if(items.get(position).getClass().getName().contains("FoodItem")) {
            final FoodItem item = (FoodItem) items.get(position);
            String imgURL = "http://tour.chungbuk.go.kr/upload/bbs/00000005/2016/" + item.imgURL;

            Picasso.with(context)
                    .load(imgURL)
                    .resize(170, 160)
                    .into(holder.customLayout);
            holder.textView.setText(item.title);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final FoodDialog foodDialog = new FoodDialog(context);
                    foodDialog.setParams(item.title, item.tel, item.intro, item.cityName, item.address,
                            item.time, item.siteURL, item.latitude, item.longitude, item.mainMenu, item.menuInfo);
                    foodDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    foodDialog.show();
                    foodDialog.set();
                    foodDialog.btnClear.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            foodDialog.cancel();
                        }
                    });
                }
            });
        }
        //ArrayList에 저장된 객체가 LodgmentItem 타입일 경우
        else if(items.get(position).getClass().getName().contains("LodgmentItem")) {
            final LodgmentItem item = (LodgmentItem) items.get(position);
            String imgURL = item.imgURL;

            Picasso.with(context)
                    .load(imgURL)
                    .resize(170, 160)
                    .into(holder.customLayout);
            holder.textView.setText(item.title);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final LodgmentDialog lodgmentDialog = new LodgmentDialog(context);
                    lodgmentDialog.setParams(item.title, item.tel, item.intro, item.cityName, item.address,
                            item.time, item.siteURL, item.latitude, item.longitude, item.room, item.breakfast,
                            item.creditCard, item.pickUp, item.parkingLot, item.facility);
                    lodgmentDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    lodgmentDialog.show();
                    lodgmentDialog.set();
                    lodgmentDialog.btnClear.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            lodgmentDialog.cancel();
                        }
                    });
                }
            });
        }

        setAnimation(holder.customLayout, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //리사이클러 뷰 이미지 생성시 애니메이션 효과 부여
    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}