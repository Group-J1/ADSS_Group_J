package Stock.Service;

import Stock.Business.Product;
import Stock.Business.ProductManager;
import Stock.Business.ReportsManager;
import Stock.Business.Shortages;
import Stock.DataAccess.ProductDetailsDAO;

import java.util.Date;
import java.util.HashMap;

public class ProductService {

    private static final ProductManager productManager = ProductManager.getInstance();
    //private static final ReportsManager reportsManager = ReportsManager.getInstance();


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
        Product newProductIfSuccessfullyAdded = productManager.addNewProduct(categoryStr, subCategoryStr, subSubCategoryStr,
                manufacturer, quantity, minQuantity, weight, expirationDate);
        if (newProductIfSuccessfullyAdded != null) {
            HashMap<String, Integer> newProductToSupplier = new HashMap<>();
            newProductToSupplier.put(newProductIfSuccessfullyAdded.getCatalogNumber(), minQuantity);
            //functionToSupplierForNewProduct(newProductToSupplier);
        }
        return newProductIfSuccessfullyAdded != null;
    }

    // ------------ Helper function for Case 2.1 in Product UI ------------
    public Product getProductByCategories(String subCategoryStr, String subSubCategoryStr) {
        return productManager.getProductByCategories(subCategoryStr, subSubCategoryStr);
    }

    // ------------ Case 2.1 in Product UI ------------
    public void addMoreItemsToProduct(Product product, Date expDate, int quantity) {
        productManager.addMoreItemsToProduct(product, expDate, quantity);
    }

    // ------------ Case 2.2 in Product UI ------------
    public void sellProductsByUniqueCode(Product soldProduct, int quantitySold) {
        if (productManager.sellProductsByUniqueCode(soldProduct, quantitySold)) {
            Shortages shortagesForSupplier = new Shortages();
            //functionToSupplierForNewShortage(shortagesForSupplier.getMissing());
        };
    }

    // ------------ Helper function for Case 3 in Product UI ------------
    public Product getProductByUniqueCode(String productCatalogNumber) {
        return productManager.getProductByUniqueCode(productCatalogNumber);
    }

    // ------------ Case 3 in Product UI ------------
    public void markAsDamaged(Product defectedProduct, int uniqueCode, String reason) {
        productManager.markAsDamaged(defectedProduct, uniqueCode, reason);
    }

    // ------------ Case 4 in Product UI ------------
    public void printProductInformation(int productInformationCase, Product product) {
        productManager.printProductInformation(productInformationCase, product);
    }

    // ------------ Case 5 in Product UI ------------
    public void setMinimumQuantity(Product product, int newMinQuantity) {
        if (productManager.setMinimumQuantity(product, newMinQuantity)) {
            HashMap<String, Integer> newMinimumForProductToSupplier = new HashMap<>();
            newMinimumForProductToSupplier.put(product.getCatalogNumber(), newMinQuantity);
            //functionToSupplierForUpdateMinimumToProduct(newMinimumForProductToSupplier);
        }
    }

    public void sendToSupplierAllProductsQuantity() {
        HashMap<String, Integer> allProductsToSupplier = productManager.getAllProducts();
        if (allProductsToSupplier != null) {
            //functionToSupplierForInitializationAllProducts(allProductsToSupplier);
        }
    }

}
