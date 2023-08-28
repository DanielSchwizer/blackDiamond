package com.blackdiamond.testproducts.packagedtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.PackagingType;

public class PackagedGainTest {
    /**
     * Prueba establecer un porcentaje de ganancia que supere el límite máximo en un
     * producto envasado.
     * Se espera que la prueba lance una excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMaxGainPackaged() {
        Packaged productoEnvasado = new Packaged("AB789", "Producto Envasado", 20, PackagingType.LATA, true, 500,
                "2024-04-08");
        productoEnvasado.setStockPrice(21);

    }

    /**
     * Prueba establecer un porcentaje de ganancia que sea valido para un producto
     * envasado
     */
    @Test
    public void testGainPackaged() {
        Packaged productoEnvasado = new Packaged("AB789", "Producto Envasado", 20, PackagingType.LATA, false, 500,
                "2024-04-08");
        float gainPer = 20;
        productoEnvasado.setStockPrice(gainPer);
        //precio esperado con ganancias
        float expectedPrice = productoEnvasado.getUnitPrice() * (1 + gainPer / 100);
        //verifica que cumpla el precio esperado
        assertEquals(expectedPrice, productoEnvasado.getStockPrice(), 0.01);

    }
}
