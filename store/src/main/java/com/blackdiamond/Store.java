package com.blackdiamond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.blackdiamond.models.Packaged;
import com.blackdiamond.models.Product;
import com.blackdiamond.shoppingcart.ShoppingCart;

/**
 * Clase que representa una tienda que vende y compra productos.
 */
public class Store {
    final static String name = "BlackDiamondStore";
    int maxStock;
    float balance;
    HashMap<String, Product> list = new HashMap<String, Product>();
    ShoppingCart shoppingCart = new ShoppingCart();

    /**
     * Constructor de la clase Store.
     *
     * @param maxStock Máximo de stock que la tienda puede manejar.
     * @param balance  Saldo inicial de la tienda.
     */
    public Store(int maxStock, float balance) {
        this.maxStock = maxStock;
        this.balance = balance;
    }

    /**
     * Compra productos y agrega al inventario.
     *
     * @param product  Producto a comprar y agregar al inventario.
     * @param quantity Cantidad del producto a comprar.
     */
    public void buyProducts(Product product, int quantity) {
        if (!isOverstocked(quantity)) {
            return;
        }
        if (!isSufficientBalance(product, quantity)) {
            return;
        }
        if (!isStockReached(product, quantity)) {
            return;
        }
        balance -= quantity * product.getUnitPrice();
        product.addProduct(quantity);
        product.setHasStock(true);
        list.put(product.getID(), product);
        System.out.println(product.getDescription() + " fue comprado y agregado al stock");
    }

    /**
     * Vende los productos en el carrito de compras y actualiza el saldo de la
     * tienda.
     */
    public void sellProducts() {
        for (Map.Entry<String, Product> p : shoppingCart.getShoppingList().entrySet()) {
            list.replace(p.getKey(), p.getValue());
        }
        balance = balance + shoppingCart.getGains();

        System.out.println("TOTAL VENTA = " + shoppingCart.getGains());

    }

    /**
     * Agrega un producto y su cantidad al carrito de compras.
     *
     * @param product  Producto a agregar al carrito.
     * @param quantity Cantidad del producto a agregar al carrito.
     */
    public void addToCart(Product product, int quantity) {
        if (!list.containsKey(product.getID())) {
            System.out.println("el producto no existe en el inventario");
            return;
        }
        shoppingCart.addProductToCart(product, quantity);
    }

    /**
     * Obtiene una lista de productos comestibles NO importados con descuento menor
     * a un porcentaje dado.
     *
     * @param discountPer Porcentaje de descuento máximo.
     * @return Lista de descripciones de productos comestibles NO importados con
     *         descuento menor al porcentaje dado.
     */
    public List<String> obtainEatablesWithLessDiscount(float discountPer) {
        return list.values().stream()
                .filter(product -> product instanceof Packaged && !product.getisImported())
                .filter(Packaged -> Packaged.getDiscountPercent() < discountPer)
                .sorted((packaged1, packaged2) -> Float.compare(packaged1.getStockPrice(),
                        packaged2.getStockPrice()))
                .map(packaged -> packaged.getDescription().toUpperCase())
                .collect(Collectors.toList());
    }

    /**
     * Lista los productos con ganancias inferiores a un porcentaje dado y su
     * cantidad en stock.
     *
     * @param gainPer Porcentaje de ganancia máximo.
     * @return Lista de cadenas que contienen la información de los productos con
     *         ganancias inferiores y su stock.
     */
    public List<String> listProductsWithLowerUtilities(float gainPer) {
        List<String> resultList = new ArrayList<>();

        list.values().stream()
                .filter(product -> product.getGainPer() < gainPer)
                .forEach(product -> {
                    String productInfo = "Código: " + product.getID() +
                            "  Descripcion: " + product.getDescription() +
                            "  Cantidad en stock: " + product.getStock();
                    System.out.println(productInfo);
                    resultList.add(productInfo);
                });
        return resultList;
    }

    /**
     * Verifica si se ha alcanzado el límite de stock al agregar nuevos productos.
     *
     * @param quantity Cantidad de productos a agregar.
     * @return true si se puede agregar la cantidad de productos sin superar el
     *         límite de stock, false en caso contrario.
     */

    private boolean isOverstocked(int quantity) {
        if (list.size() + quantity > maxStock) {
            System.out.println("No se pueden agregar más productos, el stock llego a su limite.");
            return false;
        }
        return true;
    }

    /**
     * Verifica si hay suficiente saldo para realizar una compra.
     *
     * @param product  Producto a comprar.
     * @param quantity Cantidad de productos a comprar.
     * @return true si hay suficiente balance para la compra, false en caso
     *         contrario.
     */
    private boolean isSufficientBalance(Product product, int quantity) {
        if (product.getUnitPrice() * quantity > balance) {
            System.out.println("no hay dinero suficiente para ejecutar la transaccion");
            return false;
        }
        return true;
    }

    /**
     * Verifica si se alcanzará el límite de stock al agregar nuevos productos.
     *
     * @param product  Producto a agregar.
     * @param quantity Cantidad de productos a agregar.
     * @return true si se puede agregar la cantidad de productos sin superar el
     *         límite de stock, false en caso contrario.
     */
    private boolean isStockReached(Product product, int quantity) {
        if (product.getStock() + quantity >= maxStock) {
            System.out.println("limite de stock alcanzado por: " + product.getDescription());
            return false;
        }
        return true;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public float getBalance() {
        return balance;
    }

    public HashMap<String, Product> getList() {
        return list;
    }
}
