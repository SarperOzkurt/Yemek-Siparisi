package com.example.evyemekleri.api.Istekler;

import com.example.evyemekleri.api.TemelYonetim;
import com.example.evyemekleri.model.KrediKarti;

import retrofit2.Call;

public class KrediKartiYonetim extends TemelYonetim {

    private static KrediKartiYonetim yonetim = new KrediKartiYonetim();

    public static KrediKartiYonetim getInstance(){
        return yonetim;
    }

    public Call<KrediKarti> krediKartiSorgu(String isim,String kartNo,String tarih,String CVV,int hesapId){
        Call<KrediKarti> call = getTemelCagri().krediKartiSorgula(isim, kartNo, tarih, CVV, hesapId);
        return call;
    }

}
