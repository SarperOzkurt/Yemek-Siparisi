package com.example.evyemekleri.model;

public class Yorum {

    private int id;
    private String yorumm;
    private int hesapId;
    private int yapanHesap;
    private Hesap hesap;


    public void setHesap(Hesap hesap) {
        this.hesap = hesap;
    }

    public Hesap getHesap() {
        return hesap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYorumm() {
        return yorumm;
    }

    public void setYorumm(String yorum) {
        this.yorumm = yorum;
    }

    public int getHesapId() {
        return hesapId;
    }

    public void setHesapId(int hesapId) {
        this.hesapId = hesapId;
    }

    public int getYapanHesap() {
        return yapanHesap;
    }

    public void setYapanHesapId(int yapanHesap) {
        this.yapanHesap = yapanHesap;
    }
}
