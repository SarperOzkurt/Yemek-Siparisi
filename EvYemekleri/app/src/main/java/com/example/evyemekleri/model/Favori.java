package com.example.evyemekleri.model;

public class Favori {


    private int id;
    private int ilanId;
    private int hesapId;

    public int getHesapId() {
        return hesapId;
    }

    public void setHesapId(int hesapId) {
        this.hesapId = hesapId;
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

    @Override
    public String toString() {
        return "Favori{" +
                "id=" + id +
                ", ilanId=" + ilanId +
                ", hesapId=" + hesapId +
                '}';
    }
}
