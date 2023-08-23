package com.blackdiamond;

import com.blackdiamond.models.CleaningProduct;
import com.blackdiamond.models.Drink;
import com.blackdiamond.models.Packaged;
import com.blackdiamond.types.CleaningType;
import com.blackdiamond.types.PackagingType;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Store blackDiamondStore = new Store(100, 300);

        Drink coca = new Drink("AC321", "Coca Cola", 7, 25, true, false, false, 0);
        Packaged papas = new Packaged("AB234", "papas", 10, false, PackagingType.PLASTICO, false, 20);
        CleaningProduct detergente = new CleaningProduct("AZ321", "detergente", 4, false,CleaningType.MULTIUSO, 27);
        //blackDiamondStore.buyProducts(coca, 6);
        System.out.println(papas.getStockPrice());
        System.out.println(detergente.getStockPrice());
        //blackDiamondStore.addToCart(coca, 7);
        //System.out.println(coca.getStock());
        //blackDiamondStore.sellProducts();
    }
}
