package com.example.rafal.szklarniaapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HistoriaActivity extends AppCompatActivity{

ListView historia_listview;
TextView status;
    Toolbar mTopToolbar;


     static List<HistoriaObject> listadanych = new ArrayList<HistoriaObject>();
     static List<HistoriaObject> lista_edytowalna = new ArrayList<HistoriaObject>();

    static HistoriaAdapter historia_adapter;
    MenuItem cofnij;
    MenuItem usun;

    Button wykresy;
    Button opcje;
   // Button zapisz_pomiar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);

        wykresy = findViewById(R.id.wykresy);
        opcje = findViewById(R.id.opcje);
       // zapisz_pomiar = findViewById(R.id.zapisz_pomiar);


        historia_listview = (ListView) findViewById(R.id.historiaListView);
        historia_listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        historia_listview.setLongClickable(true);



         mTopToolbar = (Toolbar) findViewById(R.id.historia_toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setTitle("Historia pomiarów");

        GetDataFromDb wrzucdane = new GetDataFromDb();
        wrzucdane.execute("");

        wykresy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wykresyactivity = new Intent(HistoriaActivity.this, WykresyActivity.class);
                startActivity(wykresyactivity);



            }
        });

        opcje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent opcjeactivity = new Intent(HistoriaActivity.this, OpcjeActivity.class);
                startActivity(opcjeactivity);


            }
        });




//długie kliknięcie na element listy
        historia_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                HistoriaObject obj = (HistoriaObject) arg0.getAdapter().getItem(pos);



                Intent wybierzpomiar = new Intent(HistoriaActivity.this, WybierzPomiar.class);

                wybierzpomiar.putExtra("EXTRA_pomiar_id", obj.getId());
                wybierzpomiar.putExtra("EXTRA_pomiar_data", obj.getData().toString());
                wybierzpomiar.putExtra("EXTRA_pomiar_atemp", obj.getA_temp());
                wybierzpomiar.putExtra("EXTRA_pomiar_btemp", obj.getB_temp());
                wybierzpomiar.putExtra("EXTRA_pomiar_ctemp", obj.getC_temp());
                wybierzpomiar.putExtra("EXTRA_pomiar_dtemp", obj.getD_temp());
                wybierzpomiar.putExtra("EXTRA_pomiar_dwilg", obj.getD_wilg());
                wybierzpomiar.putExtra("EXTRA_pomiar_egleba", obj.getE_gleba_wilg());
                wybierzpomiar.putExtra("EXTRA_pomiar_fswiatlo", obj.getF_swiatlo());
                wybierzpomiar.putExtra("EXTRA_pomiar_gswiatlo", obj.getG_swiatlo());




                startActivity(wybierzpomiar);



               return true;
            }


        });



        // krótkie kliknięcie na element listy
        historia_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {










            }

        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_historia,menu);

        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        cofnij = menu.findItem(R.id.action_cofnij);
        usun = menu.findItem(R.id.action_usun);
        usun.setVisible(true);
        cofnij.setVisible(true);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_cofnij) {
            finish();
            return true;
        }

        if (id == R.id.action_usun) {

            ArrayList<Integer> list = new ArrayList<Integer>();
            SparseBooleanArray checkedItems = historia_listview.getCheckedItemPositions();
            if (checkedItems != null) {
                String idwbazie = "";
                for (int i=0; i<checkedItems.size(); i++) {
                    historia_adapter.notifyDataSetChanged();
                    if (checkedItems.valueAt(i)) {
                        String item1 = historia_listview.getAdapter().getItem(checkedItems.keyAt(i)).toString();
                        int pos = checkedItems.keyAt(i);
                        HistoriaObject obj =  (HistoriaObject) historia_listview.getAdapter().getItem(checkedItems.keyAt(i));
                        idwbazie += obj.getId() +",";


                        //list.add(pos);




                    }
                }
                if (idwbazie.length() > 1)
                {
                    idwbazie = idwbazie.substring(0, idwbazie.length() - 1);
                    DeleteDataFromDb usundane = new DeleteDataFromDb();
                    usundane.execute(idwbazie);
                    lista_edytowalna.clear();
                    GetDataFromDb wrzucdane = new GetDataFromDb();
                    wrzucdane.execute("");
                    historia_adapter.notifyDataSetChanged();

                }

            }




            return true;
        }


        return super.onOptionsItemSelected(item);
    }







    public void onBackPressed() {
        finish();
    }








    public class GetDataFromDb extends AsyncTask<String,String,String> {


//klasa odpowiadająca za wrzucenie danych z bazy do listview

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            Connection polaczenie = null;
            Statement statement = null;

            try {
                Class.forName(Db_Data.JDBC_DRIVER);
                polaczenie = DriverManager.getConnection(Db_Data.DB_address, Db_Data.DB_User,Db_Data.DB_Pass);


                if (polaczenie != null){
                    statement = polaczenie.createStatement();

                    String sql = "SELECT * FROM historia_pomiarow" ;

                    ResultSet result = statement.executeQuery(sql);

                    while (result.next()) {

                        Date data = new Date();
                        Timestamp ts = result.getTimestamp("data_pomiaru"); //trzeba timestampem bo getDate nie zwraca czasu
                        if (ts != null)
                            data = new Date(ts.getTime());

                        String id = result.getString("id");
                        String a_temp = result.getString("a_temp");
                        String b_temp = result.getString("b_temp");
                        String c_temp = result.getString("c_temp");
                        String d_temp = result.getString("d_temp");
                        String d_wilg = result.getString("d_wilg");
                        String e_gleba_wilg = result.getString("e_gleba_wilg");
                        String f_swiatlo = result.getString("f_swiatlo");
                        String g_swiatlo = result.getString("g_swiatlo");

                        HistoriaObject obiekt = new HistoriaObject(data, id,a_temp,b_temp,c_temp,d_temp,d_wilg,e_gleba_wilg,f_swiatlo,g_swiatlo);
                        listadanych.add(obiekt);
                    }

                    if (polaczenie != null) {
                        polaczenie.close();
                    }

                    if (statement != null) {
                        statement.close();
                    }



                }





            }catch (SQLException e)
            {


                e.printStackTrace();

            }
            catch (ClassNotFoundException e) {


                e.printStackTrace();
            }



            return null;
        }


        @Override
        protected void onPostExecute(String msg) {
            if(listadanych.size() > 0) {
//                status.setText("Dane w adapterze");

                Collections.reverse(listadanych);

                for(HistoriaObject objekt : listadanych) {
                    lista_edytowalna.add(objekt);
                }


                historia_adapter = new HistoriaAdapter(HistoriaActivity.this, lista_edytowalna);
                historia_listview.setAdapter(historia_adapter);


            }
            else
            {
            status.setText("Brak obiektow");
            }

        }
    }

    public class DeleteDataFromDb extends AsyncTask<String,String,String> {


//klasa odpowiadająca za wrzucenie danych z bazy do listview

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            Connection polaczenie = null;
            Statement statement = null;


            try {
                Class.forName(Db_Data.JDBC_DRIVER);
                polaczenie = DriverManager.getConnection(Db_Data.DB_address, Db_Data.DB_User,Db_Data.DB_Pass);


                if (polaczenie != null){
                    statement = polaczenie.createStatement();

                    String sql = "DELETE FROM historia_pomiarow WHERE id IN (" + strings[0] +")" ;

                    int result = statement.executeUpdate(sql);
                    Log.d("test", "usunieto rekordow = " + result);


                    polaczenie.close();
                    statement.close();



                }





            }catch (SQLException e)
            {
                e.printStackTrace();

            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }



            return null;
        }


        @Override
        protected void onPostExecute(String msg) {


        }
    }




}
