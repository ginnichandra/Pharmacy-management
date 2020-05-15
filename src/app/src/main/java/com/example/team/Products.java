package com.example.team;

public class Products {



    private String name,manufacturer,description,chemicalformula;
    int quantity;
    private int available,price,required,storeid;

    public Products(String name,int available,int price,String manufacturer,int required,String description,int storeid,String chemicalformula,int quantity) {
        this.name = name;
        this.available = available;
        this.price = price;
        this.manufacturer =manufacturer;
        this.required=required;
        this.description=description;
        this.storeid=storeid;
        this.chemicalformula=chemicalformula;
        this.quantity = quantity;
    }


    public void changeQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() { return name; }

    public int getAvailable() {
        return available;
    }

    public int getPrice() {
        return price;
    }

    public String getManufacturer() { return manufacturer; }

    public int getRequired() { return required; }

    public String getDescription() { return description; }

    public int getStoreid() { return storeid; }

    public String getChemicalformula() { return chemicalformula; }

}
