package com.example.evyemekleri.fragment.fragment_bagli_activity.profil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evyemekleri.R;
import com.example.evyemekleri.adapter.AdresAdapter;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.AdresEkle;
import com.example.evyemekleri.bilesen.dialog.AdresSil;
import com.example.evyemekleri.iletisim.AdresModel;
import com.example.evyemekleri.model.Adres;
import com.example.evyemekleri.model.Hesap;

import java.util.ArrayList;
import java.util.List;

public class AdreslerimActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdresEkle.AdresEkleListener,
        AdresModel.AdresModelListener, View.OnClickListener, AdapterView.OnItemLongClickListener, AdresSil.AdresSilListener {
    private ListView lvAdreslerim;
    private TextView tvSeciliAdres;
    private Button btnAdresKaydet;
    private List<Adres> listAdres;
    private AdresModel model;
    private int hesapId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adreslerim);
        init();
    }

    public void init(){
        lvAdreslerim = findViewById(R.id.lvAdresler);
        tvSeciliAdres = findViewById(R.id.tvSeciliAdres);
        btnAdresKaydet = findViewById(R.id.btnAdresKaydet);

        getHesapId();
        model = new AdresModel(this);
        lvAdreslerim.setOnItemClickListener(this);
        lvAdreslerim.setLongClickable(true);
        lvAdreslerim.setOnItemLongClickListener(this);
        btnAdresKaydet.setOnClickListener(this);

        model.hepsiniGetir(hesapId);
    }
    public void getHesapId(){
        Bundle bundle = getIntent().getExtras();
        hesapId = bundle.getInt("hesapId");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        tvSeciliAdres.setText(listAdres.get(position).getAdres());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AdresEkle ekle = new AdresEkle();

        switch (item.getItemId()){
            case R.id.menuAdresEkle:
                ekle.show(getSupportFragmentManager(),"adres ekle");
                break;

        }

        return true;
    }

    @Override
    public void adresKabul(String adres) {
        //adreslerime insert edicek
        model.insert(adres,hesapId);
        model.hepsiniGetir(hesapId);
    }
    @Override
    public void mesaj() {
        ToastMesaj.getInstance().basariMesaj(this,"Adres Eklendi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void adresGetir(List<Adres> listAdres) {
        AdresAdapter adapter = new AdresAdapter(listAdres,this);
        this.listAdres = listAdres;
        lvAdreslerim.setAdapter(adapter);

    }

    @Override
    public void guncelleMesaj() {
        ToastMesaj.getInstance().basariMesaj(this,"Kayıt Edildi",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void silMesaj() {
        ToastMesaj.getInstance().basariMesaj(this,"Silindi",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void kayitliAdresGetir(Hesap hesap) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_adres,menu);

        return true;
    }

    @Override
    public void onClick(View v) {
        model.adresGuncelle(hesapId,tvSeciliAdres.getText().toString());
    }
    private int adresId ;
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("uzun basıldı",listAdres.get(position).getId()+"");
        adresId = listAdres.get(position).getId();

        AdresSil sil = new AdresSil();

        sil.show(getSupportFragmentManager(),"adres sil");

        return true;
    }

    @Override
    public void adresSil() {
        model.AdresSil(adresId);
        model.hepsiniGetir(hesapId);
    }
}