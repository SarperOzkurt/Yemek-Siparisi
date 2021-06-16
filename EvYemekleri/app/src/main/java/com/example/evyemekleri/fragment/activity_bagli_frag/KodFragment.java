package com.example.evyemekleri.fragment.activity_bagli_frag;

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
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.evyemekleri.R;
import com.example.evyemekleri.bilesen.ToastMesaj;
import com.example.evyemekleri.destek.Destek;
import com.example.evyemekleri.fragment.fragment_bagli_activity.YeniSifreActivity;

public class KodFragment extends Fragment implements View.OnClickListener {

    private EditText edtKod;
    private Button btnDevam;
    private Destek destek;

    private String kod,email;

    public KodFragment(String kod,String email){
        this.kod = kod;
        this.email = email;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kod,container,false);
        init(view);


        return view;
    }

    private void init(View v){
        edtKod = v.findViewById(R.id.edtKodKF);
        btnDevam = v.findViewById(R.id.btnDevamKF);

        destek = new Destek();

        Log.d("kod",kod);
        btnDevam.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String hata = "";
        if(!destek.kontTekEditText(edtKod)){
            hata = "Boş geçmeyin";
        }
        else if (!destek.objeUyusuyorMu(edtKod.getText().toString().trim(),kod)){
            hata = "Hatalı kod";
        }
        else{
            Intent intent = new Intent(getActivity(), YeniSifreActivity.class);
            intent.putExtra("email",email);
            startActivity(intent);
            return;
        }
        ToastMesaj.getInstance().basarisizMesaj(getActivity(),hata, Toast.LENGTH_SHORT).show();

    }
}
