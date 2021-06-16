package com.example.evyemekleri.bilesen.dialog;

import android.app.ProgressDialog;
import android.content.Context;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.evyemekleri.R;

public class Yuklemeler {

    private static Yuklemeler yuklemeler = new Yuklemeler();

    public static synchronized Yuklemeler getInstance(){

        return yuklemeler;
    }


    public  ProgressDialog progressDialog(Context context,String mesaj){

        ProgressDialog dialog = new ProgressDialog(context);

        dialog.setTitle("Bekleyiniz");
        dialog.setMessage(mesaj);


        return dialog;
    }

}
