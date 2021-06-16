package com.example.evyemekleri.fragment.activity_bagli_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evyemekleri.R;
import com.example.evyemekleri.api.Istekler.SiparisYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.AdresEkle;
import com.example.evyemekleri.bilesen.dialog.AdresSec;
import com.example.evyemekleri.destek.Destek;
import com.example.evyemekleri.iletisim.AdresModel;
import com.example.evyemekleri.iletisim.KrediIstek;
import com.example.evyemekleri.model.Adres;
import com.example.evyemekleri.model.Hesap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiparisActivity extends AppCompatActivity implements View.OnClickListener, AdresEkle.AdresEkleListener, AdresSec.AdaresSecListener
, AdresModel.AdresModelListener, KrediIstek.KrediKartiListener {

    private EditText edtKartNo,edtKartIsim;
    private EditText edtCVV,edtTarih;
    private TextView tvAdres;
    private Button btnOnayla;
    private Destek destek;
    private AdresModel model;
    private AdresSec sec;
    private List<Adres> listAdres;
    private KrediIstek krediIstek;

    private int hesapId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis);

        init();
    }

    public void init(){
        edtKartNo = findViewById(R.id.edtKartNo);
        edtKartIsim = findViewById(R.id.edtKartIsim);
        edtTarih = findViewById(R.id.edtTarih);
        edtCVV = findViewById(R.id.edtCVV);
        btnOnayla = findViewById(R.id.btnOnayla);
        tvAdres = findViewById(R.id.tvAdres);
        getHesapId();
        model = new AdresModel(this);
        krediIstek = new KrediIstek(this);
        model.kayitliAdresGetir(hesapId);

        btnOnayla.setOnClickListener(this);
        destek = new Destek();
    }

    public void getHesapId(){
        Bundle bundle = getIntent().getExtras();
        hesapId = bundle.getInt("hesapId");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AdresEkle adresEkle = new AdresEkle();
        switch (item.getItemId()){

            case R.id.adres:
                adresEkle.show(getSupportFragmentManager(),"adres ekle");
                ToastMesaj.getInstance().basariMesaj(this,"adres eklendi",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuAdresSec:
                model.hepsiniGetir(hesapId);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Map<EditText , Integer> map = new HashMap<>();
        map.put(edtKartNo,16);
        map.put(edtCVV,3);

        if(!destek.kontEditTexts(edtKartNo,edtKartIsim,edtCVV,edtTarih)){
            ToastMesaj.getInstance().basarisizMesaj(this,"Boş alan bırakmayınız", Toast.LENGTH_SHORT).show();
        }
        else if(!destek.edtKarakterSayiDogrulama(map)){
            ToastMesaj.getInstance().basarisizMesaj(this, "geçersiz karakter sayısı",Toast.LENGTH_SHORT).show();
        }
        else {
            String isim = edtKartIsim.getText().toString();
            String kartNo = edtKartNo.getText().toString();
            String tarih = edtTarih.getText().toString();
            String cvv = edtCVV.getText().toString();

            krediIstek.krediKartiSorgu(isim,kartNo,tarih,cvv,hesapId);
        }
    }

    @Override
    public void adresKabul(String adres) {
        tvAdres.setText(adres);
    }

    @Override
    public void getadres(String adres) {
        tvAdres.setText(adres);
    }

    @Override
    public void mesaj() {

    }

    @Override
    public void adresGetir(List<Adres> listAdres) {
        sec = new AdresSec(listAdres);

        sec.show(getSupportFragmentManager(),"adres sec");
    }

    @Override
    public void guncelleMesaj() {

    }

    @Override
    public void silMesaj() {

    }

    @Override
    public void kayitliAdresGetir(Hesap hesap) {
        tvAdres.setText(hesap.getAdres());

    }

    @Override
    public void basarili() {
        ToastMesaj.getInstance().basariMesaj(this,"Siparişiniz hazırlanıyor...",Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void basarisiz() {
        ToastMesaj.getInstance().basarisizMesaj(this,"Alanları doğru girdiğinizden emin olun",Toast.LENGTH_SHORT).show();
    }
}