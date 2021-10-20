package com.example.rafal.szklarniaapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//textview
    TextView tryb;
    TextView a_temp;
    TextView b_temp;
    TextView c_temp;
    TextView d_temp;
    TextView d_wilg;
    TextView e_wilg_gleba;
    TextView f_swiatlo;
    TextView g_swiatlo;
    TextView went;
    TextView serwo;
    TextView zarowka1;
    TextView zarowka2;
    TextView zarowka3;
    TextView zarowka4;
    TextView pompa;
    TextView grzalka;
//textview statusów - czy coś rośnie lub maleje
    TextView a_temp_status;
    TextView b_temp_status;
    TextView c_temp_status;
    TextView d_temp_status;
    TextView d_wilg_status;
    TextView e_gleba_status;
    TextView f_swiatlo_status;
    TextView g_swiatlo_status;



    TextView pomiary;




    //receivery od aktualizowania danych i restartowania service aktualizującego
    private AktualizujDaneReceiver AktualizujDane;
    private RestartujServiceReceiver RestartujService;

    //zmienna od której zależy czy restartować aktualizowanie danych service
    static boolean czyrestartujactivity = false;

    //przyciski
    Button historiaButton;
    Button sterowanieButton;
    Button wykresyButton;

    //poprzednie dane, służące do statusów - czy dane rosną czy maleją
    float a_temp_prev = -100;
    float b_temp_prev = -100;
    float c_temp_prev = -100;
    float d_temp_prev = -100;
    int d_wilg_prev = -100;
    int e_gleba_prev = -100;
    int f_swiatlo_prev = -100;
    int g_swiatlo_prev = -100;
    Intent aktualizujdane;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ustawienie widoków i id
        setContentView(R.layout.activity_main);
        tryb = findViewById(R.id.tryb);
        a_temp = findViewById(R.id.a_temp);
        b_temp = findViewById(R.id.b_temp);
        c_temp = findViewById(R.id.c_temp);
        d_temp = findViewById(R.id.d_temp);
        d_wilg = findViewById(R.id.d_wilg);
        e_wilg_gleba = findViewById(R.id.e_wilg_gleba);
        f_swiatlo = findViewById(R.id.f_swiatlo);
        g_swiatlo = findViewById(R.id.g_swiatlo);
        went = findViewById(R.id.went);
        serwo = findViewById(R.id.serwo);
        zarowka1 = findViewById(R.id.zarowka1);
        zarowka2 = findViewById(R.id.zarowka2);
        zarowka3 = findViewById(R.id.zarowka3);
        zarowka4 = findViewById(R.id.zarowka4);
        pompa = findViewById(R.id.pompa);
        grzalka = findViewById(R.id.grzalka);
        pomiary = findViewById(R.id.pomiary);
        historiaButton = findViewById(R.id.historiaButton);
        sterowanieButton = findViewById(R.id.sterowanieButton);
        wykresyButton = findViewById(R.id.wykresyButton);

        a_temp_status = findViewById(R.id.a_temp_status);
        b_temp_status = findViewById(R.id.b_temp_status);
        c_temp_status = findViewById(R.id.c_temp_status);
        d_temp_status = findViewById(R.id.d_temp_status);
        d_wilg_status = findViewById(R.id.d_wilg_status);
        e_gleba_status = findViewById(R.id.e_gleba_status);
        f_swiatlo_status = findViewById(R.id.f_swiatlo_status);
        g_swiatlo_status = findViewById(R.id.g_swiatlo_status);






        //włączenie intentservice odpowiadające za aktualizowanie danych
        aktualizujdane = new Intent(MainActivity.this, AktualizujPomiaryService.class);
        startService(aktualizujdane);




        historiaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //start activity historii
                Intent historia = new Intent(MainActivity.this,HistoriaActivity.class);
                startActivity(historia);

            }
        });


        sterowanieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start activity sterowania
                Intent sterowanie = new Intent(MainActivity.this, SterowanieActivity.class);
                startActivity(sterowanie);

            }
        });
        wykresyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start activity wykresów
                Intent wykresy = new Intent(MainActivity.this, WykresyRzeczywisteActivity.class);
                startActivity(wykresy);
            }
        });




    }

    @Override
    protected void onStart() {
        //zarejestrowanie receivera od aktualizowania danych
        AktualizujDane = new AktualizujDaneReceiver();
        IntentFilter intentFilter = new IntentFilter(AktualizujPomiaryService.ACTION_AktualizujPomiaryService);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(AktualizujDane, intentFilter);

        //zarejestrowanie receivera od restartowania service
        RestartujService = new RestartujServiceReceiver();
        IntentFilter intentFilter1 = new IntentFilter(AktualizujPomiaryService.ACTION_RestartujServiceReceiver);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(RestartujService, intentFilter1);
        super.onStart();
    }

    @Override
    protected void onStop() {


        super.onStop();
    }

    @Override
    protected void onPause() {




        super.onPause();
    }

    @Override
    protected void onResume() {
        //przy wznowieniu activity

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        czyrestartujactivity = false;
        Intent aktualizujdane = new Intent(MainActivity.this, AktualizujPomiaryService.class);
        stopService(aktualizujdane);

        super.onDestroy();
    }

    public class AktualizujDaneReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //stworzenie bundle i get extras
            Bundle odbierzpaczke = new Bundle();
            odbierzpaczke = intent.getExtras();
            String obecny_tryb = odbierzpaczke.getString("EXTRA_tryb_sterowania");
            if (obecny_tryb != null) {
                if (obecny_tryb.equals("0")) {
                    tryb.setText("Manual");
                } else {
                    tryb.setText("Auto");
                }
            }
            //odebranie danych z bundle i ustawienie danymi
            a_temp.setText(Float.toString(odbierzpaczke.getFloat("EXTRA_a_temp")) + " \u2103");
            b_temp.setText(Float.toString(odbierzpaczke.getFloat("EXTRA_b_temp")) + " \u2103");
            c_temp.setText(Float.toString(odbierzpaczke.getFloat("EXTRA_c_temp")) + " \u2103");
            d_temp.setText(Float.toString(odbierzpaczke.getFloat("EXTRA_d_temp")) + " \u2103");
            d_wilg.setText(Integer.toString(odbierzpaczke.getInt("EXTRA_d_wilg")) + " %RH");
            e_wilg_gleba.setText(Integer.toString(odbierzpaczke.getInt("EXTRA_e_wilg_gleba"))+ " %");
            f_swiatlo.setText(Integer.toString(odbierzpaczke.getInt("EXTRA_f_swiatlo")) +" lx");
            g_swiatlo.setText(Integer.toString(odbierzpaczke.getInt("EXTRA_g_swiatlo")) +" lx");

            // sprawdzenie zmian między poprzednimi i obecnymi parametrami
            sprawdzzmiany(a_temp_prev,odbierzpaczke.getFloat("EXTRA_a_temp"),a_temp_status);
            a_temp_prev = odbierzpaczke.getFloat("EXTRA_a_temp");

            sprawdzzmiany(b_temp_prev,odbierzpaczke.getFloat("EXTRA_b_temp"),b_temp_status);
            b_temp_prev = odbierzpaczke.getFloat("EXTRA_b_temp");

            sprawdzzmiany(c_temp_prev,odbierzpaczke.getFloat("EXTRA_c_temp"),c_temp_status);
            c_temp_prev = odbierzpaczke.getFloat("EXTRA_c_temp");


            sprawdzzmiany(d_temp_prev,odbierzpaczke.getFloat("EXTRA_d_temp"),d_temp_status);
            d_temp_prev = odbierzpaczke.getFloat("EXTRA_d_temp");

            sprawdzzmiany(d_wilg_prev,odbierzpaczke.getInt("EXTRA_d_wilg"),d_wilg_status);
            d_wilg_prev = odbierzpaczke.getInt("EXTRA_d_wilg");

            sprawdzzmiany(e_gleba_prev,odbierzpaczke.getInt("EXTRA_e_wilg_gleba"),e_gleba_status);
            e_gleba_prev = odbierzpaczke.getInt("EXTRA_e_wilg_gleba");

            sprawdzzmiany(f_swiatlo_prev,odbierzpaczke.getInt("EXTRA_f_swiatlo"),f_swiatlo_status);
            f_swiatlo_prev = odbierzpaczke.getInt("EXTRA_f_swiatlo");

            sprawdzzmiany(g_swiatlo_prev,odbierzpaczke.getInt("EXTRA_g_swiatlo"),g_swiatlo_status);
            g_swiatlo_prev = odbierzpaczke.getInt("EXTRA_g_swiatlo");



            //ustawienie statusów wentylatorów i urządzeń z bundle
            went.setText(odbierzpaczke.getString("EXTRA_went") +" %");

            ustawstatusserwo(odbierzpaczke.getString("EXTRA_serwo"), serwo);
            ustawstatus(odbierzpaczke.getString("EXTRA_zarowka1"), zarowka1);
            ustawstatus(odbierzpaczke.getString("EXTRA_zarowka2"), zarowka2);
            ustawstatus(odbierzpaczke.getString("EXTRA_zarowka3"), zarowka3);
            ustawstatus(odbierzpaczke.getString("EXTRA_zarowka4"), zarowka4);
            ustawstatus(odbierzpaczke.getString("EXTRA_grzalka"), grzalka);
            ustawstatus(odbierzpaczke.getString("EXTRA_pompa"), pompa);

        }

    }

    public class RestartujServiceReceiver extends BroadcastReceiver{
//receiver odpowiadający za restartowanie usługi aktualizujpomiaryservice

        @Override
        public void onReceive(Context context, Intent intent) {

            Intent aktualizujdane = new Intent(MainActivity.this, AktualizujPomiaryService.class);
            if (MainActivity.czyrestartujactivity == true) {
                startService(aktualizujdane);
            }
            else
            {
                stopService(aktualizujdane);
                Intent zalogujctivity = new Intent(MainActivity.this, ZalogujActivity.class);
                startActivity(zalogujctivity);
            }

        }

    }

    public void onBackPressed() {
        finish();
    }

public void ustawstatus (String wartosc, TextView dane){
        //ustawianie statusów On, Off żarówek itd
      if (wartosc.equals("1")){
          dane.setText("On");
          dane.setTextColor(Color.GREEN);
      }
      else
      {
          dane.setText("Off");
          dane.setTextColor(Color.RED);
      }

}


    public void ustawstatusserwo (String wartosc, TextView dane){
        //ustawienie statusu serwo
        if (wartosc.equals("1")){
            dane.setText("Otwarte");
            dane.setTextColor(Color.GREEN);
        }
        else
        {
            dane.setText("Zamknięte");
            dane.setTextColor(Color.RED);
        }

    }




public void sprawdzzmiany (float poprzedniawartosc, float obecnawartosc, TextView zmien_status){
        //funkcja odpowiadająca za sprawdzanie zmian między poprzednimi a obecnymi pomiarami

        if((poprzedniawartosc == obecnawartosc) || (poprzedniawartosc == -100)){
         zmien_status.setText("     ");
         zmien_status.setTextColor(Color.BLUE);
        }
        else if (poprzedniawartosc > obecnawartosc){

            float roznica = obecnawartosc - poprzedniawartosc;
            zmien_status.setText(String.format("%.3f", roznica));

            zmien_status.setTextColor(Color.RED);
        }
        else if (poprzedniawartosc < obecnawartosc){

            float roznica = obecnawartosc - poprzedniawartosc;
            zmien_status.setText("+" + String.format("%.3f", roznica));
            zmien_status.setTextColor(Color.GREEN);
        }




}






}