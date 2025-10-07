package com.example.myapplication;

public class Produkt {
    private String nazwaProduktu;
    private double cena;
    private boolean czySkreslone;

    public Produkt(String nazwaProduktu, double cena) {
        this.nazwaProduktu = nazwaProduktu;
        this.cena = cena;
        czySkreslone = false;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "nazwaProduktu='" + nazwaProduktu + '\'' +
                ", cena=" + cena +
                '}';
    }

    public boolean isCzySkreslone() {
        return czySkreslone;
    }

    public void setCzySkreslone(boolean czySkreslone) {
        this.czySkreslone = czySkreslone;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
