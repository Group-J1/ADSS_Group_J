import java.util.*;

public class DamagedReport extends Report {

    // < product name, <product id (barCode/ id), cause> >
    protected Map<String, Map<Integer, String>> products;

    public DamagedReport(Stock stock) {
//        Date currentDate = new Date();
        products = new HashMap<>();;
        this.date = new Date();;
        this.id = ++reportsCounter;
        addProductsToDamagedReport(stock);
    }

    public void addProductsToDamagedReport(Stock stock) {
        Map<Product, Integer []> itemsInStock = stock.getItemsInStock();
//            Map<String, Integer> newCategory = new HashMap<String, Integer>();
        Map<Integer, String> newProduct = new HashMap<>();
        Set<Product> allProducts = itemsInStock.keySet();
        for (Product product : allProducts) {
            if (!product.getDamagedProducts().isEmpty()) {
                product.getDamagedProducts().forEach((damagedProductsId, value) -> {
                    newProduct.put(damagedProductsId, value);
                });
            }
            products.put(product.getName(), newProduct);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilderStockReport = new StringBuilder();
        for (String productName : products.keySet()) {
            System.out.println("Product: " + productName);
            Map<Integer, String> productNameData = products.get(productName);
            for (Integer dataBarCode : productNameData.keySet()) {
                String dataCause = productNameData.get(dataBarCode);
                System.out.println("- " + dataBarCode + ": " + dataCause);
            }
            System.out.println();
        }
        return stringBuilderStockReport.toString();
    }

}
