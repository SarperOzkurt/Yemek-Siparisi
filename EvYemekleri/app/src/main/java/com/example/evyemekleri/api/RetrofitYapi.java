package com.example.evyemekleri.api;

import com.example.evyemekleri.api.arayüz.Cagri;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitYapi {
    private Cagri cagri ;

    public RetrofitYapi(String URL){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cagri = retrofit.create(Cagri.class);


    }

    public Cagri getCagri(){

        return cagri;
    }
}
