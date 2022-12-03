package model;

import java.util.List;

public class Order  implements Comparable<Order>{

    private int id;
    private String date;
    private int id_pouz;
    private double celkova_suma;
    private String stav;

    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_pouz() {
        return id_pouz;
    }

    public void setId_pouz(int id_pouz) {
        this.id_pouz = id_pouz;
    }

    public double getCelkova_suma() {
        return celkova_suma;
    }

    public void setCelkova_suma(double celkova_suma) {
        this.celkova_suma = celkova_suma;
    }

    public String getStav() {
        return stav;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    @Override
    public int compareTo(Order o) {
        return getDate().compareTo(o.getDate());
    }
}
