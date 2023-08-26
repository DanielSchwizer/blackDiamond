package com.blackdiamond.shoppingcart;

import java.util.HashMap;

import com.blackdiamond.models.Product;

public class ShoppingCart {
    HashMap<String, Product> shoppingList = new HashMap<String, Product>();
    float gains;

    public void addProductToCart(Product product, int quantity) {
        if (!isProductAvailable(product, quantity)) {
            return;
        }
        if (!isShoppingCartFull()) {
            return;
        }
        if (quantity > 10) {
            System.out.println("limite de unidades alcanzado");
            return;
        }
        if (!hasSufficientStock(product, quantity)) {
            return;
        }
        product.removeProduct(quantity);
        gains += quantity * product.getStockPrice();
        shoppingList.put(product.getID(), product);
        System.out.println(printProduct(product, quantity));
    }

    private void handleInsufficientStock(Product product) {
        product.setIsForSale(false);
        product.setHasStock(false);
        gains += product.getStock() * product.getStockPrice();
        shoppingList.put(product.getID(), product);
        System.out.println("hay productos con stock disponible menor al solicitado");
        System.out.println(printProduct(product, product.getStock()));
        product.removeProduct(product.getStock());
    }

    private boolean isProductAvailable(Product product, int quantity) {
        if (!product.getIsForsale()) {
            System.out.println("el producto no esta disponible para la venta");
            return false;
        }
        if (!product.getHasStock()) {
            System.out.println("el producto no tiene stock");
            return false;
        }

        return true;
    }

    private boolean isShoppingCartFull() {
        if (shoppingList.size() > 2) {
            System.out.println("el carrito esta lleno");
            return false;
        }
        return true;
    }

    private boolean hasSufficientStock(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            return true;
        }

        handleInsufficientStock(product);
        return false;
    }

    private String printProduct(Product product, int quantity) {

        return String.format("%s %s %d x %f", product.getID(), product.getDescription(), quantity,
                product.getStockPrice());
    }

    public HashMap<String, Product> getShoppingList() {
        return shoppingList;
    }

    public float getGains() {
        return gains;
    }

}
