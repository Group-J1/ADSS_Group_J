package Stock.Service;

import Stock.Business.Product;
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

    // ------------ Case 1 in Product UI ------------
    public boolean addNewProduct(String categoryStr, String subCategoryStr, String subSubCategoryStr, String manufacturer,
                                 int quantity, int minQuantity, double weight, Date expirationDate) {
        return productManager.addNewProduct(categoryStr, subCategoryStr, subSubCategoryStr, manufacturer, quantity,
                minQuantity, weight, expirationDate);
    }

    // ------------ Helper function for Case 2 in Product UI ------------
    public Product getProductByCategories(String subCategoryStr, String subSubCategoryStr) {
        return productManager.getProductByCategories(subCategoryStr, subSubCategoryStr);
    }

    // ------------ Case 2 in Product UI ------------
    public void addMoreItemsToProduct(Product product, Date expDate, int quantity) {
        productManager.addMoreItemsToProduct(product, expDate, quantity);
    }

    // ------------ Helper function for Case 3 in Product UI ------------
    public Product getProductByUniqueCode(String productCatalogNumber) {
        return productManager.getProductByUniqueCode(productCatalogNumber);
    }

    // ------------ Case 3 in Product UI ------------
    public void markAsDamaged(Product defectedProduct, int uniqueCode, String reason) {
        productManager.markAsDamaged(defectedProduct, uniqueCode, reason);
    }

}
