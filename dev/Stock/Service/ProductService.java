package Stock.Service;

import Stock.Business.ProductManager;

import java.util.Date;

public class ProductService {

    private static final ProductManager productManager = ProductManager.getInstance();

    private static ProductService instance = null;
    private ProductService() {
        // private constructor
    }
    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    // other methods and variables of the class

    public boolean addNewProduct(String categoryStr, String subCategoryStr, String subSubCategoryStr, String manufacturer,
                                 int quantity, int minQuantity, double weight, Date expirationDate) {
        return productManager.addNewProduct(categoryStr, subCategoryStr, subSubCategoryStr, manufacturer, quantity,
                minQuantity, weight, expirationDate);
    }
}
