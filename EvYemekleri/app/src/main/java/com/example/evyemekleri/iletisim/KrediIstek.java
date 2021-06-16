package com.example.evyemekleri.iletisim;

import android.content.Context;
import android.util.Log;

import com.example.evyemekleri.api.Istekler.KrediKartiYonetim;
import com.example.evyemekleri.model.KrediKarti;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KrediIstek {
    private KrediKartiListener listener;

    public KrediIstek(Context context){
        listener = (KrediKartiListener) context;
    }

    public void krediKartiSorgu(String isim,String kartNo,String tarih,String CVV,int hesapId){

        KrediKartiYonetim.getInstance().krediKartiSorgu(isim, kartNo, tarih, CVV, hesapId)
                .enqueue(new Callback<KrediKarti>() {
                    @Override
                    public void onResponse(Call<KrediKarti> call, Response<KrediKarti> response) {
                        if(response.isSuccessful()){
                            listener.basarili();

                        }
                    }

                    @Override
                    public void onFailure(Call<KrediKarti> call, Throwable t) {
                        Log.e("err",t.getMessage());
                        listener.basarisiz();
                    }
                });

    }

    public interface KrediKartiListener {

        void basarili();
        void basarisiz();
    }
}
