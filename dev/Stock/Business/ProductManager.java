package Stock.Business;

import Stock.DataAccess.DamagedProductDAO;
import Stock.DataAccess.ExpDateDAO;
import Stock.DataAccess.ProductDAO;
import Stock.DataAccess.ProductDetailsDAO;

import java.util.Date;

public class ProductManager {

    private static final ProductDAO productDAO = ProductDAO.getInstance();
    private static final ExpDateDAO expDateDAO = ExpDateDAO.getInstance();
    private static final DamagedProductDAO damagedProductDAO = DamagedProductDAO.getInstance();


    private static ProductManager instance = null;

    private static Store store;//= new Store(30); // Freshie check
    private static Storage storage;// = new Storage(30);// Freshie check

    private ProductManager() {
        // private constructor

    }
    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    // other methods and variables of the class

    // Case 1 at Product's menu
    public boolean addNewProduct(String categoryStr, String subCategoryStr, String subSubCategoryStr, String manufacturer,
                                 int quantity, int minQuantity, double weight, Date expirationDate) {
        /**
         * Add a new product to the system.
         * @param categoryStr the name of the product category.
         * @param subCategoryStr the name of the product sub-category.
         * @param subSubCategoryStr the name of the product sub-sub-category.
         * @param manufacturer the name of the product manufacturer.
         * @param quantity the quantity of the new product to add.
         * @param minQuantity the minimum quantity of the new product to have in stock.
         * @param weight the weight of the new product.
         * @param expirationDate the expiration date of the new product.
         * @return true if the product was successfully added, false otherwise.
         */
        // New one
        if (getProductByCategories(subCategoryStr, subSubCategoryStr) == null) {
            // Old one
            //if (getProductByCategories(categoryStr, subCategoryStr, subSubCategoryStr) == null) {
            AProductCategory Ccategory = new AProductCategory(categoryStr);
            AProductCategory CsubCategoryStr = new AProductCategory(subCategoryStr);
            String[] parts = subSubCategoryStr.split(" ");
            double number = Double.parseDouble(parts[0]);
            String unit = parts[1];
            AProductSubCategory CsubSubCategoryStr = new AProductSubCategory(number, unit);
            Location storageLocation = new Location(-1, -1);
            Location storeLocation = new Location(-1, -1);
            Product product = new Product(Ccategory, CsubCategoryStr, CsubSubCategoryStr, storageLocation, storeLocation,
                    manufacturer, quantity, minQuantity, weight, expirationDate);
            // New one
            productDAO.addNewProductToProducts(product);

            // Old one
            //stock.addNewProductToStock(product, minQuantity + 100, minQuantity + 30, minQuantity);

            product.setStoreLocation(store.addProductToStore(product));
            product.setStorageLocation(storage.addProductToStorage(product));
            product.setCatalogNumber();
            productDAO.writeProducts(); // Freshie check
            ProductDetailsDAO.saveDetails(); // Freshie check
            System.out.println(product.getName() + " : " + (ProductDetailsDAO.getProductIdNoUpdate() - quantity + 1)
                    + "-" + ProductDetailsDAO.getProductIdNoUpdate());
            return true;
        } else {
            return false;
        }
    }

    // ------------ Helper function for Case 2 in Product UI ------------
    public Product getProductByCategories(String subCategory,String subSubCategory){
        String[] subsubSplited = subSubCategory.split(" ");
        String name = subCategory + " " + Double.toString(Double.parseDouble(subsubSplited[subsubSplited.length - 2]))
                + " " + subsubSplited[subsubSplited.length - 1];
        String productCatalogNumber = UniqueStringGenerator.generateUniqueString(name);
        return productDAO.getProduct(productCatalogNumber);
    }

    // Case 2 at Product's menu
    public void addMoreItemsToProduct(Product product, Date expDate, int quantity){
        product.addMoreItemsToProduct(quantity,expDate);
        //productDAO.getInstance();
        productDAO.writeProducts();
        //ExpDateDAO.getInstance();
        expDateDAO.writeExpDates();
    }


    // ------------ Helper function for Case 3 in Product UI ------------
    public Product getProductByUniqueCode(String productCatalogNumber) {
        return productDAO.getProduct(productCatalogNumber);
    }

    // Case 3 at Product's menu
    public void markAsDamaged(Product defectedProduct, int uniqueCode, String reason){
        defectedProduct.markAsDamaged(uniqueCode, reason);
        //productDAO.getInstance();
        productDAO.writeProducts();
        //ExpDateDAO.getInstance();
        damagedProductDAO.writeDamagedProducts();
    }






    public static void setStore(Store store) {          // freshie change
        ProductManager.store = store;
    }

    public static void setStorage(Storage storage) {
        ProductManager.storage = storage;               // freshie change
    }
}
