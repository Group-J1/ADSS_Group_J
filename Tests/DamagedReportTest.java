import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DamagedReportTest {

    @Test
    void addProductsToDamagedReport() {

       AProductCategory category = new AProductCategory("Milk Products");
       AProductSubCategory subCategory = new AProductCategory("Milk 3%");
       AProductSubCategory subSubCategory = new AProductSubCategory(1,"Litter") ;
       Location storeLocation = new Location(3,3);
       Location storageLocation = new Location(2,2);
       Date expDate = new Date(2023, Calendar.DECEMBER,12);

       Product product = new Product(category,subCategory,subSubCategory,storageLocation,storeLocation,"Yotvata",40,10,1.2,expDate);

       Stock stock = new Stock();
       stock.addNewProductToStock(product,100,13,10);
       DamagedReport damagedReport = new DamagedReport(stock);
       String str = damagedReport.toString();
//       product.markAsDamaged(product.get);








    }

    @Test
    void testToString() {
    }
}