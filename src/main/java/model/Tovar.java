package model;

public class Tovar {

    private int id;
    private String nazov;
    private int ks;
    private double cena;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public int getKs() {
        return ks;
    }

    public void setKs(int ks) {
        this.ks = ks;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Tovar{" +
                "nazov='" + nazov + '\'' +
                ", ks=" + ks +
                ", cena=" + cena +
                '}';
    }
}
