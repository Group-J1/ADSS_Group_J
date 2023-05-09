package Stock.Business;

import java.util.HashMap;
import java.util.ArrayList;

/*
 * here is  the Stock.Business.Sales:
 *      1.void addSale(Stock.Business.Product , Integer id)
 *      2.boolean checkIfSold(Stock.Business.Product , Integer id)
 */
public class Sales {
    private HashMap<Product,ArrayList<Integer>> sold;
    private int soldItems;

    public Sales() {
        /**
         * Constructs a new Stock.Business.Sales object with an empty map of sold items.
         */
        this.sold = new HashMap<>();
    }

    public void addSale(Product product, Integer id){
        /**
         * Adds the sale of a product with the given ID to the sales records.
         * If the product is not already in the sales records, creates a new entry for it.
         *
         * @param product the product that was sold
         * @param id      the ID of the sold product
         */
        if(sold.get(product) == null){
            ArrayList<Integer> val = new ArrayList<>();
            sold.put(product,val);
        }
        sold.get(product).add(id);
        soldItems++;
    }

    public void resetSales(){
        /**
         * Resets the sales records by setting the number of sold items to zero and creating a new empty map for sold items.
         */
        this.soldItems = 0;
        this.sold = new HashMap<>();
    }

    public int howManyItemSold(){/**
     * Returns the number of items sold.
     *
     * @return the number of items sold
     */return soldItems;}

    public boolean checkIfSold(Product product, Integer id){
        /**
         * Returns whether a product with the given ID has been sold or not.
         *
         * @param product the product to check
         * @param id the ID to look for
         * @return true if the product has been sold and the given ID exists in the sales records, false otherwise
         */
        if(sold.get(product) != null){
            return sold.get(product).contains(id);
        }
        return false;
    }

    public HashMap<Product, ArrayList<Integer>> getSold() {
        return sold;
    }


    @Override
    public String toString(){
        /**
         * Returns a string representation of the sales report.
         *
         * This method generates a string representation of the sales report by iterating through the sold products and their
         * associated barcodes. The resulting string includes each product's name and the barcodes of all the items sold for
         * that product. If no items were sold, the string "No items were sold" is returned instead.
         *
         * @return a string representation of the sales report
         */
        StringBuilder report = new StringBuilder();
        for (Product product: sold.keySet()) {
            report.append(product.getName()).append(" : ");
            for (Integer barcode: sold.get(product)){
                report.append(barcode + ", ");
            }
            report.append('\n');
        }
        if (report.toString().isEmpty())
            report.append("No item were sold");
        return report.toString();
    }


}