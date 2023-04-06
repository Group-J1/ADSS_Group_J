import java.util.*;

public class OrderReport extends Report {
    // <product name (subCategory) and size (subSubCategory), quantity>
    protected Map<String, Integer> products;

    public OrderReport(Stock stock) {
//        Date currentDate = new Date();
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
        StringBuilder stringBuilderStockReport = new StringBuilder();
        for (String productName : products.keySet()) {
            Integer dataQuantity = products.get(productName);
            System.out.println("- " + productName + ": " + dataQuantity);
            System.out.println();
        }
        return stringBuilderStockReport.toString();
    }
}
