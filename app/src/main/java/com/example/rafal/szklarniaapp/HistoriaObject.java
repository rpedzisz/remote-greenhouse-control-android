package com.example.rafal.szklarniaapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoriaObject {
    //klasa obiektów historii
    //wrzucane do ArrayList HistoriaObject
    private Date data;
    private String id;
    private String a_temp;
    private String b_temp;
    private String c_temp;
    private String d_temp;
    private String d_wilg;
    private String e_gleba_wilg;
    private String f_swiatlo;
    private String g_swiatlo;


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public HistoriaObject(Date data, String id, String a_temp, String b_temp, String c_temp, String d_temp, String d_wilg, String e_gleba_wilg, String f_swiatlo, String g_swiatlo) {
        this.data = data;

        this.id = id;
        this.a_temp = a_temp;
        this.b_temp = b_temp;
        this.c_temp = c_temp;
        this.d_temp = d_temp;
        this.d_wilg = d_wilg;
        this.e_gleba_wilg = e_gleba_wilg;
        this.f_swiatlo = f_swiatlo;
        this.g_swiatlo = g_swiatlo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getA_temp() {
        return a_temp;
    }

    public void setA_temp(String a_temp) {
        this.a_temp = a_temp;
    }

    public String getB_temp() {
        return b_temp;
    }

    public void setB_temp(String b_temp) {
        this.b_temp = b_temp;
    }

    public String getC_temp() {
        return c_temp;
    }

    public void setC_temp(String c_temp) {
        this.c_temp = c_temp;
    }

    public String getD_temp() {
        return d_temp;
    }

    public void setD_temp(String d_temp) {
        this.d_temp = d_temp;
    }

    public String getD_wilg() {
        return d_wilg;
    }

    public void setD_wilg(String d_wilg) {
        this.d_wilg = d_wilg;
    }

    public String getE_gleba_wilg() {
        return e_gleba_wilg;
    }

    public void setE_gleba_wilg(String e_gleba_wilg) {
        this.e_gleba_wilg = e_gleba_wilg;
    }

    public String getF_swiatlo() {
        return f_swiatlo;
    }

    public void setF_swiatlo(String f_swiatlo) {
        this.f_swiatlo = f_swiatlo;
    }

    public String getG_swiatlo() {
        return g_swiatlo;
    }

    public void setG_swiatlo(String g_swiatlo) {
        this.g_swiatlo = g_swiatlo;
    }

    @Override
    public String toString() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(data);
    }


}
