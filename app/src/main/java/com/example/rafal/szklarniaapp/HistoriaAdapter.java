package com.example.rafal.szklarniaapp;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoriaAdapter extends BaseAdapter {

    //adapter służący do wrzucenia danych ArrayList obiektów historii do listview historii
    LayoutInflater minflater;


    List<HistoriaObject> listadanych;

    //konstruktor
    public HistoriaAdapter(Context context, List<HistoriaObject> lista){

        minflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        listadanych = new ArrayList<HistoriaObject>();
        listadanych = lista;

    }

    @Override
    public int getCount() {
        return listadanych.size();
    }

    @Override
    public Object getItem(int pos) {
        return listadanych.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        View widok = minflater.inflate(R.layout.listview_historia_item,null);

        TextView data = widok.findViewById(R.id.data);
        TextView id = widok.findViewById(R.id.id);
        TextView a_temp = widok.findViewById(R.id.a_temp);
        TextView b_temp = widok.findViewById(R.id.b_temp);
        TextView c_temp = widok.findViewById(R.id.c_temp);
        TextView d_temp = widok.findViewById(R.id.d_temp);
        TextView d_wilg = widok.findViewById(R.id.d_wilg);
        TextView e_gleba = widok.findViewById(R.id.e_gleba);
        TextView f_swiatlo = widok.findViewById(R.id.f_swiatlo);
        TextView g_swiatlo = widok.findViewById(R.id.g_swiatlo);

        DateFormat df = new DateFormat();



        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");

        String strDate = dateFormat.format(listadanych.get(pos).getData());

        data.setText(strDate);





        id.setText(listadanych.get(pos).getId());
        a_temp.setText(listadanych.get(pos).getA_temp() + " \u2103");
        b_temp.setText(listadanych.get(pos).getB_temp()+ " \u2103");
        c_temp.setText(listadanych.get(pos).getC_temp()+ " \u2103");
        d_temp.setText(listadanych.get(pos).getD_temp()+ " \u2103");
        d_wilg.setText(listadanych.get(pos).getD_wilg()+ " %RH");
        e_gleba.setText(listadanych.get(pos).getE_gleba_wilg() + " %");
        f_swiatlo.setText(listadanych.get(pos).getF_swiatlo() + " lx");
        g_swiatlo.setText(listadanych.get(pos).getG_swiatlo() + " lx");


        return widok;
    }
}


