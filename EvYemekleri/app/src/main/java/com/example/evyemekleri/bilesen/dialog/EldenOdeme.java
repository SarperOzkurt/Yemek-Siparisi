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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearSmoothScroller;

import com.example.evyemekleri.R;
import com.example.evyemekleri.adapter.AdresAdapter;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.iletisim.AdresModel;
import com.example.evyemekleri.model.Adres;
import com.example.evyemekleri.model.Hesap;

import java.util.List;

public class EldenOdeme extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {

    private EldenOdemeListener listener;
    private Spinner spnAdres;
    private List<Adres> listAdres;
    private Onay cOnay;
    public EldenOdeme(List<Adres> listAdres){
        this.listAdres = listAdres;
        cOnay = new Onay();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (EldenOdemeListener) context;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_adres_sec,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Elden Ödeme")
                .setView(view)
                .setPositiveButton("sipariş", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!(listAdres.size()<=0)){
//                            listener.siparis();
                            listener.siparis(cOnay.getOnay());
                        }
                    }
                })
                .setNegativeButton("vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        spnAdres = view.findViewById(R.id.spnAdresler);
        spnAdres.setOnItemSelectedListener(this);
        if(listAdres!=null){
            AdresAdapter adapter = new AdresAdapter(listAdres,getActivity());
            spnAdres.setAdapter(adapter);
        }

        return builder.create();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cOnay.setOnay(listAdres.get(position).getId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public interface EldenOdemeListener{
        void siparis(int adresId);
    }
    public class Onay{
        private int adresId;

        public void setOnay(int adresId){
            this.adresId = adresId;
        }
        public int getOnay(){
            return adresId;
        }

    }

}
