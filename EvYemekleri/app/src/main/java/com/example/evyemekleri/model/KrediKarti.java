package com.example.evyemekleri.model;

public class KrediKarti {

    private int id;
    private String isim;
    private String kartNo;
    private String tarih;
    private String CVV;
    private int hesapId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getKartNo() {
        return kartNo;
    }

    public void setKartNo(String kartNo) {
        this.kartNo = kartNo;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public int getHesapId() {
        return hesapId;
    }

    public void setHesapId(int hesapId) {
        this.hesapId = hesapId;
    }
}
