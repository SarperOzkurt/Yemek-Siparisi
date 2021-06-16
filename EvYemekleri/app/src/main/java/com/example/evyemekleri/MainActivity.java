package com.example.evyemekleri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evyemekleri.api.Istekler.HesapYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.Yuklemeler;
import com.example.evyemekleri.destek.Destek;
import com.example.evyemekleri.model.Hesap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnGiris;
    private EditText edtEmail,edtSifre;
    private Destek destek;
    private Drawable ic_email,ic_sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    private void init(){
        btnGiris = findViewById(R.id.btnGiris);
        edtEmail = findViewById(R.id.edtEmail);
        edtSifre = findViewById(R.id.edtSifre);
        ic_email = getDrawable(R.drawable.ic_email);
        ic_sifre = getDrawable(R.drawable.ic_sifre);


        destek = new Destek();


    }

    private EditText[] editTexts(){
        EditText[] editTexts = {edtEmail,edtSifre};

        return editTexts;
    }


    public void onClick(View v){
        Intent intent = null;
        switch (v.getId()){
            case R.id.btnGiris:
                if(btnGirisTiklama()){
                    //intent = new Intent(MainActivity.this, AnaEkranActivity.class);
                }
                break;

            case R.id.tvUyeOl:
                intent = new Intent(MainActivity.this,UyeEkran.class);
                break;

            case R.id.tvSifreMiUnuttum:
                intent = new Intent(MainActivity.this,SifremiUnuttumActivity.class);
                break;
        }
        if(intent!=null) startActivity(intent);

    }


    private boolean btnGirisTiklama(){
        Log.d("btnGiris","click");

        if(!destek.kontEditTexts(editTexts())){
              ToastMesaj.getInstance().basarisizMesaj(this,"Boş geçmeyiniz",Toast.LENGTH_SHORT).show();

        }
        else if(!destek.emailKontrol(edtEmail)){
            ToastMesaj.getInstance().basarisizMesaj(this,"Geçerli Email giriniz",Toast.LENGTH_SHORT).show();
        }
        else {
            istek();
            return true;
        }
        return false;
    }
    private ProgressDialog dialog;
    private void istek(){

        dialog = Yuklemeler.getInstance().progressDialog(this,"Giriş yapılıyor...");
        dialog.show();

        HesapYonetim.getInstance().dbGirisKontrol(edtEmail.getText().toString(),edtSifre.getText().toString())
                .enqueue(new Callback<Hesap>() {
                    @Override
                    public void onResponse(Call<Hesap> call, Response<Hesap> response) {
                        Log.d("hesap",response.body().getHesap()+"");
                        Hesap hesap = response.body().getHesap();
                        if(hesap!=null){
                            Intent intent = new Intent(MainActivity.this,AnaEkranActivity.class);
                            intent.putExtra("id",hesap.getId());
                            startActivity(intent);
                            dialog.dismiss();
                        }
                        else{
                            ToastMesaj.getInstance().basarisizMesaj(MainActivity.this,"Hatalı bilgi",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Hesap> call, Throwable t) {
                        Log.d("hata",t.getMessage());
                    }
                });
    }
}