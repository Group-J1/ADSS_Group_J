import java.util.ArrayList;
import java.util.Date;

public class Market {
    Store store;
    Storage storage;
    Sales sales;
    Shortages shortages;
    Stock stock;
    ArrayList<Report> allReports;

    public Market(int numberOfShelves) {
        store = new Store(numberOfShelves);
        storage = new Storage(numberOfShelves);
        sales = new Sales();
        shortages = new Shortages();
        stock = new Stock();
        allReports = new ArrayList<>();
    }


    public boolean addNewProduct(String categoryStr, String subCategoryStr, String subSubCategoryStr, String manufacturer,
                              int quantity, int minQuantity, double weight, Date expirationDate) {
        AProductCategory Ccategory = new AProductCategory(categoryStr);
        AProductCategory CsubCategoryStr = new AProductCategory(subCategoryStr);
        String[] parts = subSubCategoryStr.split(" ");
        double number = Double.parseDouble(parts[0]);
        String unit = parts[1];
        AProductSubCategory CsubSubCategoryStr = new AProductSubCategory(number, unit);
        Location storageLocation  = new Location(-1, -1);
        Location storeLocation = new Location(-1, -1);
        Product product = new Product(Ccategory, CsubCategoryStr, CsubSubCategoryStr, storageLocation, storeLocation,
                manufacturer, quantity, minQuantity, weight, expirationDate);
        product.setStoreLocation(store.addProductToStore(product));
        product.setStorageLocation(storage.addProductToStorage(product));
        return true;
    }
}
