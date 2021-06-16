package com.example.evyemekleri.bilesen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.iletisim.AdresModel;
import com.example.evyemekleri.model.Adres;
import com.example.evyemekleri.model.Hesap;

import java.util.List;

public class AdresSil extends AppCompatDialogFragment implements AdresModel.AdresModelListener {

    private AdresSilListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        listener = (AdresSilListener)getActivity();

        builder.setTitle("Sil")
                .setNegativeButton("vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .setPositiveButton("sil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.adresSil();
                    }
                });

        return builder.create();
    }
    public interface AdresSilListener{
        void adresSil();

    }

    @Override
    public void mesaj() {

    }

    @Override
    public void adresGetir(List<Adres> listAdres) {

    }

    @Override
    public void guncelleMesaj() {

    }

    @Override
    public void silMesaj() {
        ToastMesaj.getInstance().basariMesaj(getActivity(),"Silindi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void kayitliAdresGetir(Hesap hesap) {

    }
}
