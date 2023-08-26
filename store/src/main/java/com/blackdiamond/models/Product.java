package com.blackdiamond.models;

import com.blackdiamond.interfaces.IDiscount;

public abstract class Product implements IDiscount {
    protected String ID;
    protected String description;
    protected int stock;
    protected float stockPrice;
    protected float unitPrice;
    protected boolean hasStock;
    protected float discountPer;
    protected boolean isForSale = true;
    protected boolean isImported;

    public Product(String des, float unitPrice) {
        this.description = des;
        this.unitPrice = unitPrice;
    }

    public void checkID(String id) {
        if (!(id.length() == 5)) {
            throw new IllegalArgumentException("identicador de producto invalido");
        }
        if (!id.matches("^[A-Z]{2}\\d{3}")) {
            throw new IllegalArgumentException("identicador de producto invalido");
        }
    }

    public void addProduct(int quantity) {
        stock += quantity;
    }

    public void removeProduct(int quantity) {
        stock -= quantity;
        if (stock <= 0) {
            hasStock = false;
        }
    }

    public boolean validDiscount(float discount) {
        if (getDiscountPrice(discount) < unitPrice) {
            return false;
        }
        return true;

    }

    public float getDiscountPrice(float discount) {
        float finalprice = stockPrice * (discount / 100);
        finalprice = stockPrice - finalprice;
        return finalprice;
    }

    public void addTaxes() {
        if (this.getisImported()) {
            System.out.println(("impuestos"));
            float taxes = stockPrice * (1 + 10 / 100);
            float taxesPrice = stockPrice + taxes;
            this.stockPrice = taxesPrice;

        }
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %s descripcion: %s stock: %d precio de venta: %f precio de compra: %f queda stock?: %b", ID,
                description, stock, stockPrice, unitPrice, hasStock);
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

    public boolean getisImported() {
        return isImported;
    }

    public boolean getIsForsale() {
        return isForSale;
    }

    public void setIsForSale(boolean b) {
        this.isForSale = b;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }

    public void setDiscount(float discount) {
        this.discountPer = discount;
    }

    public void setIsImported(boolean isImported) {
        this.isImported = isImported;
    }

    public float getGainPer() {
        float gainPer = (stockPrice / unitPrice - 1) * 100;
        return gainPer;
    }

}
