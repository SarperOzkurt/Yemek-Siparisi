package com.example.evyemekleri.fragment.activity_bagli_frag.anaekran;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.evyemekleri.AnaEkranActivity;
import com.example.evyemekleri.R;
import com.example.evyemekleri.adapter.KullanicilarAdapter;
import com.example.evyemekleri.fragment.activity_bagli_activity.anasayfa.ProfilDetayActivity;
import com.example.evyemekleri.iletisim.HesapIletisim;
import com.example.evyemekleri.model.Hesap;

import java.util.List;

public class AramaFragment extends Fragment implements TextWatcher, AdapterView.OnItemClickListener {
    private Hesap hesap;
    private HesapIletisim iletisim;

    public AramaFragment(Hesap hesap){
        this.hesap = hesap;
    }


    private EditText edtAra;
    private ListView lvKullanicilar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        iletisim = new HesapIletisim(getActivity());

        View view = inflater.inflate(R.layout.fragment_arama,container,false);
        init(view);

        return view;
    }

    private void init(View view) {
        edtAra = view.findViewById(R.id.edtArama);
        lvKullanicilar = view.findViewById(R.id.lvKullanicilar);

        lvKullanicilar.setOnItemClickListener(this);
        edtAra.addTextChangedListener(this);


    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //iletisim.kullaniciAra();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
       // Log.d("text",edtAra.getText().toString());
        if(edtAra.getText().toString().equals("")){
            Log.d("ontext",  "boş");
            lvKullanicilar.setVisibility(View.INVISIBLE);
        }
        else{
            lvKullanicilar.setVisibility(View.VISIBLE);
            iletisim.kullaniciAra(edtAra.getText().toString(),lvKullanicilar);

        }


    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       LinearLayout ll = (LinearLayout) lvKullanicilar.getChildAt(position);
       LinearLayout ll2 =(LinearLayout) ll.getChildAt(0);
       TextView tv = (TextView) ll2.getChildAt(0);

        Intent intent = new Intent(getActivity(), ProfilDetayActivity.class);
        intent.putExtra("hesapId",Integer.parseInt(tv.getText().toString()));
        intent.putExtra("benimId",hesap.getId());
        startActivity(intent);
    }
}
