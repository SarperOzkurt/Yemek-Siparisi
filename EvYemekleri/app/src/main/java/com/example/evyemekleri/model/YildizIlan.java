package com.example.evyemekleri.model;

public class YildizIlan {

    private int id;
    private int ilanId;
    private int hesapId;
    private float yildiz;

    public float getYildiz() {
        return yildiz;
    }

    public void setYildiz(float yildiz) {
        this.yildiz = yildiz;
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
}
