package com.example.evyemekleri.fragment.fragment_bagli_activity.profil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.evyemekleri.R;
import com.example.evyemekleri.adapter.IlanAdapter;
import com.example.evyemekleri.api.Istekler.FavoriYonetim;
import com.example.evyemekleri.model.Favori;
import com.example.evyemekleri.model.Ilan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavorilerimActivity extends AppCompatActivity {

    private ListView lvFavlar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorilerim);
        init();

    }
    private int hesapId;
    private void init(){
        lvFavlar = findViewById(R.id.lvFavlar);

        Bundle bundle = getIntent().getExtras();
        hesapId = bundle.getInt("hesapId");

        istek();
    }
    private void istek(){
        FavoriYonetim.getInstance().getFavoriHesapId(hesapId)
                .enqueue(new Callback<List<Ilan>>() {
                    @Override
                    public void onResponse(Call<List<Ilan>> call, Response<List<Ilan>> response) {
                        Log.d("response",response.body().toString());
                        IlanAdapter adapter = new IlanAdapter(FavorilerimActivity.this,response.body());
                        lvFavlar.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Ilan>> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });

    }


}