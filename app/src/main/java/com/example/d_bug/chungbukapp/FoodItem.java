package com.example.d_bug.chungbukapp;

//음식엑셀에서 읽어온 데이터를 저장할 객체

public class FoodItem {
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
    String mainMenu;
    String menuInfo;

    public FoodItem(String title, String tel, String intro, String cityName,
                    String address, String time, String siteURL, String imgURL,
                    String latitude, String longitude, String mainMenu, String menuInfo) {
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
        this.mainMenu = mainMenu;
        this.menuInfo = menuInfo;
    }
}