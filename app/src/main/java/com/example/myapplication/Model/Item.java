package com.example.myapplication.Model;

public class Item {
    private String ID;
    private String name;
    private int quantity;
    private String description;

    public Item(String ID, String name, int quantity, String description) {
        this.ID = ID;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
