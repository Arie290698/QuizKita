package com.ilkom.quizkita.Model;

public class RankModel {
    private String Nama;
    private String Nilai;

    public RankModel() {

    }

    public RankModel( String Nama, String Nilai ) {
        this.Nama = Nama;
        this.Nilai = Nilai;
    }

    public String getNama() {
        return Nama;
    }

    public String getNilai() {
        return Nilai;
    }

    public void setNilai(String Nilai) {
        this.Nilai = Nilai;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }
}
