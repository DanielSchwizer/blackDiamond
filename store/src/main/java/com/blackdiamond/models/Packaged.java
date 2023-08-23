package com.blackdiamond.models;

import java.util.Date;

import com.blackdiamond.interfaces.IEatable;
import com.blackdiamond.interfaces.ISalesMagnament;
import com.blackdiamond.types.PackagingType;

public class Packaged extends Product implements IEatable, ISalesMagnament {
    private PackagingType pType;
    private boolean isImported;
    private Date expiredDate;
    private int kcal;
    private double gainPer;

    public Packaged(String id, String des, double unitPrice, boolean hasStock,
            PackagingType pType, boolean isImported, double gainPer) {
        super(des, unitPrice, hasStock);
        setID(id);
        this.pType = pType;
        this.isImported = isImported;
        setStockPrice(gainPer);
    }

    public PackagingType getpType() {
        return pType;
    }

    public boolean getisImported() {
        return isImported;
    }

    public double getGainPer() {
        return gainPer;
    }

    @Override
    public void setID(String id) {
        if (!checkID(id))
            return;
        if (id.charAt(0) != 'A') {
            System.out.println("producto mal clasificado");
            return;
        }
        if (id.charAt(1) != 'B') {
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

    @Override
    public void setStockPrice(double gainPer) {
        checkGainPer(gainPer);
        this.gainPer = gainPer;
        this.stockPrice = getUnitPrice() * (1 + getGainPer() / 100);

    }

    @Override
    public void checkGainPer(double gainPer) {
        if (gainPer > 20) {
            throw new IllegalArgumentException("El porcentaje de ganancia para comestibles no puede superar el 20%.");
        }
    }

}
