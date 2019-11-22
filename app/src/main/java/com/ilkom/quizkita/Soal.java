package com.ilkom.quizkita;

public class Soal {

    public String pertanyaan[] = {
            "Siapa nama presiden Indonesia yang pertama ?",
            "Ideologi dasar bagi negara Indonesia adalah ...",
            "Bhinneka Tunggal Ika mempunyai arti ...",
            "Ibukota negara Indonesia saat ini adalah ...",
            "Siapa yang menjajah Indonesia selama 350 tahun ?",
            "Saat masa penjajahan, senjata yang biasa digunakan oleh pahlawan Indonesia adalah ...",
            "Monumen untuk mengenang perlawanan dan perjuanagan rakyat Indonesia untuk merebut kemerdekaan dari pemerintahan kolonial Hindia Belanda adalah ...",
            "Teks yang dibacakan Ir. Soekarno yang menyatakan Indonesia merdeka dalah teks ...",
            "Pulau terbesar di Indonesia adalah ...",
    };

    private String jawaban[][] = {
            {"Ir. Soekarno","Joko Widodo","Susilo Bambang Yudhoyono","Jokowi"},
            {"UUD 1945","Pancasila","Bhinneka Tunggal Ika","Garuda"},
            {"Berbeda-beda tetapi tetap satu","Bersama selamanya","Bersatu teguh bercerai runtuh","Satu untuk semua"},
            {"Semarang","Surabaya","Jakarta","Kalimantan"},
            {"Jepang","Belanda","Malaysia","Pinoy"},
            {"Bambu runcing","Ketapel","Shotgun","Sniper"},
            {"Tugu Muda","Patung Pancoran","Monas","Pizza"},
            {"Proklamasi","Pancasila","Pembukaan UUD 1945","Gladiator"},
            {"Jawa","Sumatera","Kalimantan","Papua"},
    };

    private String jawabanBenar[] = {
            "Ir. Soekarno",
            "Pancasila",
            "Berbeda-beda tetapi tetap satu",
            "Jakarta",
            "Belanda",
            "Bambu runcing",
            "Monas",
            "Proklamasi",
            "Kalimantan",
    };

    public String getPertanyaan(int x){
        String soal = pertanyaan[x];
        return soal;
    }
    public String getA(int x){
        String A = jawaban[x][0];
        return A;
    }
    public String getB(int x){
        String B = jawaban[x][1];
        return B;
    }
    public String getC(int x){
        String C = jawaban[x][2];
        return C;
    }
    public String getD(int x){
        String D = jawaban[x][3];
        return D;
    }

    public String getJawabanBenar(int x){
        String jawaban = jawabanBenar[x];
        return jawaban;
    }
}