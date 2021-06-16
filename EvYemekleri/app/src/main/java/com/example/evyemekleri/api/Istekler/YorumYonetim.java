package com.example.evyemekleri.api.Istekler;

import com.example.evyemekleri.api.TemelYonetim;
import com.example.evyemekleri.model.Yorum;

import java.util.List;

import retrofit2.Call;

public class YorumYonetim extends TemelYonetim {

    private static YorumYonetim yonetim = new YorumYonetim();

    public static YorumYonetim getInstance(){

        return yonetim;
    }

    public Call<Yorum> insertYorum(String yorum,int hesapId,int yapanHesap){
        Call<Yorum> call = getTemelCagri().insertYorum(yorum, hesapId,yapanHesap);
        return  call;
    }
    public Call<List<Yorum>> getYorum(int hesapId){
        Call<List<Yorum>> call = getTemelCagri().getYorum(hesapId);
        return  call;
    }
    public Call<Yorum> getTekYorum(int id){
        Call<Yorum> call = getTemelCagri().getTekYorum(id);
        return  call;
    }

}
