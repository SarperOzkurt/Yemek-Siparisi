package com.example.evyemekleri.bilesen.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.evyemekleri.fragment.fragment_bagli_activity.profil.SiparislerimActivity;
import com.example.evyemekleri.iletisim.SiparisModel;
import com.example.evyemekleri.model.Siparis;

public class SiparisSil extends AppCompatDialogFragment {

    private int id;
    private SiparisModel model;
    public SiparisSil (int id){
        this.id = id;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        model = new SiparisModel(getActivity());

        builder.setTitle("Sipariş iptal")
                .setNegativeButton("vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("iptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        model.siparisSil(id);
                    }
                });

        return builder.create();
    }


}
