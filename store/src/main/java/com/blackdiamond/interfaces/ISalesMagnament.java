package com.blackdiamond.interfaces;

public interface ISalesMagnament {
    /**
     * Establece el precio de venta de un producto aplicando un porcentaje de
     * ganancia.
     * 
     * @param gainPer El porcentaje de ganancia a aplicar al precio de compra.
     * @throws IllegalArgumentException Si el porcentaje de ganancia no cumple con
     *                                  las restricciones establecidas.
     */
    public void setStockPrice(float gainPer);
}
