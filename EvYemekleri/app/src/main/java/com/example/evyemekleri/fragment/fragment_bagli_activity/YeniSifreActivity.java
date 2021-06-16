package com.example.evyemekleri.fragment.fragment_bagli_activity;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.evyemekleri.MainActivity;
import com.example.evyemekleri.R;
import com.example.evyemekleri.api.Istekler.HesapYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.Yuklemeler;
import com.example.evyemekleri.destek.Destek;
import com.example.evyemekleri.model.Hesap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YeniSifreActivity extends AppCompatActivity {

    private EditText edtSifre,edtYeniSifre;
    private Button btnDegistir;
    private Destek destek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_sifre);

        init();
    }


    private void init(){
        edtSifre = findViewById(R.id.edtSifreYSA);
        edtYeniSifre = findViewById(R.id.edtSifreTekrarYSA);

        btnDegistir = findViewById(R.id.btnDegistirYSA);

        destek = new Destek();
    }

    private EditText[] editTexts(){
        EditText[] editTexts = {edtSifre,edtYeniSifre};
        return editTexts;
    }

    public void onClickYeniSifre(View v){
        String hata = "";
        if( !destek.kontEditTexts(editTexts() )){
            hata = "Boş geçmeyiniz";

        }
        else if (!destek.uyusuyorMu(edtSifre,edtYeniSifre)){
            hata = "Şifreler Uyuşmuyor";
        }
        else {
            istek();

            return;
        }
        ToastMesaj.getInstance().basarisizMesaj(this,hata, Toast.LENGTH_SHORT).show();
    }

    private String intentEmail(){
        Bundle bundle = getIntent().getExtras();

        return bundle.getString("email");
    }


    private ProgressDialog dialog;
    public void istek(){
        Log.d("email",intentEmail());
        dialog = Yuklemeler.getInstance().progressDialog(this,"Şifre değiştiriliyor...");
        dialog.show();

        Call<Hesap> call = HesapYonetim.getInstance().dbHesapSorgu(intentEmail(),edtSifre.getText().toString());

       call.enqueue(new Callback<Hesap>() {
           @Override
           public void onResponse(Call<Hesap> call, Response<Hesap> response) {

               if(response.body().isDurum()){
                    Intent intent = new Intent(YeniSifreActivity.this, MainActivity.class);
                    ToastMesaj.getInstance().basariMesaj(YeniSifreActivity.this,"Şifre değiştirildi",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    startActivity(intent);
               }
               else {
                   ToastMesaj.getInstance().basarisizMesaj(YeniSifreActivity.this,"Hatalı bilgi",Toast.LENGTH_SHORT).show();
                   dialog.dismiss();
               }
           }

           @Override
           public void onFailure(Call<Hesap> call, Throwable t) {
                Log.d("hesap",t.getMessage());
           }
       });
    }
}