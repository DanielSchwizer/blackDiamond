package com.blackdiamond;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.blackdiamond.models.CleaningProduct;
import com.blackdiamond.models.Drink;
import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.CleaningType;
import com.blackdiamond.types.PackagingType;

/**
 * Pruebas unitarias para la clase Store.
 */
public class StoreTest {

    private Store store;
    private Packaged productoEnvasado;
    private Drink productoBebida;
    private CleaningProduct productoLimpieza;
    private Packaged comestible1;
    private Packaged comestible2;
    private Packaged comestible3;

    /**
     * Configuración inicial para las pruebas.
     */
    @Before
    public void setUp() {
        // Inicializar la tienda y los productos para cada prueba
        store = new Store(100, 1000);
        productoEnvasado = new Packaged("AB123", "producto envasado", 20, PackagingType.LATA, true, 500, "2024-04-08");
        productoBebida = new Drink("AC345", 20, "producto bebida", false, false, 0, 600, "2024-08-09");
        productoLimpieza = new CleaningProduct("AZ431", "producto limpieza", 20, CleaningType.MULTIUSO);

        comestible1 = new Packaged("AB321", "comestible1", 20, PackagingType.VIDRIO, false, 500, "2023-10-15");
        comestible2 = new Packaged("AB123", "comestible2", 40, PackagingType.VIDRIO, false, 500, "2023-10-15");
        comestible3 = new Packaged("AB521", "comestible3", 60, PackagingType.VIDRIO, false, 500, "2023-10-15");

    }

    /**
     * Comprueba si un producto se agrega correctamente a la tienda.
     */
    @Test
    public void testBuyProduct() {
        store.buyProducts(productoEnvasado, 1);
        assertTrue(store.getList().containsValue(productoEnvasado));
    }

    /**
     * Comprueba que no sea posible agregar más productos al stock máximo.
     */
    @Test
    public void testMaxStock() {
        // Agregar productos hasta alcanzar el stock máximo
        for (int i = 0; i < 100; i++) {
            Packaged productoEnvasado = new Packaged("AB123", "producto envasado", 20, PackagingType.LATA, true, 500,
                    "2024-04-08");
            store.buyProducts(productoEnvasado, i);
        }

        // Intentar agregar un producto más allá del stock máximo
        Packaged productoEnvasado = new Packaged("AB123", "producto envasado", 20, PackagingType.LATA, true, 500,
                "2024-04-08");
        store.buyProducts(productoEnvasado, 1);
        assertFalse(store.getMaxStock() > 100);
    }

    /**
     * Comprueba si los productos se venden y actualizan correctamente en la tienda.
     */
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

    /**
     * Comprueba si la compra con saldo suficiente reduce el balance correctamente.
     */
    @Test
    public void testPurchaseWithSufficientBalance() {
        store.buyProducts(productoEnvasado, 5);
        assertEquals(1000 - (20 * 5), store.getBalance(), 0.001); // El saldo debe disminuir correctamente
    }

    /**
     * Comprueba si la compra con saldo insuficiente no afecta el balance.
     */
    @Test
    public void testPurchaseWithInsufficientBalance() {
        store = new Store(100, 50);
        store.buyProducts(productoEnvasado, 5); // Compra fallida
        assertEquals(50, store.getBalance(), 0.001); // El saldo no debe haber cambiado
    }

    /**
     * Comprueba si se supera el límite de tipos de productos por venta.
     */
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

    /**
     * Comprueba si se excede la cantidad máxima de unidades por producto en venta.
     */
    @Test
    public void testExceedMaxUnitsPerProduct() {
        store.buyProducts(productoEnvasado, 12);

        store.addToCart(productoEnvasado, 11); // Intentar vender más de 10 unidades
        store.sellProducts();

        int remainingUnits = productoEnvasado.getStock();
        assertEquals(12, remainingUnits); // Se espera que el stock no se haya actualizado
    }

    /**
     * Comprueba si se intenta vender más unidades de las disponibles en stock.
     */
    @Test
    public void testSellExceedStock() {
        store.buyProducts(productoEnvasado, 5); // Comprar 5 unidades de producto envasado

        store.addToCart(productoEnvasado, 7); // Intentar vender 7 unidades (más de las disponibles)
        store.sellProducts();

        // Verificar que el producto envasado se haya actualizado con las unidades
        // disponibles y esté fuera de venta
        assertEquals(0, productoEnvasado.getStock());
        assertFalse(productoEnvasado.getHasStock());
        assertFalse(productoEnvasado.getIsForsale());
    }

    /**
     * Comprueba si se obtienen los productos comestibles con descuento menor al
     * valor dado.
     */
    @Test
    public void testproductsObtainEatablesWithLessDiscount() {
        store.buyProducts(comestible1, 1);
        store.buyProducts(comestible2, 1);
        store.buyProducts(comestible3, 1);

        comestible1.setStockPrice(20.0f);
        comestible2.setStockPrice(20.0f);
        comestible3.setStockPrice(14.0f);

        comestible1.setDiscount(10.0f);
        comestible2.setDiscount(7.0f);
        comestible3.setDiscount(9.0f);

        List<String> result = store.obtainEatablesWithLessDiscount(10.0f);
        assertEquals(2, result.size());
        assertEquals("COMESTIBLE2", result.get(0));
        assertEquals("COMESTIBLE3", result.get(1));
    }

    /**
     * Comprueba si se obtienen los productos con utilidades menores al porcentaje de ganancia dado.
     */
    @Test
    public void testlistProductsWithLowerUtilities() {
        store.buyProducts(comestible1, 1);
        store.buyProducts(comestible2, 1);
        store.buyProducts(comestible3, 1);

        comestible1.setStockPrice(20.0f);
        comestible2.setStockPrice(11.0f);
        comestible3.setStockPrice(9.0f);

        List<String> result = store.listProductsWithLowerUtilities(10.0f);
        assertEquals(1, result.size());
        assertEquals("Código: AB521  Descripcion: comestible3  Cantidad en stock: 1", result.get(0));
    }
}
