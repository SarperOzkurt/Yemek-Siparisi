package com.example.evyemekleri.fragment.fragment_bagli_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.evyemekleri.R;
import com.example.evyemekleri.model.Hesap;

public class BilgiFragment extends Fragment {
    private Hesap hesap;
    private TextView tvEmail,tvTelNo;

    public BilgiFragment (Hesap hesap){
        this.hesap = hesap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bilgi,container,false);
        init(view);

        return view;
    }

    private void init(View v){
        tvEmail = v.findViewById(R.id.tvEmail);
        tvTelNo = v.findViewById(R.id.tvTelNo);

        tvEmail.setText(hesap.getEmail());
        tvTelNo.setText(hesap.getTelNo());
    }
}
