import java.util.*;

/**
 * here is the stock.
 * functions:
 *       1.Integer getGreenLine(Product)
 *       2.Integer getRedLine(Product)
 *       3.Integer getBlackLine(Product)
 *       4.void addProductToStock(Product , Integer green, Integer red, Integer black)
 *       5.Integer getStatusInStock(Product )
 */


public class Stock {
    private HashMap<Product ,Integer[] > stock;

    public Stock() {
        this.stock = new HashMap<>();
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

    public void addProductToStock(Product product, Integer green, Integer red, Integer black){
        if (green < red || red  < black || black < 1 || stock.get(product) != null){
            return;
        }
        Integer[] val = {green,red,black};
        stock.put(product, val);
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

    public HashMap<Product ,Integer[] > getItemsInStock() {
        return stock;
    }
}
