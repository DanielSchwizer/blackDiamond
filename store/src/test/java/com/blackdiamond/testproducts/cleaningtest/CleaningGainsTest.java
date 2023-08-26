package com.blackdiamond.testproducts.cleaningtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blackdiamond.models.CleaningProduct;
import com.blackdiamond.types.CleaningType;

public class CleaningGainsTest {
    
     @Test(expected = Exception.class)
    public void testMinGainCleaningProduct() {
        CleaningProduct productoLimpieza = new CleaningProduct("AZ567", "Producto Limpieza", 20, CleaningType.PISOS);
        productoLimpieza.setStockPrice(9); // Establecer porcentaje de ganancia menor al 10% 
    }



    @Test(expected = Exception.class)
    public void testMaxGainCleaningProduct() {
        CleaningProduct productoLimpieza = new CleaningProduct("AZ567", "Producto Limpieza", 20, CleaningType.PISOS);
        productoLimpieza.setStockPrice(26); // Establecer porcentaje de ganancia menor al 10% 
    }


    @Test
    public void testNoMinGainClothing() {
        CleaningProduct productoRopa = new CleaningProduct("AZ678", "Producto Ropa", 20, CleaningType.ROPA);
        productoRopa.setStockPrice(9); // Establecer porcentaje de ganancia menor al mínimo (5%)
        assertEquals(9, productoRopa.getGainPer(), 0.01);
    }
      

    @Test
    public void testNoMinGainMultiuse() {
        CleaningProduct productoMultiuso = new CleaningProduct("AZ789", "Producto Multiuso", 20, CleaningType.MULTIUSO);
        productoMultiuso.setStockPrice(9); // Establecer porcentaje de ganancia menor al mínimo
        assertEquals(9, productoMultiuso.getGainPer(), 0.01);
    }
}
