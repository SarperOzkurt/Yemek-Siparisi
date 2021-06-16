package com.example.evyemekleri.bilesen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.evyemekleri.R;
import com.example.evyemekleri.adapter.AdresAdapter;
import com.example.evyemekleri.model.Adres;

import java.util.ArrayList;
import java.util.List;

public class AdresSec extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {

    private Spinner spnAdres;
    private AdaresSecListener listener;
    //Object[] object = {"aşlsdkalsdkşalsdkşasldaşsdkşalsdkşalskdalşskdlklsfkd","alsdfakşsdfksşldfkşsldmfşlkSDFLŞKFJSL"};
    private List<Adres> listAdres;
    private Context context;

    public AdresSec(List<Adres> listAdres){
        this.listAdres = listAdres;
    }
    public AdresSec(){}
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable  Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


       View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_adres_sec,null);
       builder.setView(view)
       .setTitle("Adres Seç")
       .setPositiveButton("tamam", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               
           }

       });
       spnAdres = view.findViewById(R.id.spnAdresler);
        spnAdres.setOnItemSelectedListener(this);
        if(listAdres!=null){
//            ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,listAdres);
            AdresAdapter adresAdapter = new AdresAdapter(listAdres,getActivity());
            spnAdres.setAdapter(adresAdapter);
        }

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (AdaresSecListener) context;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String adres = object[position].toString();
        String adres = listAdres.get(position).getAdres();
        listener.getadres(adres);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface AdaresSecListener{

        void getadres(String adres);

    }

}
