package com.blackdiamond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.blackdiamond.models.Product;
import com.blackdiamond.shoppingcart.ShoppingCart;

public class Store {
    String name = "BlackDiamondStore";
    int maxStock;
    float balance;
    HashMap<String, Product> list = new HashMap<String, Product>();
    ShoppingCart shoppingCart = new ShoppingCart();

    public Store(int maxStock, float balance) {
        this.maxStock = maxStock;
        this.balance = balance;
    }

    public void buyProducts(Product product, int quantity) {
        if (list.size() + quantity > maxStock) {
            System.out.println("No se pueden agregar más productos, el stock llego a su limite.");
            return;
        }

        if (product.getUnitPrice() * quantity > balance) {
            System.out.println("no hay dinero suficiente para ejecutar la transaccion");
            return;
        }
        if (product.getStock() + quantity >= maxStock) {
            System.out.println("limite de stock alcanzado por: " + product.getDescription());
            return;
        }
        balance -= quantity * product.getUnitPrice();
        product.addProduct(quantity);
        list.put(product.getID(), product);
        System.out.println(product.getDescription() + " fue comprado y agregado al stock");
    }

    public void sellProducts() {
        for (Map.Entry<String, Product> p : shoppingCart.getShoppingList().entrySet()) {
            list.replace(p.getKey(), p.getValue());
            System.out.println(list.toString());
        }

        System.out.println(list.toString());

    }

    public void addToCart(Product product, int quantity) {
        if(!list.containsKey(product.getID())){
            System.out.println("el producto no existe en el inventario");
            return;
        }
        shoppingCart.addProductToCart(product, quantity);
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public int getMaxStock() {
        return maxStock;
    }

}
