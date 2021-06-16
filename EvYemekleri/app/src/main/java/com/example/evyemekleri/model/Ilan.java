package com.example.evyemekleri.model;

import java.util.List;

public class Ilan {

    private int id;
    private String ilanURL;
    private String ilanAd;
    private String odemeTuru;
    private float yildiz;
    private int hesapId;
    private float fiyat;
    private int res;
    private int degerlendirme;

    private List<Ilan> ilan;

    private Hesap hesap;
    private Ilan ilann;

    private Favori fav;

    private boolean favori;

    public void setDegerlendirme(int degerlendirme) {
        this.degerlendirme = degerlendirme;
    }

    public int getDegerlendirme() {
        return degerlendirme;
    }

    public Favori getFav() {
        return fav;
    }

    public void setFav(Favori fav) {
        this.fav = fav;
    }

    public Ilan getIlann() {
        return ilann;
    }

    public void setIlann(Ilan ilann) {
        this.ilann = ilann;
    }

    public Hesap getHesap() {
        return hesap;
    }

    public void setHesap(Hesap hesap) {
        this.hesap = hesap;
    }

    public int getRes(){
        return res;
    }
    public void setRes(int res){
        this.res = res;
    }
    public List<Ilan> getIlan() {
        return ilan;
    }

    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    public float getFiyat() {
        return fiyat;
    }

    public void setFiyat(float fiyat) {
        this.fiyat = fiyat;
    }

    public void setIlan(List<Ilan> ilan) {
        this.ilan = ilan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIlanURL() {
        return ilanURL;
    }

    public void setIlanURL(String ilanURL) {
        this.ilanURL = ilanURL;
    }

    public String getIlanAd() {
        return ilanAd;
    }

    public void setIlanAd(String ilanAd) {
        this.ilanAd = ilanAd;
    }

    public String getOdemeTuru() {
        return odemeTuru;
    }

    public void setOdemeTuru(String odemeTuru) {
        this.odemeTuru = odemeTuru;
    }

    public float getYildiz() {
        return yildiz;
    }

    public void setYildiz(float yildiz) {
        this.yildiz = yildiz;
    }

    public int getHesapId() {
        return hesapId;
    }

    public void setHesapId(int hesapId) {
        this.hesapId = hesapId;
    }

    @Override
    public String toString() {
        return "Ilan{" +
                "id=" + id +
                ", ilanURL='" + ilanURL + '\'' +
                ", ilanAd='" + ilanAd + '\'' +
                ", odemeTuru='" + odemeTuru + '\'' +
                ", yildiz=" + yildiz +
                ", hesapId=" + hesapId +
                ", fiyat=" + fiyat +
                '}';
    }
}
