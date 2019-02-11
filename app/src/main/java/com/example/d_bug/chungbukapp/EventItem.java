package com.example.d_bug.chungbukapp;

//종목엑셀에서 읽어온 데이터를 저장할 객체

public class EventItem {

    String title = "";
    String cityName = "";
    String imgURL = "";
    String eventNum = "";
    String introNum = "";
    String stadiumNum = "";
    StadiumItem[] stadiumInfo;

    public EventItem(String title, String cityName, String imgURL, String eventNum, String introNum, String stadiumNum, StadiumItem[] stadiumInfo) {

        this.title = title;
        this.cityName = cityName;
        this.imgURL = imgURL;
        this.eventNum = eventNum;
        this.introNum = introNum;
        this.stadiumNum = stadiumNum;
        this.stadiumInfo = stadiumInfo;
    }
}