package com.blackdiamond;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.blackdiamond.models.CleaningProduct;
import com.blackdiamond.models.Drink;
import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.CleaningType;
import com.blackdiamond.types.PackagingType;

/**
 * Unit test for simple App.
 */
public class StoreTest {

    private Store store;
    private Packaged productoEnvasado;
    private Drink productoBebida;
    private CleaningProduct productoLimpieza;

    /**
     * Rigorous Test :-)
     */
    @Before
    public void setUp() {
        // Inicializar la tienda y los productos para cada prueba
        store = new Store(100, 1000); // Ejemplo de valores, ajusta según tu implementación

        productoEnvasado = new Packaged("AB123", "producto envasado", 20, PackagingType.LATA, true, 500,"2024-04-08");
        productoBebida = new Drink("AC345", 20, "producto bebida", false, false, 0, 600, "2024-08-09");
        productoLimpieza = new CleaningProduct("AZ431", "producto limpieza", 20, CleaningType.MULTIUSO);
    }

    @Test
    public void testBuyProduct() {
        store.buyProducts(productoEnvasado, 1);
        assertTrue(store.getList().containsValue(productoEnvasado));
    }

    @Test
    public void testMaxStock() {
        // Agregar productos hasta alcanzar el stock máximo
        for (int i = 0; i < 100; i++) {
            Packaged productoEnvasado = new Packaged("AB123", "producto envasado", 20, PackagingType.LATA, true, 500,"2024-04-08");
            store.buyProducts(productoEnvasado, i);
        }

        // Intentar agregar un producto más allá del stock máximo
        Packaged productoEnvasado = new Packaged("AB123", "producto envasado", 20, PackagingType.LATA, true, 500,
                "2024-04-08");
        store.buyProducts(productoEnvasado, 1);
        assertFalse(store.getMaxStock() > 100);
    }

    @Test
    public void testSellProducts() {
        store.buyProducts(productoEnvasado, 4);
        store.buyProducts(productoBebida, 3);
        store.buyProducts(productoLimpieza, 4);

        store.addToCart(productoEnvasado, 4);
        store.addToCart(productoBebida, 2);
        store.sellProducts();

        assertEquals(0, productoEnvasado.getStock());
        assertEquals(1, productoBebida.getStock());
        assertEquals(4, productoLimpieza.getStock());

        // Verificar que los productos se hayan vendido y actualizado correctamente
        assertTrue(store.getList().containsValue(productoEnvasado));
        assertTrue(store.getList().containsValue(productoBebida));
        assertTrue(store.getList().containsValue(productoLimpieza));
    }
    @Test
    public void testPurchaseWithSufficientBalance() {
        store.buyProducts(productoEnvasado, 5);
        assertEquals(1000 - (20 * 5), store.getBalance(), 0.001); // El saldo debe disminuir correctamente
    }

    @Test
    public void testPurchaseWithInsufficientBalance() {
        store = new Store(100, 50);
        store.buyProducts(productoEnvasado, 5); // Compra fallida
        assertEquals(50, store.getBalance(), 0.001); // El saldo no debe haber cambiado
    }

    @Test
    public void testExceedMaxProductTypesPerSale() {
        Drink gaseosa = new Drink("AC123", 20, "GASEOSA", false, false, 0, 500, "2025-08-05");
        store.buyProducts(productoEnvasado, 2);
        store.buyProducts(productoBebida, 3);
        store.buyProducts(productoLimpieza, 4);
        

        store.addToCart(productoEnvasado, 1);
        store.addToCart(productoBebida, 2);
        store.addToCart(productoLimpieza, 1);
        store.addToCart(gaseosa, 1);
        store.sellProducts();

        // Verificar que los productos se hayan vendido y actualizado correctamente
        assertTrue(store.getList().containsValue(productoEnvasado));
        assertTrue(store.getList().containsValue(productoBebida));
        assertTrue(store.getList().containsValue(productoLimpieza));
        assertFalse(store.getList().containsValue(gaseosa));
    }

    @Test
    public void testExceedMaxUnitsPerProduct() {
        store.buyProducts(productoEnvasado, 7);

        store.addToCart(productoEnvasado, 11); // Intentar vender más de 10 unidades
        store.sellProducts();

        int remainingUnits = productoEnvasado.getStock();
        assertEquals(7, remainingUnits); // Se espera que el stock no se haya actualizado
    }

    @Test
    public void testSellExceedStock() {
        store.buyProducts(productoEnvasado, 5); // Comprar 5 unidades de producto envasado

        store.addToCart(productoEnvasado, 7); // Intentar vender 7 unidades (más de las disponibles)
        store.sellProducts();

        // Verificar que el producto envasado se haya actualizado con las unidades disponibles y esté fuera de venta
        assertEquals(0, productoEnvasado.getStock());
        assertFalse(productoEnvasado.getHasStock());
        assertFalse(productoEnvasado.getIsForsale());
    }
}


   

    






    

