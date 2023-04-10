import java.util.*;

public class OrderReport extends Report {
    // <product name (subCategory) and size (subSubCategory), quantity>
    protected HashMap<String, Integer> products;

    public OrderReport(Stock stock) {
        /**
         * Constructs a new instance of an OrderReport.
         * @param stock the Stock object that contains the inventory information.
         */
        products = new HashMap<>();;
        this.date = new Date();;
        this.id = ++reportsCounter;
        addProductsToOrderReport(stock);
    }

    public void addProductsToOrderReport(Stock stock) {
        Map<Product, Integer []> itemsInStock = stock.getItemsInStock();
        Set<Product> allProducts = itemsInStock.keySet();
        int howMuchToOrder;
        for (Product product : allProducts) {
            if (stock.getStatusInStock(product) <= 2) {
                howMuchToOrder = stock.getGreenLine(product) - (product.getStoreQuantity() + product.getStorageQuantity());
                products.put(product.getName(),howMuchToOrder);
            }
        }
    }

    @Override
    public String toString() {
        /**
         * Returns a string representation of the StockReport object. The string includes the names and quantities of all the products in the report.
         * @return a string containing the names and quantities of all the products in the report
         */
        StringBuilder stringBuilderStockReport = new StringBuilder();
        String productInString = "";
        for (String productName : products.keySet()) {
            Integer dataQuantity = products.get(productName);
            productInString = "- " + productName + ": " + dataQuantity;
            stringBuilderStockReport.append(productInString).append(System.lineSeparator());
        }
        return stringBuilderStockReport.toString();
    }
}
