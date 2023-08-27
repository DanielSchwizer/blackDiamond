package com.blackdiamond.testproducts.drinktest;

import org.junit.Test;

import com.blackdiamond.models.Drink;

/**
 * Prueba establecer un precio de stock con un porcentaje de ganancia que excede
 * el límite máximo.
 * Se espera que se lance una IllegalArgumentException.
 */
public class DrinkTest {
    @Test(expected = IllegalArgumentException.class)
    public void testMaxGainDrink() {
        Drink productoEnvasado = new Drink("AC123", 20, "Bebida Inválida", false, false, 0, 600, "2024-08-09");
        ;
        productoEnvasado.setStockPrice(21);

    }
}
