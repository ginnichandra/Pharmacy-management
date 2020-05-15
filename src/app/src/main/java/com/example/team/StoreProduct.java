package com.example.team;



public class StoreProduct {
    private int id,location,availablequantity,requiredquantity,price,quantity;
    private String product;

    public StoreProduct(int id,String product, int location, int availablequantity, int requiredquantity, int price,int quantity) {
        this.id = id;
        this.product = product;
        this.location = location;
        this.availablequantity = availablequantity;
        this.requiredquantity = requiredquantity;
        this.price = price;
        this.quantity = quantity;
    }

    public void changeQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public int getLocation() {
        return location;
    }

    public int getAvailablequantity() {
        return availablequantity;
    }

    public int getRequiredquantity() {
        return requiredquantity;
    }

    public String getProduct() {
        return product;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
