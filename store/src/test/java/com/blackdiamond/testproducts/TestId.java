package com.blackdiamond.testproducts;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.blackdiamond.models.CleaningProduct;
import com.blackdiamond.models.Drink;
import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.CleaningType;
import com.blackdiamond.types.PackagingType;

public class TestId {
     
    @Test
    public void testInvalidPackageID() {
        try {
            Packaged invalidPackaged = new Packaged("AB3456", "Producto Inválido", 10, PackagingType.LATA, true, 500, "2024-04-08");
            Packaged invalidPackaged2 = new Packaged("12345", "Producto Inválido", 10, PackagingType.LATA, true, 500, "2024-04-08");
            Packaged invalidPackaged3 = new Packaged("AB35", "Producto Inválido", 10, PackagingType.LATA, true, 500, "2024-04-08");
            fail("Se esperaba IllegalArgumentException pero no se lanzó ninguna excepción");
        } catch (IllegalArgumentException e) {
            // Se espera que se lance una IllegalArgumentException
        }
    }

    @Test
    public void testInvalidDrinkID() {
        try {
            Drink invalidDrink = new Drink("12345", 20, "Bebida Inválida", false, false, 0, 600, "2024-08-09");
            Drink invalidDrink2 = new Drink("ac345", 20, "Bebida Inválida", false, false, 0, 600, "2024-08-09");
            Drink invalidDrink3 = new Drink("AC3456", 20, "Bebida Inválida", false, false, 0, 600, "2024-08-09");
            fail("Se esperaba IllegalArgumentException pero no se lanzó ninguna excepción");
        } catch (IllegalArgumentException e) {
            // Se espera que se lance una IllegalArgumentException
        }
    }

    @Test
    public void testInvalidCleaningID() {
        try {
            CleaningProduct invalidCleaningProduct = new CleaningProduct("az345", "Producto Limpieza Inválido", 20, CleaningType.MULTIUSO);
            CleaningProduct invalidCleaningProduct2 = new CleaningProduct("12345", "Producto Limpieza Inválido", 20, CleaningType.MULTIUSO);
            CleaningProduct invalidCleaningProduct3 = new CleaningProduct("AZ3455", "Producto Limpieza Inválido", 20, CleaningType.MULTIUSO);
            fail("Se esperaba IllegalArgumentException pero no se lanzó ninguna excepción");
        } catch (IllegalArgumentException e) {
            // Se espera que se lance una IllegalArgumentException
        }
    }

}


