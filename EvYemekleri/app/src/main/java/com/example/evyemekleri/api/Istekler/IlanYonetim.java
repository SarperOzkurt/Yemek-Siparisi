package com.example.evyemekleri.api.Istekler;

import com.example.evyemekleri.api.TemelYonetim;
import com.example.evyemekleri.model.Ilan;

import java.util.List;

import retrofit2.Call;

public class IlanYonetim extends TemelYonetim {


    private static IlanYonetim ilanYonetim = new IlanYonetim();


    public static IlanYonetim getInstance(){

        return ilanYonetim;
    }

    public Call<Ilan> andResimInsert(String resimByte,String resimYol){
        Call<Ilan> call = getTemelCagri().andResimInsert(resimByte, resimYol);
        return call;

    }
    public Call<Ilan> dbIlanInsert(String ilanURL,String ilanAd,String odemeTuru,float fiyat,int id){
        Call<Ilan> call = getTemelCagri().dbIlanInsert(ilanURL, ilanAd, odemeTuru,fiyat, id);
        return call;

    }
    public Call<List<Ilan>> dbIlanSorgu(){
        Call<List<Ilan>> call = getTemelCagri().dbIlanSorgu();

        return call;
    }
    public Call<List<Ilan>> dbIlanHesapId(int id){
        Call<List<Ilan>> call = getTemelCagri().dbIlanHesapId(id);
        return call;
    }
    public Call<Ilan> dbIlanId (int id){
        Call<Ilan> call = getTemelCagri().dbIlanId(id);
        return call;
    }
    public Call<Ilan> dbIlanSil(int id){
        Call<Ilan> call = getTemelCagri().dbIlanSil(id);
        return call;
    }
    public Call<Ilan> dbIlanGuncelle(int id,String ilanAd,String odemeTuru,float fiyat){
        Call<Ilan> call = getTemelCagri().dbIlanGuncelle(id, ilanAd, odemeTuru, fiyat);
        return call;
    }
    public Call<Ilan> dbIlanDetay(int id){
        Call<Ilan> call = getTemelCagri().dbIlanDetay(id);
        return call;
    }
}
