package com.blackdiamond.testproducts.packagedtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.PackagingType;

public class PackageDiscountTest {
    /**
     * Prueba establecer un porcentaje de descuento que supere el límite máximo en
     * un producto envasado.
     * Se espera que el porcentaje de descuento sea 0.
     */
    @Test
    public void testMaxDiscountPackaged() {
        Packaged productoEnvasado = new Packaged("AB456", "Producto Envasado", 20, PackagingType.LATA, true, 500,
                "2024-04-08");
        productoEnvasado.setStockPrice(20);
        productoEnvasado.setDiscount(21);

        assertEquals(0, productoEnvasado.getDiscountPercent(), 0.01);
    }
    /**
     * Prueba establecer un porcentaje de descuento que sea valido para un producto envasado
     */
    @Test
    public void testDiscountPackaged() {
        Packaged productoEnvasado = new Packaged("AB456", "Producto Envasado", 20, PackagingType.LATA, false, 500,
                "2024-04-08");
        float gainPer = 20;
        float discount = 15;
        productoEnvasado.setStockPrice(gainPer);
        productoEnvasado.setDiscount(discount);
        //el precio que se espera tenga el producto envasado
        float stockPriceWgains = productoEnvasado.getUnitPrice() * (1 + gainPer / 100);
        float expectedDiscount = stockPriceWgains *(discount/100);
        float expectedPrice = stockPriceWgains - expectedDiscount;

        //se verifica que el producto tenga ese precio despues del descuento
        assertEquals(expectedPrice, productoEnvasado.getStockPrice(), 0.01);
    }

}
