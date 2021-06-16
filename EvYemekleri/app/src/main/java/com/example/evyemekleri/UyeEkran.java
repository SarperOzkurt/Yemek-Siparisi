package com.example.evyemekleri;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.evyemekleri.api.Istekler.HesapYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.Yuklemeler;
import com.example.evyemekleri.destek.Destek;
import com.example.evyemekleri.model.Hesap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UyeEkran extends AppCompatActivity {

    private EditText edtTelNo,edtAd,edtSoyad;
    private EditText edtEmail,edtKullaniciAdi,edtSifre,edtSifreTekrar;
    private Destek destek;
    private Button btnUyeOl;
    private ImageView imgProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_ekran);

        init();
    }

    private void init(){
        edtTelNo = findViewById(R.id.edtUETelNo);
        edtAd = findViewById(R.id.edtUEAd);
        edtSoyad = findViewById(R.id.edtUESoyad);

        edtEmail = findViewById(R.id.edtUEEmail);
        edtKullaniciAdi = findViewById(R.id.edtUEKullaniciAdi);
        edtSifre = findViewById(R.id.edtUESifre);
        edtSifreTekrar = findViewById(R.id.edtUESifreTekrar);

        imgProfil = findViewById(R.id.imgProifl);

        btnUyeOl = findViewById(R.id.btnUyeOl);
        destek = new Destek();
    }



    private EditText[] editTexts(){
        EditText[] editTexts = {edtTelNo, edtAd,edtSoyad,edtEmail,edtKullaniciAdi,edtSifre,edtSifreTekrar};
        return editTexts;
    }
    private Map<EditText,Integer> maps(){
        Map<EditText,Integer> hashMap = new HashMap<>();

        hashMap.put(edtTelNo,10);
        return hashMap;
    }


    public void onClickUyeEkran(View v){
        Intent intent = null;
        switch (v.getId()){

            case R.id.btnUyeOl:
                if(btnUyeOlTiklama()){
                    istek();
                }
                break;
            case R.id.imgProifl:
                resimSec(intent);

                break;
        }
        if(intent!=null) startActivity(intent);

    }



    private Bitmap bitmap;

    private boolean btnUyeOlTiklama(){
        String hata = "";
        if(!destek.kontEditTexts(editTexts())){
            hata = "Boş bırakmayınız";
        }
        else if(!destek.emailKontrol(edtEmail)){
            hata = "Email Kontrol";
        }
        else if(!destek.uyusuyorMu(edtSifre,edtSifreTekrar)){
            hata = "Şifreler uyuşmuyor";
        }
        else if (!destek.edtKarakterSayiDogrulama(maps())){
            hata = "Geçerli telefon no giriniz";
        }
        else{
            return true;
        }

        ToastMesaj.getInstance().basarisizMesaj(this,hata,Toast.LENGTH_SHORT).show();
        return false;
    }

    private static final int REQUEST_CODE = 777;

    private void resimSec(Intent intent){
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imgProfil.setImageURI(uri);
                Log.d("byte",Destek.imgByteCevir(bitmap));

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    private void istek(){

        String rastgeleIsim = destek.rastgeleKarakter();
        String profilYol =  "./profils/"+rastgeleIsim+".jpg";
        String dbProfilYol = "/profils/"+rastgeleIsim+".jpg";

        final ProgressDialog dialog = Yuklemeler.getInstance().progressDialog(this,"Hesabınız Oluşturuluyor...");
        dialog.show();

        if(bitmap!=null){
            Call<Hesap> call =  HesapYonetim.getInstance().dbResimYukle(Destek.imgByteCevir(bitmap),profilYol);
            call.enqueue(new Callback<Hesap>() {
                @Override
                public void onResponse(Call<Hesap> call, Response<Hesap> response) {
                    Log.d("call",response.body().getProfilURL());
                    if(response.isSuccessful()){
                    }
                }

                @Override
                public void onFailure(Call<Hesap> call, Throwable t) {
                    Log.d("hata",t.getMessage());
                }
            });
        }
        else{
            Log.d("foto","girilmedi");
        }


      Call<Hesap> call2 = HesapYonetim.getInstance().dbHesapInsert(edtTelNo.getText().toString(),edtAd.getText().toString(),edtSoyad.getText().toString(),dbProfilYol,edtEmail.getText().toString(),edtKullaniciAdi.getText().toString(),edtSifre.getText().toString());
      call2.enqueue(new Callback<Hesap>() {
          @Override
          public void onResponse(Call<Hesap> call, Response<Hesap> response) {
              if(response.isSuccessful()){
                  if(response.body().getId()!=0){
                      Intent intent = new Intent(UyeEkran.this,MainActivity.class);
                      ToastMesaj.getInstance().basariMesaj(UyeEkran.this,"Hesap olusturuldu",Toast.LENGTH_SHORT).show();
                      dialog.dismiss();
                      startActivity(intent);
                  }
              }
              else{
                  ToastMesaj.getInstance().basarisizMesaj(UyeEkran.this,"Bilgilerinizi kontrol ediniz",Toast.LENGTH_SHORT).show();
              }

          }
          @Override
          public void onFailure(Call<Hesap> call, Throwable t) {
                Log.d("hata",t.getMessage());
          }
      });


    }

}