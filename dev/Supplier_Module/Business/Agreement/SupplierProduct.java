package Supplier_Module.Business.Agreement;
import Supplier_Module.Business.Discount.PrecentageDiscount;
import java.util.LinkedList;

public class SupplierProduct {
    private String product_name;
    private int local_key;
    private double unit_weight;
    private double unit_price;
    private int amount_available;
    private LinkedList<PrecentageDiscount> discounts;
    private int supplierID;

    /**constructor
     *
     */
    public SupplierProduct(String product_name, int local_key, double unit_weight, double unit_price, int amount_available, int supplierID) {
        this.product_name = product_name;
        this.local_key = local_key;
        this.unit_weight = unit_weight;
        this.unit_price = unit_price;
        this.amount_available = amount_available;
        this.discounts = new LinkedList<>();
        this.supplierID=supplierID;
    }

    /**constructor
     *
     */
    public SupplierProduct(String product_name, int local_key, double unit_weight, double unit_price, int amount_available, LinkedList<PrecentageDiscount> discounts,int supplierID) {
        this.product_name = product_name;
        this.local_key = local_key;
        this.unit_weight = unit_weight;
        this.unit_price = unit_price;
        this.amount_available = amount_available;
        this.discounts = discounts;
        this.supplierID=supplierID;

    }

    /**
     * Getters and Setters
     */
    public int getAmount_available() {
        return amount_available;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public void setAmount_available(int amount_available) {
        this.amount_available = amount_available;
    }

    public int getLocal_key() {
        return local_key;
    }


    public double getUnit_weight() {
        return unit_weight;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public LinkedList<PrecentageDiscount> getDiscounts() {
        return discounts;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    /**
     * function that print product info
     */
    public void printProductInfo()
    {
        System.out.println("Product Name: "+product_name+", Id: "+local_key+", Weight: "+unit_weight+", Price: "+unit_price);
    }

    public void printProductToOrder(int amount){
        double discount = 0;
        for(int i = 0; i < this.getDiscounts().size(); i++) {
            if (this.getDiscounts().get(i).getAmountRange().getMax() >= amount && this.getDiscounts().get(i).getAmountRange().getMin() <= amount) {
                discount=this.getDiscounts().get(i).getPercentage();
                break;
            }
        }
        double final_p = amount*this.getUnit_price()*(1-(0.01*discount));
        System.out.println("Product Number: "+this.getLocal_key()+", Product Name: "+this.getProduct_name()+", Amount:"
                + amount+ " Catalog Price:" + this.getUnit_price() + " Discount:"+ discount +
                " Final Price:" + final_p);
    }

    /**
     * function that print product discounts
     */
    public void printProductDiscounts()
    {
        for (PrecentageDiscount p:discounts)
        {
            p.printDiscount();
        }
        System.out.println("-----------------------------------------------");
    }
    public void printProduct(){
        printProductInfo();
        printProductDiscounts();
    }

    public String allDiscountToString(){
        String ans = "";
        for(PrecentageDiscount discount : this.discounts){
            ans+= discount.toString() + ",";
        }
        return ans;
    }

    public double discountByAmount(int amount)
    {
        double ans = 0;
        for(PrecentageDiscount discount: discounts)
        {
            int min=discount.getAmountRange().getMin();
            int max=discount.getAmountRange().getMax();
            if(amount>=min && amount<= max)
                return discount.getPercentage();
        }
        return ans;
    }

}

