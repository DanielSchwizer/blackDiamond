package com.blackdiamond.models;

import com.blackdiamond.interfaces.IEatable;
import com.blackdiamond.interfaces.ISalesMagnament;

public class Drink extends Product implements IEatable, ISalesMagnament {
    private boolean isAlcoholic;
    private boolean isImported;
    private float alcoholicPer;
    private String expiredDate;
    private int kcal;

    public Drink(String id, float unitPrice, String des,
            boolean isAlcoholic, boolean isImported, float alcoholicPer, int kcal,
            String expiredDate) {
        super(des, unitPrice);
        setID(id);
        this.isAlcoholic = isAlcoholic;
        this.isImported = isImported;
        this.alcoholicPer = alcoholicPer;
        setKcal(kcal);
        setExpiredDate(expiredDate);
    }

    public void setStockPrice(float gainPer) {
        if (gainPer > 20) {
            throw new IllegalArgumentException("El porcentaje de ganancia para comestibles no puede superar el 20%.");
        }
        this.stockPrice = (getUnitPrice() * (1 + gainPer / 100));
        addTaxes();
    }

    public boolean checkDiscountPer(float discount) {
        if (discount > 15) {
            System.out.println("el descuento no pudo ser aplicado");
            return false;
        }
        if (!validDiscount(discount)) {
            System.out.println("el descuento no pudo ser aplicado porque el precio final es menor al precio de compra");
            return false;
        }
        return true;
    }

    public void setDiscount(float discount) {
        if (!checkDiscountPer(discount)) {
            return;
        }
        this.discountPer = discount;
        this.stockPrice = getDiscountPrice(discount);
    }

    public void addTaxes() {
        if (isImported) {
            stockPrice += stockPrice * 0.1;
            System.out.println("al producto se le aplicara un impuesto del 10% por ser importado");
            System.out.println("precio con impuestos :" + stockPrice);
        }
    }

    public void setID(String id) {
        checkID(id);
        if (!id.startsWith("AC")) {
            System.out.println("producto mal clasificado");
            throw new IllegalArgumentException("identicador de producto invalido");
        }
        this.ID = id;
    }

    public void setExpiredDate(String date) {
        this.expiredDate = date;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getKcal() {
        return kcal;
    }

    @Override
    public float getDiscount() {
        return getDiscountPrice(this.discountPer);
    }

    @Override
    public float getDiscountPercent() {
        return this.discountPer;
    }

}
