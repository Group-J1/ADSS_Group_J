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

    // Case 1 in UI
    public boolean addNewProduct(String categoryStr, String subCategoryStr, String subSubCategoryStr, String manufacturer,
                                 int quantity, int minQuantity, double weight, Date expirationDate) {
        if (getProductByCategories(categoryStr, subCategoryStr, subSubCategoryStr) == null) {
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
            stock.addNewProductToStock(product, minQuantity + 100, minQuantity + 30, minQuantity);
            product.setStoreLocation(store.addProductToStore(product));
            product.setStorageLocation(storage.addProductToStorage(product));
            product.setCatalogNumber();
            System.out.println(product.getName() + " : " + (Product.productsCounter - quantity + 1) + "-" + Product.productsCounter);
            return true;
        } else {
            return false;
        }
    }


    // Case 2 in UI
    public Product getProductByCategories(String categoryStr, String subCategoryStr, String subSubCategoryStr) {
        String name = subCategoryStr + " " + subSubCategoryStr;
        String catalogNumber = UniqueStringGenerator.generateUniqueString(name);
        return stock.findProductByCatalogNumber(catalogNumber);
    }

    public boolean sellProductsByID(String productCatalogNumber, int quantitySold) {
        Product product = getByProductID(productCatalogNumber);
        if (product == null) {
            return false;
        }
        int[] sold = product.sellMultipleItemsFromProduct(quantitySold);
        for (int i = 0; i < quantitySold; i++) {
            sales.addSale(product, sold[i]);
        }
        if (product.getStoreQuantity() == 0) {
            shortages.addProductToShortages(product);
        }

        return true;
    }

    // Case 4 in UI
    public boolean appendStorage(int addedShelves) {
        storage.updateStorageShelvesNumber(addedShelves);
        return true;
    }

    public boolean appendStore(int addedShelves) {
        store.updateStoreShelvesNumber(addedShelves);
        return true;
    }

    // Case 5 in UI
    public Product getByProductID(String productCatalogNumber) {
        return stock.findProductByCatalogNumber(productCatalogNumber);
    }

    // Case 6 in UI
    public boolean createStockReport() {
        try {
            StockReport stockReport = new StockReport(stock, stock.getCategories());
            stockReport.addProductsToStockReport(stock, stock.getCategories());
            allReports.add(stockReport);
            System.out.println("-------- Stock Report: --------");
            System.out.println(stockReport);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean createOrderReport() {
        try {
            OrderReport orderReport = new OrderReport(stock);
            orderReport.addProductsToOrderReport(stock);
            allReports.add(orderReport);
            System.out.println("-------- Order Report: --------");
            System.out.println(orderReport);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean createDamagedReport() {
        try {
            DamagedReport damagedReport = new DamagedReport(stock);
            damagedReport.addProductsToDamagedReport(stock);
            allReports.add(damagedReport);
            System.out.println("-------- Damaged Report: --------");
            System.out.println(damagedReport);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // Case 7 in UI
    public void printProductInformation(int productInformationCase, Product product) {
        if (productInformationCase == 1) {
            System.out.println(product.getCatalogNumber());
        }
        if (productInformationCase == 2) {
            System.out.println(product.getName());
        }
    }

    // Case 8 in UI
    public boolean isCategoryExist(String categoryStr) {
        return stock.isCategoryExist(categoryStr);
    }

    public void setDiscountToCategory(String categoryStr, double discount) {
        AProductCategory aProductCategory = stock.getAProductCategory(categoryStr);
        aProductCategory.setDiscount(discount);
    }

    // Case 9 in UI
    public void printSold(){
        System.out.println("-----Sold-----");
        System.out.println(sales.toString());
    }

    // Case 11 in UI
    public void printShortages(){
        System.out.println("-----Shortages-----");
        System.out.println(shortages.toString());

    }

}