package com.example.rafal.szklarniaapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public  class ZalogujActivity extends AppCompatActivity {
     TextView internetStatus;
     TextView databaseStatus;
     TextView zalogujstatus;
    EditText UserInput;
    EditText PasswordInput;
    Button zalogujButton;

    static boolean zalogowano = false;
    Zaloguj zaloguj;
    Toolbar toolbar;

    CheckInternetConnection sprawdzinternet;
    CheckDbConnection sprawdzbaze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ustawienie content view
        setContentView(R.layout.activity_zaloguj);
        internetStatus = (TextView) findViewById(R.id.internetStatus);
        databaseStatus = (TextView) findViewById(R.id.databaseStatus);
        zalogujstatus = (TextView) findViewById(R.id.ZalogujStatus);
        zalogujstatus.setVisibility(View.GONE);

        //pola wpisania user i pass oraz login button
        UserInput = (EditText) findViewById(R.id.UserInput);
        PasswordInput = (EditText) findViewById(R.id.PasswordInput);
        zalogujButton = (Button) findViewById(R.id.LoginButton);


        // stworzenie toolbara górnego
        toolbar = (Toolbar) findViewById(R.id.zaloguj_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Szklarnia App");

        //uruchomienie asynctask sprawdzający połączenie z bazą
        sprawdzbaze = new CheckDbConnection();
        sprawdzbaze.execute("");

        //uruchomienie asynctask sprawdzający połączenie z siecią
        sprawdzinternet = new CheckInternetConnection();
        sprawdzinternet.execute("");






        zalogujButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = UserInput.getText().toString();
                String pass = PasswordInput.getText().toString();

            // uruchomienie async taska logowania z parametrami logowania
                zaloguj = new Zaloguj();
                zaloguj.execute(user,pass);



            }
        });







    }

    @Override
    protected void onStart() {
        //jeśli aktywność ta się włączy to service aktualizowania danych przestanie działać
        MainActivity.czyrestartujactivity = false;

        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//stworzenie toolbara gornego
        getMenuInflater().inflate(R.menu.menu_zaloguj,menu);

        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        //dodanie przycisków toolbara
        MenuItem wyjdz = menu.findItem(R.id.action_wyjdz);
        MenuItem odswiez = menu.findItem(R.id.action_odswiez);
        wyjdz.setVisible(true);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
    //sprawdzanie co jest naciśnięte na toolbarze

        if (id == R.id.action_wyjdz) {
            finish();
            System.exit(0);


            return true;
        }

        if (id == R.id.action_odswiez) {

            // uruchomienie async tasków
            sprawdzinternet = new CheckInternetConnection();
            sprawdzinternet.execute("");

            sprawdzbaze = new CheckDbConnection();
            sprawdzbaze.execute("");


            return true;
        }




        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed()
    {
        //naciśnięcie przycisku wstecz kończy aplikację
        finish();
        System.exit(0);
    }



    @Override
    public void onResume(){
        //co się dzieje przy wznowieniu aplikacji
        super.onResume();
        zalogujstatus.setVisibility(View.GONE);
        zalogowano = false;
        if (zalogowano = false)
        {
            Toast.makeText(ZalogujActivity.this, "Błąd połączenia", Toast.LENGTH_LONG).show();
        }


    }

    public class Zaloguj extends AsyncTask<String,String,String>{
        //AsyncTask logowania
        String rezultatlogowania = "";

        @Override
        protected String doInBackground(String... strings) {
            Connection polaczenie = null;
            Statement statement = null;
            //odebranie parametrów logowania
            String user = strings[0];
            String pass = strings[1];
            try {
                //Połączenie z bazą
                Class.forName(Db_Data.JDBC_DRIVER);
                polaczenie = DriverManager.getConnection(Db_Data.DB_address, Db_Data.DB_User,Db_Data.DB_Pass);
                //select ze sprawdzeniem danych
                String sql = "SELECT * FROM users WHERE user = ? AND password = ?";
                PreparedStatement preparedstatement = polaczenie.prepareStatement(sql);
                preparedstatement.setString(1, user);
                preparedstatement.setString(2, pass);
                ResultSet result = preparedstatement.executeQuery();
                //jeśli zwrócono
                if (result.next()){

                    String username = result.getString("user");
                    String password= result.getString("password");
                    rezultatlogowania = "Poprawne dane";
                    zalogowano = true;
                }
                else//w innym przypadku
                {
                    rezultatlogowania = "Niepoprawne dane logowania";
                    zalogowano = false;
                }
                result.close();
                preparedstatement.close();
                polaczenie.close();

            }catch (SQLException connerror)
            {
                //exception w razie błędu połączenie
                rezultatlogowania = "Błąd połączenia";
                zalogowano = false;
                connerror.printStackTrace();
            }

            catch (ClassNotFoundException e) {
                //exception w razie braku klasy
                rezultatlogowania = "Błąd połączenia";
                zalogowano = false;
                e.printStackTrace();
            }



            return null;
        }


        @Override
        protected void onPostExecute(String msg) {
            if (zalogowano == true){
                // uruchomienie main activity
                Intent mainactivity = new Intent(ZalogujActivity.this, MainActivity.class);
                startActivity(mainactivity);
            }
            else
            {
                zalogowano = false;
                //ustawienie statusów
                zalogujstatus.setText(rezultatlogowania);
                zalogujstatus.setVisibility(View.VISIBLE);
                zalogujstatus.setTextColor(Color.RED);
            }





        }



    }






    public class CheckInternetConnection extends AsyncTask<String,String,String> {
        String status_internet = "Sprawdzanie";
        //Klasa sprawdzająca połączenie z siecią


        @Override
        protected void onPreExecute() {
            internetStatus.setText("Sprawdzanie");
            internetStatus.setTextColor(Color.BLUE);

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            //sprawdzenie czy mamy połączenie z wifi bądź siecią mobilną
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                //sprawdzenie połączenie z internetem poprzez ping google.com
                try  {
                    Socket socket = new Socket();
                    socket.setSoTimeout(1000);
                    socket.connect(new InetSocketAddress("www.google.com", 80), 1000);

                    status_internet = "Online";
                } catch (IOException e) {

                    System.out.println(e);
                    status_internet = "Offline";
                }

            }
            else
            {

                status_internet = "Offline";
            }

            return null;
        }


        @Override
        protected void onPostExecute(String msg) {
            //ustawienie statusu internetu
            internetStatus.setText(this.status_internet);

            if (status_internet.equals("Online")){
                internetStatus.setTextColor(Color.GREEN);
            }
            else
            {
                internetStatus.setTextColor(Color.RED);
            }



        }
    }

    public class CheckDbConnection extends AsyncTask<String,String,String> {
        //klasa sprawdzająca łączność z bazą
        String status_baza = "Sprawdzanie";

        @Override
        protected void onPreExecute() {
            databaseStatus.setText("Sprawdzanie");
            databaseStatus.setTextColor(Color.BLUE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            Connection polaczenie = null;
            // próba łączenia z bazą
            try {
                Class.forName(Db_Data.JDBC_DRIVER);
                DriverManager.setLoginTimeout(1);
                polaczenie = DriverManager.getConnection(Db_Data.DB_address, Db_Data.DB_User, Db_Data.DB_Pass);
                if (polaczenie != null) {
                    status_baza = "Online";
                } else {
                    status_baza = "Offline";
                }
                polaczenie.close();
            } catch (SQLException e) {
                status_baza = "Offline";
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                status_baza = "Offline";
                e.printStackTrace();
            }

        return null;
        }


        @Override
        protected void onPostExecute(String msg) {
            //ustawienie statusu
            databaseStatus.setText(this.status_baza);
            if (status_baza.equals("Online")){
                databaseStatus.setTextColor(Color.GREEN);
            }
            else
            {
                databaseStatus.setTextColor(Color.RED);
            }





        }
    }









}
