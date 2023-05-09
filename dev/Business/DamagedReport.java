package Business;

import java.util.*;

public class DamagedReport extends Report {

    // < product name, <product id (barCode/ id), cause> >
    protected HashMap<String, Map<Integer, String>> products;

    public DamagedReport(Stock stock) {
        /**
         * Constructs a new Business.DamagedReport object based on the given Business.Stock object.
         * @param stock The Business.Stock object to generate the Business.DamagedReport from.
         * @return None
         * @throws None
         */
        products = new HashMap<>();;
        this.date = new Date();;
        this.id = ++Report.reportsCounter;
        addProductsToDamagedReport(stock);
    }

    public void addProductsToDamagedReport(Stock stock) {
        /**
         * Adds the damaged products from the given Business.Stock object to the current Business.DamagedReport object.
         * @param stock The Business.Stock object to extract damaged products from.
         * @return None
         * @throws None
         */
        Map<Product, Integer []> itemsInStock = stock.getItemsInStock();
        Map<Integer, String> newProduct = new HashMap<>();
        Set<Product> allProducts = itemsInStock.keySet();
        for (Product product : allProducts) {
            if (!product.getDamagedProducts().isEmpty()) {
                product.getDamagedProducts().forEach((damagedProductsId, value) -> {
                    newProduct.put(damagedProductsId, value);
                });
            }
            if(!newProduct.isEmpty()) {
                products.put(product.getName(), newProduct);
            }
        }
    }

    @Override
    public String toString() {
        /**
         * Returns a string representation of the current Business.DamagedReport object.
         * @param None.
         * @return A string containing the list of products with their associated damaged products and causes, separated by new lines. If the Business.DamagedReport object doesn't contain any products, the function returns a string stating that there are no damaged items.
         * @throws None
         */
        StringBuilder stringBuilderStockReport = new StringBuilder();
        String productInString = "";
        String productDetails = "";
        for (String productName : products.keySet()) {
            productInString = "Business.Product: " + productName;
            stringBuilderStockReport.append(productInString).append(System.lineSeparator());
            Map<Integer, String> productNameData = products.get(productName);
            for (Integer dataBarCode : productNameData.keySet()) {
                String dataCause = productNameData.get(dataBarCode);
                productDetails = "- " + dataBarCode + ": " + dataCause;
                stringBuilderStockReport.append(productDetails).append(System.lineSeparator());
                System.out.println("here");
            }
        }
        if (stringBuilderStockReport.toString().isEmpty()){
            stringBuilderStockReport.append("There are no damaged items").append(System.lineSeparator());
        }
        return stringBuilderStockReport.toString();
    }

}
