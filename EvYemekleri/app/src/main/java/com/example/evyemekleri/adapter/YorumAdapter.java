package com.example.evyemekleri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evyemekleri.R;
import com.example.evyemekleri.model.Yorum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YorumAdapter extends BaseAdapter {

    private List<Yorum> listYorum;
    private Context context;

    public YorumAdapter(List<Yorum> listYorum,Context context){
        this.listYorum = listYorum;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listYorum.size();
    }

    @Override
    public Object getItem(int position) {
        return listYorum.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private TextView tvKullaniciAdi,tvYorum;
    private ImageView imgProfil;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_yorum,parent,false);
        init(view);

        tvKullaniciAdi.setText(listYorum.get(position).getHesap().getKullaniciAdi());
        tvYorum.setText(listYorum.get(position).getYorumm());
        Picasso.get().load("http://192.168.1.35:8080"+listYorum.get(position).getHesap().getProfilURL()).into(imgProfil);

        return view;
    }
    private void init(View v){
        tvKullaniciAdi = v.findViewById(R.id.tvKullaniciAdiAdapter);
        tvYorum = v.findViewById(R.id.tvYorumAdapter);
        imgProfil = v.findViewById(R.id.imgProfilAdapter);
    }
}
