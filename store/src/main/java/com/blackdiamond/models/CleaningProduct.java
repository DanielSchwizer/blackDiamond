package com.blackdiamond.models;

import com.blackdiamond.interfaces.ISalesMagnament;
import com.blackdiamond.types.CleaningType;

public class CleaningProduct extends Product implements ISalesMagnament {
    CleaningType tCleaningType;
    double gainPer;

    public CleaningProduct(String id, String des, float unitPrice, boolean hasStock,
            CleaningType tCleaningType, double gainPer) {
        super(des, unitPrice, hasStock);
        setID(id);
        this.tCleaningType = tCleaningType;
        setStockPrice(gainPer);
    }

    @Override
    public void setID(String id) {
        if (!checkID(id))
            return;
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
    public void setStockPrice(double gainPer) {
        checkGainPer(gainPer);
        this.gainPer = gainPer;
        this.stockPrice = getUnitPrice() * (1 + getGainPer() / 100);
    }

    public double getGainPer() {
        return gainPer;
    }

    @Override
    public void checkGainPer(double gainPer) {
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
