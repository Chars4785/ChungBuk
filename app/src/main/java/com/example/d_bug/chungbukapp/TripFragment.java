package com.example.d_bug.chungbukapp;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TripFragment extends Fragment {

    int cityNum;               //도시 번호

    Context context;           //메인 액티비티의 Context

    RecyclerView recyclerView;                  //리사이클러 뷰
    RecyclerView.Adapter adapter;               //리사이클러 뷰 어답터
    RecyclerView.LayoutManager layoutManager;   //리사이클러 레이아웃 매니저

    Thread thread;                              //엑셀 데이를 읽을 쓰레드
    Handler handler = new Handler();            //분기한 쓰레드에서 UI 조작을 위한 핸들러

    private AppCompatDialog pDlg;                //쓰레드가 엑셀을 읽을동안 보여질 프로그레스 다이얼로그

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.trip_fragment, container, false);

        context = getActivity();

        //메인 엑티비티에서 넘어온 인자(도시 번호) 받음
        if (getArguments() != null) {
            cityNum = getArguments().getInt("cityNum");
        }

        //리사이클러 뷰 인플레이션
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //TripItem 타입을 저장할 ArrayList 정의
        final ArrayList items = new ArrayList();

        pDlg = new AppCompatDialog(context);
        pDlg.setCancelable(false);
        pDlg.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        pDlg.setContentView(R.layout.progress_dialog);
        pDlg.show();

        //엑셀 데이터를 읽어오는 쓰레드 실행
        //Handler의 post()를 이용하여 메인 쓰레드에서 UI 조작을 수행(UI 조작은 메인 쓰레드에서만 수행 가능)
        thread = new Thread(new Runnable() {
            String title = "";
            String tel = "";
            String intro = "";
            String cityName = "";
            String address = "";
            String time = "";
            String siteURL = "";
            String facility = "";
            String imgURL = "";
            String latitude = "";
            String longitude = "";

            Workbook workbook = null;
            Sheet sheet = null;

            public void getXlsData(int row){
                title = sheet.getCell(1, row).getContents();
                tel = sheet.getCell(0, row).getContents();
                intro = sheet.getCell(2, row).getContents();
                cityName = sheet.getCell(3, row).getContents();
                address = sheet.getCell(4, row).getContents();
                time = sheet.getCell(5, row).getContents();
                siteURL = sheet.getCell(6, row).getContents();
                facility = sheet.getCell(7, row).getContents();
                imgURL = sheet.getCell(10, row).getContents();
                latitude = sheet.getCell(8, row).getContents();
                longitude = sheet.getCell(9, row).getContents();

                items.add(new TripItem(title, tel, intro, cityName, address, time, siteURL, facility, imgURL, latitude, longitude));
            }

            @Override
            public void run() {
                try {
                    InputStream inputStream = getActivity().getBaseContext().getResources().getAssets().open("xls_trip.xls");
                    workbook = Workbook.getWorkbook(inputStream);
                    sheet = workbook.getSheet(0);

                    int rows = sheet.getRows();

                    //엑셀 데이터에서 도시 번호로 각 시와 군을 구별하여 정보를 추출
                    //도시번호
                    //단양군 :  1
                    //제천시 :  2
                    //충주시 :  3
                    //음성군 :  4
                    //진천군 :  5
                    //증평군 :  6
                    //괴산군 :  7
                    //청주시 :  8
                    //보은군 :  9
                    //옥천군 : 10
                    //영동군 : 11
                    switch (cityNum) {
                        case 1:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("단양군")) {
                                    getXlsData(row);
                                }
                            }
                            break;

                        case 2:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("제천시")) {
                                    getXlsData(row);
                                }
                            }
                            break;

                        case 3:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("충주시")) {
                                    getXlsData(row);
                                }
                            }
                            break;

                        case 4:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("음성군")) {
                                    getXlsData(row);
                                }
                            }
                            break;

                        case 5:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("진천군")) {
                                    getXlsData(row);
                                }
                            }
                            break;

                        case 6:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("증평군")) {
                                    getXlsData(row);
                                }
                            }
                            break;

                        case 7:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("괴산군")) {
                                    getXlsData(row);
                                }
                            }
                            break;

                        case 8:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("청주시")) {
                                    getXlsData(row);
                                }
                            }
                            break;

                        case 9:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("보은군")) {
                                    getXlsData(row);
                                }
                            }
                            break;

                        case 10:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("옥천군")) {
                                    getXlsData(row);
                                }
                            }
                            break;

                        case 11:
                            for (int row = 0; row < rows; row++) {
                                if (sheet.getCell(3, row).getContents().equals("영동군")) {
                                    getXlsData(row);
                                }
                            }
                            break;
                    }
                    //쓰레드에서 UI 조작을 수행하기 위해 핸들러 정의
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(layoutManager);

                            adapter = new MyAdapter(items, context, 2);
                            recyclerView.setAdapter(adapter);

                            pDlg.dismiss();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (BiffException e) {
                    e.printStackTrace();
                } finally {
                    workbook.close();
                }

            }
        });

        thread.start();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        thread.interrupt(); //프레그먼트 전환 시 수행중이던 쓰레드 종료
        super.onDestroyView();
    }
}