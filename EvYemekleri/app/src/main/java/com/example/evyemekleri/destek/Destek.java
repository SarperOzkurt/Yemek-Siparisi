package com.example.evyemekleri.destek;

import android.animation.BidirectionalTypeConverter;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Base64;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Destek {
    private static InputStream is = null;

    public static Drawable loadImageFromWeb(String url){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            is =(InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is,url);
            is.close();
            return d;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static String imgByteCevir(Bitmap bitmap){

        if(bitmap!=null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG,80,baos);
            byte[] byt = baos.toByteArray();
            return Base64.encodeToString(byt,Base64.DEFAULT);
        }
        return "";
    }

    private int sayac;

    public boolean kontEditTexts(EditText...editTexts){
        sayac =0;

        for(int i =0;i<editTexts.length;i++){

            if(editTexts[i].getText().toString().equals("")){
                editTexts[i].setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                sayac++;
            }
            else{
                editTexts[i].setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
            }

        }
        if(sayac==0){

            return true;
        }
        else{
            return false;
        }

    }
    public boolean kontTekEditText(EditText editText){
        if(editText.getText().toString().trim().equals("")){

            editText.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            return false;
        }
        else {
            editText.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
            return true;
        }


    }

    public boolean edtKarakterSayiDogrulama(Map<EditText,Integer> map){
        Set<Map.Entry<EditText,Integer>> entrySet = map.entrySet();
        int sayac = 0;
        for (Map.Entry<EditText,Integer> entry : entrySet) {

            int boyut = entry.getKey().getText().toString().length();

            if(boyut!=entry.getValue()){
                entry.getKey().setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                sayac++;
            }
            else{
                entry.getKey().setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
            }

        }
        if(sayac==0){
            return true;
        }
        else{
            return false;
        }
    }

    private static String[] mails = {"gmail.com","hotmail.com","outlook.com"};

    public boolean emailKontrol(EditText edt_email) {
        String email = edt_email.getText().toString();

        String arr[] = email.split("@");
        if(arr.length==2) {

            for(int i =0;i<mails.length;i++) {
                if(mails[i].equals(arr[1])) {
                    edt_email.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                    return true;
                }
            }
        }

        edt_email.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        return false;
    }

   /* public boolean iconluEditTextKontrol(Map<EditText, Drawable> edtIcon){
        Set<Map.Entry<EditText,Drawable>> entrySet = edtIcon.entrySet();
        int sayac = 0;

        for (Map.Entry<EditText,Drawable> entry:entrySet) {
            if(entry.getKey().getText().toString().trim().equals("")){
                entry.getKey().setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                entry.getValue().setColorFilter(Color.RED,null);
                sayac++;
            }
            else {
                entry.getKey().setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                entry.getValue().setTint(Color.CYAN);
            }

        }

        if(sayac==0){
            return true;
        }
        else{
            return false;
        }
    }*/

    public boolean uyusuyorMu(EditText edt1,EditText edt2){
        String str1 = edt1.getText().toString();
        String str2 = edt2.getText().toString();

        if(!str1.equals(str2)){
            edt1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            edt2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            return false;
        }

        else{
            edt1.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
            edt2.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
            return true;
        }

    }

    public String rastgeleKarakter(){
        Random random = new Random();

        String karakter[] = {"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","A","B","C","D","E","F","G"};
        String rastKarakter = "";
        for(int i =0;i<20;i++){
            int a = random.nextInt(20);
            rastKarakter+=karakter[a];
        }

        return rastKarakter;
    }
   public String rastgeleKod(){
       Random random = new Random();
       String karakter[] = {"1","2","3","4","5","6","7","8","9"};
       String rastKarakter = "";
       for(int i =0;i<4;i++){
           int a = random.nextInt(8);
           rastKarakter+=karakter[a];
       }

        return rastKarakter;
   }

    public boolean objeUyusuyorMu(Object o1 ,Object o2){

        if(o1.equals(o2)) return true;
        else return false;

    }
}
