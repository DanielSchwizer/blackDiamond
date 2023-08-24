package com.blackdiamond.models;

import com.blackdiamond.interfaces.ISalesMagnament;
import com.blackdiamond.types.CleaningType;

public class CleaningProduct extends Product implements ISalesMagnament {
    CleaningType tCleaningType;
    float gainPer;

    public CleaningProduct(String id, String des, float unitPrice,
            CleaningType tCleaningType, float gainPer, float discountPer) {
        super(des, unitPrice);
        setID(id);
        this.tCleaningType = tCleaningType;
        setStockPrice(gainPer,discountPer);
    }

    @Override
    public void setID(String id) {
        checkID(id);
        if (id.charAt(0) != 'A') {
            System.out.println("producto mal clasificado");
            return;
        }
        if (id.charAt(1) != 'Z') {
            System.out.println("producto mal clasificado");
            return;
        }

        this.ID = id;
    }

    @Override
    public void setStockPrice(float gainPer, float discountPer) {
        checkGainPer(gainPer);
        this.gainPer = gainPer;
        this.stockPrice = getUnitPrice() * (1 + getGainPer() / 100) - discountPer;
    }

    public void setDiscountPer(float discount){
        if(discount > 25 && !validDiscount(discount)){
            System.out.println("el descuento no pudo ser aplicado");
            return;
        }
        this.discountPer = discount;
    }

    public float getGainPer() {
        return gainPer;
    }

    @Override
    public void checkGainPer(float gainPer) {
        boolean isTypeROPAoMULTIUSO = tCleaningType.equals(CleaningType.ROPA)
                || tCleaningType.equals(CleaningType.MULTIUSO);

        if (isTypeROPAoMULTIUSO && (gainPer < 10 || gainPer > 25)) {
            throw new IllegalArgumentException(
                    "El porcentaje de ganancia para productos de limpieza no cumple con las restricciones.");
        } else if (isTypeROPAoMULTIUSO && gainPer > 25) {
            throw new IllegalArgumentException(
                    "El porcentaje de ganancia para productos de limpieza tipo ROPA o MULTIUSO no puede superar el 25%.");
        }

    }
}
