package com.example.evyemekleri.model;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Body;

public class Hesap {
    private int id;
    private String telNo;
    private  String ad;
    private String soyad;

    private String profilURL;
    private String email;
    private String kullaniciAdi;
    private String sifre;
    private boolean durum;
    private String adres;

    private Hesap hesap;

    public Hesap(int id, String telNo, String ad, String soyad, String profilURL, String email, String kullaniciAdi, String sifre) {
        this.id = id;
        this.telNo = telNo;
        this.ad = ad;
        this.soyad = soyad;
        this.profilURL = profilURL;
        this.email = email;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
    }
    public Hesap(){

    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Hesap getHesap() {
        return hesap;
    }

    public void setHesap(Hesap hesap) {
        this.hesap = hesap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getProfilURL() {
        return profilURL;
    }

    public void setProfilURL(String profilURL) {
        this.profilURL = profilURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public boolean isDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    @Override
    public String toString() {
        return "Hesap{" +
                "id=" + id +
                ", telNo='" + telNo + '\'' +
                ", ad='" + ad + '\'' +
                ", soyad='" + soyad + '\'' +
                ", profilURL='" + profilURL + '\'' +
                ", email='" + email + '\'' +
                ", kullaniciAdi='" + kullaniciAdi + '\'' +
                ", sifre='" + sifre + '\'' +
                '}';
    }
}
