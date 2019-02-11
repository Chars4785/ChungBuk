package com.example.d_bug.chungbukapp;

//경기장 정보 클래스

public class StadiumItem {
    String stadiumName;
    String stadiumEvent;
    String stadiumAddress;
    String stadiumLatitude;
    String stadiumLongitude;
    String stadiumTel;
    String stadiumSeat;
    String stadiumParkingLot;

    public StadiumItem(String stadiumName, String stadiumEvent, String stadiumTel, String stadiumAddress, String stadiumLatitude, String stadiumLongitude,
                       String stadiumSeat, String stadiumParkingLot) {
        this.stadiumName =  stadiumName;
        this.stadiumEvent = stadiumEvent;
        this.stadiumAddress = stadiumAddress;
        this.stadiumLatitude = stadiumLatitude;
        this.stadiumLongitude = stadiumLongitude;
        this.stadiumTel = stadiumTel;
        this.stadiumSeat = stadiumSeat;
        this.stadiumParkingLot = stadiumParkingLot;
    }
}