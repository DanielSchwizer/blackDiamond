package com.blackdiamond.testproducts.discount;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.PackagingType;

public class GeneralDiscountTest {
    /**
     * Prueba aplicar un descuento que genere pérdida en un producto envasado.
     * Se espera que el descuento no se aplique y el precio de compra se mantenga
     * igual.
     */
    @Test
    public void testDiscountWithLoss() {

        Packaged envasadoConPerdida = new Packaged("AB456", "Producto con Pérdida", 10, PackagingType.PLASTICO, false,
                500, "2024-04-08");

        envasadoConPerdida.setDiscount(15);

        // Verificar que el descuento no se haya aplicado y el precio de compra se
        // mantenga igual (10)
        assertEquals(10, envasadoConPerdida.getUnitPrice(), 0.01);
    }
}
