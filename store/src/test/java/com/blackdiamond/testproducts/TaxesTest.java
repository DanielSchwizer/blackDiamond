package com.blackdiamond.testproducts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.PackagingType;

public class TaxesTest {

    /**
     * Prueba aplicar el impuesto del 10% a un producto envasado importado.
     * Se espera que el precio de venta se actualice correctamente con el impuesto.
     */
    @Test
    public void testImportedProductTax() {
        // Crear un producto envasado importado con un precio de venta de 10
        Packaged importedEnvasado = new Packaged("AB789", "Producto Importado", 10, PackagingType.VIDRIO, true, 500,
                "2024-04-08");
        float gainPer = 20;

        importedEnvasado.setStockPrice(gainPer);
        // precio de venta con porcentaje de ganancias
        float stockPriceWgains = importedEnvasado.getUnitPrice() * (1 + gainPer / 100);
        // precio esperado con impuesto del 10%
        float expectedPrice = stockPriceWgains * (1 + 0.1f);

        // Verificar que el precio de venta se haya actualizado correctamente
        assertEquals(expectedPrice, importedEnvasado.getStockPrice(), 0.01);
    }

    /**
     * Prueba a no aplicar el impuesto del 10% a un producto envasado no importado.
     * Se espera que el precio de venta quede tal cual esta
     */
    @Test
    public void testNonImportedProductTax() {
        // Crear un producto envasado no importado con un precio de venta de 10
        Packaged nonImportedEnvasado = new Packaged("AB789", "Producto Importado", 10, PackagingType.VIDRIO, false, 500,
                "2024-04-08");
        float gainPer = 20;

        nonImportedEnvasado.setStockPrice(gainPer);
        // precio de venta con porcentaje de ganancias
        float stockPriceWgains = nonImportedEnvasado.getUnitPrice() * (1 + gainPer / 100);
        // precio esperado sin impuestos
        float expectedPrice = stockPriceWgains;

        // Verificar que el precio de venta se haya actualizado correctamente
        assertEquals(expectedPrice, nonImportedEnvasado.getStockPrice(), 0.01);
    }

    @Test
    public void testLowerImportedProductTax() {
        // Crear un producto envasado importado con un precio de venta de 10
        Packaged importedEnvasado = new Packaged("AB789", "Producto Importado", 10, PackagingType.VIDRIO, true, 500,
                "2024-04-08");
        float gainPer = 20;

        importedEnvasado.setStockPrice(gainPer);
        // precio de venta con porcentaje de ganancias
        float stockPriceWgains = importedEnvasado.getUnitPrice() * (1 + gainPer / 100);
        // precio esperado con impuesto del 9%
        float expectedPrice = stockPriceWgains * (1 + 0.9f);

        // Verificar que el precio de venta sea distinto al precio esperado con 9% de descuento
        assertFalse(importedEnvasado.getStockPrice() == expectedPrice);
    }

    @Test
    public void testHigherImportedProductTax() {
        // Crear un producto envasado importado con un precio de venta de 10
        Packaged importedEnvasado = new Packaged("AB789", "Producto Importado", 10, PackagingType.VIDRIO, true, 500,
                "2024-04-08");
        float gainPer = 20;

        importedEnvasado.setStockPrice(gainPer);
        // precio de venta con porcentaje de ganancias
        float stockPriceWgains = importedEnvasado.getUnitPrice() * (1 + gainPer / 100);
        // precio esperado con impuesto del 11%
        float expectedPrice = stockPriceWgains * (1 + 0.11f);

        // Verificar que el precio de venta sea distinto al precio esperado con 11% de descuento
        assertFalse(importedEnvasado.getStockPrice() == expectedPrice);
    }

}
