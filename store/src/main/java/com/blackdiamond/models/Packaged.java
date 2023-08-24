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
    private float gainPer;

    public Packaged(String id, String des, float unitPrice, 
            PackagingType pType, boolean isImported, float gainPer, float discountPer) {
        super(des, unitPrice);
        setID(id);
        this.pType = pType;
        this.isImported = isImported;
        setStockPrice(gainPer, discountPer);
    }

    public PackagingType getpType() {
        return pType;
    }

    public boolean getisImported() {
        return isImported;
    }

    public float getGainPer() {
        return gainPer;
    }

    @Override
    public void setID(String id) {
        checkID(id);
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
    public void setStockPrice(float gainPer, float discountPer) {
        checkGainPer(gainPer);
        setDiscountPer(discountPer);
        this.gainPer = gainPer;
        this.stockPrice = getUnitPrice() * (1 + getGainPer() / 100);

    }

    @Override
    public void checkGainPer(float gainPer) {
        if (gainPer > 20) {
            throw new IllegalArgumentException("El porcentaje de ganancia para comestibles no puede superar el 20%.");
        }
    }

    public void setDiscountPer(float discount){
        if(discount > 20 && !validDiscount(discount)){
            System.out.println("el descuento no pudo ser aplicado");
            return;
        }
        this.discountPer = discount;
    }

}
