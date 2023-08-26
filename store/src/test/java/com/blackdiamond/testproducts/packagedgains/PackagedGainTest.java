package com.blackdiamond.testproducts.packagedgains;

import org.junit.Test;

import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.PackagingType;

public class PackagedGainTest {
    @Test(expected = Exception.class)
    public void testMaxGainPackaged() {
        Packaged productoEnvasado = new Packaged("AB789", "Producto Envasado", 20, PackagingType.LATA, true, 500, "2024-04-08");
        productoEnvasado.setStockPrice(21); 

    }
}
