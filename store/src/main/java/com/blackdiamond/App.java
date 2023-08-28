package com.blackdiamond;

import java.util.List;

import com.blackdiamond.models.CleaningProduct;
import com.blackdiamond.models.Drink;
import com.blackdiamond.models.Packaged;
import com.blackdiamond.shoppingcart.ShoppingCart;
import com.blackdiamond.types.CleaningType;
import com.blackdiamond.types.PackagingType;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    // Crear una instancia de la tienda
    Store store = new Store(100, 1000);

    // Comprar productos para el inventario
    Packaged productoEnvasado = new Packaged("AB123", "producto envasado", 20, PackagingType.LATA, false, 500,
        "2024-04-08");
    Drink productoBebida = new Drink("AC345", 20, "producto bebida", false, false, 0, 600, "2024-08-09");
    CleaningProduct productoLimpieza = new CleaningProduct("AZ431", "producto limpieza", 20, CleaningType.MULTIUSO);

    store.buyProducts(productoEnvasado, 10);
    store.buyProducts(productoBebida, 5);
    store.buyProducts(productoLimpieza, 8);

    System.out.println("Saldo actual de la tienda: $ despues de la compra " + store.getBalance());

    // setear los precios de venta y el descuento
    productoEnvasado.setStockPrice(17);
    productoEnvasado.setDiscount(5);

    productoBebida.setStockPrice(14);
    productoBebida.setDiscount(11);

    productoLimpieza.setStockPrice(20);
    productoLimpieza.setDiscount(10);
    System.out.println(productoBebida.getDiscountPercent());
    // Agregar productos al carrito de compras

    store.addToCart(productoEnvasado, 2);
    store.addToCart(productoBebida, 3);

    // Vender los productos en el carrito
    store.sellProducts();
    

    // Obtener la lista de productos con descuento menor al 10%
    List<String> eatablesWithLessDiscount = store.obtainEatablesWithLessDiscount(10.0f);
    System.out.println("Productos con descuento menor al 10%: " + eatablesWithLessDiscount);

    // Listar productos con utilidad menor a $15
    List<String> lowUtilityProducts = store.listProductsWithLowerUtilities(15.0f);

    // Mostrar saldo actual de la tienda
    System.out.println("Saldo actual de la tienda: $" + store.getBalance());
  }
}
