package com.example.evyemekleri.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.evyemekleri.R;
import com.example.evyemekleri.model.Hesap;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KullanicilarAdapter extends BaseAdapter {
    private List<Hesap> listHesap;
    private Context context;

    public KullanicilarAdapter(List<Hesap> listHesap, Context context) {
        this.listHesap = listHesap;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listHesap.size();
    }

    @Override
    public Object getItem(int position) {
        return listHesap.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private TextView tvHesapId,tvKullaniciAdi;
    private ImageView imgProfilFoto;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_kullanicilar,parent,false);
        init(view);
        Log.d("burda", "buarası");
        tvHesapId.setText(listHesap.get(position).getId()+"");
        tvKullaniciAdi.setText(listHesap.get(position).getKullaniciAdi());
        Picasso.get().load("http://192.168.1.35:8080"+listHesap.get(position).getProfilURL()).into(imgProfilFoto);
        return view;
    }

    private void init(View view) {
        tvHesapId = view.findViewById(R.id.tvHesapId);
        tvKullaniciAdi = view.findViewById(R.id.tvKullaniciAdi);
        imgProfilFoto = view.findViewById(R.id.imgProfilFoto);
    }
}
