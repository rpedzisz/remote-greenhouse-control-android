package com.example.rafal.szklarniaapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OpcjeActivity extends AppCompatActivity {
    int czas = 0;
    int minuty = 0;
    int sekundy = 0;
    EditText minutes;
    EditText seconds;
    Button zapisz_czestotliwosc;
    ustawczestotliwoscpomiarow ustaw;
    int typsortowania = 0;
    int sposobsortowania = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //widoki
        setContentView(R.layout.activity_opcje);
        minutes = findViewById(R.id.minutes);
        seconds = findViewById(R.id.seconds);
        zapisz_czestotliwosc = findViewById(R.id.zapisz_czestotliwosc);
        RadioGroup radiogroup_typ = findViewById(R.id.radiogroup_typ);
        RadioGroup radiogroup_sposob = findViewById(R.id.radiogroup_sposob);
        Button sortuj = findViewById(R.id.sortuj_button);












        zapisz_czestotliwosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ustawienie cżęstotliwości pomiarów
                //sprawdzenie czy dane są poprawne

                try{
                    int sekundy_int = Integer.parseInt(seconds.getText().toString());
                    int minuty_int = Integer.parseInt(minutes.getText().toString());

                    if ((sekundy_int < 60)  && ( sekundy_int > 0 ) &&
                            ( minuty_int == 0 )
                            ||(sekundy_int < 60)  && ( sekundy_int >= 0 ) &&
                            ( minuty_int != 0 )){

                        minuty = Integer.parseInt(minutes.getText().toString());
                        sekundy = Integer.parseInt(seconds.getText().toString());
                        czas = minuty * 60 + sekundy;
                        Toast.makeText(OpcjeActivity.this, "Zapisano", Toast.LENGTH_SHORT).show();
                        ustaw = new ustawczestotliwoscpomiarow();
                        ustaw.execute(2);
                    }
                    else
                    {
                        Toast.makeText(OpcjeActivity.this, "Niepoprawne dane", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e){
                    Toast.makeText(OpcjeActivity.this, "Niepoprawne dane", Toast.LENGTH_SHORT).show();
                }


            }
        });

        radiogroup_typ.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int zaznaczone) {
            if (zaznaczone == R.id.atemp_sortuj){
                typsortowania = 0;
            }
            else if (zaznaczone == R.id.btemp_sortuj){
                typsortowania = 1;
            }
            else if (zaznaczone == R.id.ctemp_sortuj){
                typsortowania = 2;
            }
            else if (zaznaczone == R.id.dtemp_sortuj){
                typsortowania = 3;
            }
            else if (zaznaczone == R.id.dwilg_sortuj){
                typsortowania = 4;
            }
            else if (zaznaczone == R.id.egleba_sortuj){
                typsortowania = 5;
            }
            else if (zaznaczone == R.id.fswiatlo_sortuj){
                typsortowania = 6;
            }
            else if (zaznaczone == R.id.gswiatlo_sortuj){
                typsortowania = 7;
            }
            else if (zaznaczone == R.id.data_sortuj){
                typsortowania = 8;
            }
            else if (zaznaczone == R.id.idbazy_sortuj){
                typsortowania = 9;
            }


            }
        });


        radiogroup_sposob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int zaznaczone) {
                if (zaznaczone == R.id.rosnaco_sortuj){
                    sposobsortowania = 0;
                }
                else if (zaznaczone == R.id.malejaco_sortuj){
                    sposobsortowania = 1;
                }


            }
        });

        sortuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Collections.sort(HistoriaActivity.lista_edytowalna, new Comparator<HistoriaObject>() {
                    @Override
                    public int compare(HistoriaObject t1, HistoriaObject t2) {
                        if (typsortowania == 0) {
                            if (sposobsortowania == 0) {
                                return Float.compare(Float.valueOf(t1.getA_temp()), Float.valueOf(t2.getA_temp()));
                            }
                            else if (sposobsortowania == 1){
                                return Float.compare(Float.valueOf(t2.getA_temp()), Float.valueOf(t1.getA_temp()));
                            }
                        }
                        if (typsortowania == 1) {
                            if (sposobsortowania == 0) {
                                return Float.compare(Float.valueOf(t1.getB_temp()), Float.valueOf(t2.getB_temp()));
                            }
                            else if (sposobsortowania == 1){
                                return Float.compare(Float.valueOf(t2.getB_temp()), Float.valueOf(t1.getB_temp()));
                            }
                        }
                        if (typsortowania == 2) {
                            if (sposobsortowania == 0) {
                                return Float.compare(Float.valueOf(t1.getC_temp()), Float.valueOf(t2.getC_temp()));
                            }
                            else if (sposobsortowania == 1){
                                return Float.compare(Float.valueOf(t2.getC_temp()), Float.valueOf(t1.getC_temp()));
                            }
                        }
                        if (typsortowania == 3) {
                            if (sposobsortowania == 0) {
                                return Float.compare(Float.valueOf(t1.getD_temp()), Float.valueOf(t2.getD_temp()));
                            }
                            else if (sposobsortowania == 1){
                                return Float.compare(Float.valueOf(t2.getD_temp()), Float.valueOf(t1.getD_temp()));
                            }
                        }
                        if (typsortowania == 4) {
                            if (sposobsortowania == 0) {
                                return Float.compare(Float.valueOf(t1.getD_wilg()), Float.valueOf(t2.getD_wilg()));
                            }
                            else if (sposobsortowania == 1){
                                return Float.compare(Float.valueOf(t2.getD_wilg()), Float.valueOf(t1.getD_wilg()));
                            }
                        }
                        if (typsortowania == 5) {
                            if (sposobsortowania == 0) {
                                return Float.compare(Float.valueOf(t1.getE_gleba_wilg()), Float.valueOf(t2.getE_gleba_wilg()));
                            }
                            else if (sposobsortowania == 1){
                                return Float.compare(Float.valueOf(t2.getE_gleba_wilg()), Float.valueOf(t1.getE_gleba_wilg()));
                            }
                        }
                        if (typsortowania == 6) {
                            if (sposobsortowania == 0) {
                                return Float.compare(Float.valueOf(t1.getF_swiatlo()), Float.valueOf(t2.getF_swiatlo()));
                            }
                            else if (sposobsortowania == 1){
                                return Float.compare(Float.valueOf(t2.getF_swiatlo()), Float.valueOf(t1.getF_swiatlo()));
                            }
                        }
                        if (typsortowania == 7) {
                            if (sposobsortowania == 0) {
                                return Float.compare(Float.valueOf(t1.getG_swiatlo()), Float.valueOf(t2.getG_swiatlo()));
                            }
                            else if (sposobsortowania == 1){
                                return Float.compare(Float.valueOf(t2.getG_swiatlo()), Float.valueOf(t1.getG_swiatlo()));
                            }
                        }
                        if (typsortowania == 8) {
                            if (sposobsortowania == 0) {
                                return t1.getData().compareTo(t2.getData());
                            }
                            else if (sposobsortowania == 1){
                                return t2.getData().compareTo(t1.getData());
                            }
                        }
                        if (typsortowania == 9) {
                            if (sposobsortowania == 0) {
                                return Float.compare(Float.valueOf(t1.getId()), Float.valueOf(t2.getId()));
                            }
                            else if (sposobsortowania == 1){
                                return Float.compare(Float.valueOf(t2.getId()), Float.valueOf(t1.getId()));
                            }
                        }



                         return 0;
                    }

                });






                HistoriaActivity.historia_adapter.notifyDataSetChanged();
            }
        });





    }

    @Override
    protected void onStart() {
        //przy starcie aktywności uruchomienie async taska odpowiadającego za ustawienie czestotliwości z bazy
        ustaw = new ustawczestotliwoscpomiarow();
        ustaw.execute(1);
        super.onStart();
    }

    public class ustawczestotliwoscpomiarow extends AsyncTask<Integer,Integer,Integer>{
        //async task odpowiadający za ustawienie częstotliwości pomiarów w bazie
        //przy włączeniu aktywności również ustawia obecne dane z bazy
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Integer... parametrustawienia) {
            try {
                DriverManager.setLoginTimeout(1);
                Connection polaczenie = null;
                Statement statement = null;
                //łączenie z bazą
                polaczenie = DriverManager.getConnection(Db_Data.DB_address, Db_Data.DB_User,Db_Data.DB_Pass);
                if (polaczenie != null){
                    statement = polaczenie.createStatement();

                    // przy starcie aktywności
                    if (parametrustawienia[0]== 1) {


                        String sql_select = "SELECT * FROM ustawienia";

                        ResultSet result = statement.executeQuery(sql_select);
                        //ustawienie obecnych danych z bazy
                        if (result.next()) {

                            czas = result.getInt("czestotliwosc_zapisywania_pomiarow");
                            minuty = (int) czas/60;
                            sekundy = (int) czas % 60;
                            result.close();
                        }
                    }
                    //przy naciśnięciu przycisku zapisz czestotliwość pomiarów
                    else if (parametrustawienia[0] == 2){

                        String zapisz_czestotliwosc = "UPDATE ustawienia SET czestotliwosc_zapisywania_pomiarow = \'" + String.valueOf(czas) +"\'";

                        int rezultatupdate = statement.executeUpdate(zapisz_czestotliwosc);


                    }





                    polaczenie.close();
                    statement.close();



                }
            } catch (SQLException e) {

                e.printStackTrace();
            }





        return null;
    }

        @Override
        protected void onPostExecute(Integer o) {
        //ustawienie textfieldów
        minutes.setText(String.valueOf(minuty));
        seconds.setText(String.valueOf(sekundy));

            super.onPostExecute(o);
        }
    }



}
