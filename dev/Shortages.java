import java.util.ArrayList;



public class Shortages {
    private ArrayList<Product> missing;

    public Shortages() {
        this.missing = new ArrayList<>();
    }

    public void addProductToShortages(Product product){
        if(!missing.contains(product)){
            missing.add(product);
        }
    }



    public boolean isMissing(Product product){
        return missing.contains(product);
    }

    public void updateMissing(ArrayList<Product> products){
        ArrayList<Product> newMissing = new ArrayList<>();
        for(Product product: products){
            if(product.getStoreQuantity() == 0 && product.getStorageQuantity() == 0){
                newMissing.add(product);
            }
        }
        this.missing = newMissing;

    }

}
