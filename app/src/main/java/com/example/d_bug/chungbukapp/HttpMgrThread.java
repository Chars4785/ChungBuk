package com.example.d_bug.chungbukapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//스플레쉬 액티비티에서 인터넷 검사를 할 때 호출되는 쓰레드

public class HttpMgrThread extends Thread {
    public HttpMgrThread(){
    }
    @Override
    public void run(){
        reqHttp();
    }
    public void reqHttp(){
        URL url = null;
        try{
            url = new URL("http://www.naver.com");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.connect();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}