package com.example.rafal.szklarniaapp;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


import com.mysql.jdbc.CommunicationsException;

import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;


public  class AktualizujPomiaryService extends IntentService {
//intent service odpowiadający za aktualizowanie danych w tle oraz zapisywania danych do wykresów
    //broadcasty
    public static final String ACTION_AktualizujPomiaryService = "com.example.androidintentservice.RESPONSE";
    public static final String ACTION_RestartujServiceReceiver = "com.example.androidintentservice.RESTART";
/*
    Intent intentResponse = new Intent();
                    intentResponse.setAction(ACTION_AktualizujPomiaryService );
                    intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
                    */
    public AktualizujPomiaryService() {
        super("AktualizujPomiaryService");
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {

        super.onStart(intent, startId);
    }


    @Override
    public void onDestroy() {

        if (MainActivity.czyrestartujactivity == true) {
            Intent broadcastIntent = new Intent(AktualizujPomiaryService.ACTION_RestartujServiceReceiver);
            sendBroadcast(broadcastIntent);

        }
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        //sprawdzenie czy istnieje klasa
        try {
            Class.forName(Db_Data.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            MainActivity.czyrestartujactivity = false;



            e.printStackTrace();
        }

        //connection i statement
        Connection polaczenietryb = null;
        Statement statementtryb = null;

        Connection polaczenie = null;
        Statement statement = null;

        Bundle paczkadanych = new Bundle();

        try {
            //łączenie z bazą
            polaczenie = DriverManager.getConnection(Db_Data.DB_address, Db_Data.DB_User,Db_Data.DB_Pass);
            statement = polaczenie.createStatement();
            if (polaczenie != null){

                String ustawienia = "SELECT * FROM ustawienia" ;
                ResultSet result = statement.executeQuery(ustawienia);
                //sprawdzenie obecnego trybu sterowania
                if (result.next()){
                MainActivity.czyrestartujactivity = true;
                    //dodanie obecnego trybu do bundle
                    paczkadanych.putString("EXTRA_tryb_sterowania",result.getString("tryb_sterowania"));
                }

                polaczenie.close();
                statement.close();
                result.close();
            }
        } catch (SQLException e) {
            MainActivity.czyrestartujactivity = false;
            ZalogujActivity.zalogowano = false;
            Intent zalogujctivity = new Intent(this, ZalogujActivity.class);
            startActivity(zalogujctivity);
            this.stopSelf();
            e.printStackTrace();
        }



        //aktualizacja tabeli biezace_parametry oraz ArrayList
        try {
            polaczenie = DriverManager.getConnection(Db_Data.DB_address, Db_Data.DB_User,Db_Data.DB_Pass);
            if (polaczenie != null){
                statement = polaczenie.createStatement();

                String parametry = "SELECT * FROM biezace_parametry" ;

                ResultSet result = statement.executeQuery(parametry);

                if (result.next()){
                    MainActivity.czyrestartujactivity = true;

                    if (WykresyRzeczywisteActivity.maxdanych <= WykresyRzeczywisteActivity.lista_wykresy_realtime.size())
                    {
                        WykresyRzeczywisteActivity.lista_wykresy_realtime.remove(0);
                    }
                    else
                    {
                        HistoriaObject obiekt = new HistoriaObject(Calendar.getInstance().getTime(),
                                "0",result.getString("a_temp"),
                                result.getString("b_temp"),
                                result.getString("c_temp"),
                                result.getString("d_temp"),
                                result.getString("d_wilg"),
                                result.getString("e_wilg_gleba"),
                                result.getString("f_swiatlo"),
                                result.getString("g_swiatlo"));
                        WykresyRzeczywisteActivity.lista_wykresy_realtime.add(obiekt);
                    }

                    Intent intentResponse = new Intent();
                    intentResponse.setAction(ACTION_AktualizujPomiaryService );
                    intentResponse.addCategory(Intent.CATEGORY_DEFAULT);

                    //dodanie danych do bundle
                    paczkadanych.putFloat("EXTRA_a_temp",result.getFloat("a_temp"));
                    paczkadanych.putFloat("EXTRA_b_temp",result.getFloat("b_temp"));
                    paczkadanych.putFloat("EXTRA_c_temp",result.getFloat("c_temp"));
                    paczkadanych.putFloat("EXTRA_d_temp",result.getFloat("d_temp"));
                    paczkadanych.putInt("EXTRA_d_wilg",result.getInt("d_wilg"));
                    paczkadanych.putInt("EXTRA_e_wilg_gleba",result.getInt("e_wilg_gleba"));
                    paczkadanych.putInt("EXTRA_f_swiatlo",result.getInt("f_swiatlo"));
                    paczkadanych.putInt("EXTRA_g_swiatlo",result.getInt("g_swiatlo"));
                    paczkadanych.putString("EXTRA_went",result.getString("went"));
                    paczkadanych.putString("EXTRA_serwo",result.getString("serwo"));
                    paczkadanych.putString("EXTRA_zarowka1",result.getString("zarowka1"));
                    paczkadanych.putString("EXTRA_zarowka2",result.getString("zarowka2"));
                    paczkadanych.putString("EXTRA_zarowka3",result.getString("zarowka3"));
                    paczkadanych.putString("EXTRA_zarowka4",result.getString("zarowka4"));
                    paczkadanych.putString("EXTRA_grzalka",result.getString("grzalka"));
                    paczkadanych.putString("EXTRA_pompa",result.getString("pompa"));

                    //wysyłanie broadcastu z danymi
                    intentResponse.putExtras(paczkadanych);
                    sendBroadcast(intentResponse);

                    polaczenie.close();
                    statement.close();
                    result.close();
                }
            }
        } catch (SQLException e) {
            MainActivity.czyrestartujactivity = false;
            ZalogujActivity.zalogowano = false;

            e.printStackTrace();
        }

        //opóźnienie zakończenia usługi
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
