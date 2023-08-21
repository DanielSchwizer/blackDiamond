package com.blackdiamond.models;

import com.blackdiamond.types.CleaningType;

public class CleaningProduct extends Product {
    CleaningType tCleaningType;

    public CleaningProduct(String ID, String des, int stock, float stockPrice, float unitPrice, boolean hasStock) {
        this.ID = ID;
        this.description = des;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.unitPrice = unitPrice;
        this.hasStock = hasStock;
    }
    @Override
    public void setID(String id) {
        if (!checkID(id))
            return;
        if (id.charAt(0) != 'A') {
            System.out.println("producto mal clasificado");
            return;
        }
        if (id.charAt(1) != 'Z') {
            System.out.println("producto mal clasificado");
            return;
        }

        this.ID = id;
    }
}


