package com.blackdiamond.testproducts.packagedtest;

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
}
