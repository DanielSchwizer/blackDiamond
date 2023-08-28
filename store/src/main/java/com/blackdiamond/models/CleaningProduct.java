package com.blackdiamond.models;

import com.blackdiamond.interfaces.ISalesMagnament;
import com.blackdiamond.types.CleaningType;

/**
 * Clase que representa un producto de limpieza.
 */
public class CleaningProduct extends Product implements ISalesMagnament {
    CleaningType tCleaningType;

    /**
     * Constructor de la clase CleaningProduct.
     *
     * @param id            Identificador del producto.
     * @param des           Descripción del producto.
     * @param unitPrice     Precio unitario(compra) del producto.
     * @param tCleaningType Tipo de producto de limpieza.
     */
    public CleaningProduct(String id, String des, float unitPrice,
            CleaningType tCleaningType) {
        super(des, unitPrice);
        setID(id);
        this.tCleaningType = tCleaningType;
    }

    /**
     * Establece el identificador del producto y verifica su formato.
     *
     * @param id Identificador del producto.
     * @throws IllegalArgumentException Si el identificador no cumple con el formato
     *                                  válido.
     */
    public void setID(String id) {
        checkID(id);
        if (!id.startsWith("AZ")) {
            System.out.println("producto mal clasificado");
            throw new IllegalArgumentException("identicador de producto invalido");
        }
        this.ID = id;
    }

    /**
     * Establece el precio de compra y aplica los impuestos correspondientes.
     *
     * @param gainPer Porcentaje de ganancia para el producto.
     * @throws IllegalArgumentException Si el porcentaje de ganancia no cumple con
     *                                  las restricciones.
     */
    public void setStockPrice(float gainPer) {
        checkGainPer(gainPer);
        this.stockPrice = (getUnitPrice() * (1 + gainPer / 100));
    }

    /**
     * Verifica si el descuento puede ser aplicado al producto.
     *
     * @param discount Descuento a aplicar en porcentaje.
     * @return `true` si el descuento es válido, `false` si no es válido.
     */
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

    /**
     * Establece el descuento para el producto.
     *
     * @param discount Descuento a aplicar en porcentaje.
     */
    public void setDiscount(float discount) {
        if (!checkDiscountPer(discount)) {
            return;
        }
        this.discountPer = discount;
        this.stockPrice = getDiscountPrice(discount);
    }

    /**
     * Verifica si el porcentaje de ganancia cumple con las restricciones.
     *
     * @param gainPer Porcentaje de ganancia para el producto.
     * @throws IllegalArgumentException Si el porcentaje de ganancia no cumple con
     *                                  las restricciones.
     */
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

    /**
     * implementacion de la interfaz
     */
    @Override
    public float getDiscount() {
        return getDiscountPrice(this.discountPer);
    }

    @Override
    public float getDiscountPercent() {
        return this.discountPer;
    }
}
