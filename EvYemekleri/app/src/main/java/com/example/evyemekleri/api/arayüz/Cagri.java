package com.example.evyemekleri.api.arayüz;

import androidx.annotation.DimenRes;

import com.example.evyemekleri.model.Adres;
import com.example.evyemekleri.model.Favori;
import com.example.evyemekleri.model.Hesap;
import com.example.evyemekleri.model.Ilan;
import com.example.evyemekleri.model.KrediKarti;
import com.example.evyemekleri.model.Siparis;
import com.example.evyemekleri.model.YildizIlan;
import com.example.evyemekleri.model.Yorum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Cagri {

    @FormUrlEncoded
    @POST("hesapp")
    Call<List<Hesap>> hesapHepsiGetir(@Field("kullaniciAdi") String kullaniciAdi);

    @FormUrlEncoded
    @POST("hesap")
    Call<Hesap> dbHesapInsert(
            @Field("telNo") String telNo,
            @Field("ad") String ad,
            @Field("soyad") String soyad,
            @Field("profilURL") String profilURL,
            @Field("email") String email,
            @Field("kullaniciAdi") String kullaniciAdi,
            @Field("sifre") String sifre
    );

    @FormUrlEncoded
    @POST("hesapResim")
    Call<Hesap> dbResimYukle(@Field("resimByte") String resimByte,@Field("resimYol")String resimYol);


    @FormUrlEncoded
    @POST("mail")
    Call<Hesap> mMailGonder(@Field("email") String email,@Field("kod") String kod);

    @FormUrlEncoded
    @POST("sorgu")
    Call<Hesap> dbHesapSorgu(@Field("email")String email,@Field("sifre")String sifre);

    @FormUrlEncoded
    @POST("giris")
    Call<Hesap> dbGirisKontrol(@Field("email")String email,@Field("sifre")String sifre);

    @FormUrlEncoded
    @POST("anaekran")
    Call<Hesap> dbAnaEkran(@Field("id")int id);

    @FormUrlEncoded
    @POST("guncelle")
    Call<Hesap> dbGuncelle(@Field("email") String email,@Field("telNo")String telNo,@Field("id")int id);

    @GET("hesap/{id}")
    Call<Hesap> hesapSorgu(@Path("id")int id);

    //ILANLAR

    @FormUrlEncoded
    @POST("ilanResim")
    Call<Ilan> andResimInsert(@Field("resimByte")String resimByte,@Field("resimYol")String resimYol);

    @FormUrlEncoded
    @POST("ilanEkle")
    Call<Ilan> dbIlanInsert(@Field("ilanURL")String ilanURL,@Field("ilanAd")String ilanAd,@Field("odemeTuru")String odemeTuru,@Field("fiyat")float fiyat,@Field("hesapId")int id);

    @GET("ilanSorgu")
    Call<List<Ilan>> dbIlanSorgu();

    @GET("ilanSorgu/{id}")
    Call<List<Ilan>> dbIlanHesapId(@Path("id") int id);

    @GET("ilanQuery/{id}")
    Call<Ilan> dbIlanId(@Path("id") int id);

    @DELETE("ilanSil/{id}")
    Call<Ilan> dbIlanSil(@Path("id")int id);

    @FormUrlEncoded
    @PUT("ilanGuncelle/{id}")
    Call<Ilan> dbIlanGuncelle(@Path("id")int id,@Field("ilanAd")String ilanAd,@Field("odemeTuru")String odemeTuru,@Field("fiyat")float fiyat);

    @GET("ilanDetay/{id}")
    Call<Ilan> dbIlanDetay(@Path("id")int id);


    //YildizliIlan
    @FormUrlEncoded
    @POST("yildizInsert")
    Call<YildizIlan> dbYildizInsert(@Field("ilanId")int ilanId,@Field("hesapId")int hesapId,@Field("yildiz")float yildiz);
    @FormUrlEncoded
    @POST("yildizSorgu")
    Call<YildizIlan> dbYildizSorgu(@Field("ilanId")int ilanId,@Field("hesapId")int hesapId);

    //Favori
    @FormUrlEncoded
    @POST("favoriInsert")
    Call<Favori> dbFavoriInsert (@Field("ilanId")int ilanId,@Field("hesapId")int hesapId);

    @DELETE("favoriSil/{ilanId}")
    Call<Favori> dbFavoriSil (@Path("ilanId") int ilanId);

    //favori sorgudaki ilanId ve hesapId kontrol edilecek
    @GET("favoriSorgu")
    Call<List<Favori>> getFavori();

    @GET("favoriSorgu/{hesapId}")
    Call<List<Ilan>> getFavoriHesapId(@Path("hesapId")int hesapId);
    //yorum
    @FormUrlEncoded
    @POST("yorum")
    Call<Yorum> insertYorum(@Field("yorum")String yorum,@Field("hesapId") int hesapId,@Field("yapanHesap")int yapanHesap);

    @GET("yorum/{hesapId}")
    Call<List<Yorum>> getYorum(@Path("hesapId")int hesapId);
    @GET("tekYorum/{id}")
    Call<Yorum> getTekYorum(@Path("id")int id);
    //adres

    @FormUrlEncoded
    @POST("adres")
    Call<Adres> insertAdres(@Field("adres")String adres,@Field("hesapId")int hesapId);

    @GET("adres/{hesapId}")
    Call<List<Adres>> adresGetir(@Path("hesapId")int hesapId);

    @FormUrlEncoded
    @PUT("adres/{hesapId}")
    Call<Adres> adresGuncelle(@Path("hesapId")int hesapId,@Field("adres") String adres);

    @DELETE("adres/{id}")
    Call<Adres> adresSil(@Path("id")int id);

    @GET("kayitliAdres/{id}")
    Call<Hesap> kayitliAdresGetir(@Path("id")int id);

    //kredi karti
    @FormUrlEncoded
    @POST("kredi")
    Call<KrediKarti> krediKartiSorgula(@Field("isim")String isim
            ,@Field("kartNo")String kartNo
            ,@Field("tarih")String tarih
            ,@Field("CVV")String CVV
            ,@Field("hesapId")int hesapId);
    // siparis
    @FormUrlEncoded
    @POST("siparis")
    Call<Siparis> siparisInsert (@Field("ilanId")int ilanId,@Field("hesapId")int hesapId,@Field("adresId")int adresId);

    @GET("siparis/{hesapId}")
    Call<List<Siparis>> siparisSorgu(@Path("hesapId")int hesapId);

    @DELETE("siparis/{id}")
    Call<Siparis> siparisSil(@Path("id")int id);


}
