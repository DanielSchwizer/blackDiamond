package com.blackdiamond.shoppingcart;

import java.util.HashMap;

import com.blackdiamond.models.Product;

public class ShoppingCart {
    HashMap<String,Product> shoppingList = new HashMap<String,Product>();

    public void addProductToCart(Product product,int quantity){
        if(shoppingList.size() > 3){
            System.out.println("el carrito esta lleno");
            return;
        }

        if(product.getStock() < quantity){
            System.out.println("no hay tantas unidades en stock");
            return;
        }
        if(quantity > 10){
            System.out.println("limite de unidades alcanzado");
            return;
        }
        shoppingList.put(product.getID(), product);
        System.out.println(product.getID() + " x " + product.getStockPrice());
    }
}
