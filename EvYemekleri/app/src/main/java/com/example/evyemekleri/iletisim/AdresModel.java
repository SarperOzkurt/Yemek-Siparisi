package com.example.evyemekleri.iletisim;

import android.content.Context;
import android.util.Log;

import com.example.evyemekleri.api.Istekler.AdresYonetim;
import com.example.evyemekleri.model.Adres;
import com.example.evyemekleri.model.Hesap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdresModel {

    private AdresModelListener listener;

    public AdresModel(Context context){
        listener = (AdresModelListener) context;
    }

    public void insert(String adres,int hesapId){

        AdresYonetim.getInstance().insertAdres(adres,hesapId)
                .enqueue(new Callback<Adres>() {
                    @Override
                    public void onResponse(Call<Adres> call, Response<Adres> response) {
                        if(response.isSuccessful()){
                            listener.mesaj();
                        }
                    }

                    @Override
                    public void onFailure(Call<Adres> call, Throwable t) {
                        Log.e("err", t.getMessage());
                    }
                });

    }
    public void hepsiniGetir(int hesapId){
        AdresYonetim.getInstance().adresGetir(hesapId)
        .enqueue(new Callback<List<Adres>>() {
            @Override
            public void onResponse(Call<List<Adres>> call, Response<List<Adres>> response) {
                if(response.isSuccessful()){
                    List<Adres> listAdres = response.body();
                    listener.adresGetir(listAdres);
                }
            }

            @Override
            public void onFailure(Call<List<Adres>> call, Throwable t) {
                Log.e("err", t.getMessage());
            }
        });

    }
    public void adresGuncelle(int hesapId,String adres){
        AdresYonetim.getInstance().adresGuncelle(hesapId,adres)
        .enqueue(new Callback<Adres>() {
            @Override
            public void onResponse(Call<Adres> call, Response<Adres> response) {
                if(response.isSuccessful()){
                        listener.guncelleMesaj();
                }
            }

            @Override
            public void onFailure(Call<Adres> call, Throwable t) {
                Log.d("hata", t.getMessage());
            }
        });

    }
    public void AdresSil(int id){
        AdresYonetim.getInstance().adresSil(id)
                .enqueue(new Callback<Adres>() {
                    @Override
                    public void onResponse(Call<Adres> call, Response<Adres> response) {
                        if(response.isSuccessful()){
                            listener.silMesaj();
                        }
                    }

                    @Override
                    public void onFailure(Call<Adres> call, Throwable t) {
                        Log.d("hata", t.getMessage());
                    }
                });

    }
    public void kayitliAdresGetir(int id){
        AdresYonetim.getInstance().kayitliAdresGetir(id)
                .enqueue(new Callback<Hesap>() {
                    @Override
                    public void onResponse(Call<Hesap> call, Response<Hesap> response) {
                        if(response.isSuccessful()){
                            listener.kayitliAdresGetir(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Hesap> call, Throwable t) {
                        Log.e("err", t.getMessage());
                    }
                });
    }
    public interface AdresModelListener{

        void mesaj();
        void adresGetir(List<Adres> listAdres);
        void guncelleMesaj();
        void silMesaj();
        void kayitliAdresGetir(Hesap hesap);
    }


}
