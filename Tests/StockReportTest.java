import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class StockReportTest {

    AProductCategory category = new AProductCategory("Milk Products");
    AProductCategory subCategory = new AProductCategory("Milk 3%");
    AProductSubCategory subSubCategory = new AProductSubCategory(1,"Litter") ;

    Location storeLocation = new Location(3,3);
    Location storageLocation = new Location(2,2);
    Date expDate = new Date(2023, Calendar.DECEMBER,12);


    AProductCategory category1 = new AProductCategory("Meat");
    AProductCategory subCategory1 = new AProductCategory("Burgers");
    AProductSubCategory subSubCategory1 = new AProductSubCategory(1,"Kg") ;

    Location storeLocation1 = new Location(3,3);
    Location storageLocation1 = new Location(2,2);
    Date expDate1 = new Date(2023, Calendar.MAY,12);

    Product product = new Product(category,subCategory,subSubCategory,storageLocation,storeLocation,"Yotvata",40,10,1.2,expDate);
    Product product1 = new Product(category1,subCategory1,subSubCategory1,storageLocation1,storeLocation1,"Yumyum",30,20,1.0,expDate1);

    Stock stock = new Stock();
    StockReport stockReport = new StockReport(stock,stock.getCategories());

    @Test
    void addProductsToStockReport() {
        String firstReport = stockReport.toString();

        stock.addNewProductToStock(product,100,30,10);
        stockReport.addProductsToStockReport(stock,stock.getCategories());
        String secondReport = stockReport.toString();


        stock.addNewProductToStock(product1,100,30,10);
        stockReport.addProductsToStockReport(stock,stock.getCategories());
        String thirdReport = stockReport.toString();

        assertNotEquals(firstReport,secondReport);
        assertNotEquals(secondReport,thirdReport);

        assertTrue(thirdReport.length() > secondReport.length());
        assertTrue(secondReport.length() > firstReport.length());


    }
}