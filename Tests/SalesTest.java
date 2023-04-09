import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SalesTest {


    Sales sales = new Sales();
    AProductCategory category = new AProductCategory("Milk Products");
    AProductSubCategory subCategory = new AProductCategory("Milk 3%");
    AProductSubCategory subSubCategory = new AProductSubCategory(1,"Litter") ;
    Location storeLocation = new Location(3,3);
    Location storageLocation = new Location(2,2);
    Date expDate = new Date(2023, Calendar.DECEMBER,12);

    Product product = new Product(category,subCategory,subSubCategory,storageLocation,storeLocation,"Yotvata",40,10,1.2,expDate);

    @Test
    void addSale() {
        assertEquals(sales.howManyItemSold(),0);
        sales.addSale(product,23);
        sales.addSale(product,11);
        sales.addSale(product, 7);
        assertNotEquals(sales.howManyItemSold(),0);
        assertEquals(sales.howManyItemSold(),3);
        assertTrue(sales.checkIfSold(product,11));
        assertTrue(sales.checkIfSold(product,23));
        assertTrue(sales.checkIfSold(product,7));
        assertFalse(sales.checkIfSold(product,22));
        assertFalse(sales.checkIfSold(product,10));
        sales.resetSales();
        assertEquals(sales.howManyItemSold(),0);
        assertFalse(sales.checkIfSold(product,11));
        assertFalse(sales.checkIfSold(product,7));



    }


}