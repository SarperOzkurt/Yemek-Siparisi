package com.example.evyemekleri.fragment.activity_bagli_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.evyemekleri.R;
import com.example.evyemekleri.adapter.YorumAdapter;
import com.example.evyemekleri.api.Istekler.YorumYonetim;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.model.Yorum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YorumlarActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lvYorumlar;
    private EditText edtYorum;
    private ImageView imgSend;
    private int hesapId,benimId;
    private List<Yorum> listYorum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yorumlar);
        init();
    }

    public void init(){
        lvYorumlar = findViewById(R.id.lvYorumlar);
        edtYorum = findViewById(R.id.edtYorum);
        imgSend = findViewById(R.id.imgSend);

        imgSend.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        hesapId = bundle.getInt("hesapId");
        benimId = bundle.getInt("benimId");

        istek();

    }

    private void istek(){
        YorumYonetim.getInstance().getYorum(hesapId)
                .enqueue(new Callback<List<Yorum>>() {
                    @Override
                    public void onResponse(Call<List<Yorum>> call, Response<List<Yorum>> response) {
                        if(response.isSuccessful()){
                            Log.d("s",response.isSuccessful()+"");
                            listYorum = response.body();
                            YorumAdapter adapter = new YorumAdapter(listYorum,YorumlarActivity.this);
                            lvYorumlar.setAdapter(adapter);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Yorum>> call, Throwable t) {
                        Log.e("hata",t.getMessage());
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if(edtYorum.getText().toString().trim().equals("")){
        }
        else{
            YorumYonetim.getInstance().insertYorum(edtYorum.getText().toString(),hesapId,benimId)
                    .enqueue(new Callback<Yorum>() {
                        @Override
                        public void onResponse(Call<Yorum> call, Response<Yorum> response) {
                            if(response.isSuccessful()){
                                Log.d("yorum","başarı");
                                edtYorum.setText("");
                                istek();

                            }
                        }

                        @Override
                        public void onFailure(Call<Yorum> call, Throwable t) {
                            Log.e("hata:",t.getMessage());
                        }
                    });

        }
    }
}