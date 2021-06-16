package com.example.evyemekleri.fragment.activity_bagli_frag.anaekran;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.evyemekleri.R;
import com.example.evyemekleri.api.Istekler.IlanYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.bilesen.dialog.Yuklemeler;
import com.example.evyemekleri.destek.Destek;
import com.example.evyemekleri.model.Hesap;
import com.example.evyemekleri.model.Ilan;

import java.io.IOException;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanVerFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spnOdeme;
    private EditText edtIlanAd,edtFiyat;
    private ImageView imgResim;
    private Button btnEkle;
    private Destek destek;
    private Hesap hesap;

    public IlanVerFragment(Hesap hesap){
        this.hesap = hesap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ilanver,container,false);

        init(view);

        return view;
    }

    public void init(View v){
        spnOdeme = v.findViewById(R.id.spnOdemeTur);
        edtIlanAd = v.findViewById(R.id.edtYemekAdi);
        edtFiyat = v.findViewById(R.id.edtFiyat);
        imgResim = v.findViewById(R.id.imgYemekResim);
        btnEkle = v.findViewById(R.id.btnEkle);

        destek = new Destek();
        imgResim.setOnClickListener(this);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.odeme,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOdeme.setAdapter(arrayAdapter);

        spnOdeme.setOnItemSelectedListener(this);
        btnEkle.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgYemekResim:
                galeriEris();
                break;
            case R.id.btnEkle:
                buttonTiklama();
                break;

        }
    }

    public void buttonTiklama(){

        if(!destek.kontEditTexts(editTexts())){
            ToastMesaj.getInstance().basarisizMesaj(getActivity(),"Boş geçmeyiniz",Toast.LENGTH_SHORT).show();

        }
        else {
            istek();
        }

    }

    private String odemeTuru = "";
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        odemeTuru= parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public EditText[] editTexts(){
        EditText[] editTexts = {edtIlanAd,edtFiyat};
        return editTexts;
    }


    private static final int REQUEST_CODE = 777;
    private Bitmap bitmap;

    public void galeriEris(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CODE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(REQUEST_CODE == requestCode && resultCode==-1 && data!=null){
            Uri uri = data.getData();
            imgResim.setImageURI(uri);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private ProgressDialog dialog;

    public void istek(){
        dialog = Yuklemeler.getInstance().progressDialog(getActivity(),"Ilan Oluşturuluyor");
        dialog.show();

        String rastgeleKod = destek.rastgeleKarakter();
        Log.d("text",odemeTuru);

        final String resimYol = "./ilan/"+rastgeleKod+".jpg";
        String dbResimYol = "/ilan/"+rastgeleKod+".jpg";

        IlanYonetim.getInstance().andResimInsert(Destek.imgByteCevir(bitmap),resimYol)
                .enqueue(new Callback<Ilan>() {
                    @Override
                    public void onResponse(Call<Ilan> call, Response<Ilan> response) {
                        //Log.d("resim",response.body().getIlanURL());
                    }

                    @Override
                    public void onFailure(Call<Ilan> call, Throwable t) {
                        Log.d("hata",t.getMessage());
                    }
                });
        IlanYonetim.getInstance().dbIlanInsert(dbResimYol,edtIlanAd.getText().toString(),odemeTuru,Float.parseFloat(edtFiyat.getText().toString()),hesap.getId())
                .enqueue(new Callback<Ilan>() {
                    @Override
                    public void onResponse(Call<Ilan> call, Response<Ilan> response) {
                        Log.d("response",response.body().getId()+"");
                        if(response.isSuccessful()){
                            ToastMesaj.getInstance().basariMesaj(getActivity(),"İlan oluşturuldu",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }
                    }

                    @Override
                    public void onFailure(Call<Ilan> call, Throwable t) {
                        Log.d("hata",t.getMessage());
                    }
                });

    }

}
