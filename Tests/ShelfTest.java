import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

    Shelf shelf = new Shelf(30);
    Product[] example;
    AProductCategory category = new AProductCategory("Milk Products");
    AProductCategory subCategory = new AProductCategory("Milk 3%");
    AProductSubCategory subSubCategory = new AProductSubCategory(1,"Litter") ;
    Location storeLocation = new Location(3,3);
    Location storageLocation = new Location(2,2);
    Date expDate = new Date(2023, Calendar.DECEMBER,12);

    Product product = new Product(category,subCategory,subSubCategory,storageLocation,storeLocation,"Yotvata",40,10,1.2,expDate);


    @Test
    void getItems() {
        assertEquals(example.getClass(),shelf.getItems().getClass());
    }

    @Test
    void addItemToShelf() {
        assertEquals(shelf.nextFreeIndex(),0);
        shelf.addItemToShelf(product, shelf.nextFreeIndex());
        assertEquals(shelf.getItems()[0],product);
        assertNotEquals(shelf.nextFreeIndex(),0);
        assertEquals(shelf.nextFreeIndex(),1);
    }

}