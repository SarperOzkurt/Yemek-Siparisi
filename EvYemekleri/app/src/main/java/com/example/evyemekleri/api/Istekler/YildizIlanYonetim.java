package com.example.evyemekleri.api.Istekler;

import com.example.evyemekleri.api.TemelYonetim;
import com.example.evyemekleri.model.YildizIlan;

import retrofit2.Call;

public class YildizIlanYonetim extends TemelYonetim {

    private static YildizIlanYonetim yonetim = new YildizIlanYonetim();

    public static YildizIlanYonetim getInstance(){

        return yonetim;
    }


    public Call<YildizIlan> dbYildizInsert(int ilanId,int hesapId,float yildiz){
        Call<YildizIlan> call = getTemelCagri().dbYildizInsert(ilanId, hesapId,yildiz);
        return call;
    }
    public Call<YildizIlan> dbYildizSorgu(int ilanId,int hesapId){
        Call<YildizIlan> call = getTemelCagri().dbYildizSorgu(ilanId, hesapId);
        return call;
    }
}
