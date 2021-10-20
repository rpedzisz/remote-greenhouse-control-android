package com.example.rafal.szklarniaapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SterowanieActivity extends AppCompatActivity {

    Switch sterowanie;

    Button zapisz_auto;
    ConstraintLayout layout;
    Steruj steruj;
    View view_manual;
    View view_auto;
    static int obecnytryb = 1;


//przyciski manual
    SeekBar wentylatory_zmiana;
    ToggleButton serwo_zmiana;
    ToggleButton zarowka1_zmiana;
    ToggleButton zarowka2_zmiana;
    ToggleButton zarowka3_zmiana;
    ToggleButton zarowka4_zmiana;
    ToggleButton pompa_zmiana;
    ToggleButton grzalka_zmiana;

//edit text auto i radio
    EditText temperatura_zmiana;
    EditText wilgotnosc_zmiana;
    EditText jasnosc_zmiana;
    RadioGroup radiogroup_trybnaswietlenia_zmiana;
    RadioButton naswietlenie_stale;
    RadioButton naswietlenie_czunik;
    RadioButton naswietlenie_przedzial;
    EditText czujnik_zmierzchu_zmiana;
    EditText godzina_naswietlenia_od_zmiana;
    EditText godzina_naswietlenia_do_zmiana;
    EditText podlewanie_zmiana;
    EditText godzina_podlewania_od_zmiana;
    EditText godzina_podlewania_do_zmiana;
    LinearLayout naswietlenie_czujnikzmierzchu;
    LinearLayout naswietlenie_przedzialczasowy;

    //dane manual
    int went = 0;
    int serwo = 0;
    int zarowka1 = 0;
    int zarowka2 = 0;
    int zarowka3 = 0;
    int zarowka4 = 0;
    int pompa = 0;
    int grzalka = 0;


    //dane auto
    float temperatura = 1.0F;
    int wilgotnosc = 0;

    int jasnosc = 0;
    int tryb_naswietlenia = 0;
    int czujnik_zmierzchu = 0; //lx
    int godzina_naswietlania_od = 6;
    int godzina_naswietlania_do = 20;

    int wilgotnosc_gleby = 0;
    int godzina_podlewania_od = 12;
    int godzina_podlewania_do = 20;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sterowanie);
        sterowanie = findViewById(R.id.sterowanieSwitch);



        layout =  (ConstraintLayout) findViewById(R.id.layout_sterowania);

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        view_manual = inflater.inflate(R.layout.activity_sterowanie_manual, null);
        view_auto = inflater.inflate(R.layout.activity_sterowanie_auto, null);
        layout.addView(view_manual);
        layout.addView(view_auto);
        view_auto.setVisibility(View.GONE);
        view_manual.setVisibility(View.GONE);


        //tu obiekty auto
        temperatura_zmiana = findViewById(R.id.temp_edit);
        wilgotnosc_zmiana = findViewById(R.id.wilg_edit);

        jasnosc_zmiana = findViewById(R.id.jasnosc_edit);
        radiogroup_trybnaswietlenia_zmiana = findViewById(R.id.radiogroup_trybnaswietlenia);
        naswietlenie_stale = findViewById(R.id.naswietlenie_stale);
        naswietlenie_czunik = findViewById(R.id.naswietlenie_czunik);
        naswietlenie_przedzial = findViewById(R.id.naswietlenie_przedzial);
        czujnik_zmierzchu_zmiana = findViewById(R.id.zmierch_edit);
        godzina_naswietlenia_od_zmiana = findViewById(R.id.lampy_od_edit);
        godzina_naswietlenia_do_zmiana = findViewById(R.id.lampy_do_edit);


        podlewanie_zmiana = findViewById(R.id.podlewanie_edit);

        godzina_podlewania_od_zmiana = findViewById(R.id.pompa_od_edit);
        godzina_podlewania_do_zmiana = findViewById(R.id.pompa_do_edit);

        zapisz_auto = findViewById(R.id.zapisz_auto);

        naswietlenie_czujnikzmierzchu = findViewById(R.id.layout_naswietlenie_zmierzchu);
        naswietlenie_przedzialczasowy = findViewById(R.id.layout_naswietlenie_przedzial);
        naswietlenie_czujnikzmierzchu.setVisibility(View.GONE);
        naswietlenie_przedzialczasowy.setVisibility(View.GONE);



        //tu obiekty manual
        wentylatory_zmiana = (SeekBar) findViewById(R.id.wentylatory_zmiana);
        serwo_zmiana = findViewById(R.id.serwo_zmiana);
        zarowka1_zmiana = findViewById(R.id.zarowka1_zmiana);
        zarowka2_zmiana = findViewById(R.id.zarowka2_zmiana);
        zarowka3_zmiana = findViewById(R.id.zarowka3_zmiana);
        zarowka4_zmiana = findViewById(R.id.zarowka4_zmiana);
        pompa_zmiana = findViewById(R.id.pompa_zmiana);
        grzalka_zmiana = findViewById(R.id.grzalka_zmiana);







        wentylatory_zmiana.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            went = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                steruj = new Steruj();
                steruj.execute(3);
            }
        });



        serwo_zmiana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean czyjestwcisniete) {
                if(czyjestwcisniete == true){
                    serwo  = 1;
                }
                else
                {
                    serwo = 0;
                }
                steruj = new Steruj();
                steruj.execute(3);


            }
        });


        zarowka1_zmiana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean czyjestwcisniete) {
                if(czyjestwcisniete == true){
                    zarowka1  = 1;
                }
                else
                {
                    zarowka1 = 0;
                }
                steruj = new Steruj();
                steruj.execute(3);


            }
        });

        zarowka2_zmiana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean czyjestwcisniete) {
                if(czyjestwcisniete == true){
                    zarowka2  = 1;
                }
                else
                {
                    zarowka2 = 0;
                }
                steruj = new Steruj();
                steruj.execute(3);


            }
        });

        zarowka3_zmiana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean czyjestwcisniete) {
                if(czyjestwcisniete == true){
                    zarowka3  = 1;
                }
                else
                {
                    zarowka3 = 0;
                }
                steruj = new Steruj();
                steruj.execute(3);


            }
        });

        zarowka4_zmiana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean czyjestwcisniete) {
                if(czyjestwcisniete == true){
                    zarowka4  = 1;
                }
                else
                {
                    zarowka4 = 0;
                }
                steruj = new Steruj();
                steruj.execute(3);


            }
        });

        pompa_zmiana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean czyjestwcisniete) {
                if(czyjestwcisniete == true){
                    pompa  = 1;
                }
                else
                {
                    pompa = 0;
                }
                steruj = new Steruj();
                steruj.execute(3);


            }
        });

        grzalka_zmiana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean czyjestwcisniete) {
                if(czyjestwcisniete == true){
                    grzalka  = 1;
                }
                else
                {
                    grzalka = 0;
                }
                steruj = new Steruj();
                steruj.execute(3);


            }
        });

        radiogroup_trybnaswietlenia_zmiana.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int zaznaczone) {
                if (zaznaczone == R.id.naswietlenie_stale){
                    tryb_naswietlenia = 0;
                    naswietlenie_czujnikzmierzchu.setVisibility(View.GONE);
                    naswietlenie_przedzialczasowy.setVisibility(View.GONE);
                }
                else if (zaznaczone == R.id.naswietlenie_czunik){
                    tryb_naswietlenia = 1;
                    naswietlenie_czujnikzmierzchu.setVisibility(View.VISIBLE);
                    naswietlenie_przedzialczasowy.setVisibility(View.GONE);
                }
                else if (zaznaczone == R.id.naswietlenie_przedzial){
                    naswietlenie_czujnikzmierzchu.setVisibility(View.GONE);
                    naswietlenie_przedzialczasowy.setVisibility(View.VISIBLE);
                    tryb_naswietlenia = 2;
                }



            }
        });



        zapisz_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean danepoprawne = false;

                try {
                    temperatura =  Float.parseFloat(temperatura_zmiana.getText().toString());
                    wilgotnosc =  Integer.parseInt(wilgotnosc_zmiana.getText().toString());
                    jasnosc =  Integer.parseInt(jasnosc_zmiana.getText().toString());
                    czujnik_zmierzchu = Integer.parseInt(czujnik_zmierzchu_zmiana.getText().toString());
                    godzina_naswietlania_od = Integer.parseInt(godzina_naswietlenia_od_zmiana.getText().toString());
                    godzina_naswietlania_do = Integer.parseInt(godzina_naswietlenia_do_zmiana.getText().toString());
                    wilgotnosc_gleby = Integer.parseInt(podlewanie_zmiana.getText().toString());
                    godzina_podlewania_od = Integer.parseInt(godzina_podlewania_od_zmiana.getText().toString());
                    godzina_podlewania_do = Integer.parseInt(godzina_podlewania_do_zmiana.getText().toString());


                    if (
                            (21 <= temperatura && temperatura <= 35)&&
                            (50 <=wilgotnosc && wilgotnosc <= 95) &&
                            (0 <= jasnosc && jasnosc <= 3600) &&
                            (0 <= wilgotnosc_gleby && wilgotnosc_gleby <= 100) &&

                                    (godzina_naswietlania_od < godzina_naswietlania_do) &&
                                    (1 <= godzina_naswietlania_od && godzina_naswietlania_od <= 24) &&
                                    (1 <= godzina_naswietlania_do && godzina_naswietlania_do <= 24) &&

                                        (godzina_podlewania_od < godzina_podlewania_do) &&
                                        (1 <= godzina_podlewania_od && godzina_naswietlania_od <= 24) &&
                                        (1 <= godzina_podlewania_do && godzina_naswietlania_do <= 24)

                            )
                    {

                        danepoprawne = true;
                    }
                    else
                    {
                        danepoprawne = false;
                    }









                }catch(Exception e)
                {
                    danepoprawne = false;
                }





                if (danepoprawne == true){
                    Toast.makeText(SterowanieActivity.this, "Dane Poprawne - Zapisano", Toast.LENGTH_SHORT).show();
                    steruj = new Steruj();
                    steruj.execute(4);
                }
                else
                {
                    Toast.makeText(SterowanieActivity.this, "Dane Niepoprawne", Toast.LENGTH_SHORT).show();
                }





            }
        });

        sterowanie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean czyjestnacisniete) {
            //lewa pozycja manual
                if (czyjestnacisniete == false){
                    obecnytryb = 0;
                    //zmiana widoków
                    view_auto.setVisibility(View.GONE);
                    view_manual.setVisibility(View.VISIBLE);
                    steruj = new Steruj();
                    steruj.execute(obecnytryb);

                }
                //prawa pozycja auto
                else
                {
                    obecnytryb = 1;
                    view_auto.setVisibility(View.VISIBLE);
                    view_manual.setVisibility(View.GONE);
                    steruj = new Steruj();
                    steruj.execute(obecnytryb);

                }


            }
        });



    }//koniec OnCreate

    @Override
    protected void onStart() {
        steruj = new Steruj();
        steruj.execute(-1);
        super.onStart();
    }

    public void onBackPressed() {
        finish();
    }
    public class Steruj extends AsyncTask<Integer,Integer,Integer>{



        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Integer doInBackground(Integer... ints) {
            Connection polaczenie = null;
            Statement statement = null;
            PreparedStatement preparedstatement;
            long startTime = System.currentTimeMillis();

            try {
                Class.forName(Db_Data.JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                Log.i("SterowanieActivity", "Błąd brak klasy");
                e.printStackTrace();
            }


            try {
                polaczenie = DriverManager.getConnection(Db_Data.DB_address, Db_Data.DB_User,Db_Data.DB_Pass);
                polaczenie.setAutoCommit(false);
                if (polaczenie != null){

                    statement = polaczenie.createStatement();

                    int trybdozmiany = ints[0];
                    // inicjalizacja obecnymi danymi
                    if(trybdozmiany == -1){ //inicjalizowanie danymi on create
                        //get obecny tryb
                        //select pól z tablicy
                        //ustaw tryb

                        String sprawdztryb = "SELECT * from ustawienia" ;

                        ResultSet result = statement.executeQuery(sprawdztryb);

                        if (result.next()){

                            String trybsterowania = result.getString("tryb_sterowania");

                            if (trybsterowania.equals("m") || trybsterowania.equals("0")) {
                                obecnytryb = 0;
                            }
                            else if (trybsterowania.equals("a") || trybsterowania.equals("1")){
                                obecnytryb = 1;
                            }


                        }

                        if(obecnytryb == 0){
                            String selectmanual = "SELECT * FROM manual";
                            ResultSet select_m = statement.executeQuery(selectmanual);
                            if(select_m.next()){

                                went = select_m.getInt("went");
                                serwo = select_m.getInt("serwo");
                                zarowka1 = select_m.getInt("zarowka1");
                                zarowka2 = select_m.getInt("zarowka2");
                                zarowka3 = select_m.getInt("zarowka3");
                                zarowka4 = select_m.getInt("zarowka4");
                                pompa = select_m.getInt("pompa");
                                grzalka = select_m.getInt("grzalka");
                            }

                        }

                        else if(obecnytryb == 1){
                            String selectauto = "SELECT * FROM auto";
                            ResultSet select_a = statement.executeQuery(selectauto);


                            if(select_a.next()){
                                temperatura = select_a.getFloat("temperatura");
                                wilgotnosc = select_a.getInt("wilgotnosc");
                                jasnosc = select_a.getInt("jasnosc");
                                tryb_naswietlenia = select_a.getInt("tryb_naswietlania");
                                czujnik_zmierzchu = select_a.getInt("czujnik_zmierzchu");
                                godzina_naswietlania_od = select_a.getInt("godzina_naswietlania_od");
                                godzina_naswietlania_do = select_a.getInt("godzina_naswietlania_do");

                                wilgotnosc_gleby = select_a.getInt("wilgotnosc_gleby");
                                godzina_podlewania_od = select_a.getInt("godzina_podlewania_od");
                                godzina_podlewania_do = select_a.getInt("godzina_podlewania_do");







                            }

                        }

                    }



                    if (trybdozmiany == 1  || trybdozmiany == 0) { // zmiana trybu na manual lub auto

                        String ustawtryb = "UPDATE ustawienia SET tryb_sterowania = ?";
                        preparedstatement = polaczenie.prepareStatement(ustawtryb);
                        preparedstatement.setInt(1, trybdozmiany);
                        int rezultatupdate = preparedstatement.executeUpdate();

                        if(trybdozmiany == 0){
                            //tu select obecnych danych trybu manual
                            String selectmanual = "SELECT * FROM manual";
                            ResultSet select_m = statement.executeQuery(selectmanual);

                            if(select_m.next()){

                                went = select_m.getInt("went");
                                serwo = select_m.getInt("serwo");
                                zarowka1 = select_m.getInt("zarowka1");
                                zarowka2 = select_m.getInt("zarowka2");
                                zarowka3 = select_m.getInt("zarowka3");
                                zarowka4 = select_m.getInt("zarowka4");
                                pompa = select_m.getInt("pompa");
                                grzalka = select_m.getInt("grzalka");

                            }




                        }
                        else if (trybdozmiany == 1)
                        {   //tu select obecnych danych trybu auto
                            String selectauto = "SELECT * FROM auto";
                            ResultSet select_a = statement.executeQuery(selectauto);


                            if(select_a.next()){
                                temperatura = select_a.getFloat("temperatura");
                                wilgotnosc = select_a.getInt("wilgotnosc");
                                jasnosc = select_a.getInt("jasnosc");
                                tryb_naswietlenia = select_a.getInt("tryb_naswietlania");
                                czujnik_zmierzchu = select_a.getInt("czujnik_zmierzchu");
                                godzina_naswietlania_od = select_a.getInt("godzina_naswietlania_od");
                                godzina_naswietlania_do = select_a.getInt("godzina_naswietlania_do");

                                wilgotnosc_gleby = select_a.getInt("wilgotnosc_gleby");
                                godzina_podlewania_od = select_a.getInt("godzina_podlewania_od");
                                godzina_podlewania_do = select_a.getInt("godzina_podlewania_do");


                            }


                        }



                    }




                    if (trybdozmiany == 3) // zmiana danych manual
                    {

                        String zapisz_manual = "UPDATE manual SET " +
                                "went =     ?  , " +
                                "serwo =     ?  , " +
                                "zarowka1 =  ?  , " +
                                "zarowka2 =  ?  , " +
                                "zarowka3 =  ?  , " +
                                "zarowka4 =  ?  , " +
                                "pompa =     ?  , " +
                                "grzalka =   ?     ";

                        preparedstatement = polaczenie.prepareStatement(zapisz_manual);
                        preparedstatement.setInt(1, went);
                        preparedstatement.setInt(2, serwo);
                        preparedstatement.setInt(3, zarowka1);
                        preparedstatement.setInt(4, zarowka2);
                        preparedstatement.setInt(5, zarowka3);
                        preparedstatement.setInt(6, zarowka4);
                        preparedstatement.setInt(7, pompa);
                        preparedstatement.setInt(8, grzalka);

                        int rezultatupdate = preparedstatement.executeUpdate();
                        polaczenie.commit();

                    }
                    else if (trybdozmiany == 4){ //zmiana danych auto


                        String zapisz_auto = "UPDATE auto SET " +
                                "temperatura =     ?  , " +
                                "wilgotnosc =     ?  , " +
                                "jasnosc =  ?  , " +
                                "tryb_naswietlania =  ?  , " +
                                "czujnik_zmierzchu =  ?  , " +
                                "godzina_naswietlania_od =  ?  , " +
                                "godzina_naswietlania_do =  ?  , " +
                                "wilgotnosc_gleby =     ?  , " +
                                "godzina_podlewania_od =  ?  , " +
                                "godzina_podlewania_do =  ?   ";


                        preparedstatement = polaczenie.prepareStatement(zapisz_auto);
                        preparedstatement.setFloat(1, temperatura);
                        preparedstatement.setInt(2, wilgotnosc);
                        preparedstatement.setInt(3, jasnosc);
                        preparedstatement.setInt(4, tryb_naswietlenia);
                        preparedstatement.setInt(5, czujnik_zmierzchu);
                        preparedstatement.setInt(6, godzina_naswietlania_od);
                        preparedstatement.setInt(7, godzina_naswietlania_do);
                        preparedstatement.setInt(8, wilgotnosc_gleby);
                        preparedstatement.setInt(9, godzina_podlewania_od);
                        preparedstatement.setInt(10, godzina_podlewania_do);
                        int rezultatupdateauto = preparedstatement.executeUpdate();
                        polaczenie.commit();


                    }




                    String sprawdztryb = "SELECT * from ustawienia" ;

                    ResultSet result = statement.executeQuery(sprawdztryb);

                    if (result.next()){


                        Log.i("SterowanieActivity", "SELECT");
                        String trybsterowania = result.getString("tryb_sterowania");


                        if (trybsterowania.equals("m")){
                            obecnytryb = 0;
                        }
                        else if (trybsterowania.equals("a")){
                            obecnytryb = 1;
                        }





                        Log.i("SterowanieActivity", "SELECT pomyslny");

                    }

                    polaczenie.commit();
                    polaczenie.close();
                    statement.close();
                    result.close();

                }
            } catch (SQLException e) {



                e.printStackTrace();
            }
            long difference = System.currentTimeMillis() - startTime;
            Log.d("test1", "Czas UPDATE: " + difference + "ms");





            return null;
        }

        public void ustawprzycisk(ToggleButton przycisk, int dane){
            if (dane == 0){
                przycisk.setChecked(false);
            }
            else if (dane == 1)
            {
                przycisk.setChecked(true);
            }

        }

        @Override
        protected void onPostExecute(Integer s) {

            if (obecnytryb == 0){//zmiana manual
                sterowanie.setChecked(false);

                view_auto.setVisibility(View.GONE);
                view_manual.setVisibility(View.VISIBLE);
                wentylatory_zmiana.setProgress(went);
                ustawprzycisk(serwo_zmiana,serwo);

                ustawprzycisk(zarowka1_zmiana,zarowka1);
                ustawprzycisk(zarowka2_zmiana,zarowka2);
                ustawprzycisk(zarowka3_zmiana,zarowka3);
                ustawprzycisk(zarowka4_zmiana,zarowka4);
                ustawprzycisk(pompa_zmiana,pompa);
                ustawprzycisk(grzalka_zmiana,grzalka);

            }
            else if(obecnytryb == 1)//zmiana auto
            {

                sterowanie.setChecked(true);
                view_auto.setVisibility(View.VISIBLE);
                view_manual.setVisibility(View.GONE);


                temperatura_zmiana.setText(Float.toString(temperatura));
                wilgotnosc_zmiana.setText(Integer.toString(wilgotnosc));
                jasnosc_zmiana.setText(Integer.toString(jasnosc));

                if (tryb_naswietlenia == 0){
                    naswietlenie_stale.toggle();
                }
                else if (tryb_naswietlenia == 1){
                    naswietlenie_czunik.toggle();
                }
                else if (tryb_naswietlenia == 2){
                    naswietlenie_przedzial.toggle();
                }

                czujnik_zmierzchu_zmiana.setText(String.valueOf(czujnik_zmierzchu));
                godzina_naswietlenia_od_zmiana.setText(String.valueOf(godzina_naswietlania_od));
                godzina_naswietlenia_do_zmiana.setText(String.valueOf(godzina_naswietlania_do));


                podlewanie_zmiana.setText(Integer.toString(wilgotnosc_gleby));
                godzina_podlewania_od_zmiana.setText(String.valueOf(godzina_podlewania_od));
                godzina_podlewania_do_zmiana.setText(String.valueOf(godzina_podlewania_do));

            }





            super.onPostExecute(s);



        }
    }



}
