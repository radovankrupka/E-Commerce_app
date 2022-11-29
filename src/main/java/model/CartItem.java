package model;

import DAO.TovarDAO;

public class CartItem {

    private int id;
    private int id_pouz;
    private int id_tovaru;
    private double cena;
    private int poc_ks;

    private Tovar tovar;

    public Tovar getTovar() {
        return tovar;
    }

    public void setTovar(Tovar tovar) {
        this.tovar = tovar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pouz() {
        return id_pouz;
    }

    public void setId_pouz(int id_pouz) {
        this.id_pouz = id_pouz;
    }

    public int getId_tovaru() {
        return id_tovaru;
    }

    public void setId_tovaru(int id_tovaru) {
        this.id_tovaru = id_tovaru;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getPoc_ks() {
        return poc_ks;
    }

    public void setPoc_ks(int poc_ks) {
        this.poc_ks = poc_ks;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", id_pouz=" + id_pouz +
                ", id_tovaru=" + id_tovaru +
                ", cena=" + cena +
                ", poc_ks=" + poc_ks +
                ", tovar=" + tovar +
                '}';
    }
}
