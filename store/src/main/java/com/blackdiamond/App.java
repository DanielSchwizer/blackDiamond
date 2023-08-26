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
    Store blackDiamondStore = new Store(100, 1000);

    Drink coca = new Drink("AC123", 20,"COCA" , false, false, 0, 0, "2222-22-22");
    Packaged papas = new Packaged("AB123", "Papita", 20, PackagingType.PLASTICO, false, 1000, "24/08/2023");
    blackDiamondStore.buyProducts(papas, 10);
     blackDiamondStore.buyProducts(coca, 10);
     coca.setStockPrice(20);
     coca.setDiscount(14);
     System.out.println(coca.getStockPrice());
    System.out.println(coca.getDiscountPercent()+ "DESCUENTO");
    papas.setStockPrice(17);
    System.out.println("%" + papas.getGainPer());
    //papas.setDiscount(15);
    //papas.setIsImported(true);
    //papas.setGainStockPrice(20);
    //papas.setFinalStockPrice(20);

    //System.out.println("stock price: "+ papas.getDiscount());
    // blackDiamondStore.buyProducts(coca, 2);
    // blackDiamondStore.buyProducts(detergente, 4);
    // // System.out.println(papas.getStockPrice());
    // // System.out.println(detergente.getStockPrice());
    blackDiamondStore.addToCart(papas, 2);
    blackDiamondStore.addToCart(papas, 11);
    // blackDiamondStore.addToCart(detergente, 5);
    blackDiamondStore.sellProducts();
    System.out.println(blackDiamondStore.getList().containsValue(papas));
    System.out.println(blackDiamondStore.getList().toString());
    System.out.println(blackDiamondStore.getBalance() + "balance");
    System.out.println(blackDiamondStore.obtainEatablesWithLessDiscount(20).toString());
    blackDiamondStore.listProductsWithLowerUtilities(18);
  }
}
