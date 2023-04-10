import java.util.HashMap;
import java.util.ArrayList;

/*
 * here is  the Sales:
 *      1.void addSale(Product , Integer id)
 *      2.boolean checkIfSold(Product , Integer id)
 */
public class Sales {
    private HashMap<Product,ArrayList<Integer>> sold;
    private int soldItems;

    public Sales() {
        this.sold = new HashMap<>();
    }

    public void addSale(Product product, Integer id){
        if(sold.get(product) == null){
            ArrayList<Integer> val = new ArrayList<>();
            sold.put(product,val);
        }
        sold.get(product).add(id);
        soldItems++;
    }

    public void resetSales(){
        this.soldItems = 0;
        this.sold = new HashMap<>();

    }

    public int howManyItemSold(){return soldItems;}

    public boolean checkIfSold(Product product, Integer id){
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