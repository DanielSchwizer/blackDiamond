package com.blackdiamond.testproducts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.PackagingType;

public class TaxesTest {
    @Test
public void testImportedProductTax() {
    // Crear un producto envasado importado con un precio de venta de 50
    Packaged importedEnvasado = new Packaged("AB789", "Producto Importado", 10, PackagingType.VIDRIO, true, 500, "2024-04-08");
    float gainPer = 20;

    importedEnvasado.setStockPrice(gainPer);
    //precio de venta con porcentaje de ganancias
    float stockPriceWgains = importedEnvasado.getUnitPrice() *(1 + gainPer/100);
    //precio esperado con impuesto del 10%
    float expectedPrice = stockPriceWgains * (1 + 0.1f);
   
    
    // Verificar que el precio de venta se haya actualizado correctamente
    assertEquals(expectedPrice, importedEnvasado.getStockPrice(), 0.01);
}
}
