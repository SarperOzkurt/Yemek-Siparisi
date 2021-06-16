package com.example.evyemekleri.fragment.fragment_bagli_activity.anasayfa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evyemekleri.R;
import com.example.evyemekleri.api.Istekler.IlanYonetim;
import com.example.evyemekleri.api.Istekler.SiparisYonetim;
import com.example.evyemekleri.api.Istekler.YildizIlanYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.EldenOdeme;
import com.example.evyemekleri.bilesen.dialog.EldenOdeme.EldenOdemeListener;
import com.example.evyemekleri.bilesen.dialog.OdemeSecim;
import com.example.evyemekleri.fragment.activity_bagli_activity.SiparisActivity;
import com.example.evyemekleri.fragment.activity_bagli_activity.UrunDuzenleActivity;
import com.example.evyemekleri.fragment.activity_bagli_activity.anasayfa.ProfilDetayActivity;
import com.example.evyemekleri.fragment.fragment_bagli_activity.profil.UrunlerimActivity;
import com.example.evyemekleri.iletisim.AdresModel;
import com.example.evyemekleri.model.Adres;
import com.example.evyemekleri.model.Hesap;
import com.example.evyemekleri.model.Ilan;
import com.example.evyemekleri.model.Siparis;
import com.example.evyemekleri.model.YildizIlan;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrunDetayAcitivity extends AppCompatActivity implements View.OnClickListener, OdemeSecim.OdemeSecimListener, AdresModel.AdresModelListener,EldenOdemeListener {
    //View
    private LinearLayout llYildiz,llProfil;
    private TextView tvFiyat,tvOdemeTuru,tvIlanAd,tvDegerlendirme;
    private TextView tvProfilKullanici,tvProfilAd;
    private ImageView imgProfil,imgIlan;
    private Button btnSiparis;
    //
    private int ilanId,hesapId,benimId;
    private float yildizPuani;
    private AdresModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay_acitivity);
        init();
    }

    private void init(){
        //View bağlama
        llYildiz = findViewById(R.id.llYildiz);
        llProfil = findViewById(R.id.llProfilUDA);
        tvFiyat = findViewById(R.id.tvIlanFiyatUDA);
        tvOdemeTuru = findViewById(R.id.tvOdemeTuruUDA);
        tvIlanAd = findViewById(R.id.tvIlanAdUDA);
        tvProfilKullanici = findViewById(R.id.tvProfilKullaniciUDA);
        tvProfilAd = findViewById(R.id.tvProfilAd);
        imgProfil = findViewById(R.id.imgProfilUDA);
        imgIlan = findViewById(R.id.imgIlanUDA);
        btnSiparis = findViewById(R.id.btnSiparis);
        tvDegerlendirme = findViewById(R.id.tvDegerlendirme);
        //
        Bundle bundle = getIntent().getExtras();
        ilanId = bundle.getInt("ilanId");
        hesapId = bundle.getInt("hesapId");
        Log.d("hepsapIdd:",hesapId+"");
        benimId = bundle.getInt("id");

        model = new AdresModel(this);

        llProfil.setOnClickListener(this);
        btnSiparis.setOnClickListener(this);

        istekIlanDetay();
        yildizKontrol();
        yildizIslemi();
    }


    private ImageView img;
    private List<ImageView> listImageView;
    //YILDIZ İŞLEMİ
    public void yildizIslemi(){
        listImageView = new LinkedList<>();

        Drawable drawable = getResources().getDrawable(R.drawable.ic_star_empty,getTheme());

        for(int i =0;i<5;i++){
            img = new ImageView(this);
            img.setImageDrawable(drawable);
            img.setMinimumWidth(80);
            img.setMinimumHeight(80);
            listImageView.add(img);
        }
        ImageView img;
        for(int i =0;i<5;i++){
            img = listImageView.get(i);
            img.setClickable(true);

            final int position = i;

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    yildizTiklama(position);
                    istekYildizInsert(position);
                }
            });

            llYildiz.addView(img);
        }

    }
    public void yildizTiklama(float position){
        Drawable draw = getResources().getDrawable(R.drawable.ic_start,getTheme());
        Drawable draw2 = getResources().getDrawable(R.drawable.ic_star_empty,getTheme());
        for(int i =0;i<llYildiz.getChildCount();i++){
            llYildiz.getChildAt(i).setBackground(draw2);
        }
        Log.d("positionFloat",position+"");
        Log.d("positionInteger",(int)position+"");
        for (int i =0;i<=(int)position;i++){
            llYildiz.getChildAt(i).setBackground(draw);
        }
        yildizPuani =(float) position+1;
    }

    //istekler
    public void yildizKontrol(){
        YildizIlanYonetim.getInstance().dbYildizSorgu(ilanId,benimId)
                .enqueue(new Callback<YildizIlan>() {
                    @Override
                    public void onResponse(Call<YildizIlan> call, Response<YildizIlan> response) {
                        float yildiz = response.body().getYildiz();
                        if(yildiz!=-1){
                            yildizTiklama(yildiz-1);
                            for(int i =0;i<llYildiz.getChildCount();i++){
                                llYildiz.getChildAt(i).setClickable(false);

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<YildizIlan> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });

    }
    public void istekYildizInsert(final int position){
        YildizIlanYonetim.getInstance().dbYildizInsert(ilanId,benimId,(float)(position+1))
                .enqueue(new Callback<YildizIlan>() {
                    @Override
                    public void onResponse(Call<YildizIlan> call, Response<YildizIlan> response) {
                        if(response.isSuccessful()){
                            ToastMesaj.getInstance().basariMesaj(UrunDetayAcitivity.this,"Geri dönüşünüz için teşekkürler", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<YildizIlan> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });

    }
    public void istekIlanDetay(){
        IlanYonetim.getInstance().dbIlanDetay(ilanId)
                .enqueue(new Callback<Ilan>() {
                    @Override
                    public void onResponse(Call<Ilan> call, Response<Ilan> response) {
                        Log.d("body",response.body().toString());
                        veriBagla(response.body());
                    }

                    @Override
                    public void onFailure(Call<Ilan> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });
    }
    //veri bağlama
    public void veriBagla(Ilan ilan){


        tvIlanAd.setText(ilan.getIlanAd());
        tvFiyat.setText(ilan.getIlann().getFiyat()+" TL");
        tvOdemeTuru.setText(ilan.getIlann().getOdemeTuru());

        tvProfilKullanici.setText(ilan.getHesap().getKullaniciAdi());
        tvProfilAd.setText(ilan.getHesap().getAd());
        tvDegerlendirme.setText(ilan.getIlann().getDegerlendirme()+" kişi değerlendirdi");

        Picasso.get().load("http://192.168.1.35:8080"+ilan.getIlann().getIlanURL()).into(imgIlan);
        Picasso.get().load("http://192.168.1.35:8080"+ilan.getHesap().getProfilURL()).into(imgProfil);
    }



    @Override
    public void onClick(View v) {
        Intent intent =null ;
        switch (v.getId()){
            case R.id.llProfilUDA:
                intent = new Intent(UrunDetayAcitivity.this, ProfilDetayActivity.class);
                intent.putExtra("hesapId",hesapId);
                intent.putExtra("benimId",benimId);
                break;
            case R.id.btnSiparis:
                siparisTiklama(intent);
                break;
        }
        if(intent!=null)startActivity(intent);
    }

    public void siparisTiklama(Intent intent){
        int sonuc = odemeTuru();
        OdemeSecim odemeSecim = new OdemeSecim();
        if(sonuc == 1){
            intent = new Intent(UrunDetayAcitivity.this, SiparisActivity.class);
            intent.putExtra("hesapId",benimId);

            startActivity(intent);
        }
        else if (sonuc==2){
//            eldenOdeme.show(getSupportFragmentManager(),"elden");
            model.hepsiniGetir(benimId);
        }
        else if(sonuc == 3){
            odemeSecim.show(getSupportFragmentManager(),"her ikiside");
        }

    }

    public int odemeTuru(){
        String odemeTuru = tvOdemeTuru.getText().toString();

        if(odemeTuru.equals("Kredi Kartı")){
            return 1;
        }
        else if(odemeTuru.equals("Elden")){
            return 2;
        }
        else if(odemeTuru.equals("Her İkiside")){
            return 3;
        }
        return -1;
    }


    @Override
    public void krediKarti() {
        Intent intent = new Intent(UrunDetayAcitivity.this,SiparisActivity.class);
        intent.putExtra("hesapId",benimId);
        startActivity(intent);
    }

    @Override
    public void elden() {
        model.hepsiniGetir(benimId);
    }



    @Override
    public void mesaj() {

    }

    @Override
    public void adresGetir(List<Adres> listAdres) {
        EldenOdeme odeme = new EldenOdeme(listAdres);
        odeme.show(getSupportFragmentManager(),"elden");
    }

    @Override
    public void guncelleMesaj() {

    }

    @Override
    public void silMesaj() {

    }

    @Override
    public void kayitliAdresGetir(Hesap hesap) {

    }

    @Override
    public void siparis(int adresId) {
        SiparisYonetim.getInstance().siparisInsert(ilanId,benimId,adresId)
                .enqueue(new Callback<Siparis>() {
                    @Override
                    public void onResponse(Call<Siparis> call, Response<Siparis> response) {
                        if (response.isSuccessful()){
                            ToastMesaj.getInstance().basariMesaj(UrunDetayAcitivity.this,"Siparişiniz hazırlanıyor...",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Siparis> call, Throwable t) {
                        Log.e("err",t.getMessage());
                    }
                });
    }
}