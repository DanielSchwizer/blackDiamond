package com.blackdiamond.testproducts.drinktest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.Drink;

/**
 * Prueba establecer un precio de stock con un porcentaje de ganancia que excede
 * el límite máximo.
 * Se espera que se lance una IllegalArgumentException.
 */
public class DrinkGainTest {
    @Test(expected = IllegalArgumentException.class)
    public void testMaxGainDrink() {
        Drink productoBebida = new Drink("AC123", 20, "Bebida Inválida", false, false, 0, 600, "2024-08-09");
        ;
        productoBebida.setStockPrice(21);

    }

    /**
     * Prueba establecer un porcentaje de ganancia que sea valido para una bebida
     * 
     */
    @Test
    public void testGainDrink() {
        Drink productoBebida = new Drink("AC123", 20, "Bebida Inválida", false, false, 0, 600, "2024-08-09");
        float gainPer = 20;
        productoBebida.setStockPrice(gainPer);
        // precio esperado con ganancias
        float expectedPrice = productoBebida.getUnitPrice() * (1 + gainPer / 100);
        // verifica que cumpla el precio esperado
        assertEquals(expectedPrice, productoBebida.getStockPrice(), 0.01);

    }

}
