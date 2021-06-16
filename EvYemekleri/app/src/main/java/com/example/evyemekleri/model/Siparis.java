package com.example.evyemekleri.model;

public class Siparis {

    private int id;
    private int ilanId;
    private int hesapId;
    private int adresId;

    private Ilan ilann;
    private Adres adress;
    private Hesap hesap;


    public Ilan getIlann() {
        return ilann;
    }

    public void setIlann(Ilan ilann) {
        this.ilann = ilann;
    }

    public Adres getAdress() {
        return adress;
    }

    public void setAdress(Adres adress) {
        this.adress = adress;
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

    public int getIlanId() {
        return ilanId;
    }

    public void setIlanId(int ilanId) {
        this.ilanId = ilanId;
    }

    public int getHesapId() {
        return hesapId;
    }

    public void setHesapId(int hesapId) {
        this.hesapId = hesapId;
    }

    public int getAdresId() {
        return adresId;
    }

    public void setAdresId(int adresId) {
        this.adresId = adresId;
    }
}
