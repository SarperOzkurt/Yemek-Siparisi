package com.example.evyemekleri.iletisim;

import android.content.Context;
import android.util.Log;

import com.example.evyemekleri.api.Istekler.SiparisYonetim;
import com.example.evyemekleri.model.Siparis;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiparisModel {
    private SiparisListener listener;

    public SiparisModel(Context context){
        listener = (SiparisListener) context;
    }

    public void siparisSorgu(int hesapId){

        SiparisYonetim.getInstance().sipariSorgu(hesapId)
                .enqueue(new Callback<List<Siparis>>() {
                    @Override
                    public void onResponse(Call<List<Siparis>> call, Response<List<Siparis>> response) {

                        listener.siparisGetir(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Siparis>> call, Throwable t) {
                        Log.e("err", t.getMessage());
                    }
                });

    }
    public void siparisSil(int id){
        SiparisYonetim.getInstance().siparisSil(id)
                .enqueue(new Callback<Siparis>() {
                    @Override
                    public void onResponse(Call<Siparis> call, Response<Siparis> response) {
                        if(response.isSuccessful()){
                            listener.sil();
                        }
                    }

                    @Override
                    public void onFailure(Call<Siparis> call, Throwable t) {
                        Log.e("err",t.getMessage());
                    }
                });

    }
    public interface SiparisListener{
        void siparisGetir(List<Siparis> listSiparis);
        void sil();
    }
}
