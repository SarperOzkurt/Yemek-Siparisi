package com.example.evyemekleri.model;

import com.example.evyemekleri.destek.Destek;

public class S {

    private static S s = new S();
    private static Destek destek;

    public static synchronized S getInstance(){
        destek = new Destek();
        return s;
    }

    public final String URL = "http://192.168.1.35:8080/";




}
