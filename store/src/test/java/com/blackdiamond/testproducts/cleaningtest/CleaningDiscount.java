package com.blackdiamond.testproducts.cleaningtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.CleaningProduct;
import com.blackdiamond.types.CleaningType;

public class CleaningDiscount {
    /**
     * Prueba establecer un porcentaje de descuento mayor al 25% en un producto de
     * limpieza.
     * Se espera que el porcentaje de descuento sea 0
     */
    @Test
    public void testMaxDiscountCleaningProduct() {
        CleaningProduct productoLimpieza = new CleaningProduct("AZ789", "Producto Limpieza", 20, CleaningType.PISOS);
        productoLimpieza.setStockPrice(25);
        productoLimpieza.setDiscount(26); // Establecer porcentaje de descuento mayor al 25%

        assertEquals(0, productoLimpieza.getDiscountPercent(), 0.01);
    }
}
