package com.blackdiamond.testproducts;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.blackdiamond.models.CleaningProduct;
import com.blackdiamond.models.Drink;
import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.CleaningType;
import com.blackdiamond.types.PackagingType;

public class TestId {
    /**
     * Verifica que se lance una excepción IllegalArgumentException al intentar
     * crear un producto envasado con un identificador inválido.
     */
    @Test
    public void testInvalidPackageID() {
        try {
            Packaged invalidPackaged = new Packaged("AB3456", "Producto Inválido", 10, PackagingType.LATA, true, 500,
                    "2024-04-08");
            Packaged invalidPackaged2 = new Packaged("12345", "Producto Inválido", 10, PackagingType.LATA, true, 500,
                    "2024-04-08");
            Packaged invalidPackaged3 = new Packaged("AB35", "Producto Inválido", 10, PackagingType.LATA, true, 500,
                    "2024-04-08");
            fail("Se esperaba IllegalArgumentException pero no se lanzó ninguna excepción");
        } catch (IllegalArgumentException e) {
            // Se espera que se lance una IllegalArgumentException
        }
    }

    /**
     * Verifica que se lance una excepción IllegalArgumentException al intentar
     * crear una bebida con un identificador inválido.
     */
    @Test
    public void testInvalidDrinkID() {
        try {
            Drink invalidDrink = new Drink("12345", 20, "Bebida Inválida", false, false, 0, 600, "2024-08-09");
            Drink invalidDrink2 = new Drink("ac345", 20, "Bebida Inválida", false, false, 0, 600, "2024-08-09");
            fail("Se esperaba IllegalArgumentException pero no se lanzó ninguna excepción");
        } catch (IllegalArgumentException e) {
            // Se espera que se lance una IllegalArgumentException
        }
    }

    /**
     * Verifica que se lance una excepción IllegalArgumentException al intentar
     * crear un producto de limpieza con un identificador inválido.
     */
    @Test
    public void testInvalidCleaningID() {
        try {
            CleaningProduct invalidCleaningProduct = new CleaningProduct("az345", "Producto Limpieza Inválido", 20,
                    CleaningType.MULTIUSO);
            CleaningProduct invalidCleaningProduct2 = new CleaningProduct("12345", "Producto Limpieza Inválido", 20,
                    CleaningType.MULTIUSO);
            CleaningProduct invalidCleaningProduct3 = new CleaningProduct("AZ3455", "Producto Limpieza Inválido", 20,
                    CleaningType.MULTIUSO);
            fail("Se esperaba IllegalArgumentException pero no se lanzó ninguna excepción");
        } catch (IllegalArgumentException e) {
            // Se espera que se lance una IllegalArgumentException
        }
    }

    /**
     * verifica la creacion de id validos
     */
    @Test
    public void testValidPackageID() {
        Packaged validPackaged = new Packaged("AB356", "Producto válido", 10, PackagingType.LATA, true, 500,
                "2024-04-08");

        assertTrue(validPackaged != null);

    }

    @Test
    public void testValidDrinkID() {
        Drink validDrink = new Drink("AC345", 20, "Bebida válida", false, false, 0, 600, "2024-08-09");
        assertTrue(validDrink != null);

    }

    @Test
    public void testValidCleaningID() {
        CleaningProduct validCleaningProduct = new CleaningProduct("AZ345", "Producto Limpieza Inválido", 20,
                CleaningType.MULTIUSO);
        assertTrue(validCleaningProduct != null);

    }

}
