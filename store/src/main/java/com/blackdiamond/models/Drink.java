package com.blackdiamond.models;

import java.util.Date;

import com.blackdiamond.interfaces.IEatable;

public class Drink extends Product implements IEatable {
    private boolean isAlcoholic;
    private boolean isImported;
    private double alcoholicPer;
    private Date expiredDate;
    private int kcal;

    public Drink(String id, String des, double stockPrice, double unitPrice, boolean hasStock,
            boolean isAlcoholic, boolean isImported, double alcoholicPer) {
        super(des, unitPrice, hasStock);
        setID(id);
        this.isAlcoholic = isAlcoholic;
        this.isImported = isImported;
        this.alcoholicPer = alcoholicPer;
    }

    @Override
    public void setID(String id) {
        if (!checkID(id))
            return;
        if (id.charAt(0) != 'A') {
            System.out.println("producto mal clasificado");
            return;
        }
        if (id.charAt(1) != 'C') {
            System.out.println("producto mal clasificado");
            return;
        }

        this.ID = id;
    }

    @Override
    public void setExpiredDate(Date date) {
        this.expiredDate = date;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getKcal() {
        return kcal;
    }
}
