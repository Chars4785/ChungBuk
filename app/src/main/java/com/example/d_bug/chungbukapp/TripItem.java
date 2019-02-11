package com.example.d_bug.chungbukapp;

//관광엑셀에서 읽어온 데이터를 저장할 객체

public class TripItem {
    String title;
    String tel;
    String intro;
    String cityName;
    String address;
    String time;
    String siteURL;
    String facility;
    String imgURL;
    String latitude;
    String longitude;

    public TripItem(String title, String tel, String intro, String cityName,
                    String address, String time, String siteURL, String facility,
                    String imgURL, String latitude, String longitude) {
        this.title = title;
        this.tel = tel;
        this.intro = intro;
        this.cityName = cityName;
        this.address = address;
        this.time = time;
        this.siteURL = siteURL;
        this.facility = facility;
        this.imgURL = imgURL;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}