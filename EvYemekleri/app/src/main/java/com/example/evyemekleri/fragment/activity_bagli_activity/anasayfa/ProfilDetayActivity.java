package com.example.evyemekleri.fragment.activity_bagli_activity.anasayfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evyemekleri.R;
import com.example.evyemekleri.adapter.IlanAdapter;
import com.example.evyemekleri.api.Istekler.HesapYonetim;
import com.example.evyemekleri.api.Istekler.IlanYonetim;
import com.example.evyemekleri.api.Istekler.YorumYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.fragment.activity_bagli_activity.YorumlarActivity;
import com.example.evyemekleri.model.Hesap;
import com.example.evyemekleri.model.Ilan;
import com.example.evyemekleri.model.Yorum;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilDetayActivity extends AppCompatActivity implements View.OnClickListener {

    private int hesapId,benimId;
    private ImageView imgProfil,imgProfilYorum;
    private TextView tvKullaniciAdi,tvadSoyad;
    private TextView tvKullaniciAdiYorum,tvYorum;
    private ListView lvIlan;
    private List<Ilan> listIlan;
    private TextView tvDigerYorum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_detay);

        init();
        Log.d("hesapId: ",hesapId+"");
    }

    private void init(){
        Bundle bundle = getIntent().getExtras();
        imgProfil = findViewById(R.id.imgProfilPDA);
        tvKullaniciAdi = findViewById(R.id.tvKullaniciAdiPDA);
        tvadSoyad = findViewById(R.id.tvAdSoyadPDA);
        lvIlan = findViewById(R.id.lvIlanlarPDA);

        tvKullaniciAdiYorum = findViewById(R.id.tvKullaniciAdiYorum);
        imgProfilYorum = findViewById(R.id.imgProfilYorum);
        tvYorum = findViewById(R.id.tvYorum);

        hesapId = bundle.getInt("hesapId");
        benimId = bundle.getInt("benimId");
        tvDigerYorum = findViewById(R.id.tvDigerYorum);

        tvDigerYorum.setOnClickListener(this);
        istek();
    }
    private void istek(){
        HesapYonetim.getInstance().hesapSorgu(hesapId)
                .enqueue(new Callback<Hesap>() {
                    @Override
                    public void onResponse(Call<Hesap> call, Response<Hesap> response) {
                        Hesap hesap = response.body().getHesap();
                        veriBagla(hesap);
                    }

                    @Override
                    public void onFailure(Call<Hesap> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });
        IlanYonetim.getInstance().dbIlanHesapId(hesapId)
                .enqueue(new Callback<List<Ilan>>() {
                    @Override
                    public void onResponse(Call<List<Ilan>> call, Response<List<Ilan>> response) {
                        if(response.isSuccessful()){
                            listIlan = response.body();
                            IlanAdapter adapter = new IlanAdapter(ProfilDetayActivity.this,listIlan);
                            lvIlan.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Ilan>> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });

    }
    public void veriBagla(Hesap hesap){
        Picasso.get().load("http://192.168.1.35:8080"+hesap.getProfilURL()).into(imgProfil);
        tvKullaniciAdi.setText(hesap.getKullaniciAdi());
        tvadSoyad.setText(hesap.getAd()+" "+hesap.getSoyad());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ProfilDetayActivity.this, YorumlarActivity.class);
        intent.putExtra("hesapId",hesapId);
        intent.putExtra("benimId",benimId);
        startActivity(intent);
    }
}