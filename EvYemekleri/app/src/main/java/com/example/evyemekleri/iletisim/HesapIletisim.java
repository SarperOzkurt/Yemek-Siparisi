package com.example.evyemekleri.iletisim;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.example.evyemekleri.adapter.KullanicilarAdapter;
import com.example.evyemekleri.api.Istekler.HesapYonetim;
import com.example.evyemekleri.model.Hesap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HesapIletisim  {
    private Context context;
    public HesapIletisim(Context context){
        this.context = context;
    }
    public void kullaniciAra(String kullaniciAdi, final ListView lvKullanicilar){
        HesapYonetim.getInstance().hesapHepsiGetir(kullaniciAdi)
                .enqueue(new Callback<List<Hesap>>() {
                    @Override
                    public void onResponse(Call<List<Hesap>> call, Response<List<Hesap>> response) {
                        KullanicilarAdapter adapter = new KullanicilarAdapter(response.body(),context);
                        lvKullanicilar.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Hesap>> call, Throwable t) {
                        Log.e("err",t.getMessage());
                    }
                });

    }

}
