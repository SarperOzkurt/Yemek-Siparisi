package com.example.evyemekleri.api.Istekler;

import com.example.evyemekleri.api.TemelYonetim;
import com.example.evyemekleri.model.Hesap;

import java.util.List;

import retrofit2.Call;

public class HesapYonetim extends TemelYonetim {

    private static HesapYonetim yonetim = new HesapYonetim();

    public static synchronized HesapYonetim getInstance(){

        return yonetim;
    }

    public Call<Hesap> dbHesapInsert(String telNo,String ad,String soyad,String profilURL,String email,String kullaniciAdi,String sifre){
        Call<Hesap> call = getTemelCagri().dbHesapInsert(telNo, ad, soyad, profilURL, email, kullaniciAdi, sifre);

        return call;
    }
    public Call<Hesap> dbResimYukle(String resimByte,String resimYol){
        Call<Hesap> call = getTemelCagri().dbResimYukle(resimByte,resimYol);
        return call;
    }
    public Call<Hesap> mMailGonder(String email,String kod){
        Call<Hesap> call = getTemelCagri().mMailGonder(email, kod);
        return call;
    }
    public Call<Hesap> dbHesapSorgu(String email,String sifre){

        Call<Hesap> call = getTemelCagri().dbHesapSorgu(email,sifre);
        return call;
    }
    public Call<Hesap> dbGirisKontrol(String email,String sifre){
        Call<Hesap> call = getTemelCagri().dbGirisKontrol(email,sifre);
        return call;
    }
    public Call<Hesap> dbAnaEkran(int id){
        Call<Hesap> call = getTemelCagri().dbAnaEkran(id);
        return call;
    }

    public Call<Hesap> dbGuncelle(String email,String telNo,int id){
        Call<Hesap> call = getTemelCagri().dbGuncelle(email, telNo,id);
        return call;
    }
    public Call<Hesap> hesapSorgu(int id){
        Call<Hesap> call = getTemelCagri().hesapSorgu(id);
        return call;
    }

    public Call<List<Hesap>> hesapHepsiGetir(String kullaniciAdi){
        Call<List<Hesap>> call = getTemelCagri().hesapHepsiGetir(kullaniciAdi);
        return call;
    }

}
