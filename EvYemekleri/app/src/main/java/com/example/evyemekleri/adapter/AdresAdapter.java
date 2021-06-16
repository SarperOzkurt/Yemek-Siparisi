package com.example.evyemekleri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;

import com.example.evyemekleri.R;
import com.example.evyemekleri.model.Adres;

import java.util.List;

public class AdresAdapter extends BaseAdapter {

    private List<Adres> listAdres;
    private Context context;

    public AdresAdapter(List<Adres> listAdres,Context context){
        this.listAdres = listAdres;
        this.context = context;
    }
    @Override
    public int getCount() {
        return listAdres.size();
    }

    @Override
    public Object getItem(int position) {
        return listAdres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private TextView tvAdres,tvAdresId;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_adres,parent,false);

        init(view);

        tvAdresId.setText(listAdres.get(position).getId()+"");
        tvAdres.setText(listAdres.get(position).getAdres());
        return view;
    }

    public void init(View v){
        tvAdres = v.findViewById(R.id.tvAdresAdapter);
        tvAdresId = v.findViewById(R.id.tvAdresIdAdapter);
    }
}
