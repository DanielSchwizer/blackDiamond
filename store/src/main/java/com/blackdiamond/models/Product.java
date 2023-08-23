package com.blackdiamond.models;

import com.blackdiamond.interfaces.IDiscount;
import com.blackdiamond.interfaces.ISalesMagnament;

public abstract class Product implements IDiscount {
    protected String ID;
    protected String description;
    protected int stock;
    protected double stockPrice;
    protected double unitPrice;
    protected boolean hasStock;
    protected double discountPer;
    protected boolean isForSale = true;

    public Product(String des, double unitPrice, boolean hasStock) {
        this.description = des;
        this.unitPrice = unitPrice;
        this.hasStock = hasStock;
    }

    public String getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public boolean getHasStock() {
        return hasStock;
    }

    public boolean getIsForsale() {
        return isForSale;
    }


    public void setID(String ID) {

    }

    public void setIsForSale(boolean b) {
        this.isForSale = b;
    }

    public boolean checkID(String id) {
        if (id.length() == 5) {
            return true;
        }
        System.out.println("codigo de producto invalido");
        return false;
    }

    public void addProduct(int quantity) {
        stock += quantity;
    }

    public void removeProduct(int quantity) {
        stock -= quantity;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %s descripcion: %s stock: %d precio de venta: %f precio de compra: %f ¿queda stock?: %b", ID,
                description, stock, stockPrice, unitPrice, hasStock);
    }

    public void setDiscountPer(float discount) {
        this.discountPer = discount;
    }

    public double getDiscountPer() {
        return discountPer;
    }

    public double getDiscountPrice() {
        double discount = stockPrice * (discountPer / 100);
        return stockPrice - discount;
    }
}
