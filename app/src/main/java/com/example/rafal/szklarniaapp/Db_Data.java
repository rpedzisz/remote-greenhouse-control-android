package com.example.rafal.szklarniaapp;

class Db_Data {
//klasa w której są przechowywane dane do połączenia z bazą

    final static String DB_Url = "10.0.2.2:3306";
    final static String DB_Name = "szklarnia";
    final static String DB_User = "szklarnia123";
    final static String DB_Pass = "qwe123";

    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String DB_address = "jdbc:mysql://" + Db_Data.DB_Url + "/" + Db_Data.DB_Name;


}
