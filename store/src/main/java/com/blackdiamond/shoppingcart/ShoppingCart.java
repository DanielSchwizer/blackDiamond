package com.blackdiamond.shoppingcart;

import java.util.HashMap;

import com.blackdiamond.models.Product;

public class ShoppingCart {
    HashMap<String, Product> shoppingList = new HashMap<String, Product>();

    public void addProductToCart(Product product, int quantity) {
        if (!product.getHasStock()) {
            System.out.println("el producto no tiene stock");
            return;
        }

        if (!product.getIsForsale()) {
            System.out.println("el producto no esta disponible para la venta");
            return;
        }

        if (shoppingList.size() > 2) {
            System.out.println("el carrito esta lleno");
            return;
        }

        if (product.getStock() < quantity) {
            product.removeProduct(product.getStock());
            product.setIsForSale(false);
            shoppingList.put(product.getID(), product);
            System.out.println("hay productos con stock disponible menor al solicitado");
            System.out.println(product.getID() + " " + product.getDescription() + " x " + product.getStockPrice());
            return;
        }
        if (quantity > 10) {
            System.out.println("limite de unidades alcanzado");
            return;
        }
        product.removeProduct(quantity);
        shoppingList.put(product.getID(), product);
        System.out.println(product.getID() + " " + product.getDescription() + " x " + product.getStockPrice());
    }

    public HashMap<String, Product> getShoppingList() {
        return shoppingList;
    }
}
