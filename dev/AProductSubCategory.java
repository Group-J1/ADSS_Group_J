public class AProductSubCategory extends  AProductCategory{

    private double amount;
    private String unit;

    public AProductSubCategory(double amount, String unit) {
        super((amount + unit));
        this.amount = amount;
        this.unit = unit;
    }

    public double getAmount() { return amount;}
    public String getUnit() { return unit;}

}
