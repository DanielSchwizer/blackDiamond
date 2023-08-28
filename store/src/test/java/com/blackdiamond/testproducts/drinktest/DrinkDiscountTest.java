package com.blackdiamond.testproducts.drinktest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.Drink;

public class DrinkDiscountTest {
    /**
     * Prueba establecer un precio de stock con un porcentaje de ganancia que excede
     * el límite máximo.
     * Se espera que no se aplique ningun descuento.
     */
    @Test
    public void testMaxDiscountDrink() {
        Drink productoBebida = new Drink("AC123", 20, "Producto Bebida", false, false, 0, 600, "2024-08-09");
        productoBebida.setStockPrice(20);
        productoBebida.setDiscount(16); // Establecer porcentaje de descuento mayor al 15%

        assertEquals(0, productoBebida.getDiscountPercent(), 0.01);
    }
     /**
     * Prueba establecer un porcentaje de descuento que sea valido para una bebida
     */
    @Test
    public void testDiscountDrink() {
        Drink productoBebida = new Drink("AC123", 20, "Producto Bebida", false, false, 0, 600, "2024-08-09");
        float gainPer = 20;
        float discount = 15;
        productoBebida.setStockPrice(gainPer);
        productoBebida.setDiscount(discount);
        // el precio que se espera tenga una bebida
        float stockPriceWgains = productoBebida.getUnitPrice() * (1 + gainPer / 100);
        float expectedDiscount = stockPriceWgains * (discount / 100);
        float expectedPrice = stockPriceWgains - expectedDiscount;

        // se verifica que el producto tenga ese precio despues del descuento
        assertEquals(expectedPrice, productoBebida.getStockPrice(), 0.01);
    }
}
