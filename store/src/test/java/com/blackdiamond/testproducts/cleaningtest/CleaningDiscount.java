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

    /**
     * Prueba establecer un porcentaje de descuento que sea valido para un producto
     * de limpieza
     */
    @Test
    public void testDiscountPackaged() {
        CleaningProduct productoLimpieza = new CleaningProduct("AZ789", "Producto Limpieza", 20, CleaningType.PISOS);
        float gainPer = 20;
        float discount = 15;
        productoLimpieza.setStockPrice(gainPer);
        productoLimpieza.setDiscount(discount);
        // el precio que se espera tenga el producto de limpieza
        float stockPriceWgains = productoLimpieza.getUnitPrice() * (1 + gainPer / 100);
        float expectedDiscount = stockPriceWgains * (discount / 100);
        float expectedPrice = stockPriceWgains - expectedDiscount;

        // se verifica que el producto tenga ese precio despues del descuento
        assertEquals(expectedPrice, productoLimpieza.getStockPrice(), 0.01);
    }

}
