package model;

public class OrderItem {


    private int id;

    private Article tovar;
    private double cena_kus;
    private double cena_spolu;
    private int poc_ks;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Article getTovar() {
        return tovar;
    }

    public void setTovar(Article tovar) {
        this.tovar = tovar;
    }

    public double getCena_kus() {
        return cena_kus;
    }

    public void setCena_kus(double cena_kus) {
        this.cena_kus = cena_kus;
    }

    public double getCena_spolu() {
        return cena_kus * poc_ks;
    }

    public int getPoc_ks() {
        return poc_ks;
    }

    public void setPoc_ks(int poc_ks) {
        this.poc_ks = poc_ks;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", tovar=" + tovar +
                ", cena_kus=" + cena_kus +
                ", cena_spolu=" + cena_spolu +
                ", poc_ks=" + poc_ks +
                '}';
    }
}
