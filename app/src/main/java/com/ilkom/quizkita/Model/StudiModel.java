package com.ilkom.quizkita.Model;

public class StudiModel {
    private String Name, Image, Kode;
    private int Total;

    public StudiModel() {

    }

    public StudiModel( String Name, String Image, String Kode, int Total ) {
        this.Name = Name;
        this.Image = Image;
        this.Kode = Kode;
        this.Total = Total;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getKode() {
        return Kode;
    }

    public void setKode(String Kode) {
        this.Kode = Kode;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

}
