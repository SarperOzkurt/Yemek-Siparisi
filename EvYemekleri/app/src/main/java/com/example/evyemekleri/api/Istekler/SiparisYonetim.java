package com.example.evyemekleri.api.Istekler;

import com.example.evyemekleri.api.TemelYonetim;
import com.example.evyemekleri.model.Siparis;

import java.util.List;

import retrofit2.Call;

public class SiparisYonetim extends TemelYonetim {

    private static SiparisYonetim siparis = new SiparisYonetim();

    public static SiparisYonetim getInstance(){
        return siparis;
    }

    public Call<Siparis> siparisInsert(int ilanId,int hesapId,int adresId){
        Call<Siparis> call = getTemelCagri().siparisInsert(ilanId, hesapId, adresId);
        return call;
    }
    public Call<List<Siparis>> sipariSorgu(int hesapId){
        Call<List<Siparis>> call = getTemelCagri().siparisSorgu(hesapId);
        return call;
    }
    public Call<Siparis> siparisSil(int id){
        Call<Siparis> call = getTemelCagri().siparisSil(id);
        return call;
    }


}
