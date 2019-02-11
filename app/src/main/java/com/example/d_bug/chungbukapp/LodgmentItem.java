package com.example.d_bug.chungbukapp;

//숙박엑셀에서 읽어온 데이터를 저장할 객체

public class LodgmentItem {
    String title;
    String tel;
    String intro;
    String cityName;
    String address;
    String time;
    String siteURL;
    String imgURL;
    String latitude;
    String longitude;
    String room;
    String breakfast;
    String creditCard;
    String pickUp;
    String parkingLot;
    String facility;

    public LodgmentItem(String title, String tel, String intro, String cityName,
                        String address, String time, String siteURL, String imgURL,
                        String latitude, String longitude,
                        String room, String breakfast, String creditCard,
                        String pickUp, String parkingLot, String facility) {
        this.title = title;
        this.tel = tel;
        this.intro = intro;
        this.cityName = cityName;
        this.address = address;
        this.time = time;
        this.siteURL = siteURL;
        this.imgURL = imgURL;
        this.latitude = latitude;
        this.longitude = longitude;
        this.room =room;
        this.breakfast = breakfast;
        this.creditCard = creditCard;
        this.pickUp = pickUp;
        this.parkingLot = parkingLot;
        this.facility = facility;
    }
}