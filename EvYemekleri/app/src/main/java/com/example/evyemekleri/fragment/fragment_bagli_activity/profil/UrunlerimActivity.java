package com.example.evyemekleri.fragment.fragment_bagli_activity.profil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.evyemekleri.R;
import com.example.evyemekleri.adapter.IlanAdapter;
import com.example.evyemekleri.api.Istekler.IlanYonetim;
import com.example.evyemekleri.fragment.activity_bagli_activity.UrunDuzenleActivity;
import com.example.evyemekleri.model.Ilan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrunlerimActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lvIlan;
    private List<Ilan> listIlan;

    private int hesapId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urunlerim);
        init();
    }

    public void init(){
        lvIlan = findViewById(R.id.lvIlanlarUA);
        lvIlan.setOnItemClickListener(this);
        getId();
        istek();

    }
    private void getId(){
        Bundle bundle = getIntent().getExtras();
        hesapId = bundle.getInt("hesapId");

    }

    private void istek(){

    IlanYonetim.getInstance().dbIlanHesapId(hesapId)
            .enqueue(new Callback<List<Ilan>>() {
                @Override
                public void onResponse(Call<List<Ilan>> call, Response<List<Ilan>> response) {
                    listIlan = response.body();

                    IlanAdapter adapter = new IlanAdapter(UrunlerimActivity.this,listIlan);
                    lvIlan.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Ilan>> call, Throwable t) {
                    Log.e("hata",t.getMessage());
                }
            });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("list",listIlan.get(position).toString());

        Intent intent = new Intent(UrunlerimActivity.this, UrunDuzenleActivity.class);
        intent.putExtra("ilanId",listIlan.get(position).getIlann().getId());
        startActivity(intent);
    }
}