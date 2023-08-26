package com.blackdiamond.testproducts.drinktest;

import org.junit.Test;

import com.blackdiamond.models.Drink;
import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.PackagingType;

public class DrinkTest {
    @Test(expected = Exception.class)
    public void testMaxGainDrink() {
        Drink productoEnvasado = new Drink("AC123", 20, "Bebida Inválida", false, false, 0, 600, "2024-08-09");;
        productoEnvasado.setStockPrice(21); 

    }
}
