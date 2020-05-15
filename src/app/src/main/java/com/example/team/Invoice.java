package com.example.team;


import java.util.ArrayList;

public class Invoice {
    private int totalprice;
    private ArrayList<Sales> salesList;

    public Invoice(int totalprice, ArrayList<Sales> saleslist) {
        this.totalprice = totalprice;
        this.salesList = saleslist;
    }
}
