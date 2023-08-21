package com.blackdiamond;

import com.blackdiamond.models.Drink;
import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.PackagingType;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Store blackDiamondStore = new Store(100, 100);
        
        Drink coca = new Drink("AC321", "Coca Cola", 7, 25, 10, true, false, false, 0);
        blackDiamondStore.buyProducts(coca, 6);
        blackDiamondStore.sellProducts(coca, 2);
    }
}
