package com.blackdiamond.interfaces;

public interface IDiscount {

    /**
     * Establece el descuento a aplicar al producto.
     *
     * @param discount Descuento a aplicar.
     */
    public void setDiscount(float discount);

    /**
     * Obtiene el porcentaje de descuento aplicado al producto.
     *
     * @return Porcentaje de descuento del producto.
     */
    public float getDiscountPercent();

    /**
     * Obtiene el precio con descuento del producto.
     *
     * @return Precio con descuento del producto.
     */
    public float getDiscount();
}
