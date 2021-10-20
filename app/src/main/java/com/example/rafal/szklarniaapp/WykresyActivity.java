package com.example.rafal.szklarniaapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WykresyActivity extends AppCompatActivity {
    static LineGraphSeries<DataPoint> atemp,btemp,ctemp,dtemp,dwilg,egleba,fswiatlo,gswiatlo;
    private RadioGroup radiogroup_wykresy;
    private RadioButton temp,wilg,gleba,swiatlo;
    Spinner wybor_data_przed;
    Spinner wybor_data_po;
    Button rysuj_wykres;
    int wybor = 0;
    Date dataprzed;
    Date datapo;
    int indexpierwszejdaty = 0;
    int indexdrugiejdaty = 0;
    List<HistoriaObject> listadowykresow;

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_wykresy);
        super.onCreate(savedInstanceState);



        final List<HistoriaObject> listaspinner2 = new ArrayList<HistoriaObject>();
        final GraphView graf = findViewById(R.id.wykres_pole);

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








        listadowykresow = HistoriaActivity.listadanych;

        wybor_data_przed = findViewById(R.id.spinner_data_przed);

        wybor_data_po = findViewById(R.id.spinner_data_po);
        graf.getGridLabelRenderer().setNumVerticalLabels(10);
        graf.getGridLabelRenderer().setLabelHorizontalHeight(30);
        graf.getGridLabelRenderer().setNumHorizontalLabels(10);

        ArrayAdapter<HistoriaObject> adapter =
                new ArrayAdapter<HistoriaObject>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, listadowykresow);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        wybor_data_przed.setAdapter(adapter);



        wybor_data_przed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HistoriaObject obj =  (HistoriaObject) adapterView.getAdapter().getItem(i);
                listaspinner2.clear();

                dataprzed = obj.getData();

                for (int j =0; j<listadowykresow.size(); j++){
                    if (listadowykresow.get(j).getData().getTime() > dataprzed.getTime() ){
                        listaspinner2.add(listadowykresow.get(j));
                    }

                }



                ArrayAdapter<HistoriaObject> adapter1 =
                        new ArrayAdapter<HistoriaObject>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, listaspinner2);
                adapter1.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                wybor_data_po.setAdapter(adapter1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        wybor_data_po.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HistoriaObject obj =  (HistoriaObject) adapterView.getAdapter().getItem(i);
                datapo = obj.getData();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        rysuj_wykres = findViewById(R.id.rysujwykres_button);
        RadioGroup radiogroup_wykresy = findViewById(R.id.radiogroup_wykresy);




        graf.getGridLabelRenderer().setHorizontalLabelsAngle(90);
        graf.getViewport().setXAxisBoundsManual(true);

        final SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        graf.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this, new SimpleDateFormat("MM-dd HH:mm:ss")));










        radiogroup_wykresy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int zaznaczone) {
            if (zaznaczone == R.id.radio_temp){
                wybor = 0;
            }
            else if (zaznaczone == R.id.radio_wilg){
                wybor = 1;
            }
            else if (zaznaczone == R.id.radio_gleba){
                wybor = 2;
            }
            else if (zaznaczone == R.id.radio_swiatlo){
                wybor = 3;
            }


            }
        });

        rysuj_wykres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                for (int k = 0; k< listadowykresow.size(); k++){
                    if(listadowykresow.get(k).getData() == dataprzed){
                        indexpierwszejdaty = k;
                    }
                    if(listadowykresow.get(k).getData() == datapo){
                        indexdrugiejdaty = k;
                    }

                }






//tu tak samo jak w wykresach rzeczywistych

                graf.removeAllSeries();
                atemp.resetData(new DataPoint[] {});
                btemp.resetData(new DataPoint[] {});
                ctemp.resetData(new DataPoint[] {});
                dtemp.resetData(new DataPoint[] {});
                dwilg.resetData(new DataPoint[] {});
                egleba.resetData(new DataPoint[] {});
                fswiatlo.resetData(new DataPoint[] {});
                gswiatlo.resetData(new DataPoint[] {});
                if (wybor == 0) {
                    graf.getGridLabelRenderer().setVerticalAxisTitle("Temperatura w \u2103");
                    atemp.setTitle("Lewo_Temp");
                    btemp.setTitle("Prawo_Temp");
                    ctemp.setTitle("Zewnątrz_Temp");
                    dtemp.setTitle("Środek_Temp");
                    for (int i = indexpierwszejdaty; i >= indexdrugiejdaty; i--) {
                            Log.d("test", listadowykresow.get(i).getData().toString());
                        float y1 = Float.parseFloat(listadowykresow.get(i).getA_temp());
                        float y2 = Float.parseFloat(listadowykresow.get(i).getB_temp());
                        float y3 = Float.parseFloat(listadowykresow.get(i).getC_temp());
                        float y4 = Float.parseFloat(listadowykresow.get(i).getD_temp());



                        atemp.appendData(new DataPoint(listadowykresow.get(i).getData().getTime(), y1), false, indexpierwszejdaty-indexdrugiejdaty+1);
                        btemp.appendData(new DataPoint(listadowykresow.get(i).getData().getTime(), y2), false, indexpierwszejdaty-indexdrugiejdaty+1);
                        ctemp.appendData(new DataPoint(listadowykresow.get(i).getData().getTime(), y3), false, indexpierwszejdaty-indexdrugiejdaty+1);
                        dtemp.appendData(new DataPoint(listadowykresow.get(i).getData().getTime(), y4), false, indexpierwszejdaty-indexdrugiejdaty+1);






                    }
                    graf.addSeries(atemp);
                    graf.addSeries(btemp);
                    graf.addSeries(ctemp);
                    graf.addSeries(dtemp);
                    graf.getLegendRenderer().setVisible(true);
                    graf.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    graf.getViewport().setMinX(listadowykresow.get(indexpierwszejdaty).getData().getTime());
                    graf.getViewport().setMaxX(listadowykresow.get(indexdrugiejdaty).getData().getTime());

                }

                if (wybor == 1) {
                    graf.getGridLabelRenderer().setVerticalAxisTitle("Wilgotność w %RH");
                    dwilg.setTitle("Wilgotność");

                    for (int i = indexpierwszejdaty; i >= indexdrugiejdaty; i--) {

                        double y1 = Double.parseDouble(listadowykresow.get(i).getD_wilg());

                        dwilg.appendData(new DataPoint(listadowykresow.get(i).getData(), y1), false,
                                indexpierwszejdaty-indexdrugiejdaty+1);

                    }
                    graf.addSeries(dwilg);
                    graf.getLegendRenderer().setVisible(true);
                    graf.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);
                    graf.getViewport().setMinX(listadowykresow.get(indexpierwszejdaty).getData().getTime());
                    graf.getViewport().setMaxX(listadowykresow.get(indexdrugiejdaty).getData().getTime());


                }

                if (wybor == 2) {
                    graf.getGridLabelRenderer().setVerticalAxisTitle("Wilgotność Gleby w %");
                    egleba.setTitle("Wilgotność Gleby");

                    for (int i = indexpierwszejdaty; i >= indexdrugiejdaty; i--) {

                        double y1 = Double.parseDouble(listadowykresow.get(i).getE_gleba_wilg());

                        egleba.appendData(new DataPoint(listadowykresow.get(i).getData(), y1), false, indexpierwszejdaty-indexdrugiejdaty+1);

                    }
                    graf.addSeries(egleba);
                    graf.getLegendRenderer().setVisible(true);
                    graf.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    graf.getViewport().setMinX(listadowykresow.get(indexpierwszejdaty).getData().getTime());
                    graf.getViewport().setMaxX(listadowykresow.get(indexdrugiejdaty).getData().getTime());


                }

                if (wybor == 3) {

                    graf.getGridLabelRenderer().setVerticalAxisTitle("Jasność w lx");
                    fswiatlo.setTitle("Wewnątrz");
                    gswiatlo.setTitle("Zewnątrz");

                    for (int i = indexpierwszejdaty; i >= indexdrugiejdaty; i--) {

                        double y1 = Double.parseDouble(listadowykresow.get(i).getF_swiatlo());
                        double y2 = Double.parseDouble(listadowykresow.get(i).getG_swiatlo());




                        fswiatlo.appendData(new DataPoint(listadowykresow.get(i).getData(), y1), false, indexpierwszejdaty-indexdrugiejdaty+1);
                        gswiatlo.appendData(new DataPoint(listadowykresow.get(i).getData(), y2), false, indexpierwszejdaty-indexdrugiejdaty+1);






                    }
                    graf.addSeries(fswiatlo);
                    graf.addSeries(gswiatlo);
                    graf.getLegendRenderer().setVisible(true);
                    graf.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    graf.getViewport().setMinX(listadowykresow.get(indexpierwszejdaty).getData().getTime());
                    graf.getViewport().setMaxX(listadowykresow.get(indexdrugiejdaty).getData().getTime());


                }






            }
        });











    }

}
