package com.example.evyemekleri.fragment.fragment_bagli_fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.evyemekleri.AnaEkranActivity;
import com.example.evyemekleri.R;
import com.example.evyemekleri.api.Istekler.HesapYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.Yuklemeler;
import com.example.evyemekleri.destek.Destek;
import com.example.evyemekleri.model.Hesap;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DuzenleFragment extends Fragment implements View.OnClickListener {
    private EditText edtEmail,edtTelNo;
    private Button btnDegistir;
    private Destek destek;

    private Hesap hesap;
    private DuzenleListener listener;
    public DuzenleFragment(Hesap hesap){
        this.hesap = hesap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_duzenle,container,false);

        init(view);
        listener = (DuzenleListener)getActivity();
        return view;
    }

    public void init(View v){
        edtEmail = v.findViewById(R.id.edtEmailFD);
        edtTelNo = v.findViewById(R.id.edtTelNoFD);

        btnDegistir = v.findViewById(R.id.btnDegistirFD);
        destek = new Destek();

        edtEmail.setText(hesap.getEmail());
        edtTelNo.setText(hesap.getTelNo());

        btnDegistir.setOnClickListener(this);

    }

    private Map<EditText,Integer> maps(){
        Map<EditText,Integer> map = new HashMap<>();

        map.put(edtTelNo,10);
        return map;
    }
    private ProgressDialog dialog;

    @Override
    public void onClick(View v) {
        dialog = Yuklemeler.getInstance().progressDialog(getActivity(),"Güncelleniyor");
        dialog.show();

        String hata = "";
        if(!destek.kontEditTexts(edtEmail,edtTelNo)){
            hata = "Boş geçmeyiniz";
        }
        else if(!destek.edtKarakterSayiDogrulama(maps())){
            hata = "10 Hane giriniz";
        }
        else if(!destek.emailKontrol(edtEmail)){
            hata = "geçerli bir email giriniz";
        }
        else {
            istek();
            dialog.dismiss();
            return ;
        }
        dialog.dismiss();
        ToastMesaj.getInstance().basarisizMesaj(getActivity(),hata, Toast.LENGTH_SHORT).show();

    }
    public void istek(){
        HesapYonetim.getInstance().dbGuncelle(edtEmail.getText().toString(),edtTelNo.getText().toString(),hesap.getId())
                .enqueue(new Callback<Hesap>() {
                    @Override
                    public void onResponse(Call<Hesap> call, Response<Hesap> response) {
                        if(response.body().getHesap()!=null){
                           // setHesap(response.body().getHesap());
                            Log.d("tag", response.body().getHesap().toString());
                            hesap = response.body().getHesap();
                            Log.d("tag2",hesap.toString());
                            listener.yeniHesap(hesap);

                            ToastMesaj.getInstance().basariMesaj(getActivity(),"Hesabınız Güncellenmiştir",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Log.d("hesap","boş");
                        }
                    }

                    @Override
                    public void onFailure(Call<Hesap> call, Throwable t) {
                        Log.d("hata",t.getMessage());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("yıkıldı", "onDestroy: ");
    }

    public interface DuzenleListener{
        void yeniHesap(Hesap hesap);
    }
}
