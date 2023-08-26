package com.blackdiamond.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.blackdiamond.interfaces.IEatable;
import com.blackdiamond.interfaces.ISalesMagnament;
import com.blackdiamond.types.PackagingType;

public class Packaged extends Product implements IEatable, ISalesMagnament {
    private PackagingType pType;
    private boolean isImported;
    private String expiredDate;
    private int kcal;
    private float finalPrice;
    public Packaged(String id, String des, float unitPrice,
            PackagingType pType, boolean isImported, int kcal, String expiredDate) {
        super(des, unitPrice);
        setID(id);
        this.pType = pType;
        this.isImported = isImported;
        setKcal(kcal);
        setExpiredDate(expiredDate);
    }

    public void setID(String id) {
        checkID(id);
        if (!id.startsWith("AB")) {
            System.out.println("producto mal clasificado");
            throw new IllegalArgumentException("identicador de producto invalido");
        }
        this.ID = id;
    }

    public void setExpiredDate(String date) {
        try {
            this.expiredDate = new SimpleDateFormat("dd/MM/yyyy").parse(date).toString();
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }
    }

    public PackagingType getpType() {
        return pType;
    }

    public boolean getisImported() {
        return isImported;
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

    public void setStockPrice(float gainPer) {
        if (gainPer > 20) {
            throw new IllegalArgumentException("El porcentaje de ganancia para comestibles no puede superar el 20%.");
        }
       
        this.stockPrice = (getUnitPrice() * (1 + gainPer / 100));
        addTaxes();
    }

    public void setFinalStockPrice(float discount) {
        setDiscount(discount);
        this.stockPrice -= (stockPrice * discountPer / 100);
    }

     public void addTaxes(){
        if(isImported){
            stockPrice += stockPrice * 0.1;
            System.out.println("al producto se le aplicara un impuesto del 10% por ser importado");
            System.out.println("precio con impuestos :" + stockPrice);
        }
     }

    public boolean checkDiscountPer(float discount) {
        if (discount > 20) {
            System.out.println("el descuento no pudo ser aplicado");
            return false;
        }
        if (!validDiscount(discount)) {
            System.out.println("el descuento no pudo ser aplicado porque es menor al precio de compra");
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

    

    @Override
    public float getDiscount() {
       return getDiscountPrice(this.discountPer);
    }

    @Override
    public float getDiscountPercent() {
        return this.discountPer;
    }

}
