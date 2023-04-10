import java.util.*;

/**
 * here is the stock.
 * functions:
 *       1.Integer getGreenLine(Product)
 *       2.Integer getRedLine(Product)
 *       3.Integer getBlackLine(Product)
 *       4.void addNewProductToStock(Product , Integer green, Integer red, Integer black)
 *       5.Integer getStatusInStock(Product )
 *       6. HashMap<Product ,Integer[] > getItemsInStock()
 */


public class Stock {
    private HashMap<Product ,Integer[] > stock;
    private  HashMap<String, Integer> categories;

    public Stock() {
        this.stock = new HashMap<>();
        this.categories = new HashMap<>();
    }

    public Integer getGreenLine(Product product){
       Integer[] val = stock.get(product);
       if (val == null){
            return  -1;     // if return -1 the item not in the Map
       }
       return val[0];
    }

    public Integer getRedLine(Product product){
        Integer[] val = stock.get(product);
        if (val == null){
            return  -1;     // if return -1 the item not in the Map
        }
        return val[1];
    }

    public Integer getBlackLine(Product product){
        Integer[] val = stock.get(product);
        if (val == null){
            return  -1;     // if return -1 the item not in the Map
        }
        return val[2];
    }

    public void addNewProductToStock(Product product, Integer green, Integer red, Integer black){
        if (green < red || red  < black || black < 1 || stock.get(product) != null){
            return;
        }
        String cat = product.getCategory().getName();
        if(categories.containsKey(cat)){
            categories.replace(cat,categories.get(cat) + 1 );
        }
        else{
            categories.put(cat,1);
        }
        Integer[] val = {green,red,black};
        stock.put(product, val);

    }

    public ArrayList<String> getCategories(){
        ArrayList<String> lst = new ArrayList<>(categories.keySet());
        return lst;
    }

    public int getStatusInStock(Product product){
        int status = 0;
        Integer[] val = stock.get(product);
        if(val == null){
            return -1;      // if -1 the item was not found in the stock
        }
        int quantity = product.getStorageQuantity() + product.getStoreQuantity();
        if(quantity <= val[2]){
            status = 1;
        }
        if(val[2] < quantity && quantity <= val[1])  {
            status = 2;
        }
        if(val[1] < quantity){
            status = 3;
        }
            return status;
    }

    public Product findProductByCatalogNumber(String catalogNumber) {
        for (Product product : stock.keySet()) {
            if (product.getCatalogNumber().equals(catalogNumber)) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> getListOfProducts(){
        ArrayList<Product> products = new ArrayList<>(stock.keySet());
        return products;
    }

    public HashMap<Product ,Integer[] > getItemsInStock() {
        return stock;
    }
}
