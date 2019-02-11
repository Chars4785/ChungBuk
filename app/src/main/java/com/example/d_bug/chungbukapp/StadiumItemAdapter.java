package com.example.d_bug.chungbukapp;

//Stadium 타입의 데이터를 다이얼로그 속 리스트뷰에 인플레이션하는 클래스

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class StadiumItemAdapter extends BaseAdapter{
    Intent intent;
    final Context context;

    private ArrayList stadiumItems;

    public StadiumItemAdapter(Context context, ArrayList stadiumItems) {
        this.context = context;
        this.stadiumItems = stadiumItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_stadium, parent, false);
        }

        TextView dialogStadiumName = (TextView) convertView.findViewById(R.id.dialogStadiumName);
        TextView dialogStadiumEvent = (TextView) convertView.findViewById(R.id.dialogStadiumEvent);
        TextView dialogStadiumAddress = (TextView) convertView.findViewById(R.id.dialogStadiumAddress);
        TextView dialogStadiumTel = (TextView) convertView.findViewById(R.id.dialogStadiumTel);
        TextView dialogStadiumSeat = (TextView) convertView.findViewById(R.id.dialogStadiumSeat);
        TextView dialogStadiumParkingLot = (TextView) convertView.findViewById(R.id.dialogStadiumParkingLot);
        Button btnDialogStadiumDirection = (Button) convertView.findViewById(R.id.btnDialogStadiumDirection);
        Button btnDialogStadiumTel = (Button) convertView.findViewById(R.id.btnDialogStadiumTel);

        final StadiumItem stadiumItem = (StadiumItem) stadiumItems.get(position);

        dialogStadiumName.setText(stadiumItem.stadiumName);
        dialogStadiumEvent.setText(stadiumItem.stadiumEvent);
        dialogStadiumAddress.setText(stadiumItem.stadiumAddress);
        dialogStadiumTel.setText(stadiumItem.stadiumTel);
        dialogStadiumSeat.setText(stadiumItem.stadiumSeat);
        dialogStadiumParkingLot.setText(stadiumItem.stadiumParkingLot);

        btnDialogStadiumDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + stadiumItem.stadiumLatitude + "," + stadiumItem.stadiumLongitude));
                context.startActivity(intent);
            }
        });

        btnDialogStadiumTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + stadiumItem.stadiumTel));
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return stadiumItems.size();
    }

    @Override
    public Object getItem(int position) {
        return stadiumItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}