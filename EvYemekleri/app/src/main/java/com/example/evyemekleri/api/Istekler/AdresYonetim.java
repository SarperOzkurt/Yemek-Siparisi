package com.example.evyemekleri.api.Istekler;

import com.example.evyemekleri.api.TemelYonetim;
import com.example.evyemekleri.model.Adres;
import com.example.evyemekleri.model.Hesap;

import java.util.List;

import retrofit2.Call;

public class AdresYonetim extends TemelYonetim {

    private static AdresYonetim adresYonetim = new AdresYonetim();

    public static AdresYonetim getInstance(){
        return adresYonetim;
    }

    public Call<Adres> insertAdres(String adres,int hesapId){
        Call<Adres> call = getTemelCagri().insertAdres(adres, hesapId);
        return call;
    }
    public Call<List<Adres>> adresGetir(int hesapId){
        Call<List<Adres>> call = getTemelCagri().adresGetir(hesapId);
        return call;
    }
    public Call<Adres> adresGuncelle(int hesapId,String adres){
        Call<Adres> call = getTemelCagri().adresGuncelle(hesapId,adres);
        return call;
    }
    public Call<Adres> adresSil(int id){
        Call<Adres> call = getTemelCagri().adresSil(id);
        return call;
    }
    public Call<Hesap> kayitliAdresGetir(int id){
        Call<Hesap> call = getTemelCagri().kayitliAdresGetir(id);
        return call;
    }
}
