package com.example.evyemekleri.fragment.activity_bagli_frag.anaekran;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.evyemekleri.R;
import com.example.evyemekleri.api.Istekler.HesapYonetim;
import com.example.evyemekleri.fragment.fragment_bagli_activity.profil.AdreslerimActivity;
import com.example.evyemekleri.fragment.fragment_bagli_activity.profil.FavorilerimActivity;
import com.example.evyemekleri.fragment.fragment_bagli_activity.profil.SiparislerimActivity;
import com.example.evyemekleri.fragment.fragment_bagli_activity.profil.UrunlerimActivity;
import com.example.evyemekleri.fragment.fragment_bagli_fragment.BilgiFragment;
import com.example.evyemekleri.fragment.fragment_bagli_fragment.DuzenleFragment;
import com.example.evyemekleri.model.Hesap;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment implements View.OnClickListener, DuzenleFragment.DuzenleListener {

    private ImageView imgProfil,imgDuzenle,imgKapat;
    private TextView tvKullaniciAdi,tvAdSoyad;
    private Hesap hesap;
    private LinearLayout llUrunler,llFavoriler,llAdreslerim,llSiparislerim;

    public ProfilFragment(Hesap hesap){
        this.hesap = hesap;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil,container,false);
        init(view);


        return view;
    }

    private void init(View v){
        imgProfil = v.findViewById(R.id.imgProfilPF);
        imgDuzenle = v.findViewById(R.id.imgDuzenle);
        imgKapat = v.findViewById(R.id.imgKapat);
        tvKullaniciAdi = v.findViewById(R.id.tvKullaniciAdiPF);
        tvAdSoyad = v.findViewById(R.id.tvAdSoyadPF);
        llUrunler = v.findViewById(R.id.ll_urunlerim);
        llFavoriler = v.findViewById(R.id.llFavorilerim);
        llAdreslerim = v.findViewById(R.id.llAdreslerim);
        llSiparislerim = v.findViewById(R.id.llSiparislerim);

        tvKullaniciAdi.setText(hesap.getKullaniciAdi());
        tvAdSoyad.setText(hesap.getAd()+" "+hesap.getSoyad());


        Picasso.get().load("http://192.168.1.35:8080"+hesap.getProfilURL()).into(imgProfil);

        fragmentYukle(new BilgiFragment(hesap));

        imgDuzenle.setOnClickListener(this);
        imgKapat.setOnClickListener(this);
        llUrunler.setOnClickListener(this);
        llFavoriler.setOnClickListener(this);
        llAdreslerim.setOnClickListener(this);
        llSiparislerim.setOnClickListener(this);
    }

    public void fragmentYukle(Fragment fragment){
        if(fragment!=null){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragmentProfil,fragment)
                    .commit();
        }

    }


    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        Intent intent = null;
        switch (v.getId()){
            case R.id.imgDuzenle:
                fragment = new DuzenleFragment(hesap);
                fragmentYukle(fragment);
                imgDuzenle.setVisibility(View.INVISIBLE);
                imgKapat.setVisibility(View.VISIBLE);
            break;
            case R.id.imgKapat:
                imgDuzenle.setVisibility(View.VISIBLE);
                imgKapat.setVisibility(View.INVISIBLE);
                fragmentYukle(new BilgiFragment(hesap));
//                fragmentYukle(new BilgiFragment(hesap.getHesap()));

                break;
                //Profil ayarlana işlemleri
            case R.id.ll_urunlerim:
                intent = new Intent(getActivity(), UrunlerimActivity.class);
                intent.putExtra("hesapId",hesap.getId());
                break;
            case R.id.llFavorilerim:
                intent = new Intent(getActivity(), FavorilerimActivity.class);
                intent.putExtra("hesapId",hesap.getId());
                break;
            case R.id.llAdreslerim:
                intent = new Intent(getActivity(), AdreslerimActivity.class);
                intent.putExtra("hesapId",hesap.getId());
                break;
            case R.id.llSiparislerim:
                intent = new Intent(getActivity(), SiparislerimActivity.class);
                intent.putExtra("hesapId",hesap.getId());
        }
        if(intent!=null) startActivity(intent);


    }


    @Override
    public void yeniHesap(Hesap hesap) {
//        Log.d("yeni hesap profil",hesap.toString())
    }
}
