import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ShortagesTest {

    Stock stock = new Stock();
    AProductCategory category = new AProductCategory("Milk Products");
    AProductSubCategory subCategory = new AProductCategory("Milk 3%");
    AProductSubCategory subSubCategory = new AProductSubCategory(1,"Litter") ;
    Location storeLocation = new Location(3,3);
    Location storageLocation = new Location(2,2);
    Date expDate = new Date(2023, Calendar.DECEMBER,12);

    Product product = new Product(category,subCategory,subSubCategory,storageLocation,storeLocation,"Yotvata",0,10,1.2,expDate);
    Product product2 = new Product(category,subCategory,subSubCategory,storageLocation,storeLocation,"Tnuva",40,10,1.2,expDate);

    Shortages shortages = new Shortages();


    @Test
    void updateMissing() {
        stock.addNewProductToStock(product,100,30,10);
        stock.addNewProductToStock(product2,100,30,10);
        shortages.updateMissing(stock.getListOfProducts());
        assertFalse(shortages.isMissing(product2));
        assertTrue(shortages.isMissing(product));

    }



}