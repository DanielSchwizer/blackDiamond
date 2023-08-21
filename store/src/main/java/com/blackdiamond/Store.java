package com.blackdiamond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.blackdiamond.models.Product;

public class Store {
    String name = "BlackDiamondStore";
    int maxStock;
    float balance;
    HashMap<String, Product> list = new HashMap<String, Product>();

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
        if(product.getStock() + quantity >= maxStock){
            System.out.println("limite de stock alcanzado por: " + product.getDescription());
            return;
        }
        balance -= quantity * product.getUnitPrice();
        product.addProduct(quantity);
        list.put(product.getID(), product);
        System.out.println(product.getDescription() + " fue comprado y agregado al stock");
    }

    public void sellProducts(Product product,int quantity){
        if(!product.getHasStock()){
            System.out.println("el producto no tiene stock");
            return;
        }

        if(!product.getIsForsale()){
            System.out.println("el producto no esta disponible para la venta");
            return;
        }
        product.removeProduct(quantity);
        list.replace(product.getID(),product);
        System.out.println("despues de vender");
        System.out.println(list.toString());

    }



    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public int getMaxStock() {
        return maxStock;
    }


}
