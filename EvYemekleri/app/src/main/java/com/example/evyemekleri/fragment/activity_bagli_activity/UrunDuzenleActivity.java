package com.example.evyemekleri.fragment.activity_bagli_activity;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.evyemekleri.R;
import com.example.evyemekleri.api.Istekler.IlanYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.Yuklemeler;
import com.example.evyemekleri.model.Ilan;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrunDuzenleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private int ilanId;
    private ImageView imgYemekResim;
    private EditText edtIlanAd,edtFiyat;
    private Spinner spnOdemeTur;
    private Button btnGuncelle,btnSil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_duzenle);
        init();
    }

    public void init(){
        edtIlanAd = findViewById(R.id.edtIlanAdUD);
        imgYemekResim = findViewById(R.id.imgYemekResimUD);
        edtFiyat = findViewById(R.id.edtIlanFiyat);
        spnOdemeTur = findViewById(R.id.spnOdemeTurUD);
        btnGuncelle = findViewById(R.id.btnGuncelle);
        btnSil = findViewById(R.id.btnSil);

        spnOdemeTur.setOnItemSelectedListener(this);
        btnSil.setOnClickListener(this);
        btnGuncelle.setOnClickListener(this);

        getId();
        Log.d("ilanId",ilanId+"");
        istek();
    }

    private void getId(){
        Bundle bundle = getIntent().getExtras();
        ilanId = bundle.getInt("ilanId");
    }

    private void istek(){
        IlanYonetim.getInstance().dbIlanId(ilanId)
                .enqueue(new Callback<Ilan>() {
                    @Override
                    public void onResponse(Call<Ilan> call, Response<Ilan> response) {
                        Ilan ilan = response.body();
                        Log.d("ilan",response.body().toString());
                        veriBagla(ilan);
                    }

                    @Override
                    public void onFailure(Call<Ilan> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });

    }
    private void veriBagla(Ilan ilan){
        Picasso.get().load("http://192.168.1.35:8080"+ilan.getIlanURL()).into(imgYemekResim);
        edtIlanAd.setText(ilan.getIlanAd());
        edtFiyat.setText(ilan.getFiyat()+"");

        String arr[] = getResources().getStringArray(R.array.odeme);
        Vector<String> vector = new Vector<>();

        for(int i =0;i<arr.length;i++){
            vector.add(arr[i]);
        }

        for (int i =0;i<vector.size();i++){
            if(vector.get(i).contains(ilan.getOdemeTuru())){
               Log.d("index",i+"");
//               vector.set(0,ilan.getOdemeTuru());
               vector.remove(i);
               vector.add(0,ilan.getOdemeTuru());
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,vector);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOdemeTur.setAdapter(arrayAdapter);

    }
    private String odeme;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        odeme =  (String)parent.getItemAtPosition(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private ProgressDialog dialog;

    private void istekGuncelle(){
        IlanYonetim.getInstance().dbIlanGuncelle(ilanId,edtIlanAd.getText().toString(),odeme,Float.parseFloat(edtFiyat.getText().toString()))
                .enqueue(new Callback<Ilan>() {
                    @Override
                    public void onResponse(Call<Ilan> call, Response<Ilan> response) {
                        if(response.body().getRes()==1){
                            ToastMesaj.getInstance().basariMesaj(UrunDuzenleActivity.this,"Güncellendi",Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onFailure(Call<Ilan> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });

    }
    private void istekSil(){
        IlanYonetim.getInstance().dbIlanSil(ilanId)
                .enqueue(new Callback<Ilan>() {
                    @Override
                    public void onResponse(Call<Ilan> call, Response<Ilan> response) {
                        Log.d("basari",response.body().getRes()+"");
                        if(response.body().getRes()==1){
                            onBackPressed();
                            ToastMesaj.getInstance().basariMesaj(UrunDuzenleActivity.this, "İlan Silindi",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Ilan> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });

    }

    @Override
    public void onClick(View v) {
        dialog = Yuklemeler.getInstance().progressDialog(this,"Güncelleniyor...");
        dialog.show();
        switch (v.getId()){
            case R.id.btnSil:
                sil();
                break;
            case R.id.btnGuncelle:
                istekGuncelle();
                break;
        }
        dialog.dismiss();
    }
    public void sil(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("İlan sil")
                .setMessage("Silmek istediğine emin misin ?")
                .setPositiveButton("Sil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        istekSil();

                    }
                })
                .setNegativeButton("vazgeç", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

}