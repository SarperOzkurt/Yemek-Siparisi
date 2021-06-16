package com.example.evyemekleri.api.Istekler;

import com.example.evyemekleri.api.TemelYonetim;
import com.example.evyemekleri.model.Favori;
import com.example.evyemekleri.model.Ilan;

import java.util.List;

import retrofit2.Call;

public class FavoriYonetim extends TemelYonetim {

    private static FavoriYonetim favoriYonetim = new FavoriYonetim();

    public static FavoriYonetim getInstance(){

        return favoriYonetim;
    }

    public Call<Favori> dbFavoriInsert(int ilanId,int hesapId){
        Call<Favori> call = getTemelCagri().dbFavoriInsert(ilanId,hesapId);
        return call;
    }
    public Call<Favori> dbFavoriSil(int ilanId){
        Call<Favori> call = getTemelCagri().dbFavoriSil(ilanId);
        return call;
    }
    public Call<List<Favori>> getFavori(){
        Call<List<Favori>> call = getTemelCagri().getFavori();
        return call;
    }

    public Call<List<Ilan>> getFavoriHesapId(int hesapId){
        Call<List<Ilan>> call = getTemelCagri().getFavoriHesapId(hesapId);
        return call;
    }

}
