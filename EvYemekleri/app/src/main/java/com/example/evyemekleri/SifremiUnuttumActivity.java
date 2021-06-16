package com.example.evyemekleri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.evyemekleri.api.Istekler.HesapYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.Yuklemeler;
import com.example.evyemekleri.destek.Destek;
import com.example.evyemekleri.fragment.activity_bagli_frag.KodFragment;
import com.example.evyemekleri.model.Hesap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SifremiUnuttumActivity extends AppCompatActivity {
    private Button btnGonder;
    private EditText edtEmail;

    private Destek destek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifremi_unuttum);
        init();
    }

    private void init(){
        btnGonder = findViewById(R.id.btnGonderSUA);
        edtEmail = findViewById(R.id.edtEmailSUA);

        destek = new Destek();
    }

    public void OnClickSUA(View v){

        switch (v.getId()){

            case R.id.btnGonderSUA:
                btnGonderTiklama();
                break;

        }
    }
//my134205@gmail.com memo1233 email şifre
    private void btnGonderTiklama(){
        String hata = "";
        if (!destek.kontTekEditText(edtEmail)){
            hata ="Emailinizi giriniz";
        }
        else if (!destek.emailKontrol(edtEmail)){
            hata = "Geçerli email giriniz";
        }
        else {
            istek();
            return;
        }
        ToastMesaj.getInstance().basarisizMesaj(this,hata, Toast.LENGTH_SHORT).show();
    }
    private String kod = "";
    private void istek(){
        kod = destek.rastgeleKod();


        Call<Hesap> call =  HesapYonetim.getInstance().mMailGonder(edtEmail.getText().toString(),kod);
        call.enqueue(new Callback<Hesap>() {
            @Override
            public void onResponse(Call<Hesap> call, Response<Hesap> response) {
                String email = response.body().getEmail();
                if(email.equals("true")){
                    ToastMesaj.getInstance().basariMesaj(SifremiUnuttumActivity.this,"mail gönderildi",Toast.LENGTH_SHORT).show();

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flFragments,new KodFragment(kod,edtEmail.getText().toString()))
                            .commit();
                }
                else{
                    ToastMesaj.getInstance().basarisizMesaj(SifremiUnuttumActivity.this,"Mail komtrol ediniz",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Hesap> call, Throwable t) {
                Log.d("hata",t.getMessage());
            }
        });

    }
}