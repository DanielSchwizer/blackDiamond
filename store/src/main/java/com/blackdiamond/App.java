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

      /* // Drink coca = new Drink("AC432", "COCA", 10, false, false,0,20); 
        //Packaged papas = new Packaged("AB234", "PAPAS", 10,PackagingType.PLASTICO ,false, 20);
        //CleaningProduct detergente = new CleaningProduct("AZ321", "detergente", 4, false,CleaningType.MULTIUSO, 27);
        blackDiamondStore.buyProducts(papas, 2);
        blackDiamondStore.buyProducts(coca, 2);
        //System.out.println(papas.getStockPrice());
        //System.out.println(detergente.getStockPrice());
        blackDiamondStore.addToCart(papas, 1);
        blackDiamondStore.addToCart(coca, 2);
        blackDiamondStore.sellProducts();
        System.out.println(blackDiamondStore.getBalance() + "balance");
    */}
}
