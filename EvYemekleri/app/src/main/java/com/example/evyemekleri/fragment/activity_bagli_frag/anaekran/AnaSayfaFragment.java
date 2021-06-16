package com.example.evyemekleri.fragment.activity_bagli_frag.anaekran;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.evyemekleri.R;
import com.example.evyemekleri.adapter.IlanAdapter;
import com.example.evyemekleri.api.Istekler.IlanYonetim;
import com.example.evyemekleri.fragment.fragment_bagli_activity.anasayfa.UrunDetayAcitivity;
import com.example.evyemekleri.model.Hesap;
import com.example.evyemekleri.model.Ilan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnaSayfaFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView lvIlan;
    private List<Ilan> ilan;
    private Hesap hesap;
    public AnaSayfaFragment(Hesap hesap ){
        this.hesap = hesap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_anasayfa,container,false);

        init(v);

        return v;
    }

    public void init(View v){
        lvIlan = v.findViewById(R.id.lvIlanlar);
        istek();
        lvIlan.setOnItemClickListener(this);
        isImage= false;
    }

    public void istek(){
        IlanYonetim.getInstance().dbIlanSorgu()
                .enqueue(new Callback<List<Ilan>>() {
                    @Override
                    public void onResponse(Call<List<Ilan>> call, Response<List<Ilan>> response) {
                        ilan = response.body();
                        IlanAdapter adapter = new IlanAdapter(getActivity(),ilan);
                        adapter.setHesapId(hesap.getId());
                        lvIlan.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Ilan>> call, Throwable t) {
                        Log.d("hata",t.getMessage());
                    }
                });
    }
    //LİSTE TIKLAMA VE FAVORİ TIKLAMA İŞLEMLERİ
    private boolean isImage ;

    private ImageView imgFavori;

    @Override
    public void onClick(View v) {
        isImage = true;



    }
    RelativeLayout rl;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("ilan",ilan.get(position).toString());
        Log.d("ilanId",ilan.get(position).getId()+"");

        Intent inten = new Intent(getActivity(), UrunDetayAcitivity.class);
        inten.putExtra("ilanId",ilan.get(position).getIlann().getId());
        inten.putExtra("hesapId",ilan.get(position).getIlann().getHesapId());
        inten.putExtra("id",hesap.getId());
        Log.d("bool: false ",isImage+"" );
        startActivity(inten);
    }


}
