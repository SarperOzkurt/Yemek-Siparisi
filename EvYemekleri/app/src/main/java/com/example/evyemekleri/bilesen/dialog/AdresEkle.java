package com.example.evyemekleri.bilesen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.evyemekleri.R;
import com.example.evyemekleri.bilesen.ToastMesaj;

public class AdresEkle extends AppCompatDialogFragment {
    private EditText edtAdres;
    AdresEkleListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_adres,null);

        builder.setView(view)
                .setTitle("Adres Ekle")
                .setNegativeButton("vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ekle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String adres = edtAdres.getText().toString();
                        if(!adres.trim().equals("")){
                            listener.adresKabul(adres);
                        }
                        else{
                            ToastMesaj.getInstance().basarisizMesaj(getActivity(),"Boş geçmeyiniz", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        edtAdres = view.findViewById(R.id.edtAdres);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listener = (AdresEkleListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()+" null");
        }
    }

    public interface AdresEkleListener{

        void adresKabul(String adres);

    }
}
