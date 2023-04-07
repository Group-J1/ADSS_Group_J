import java.util.*;

public class DamagedReport extends Report {

    // < product name, <product id (barCode/ id), cause> >
    protected HashMap<String, Map<Integer, String>> products;

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
        String productInString = "";
        String productDetails = "";
        for (String productName : products.keySet()) {
            productInString = "Product: " + productName;
            stringBuilderStockReport.append(productInString).append(System.lineSeparator());
            Map<Integer, String> productNameData = products.get(productName);
            for (Integer dataBarCode : productNameData.keySet()) {
                String dataCause = productNameData.get(dataBarCode);
                productDetails = "- " + dataBarCode + ": " + dataCause;
                stringBuilderStockReport.append(productDetails).append(System.lineSeparator());
            }
        }
        return stringBuilderStockReport.toString();
    }

}
