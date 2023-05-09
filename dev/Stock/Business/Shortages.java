package Stock.Business;

import java.util.ArrayList;

public class Shortages {
    private ArrayList<Product> missing;

    public Shortages() {
        /**
         * Constructs a new Stock.Business.Shortages object.
         */
        this.missing = new ArrayList<>();
    }

    public void addProductToShortages(Product product){
        /**
         * Adds a missing product to the list of shortages.
         *
         * This method adds the specified Stock.Business.Product object to the list of missing items. If the specified product is already in the
         * list of missing items, it will not be added again.
         *
         * @param product the Stock.Business.Product object to add to the list of missing items
         */
        if(!missing.contains(product)){
            missing.add(product);
        }
    }

    public boolean isMissing(Product product){
        /**
         * Checks if a product is on the list of missing items.
         *
         * @param product the Stock.Business.Product object to check for in the list of missing items
         * @return true if the product is on the list of missing items, false otherwise
         */
        return missing.contains(product);
    }

    public void updateMissing(ArrayList<Product> products){
        /**
         * Updates the list of missing items.
         *
         * This method updates the list of missing items based on the quantities of items in the store and storage. If an item
         * has a store quantity and a storage quantity of 0, it will be added to the list of missing items. The missing list is
         * then updated to only contain the new missing items found.
         *
         * @param products the list of Stock.Business.Product objects to check for missing items
         */
        ArrayList<Product> newMissing = new ArrayList<>();
        for(Product product: products){
            if(product.getStoreQuantity() == 0 && product.getStorageQuantity() == 0){
                newMissing.add(product);
            }
        }
        this.missing = newMissing;
    }

    public ArrayList<Product> getMissing() {
        return missing;
    }

    @Override
    public String toString(){
        /**
         * Returns a String representation of the missing items list.
         *
         * This method returns a String representation of the missing items list. Each missing item is listed on a new line.
         * If there are no missing items, the method returns the String "No shortages".
         *
         * @return a String representation of the missing items list
         */
        StringBuilder report = new StringBuilder();
        for(Product product: missing){
            report.append(product.getName()).append('\n');
        }
        if(report.toString().isEmpty()){
            report.append("No shortages");
        }
        return report.toString();
    }
}
