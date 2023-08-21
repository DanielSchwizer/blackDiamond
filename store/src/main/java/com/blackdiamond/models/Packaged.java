package com.blackdiamond.models;

import java.util.Date;

import com.blackdiamond.interfaces.IEatable;
import com.blackdiamond.types.PackagingType;

public class Packaged extends Product implements IEatable {
    private PackagingType pType;
    private boolean isImported;
    private Date expiredDate;
    private int kcal;

    public Packaged(String id, String des, int stock, double stockPrice, double unitPrice, boolean hasStock,
            PackagingType pType, boolean isImported) {
        setID(id);
        this.description = des;
        this.stock = stock;
        this.stockPrice = stockPrice;
        this.unitPrice = unitPrice;
        this.hasStock = hasStock;
        this.pType = pType;
        this.isImported = isImported;
    }

    public PackagingType getpType() {
        return pType;
    }

    public boolean getisImported(){
        return isImported;
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
}
