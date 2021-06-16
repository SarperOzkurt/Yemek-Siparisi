package com.example.evyemekleri.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evyemekleri.R;
import com.example.evyemekleri.model.Siparis;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SiparisAdapter extends BaseAdapter {
    private List<Siparis> listSiparis;
    private Context context;

    public SiparisAdapter(List<Siparis> listSiparis,Context context){
        this.listSiparis = listSiparis;
        this.context = context;
    }
    @Override
    public int getCount() {
        return listSiparis.size();
    }

    @Override
    public Object getItem(int position) {
        return listSiparis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private ImageView imgIlan;
    private TextView tvAdres,tvIlanAd,tvSiparisId;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_siparisler,parent,false);
        init(view);

        Picasso.get().load("http://192.168.1.35:8080"+listSiparis.get(position).getIlann().getIlanURL()).into(imgIlan);
        tvIlanAd.setText(listSiparis.get(position).getIlann().getIlanAd());
        tvAdres.setText(listSiparis.get(position).getAdress().getAdres());
        tvSiparisId.setText(listSiparis.get(position).getId()+"");
        return view;
    }

    private void init(View view) {
        tvSiparisId = view.findViewById(R.id.tvSiparisId);
        imgIlan = view.findViewById(R.id.imgIlanAdapter);
        tvAdres = view.findViewById(R.id.tvIlanAdresAdapter);
        tvIlanAd = view.findViewById(R.id.tvIlanAdAdapter);
    }
}
