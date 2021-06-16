package com.example.evyemekleri.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.style.AlignmentSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evyemekleri.R;
import com.example.evyemekleri.api.Istekler.FavoriYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.destek.Destek;
import com.example.evyemekleri.model.Favori;
import com.example.evyemekleri.model.Ilan;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanAdapter extends BaseAdapter {

    private Context context;
    private List<Ilan> lstIlan;

    public IlanAdapter(Context context,List<Ilan> lstIlan){
        this.context = context;
        this.lstIlan = lstIlan;
    }
    @Override
    public int getCount() {
        return lstIlan.size();
    }

    @Override
    public Object getItem(int position) {
        return lstIlan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private ImageView imgYemekResim;
    private TextView tvYildiz,tvId,tvIlanAd;
    public ImageView imgFavori;
    private Drawable favori_0,favori_1;
    private int sayac = 0;
    Object obj ="0";
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_ilan,parent,false);
        init(view);

//        tvYildiz.setText(lstIlan.get(position).getYildizDeger().getYildiz()+""); yildiz eklenen yer
            tvYildiz.setText(lstIlan.get(position).getIlann().getYildiz()+"");
        tvId.setText(lstIlan.get(position).getIlann().getId()+"");
        tvIlanAd.setText(lstIlan.get(position).getIlann().getIlanAd());

         favori_0 = context.getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24,context.getTheme());
         favori_1 = context.getResources().getDrawable(R.drawable.ic_favori_1,context.getTheme());

         favoriSorgu(position,imgFavori);
        imgFavori.setTag(obj);

        imgFavori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriIslem(v,imgFavori,lstIlan.get(position).getIlann().getId());
            }
        });

        Log.d("resim","http://localhost:8080"+lstIlan.get(position).getIlann().getIlanURL());
//        imgYemekResim.setImageDrawable(Destek.loadImageFromWeb("http://192.168.1.37:8080"+lstIlan.get(position).getIlanURL()));
       Picasso.get().load("http://192.168.1.35:8080"+lstIlan.get(position).getIlann().getIlanURL()).into(imgYemekResim);
        sayac++;
        return view;
    }


    public void init(View v){
        imgYemekResim = v.findViewById(R.id.imgYemekResimAI);
        tvYildiz = v.findViewById(R.id.tvYildiz);
        tvId = v.findViewById(R.id.tvId);
        tvIlanAd = v.findViewById(R.id.tvIlanAd);
        imgFavori = v.findViewById(R.id.imgFavori);
    }


    //FAVORİ İŞLEMLERİ
    public void favoriIslem(View view,ImageView imgFavori,int ilanId){

        if(imgFavori.getTag().equals("0")){
            obj = "1";
            view.setBackground(favori_1);
            favoriInsert(ilanId);
            Log.d("favori","favori 0 ");
        }
         else if(imgFavori.getTag().equals("1")){
             obj ="0";
            view.setBackground(favori_0);
            favoriSil(ilanId);
            Log.d("favori","favori 1 ");
        }
        imgFavori.setTag(obj);
    }
    private int hesapId;
    public void setHesapId(int hesapId){
        this.hesapId = hesapId;
    }
    private void favoriInsert(int ilanId){
        FavoriYonetim.getInstance().dbFavoriInsert(ilanId,hesapId)
                .enqueue(new Callback<Favori>() {
                    @Override
                    public void onResponse(Call<Favori> call, Response<Favori> response) {
                        if(response.isSuccessful()){
                            ToastMesaj.getInstance().basariMesaj(context,"Favorilere eklendi",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Favori> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });

    }
    private void favoriSil(int ilanId){
        FavoriYonetim.getInstance().dbFavoriSil(ilanId)
                .enqueue(new Callback<Favori>() {
                    @Override
                    public void onResponse(Call<Favori> call, Response<Favori> response) {
                        if(response.isSuccessful()){
                            ToastMesaj.getInstance().basarisizMesaj(context,"Favoriden çıkarıldı",Toast.LENGTH_SHORT).show();
                            imgFavori.setImageDrawable(favori_0);
                        }
                    }

                    @Override
                    public void onFailure(Call<Favori> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });

    }
    private void favoriSorgu(final int position, final ImageView img){

        final int ilanId = lstIlan.get(position).getIlann().getId();

        FavoriYonetim.getInstance().getFavori()
                .enqueue(new Callback<List<Favori>>() {
                    @Override
                    public void onResponse(Call<List<Favori>> call, Response<List<Favori>> response) {
                        for (int i =0;i<response.body().size();i++){
                            if(response.body().get(i).getIlanId() == ilanId && response.body().get(i).getHesapId() == hesapId){
                                Log.d("ilanId",ilanId+"");
                                Log.d("hesapId",hesapId+"");

                                obj = "1";
                                img.setTag(obj);
                                img.setImageDrawable(favori_1);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Favori>> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });
    }
}
