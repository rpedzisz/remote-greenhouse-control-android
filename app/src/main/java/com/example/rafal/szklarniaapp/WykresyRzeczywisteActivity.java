package com.example.rafal.szklarniaapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WykresyRzeczywisteActivity extends AppCompatActivity {
    //klasa odpowiadająca za wyświetlanie wyników pomiarów czasie rzeczywistym
    // dane przechowywane w lista_wykresy_realtime
    //dane odbierane od aktualizujpomiaryservice


    static int maxdanych = 1800;

    static List<HistoriaObject> lista_wykresy_realtime = new ArrayList<HistoriaObject>();

    static LineGraphSeries<DataPoint> atemp,btemp,ctemp,dtemp,dwilg,egleba,fswiatlo,gswiatlo;
    static GraphView graf;
    //handler i timer od częstotliwości aktualizwania
    private Handler mHandler = new Handler();;
    private Runnable mTimer;

    RadioGroup radiogroup_wykresy_rzeczywiste;

int wybor = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_wykresyrzeczywiste);
        super.onCreate(savedInstanceState);
radiogroup_wykresy_rzeczywiste = findViewById(R.id.radiogroup_rzeczywiste);

        //dane grafu
        graf = findViewById(R.id.wykres_rzeczywisty_pole);
        graf.getGridLabelRenderer().setNumVerticalLabels(10);
        graf.getGridLabelRenderer().setLabelHorizontalHeight(30);
        graf.getGridLabelRenderer().setNumHorizontalLabels(10);
        graf.getGridLabelRenderer().setHorizontalLabelsAngle(90);
        graf.getViewport().setXAxisBoundsManual(true);
        //format daty
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        graf.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this, new SimpleDateFormat("HH:mm:ss")));
graf.getGridLabelRenderer().setPadding(30);

        //datapointy
        atemp= new LineGraphSeries<DataPoint>();
        btemp = new LineGraphSeries<DataPoint>();
        ctemp = new LineGraphSeries<DataPoint>();
        dtemp = new LineGraphSeries<DataPoint>();
        dwilg = new LineGraphSeries<DataPoint>();
        egleba = new LineGraphSeries<DataPoint>();
        fswiatlo = new LineGraphSeries<DataPoint>();
        gswiatlo = new LineGraphSeries<DataPoint>();

        //reset danych z datapointów
        atemp.resetData(new DataPoint[] {});
        btemp.resetData(new DataPoint[] {});
        ctemp.resetData(new DataPoint[] {});
        dtemp.resetData(new DataPoint[] {});
        dwilg.resetData(new DataPoint[] {});
        egleba.resetData(new DataPoint[] {});
        fswiatlo.resetData(new DataPoint[] {});
        gswiatlo.resetData(new DataPoint[] {});


        graf.removeAllSeries();

        //ustawienie kolorów datapointów
        atemp.setColor(Color.BLACK);
        btemp.setColor(Color.RED);
        ctemp.setColor(Color.GREEN);
        dtemp.setColor(Color.BLUE);
        dwilg.setColor(Color.MAGENTA);
        egleba.setColor(Color.RED);
        fswiatlo.setColor(Color.CYAN);
        gswiatlo.setColor(Color.BLACK);





        radiogroup_wykresy_rzeczywiste.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int zaznaczone) {
                //wybór radio
                if (zaznaczone == R.id.rzeczywiste_temperatury){
                    atemp.resetData(new DataPoint[] {});
                    btemp.resetData(new DataPoint[] {});
                    ctemp.resetData(new DataPoint[] {});
                    dtemp.resetData(new DataPoint[] {});
                    graf.removeAllSeries();
                    graf.getGridLabelRenderer().setVerticalAxisTitle("Temperatura w \u2103");
                    //ustawienie tytułów legendy
                    atemp.setTitle("Temp_Lewo");
                    btemp.setTitle("Temp_Prawo");
                    ctemp.setTitle("Temp_Zewnętrzna");
                    dtemp.setTitle("Temp_Środek");
                    //dodanie obecnych danych w liście do jej size
                    for (int i = 0; i< lista_wykresy_realtime.size(); i++){
                        atemp.appendData(new DataPoint(lista_wykresy_realtime.get(i).getData().getTime(),
                                Float.valueOf(lista_wykresy_realtime.get(i).getA_temp())), false, maxdanych);
                        btemp.appendData(new DataPoint(lista_wykresy_realtime.get(i).getData().getTime(), Float.valueOf(lista_wykresy_realtime.get(i).getB_temp())), false, maxdanych);
                        ctemp.appendData(new DataPoint(lista_wykresy_realtime.get(i).getData().getTime(), Float.valueOf(lista_wykresy_realtime.get(i).getC_temp())), false, maxdanych);
                        dtemp.appendData(new DataPoint(lista_wykresy_realtime.get(i).getData().getTime(), Float.valueOf(lista_wykresy_realtime.get(i).getD_temp())), false, maxdanych);

                    }
                    //dodanie datapointów do wykresu
                    graf.addSeries(atemp);
                    graf.addSeries(btemp);
                    graf.addSeries(dtemp);
                    graf.addSeries(ctemp);
                    //ustawienie legendy
                    graf.getLegendRenderer().setVisible(true);
                    graf.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    //wybór pokazuje co będzie wyświetlane i aktualizowane w timerze
                    wybor = 0;
                }
                else if (zaznaczone == R.id.rzeczywiste_wilgotnosc){
                    graf.getGridLabelRenderer().setVerticalAxisTitle("Wilgotność w %RH");
                    dwilg.resetData(new DataPoint[] {});

                    graf.removeAllSeries();
                    dwilg.setTitle("Wilgotność");

                    for (int i = 0; i< lista_wykresy_realtime.size(); i++){
                        dwilg.appendData(new DataPoint(lista_wykresy_realtime.get(i).getData().getTime(), Integer.valueOf(lista_wykresy_realtime.get(i).getD_wilg())), false, maxdanych);

                    }
                    graf.addSeries(dwilg);



                    graf.getLegendRenderer().setVisible(true);
                    graf.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);



                    wybor = 1;
                }
                else if (zaznaczone == R.id.rzeczywiste_gleba){

                    graf.getGridLabelRenderer().setVerticalAxisTitle("Wilgotność Gleby w %");
                    egleba.resetData(new DataPoint[] {});

                    graf.removeAllSeries();
                    egleba.setTitle("Wilg Gleby");

                    for (int i = 0; i< lista_wykresy_realtime.size(); i++){
                        egleba.appendData(new DataPoint(lista_wykresy_realtime.get(i).getData().getTime(), Integer.valueOf(lista_wykresy_realtime.get(i).getE_gleba_wilg())), false, maxdanych);

                    }
                    graf.addSeries(egleba);

                    graf.getLegendRenderer().setVisible(true);
                    graf.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);



                    wybor = 2;
                }
                else if (zaznaczone == R.id.rzeczywiste_swiatlo){
                    graf.getGridLabelRenderer().setVerticalAxisTitle("Jasność w lx");
                    fswiatlo.resetData(new DataPoint[] {});
                    gswiatlo.resetData(new DataPoint[] {});

                    graf.removeAllSeries();

                    fswiatlo.setTitle("Wewnątrz");
                    gswiatlo.setTitle("Zewnątrz");

                    for (int i = 0; i< lista_wykresy_realtime.size(); i++){
                        fswiatlo.appendData(new DataPoint(lista_wykresy_realtime.get(i).getData().getTime(), Integer.valueOf(lista_wykresy_realtime.get(i).getF_swiatlo())), false, maxdanych);
                        gswiatlo.appendData(new DataPoint(lista_wykresy_realtime.get(i).getData().getTime(), Integer.valueOf(lista_wykresy_realtime.get(i).getG_swiatlo())), false, maxdanych);



                    }
                    graf.addSeries(fswiatlo);
                    graf.addSeries(gswiatlo);


                    graf.getLegendRenderer().setVisible(true);
                    graf.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);



                    wybor = 3;
                }




            }
        });









        //  aktualizowanie pomiarów
        mTimer = new Runnable() {
            @Override
            public void run() {

                    if (wybor == 0) {
                        atemp.appendData(new DataPoint(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getData().getTime(),
                                Float.valueOf(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getA_temp())),
                                false, maxdanych);
                        btemp.appendData(new DataPoint(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getData().getTime(), Float.valueOf(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getB_temp())), false, maxdanych);
                        ctemp.appendData(new DataPoint(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getData().getTime(), Float.valueOf(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getC_temp())), false, maxdanych);
                        dtemp.appendData(new DataPoint(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getData().getTime(), Float.valueOf(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getD_temp())), false, maxdanych);


                    }
                    if (wybor == 1) {
                        dwilg.appendData(new DataPoint(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getData().getTime(), Integer.valueOf(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getD_wilg())), false, maxdanych);


                    }
                    if (wybor == 2) {
                        egleba.appendData(new DataPoint(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getData().getTime(), Integer.valueOf(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getE_gleba_wilg())), false, maxdanych);


                    }

                    if (wybor == 3) {
                        fswiatlo.appendData(new DataPoint(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getData().getTime(), Integer.valueOf(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getF_swiatlo())), false, maxdanych);
                        gswiatlo.appendData(new DataPoint(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getData().getTime(), Integer.valueOf(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getG_swiatlo())), false, maxdanych);


                    }
                graf.getViewport().setMinX(lista_wykresy_realtime.get(0).getData().getTime());
                graf.getViewport().setMaxX(lista_wykresy_realtime.get(lista_wykresy_realtime.size() - 1).getData().getTime());


                mHandler.postDelayed(this, 2000);


            }
        };
        mHandler.postDelayed(mTimer, 2000);
//co 2s



    }
}
