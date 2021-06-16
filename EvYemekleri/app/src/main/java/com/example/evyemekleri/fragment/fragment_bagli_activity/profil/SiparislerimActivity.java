package com.example.evyemekleri.fragment.fragment_bagli_activity.profil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evyemekleri.R;
import com.example.evyemekleri.adapter.SiparisAdapter;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.SiparisSil;
import com.example.evyemekleri.iletisim.SiparisModel;
import com.example.evyemekleri.model.Siparis;

import java.util.List;

public class SiparislerimActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, SiparisModel.SiparisListener {
    private ListView lvSiparisler;
    private SiparisModel model;
    private int hesapId;
    private List<Siparis> listSiparis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparislerim);
        init();
    }

    private void init() {
        lvSiparisler = findViewById(R.id.lvSiparisler);
        getHesapId();
        model = new SiparisModel(this);
        model.siparisSorgu(hesapId);


        lvSiparisler.setOnItemClickListener(this);

    }
    private void getHesapId(){
        Bundle bundle = getIntent().getExtras();
        hesapId = bundle.getInt("hesapId");
    }



    @Override
    public void siparisGetir(List<Siparis> listSiparis) {
        SiparisAdapter adapter = new SiparisAdapter(listSiparis,this);
        this.listSiparis = listSiparis;
        lvSiparisler.setAdapter(adapter);

    }

    @Override
    public void sil() {
        //ToastMesaj.getInstance().basariMesaj(this,"Siparişiniz iptal edildi",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       /* SiparisSil sil = new SiparisSil(listSiparis.get(position).getId());
        sil.show(getSupportFragmentManager(),"siparis iptal");*/
    }

}