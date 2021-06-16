package com.example.evyemekleri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.evyemekleri.api.Istekler.HesapYonetim;
import com.example.evyemekleri.fragment.activity_bagli_frag.anaekran.AnaSayfaFragment;
import com.example.evyemekleri.fragment.activity_bagli_frag.anaekran.AramaFragment;
import com.example.evyemekleri.fragment.activity_bagli_frag.anaekran.IlanVerFragment;
import com.example.evyemekleri.fragment.activity_bagli_frag.anaekran.ProfilFragment;
import com.example.evyemekleri.fragment.fragment_bagli_fragment.DuzenleFragment;
import com.example.evyemekleri.iletisim.HesapIletisim;
import com.example.evyemekleri.model.Hesap;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AnaEkranActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, DuzenleFragment.DuzenleListener {
    private BottomNavigationView bnv;
    private Hesap yeniHesap;
    private Hesap hesap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_ekran);

        init();

    }

    private void init(){
        bnv = findViewById(R.id.bnv);
        Log.d("id",hesapId()+"");
        bnv.setOnNavigationItemSelectedListener(this);
        istek();
//        fragmentYukle(new AnaSayfaFragment(hesap));
    }
    private int hesapId(){
        Bundle bundle = getIntent().getExtras();

        return bundle.getInt("id");

    }

    private boolean fragmentYukle(Fragment fragment){

        if(fragment!=null){
            getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.flFragmentsAEA,fragment)
            .commit();

            return true;
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()){

            case R.id.mProfil:
                if(yeniHesap!=null) this.hesap = yeniHesap;

                fragment = new ProfilFragment(hesap);

                break;
            case R.id.mIlan:
                fragment = new IlanVerFragment(hesap);
                break;
            case R.id.mAnasayfa:
                fragment = new AnaSayfaFragment(hesap);
                break;
            case R.id.mAra:
                fragment = new AramaFragment(hesap);
                break;
        }



        return fragmentYukle(fragment);
    }

    private void istek(){
        HesapYonetim.getInstance().dbAnaEkran(hesapId())
                .enqueue(new Callback<Hesap>() {
                    @Override
                    public void onResponse(Call<Hesap> call, Response<Hesap> response) {
                        setHesap(response.body().getHesap());
                    }

                    @Override
                    public void onFailure(Call<Hesap> call, Throwable t) {

                    }
                });

    }
    public void setHesap(Hesap hesap){
        this.hesap = hesap;
        Log.d("default", this.hesap.toString());
        fragmentYukle(new AnaSayfaFragment(this.hesap));

    }

    @Override
    public void yeniHesap(Hesap hesap) {
        this.yeniHesap = hesap;
    }


    public interface KullaniciSonuc{
        void sonuc(List<Hesap> listHesap);

    }

}