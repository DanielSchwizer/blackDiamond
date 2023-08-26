package com.blackdiamond.models;

import com.blackdiamond.interfaces.ISalesMagnament;
import com.blackdiamond.types.CleaningType;

public class CleaningProduct extends Product implements ISalesMagnament {
    CleaningType tCleaningType;

    public CleaningProduct(String id, String des, float unitPrice,
            CleaningType tCleaningType) {
        super(des, unitPrice);
        setID(id);
        this.tCleaningType = tCleaningType;
    }

    public void setID(String id) {
        checkID(id);
        if (!id.startsWith("AZ")) {
            System.out.println("producto mal clasificado");
            throw new IllegalArgumentException("identicador de producto invalido");
        }
        this.ID = id;
    }

    public void setStockPrice(float gainPer) {
        checkGainPer(gainPer);
        this.stockPrice = (getUnitPrice() * (1 + gainPer / 100));
    }

    public boolean checkDiscountPer(float discount) {
        if (discount > 25) {
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


    public void checkGainPer(float gainPer) {
        boolean isTypeROPAoMULTIUSO = tCleaningType.equals(CleaningType.ROPA)
                || tCleaningType.equals(CleaningType.MULTIUSO);
    
        if (!isTypeROPAoMULTIUSO && (gainPer < 10 || gainPer > 25)) {
            throw new IllegalArgumentException(
                "El porcentaje de ganancia para productos de limpieza no cumple con las restricciones.");
        } else if (gainPer > 25) {
            throw new IllegalArgumentException(
                "El porcentaje de ganancia para productos de limpieza no puede superar el 25%.");
        }
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
