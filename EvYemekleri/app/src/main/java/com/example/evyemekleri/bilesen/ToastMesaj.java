package com.example.evyemekleri.bilesen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evyemekleri.R;

public class ToastMesaj {

    private LinearLayout pLayout;

    private  TextView tMesaj;
    private  ImageView tResim;

    private static ToastMesaj toastMesaj = new ToastMesaj();
    public static synchronized ToastMesaj getInstance(){

        return toastMesaj;
    }


    private  Toast customToast(Context context,String mesaj,int sure){
       View view =  LayoutInflater.from(context).inflate(R.layout.toast_mesaj,parentRed(context));
       goruntuBagla(view);

       tMesaj.setText(mesaj);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM,0,40);
        toast.setDuration(sure);
        toast.setView(view);
        return toast;
    }

    public  Toast basariMesaj(Context context,String mesaj,int sure){
        Toast toast =  customToast(context,mesaj,sure);
        tResim.setImageResource(R.drawable.ic_basari);
        return toast;

    }
    private View v;
    public  Toast basarisizMesaj(Context context,String mesaj,int sure){
        Toast toast = customToast(context,mesaj,sure);
        tResim.setImageResource(R.drawable.ic_bad);
        v.setBackgroundColor(Color.RED);
        return toast;
    }


    private  void goruntuBagla(View v){
        tMesaj = v.findViewById(R.id.tMesaj);
        tResim = v.findViewById(R.id.tIcon);
        this.v = v;
    }


    private  LinearLayout parentRed(Context context){
         pLayout= ((Activity)context).findViewById(R.id.pToastMesaj);
         return pLayout;
    }


}
