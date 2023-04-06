import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class StockReport extends Report{
    // < products category name, <product name (subCategory) and size (subSubCategory), quantity> >
    Map<String, Map<String, Integer>> products = new HashMap<>();;

    public StockReport(Stock stock, LinkedList categories) {
        Date currentDate = new Date();
        this.date = currentDate;
        this.id = ++reportsCounter;
        addProductsToStockReport(stock, categories);
    }

    // categories would be LinkedList of names for all the categories.
    public void addProductsToStockReport(Stock stock, LinkedList categories) {
        for (int i = 0; i < categories.size(); i++) {
            Map<Product, Integer> greenLineItems = stock.getGreenLineItems();
            Map<String, Integer> newCategory = new HashMap<>();
            greenLineItems.forEach((key, value) -> {
                if (key.getCategoryName() == categories.get(i)) {
                    newCategory.put(key.getName(), value);
                    products.put()
                }

            });

        }
    }



}
