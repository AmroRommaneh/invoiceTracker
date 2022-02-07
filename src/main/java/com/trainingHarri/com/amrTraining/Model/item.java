package com.trainingHarri.com.amrTraining.Model;

import javax.persistence.*;
@Entity
@Table(name = "Item")
public class item {
@Id
    @GeneratedValue
    @Column(name = "Id")
    private long itemId ;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "price")
    private double price;


//    @ManyToMany(mappedBy = "Items")
//    private List<invoice> invoices;

    public item() {
    }

    public item(String name, double quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public long getItemId() {
        return itemId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
