package com.blackdiamond;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.blackdiamond.models.Packaged;
import com.blackdiamond.models.Product;
import com.blackdiamond.shoppingcart.ShoppingCart;

public class Store {
    final static String name = "BlackDiamondStore";
    int maxStock;
    float balance;
    HashMap<String, Product> list = new HashMap<String, Product>();
    ShoppingCart shoppingCart = new ShoppingCart();

    public Store(int maxStock, float balance) {
        this.maxStock = maxStock;
        this.balance = balance;
    }

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

    public void sellProducts() {
        for (Map.Entry<String, Product> p : shoppingCart.getShoppingList().entrySet()) {
            list.replace(p.getKey(), p.getValue());
            balance += shoppingCart.getGains();
        }

        System.out.println("TOTAL VENTA = " + shoppingCart.getGains());

    }

    public void addToCart(Product product, int quantity) {
        if (!list.containsKey(product.getID())) {
            System.out.println("el producto no existe en el inventario");
            return;
        }
        shoppingCart.addProductToCart(product, quantity);
    }

     public List<String> obtainEatablesWithLessDiscount (float discountPer) {
        return list.values().stream()
            .filter(product -> product instanceof Packaged && !product.getisImported())
            .map(product -> (Packaged) product)
            .filter(Packaged -> Packaged.getDiscountPercent() < discountPer)
            .sorted((comestible1, comestible2) -> Float.compare(comestible1.getStockPrice(), comestible2.getStockPrice()))
            .map(comestible -> comestible.getDescription().toUpperCase())
            .collect(Collectors.toList());
    }


     public  List<String> listProductsWithLowerUtilities(float gainPer) {
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
    private boolean isOverstocked(int quantity) {
        if (list.size() + quantity > maxStock) {
            System.out.println("No se pueden agregar más productos, el stock llego a su limite.");
            return false;
        }
        return true;
    }

    private boolean isSufficientBalance(Product product, int quantity) {
        if (product.getUnitPrice() * quantity > balance) {
            System.out.println("no hay dinero suficiente para ejecutar la transaccion");
            return false;
        }
        return true;
    }

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
    public HashMap<String, Product> getList(){
        return list;
    }
}
