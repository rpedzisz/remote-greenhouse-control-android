package com.example.rafal.szklarniaapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WybierzPomiar extends AppCompatActivity {
//długie naciśnięcie elementu na liście pomiarów powoduje uruchomienie tej aktywności
    TextView id;
    TextView data;
    TextView atemp;
    TextView btemp;
    TextView ctemp;
    TextView dtemp;
    TextView dwilg;
    TextView egleba;
    TextView fswiatlo;
    TextView gswiatlo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.activity_wybierzpomiar);
        super.onCreate(savedInstanceState);

        Bundle paczka = new Bundle();
        paczka = getIntent().getExtras();
      //  odebranie danych z paczki i ustawienie
        if (paczka != null) {
            id = findViewById(R.id.id_wybor);
            data = findViewById(R.id.data_wybor);
            atemp = findViewById(R.id.atemp_wybor);
                    btemp = findViewById(R.id.btemp_wybor);
                    ctemp = findViewById(R.id.ctemp_wybor);
                    dtemp = findViewById(R.id.dtemp_wybor);
                    dwilg  = findViewById(R.id.dwilg_wybor);
                    egleba = findViewById(R.id.egleba_wybor);
                    fswiatlo = findViewById(R.id.fswiatlo_wybor);
                    gswiatlo = findViewById(R.id.gswiatlo_wybor);




            id.setText(paczka.getString("EXTRA_pomiar_id"));
            data.setText(paczka.getString("EXTRA_pomiar_data"));
            atemp.setText(paczka.getString("EXTRA_pomiar_atemp")+ " \u2103");
            btemp.setText(paczka.getString("EXTRA_pomiar_btemp")+ " \u2103");
            ctemp.setText(paczka.getString("EXTRA_pomiar_ctemp")+ " \u2103");
            dtemp.setText(paczka.getString("EXTRA_pomiar_dtemp")+ " \u2103");
            dwilg.setText(paczka.getString("EXTRA_pomiar_dwilg") + " %RH");
            egleba.setText(paczka.getString("EXTRA_pomiar_egleba"));
            fswiatlo.setText(paczka.getString("EXTRA_pomiar_fswiatlo") + " lx");
            gswiatlo.setText(paczka.getString("EXTRA_pomiar_gswiatlo")+ " lx");



        }


    }
}
