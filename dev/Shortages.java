import java.util.HashMap;


/**
 * here is the Shortages:
 *      1.void addProductToShortages(Product, Integer)
 *      2.void resetMissingProduct(Product)
 *      3.void updateMissingValue(Product, Integer)
 *      4.Integer getMissingValue(Product)
 */
public class Shortages {
    private  HashMap<Product,Integer> missing;

    public Shortages() {
        this.missing = new HashMap<>();
    }

    public void addProductToShortages(Product product, Integer miss){
        missing.putIfAbsent(product, miss);
    }

    public void resetMissingProduct(Product product){
        missing.replace(product,0);
    }

    public void updateMissingValue(Product product,Integer newVal){
        missing.replace(product,newVal);
    }

    public Integer getMissingValue(Product product){
        int val = -1;
        if(missing.get(product) != null){
            val = missing.get(product);
        }
        return val;
    }

}
