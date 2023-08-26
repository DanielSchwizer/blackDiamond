package com.blackdiamond.testproducts.discount;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.PackagingType;

public class GeneralDiscountTest {

    @Test
    public void testDiscountWithLoss() {
        // Crear un producto envasado con un precio de costo de 15 y un precio de venta de 20
        Packaged envasadoConPerdida = new Packaged("AB456", "Producto con Pérdida", 10, PackagingType.PLASTICO, false, 500, "2024-04-08");
        
        // Aplicar un descuento del 30%, que generará pérdidas
        envasadoConPerdida.setDiscount(15);
        
        // Verificar que el descuento no se haya aplicado y el precio de venta se mantenga igual (20)
        assertEquals(10, envasadoConPerdida.getUnitPrice(), 0.01);
    }
}
