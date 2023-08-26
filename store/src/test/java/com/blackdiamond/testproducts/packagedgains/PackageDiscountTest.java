package com.blackdiamond.testproducts.packagedgains;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.PackagingType;

public class PackageDiscountTest {

    @Test
    public void testMaxDiscountPackaged() {
    Packaged productoEnvasado = new Packaged("AB456", "Producto Envasado", 20, PackagingType.LATA, true, 500, "2024-04-08");
    productoEnvasado.setStockPrice(20);
    productoEnvasado.setDiscount(21);

    assertEquals(0, productoEnvasado.getDiscountPercent(), 0.01);
}

}
