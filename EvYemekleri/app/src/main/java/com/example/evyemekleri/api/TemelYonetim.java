package com.example.evyemekleri.api;

import com.example.evyemekleri.api.arayüz.Cagri;
import com.example.evyemekleri.model.S;

import retrofit2.Call;

public class TemelYonetim {

    public Cagri getTemelCagri(){
        RetrofitYapi yapi = new RetrofitYapi(S.getInstance().URL);
        return yapi.getCagri();
    }


}
