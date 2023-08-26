package com.blackdiamond.testproducts.drinktest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.Drink;

public class DrinkDiscount {
    @Test
    public void testMaxDiscountDrink() {
    Drink productoBebida = new Drink("AC123", 20, "Producto Bebida", false, false, 0, 600, "2024-08-09");
    productoBebida.setStockPrice(20);
    productoBebida.setDiscount(16); // Establecer porcentaje de descuento mayor al 15%

    assertEquals(0, productoBebida.getDiscountPercent(), 0.01);
    }
}
