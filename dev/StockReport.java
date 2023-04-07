import java.util.*;

public class StockReport extends Report{
    // < products category name, <product name (subCategory) and size (subSubCategory), quantity> >
    protected HashMap<String, Map<String, Integer>> products;

    public StockReport(Stock stock, ArrayList <String> categories) {
//        Date currentDate = new Date();
        products = new HashMap<>();;
        this.date = new Date();;
        this.id = ++reportsCounter;
        addProductsToStockReport(stock, categories);
    }

    // categories would be ArrayList of names for all the categories.
    // maybe arrayList as arg
    public void addProductsToStockReport(Stock stock, List <String> categories) {
        Map<Product, Integer []> itemsInStock = stock.getItemsInStock();
        for (String category : categories) {
//            Map<String, Integer> newCategory = new HashMap<String, Integer>();
            Map<String, Integer> newCategory = new HashMap<>();
            Set<Product> allProducts = itemsInStock.keySet();
            for (Product product : allProducts) {
//            itemsInStock.forEach((key, value) -> {
                if (product.getCategory().getName().equals(category)) {
                    newCategory.put(product.getName(), product.getStoreQuantity() + product.getStorageQuantity());
                }
            }
//            });
            products.put(category, newCategory);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilderStockReport = new StringBuilder();
        String title = "";
        String productInString = "";
        for (String category : products.keySet()) {
            title = "Category: " + category;
            stringBuilderStockReport.append(title).append(System.lineSeparator());
            Map<String, Integer> categoryData = products.get(category);
            for (String dataName : categoryData.keySet()) {
                Integer dataQuantity = categoryData.get(dataName);
                productInString = "- " + dataName + ": " + dataQuantity;
                stringBuilderStockReport.append(productInString).append(System.lineSeparator());
            }
        }
        return stringBuilderStockReport.toString();
    }

}
