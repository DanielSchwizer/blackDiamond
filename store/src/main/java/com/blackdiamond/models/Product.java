package com.blackdiamond.models;

import com.blackdiamond.interfaces.IDiscount;
import com.blackdiamond.interfaces.ISalesMagnament;

public abstract class Product implements IDiscount {
    protected String ID;
    protected String description;
    protected int stock;
    protected float stockPrice;
    protected float unitPrice;
    protected boolean hasStock;
    protected float discountPer;
    protected boolean isForSale = true;

    public Product(String des, float unitPrice) {
        this.description = des;
        this.unitPrice = unitPrice;
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

    public float getStockPrice() {
        return stockPrice;
    }

    public float getUnitPrice() {
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

    public void setHasStock (boolean hasStock){
        this.hasStock = hasStock;
    }

    public void checkID(String id) {
        if (!(id.length() == 5)) {
            throw new IllegalArgumentException("identicador de producto invalido");
        }
       
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
        
    }

    public float getDiscountPer() {
        return discountPer;
    }

    public float getDiscountPrice() {
        float discount = stockPrice * (discountPer / 100);
        return stockPrice - discount;
    }

    public boolean validDiscount(float discountPer){
        if(getDiscountPrice() < getUnitPrice()){
            return false;
        }
        return true;

    }
}
