package com.blackdiamond.models;

import com.blackdiamond.interfaces.IDiscount;

/**
 * Clase abstracta que representa un producto genérico.
 */
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

    /**
     * Constructor de la clase Product.
     *
     * @param des       Descripción del producto.
     * @param unitPrice Precio unitario del producto.
     */

    public Product(String des, float unitPrice) {
        this.description = des;
        this.unitPrice = unitPrice;
    }

    /**
     * Verifica la validez del identificador del producto.
     *
     * @param id Identificador a verificar.
     * @throws IllegalArgumentException Si el identificador no es válido.
     */
    public void checkID(String id) {
        if (!(id.length() == 5)) {
            throw new IllegalArgumentException("identicador de producto invalido");
        }
        if (!id.matches("^[A-Z]{2}\\d{3}")) {
            throw new IllegalArgumentException("identicador de producto invalido");
        }
    }

    /**
     * Agrega una cantidad de productos al stock.
     *
     * @param quantity Cantidad a agregar.
     */
    public void addProduct(int quantity) {
        stock += quantity;
    }

    /**
     * Remueve una cantidad de productos del stock.
     *
     * @param quantity Cantidad a remover.
     */
    public void removeProduct(int quantity) {
        stock -= quantity;
        if (stock <= 0) {
            hasStock = false;
        }
    }

    /**
     * Verifica si un descuento es válido para el producto.
     *
     * @param discount Descuento a verificar.
     * @return true si el descuento es válido, false si no lo es.
     */
    public boolean validDiscount(float discount) {
        if (getDiscountPrice(discount) < unitPrice) {
            return false;
        }
        return true;

    }

    /**
     * Calcula el precio con descuento.
     *
     * @param discount Porcentaje de descuento.
     * @return Precio con descuento.
     */

    public float getDiscountPrice(float discount) {
        float finalprice = stockPrice * (discount / 100);
        finalprice = stockPrice - finalprice;
        return finalprice;
    }
    
    /**
     * Aplica impuestos si el producto es importado.
     */
    public void addTaxes() {
        if (this.getisImported()) {
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

    /**
     * 
     * getters y setters
     */

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
